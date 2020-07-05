package com.bonlimousin.gateway.bff.mapper.graphql;

import java.util.Base64;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bonlimousin.gateway.web.api.model.ArticleVO;
import com.bonlimousin.gateway.web.api.model.SectionVO;
import com.bonlimousin.gateway.web.graphql.model.ArticleVOTO;
import com.bonlimousin.gateway.web.graphql.model.VisibilityTO;

@Mapper
public interface ArticleVOTOMapper {

	ArticleVOTOMapper INSTANCE = Mappers.getMapper(ArticleVOTOMapper.class);

	ArticleVOTO voToTO(ArticleVO vo);
	
	default VisibilityTO visibilityToVisibilityTO(ArticleVO.VisibilityEnum visibilityEnum) {
		return VisibilityTO.valueOf(visibilityEnum.getValue());
    }
	
	default VisibilityTO visibilityToVisibilityTO(SectionVO.VisibilityEnum visibilityEnum) {
		return VisibilityTO.valueOf(visibilityEnum.getValue());
    }
	
	default String bytesToString(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
    }
}