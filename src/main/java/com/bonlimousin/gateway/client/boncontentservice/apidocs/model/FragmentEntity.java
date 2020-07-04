package com.bonlimousin.gateway.client.boncontentservice.apidocs.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.model.LocalizedEntity;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.model.StoryEntity;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.model.TagEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FragmentEntity
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-04T00:04:26.742175+02:00[Europe/Stockholm]")

public class FragmentEntity   {
  @JsonProperty("body")
  private String body;

  @JsonProperty("caption")
  private String caption;

  @JsonProperty("height")
  private Integer height;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("image")
  private byte[] image;

  @JsonProperty("imageContentType")
  private String imageContentType;

  @JsonProperty("ingress")
  private String ingress;

  @JsonProperty("localizedFragments")
  @Valid
  private List<LocalizedEntity> localizedFragments = null;

  @JsonProperty("name")
  private String name;

  @JsonProperty("orderNo")
  private Integer orderNo;

  @JsonProperty("story")
  private StoryEntity story;

  @JsonProperty("tags")
  @Valid
  private List<TagEntity> tags = null;

  /**
   * Gets or Sets template
   */
  public enum TemplateEnum {
    V1("V1"),
    
    V2("V2"),
    
    V3("V3");

    private String value;

    TemplateEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TemplateEnum fromValue(String value) {
      for (TemplateEnum b : TemplateEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("template")
  private TemplateEnum template;

  @JsonProperty("title")
  private String title;

  /**
   * Gets or Sets visibility
   */
  public enum VisibilityEnum {
    ADMIN("ROLE_ADMIN"),
    
    USER("ROLE_USER"),
    
    ANONYMOUS("ROLE_ANONYMOUS");

    private String value;

    VisibilityEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static VisibilityEnum fromValue(String value) {
      for (VisibilityEnum b : VisibilityEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("visibility")
  private VisibilityEnum visibility;

  @JsonProperty("width")
  private Integer width;

  public FragmentEntity body(String body) {
    this.body = body;
    return this;
  }

  /**
   * Get body
   * @return body
  */
  @ApiModelProperty(value = "")


  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public FragmentEntity caption(String caption) {
    this.caption = caption;
    return this;
  }

  /**
   * Get caption
   * @return caption
  */
  @ApiModelProperty(value = "")

@Size(min=0,max=255) 
  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public FragmentEntity height(Integer height) {
    this.height = height;
    return this;
  }

  /**
   * Get height
   * @return height
  */
  @ApiModelProperty(value = "")


  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public FragmentEntity id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public FragmentEntity image(byte[] image) {
    this.image = image;
    return this;
  }

  /**
   * Get image
   * @return image
  */
  @ApiModelProperty(value = "")

@Pattern(regexp="^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$") 
  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  public FragmentEntity imageContentType(String imageContentType) {
    this.imageContentType = imageContentType;
    return this;
  }

  /**
   * Get imageContentType
   * @return imageContentType
  */
  @ApiModelProperty(value = "")


  public String getImageContentType() {
    return imageContentType;
  }

  public void setImageContentType(String imageContentType) {
    this.imageContentType = imageContentType;
  }

  public FragmentEntity ingress(String ingress) {
    this.ingress = ingress;
    return this;
  }

  /**
   * Get ingress
   * @return ingress
  */
  @ApiModelProperty(value = "")

@Size(min=0,max=255) 
  public String getIngress() {
    return ingress;
  }

  public void setIngress(String ingress) {
    this.ingress = ingress;
  }

  public FragmentEntity localizedFragments(List<LocalizedEntity> localizedFragments) {
    this.localizedFragments = localizedFragments;
    return this;
  }

  public FragmentEntity addLocalizedFragmentsItem(LocalizedEntity localizedFragmentsItem) {
    if (this.localizedFragments == null) {
      this.localizedFragments = new ArrayList<>();
    }
    this.localizedFragments.add(localizedFragmentsItem);
    return this;
  }

  /**
   * Get localizedFragments
   * @return localizedFragments
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<LocalizedEntity> getLocalizedFragments() {
    return localizedFragments;
  }

  public void setLocalizedFragments(List<LocalizedEntity> localizedFragments) {
    this.localizedFragments = localizedFragments;
  }

  public FragmentEntity name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(min=2,max=2147483647) 
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public FragmentEntity orderNo(Integer orderNo) {
    this.orderNo = orderNo;
    return this;
  }

  /**
   * Get orderNo
   * @return orderNo
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(Integer orderNo) {
    this.orderNo = orderNo;
  }

  public FragmentEntity story(StoryEntity story) {
    this.story = story;
    return this;
  }

  /**
   * Get story
   * @return story
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public StoryEntity getStory() {
    return story;
  }

  public void setStory(StoryEntity story) {
    this.story = story;
  }

  public FragmentEntity tags(List<TagEntity> tags) {
    this.tags = tags;
    return this;
  }

  public FragmentEntity addTagsItem(TagEntity tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<>();
    }
    this.tags.add(tagsItem);
    return this;
  }

  /**
   * Get tags
   * @return tags
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<TagEntity> getTags() {
    return tags;
  }

  public void setTags(List<TagEntity> tags) {
    this.tags = tags;
  }

  public FragmentEntity template(TemplateEnum template) {
    this.template = template;
    return this;
  }

  /**
   * Get template
   * @return template
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public TemplateEnum getTemplate() {
    return template;
  }

  public void setTemplate(TemplateEnum template) {
    this.template = template;
  }

  public FragmentEntity title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  @ApiModelProperty(value = "")

@Size(min=0,max=127) 
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public FragmentEntity visibility(VisibilityEnum visibility) {
    this.visibility = visibility;
    return this;
  }

  /**
   * Get visibility
   * @return visibility
  */
  @ApiModelProperty(value = "")


  public VisibilityEnum getVisibility() {
    return visibility;
  }

  public void setVisibility(VisibilityEnum visibility) {
    this.visibility = visibility;
  }

  public FragmentEntity width(Integer width) {
    this.width = width;
    return this;
  }

  /**
   * Get width
   * @return width
  */
  @ApiModelProperty(value = "")


  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FragmentEntity fragmentEntity = (FragmentEntity) o;
    return Objects.equals(this.body, fragmentEntity.body) &&
        Objects.equals(this.caption, fragmentEntity.caption) &&
        Objects.equals(this.height, fragmentEntity.height) &&
        Objects.equals(this.id, fragmentEntity.id) &&
        Objects.equals(this.image, fragmentEntity.image) &&
        Objects.equals(this.imageContentType, fragmentEntity.imageContentType) &&
        Objects.equals(this.ingress, fragmentEntity.ingress) &&
        Objects.equals(this.localizedFragments, fragmentEntity.localizedFragments) &&
        Objects.equals(this.name, fragmentEntity.name) &&
        Objects.equals(this.orderNo, fragmentEntity.orderNo) &&
        Objects.equals(this.story, fragmentEntity.story) &&
        Objects.equals(this.tags, fragmentEntity.tags) &&
        Objects.equals(this.template, fragmentEntity.template) &&
        Objects.equals(this.title, fragmentEntity.title) &&
        Objects.equals(this.visibility, fragmentEntity.visibility) &&
        Objects.equals(this.width, fragmentEntity.width);
  }

  @Override
  public int hashCode() {
    return Objects.hash(body, caption, height, id, image, imageContentType, ingress, localizedFragments, name, orderNo, story, tags, template, title, visibility, width);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FragmentEntity {\n");
    
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
    sb.append("    caption: ").append(toIndentedString(caption)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    imageContentType: ").append(toIndentedString(imageContentType)).append("\n");
    sb.append("    ingress: ").append(toIndentedString(ingress)).append("\n");
    sb.append("    localizedFragments: ").append(toIndentedString(localizedFragments)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    orderNo: ").append(toIndentedString(orderNo)).append("\n");
    sb.append("    story: ").append(toIndentedString(story)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    template: ").append(toIndentedString(template)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    visibility: ").append(toIndentedString(visibility)).append("\n");
    sb.append("    width: ").append(toIndentedString(width)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

