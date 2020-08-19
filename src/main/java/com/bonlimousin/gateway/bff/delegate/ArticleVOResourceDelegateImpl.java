package com.bonlimousin.gateway.bff.delegate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import com.bonlimousin.gateway.bff.BFFUtil;
import com.bonlimousin.gateway.bff.mapper.ArticleVOMapper;
import com.bonlimousin.gateway.bff.mapper.TagVOMapper;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.api.FragmentResourceApiClient;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.api.LocalizedResourceApiClient;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.api.StoryResourceApiClient;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.api.TagResourceApiClient;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.model.FragmentEntity;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.model.LocalizedEntity;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.model.StoryEntity;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.model.TagEntity;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.querymap.FragmentCriteria;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.querymap.LocalizedCriteria;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.querymap.StoryCategory;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.querymap.StoryCriteria;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.querymap.StoryCriteria.StoryCategoryFilter;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.querymap.TagCriteria;
import com.bonlimousin.gateway.config.Constants;
import com.bonlimousin.gateway.web.api.model.ArticleVO;
import com.bonlimousin.gateway.web.api.model.TagVO;
import com.bonlimousin.gateway.web.problem.AlertProblem;
import com.bonlimousin.gateway.web.problem.AlertProblemSeverity;
import com.bonlimousin.gateway.web.rest.errors.WhileFetchingDataException;

import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

@Service
public class ArticleVOResourceDelegateImpl {

	private final StoryResourceApiClient storyResourceApiClient;
	private final FragmentResourceApiClient fragmentResourceApiClient;
	private final LocalizedResourceApiClient localizedResourceApiClient;
	private final TagResourceApiClient tagResourceApiClient;

	public ArticleVOResourceDelegateImpl(StoryResourceApiClient storyResourceApiClient,
			FragmentResourceApiClient fragmentResourceApiClient, LocalizedResourceApiClient localizedResourceApiClient,
			TagResourceApiClient tagResourceApiClient) {
		super();
		this.storyResourceApiClient = storyResourceApiClient;
		this.fragmentResourceApiClient = fragmentResourceApiClient;
		this.localizedResourceApiClient = localizedResourceApiClient;
		this.tagResourceApiClient = tagResourceApiClient;
	}

	public ResponseEntity<List<ArticleVO>> getAllArticleVOs(String i18n, List<String> categoryIn, Integer page,
			Integer size, List<String> sort) {
		StoryCriteria criteria = new StoryCriteria();
		criteria.setVisibility(BFFUtil.createUserRoleFilterForCurrentUser());
		List<StoryCategory> scList = categoryIn != null
				? categoryIn.stream().map(StoryCategory::valueOf).collect(Collectors.toList())
				: null;
		criteria.setCategory((StoryCategoryFilter) new StoryCategoryFilter().setIn(scList));
		ResponseEntity<List<StoryEntity>> response = this.storyResourceApiClient.getAllStoriesUsingGET(criteria, page,
				size, sort);
		List<ArticleVO> list = response.getBody().stream().map(se -> this.populateStoryEntity(se, i18n))
				.map(ArticleVOMapper.INSTANCE::storyEntityToArticleVO).collect(Collectors.toList());
		long totalCount = BFFUtil.extractTotalCount(response);
		return BFFUtil.createResponse(list, page, size, sort, totalCount);
	}

	/*
	 * hmmm ... good enough, for now
	 */
	public ResponseEntity<List<ArticleVO>> searchArticleVOs(String query, String i18n, List<String> categoryIn,
			Integer page, Integer size, List<String> sort) {
		/*
		 * due to it should return a full article. in other words also the siblings of
		 * fragment or localized pageSize times 10 should be, by far, enough... or how
		 * many fragments are created in general
		 */
		List<Long> storyEntityIds;
		if (Constants.DEFAULT_LANGUAGE.equals(i18n)) {
			ResponseEntity<List<FragmentEntity>> response = this.fragmentResourceApiClient
					.searchFragmentsUsingGET(query, 0, size * 10, null);
			storyEntityIds = response.getBody().stream().map(se -> se.getStory().getId()).collect(Collectors.toList());
		} else {
			ResponseEntity<List<LocalizedEntity>> response = this.localizedResourceApiClient
					.searchLocalizedsUsingGET(query, 0, size * 10, null);
			storyEntityIds = response.getBody().stream().map(se -> se.getFragment().getStory().getId())
					.collect(Collectors.toList());
		}
		if (storyEntityIds.isEmpty()) {
			return BFFUtil.createResponse(Collections.emptyList(), page, size, sort, 0);
		}
		storyEntityIds = storyEntityIds.stream().distinct().collect(Collectors.toList());

		StoryCriteria criteria = new StoryCriteria();
		criteria.setVisibility(BFFUtil.createUserRoleFilterForCurrentUser());
		criteria.setId((LongFilter) new LongFilter().setIn(storyEntityIds));
		List<StoryCategory> scList = categoryIn != null
				? categoryIn.stream().map(StoryCategory::valueOf).collect(Collectors.toList())
				: null;
		criteria.setCategory((StoryCategoryFilter) new StoryCategoryFilter().setIn(scList));
		ResponseEntity<List<StoryEntity>> response = this.storyResourceApiClient.getAllStoriesUsingGET(criteria, page,
				size, sort);
		List<ArticleVO> list = response.getBody().stream().map(se -> this.populateStoryEntity(se, i18n))
				.map(ArticleVOMapper.INSTANCE::storyEntityToArticleVO).collect(Collectors.toList());
		long totalCount = BFFUtil.extractTotalCount(response);
		return BFFUtil.createResponse(list, page, size, sort, totalCount);
	}

	public ResponseEntity<ArticleVO> getArticleVOByIdOrHandle(String id, String i18n, Boolean isHandle) {
		Optional<StoryEntity> optStory = getStory(id, isHandle);
		if (!optStory.isPresent()) {						
			throw new WhileFetchingDataException(new AlertProblem("ArticleVO does not exist", Status.NOT_FOUND, AlertProblemSeverity.WARNING, "entitynotfound", id));
		}
		StoryEntity storyEntity = populateStoryEntity(optStory.get(), i18n);
		ArticleVO vo = ArticleVOMapper.INSTANCE.storyEntityToArticleVO(storyEntity);
		return ResponseEntity.ok(vo);
	}

	public ResponseEntity<List<TagVO>> getAllTagVOs(Integer page, Integer size, List<String> sort) {
		TagCriteria criteria = new TagCriteria();
		ResponseEntity<List<TagEntity>> response = this.tagResourceApiClient.getAllTagsUsingGET(criteria, page, size,
				sort);
		List<TagVO> list = response.getBody().stream()
				.map(TagVOMapper.INSTANCE::tagEntityEntityToTagVO).collect(Collectors.toList());
		long totalCount = BFFUtil.extractTotalCount(response);
		return BFFUtil.createResponse(list, page, size, sort, totalCount);
	}

	private StoryEntity populateStoryEntity(StoryEntity storyEntity, String i18n) {
		FragmentCriteria fragmentCriteria = new FragmentCriteria();
		fragmentCriteria.setVisibility(BFFUtil.createUserRoleFilterForCurrentUser());
		fragmentCriteria.setStoryId((LongFilter) new LongFilter().setEquals(storyEntity.getId()));
		ResponseEntity<List<FragmentEntity>> response = this.fragmentResourceApiClient
				.getAllFragmentsUsingGET(fragmentCriteria, 0, 100, null);

		List<FragmentEntity> fragments = response.getBody();
		if (Constants.DEFAULT_LANGUAGE.equals(i18n)) {
			storyEntity.setFragments(fragments);
		} else {
			storyEntity.setFragments(localizeFragmentEntities(i18n, fragments));
		}
		return storyEntity;
	}

	private List<FragmentEntity> localizeFragmentEntities(String i18n, List<FragmentEntity> fragments) {
		List<Long> fragmentIds = fragments.stream().map(FragmentEntity::getId).collect(Collectors.toList());
		LocalizedCriteria localizedCriteria = new LocalizedCriteria();
		localizedCriteria.seti18n((StringFilter) new StringFilter().setEquals(i18n));
		localizedCriteria.setFragmentId((LongFilter) new LongFilter().setIn(fragmentIds));
		ResponseEntity<List<LocalizedEntity>> locResponse = this.localizedResourceApiClient
				.getAllLocalizedsUsingGET(localizedCriteria, 0, 1000, null);
		List<FragmentEntity> frags = new ArrayList<>();
		for (LocalizedEntity loc : locResponse.getBody()) {
			FragmentEntity frag = loc.getFragment();
			frag.setTitle(loc.getTitle());
			frag.setIngress(loc.getIngress());
			frag.setBody(loc.getBody());
			frag.setCaption(loc.getCaption());
			frags.add(frag);
		}
		return frags;
	}

	private Optional<StoryEntity> getStory(String id, Boolean isHandle) {
		StoryCriteria storyCriteria = new StoryCriteria();
		storyCriteria.setVisibility(BFFUtil.createUserRoleFilterForCurrentUser());
		if (isHandle == null || !isHandle) {
			if (!NumberUtils.isParsable(id)) {
				return Optional.empty();
			}
			storyCriteria.setId((LongFilter) new LongFilter().setEquals(NumberUtils.toLong(id)));
		} else {
			storyCriteria.setName((StringFilter) new StringFilter().setEquals(id));
		}
		ResponseEntity<List<StoryEntity>> response = this.storyResourceApiClient.getAllStoriesUsingGET(storyCriteria, 0,
				1, null);
		if (response.getBody().isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(response.getBody().get(0));
	}
}
