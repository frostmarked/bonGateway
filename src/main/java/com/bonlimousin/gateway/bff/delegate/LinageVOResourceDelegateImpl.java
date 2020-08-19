package com.bonlimousin.gateway.bff.delegate;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import com.bonlimousin.gateway.bff.BFFUtil;
import com.bonlimousin.gateway.bff.mapper.LinageVOMapper;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.api.MatrilinealityResourceApiClient;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.MatrilinealityEntity;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.querymap.MatrilinealityCriteria;
import com.bonlimousin.gateway.web.api.model.LinageVO;
import com.bonlimousin.gateway.web.problem.AlertProblem;
import com.bonlimousin.gateway.web.problem.AlertProblemSeverity;
import com.bonlimousin.gateway.web.rest.errors.WhileFetchingDataException;

import io.github.jhipster.service.filter.IntegerFilter;

@Service
public class LinageVOResourceDelegateImpl {

	private final MatrilinealityResourceApiClient matrilinealityResourceApiClient;

	public LinageVOResourceDelegateImpl(MatrilinealityResourceApiClient matrilinealityResourceApiClient) {
		super();
		this.matrilinealityResourceApiClient = matrilinealityResourceApiClient;
	}
	
	public ResponseEntity<List<LinageVO>> findAllLinageVOs(Integer page, Integer size, List<String> sort) {
		MatrilinealityCriteria criteria = new MatrilinealityCriteria();
		criteria.setVisibility(BFFUtil.createUserRoleFilterForCurrentUser());
		ResponseEntity<List<MatrilinealityEntity>> response = matrilinealityResourceApiClient
				.getAllMatrilinealitiesUsingGET(criteria, page, size, sort);
		List<LinageVO> list = response.getBody().stream().map(LinageVOMapper.INSTANCE::matrilinealityEntityToLinageVO)
				.collect(Collectors.toList());
		long totalCount = BFFUtil.extractTotalCount(response);
		return BFFUtil.createResponse(list, page, size, sort, totalCount);
	}
	
	public ResponseEntity<LinageVO> getLinageVO(Long earTagId) {
		MatrilinealityCriteria criteria = new MatrilinealityCriteria();
		criteria.setVisibility(BFFUtil.createUserRoleFilterForCurrentUser());
		criteria.setEarTagId((IntegerFilter) new IntegerFilter().setEquals(earTagId.intValue()));
		ResponseEntity<List<MatrilinealityEntity>> response = matrilinealityResourceApiClient
				.getAllMatrilinealitiesUsingGET(criteria, 0, 1, null);
		if (response.getBody().isEmpty()) {
			throw new WhileFetchingDataException(new AlertProblem("LineageVO does not exist", Status.NOT_FOUND, AlertProblemSeverity.WARNING, "entitynotfound", String.valueOf(earTagId)));
		}		
		MatrilinealityEntity entity = response.getBody().get(0);		
		LinageVO vo = LinageVOMapper.INSTANCE.matrilinealityEntityToLinageVO(entity);		
		return ResponseEntity.ok(vo);
	}
}
