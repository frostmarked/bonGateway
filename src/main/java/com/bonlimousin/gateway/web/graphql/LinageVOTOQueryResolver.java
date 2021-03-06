package com.bonlimousin.gateway.web.graphql;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.bonlimousin.gateway.bff.BFFGraphQLUtil.GraphQLPageable;
import com.bonlimousin.gateway.bff.delegate.LinageVOResourceDelegateImpl;
import com.bonlimousin.gateway.bff.mapper.graphql.LinageVOTOMapper;
import com.bonlimousin.gateway.config.CacheConfiguration;
import com.bonlimousin.gateway.web.api.model.LinageVO;
import com.bonlimousin.gateway.web.graphql.model.ApiPublicLinagesQueryResolver;
import com.bonlimousin.gateway.web.graphql.model.LinageVOQueryResolver;
import com.bonlimousin.gateway.web.graphql.model.LinageVOTO;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class LinageVOTOQueryResolver implements ApiPublicLinagesQueryResolver, LinageVOQueryResolver, GraphQLQueryResolver {

	private LinageVOResourceDelegateImpl linageVOResourceDelegateImpl;

	public LinageVOTOQueryResolver(LinageVOResourceDelegateImpl linageVOResourceDelegateImpl) {
		this.linageVOResourceDelegateImpl = linageVOResourceDelegateImpl;
	}

	@Override
	@Cacheable(value = CacheConfiguration.CACHE_LINEAGES,
		condition = "T(com.bonlimousin.gateway.security.SecurityUtils).isAuthenticated() == false")
	public List<LinageVOTO> apiPublicLinages(Integer page, Integer size, List<String> sort)
			throws Exception {
		GraphQLPageable p = GraphQLPageable.of(page, size, sort);
		List<LinageVO> list = this.linageVOResourceDelegateImpl.findAllLinageVOs(p.getPage(), p.getSize(), p.getSort()).getBody();
		return list.stream().map(LinageVOTOMapper.INSTANCE::voToTO).collect(Collectors.toList());
	}

	@Override
	@Cacheable(value = CacheConfiguration.CACHE_LINEAGES,
		condition = "T(com.bonlimousin.gateway.security.SecurityUtils).isAuthenticated() == false")
	public LinageVOTO linageVO(double earTagId) throws Exception {
		LinageVO vo = this.linageVOResourceDelegateImpl.getLinageVO((long)earTagId).getBody();
		return LinageVOTOMapper.INSTANCE.voToTO(vo);
	}
}
