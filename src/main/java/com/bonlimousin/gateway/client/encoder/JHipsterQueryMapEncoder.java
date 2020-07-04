package com.bonlimousin.gateway.client.encoder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import feign.QueryMapEncoder;
import feign.codec.EncodeException;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.RangeFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * see feign.QueryMapEncoder.FieldQueryMapEncoder
 * 
 * @author frostis
 *
 */
public class JHipsterQueryMapEncoder implements QueryMapEncoder {

	private final Map<Class<?>, ObjectParamMetadata> classToMetadata = new HashMap<Class<?>, ObjectParamMetadata>();

	@Override
	public Map<String, Object> encode(Object object) throws EncodeException {
		try {
			ObjectParamMetadata metadata = getMetadata(object.getClass());
			Map<String, Object> fieldNameToValue = new HashMap<String, Object>();
			for (Field field : metadata.objectFields) {
				Object value = field.get(object);
				String fieldName = field.getName();
				if (value != null && value != object) {
					// custom
					if (value instanceof Filter) {
						fieldNameToValue.putAll(encode((Filter<?>) field.get(object), fieldName));											
					} else {
						fieldNameToValue.put(field.getName(), value);
					}
				}
			}
			return fieldNameToValue;
		} catch (IllegalAccessException e) {
			throw new EncodeException("Failure encoding object into query map", e);
		}
	}
	
	// custom
	private static Map<String, Object> encode(Filter<?> filter, String fieldName) {
		String prefix = StringUtils.trimToNull(fieldName) != null ? fieldName + "." : null;
		Map<String, Object> map = new HashMap<>();
		if (filter.getEquals() != null) {
			map.put(prefix + "equals", filter.getEquals().toString());
		} else if (filter.getIn() != null && !filter.getIn().isEmpty()) {
			map.put(prefix + "in", filter.getIn().stream().map(Object::toString).collect(Collectors.joining(",")));
		} else if (filter.getNotEquals() != null) {
			map.put(prefix + "notEquals", filter.getNotEquals().toString());
		} else if (filter.getSpecified() != null) {
			map.put(prefix + "specified", filter.getSpecified().toString());
		}

		if (filter instanceof RangeFilter<?>) {
			RangeFilter<?> rfilter = (RangeFilter<?>) filter;
			if (rfilter.getGreaterThan() != null) {
				map.put(prefix + "greaterThan", rfilter.getGreaterThan().toString());
			} else if (rfilter.getGreaterThanOrEqual() != null) {
				map.put(prefix + "greaterThanOrEqual", rfilter.getGreaterThanOrEqual().toString());
			} else if (rfilter.getLessThan() != null) {
				map.put(prefix + "lessThan", rfilter.getLessThan().toString());
			} else if (rfilter.getLessThanOrEqual() != null) {
				map.put(prefix + "lessThanOrEqual", rfilter.getLessThanOrEqual().toString());
			}
		} else if(filter instanceof StringFilter) {
			StringFilter sfilter = (StringFilter) filter;
			if (sfilter.getContains() != null) {
				map.put(prefix + "contains", sfilter.getContains());
			} else if (sfilter.getDoesNotContain() != null) {
				map.put(prefix + "doesNotContain", sfilter.getDoesNotContain());
			}
		}
		return map;
	}

	private ObjectParamMetadata getMetadata(Class<?> objectType) {
		ObjectParamMetadata metadata = classToMetadata.get(objectType);
		if (metadata == null) {
			metadata = ObjectParamMetadata.parseObjectType(objectType);
			classToMetadata.put(objectType, metadata);
		}
		return metadata;
	}

	private static class ObjectParamMetadata {

		private final List<Field> objectFields;

		private ObjectParamMetadata(List<Field> objectFields) {
			this.objectFields = Collections.unmodifiableList(objectFields);
		}

		private static ObjectParamMetadata parseObjectType(Class<?> type) {
			List<Field> allFields = new ArrayList<>();

			for (Class<?> currentClass = type; currentClass != null; currentClass = currentClass.getSuperclass()) {
				Collections.addAll(allFields, currentClass.getDeclaredFields());
			}

			return new ObjectParamMetadata(allFields.stream().filter(field -> !field.isSynthetic())
					.peek(field -> field.setAccessible(true)).collect(Collectors.toList()));
		}
	}
}