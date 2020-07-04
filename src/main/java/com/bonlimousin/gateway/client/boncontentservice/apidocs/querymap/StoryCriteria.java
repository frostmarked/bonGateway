package com.bonlimousin.gateway.client.boncontentservice.apidocs.querymap;

import java.io.Serializable;
import java.util.Objects;

import com.bonlimousin.gateway.bff.UserRoleFilter;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.bonlimousin.content.domain.StoryEntity} entity. This class is used
 * in {@link com.bonlimousin.content.web.rest.StoryResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /stories?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class StoryCriteria implements Serializable, Criteria {
    /**
     * Class for filtering StoryCategory
     */
    public static class StoryCategoryFilter extends Filter<StoryCategory> {

        public StoryCategoryFilter() {
        }

        public StoryCategoryFilter(StoryCategoryFilter filter) {
            super(filter);
        }

        @Override
        public StoryCategoryFilter copy() {
            return new StoryCategoryFilter(this);
        }

    }
    
    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StoryCategoryFilter category;

    private StringFilter name;

    private UserRoleFilter visibility;

    private LongFilter fragmentId;

    public StoryCriteria(){
    }

    public StoryCriteria(StoryCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.category = other.category == null ? null : other.category.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.visibility = other.visibility == null ? null : other.visibility.copy();
        this.fragmentId = other.fragmentId == null ? null : other.fragmentId.copy();
    }

    @Override
    public StoryCriteria copy() {
        return new StoryCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StoryCategoryFilter getCategory() {
        return category;
    }

    public void setCategory(StoryCategoryFilter category) {
        this.category = category;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public UserRoleFilter getVisibility() {
        return visibility;
    }

    public void setVisibility(UserRoleFilter visibility) {
        this.visibility = visibility;
    }

    public LongFilter getFragmentId() {
        return fragmentId;
    }

    public void setFragmentId(LongFilter fragmentId) {
        this.fragmentId = fragmentId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final StoryCriteria that = (StoryCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(category, that.category) &&
            Objects.equals(name, that.name) &&
            Objects.equals(visibility, that.visibility) &&
            Objects.equals(fragmentId, that.fragmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        category,
        name,
        visibility,
        fragmentId
        );
    }

    @Override
    public String toString() {
        return "StoryCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (category != null ? "category=" + category + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (visibility != null ? "visibility=" + visibility + ", " : "") +
                (fragmentId != null ? "fragmentId=" + fragmentId + ", " : "") +
            "}";
    }

}
