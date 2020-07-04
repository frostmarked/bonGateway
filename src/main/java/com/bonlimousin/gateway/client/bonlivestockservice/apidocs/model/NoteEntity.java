package com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.CattleEntity;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.PastureEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * NoteEntity
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-03T23:56:19.029760+02:00[Europe/Stockholm]")

public class NoteEntity   {
  @JsonProperty("actualDate")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
  private LocalDate actualDate;

  /**
   * Gets or Sets category
   */
  public enum CategoryEnum {
    GENERAL("GENERAL"),
    
    TREATMENT("TREATMENT"),
    
    HOOF_CARE("HOOF_CARE"),
    
    DEHORNING("DEHORNING"),
    
    PASTURE_CHANGE("PASTURE_CHANGE"),
    
    PASTURE_PLANNED("PASTURE_PLANNED"),
    
    WEIGHT("WEIGHT"),
    
    WEIGHING0("WEIGHING0"),
    
    WEIGHING200("WEIGHING200"),
    
    WEIGHING365("WEIGHING365"),
    
    ALERT("ALERT"),
    
    TEMPER("TEMPER"),
    
    ESTRUS("ESTRUS"),
    
    INSEMINATION("INSEMINATION");

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

  @JsonProperty("cattle")
  private CattleEntity cattle;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("note")
  private String note;

  @JsonProperty("pasture")
  private PastureEntity pasture;

  public NoteEntity actualDate(LocalDate actualDate) {
    this.actualDate = actualDate;
    return this;
  }

  /**
   * Get actualDate
   * @return actualDate
  */
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getActualDate() {
    return actualDate;
  }

  public void setActualDate(LocalDate actualDate) {
    this.actualDate = actualDate;
  }

  public NoteEntity category(CategoryEnum category) {
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

  public NoteEntity cattle(CattleEntity cattle) {
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

  public NoteEntity id(Long id) {
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

  public NoteEntity note(String note) {
    this.note = note;
    return this;
  }

  /**
   * Get note
   * @return note
  */
  @ApiModelProperty(value = "")

@Size(min=0,max=512) 
  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public NoteEntity pasture(PastureEntity pasture) {
    this.pasture = pasture;
    return this;
  }

  /**
   * Get pasture
   * @return pasture
  */
  @ApiModelProperty(value = "")

  @Valid

  public PastureEntity getPasture() {
    return pasture;
  }

  public void setPasture(PastureEntity pasture) {
    this.pasture = pasture;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NoteEntity noteEntity = (NoteEntity) o;
    return Objects.equals(this.actualDate, noteEntity.actualDate) &&
        Objects.equals(this.category, noteEntity.category) &&
        Objects.equals(this.cattle, noteEntity.cattle) &&
        Objects.equals(this.id, noteEntity.id) &&
        Objects.equals(this.note, noteEntity.note) &&
        Objects.equals(this.pasture, noteEntity.pasture);
  }

  @Override
  public int hashCode() {
    return Objects.hash(actualDate, category, cattle, id, note, pasture);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NoteEntity {\n");
    
    sb.append("    actualDate: ").append(toIndentedString(actualDate)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    cattle: ").append(toIndentedString(cattle)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
    sb.append("    pasture: ").append(toIndentedString(pasture)).append("\n");
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

