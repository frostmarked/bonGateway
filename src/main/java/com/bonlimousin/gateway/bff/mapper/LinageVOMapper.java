package com.bonlimousin.gateway.bff.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.MatrilinealityEntity;
import com.bonlimousin.gateway.web.api.model.LinageVO;

@Mapper
public interface LinageVOMapper {

	LinageVOMapper INSTANCE = Mappers.getMapper(LinageVOMapper.class);

	LinageVO matrilinealityEntityToLinageVO(MatrilinealityEntity entity);
}