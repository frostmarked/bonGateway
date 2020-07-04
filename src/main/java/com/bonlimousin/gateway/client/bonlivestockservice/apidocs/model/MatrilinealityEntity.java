package com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * MatrilinealityEntity
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-03T23:56:19.029760+02:00[Europe/Stockholm]")

public class MatrilinealityEntity   {
  @JsonProperty("cattleNameRegexPattern")
  private String cattleNameRegexPattern;

  @JsonProperty("country")
  private String country;

  @JsonProperty("description")
  private String description;

  @JsonProperty("earTagId")
  private Integer earTagId;

  @JsonProperty("familyname")
  private String familyname;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("patriCountry")
  private String patriCountry;

  @JsonProperty("patriId")
  private Integer patriId;

  @JsonProperty("patriName")
  private String patriName;

  @JsonProperty("polled")
  private Boolean polled;

  @JsonProperty("storyHandle")
  private String storyHandle;

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

  public MatrilinealityEntity cattleNameRegexPattern(String cattleNameRegexPattern) {
    this.cattleNameRegexPattern = cattleNameRegexPattern;
    return this;
  }

  /**
   * Get cattleNameRegexPattern
   * @return cattleNameRegexPattern
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(min=0,max=255) 
  public String getCattleNameRegexPattern() {
    return cattleNameRegexPattern;
  }

  public void setCattleNameRegexPattern(String cattleNameRegexPattern) {
    this.cattleNameRegexPattern = cattleNameRegexPattern;
  }

  public MatrilinealityEntity country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   * @return country
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(min=2,max=6) 
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public MatrilinealityEntity description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  @ApiModelProperty(value = "")

@Size(min=0,max=1023) 
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public MatrilinealityEntity earTagId(Integer earTagId) {
    this.earTagId = earTagId;
    return this;
  }

  /**
   * Get earTagId
   * minimum: 0
   * @return earTagId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Min(0)
  public Integer getEarTagId() {
    return earTagId;
  }

  public void setEarTagId(Integer earTagId) {
    this.earTagId = earTagId;
  }

  public MatrilinealityEntity familyname(String familyname) {
    this.familyname = familyname;
    return this;
  }

  /**
   * Get familyname
   * @return familyname
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(min=0,max=127) 
  public String getFamilyname() {
    return familyname;
  }

  public void setFamilyname(String familyname) {
    this.familyname = familyname;
  }

  public MatrilinealityEntity id(Long id) {
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

  public MatrilinealityEntity name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(min=0,max=127) 
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MatrilinealityEntity patriCountry(String patriCountry) {
    this.patriCountry = patriCountry;
    return this;
  }

  /**
   * Get patriCountry
   * @return patriCountry
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(min=2,max=6) 
  public String getPatriCountry() {
    return patriCountry;
  }

  public void setPatriCountry(String patriCountry) {
    this.patriCountry = patriCountry;
  }

  public MatrilinealityEntity patriId(Integer patriId) {
    this.patriId = patriId;
    return this;
  }

  /**
   * Get patriId
   * @return patriId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getPatriId() {
    return patriId;
  }

  public void setPatriId(Integer patriId) {
    this.patriId = patriId;
  }

  public MatrilinealityEntity patriName(String patriName) {
    this.patriName = patriName;
    return this;
  }

  /**
   * Get patriName
   * @return patriName
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(min=0,max=127) 
  public String getPatriName() {
    return patriName;
  }

  public void setPatriName(String patriName) {
    this.patriName = patriName;
  }

  public MatrilinealityEntity polled(Boolean polled) {
    this.polled = polled;
    return this;
  }

  /**
   * Get polled
   * @return polled
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Boolean getPolled() {
    return polled;
  }

  public void setPolled(Boolean polled) {
    this.polled = polled;
  }

  public MatrilinealityEntity storyHandle(String storyHandle) {
    this.storyHandle = storyHandle;
    return this;
  }

  /**
   * Get storyHandle
   * @return storyHandle
  */
  @ApiModelProperty(value = "")


  public String getStoryHandle() {
    return storyHandle;
  }

  public void setStoryHandle(String storyHandle) {
    this.storyHandle = storyHandle;
  }

  public MatrilinealityEntity visibility(VisibilityEnum visibility) {
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
    MatrilinealityEntity matrilinealityEntity = (MatrilinealityEntity) o;
    return Objects.equals(this.cattleNameRegexPattern, matrilinealityEntity.cattleNameRegexPattern) &&
        Objects.equals(this.country, matrilinealityEntity.country) &&
        Objects.equals(this.description, matrilinealityEntity.description) &&
        Objects.equals(this.earTagId, matrilinealityEntity.earTagId) &&
        Objects.equals(this.familyname, matrilinealityEntity.familyname) &&
        Objects.equals(this.id, matrilinealityEntity.id) &&
        Objects.equals(this.name, matrilinealityEntity.name) &&
        Objects.equals(this.patriCountry, matrilinealityEntity.patriCountry) &&
        Objects.equals(this.patriId, matrilinealityEntity.patriId) &&
        Objects.equals(this.patriName, matrilinealityEntity.patriName) &&
        Objects.equals(this.polled, matrilinealityEntity.polled) &&
        Objects.equals(this.storyHandle, matrilinealityEntity.storyHandle) &&
        Objects.equals(this.visibility, matrilinealityEntity.visibility);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cattleNameRegexPattern, country, description, earTagId, familyname, id, name, patriCountry, patriId, patriName, polled, storyHandle, visibility);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MatrilinealityEntity {\n");
    
    sb.append("    cattleNameRegexPattern: ").append(toIndentedString(cattleNameRegexPattern)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    earTagId: ").append(toIndentedString(earTagId)).append("\n");
    sb.append("    familyname: ").append(toIndentedString(familyname)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    patriCountry: ").append(toIndentedString(patriCountry)).append("\n");
    sb.append("    patriId: ").append(toIndentedString(patriId)).append("\n");
    sb.append("    patriName: ").append(toIndentedString(patriName)).append("\n");
    sb.append("    polled: ").append(toIndentedString(polled)).append("\n");
    sb.append("    storyHandle: ").append(toIndentedString(storyHandle)).append("\n");
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

