package com.bonlimousin.gateway.bff.mapper.graphql;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bonlimousin.gateway.web.api.model.LinageVO;
import com.bonlimousin.gateway.web.graphql.model.LinageVOTO;
import com.bonlimousin.gateway.web.graphql.model.VisibilityTO;

@Mapper
public interface LinageVOTOMapper {

	LinageVOTOMapper INSTANCE = Mappers.getMapper(LinageVOTOMapper.class);

	LinageVOTO voToTO(LinageVO vo);
	
	default VisibilityTO visibilityToVisibilityTO(LinageVO.VisibilityEnum visibilityEnum) {
		return VisibilityTO.valueOf(visibilityEnum.getValue());
    }
}