package com.bonlimousin.gateway.client.boncontentservice.apidocs.querymap;

import java.io.Serializable;
import java.util.Objects;

import com.bonlimousin.gateway.bff.UserRoleFilter;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.bonlimousin.content.domain.LocalizedEntity} entity. This class is used
 * in {@link com.bonlimousin.content.web.rest.LocalizedResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /localizeds?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class LocalizedCriteria implements Serializable, Criteria {
    
    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter i18n;

    private StringFilter title;

    private StringFilter ingress;

    private StringFilter caption;

    private UserRoleFilter visibility;

    private LongFilter fragmentId;

    public LocalizedCriteria(){
    }

    public LocalizedCriteria(LocalizedCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.i18n = other.i18n == null ? null : other.i18n.copy();
        this.title = other.title == null ? null : other.title.copy();
        this.ingress = other.ingress == null ? null : other.ingress.copy();
        this.caption = other.caption == null ? null : other.caption.copy();
        this.visibility = other.visibility == null ? null : other.visibility.copy();
        this.fragmentId = other.fragmentId == null ? null : other.fragmentId.copy();
    }

    @Override
    public LocalizedCriteria copy() {
        return new LocalizedCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter geti18n() {
        return i18n;
    }

    public void seti18n(StringFilter i18n) {
        this.i18n = i18n;
    }

    public StringFilter getTitle() {
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public StringFilter getIngress() {
        return ingress;
    }

    public void setIngress(StringFilter ingress) {
        this.ingress = ingress;
    }

    public StringFilter getCaption() {
        return caption;
    }

    public void setCaption(StringFilter caption) {
        this.caption = caption;
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
        final LocalizedCriteria that = (LocalizedCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(i18n, that.i18n) &&
            Objects.equals(title, that.title) &&
            Objects.equals(ingress, that.ingress) &&
            Objects.equals(caption, that.caption) &&
            Objects.equals(visibility, that.visibility) &&
            Objects.equals(fragmentId, that.fragmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        i18n,
        title,
        ingress,
        caption,
        visibility,
        fragmentId
        );
    }

    @Override
    public String toString() {
        return "LocalizedCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (i18n != null ? "i18n=" + i18n + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (ingress != null ? "ingress=" + ingress + ", " : "") +
                (caption != null ? "caption=" + caption + ", " : "") +
                (visibility != null ? "visibility=" + visibility + ", " : "") +
                (fragmentId != null ? "fragmentId=" + fragmentId + ", " : "") +
            "}";
    }

}
