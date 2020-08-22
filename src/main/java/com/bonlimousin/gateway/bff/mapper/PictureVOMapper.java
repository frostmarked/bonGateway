package com.bonlimousin.gateway.bff.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.PhotoEntity;
import com.bonlimousin.gateway.web.api.model.PictureVO;

@Mapper
public interface PictureVOMapper {

	PictureVOMapper INSTANCE = Mappers.getMapper(PictureVOMapper.class);
	
	// TODO extend mapper with logic for handling the sources as well?
	@Mapping(target = "sources", ignore = true)
	public PictureVO photoEntityToPictureVO(PhotoEntity entity);
}