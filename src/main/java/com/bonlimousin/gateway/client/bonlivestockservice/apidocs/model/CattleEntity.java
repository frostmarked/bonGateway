package com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.MatrilinealityEntity;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.NoteEntity;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.PhotoEntity;
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
 * CattleEntity
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-03T23:56:19.029760+02:00[Europe/Stockholm]")

public class CattleEntity   {
  @JsonProperty("alert")
  private Boolean alert;

  @JsonProperty("earTagId")
  private Integer earTagId;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("matrilineality")
  private MatrilinealityEntity matrilineality;

  @JsonProperty("name")
  private String name;

  @JsonProperty("notes")
  @Valid
  private List<NoteEntity> notes = null;

  @JsonProperty("photos")
  @Valid
  private List<PhotoEntity> photos = null;

  @JsonProperty("showBlup")
  private Boolean showBlup;

  @JsonProperty("storyHandle")
  private String storyHandle;

  @JsonProperty("upForSale")
  private Boolean upForSale;

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

  public CattleEntity alert(Boolean alert) {
    this.alert = alert;
    return this;
  }

  /**
   * Get alert
   * @return alert
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Boolean getAlert() {
    return alert;
  }

  public void setAlert(Boolean alert) {
    this.alert = alert;
  }

  public CattleEntity earTagId(Integer earTagId) {
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

  public CattleEntity id(Long id) {
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

  public CattleEntity matrilineality(MatrilinealityEntity matrilineality) {
    this.matrilineality = matrilineality;
    return this;
  }

  /**
   * Get matrilineality
   * @return matrilineality
  */
  @ApiModelProperty(value = "")

  @Valid

  public MatrilinealityEntity getMatrilineality() {
    return matrilineality;
  }

  public void setMatrilineality(MatrilinealityEntity matrilineality) {
    this.matrilineality = matrilineality;
  }

  public CattleEntity name(String name) {
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

  public CattleEntity notes(List<NoteEntity> notes) {
    this.notes = notes;
    return this;
  }

  public CattleEntity addNotesItem(NoteEntity notesItem) {
    if (this.notes == null) {
      this.notes = new ArrayList<>();
    }
    this.notes.add(notesItem);
    return this;
  }

  /**
   * Get notes
   * @return notes
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<NoteEntity> getNotes() {
    return notes;
  }

  public void setNotes(List<NoteEntity> notes) {
    this.notes = notes;
  }

  public CattleEntity photos(List<PhotoEntity> photos) {
    this.photos = photos;
    return this;
  }

  public CattleEntity addPhotosItem(PhotoEntity photosItem) {
    if (this.photos == null) {
      this.photos = new ArrayList<>();
    }
    this.photos.add(photosItem);
    return this;
  }

  /**
   * Get photos
   * @return photos
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<PhotoEntity> getPhotos() {
    return photos;
  }

  public void setPhotos(List<PhotoEntity> photos) {
    this.photos = photos;
  }

  public CattleEntity showBlup(Boolean showBlup) {
    this.showBlup = showBlup;
    return this;
  }

  /**
   * Get showBlup
   * @return showBlup
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Boolean getShowBlup() {
    return showBlup;
  }

  public void setShowBlup(Boolean showBlup) {
    this.showBlup = showBlup;
  }

  public CattleEntity storyHandle(String storyHandle) {
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

  public CattleEntity upForSale(Boolean upForSale) {
    this.upForSale = upForSale;
    return this;
  }

  /**
   * Get upForSale
   * @return upForSale
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Boolean getUpForSale() {
    return upForSale;
  }

  public void setUpForSale(Boolean upForSale) {
    this.upForSale = upForSale;
  }

  public CattleEntity visibility(VisibilityEnum visibility) {
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
    CattleEntity cattleEntity = (CattleEntity) o;
    return Objects.equals(this.alert, cattleEntity.alert) &&
        Objects.equals(this.earTagId, cattleEntity.earTagId) &&
        Objects.equals(this.id, cattleEntity.id) &&
        Objects.equals(this.matrilineality, cattleEntity.matrilineality) &&
        Objects.equals(this.name, cattleEntity.name) &&
        Objects.equals(this.notes, cattleEntity.notes) &&
        Objects.equals(this.photos, cattleEntity.photos) &&
        Objects.equals(this.showBlup, cattleEntity.showBlup) &&
        Objects.equals(this.storyHandle, cattleEntity.storyHandle) &&
        Objects.equals(this.upForSale, cattleEntity.upForSale) &&
        Objects.equals(this.visibility, cattleEntity.visibility);
  }

  @Override
  public int hashCode() {
    return Objects.hash(alert, earTagId, id, matrilineality, name, notes, photos, showBlup, storyHandle, upForSale, visibility);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CattleEntity {\n");
    
    sb.append("    alert: ").append(toIndentedString(alert)).append("\n");
    sb.append("    earTagId: ").append(toIndentedString(earTagId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    matrilineality: ").append(toIndentedString(matrilineality)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    notes: ").append(toIndentedString(notes)).append("\n");
    sb.append("    photos: ").append(toIndentedString(photos)).append("\n");
    sb.append("    showBlup: ").append(toIndentedString(showBlup)).append("\n");
    sb.append("    storyHandle: ").append(toIndentedString(storyHandle)).append("\n");
    sb.append("    upForSale: ").append(toIndentedString(upForSale)).append("\n");
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

