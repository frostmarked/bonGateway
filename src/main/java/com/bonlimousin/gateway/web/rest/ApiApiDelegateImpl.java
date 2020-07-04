package com.bonlimousin.gateway.web.rest;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bonlimousin.gateway.bff.delegate.ArticleVOResourceDelegateImpl;
import com.bonlimousin.gateway.bff.delegate.CowVOResourceDelegateImpl;
import com.bonlimousin.gateway.bff.delegate.LinageVOResourceDelegateImpl;
import com.bonlimousin.gateway.web.api.ApiApiDelegate;
import com.bonlimousin.gateway.web.api.model.ArticleVO;
import com.bonlimousin.gateway.web.api.model.CowVO;
import com.bonlimousin.gateway.web.api.model.LinageVO;
import com.bonlimousin.gateway.web.api.model.PhotographVO;
import com.bonlimousin.gateway.web.api.model.TagVO;

@Service
public class ApiApiDelegateImpl implements ApiApiDelegate {

	private final LinageVOResourceDelegateImpl linageVODelegate;
	private final CowVOResourceDelegateImpl cowVOResourceDelegate;
	private final ArticleVOResourceDelegateImpl articleVOResourceDelegate;

	public ApiApiDelegateImpl(LinageVOResourceDelegateImpl linageVODelegate,
			CowVOResourceDelegateImpl cowVOResourceDelegate, ArticleVOResourceDelegateImpl articleVOResourceDelegate) {
		super();
		this.linageVODelegate = linageVODelegate;
		this.cowVOResourceDelegate = cowVOResourceDelegate;
		this.articleVOResourceDelegate = articleVOResourceDelegate;
	}

	@Override
	public ResponseEntity<List<LinageVO>> findAllLinageVOs(Integer page, Integer size, List<String> sort) {
		return this.linageVODelegate.findAllLinageVOs(page, size, sort);
	}

	@Override
	public ResponseEntity<List<CowVO>> findCowVOs(Integer page, Integer size, List<String> sort, Integer linageIdEquals,
			OffsetDateTime birthDateGreaterThan, OffsetDateTime birthDateLessThan, String genderEquals,
			List<String> hornStatusIn, Integer matriIdEquals, Integer patriIdEquals, Integer weight0GreaterThan,
			Integer weight0LessThan, Boolean weight0Specified, Integer weight200GreaterThan, Integer weight200LessThan,
			Boolean weight200Specified, Integer weight365GreaterThan, Integer weight365LessThan,
			Boolean weight365Specified) {
		return this.cowVOResourceDelegate.findCowVOs(page, size, sort, linageIdEquals, birthDateGreaterThan,
				birthDateLessThan, genderEquals, hornStatusIn, matriIdEquals, patriIdEquals, weight0GreaterThan,
				weight0LessThan, weight0Specified, weight200GreaterThan, weight200LessThan, weight200Specified,
				weight365GreaterThan, weight365LessThan, weight365Specified);
	}

	@Override
	public ResponseEntity<CowVO> getCowVO(Long earTagId) {
		return this.cowVOResourceDelegate.getCowVO(earTagId);
	}

	@Override
	public ResponseEntity<List<PhotographVO>> getAllPhotographVOsByCow(Long earTagId, Integer page, Integer size,
			List<String> sort) {
		return this.cowVOResourceDelegate.getAllPhotographVOsByCow(earTagId, page, size, sort);
	}

	@Override
	public ResponseEntity<List<ArticleVO>> getAllArticleVOs(String i18n, List<String> categoryIn, Integer page, Integer size,
			List<String> sort) {
		return this.articleVOResourceDelegate.getAllArticleVOs(i18n, categoryIn, page, size, sort);
	}

	@Override
	public ResponseEntity<ArticleVO> getArticleVOByIdOrHandle(String id, String i18n, Boolean isHandle) {
		return this.articleVOResourceDelegate.getArticleVOByIdOrHandle(id, i18n, isHandle);
	}

	@Override
	public ResponseEntity<List<ArticleVO>> searchArticleVOs(String i18n, String query, Integer page, List<String> categoryIn, Integer size,
			List<String> sort) {
		return this.articleVOResourceDelegate.searchArticleVOs(query, i18n, categoryIn, page, size, sort);
	}

	@Override
	public ResponseEntity<List<TagVO>> getAllTagVOs(Integer page, Integer size, List<String> sort) {
		return this.articleVOResourceDelegate.getAllTagVOs(page, size, sort);
	}
}
