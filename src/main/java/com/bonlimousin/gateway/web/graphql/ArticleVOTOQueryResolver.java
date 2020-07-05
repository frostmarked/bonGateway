package com.bonlimousin.gateway.web.graphql;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bonlimousin.gateway.bff.BFFGraphQLUtil;
import com.bonlimousin.gateway.bff.BFFGraphQLUtil.GraphQLPageable;
import com.bonlimousin.gateway.bff.delegate.ArticleVOResourceDelegateImpl;
import com.bonlimousin.gateway.bff.mapper.graphql.ArticleVOTOMapper;
import com.bonlimousin.gateway.bff.mapper.graphql.TagVOTOMapper;
import com.bonlimousin.gateway.web.api.model.ArticleVO;
import com.bonlimousin.gateway.web.api.model.TagVO;
import com.bonlimousin.gateway.web.graphql.model.ApiPublicArticlesQueryResolver;
import com.bonlimousin.gateway.web.graphql.model.ApiPublicSearchArticlesQueryResolver;
import com.bonlimousin.gateway.web.graphql.model.ApiPublicTagsQueryResolver;
import com.bonlimousin.gateway.web.graphql.model.ArticleVOQueryResolver;
import com.bonlimousin.gateway.web.graphql.model.ArticleVOTO;
import com.bonlimousin.gateway.web.graphql.model.CategoryInListItemTO;
import com.bonlimousin.gateway.web.graphql.model.I18nTO;
import com.bonlimousin.gateway.web.graphql.model.TagVOTO;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class ArticleVOTOQueryResolver implements ApiPublicArticlesQueryResolver, ApiPublicSearchArticlesQueryResolver,
		ApiPublicTagsQueryResolver, ArticleVOQueryResolver, GraphQLQueryResolver {

	private ArticleVOResourceDelegateImpl articleVOResourceDelegateImpl;

	public ArticleVOTOQueryResolver(ArticleVOResourceDelegateImpl articleVOResourceDelegateImpl) {
		this.articleVOResourceDelegateImpl = articleVOResourceDelegateImpl;
	}

	@Override
	public List<ArticleVOTO> apiPublicArticles(List<CategoryInListItemTO> categoryIn, I18nTO i18n,
			Integer page, Integer size, List<String> sort) throws Exception {
		String lang = BFFGraphQLUtil.enumToString(i18n);
		List<String> cats = BFFGraphQLUtil.enumsToStrings(categoryIn);
		GraphQLPageable p = GraphQLPageable.of(page, size, sort);
		List<ArticleVO> arts = this.articleVOResourceDelegateImpl.getAllArticleVOs(lang, cats, p.getPage(), p.getSize(), p.getSort())
				.getBody();
		return arts.stream().map(vo -> ArticleVOTOMapper.INSTANCE.voToTO(vo)).collect(Collectors.toList());
	}

	@Override
	public List<ArticleVOTO> apiPublicSearchArticles(List<CategoryInListItemTO> categoryIn, I18nTO i18n,
			Integer page, String query, Integer size, List<String> sort) throws Exception {
		String lang = BFFGraphQLUtil.enumToString(i18n);
		List<String> cats = BFFGraphQLUtil.enumsToStrings(categoryIn);
		GraphQLPageable p = GraphQLPageable.of(page, size, sort);
		List<ArticleVO> arts = this.articleVOResourceDelegateImpl.searchArticleVOs(query, lang, cats, p.getPage(), p.getSize(), p.getSort()).getBody();
		return arts.stream().map(vo -> ArticleVOTOMapper.INSTANCE.voToTO(vo)).collect(Collectors.toList());
	}

	@Override
	public List<TagVOTO> apiPublicTags(Integer page, Integer size, List<String> sort) throws Exception {
		GraphQLPageable p = GraphQLPageable.of(page, size, sort);
		List<TagVO> tags = this.articleVOResourceDelegateImpl.getAllTagVOs(p.getPage(), p.getSize(), p.getSort()).getBody();
		return tags.stream().map(vo -> TagVOTOMapper.INSTANCE.voToTO(vo)).collect(Collectors.toList());
	}

	@Override
	public ArticleVOTO articleVO(I18nTO i18n, String id, Boolean isHandle) throws Exception {
		String lang = BFFGraphQLUtil.enumToString(i18n);
		ArticleVO vo = this.articleVOResourceDelegateImpl.getArticleVOByIdOrHandle(id, lang, isHandle).getBody();
		return ArticleVOTOMapper.INSTANCE.voToTO(vo);
	}

}