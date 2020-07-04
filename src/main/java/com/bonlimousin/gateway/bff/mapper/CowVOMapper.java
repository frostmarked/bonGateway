package com.bonlimousin.gateway.bff.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.CattleEntity;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.MatrilinealityEntity;
import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.model.BovineEntity;
import com.bonlimousin.gateway.web.api.model.CowVO;

@Mapper
public interface CowVOMapper {

	CowVOMapper INSTANCE = Mappers.getMapper(CowVOMapper.class);

	@Mapping(source = "matrilinealityEntity.earTagId", target = "linageId")
	@Mapping(source = "matrilinealityEntity.familyname", target = "linageName")
	@Mapping(source = "cattleEntity.name", target = "name")
	@Mapping(source = "bovineEntity.earTagId", target = "earTagId")
	@Mapping(source = "cattleEntity.storyHandle", target = "storyHandle")
	@Mapping(source = "bovineEntity.patriId", target = "patriId")
	@Mapping(source = "cattleEntity.visibility", target = "visibility")
	CowVO entitiesToCowVO(MatrilinealityEntity matrilinealityEntity, CattleEntity cattleEntity, BovineEntity bovineEntity);
}