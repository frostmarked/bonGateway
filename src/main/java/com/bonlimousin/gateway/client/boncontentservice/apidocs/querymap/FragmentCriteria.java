package com.bonlimousin.gateway.client.boncontentservice.apidocs.querymap;

import java.io.Serializable;
import java.util.Objects;

import com.bonlimousin.gateway.bff.UserRoleFilter;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.bonlimousin.content.domain.FragmentEntity} entity. This class is used
 * in {@link com.bonlimousin.content.web.rest.FragmentResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /fragments?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FragmentCriteria implements Serializable, Criteria {
    /**
     * Class for filtering FragmentTemplate
     */
    public static class FragmentTemplateFilter extends Filter<FragmentTemplate> {

        public FragmentTemplateFilter() {
        }

        public FragmentTemplateFilter(FragmentTemplateFilter filter) {
            super(filter);
        }

        @Override
        public FragmentTemplateFilter copy() {
            return new FragmentTemplateFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private FragmentTemplateFilter template;

    private StringFilter name;

    private StringFilter title;

    private StringFilter ingress;

    private StringFilter caption;

    private IntegerFilter width;

    private IntegerFilter height;

    private IntegerFilter orderNo;

    private UserRoleFilter visibility;

    private LongFilter localizedFragmentId;

    private LongFilter tagId;

    private LongFilter storyId;

    public FragmentCriteria(){
    }

    public FragmentCriteria(FragmentCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.template = other.template == null ? null : other.template.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.title = other.title == null ? null : other.title.copy();
        this.ingress = other.ingress == null ? null : other.ingress.copy();
        this.caption = other.caption == null ? null : other.caption.copy();
        this.width = other.width == null ? null : other.width.copy();
        this.height = other.height == null ? null : other.height.copy();
        this.orderNo = other.orderNo == null ? null : other.orderNo.copy();
        this.visibility = other.visibility == null ? null : other.visibility.copy();
        this.localizedFragmentId = other.localizedFragmentId == null ? null : other.localizedFragmentId.copy();
        this.tagId = other.tagId == null ? null : other.tagId.copy();
        this.storyId = other.storyId == null ? null : other.storyId.copy();
    }

    @Override
    public FragmentCriteria copy() {
        return new FragmentCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public FragmentTemplateFilter getTemplate() {
        return template;
    }

    public void setTemplate(FragmentTemplateFilter template) {
        this.template = template;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
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

    public IntegerFilter getWidth() {
        return width;
    }

    public void setWidth(IntegerFilter width) {
        this.width = width;
    }

    public IntegerFilter getHeight() {
        return height;
    }

    public void setHeight(IntegerFilter height) {
        this.height = height;
    }

    public IntegerFilter getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(IntegerFilter orderNo) {
        this.orderNo = orderNo;
    }

    public UserRoleFilter getVisibility() {
        return visibility;
    }

    public void setVisibility(UserRoleFilter visibility) {
        this.visibility = visibility;
    }

    public LongFilter getLocalizedFragmentId() {
        return localizedFragmentId;
    }

    public void setLocalizedFragmentId(LongFilter localizedFragmentId) {
        this.localizedFragmentId = localizedFragmentId;
    }

    public LongFilter getTagId() {
        return tagId;
    }

    public void setTagId(LongFilter tagId) {
        this.tagId = tagId;
    }

    public LongFilter getStoryId() {
        return storyId;
    }

    public void setStoryId(LongFilter storyId) {
        this.storyId = storyId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FragmentCriteria that = (FragmentCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(template, that.template) &&
            Objects.equals(name, that.name) &&
            Objects.equals(title, that.title) &&
            Objects.equals(ingress, that.ingress) &&
            Objects.equals(caption, that.caption) &&
            Objects.equals(width, that.width) &&
            Objects.equals(height, that.height) &&
            Objects.equals(orderNo, that.orderNo) &&
            Objects.equals(visibility, that.visibility) &&
            Objects.equals(localizedFragmentId, that.localizedFragmentId) &&
            Objects.equals(tagId, that.tagId) &&
            Objects.equals(storyId, that.storyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        template,
        name,
        title,
        ingress,
        caption,
        width,
        height,
        orderNo,
        visibility,
        localizedFragmentId,
        tagId,
        storyId
        );
    }

    @Override
    public String toString() {
        return "FragmentCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (template != null ? "template=" + template + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (ingress != null ? "ingress=" + ingress + ", " : "") +
                (caption != null ? "caption=" + caption + ", " : "") +
                (width != null ? "width=" + width + ", " : "") +
                (height != null ? "height=" + height + ", " : "") +
                (orderNo != null ? "orderNo=" + orderNo + ", " : "") +
                (visibility != null ? "visibility=" + visibility + ", " : "") +
                (localizedFragmentId != null ? "localizedFragmentId=" + localizedFragmentId + ", " : "") +
                (tagId != null ? "tagId=" + tagId + ", " : "") +
                (storyId != null ? "storyId=" + storyId + ", " : "") +
            "}";
    }

}
