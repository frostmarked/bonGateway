package com.bonlimousin.gateway.bff.mapper.graphql;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bonlimousin.gateway.web.api.model.PhotographVO;
import com.bonlimousin.gateway.web.graphql.model.PhotographVOTO;
import com.bonlimousin.gateway.web.graphql.model.VisibilityTO;

@Mapper
public interface PhotographVOTOMapper {

	static final String dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	
	PhotographVOTOMapper INSTANCE = Mappers.getMapper(PhotographVOTOMapper.class);

	PhotographVOTO voToTO(PhotographVO vo);
	
	default VisibilityTO visibilityToVisibilityTO(PhotographVO.VisibilityEnum visibilityEnum) {
		return VisibilityTO.valueOf(visibilityEnum.getValue());
	}
	
	default String offsetDateTimeToString(OffsetDateTime odt) {
		return odt.format(DateTimeFormatter.ofPattern(dateFormat));
	}
	
	default String bytesToString(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
    }
}