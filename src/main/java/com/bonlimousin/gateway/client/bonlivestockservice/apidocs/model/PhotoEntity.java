package com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.CattleEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PhotoEntity
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-03T23:56:19.029760+02:00[Europe/Stockholm]")

public class PhotoEntity   {
  @JsonProperty("caption")
  private String caption;

  @JsonProperty("cattle")
  private CattleEntity cattle;

  @JsonProperty("height")
  private Integer height;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("image")
  private byte[] image;

  @JsonProperty("imageContentType")
  private String imageContentType;

  @JsonProperty("taken")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime taken;

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

  public PhotoEntity caption(String caption) {
    this.caption = caption;
    return this;
  }

  /**
   * Get caption
   * @return caption
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(min=1,max=140) 
  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public PhotoEntity cattle(CattleEntity cattle) {
    this.cattle = cattle;
    return this;
  }

  /**
   * Get cattle
   * @return cattle
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public CattleEntity getCattle() {
    return cattle;
  }

  public void setCattle(CattleEntity cattle) {
    this.cattle = cattle;
  }

  public PhotoEntity height(Integer height) {
    this.height = height;
    return this;
  }

  /**
   * Get height
   * minimum: 0
   * @return height
  */
  @ApiModelProperty(value = "")

@Min(0)
  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public PhotoEntity id(Long id) {
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

  public PhotoEntity image(byte[] image) {
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

  public PhotoEntity imageContentType(String imageContentType) {
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

  public PhotoEntity taken(OffsetDateTime taken) {
    this.taken = taken;
    return this;
  }

  /**
   * Get taken
   * @return taken
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getTaken() {
    return taken;
  }

  public void setTaken(OffsetDateTime taken) {
    this.taken = taken;
  }

  public PhotoEntity visibility(VisibilityEnum visibility) {
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

  public PhotoEntity width(Integer width) {
    this.width = width;
    return this;
  }

  /**
   * Get width
   * minimum: 0
   * @return width
  */
  @ApiModelProperty(value = "")

@Min(0)
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
    PhotoEntity photoEntity = (PhotoEntity) o;
    return Objects.equals(this.caption, photoEntity.caption) &&
        Objects.equals(this.cattle, photoEntity.cattle) &&
        Objects.equals(this.height, photoEntity.height) &&
        Objects.equals(this.id, photoEntity.id) &&
        Objects.equals(this.image, photoEntity.image) &&
        Objects.equals(this.imageContentType, photoEntity.imageContentType) &&
        Objects.equals(this.taken, photoEntity.taken) &&
        Objects.equals(this.visibility, photoEntity.visibility) &&
        Objects.equals(this.width, photoEntity.width);
  }

  @Override
  public int hashCode() {
    return Objects.hash(caption, cattle, height, id, image, imageContentType, taken, visibility, width);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhotoEntity {\n");
    
    sb.append("    caption: ").append(toIndentedString(caption)).append("\n");
    sb.append("    cattle: ").append(toIndentedString(cattle)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    imageContentType: ").append(toIndentedString(imageContentType)).append("\n");
    sb.append("    taken: ").append(toIndentedString(taken)).append("\n");
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

