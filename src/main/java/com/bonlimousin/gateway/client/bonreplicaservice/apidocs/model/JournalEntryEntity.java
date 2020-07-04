package com.bonlimousin.gateway.client.bonreplicaservice.apidocs.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.model.BovineEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * JournalEntryEntity
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-04T00:07:53.375872+02:00[Europe/Stockholm]")

public class JournalEntryEntity   {
  @JsonProperty("bovine")
  private BovineEntity bovine;

  @JsonProperty("date")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime date;

  @JsonProperty("edited")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime edited;

  @JsonProperty("herdId")
  private Integer herdId;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("newHerdId")
  private Integer newHerdId;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    FOD("FOD"),
    
    IB("IB"),
    
    TU("TU"),
    
    RET("RET"),
    
    UTG("UTG");

    private String value;

    StatusEnum(String value) {
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
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("status")
  private StatusEnum status;

  @JsonProperty("subState1")
  private Integer subState1;

  @JsonProperty("subState2")
  private Integer subState2;

  public JournalEntryEntity bovine(BovineEntity bovine) {
    this.bovine = bovine;
    return this;
  }

  /**
   * Get bovine
   * @return bovine
  */
  @ApiModelProperty(value = "")

  @Valid

  public BovineEntity getBovine() {
    return bovine;
  }

  public void setBovine(BovineEntity bovine) {
    this.bovine = bovine;
  }

  public JournalEntryEntity date(OffsetDateTime date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public OffsetDateTime getDate() {
    return date;
  }

  public void setDate(OffsetDateTime date) {
    this.date = date;
  }

  public JournalEntryEntity edited(OffsetDateTime edited) {
    this.edited = edited;
    return this;
  }

  /**
   * Get edited
   * @return edited
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getEdited() {
    return edited;
  }

  public void setEdited(OffsetDateTime edited) {
    this.edited = edited;
  }

  public JournalEntryEntity herdId(Integer herdId) {
    this.herdId = herdId;
    return this;
  }

  /**
   * Get herdId
   * minimum: 0
   * @return herdId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Min(0)
  public Integer getHerdId() {
    return herdId;
  }

  public void setHerdId(Integer herdId) {
    this.herdId = herdId;
  }

  public JournalEntryEntity id(Long id) {
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

  public JournalEntryEntity newHerdId(Integer newHerdId) {
    this.newHerdId = newHerdId;
    return this;
  }

  /**
   * Get newHerdId
   * minimum: 0
   * @return newHerdId
  */
  @ApiModelProperty(value = "")

@Min(0)
  public Integer getNewHerdId() {
    return newHerdId;
  }

  public void setNewHerdId(Integer newHerdId) {
    this.newHerdId = newHerdId;
  }

  public JournalEntryEntity status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public JournalEntryEntity subState1(Integer subState1) {
    this.subState1 = subState1;
    return this;
  }

  /**
   * Get subState1
   * @return subState1
  */
  @ApiModelProperty(value = "")


  public Integer getSubState1() {
    return subState1;
  }

  public void setSubState1(Integer subState1) {
    this.subState1 = subState1;
  }

  public JournalEntryEntity subState2(Integer subState2) {
    this.subState2 = subState2;
    return this;
  }

  /**
   * Get subState2
   * @return subState2
  */
  @ApiModelProperty(value = "")


  public Integer getSubState2() {
    return subState2;
  }

  public void setSubState2(Integer subState2) {
    this.subState2 = subState2;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JournalEntryEntity journalEntryEntity = (JournalEntryEntity) o;
    return Objects.equals(this.bovine, journalEntryEntity.bovine) &&
        Objects.equals(this.date, journalEntryEntity.date) &&
        Objects.equals(this.edited, journalEntryEntity.edited) &&
        Objects.equals(this.herdId, journalEntryEntity.herdId) &&
        Objects.equals(this.id, journalEntryEntity.id) &&
        Objects.equals(this.newHerdId, journalEntryEntity.newHerdId) &&
        Objects.equals(this.status, journalEntryEntity.status) &&
        Objects.equals(this.subState1, journalEntryEntity.subState1) &&
        Objects.equals(this.subState2, journalEntryEntity.subState2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bovine, date, edited, herdId, id, newHerdId, status, subState1, subState2);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JournalEntryEntity {\n");
    
    sb.append("    bovine: ").append(toIndentedString(bovine)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    edited: ").append(toIndentedString(edited)).append("\n");
    sb.append("    herdId: ").append(toIndentedString(herdId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    newHerdId: ").append(toIndentedString(newHerdId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    subState1: ").append(toIndentedString(subState1)).append("\n");
    sb.append("    subState2: ").append(toIndentedString(subState2)).append("\n");
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

