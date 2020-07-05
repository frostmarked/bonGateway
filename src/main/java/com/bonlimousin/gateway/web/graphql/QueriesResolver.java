package com.bonlimousin.gateway.web.graphql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bonlimousin.gateway.web.graphql.model.ArticleVOTO;
import com.bonlimousin.gateway.web.graphql.model.BlupVOTO;
import com.bonlimousin.gateway.web.graphql.model.CategoryInListItemTO;
import com.bonlimousin.gateway.web.graphql.model.CategoryTO;
import com.bonlimousin.gateway.web.graphql.model.CowVOTO;
import com.bonlimousin.gateway.web.graphql.model.GenderEqualsTO;
import com.bonlimousin.gateway.web.graphql.model.HornStatusInListItemTO;
import com.bonlimousin.gateway.web.graphql.model.I18nTO;
import com.bonlimousin.gateway.web.graphql.model.LinageVOTO;
import com.bonlimousin.gateway.web.graphql.model.PhotographVOTO;
import com.bonlimousin.gateway.web.graphql.model.QueryResolver;
import com.bonlimousin.gateway.web.graphql.model.TagVOTO;
import com.bonlimousin.gateway.web.graphql.model.VisibilityTO;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class QueriesResolver  implements QueryResolver, GraphQLQueryResolver {

	@Override
	public List<ArticleVOTO> apiPublicArticles(List<CategoryInListItemTO> categoryIn, I18nTO i18n, Integer page,
			Integer size, List<String> sort) throws Exception {
		// TODO Auto-generated method stub
		List<ArticleVOTO> list = new ArrayList<>();
		ArticleVOTO voto = new ArticleVOTO(CategoryTO.NEWS, 1d, "hoho", Collections.emptyList(), VisibilityTO.ROLE_ANONYMOUS);
		list.add(voto);
		return list;
	}

	@Override
	public List<PhotographVOTO> apiPublicCowPhotographs(Double earTagId, Integer page, Integer size, List<String> sort)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CowVOTO> apiPublicCows(String birthDateGreaterThan, String birthDateLessThan,
			GenderEqualsTO genderEquals, List<HornStatusInListItemTO> hornStatusIn, Integer linageIdEquals,
			Integer matriIdEquals, Integer page, Integer patriIdEquals, Integer size, List<String> sort,
			Integer weight0GreaterThan, Integer weight0LessThan, Boolean weight0Specified, Integer weight200GreaterThan,
			Integer weight200LessThan, Boolean weight200Specified, Integer weight365GreaterThan,
			Integer weight365LessThan, Boolean weight365Specified) throws Exception {
		// TODO Auto-generated method stub
		List<CowVOTO> list = new ArrayList<>();
		CowVOTO voto = new CowVOTO();
		voto.setName("cow1");
		list.add(voto);
		return list;
	}

	@Override
	public List<LinageVOTO> apiPublicLinages(Integer page, Integer size, List<String> sort) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleVOTO> apiPublicSearchArticles(List<CategoryInListItemTO> categoryIn, I18nTO i18n, Integer page,
			String query, Integer size, List<String> sort) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TagVOTO> apiPublicTags(Integer page, Integer size, List<String> sort) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticleVOTO articleVO(I18nTO i18n, String id, Boolean isHandle) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlupVOTO blupVO(Double earTagId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CowVOTO cowVO(Double earTagId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
