package com.bonlimousin.gateway.bff;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class BFFGraphQLUtil {
	
	public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	private BFFGraphQLUtil() {

	}
	
	public static <T extends Enum<?>> String enumToString(T e) {
		return e != null ? e.name() : null;
	}

	public static <T extends Enum<?>> List<String> enumsToStrings(Collection<T> enumList) {
		return enumList != null ? enumList.stream().map(e -> e.name()).collect(Collectors.toList()) : null;
	}

	public static class GraphQLPageable {

		private final Integer page;
		private final Integer size;
		private final List<String> sort;

		private GraphQLPageable() {
			this.sort = null;
			this.page = 0;
			this.size = 10;
		}

		private GraphQLPageable(Integer page, Integer size, Collection<String> sort) {
			this.sort = sort != null ? new ArrayList<>(sort) : null;
			this.page = page != null ? page : 0;
			this.size = size != null ? size : 10;
		}

		public static GraphQLPageable of(Integer page, Integer size, Collection<String> sort) {
			return new GraphQLPageable(page, size, sort);
		}

		public Integer getPage() {
			return page;
		}

		public Integer getSize() {
			return size;
		}

		public List<String> getSort() {
			return sort;
		}
	}
}
