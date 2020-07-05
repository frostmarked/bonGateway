package com.bonlimousin.gateway.bff.mapper.graphql;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bonlimousin.gateway.web.api.model.TagVO;
import com.bonlimousin.gateway.web.graphql.model.TagVOTO;

@Mapper
public interface TagVOTOMapper {

	TagVOTOMapper INSTANCE = Mappers.getMapper(TagVOTOMapper.class);

	TagVOTO voToTO(TagVO vo);
}