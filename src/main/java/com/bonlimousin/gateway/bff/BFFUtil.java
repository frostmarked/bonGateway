package com.bonlimousin.gateway.bff;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bonlimousin.gateway.security.AuthoritiesConstants;
import com.bonlimousin.gateway.security.SecurityUtils;

import io.github.jhipster.web.util.PaginationUtil;

public final class BFFUtil {

	private static final String HEADER_X_TOTAL_COUNT = "X-Total-Count";

	private BFFUtil() {

	}
	
	public static UserRoleFilter createUserRoleFilterForCurrentUser() {
		if(SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
			return null;
		}
		return (UserRoleFilter) new UserRoleFilter().setEquals(UserRole.ROLE_ANONYMOUS);
	}

	public static <T> HttpHeaders createPageHeaders(List<T> list, Integer pageNumber, Integer sliceSize,
			List<String> sort, long totalCount) {
		return createPageHeaders(createPage(list, totalCount, pageNumber, sliceSize, sort));
	}

	public static <T> long extractTotalCount(ResponseEntity<List<T>> response) {
		String xTotalCount = response.getHeaders().get(HEADER_X_TOTAL_COUNT).get(0);
		return NumberUtils.toLong(xTotalCount, 0);
	}

	public static <T> Page<T> createPage(List<T> list, long totalCount, Integer pageNumber, Integer sliceSize,
			List<String> sort) {
		Sort s = Sort.unsorted();// TODO implement sort
		Pageable pageable = PageRequest.of(pageNumber, sliceSize, s);
		return new PageImpl<T>(list, pageable, totalCount);
	}

	public static <T> HttpHeaders createPageHeaders(Page<T> page) {
		return PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
	}

	public static <T> ResponseEntity<List<T>> createResponse(List<T> list, Integer pageNumber, Integer sliceSize,
			List<String> sort, long totalCount) {
		return ResponseEntity.ok().headers(createPageHeaders(list, pageNumber, sliceSize, sort, totalCount)).body(list);
	}
}
