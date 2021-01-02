package com.bonlimousin.gateway.bff.mapper.graphql;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bonlimousin.gateway.web.api.model.CowVO;
import com.bonlimousin.gateway.web.graphql.model.CowVOTO;
import com.bonlimousin.gateway.web.graphql.model.VisibilityTO;

@Mapper
public interface CowVOTOMapper {

	static final String dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	CowVOTOMapper INSTANCE = Mappers.getMapper(CowVOTOMapper.class);

	CowVOTO voToTO(CowVO vo);

	default VisibilityTO visibilityToVisibilityTO(CowVO.VisibilityEnum visibilityEnum) {
        if(visibilityEnum == null) {
            return null;
        }
		return VisibilityTO.valueOf(visibilityEnum.getValue());
	}

	default String offsetDateTimeToString(OffsetDateTime odt) {
	    if(odt == null) {
	        return null;
        }
		return odt.format(DateTimeFormatter.ofPattern(dateFormat));
	}
}
