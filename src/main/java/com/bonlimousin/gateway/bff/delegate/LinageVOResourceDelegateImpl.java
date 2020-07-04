package com.bonlimousin.gateway.bff.delegate;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bonlimousin.gateway.bff.BFFUtil;
import com.bonlimousin.gateway.bff.mapper.LinageVOMapper;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.api.MatrilinealityResourceApiClient;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.MatrilinealityEntity;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.querymap.MatrilinealityCriteria;
import com.bonlimousin.gateway.web.api.model.LinageVO;

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
		List<LinageVO> list = response.getBody().stream().map(entity -> LinageVOMapper.INSTANCE.matrilinealityEntityToLinageVO(entity))
				.collect(Collectors.toList());
		long totalCount = BFFUtil.extractTotalCount(response);
		return BFFUtil.createResponse(list, page, size, sort, totalCount);
	}
}
