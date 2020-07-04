package com.bonlimousin.gateway.bff.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.PhotoEntity;
import com.bonlimousin.gateway.web.api.model.PhotographVO;

@Mapper
public interface PhotographVOMapper {

	PhotographVOMapper INSTANCE = Mappers.getMapper(PhotographVOMapper.class);

	@Mapping(source = "cattle.earTagId", target = "earTagId")
	PhotographVO photoEntityToPhotographVO(PhotoEntity entity);
}