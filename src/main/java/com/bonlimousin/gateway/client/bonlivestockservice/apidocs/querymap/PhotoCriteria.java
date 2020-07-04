package com.bonlimousin.gateway.client.bonlivestockservice.apidocs.querymap;

import java.io.Serializable;
import java.util.Objects;

import com.bonlimousin.gateway.bff.UserRoleFilter;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.bonlimousin.livestock.domain.PhotoEntity} entity. This class is used
 * in {@link com.bonlimousin.livestock.web.rest.PhotoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /photos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PhotoCriteria implements Serializable, Criteria {
    
    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter caption;

    private IntegerFilter height;

    private IntegerFilter width;

    private InstantFilter taken;

    private UserRoleFilter visibility;

    private LongFilter cattleId;

    public PhotoCriteria(){
    }

    public PhotoCriteria(PhotoCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.caption = other.caption == null ? null : other.caption.copy();
        this.height = other.height == null ? null : other.height.copy();
        this.width = other.width == null ? null : other.width.copy();
        this.taken = other.taken == null ? null : other.taken.copy();
        this.visibility = other.visibility == null ? null : other.visibility.copy();
        this.cattleId = other.cattleId == null ? null : other.cattleId.copy();
    }

    @Override
    public PhotoCriteria copy() {
        return new PhotoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCaption() {
        return caption;
    }

    public void setCaption(StringFilter caption) {
        this.caption = caption;
    }

    public IntegerFilter getHeight() {
        return height;
    }

    public void setHeight(IntegerFilter height) {
        this.height = height;
    }

    public IntegerFilter getWidth() {
        return width;
    }

    public void setWidth(IntegerFilter width) {
        this.width = width;
    }

    public InstantFilter getTaken() {
        return taken;
    }

    public void setTaken(InstantFilter taken) {
        this.taken = taken;
    }

    public UserRoleFilter getVisibility() {
        return visibility;
    }

    public void setVisibility(UserRoleFilter visibility) {
        this.visibility = visibility;
    }

    public LongFilter getCattleId() {
        return cattleId;
    }

    public void setCattleId(LongFilter cattleId) {
        this.cattleId = cattleId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PhotoCriteria that = (PhotoCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(caption, that.caption) &&
            Objects.equals(height, that.height) &&
            Objects.equals(width, that.width) &&
            Objects.equals(taken, that.taken) &&
            Objects.equals(visibility, that.visibility) &&
            Objects.equals(cattleId, that.cattleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        caption,
        height,
        width,
        taken,
        visibility,
        cattleId
        );
    }

    @Override
    public String toString() {
        return "PhotoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (caption != null ? "caption=" + caption + ", " : "") +
                (height != null ? "height=" + height + ", " : "") +
                (width != null ? "width=" + width + ", " : "") +
                (taken != null ? "taken=" + taken + ", " : "") +
                (visibility != null ? "visibility=" + visibility + ", " : "") +
                (cattleId != null ? "cattleId=" + cattleId + ", " : "") +
            "}";
    }

}
