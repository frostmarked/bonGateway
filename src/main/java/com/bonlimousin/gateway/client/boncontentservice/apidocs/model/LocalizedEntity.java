package com.bonlimousin.gateway.client.boncontentservice.apidocs.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.model.FragmentEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * LocalizedEntity
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-04T00:04:26.742175+02:00[Europe/Stockholm]")

public class LocalizedEntity   {
  @JsonProperty("body")
  private String body;

  @JsonProperty("caption")
  private String caption;

  @JsonProperty("fragment")
  private FragmentEntity fragment;

  @JsonProperty("i18n")
  private String i18n;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("ingress")
  private String ingress;

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

  public LocalizedEntity body(String body) {
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

  public LocalizedEntity caption(String caption) {
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

  public LocalizedEntity fragment(FragmentEntity fragment) {
    this.fragment = fragment;
    return this;
  }

  /**
   * Get fragment
   * @return fragment
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public FragmentEntity getFragment() {
    return fragment;
  }

  public void setFragment(FragmentEntity fragment) {
    this.fragment = fragment;
  }

  public LocalizedEntity i18n(String i18n) {
    this.i18n = i18n;
    return this;
  }

  /**
   * Get i18n
   * @return i18n
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Pattern(regexp="[a-z]+") @Size(min=2,max=2147483647) 
  public String getI18n() {
    return i18n;
  }

  public void setI18n(String i18n) {
    this.i18n = i18n;
  }

  public LocalizedEntity id(Long id) {
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

  public LocalizedEntity ingress(String ingress) {
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

  public LocalizedEntity title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(min=0,max=127) 
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalizedEntity visibility(VisibilityEnum visibility) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LocalizedEntity localizedEntity = (LocalizedEntity) o;
    return Objects.equals(this.body, localizedEntity.body) &&
        Objects.equals(this.caption, localizedEntity.caption) &&
        Objects.equals(this.fragment, localizedEntity.fragment) &&
        Objects.equals(this.i18n, localizedEntity.i18n) &&
        Objects.equals(this.id, localizedEntity.id) &&
        Objects.equals(this.ingress, localizedEntity.ingress) &&
        Objects.equals(this.title, localizedEntity.title) &&
        Objects.equals(this.visibility, localizedEntity.visibility);
  }

  @Override
  public int hashCode() {
    return Objects.hash(body, caption, fragment, i18n, id, ingress, title, visibility);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LocalizedEntity {\n");
    
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
    sb.append("    caption: ").append(toIndentedString(caption)).append("\n");
    sb.append("    fragment: ").append(toIndentedString(fragment)).append("\n");
    sb.append("    i18n: ").append(toIndentedString(i18n)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    ingress: ").append(toIndentedString(ingress)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    visibility: ").append(toIndentedString(visibility)).append("\n");
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

