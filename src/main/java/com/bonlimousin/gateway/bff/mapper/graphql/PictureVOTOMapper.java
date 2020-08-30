package com.bonlimousin.gateway.bff.mapper.graphql;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bonlimousin.gateway.bff.BFFGraphQLUtil;
import com.bonlimousin.gateway.web.api.model.PictureVO;
import com.bonlimousin.gateway.web.graphql.model.PictureVOTO;
import com.bonlimousin.gateway.web.graphql.model.VisibilityTO;

@Mapper
public interface PictureVOTOMapper {
	
	PictureVOTOMapper INSTANCE = Mappers.getMapper(PictureVOTOMapper.class);

	PictureVOTO voToTO(PictureVO vo);
	
	default VisibilityTO visibilityToVisibilityTO(PictureVO.VisibilityEnum visibilityEnum) {
		return VisibilityTO.valueOf(visibilityEnum.getValue());
	}
	
	default String offsetDateTimeToString(OffsetDateTime odt) {
		return odt.format(DateTimeFormatter.ofPattern(BFFGraphQLUtil.DATETIME_FORMAT));
	}
	
	default String bytesToString(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
    }
}