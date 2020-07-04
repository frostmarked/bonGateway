package com.bonlimousin.gateway.client.boncontentservice.apidocs.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.model.FragmentEntity;
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
 * StoryEntity
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-04T00:04:26.742175+02:00[Europe/Stockholm]")

public class StoryEntity   {
  /**
   * Gets or Sets category
   */
  public enum CategoryEnum {
    NEWS("NEWS"),
    
    MATRILINEALITY("MATRILINEALITY"),
    
    CATTLE("CATTLE"),
    
    IT("IT");

    private String value;

    CategoryEnum(String value) {
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
    public static CategoryEnum fromValue(String value) {
      for (CategoryEnum b : CategoryEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("category")
  private CategoryEnum category;

  @JsonProperty("fragments")
  @Valid
  private List<FragmentEntity> fragments = null;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("name")
  private String name;

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

  public StoryEntity category(CategoryEnum category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public CategoryEnum getCategory() {
    return category;
  }

  public void setCategory(CategoryEnum category) {
    this.category = category;
  }

  public StoryEntity fragments(List<FragmentEntity> fragments) {
    this.fragments = fragments;
    return this;
  }

  public StoryEntity addFragmentsItem(FragmentEntity fragmentsItem) {
    if (this.fragments == null) {
      this.fragments = new ArrayList<>();
    }
    this.fragments.add(fragmentsItem);
    return this;
  }

  /**
   * Get fragments
   * @return fragments
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<FragmentEntity> getFragments() {
    return fragments;
  }

  public void setFragments(List<FragmentEntity> fragments) {
    this.fragments = fragments;
  }

  public StoryEntity id(Long id) {
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

  public StoryEntity name(String name) {
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

  public StoryEntity visibility(VisibilityEnum visibility) {
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
    StoryEntity storyEntity = (StoryEntity) o;
    return Objects.equals(this.category, storyEntity.category) &&
        Objects.equals(this.fragments, storyEntity.fragments) &&
        Objects.equals(this.id, storyEntity.id) &&
        Objects.equals(this.name, storyEntity.name) &&
        Objects.equals(this.visibility, storyEntity.visibility);
  }

  @Override
  public int hashCode() {
    return Objects.hash(category, fragments, id, name, visibility);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StoryEntity {\n");
    
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    fragments: ").append(toIndentedString(fragments)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

