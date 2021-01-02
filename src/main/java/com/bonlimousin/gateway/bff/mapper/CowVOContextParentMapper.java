package com.bonlimousin.gateway.bff.mapper;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.CattleEntity;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.MatrilinealityEntity;
import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.model.BovineEntity;
import com.bonlimousin.gateway.web.api.model.CowVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CowVOContextParentMapper {

	CowVOContextParentMapper INSTANCE = Mappers.getMapper(CowVOContextParentMapper.class);

    @Mapping(source = "cattleEntity.name", target = "name")
    @Mapping(source = "cattleEntity.earTagId", target = "earTagId")
    @Mapping(source = "cattleEntity.visibility", target = "visibility")
    @Mapping(target = "storyHandle", ignore = true)
    CowVO entitiesToCowVO(CattleEntity cattleEntity);
}
