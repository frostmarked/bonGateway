package com.bonlimousin.gateway.bff.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bonlimousin.gateway.client.boncontentservice.apidocs.model.TagEntity;
import com.bonlimousin.gateway.web.api.model.TagVO;

@Mapper
public interface TagVOMapper {

	TagVOMapper INSTANCE = Mappers.getMapper(TagVOMapper.class);

	TagVO tagEntityEntityToTagVO(TagEntity entity);
}