package com.bonlimousin.gateway.bff.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bonlimousin.gateway.client.boncontentservice.apidocs.model.StoryEntity;
import com.bonlimousin.gateway.web.api.model.ArticleVO;

@Mapper
public interface ArticleVOMapper {

	ArticleVOMapper INSTANCE = Mappers.getMapper(ArticleVOMapper.class);

	@Mapping(source = "fragments", target = "sections")
	ArticleVO storyEntityToArticleVO(StoryEntity entity);
}