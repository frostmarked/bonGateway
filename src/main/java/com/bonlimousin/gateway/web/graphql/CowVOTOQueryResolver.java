package com.bonlimousin.gateway.web.graphql;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.bonlimousin.gateway.bff.BFFGraphQLUtil;
import com.bonlimousin.gateway.bff.BFFGraphQLUtil.GraphQLPageable;
import com.bonlimousin.gateway.bff.delegate.CowVOResourceDelegateImpl;
import com.bonlimousin.gateway.bff.mapper.graphql.CowVOTOMapper;
import com.bonlimousin.gateway.bff.mapper.graphql.PictureVOTOMapper;
import com.bonlimousin.gateway.config.CacheConfiguration;
import com.bonlimousin.gateway.web.api.model.CowVO;
import com.bonlimousin.gateway.web.api.model.PictureVO;
import com.bonlimousin.gateway.web.graphql.model.ApiPublicCowsPictures2QueryResolver;
import com.bonlimousin.gateway.web.graphql.model.ApiPublicCowsPicturesQueryResolver;
import com.bonlimousin.gateway.web.graphql.model.ApiPublicCowsQueryResolver;
import com.bonlimousin.gateway.web.graphql.model.BlupVOQueryResolver;
import com.bonlimousin.gateway.web.graphql.model.BlupVOTO;
import com.bonlimousin.gateway.web.graphql.model.CowVOQueryResolver;
import com.bonlimousin.gateway.web.graphql.model.CowVOTO;
import com.bonlimousin.gateway.web.graphql.model.GenderEqualsTO;
import com.bonlimousin.gateway.web.graphql.model.HornStatusInListItemTO;
import com.bonlimousin.gateway.web.graphql.model.PictureVOTO;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class CowVOTOQueryResolver
		implements ApiPublicCowsQueryResolver,
		ApiPublicCowsPicturesQueryResolver, ApiPublicCowsPictures2QueryResolver,
		BlupVOQueryResolver, CowVOQueryResolver, GraphQLQueryResolver {

	private CowVOResourceDelegateImpl cowVOResourceDelegateImpl;

	public CowVOTOQueryResolver(CowVOResourceDelegateImpl cowVOResourceDelegateImpl) {
		this.cowVOResourceDelegateImpl = cowVOResourceDelegateImpl;
	}

	@Override
	@Cacheable(value = CacheConfiguration.CACHE_COWS,
		condition = "T(com.bonlimousin.gateway.security.SecurityUtils).isAuthenticated() == false")
	public List<CowVOTO> apiPublicCows(String birthDateGreaterThan, String birthDateLessThan,
			GenderEqualsTO genderEquals, List<HornStatusInListItemTO> hornStatusIn, Integer linageIdEquals,
			Integer matriIdEquals, Integer page, Integer patriIdEquals, Integer size, List<String> sort,
			Integer weight0GreaterThan, Integer weight0LessThan, Boolean weight0Specified, Integer weight200GreaterThan,
			Integer weight200LessThan, Boolean weight200Specified, Integer weight365GreaterThan,
			Integer weight365LessThan, Boolean weight365Specified) throws Exception {
		GraphQLPageable p = GraphQLPageable.of(page, size, sort);
		List<String> hornStatusList = BFFGraphQLUtil.enumsToStrings(hornStatusIn);
		String gender = BFFGraphQLUtil.enumToString(genderEquals);
		// TODO convert
		OffsetDateTime birthDateGT = null;
		OffsetDateTime birthDateLT = null;
		List<CowVO> list = this.cowVOResourceDelegateImpl.findCowVOs(p.getPage(), p.getSize(), p.getSort(),
				linageIdEquals, birthDateGT, birthDateLT, gender, hornStatusList, matriIdEquals, patriIdEquals,
				weight0GreaterThan, weight0LessThan, weight0Specified, weight200GreaterThan, weight200LessThan,
				weight200Specified, weight365GreaterThan, weight365LessThan, weight365Specified).getBody();
		return list.stream().map(CowVOTOMapper.INSTANCE::voToTO).collect(Collectors.toList());
	}

	@Override
	public BlupVOTO blupVO(double earTagId) throws Exception {
		// TODO Auto-generated method stub
		// remove blup? or keep?
		throw new UnsupportedOperationException();
	}

	@Override
	@Cacheable(value = CacheConfiguration.CACHE_COWS,
		condition = "T(com.bonlimousin.gateway.security.SecurityUtils).isAuthenticated() == false")
	public CowVOTO cowVO(double earTagId) throws Exception {
		CowVO vo = this.cowVOResourceDelegateImpl.getCowVO((long)earTagId).getBody();
		return CowVOTOMapper.INSTANCE.voToTO(vo);
	}

	@Override
	public List<PictureVOTO> apiPublicCowsPictures(double earTagId, Integer page, Integer size, List<String> sort)
			throws Exception {
		GraphQLPageable p = GraphQLPageable.of(page, size, sort);
		List<PictureVO> list = this.cowVOResourceDelegateImpl
				.getAllPictureVOsByCow((long)earTagId, p.getPage(), p.getSize(), p.getSort()).getBody();
		return list.stream().map(PictureVOTOMapper.INSTANCE::voToTO).collect(Collectors.toList());
	}

    @Override
	public String apiPublicCowsPictures2(double earTagId, String name, double pictureId) throws Exception {
		// TODO Auto-generated method stub
		// base64 encode or nothing?
		throw new UnsupportedOperationException();
	}
}
