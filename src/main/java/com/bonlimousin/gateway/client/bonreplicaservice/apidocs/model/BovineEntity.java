package com.bonlimousin.gateway.client.bonreplicaservice.apidocs.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.model.JournalEntryEntity;
import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.model.SourceFileEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BovineEntity
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-04T00:07:53.375872+02:00[Europe/Stockholm]")

public class BovineEntity   {
  @JsonProperty("birthDate")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime birthDate;

  /**
   * Gets or Sets bovineStatus
   */
  public enum BovineStatusEnum {
    UNKNOWN("UNKNOWN"),
    
    SOLD("SOLD"),
    
    MEAT("MEAT"),
    
    ON_FARM("ON_FARM");

    private String value;

    BovineStatusEnum(String value) {
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
    public static BovineStatusEnum fromValue(String value) {
      for (BovineStatusEnum b : BovineStatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("bovineStatus")
  private BovineStatusEnum bovineStatus;

  @JsonProperty("country")
  private String country;

  @JsonProperty("earTagId")
  private Integer earTagId;

  /**
   * Gets or Sets gender
   */
  public enum GenderEnum {
    HEIFER("HEIFER"),
    
    BULL("BULL");

    private String value;

    GenderEnum(String value) {
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
    public static GenderEnum fromValue(String value) {
      for (GenderEnum b : GenderEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("gender")
  private GenderEnum gender;

  @JsonProperty("herdId")
  private Integer herdId;

  /**
   * Gets or Sets hornStatus
   */
  public enum HornStatusEnum {
    UNKNOWN("UNKNOWN"),
    
    HORNED("HORNED"),
    
    POLLED("POLLED"),
    
    SCURS("SCURS"),
    
    DEHORNED("DEHORNED"),
    
    DISBUDDED("DISBUDDED");

    private String value;

    HornStatusEnum(String value) {
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
    public static HornStatusEnum fromValue(String value) {
      for (HornStatusEnum b : HornStatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("hornStatus")
  private HornStatusEnum hornStatus;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("journalEntries")
  @Valid
  private List<JournalEntryEntity> journalEntries = null;

  @JsonProperty("masterIdentifier")
  private String masterIdentifier;

  @JsonProperty("matriId")
  private Integer matriId;

  @JsonProperty("name")
  private String name;

  @JsonProperty("patriId")
  private Integer patriId;

  @JsonProperty("sourceFile")
  private SourceFileEntity sourceFile;

  @JsonProperty("weight0")
  private Integer weight0;

  @JsonProperty("weight200")
  private Integer weight200;

  @JsonProperty("weight365")
  private Integer weight365;

  public BovineEntity birthDate(OffsetDateTime birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  /**
   * Get birthDate
   * @return birthDate
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public OffsetDateTime getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(OffsetDateTime birthDate) {
    this.birthDate = birthDate;
  }

  public BovineEntity bovineStatus(BovineStatusEnum bovineStatus) {
    this.bovineStatus = bovineStatus;
    return this;
  }

  /**
   * Get bovineStatus
   * @return bovineStatus
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public BovineStatusEnum getBovineStatus() {
    return bovineStatus;
  }

  public void setBovineStatus(BovineStatusEnum bovineStatus) {
    this.bovineStatus = bovineStatus;
  }

  public BovineEntity country(String country) {
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

  public BovineEntity earTagId(Integer earTagId) {
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

  public BovineEntity gender(GenderEnum gender) {
    this.gender = gender;
    return this;
  }

  /**
   * Get gender
   * @return gender
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public GenderEnum getGender() {
    return gender;
  }

  public void setGender(GenderEnum gender) {
    this.gender = gender;
  }

  public BovineEntity herdId(Integer herdId) {
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

  public BovineEntity hornStatus(HornStatusEnum hornStatus) {
    this.hornStatus = hornStatus;
    return this;
  }

  /**
   * Get hornStatus
   * @return hornStatus
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public HornStatusEnum getHornStatus() {
    return hornStatus;
  }

  public void setHornStatus(HornStatusEnum hornStatus) {
    this.hornStatus = hornStatus;
  }

  public BovineEntity id(Long id) {
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

  public BovineEntity journalEntries(List<JournalEntryEntity> journalEntries) {
    this.journalEntries = journalEntries;
    return this;
  }

  public BovineEntity addJournalEntriesItem(JournalEntryEntity journalEntriesItem) {
    if (this.journalEntries == null) {
      this.journalEntries = new ArrayList<>();
    }
    this.journalEntries.add(journalEntriesItem);
    return this;
  }

  /**
   * Get journalEntries
   * @return journalEntries
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<JournalEntryEntity> getJournalEntries() {
    return journalEntries;
  }

  public void setJournalEntries(List<JournalEntryEntity> journalEntries) {
    this.journalEntries = journalEntries;
  }

  public BovineEntity masterIdentifier(String masterIdentifier) {
    this.masterIdentifier = masterIdentifier;
    return this;
  }

  /**
   * Get masterIdentifier
   * @return masterIdentifier
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(min=0,max=127) 
  public String getMasterIdentifier() {
    return masterIdentifier;
  }

  public void setMasterIdentifier(String masterIdentifier) {
    this.masterIdentifier = masterIdentifier;
  }

  public BovineEntity matriId(Integer matriId) {
    this.matriId = matriId;
    return this;
  }

  /**
   * Get matriId
   * @return matriId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getMatriId() {
    return matriId;
  }

  public void setMatriId(Integer matriId) {
    this.matriId = matriId;
  }

  public BovineEntity name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(min=0,max=255) 
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BovineEntity patriId(Integer patriId) {
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

  public BovineEntity sourceFile(SourceFileEntity sourceFile) {
    this.sourceFile = sourceFile;
    return this;
  }

  /**
   * Get sourceFile
   * @return sourceFile
  */
  @ApiModelProperty(value = "")

  @Valid

  public SourceFileEntity getSourceFile() {
    return sourceFile;
  }

  public void setSourceFile(SourceFileEntity sourceFile) {
    this.sourceFile = sourceFile;
  }

  public BovineEntity weight0(Integer weight0) {
    this.weight0 = weight0;
    return this;
  }

  /**
   * Get weight0
   * minimum: 0
   * maximum: 99
   * @return weight0
  */
  @ApiModelProperty(value = "")

@Min(0) @Max(99) 
  public Integer getWeight0() {
    return weight0;
  }

  public void setWeight0(Integer weight0) {
    this.weight0 = weight0;
  }

  public BovineEntity weight200(Integer weight200) {
    this.weight200 = weight200;
    return this;
  }

  /**
   * Get weight200
   * minimum: 0
   * maximum: 999
   * @return weight200
  */
  @ApiModelProperty(value = "")

@Min(0) @Max(999) 
  public Integer getWeight200() {
    return weight200;
  }

  public void setWeight200(Integer weight200) {
    this.weight200 = weight200;
  }

  public BovineEntity weight365(Integer weight365) {
    this.weight365 = weight365;
    return this;
  }

  /**
   * Get weight365
   * minimum: 0
   * maximum: 9999
   * @return weight365
  */
  @ApiModelProperty(value = "")

@Min(0) @Max(9999) 
  public Integer getWeight365() {
    return weight365;
  }

  public void setWeight365(Integer weight365) {
    this.weight365 = weight365;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BovineEntity bovineEntity = (BovineEntity) o;
    return Objects.equals(this.birthDate, bovineEntity.birthDate) &&
        Objects.equals(this.bovineStatus, bovineEntity.bovineStatus) &&
        Objects.equals(this.country, bovineEntity.country) &&
        Objects.equals(this.earTagId, bovineEntity.earTagId) &&
        Objects.equals(this.gender, bovineEntity.gender) &&
        Objects.equals(this.herdId, bovineEntity.herdId) &&
        Objects.equals(this.hornStatus, bovineEntity.hornStatus) &&
        Objects.equals(this.id, bovineEntity.id) &&
        Objects.equals(this.journalEntries, bovineEntity.journalEntries) &&
        Objects.equals(this.masterIdentifier, bovineEntity.masterIdentifier) &&
        Objects.equals(this.matriId, bovineEntity.matriId) &&
        Objects.equals(this.name, bovineEntity.name) &&
        Objects.equals(this.patriId, bovineEntity.patriId) &&
        Objects.equals(this.sourceFile, bovineEntity.sourceFile) &&
        Objects.equals(this.weight0, bovineEntity.weight0) &&
        Objects.equals(this.weight200, bovineEntity.weight200) &&
        Objects.equals(this.weight365, bovineEntity.weight365);
  }

  @Override
  public int hashCode() {
    return Objects.hash(birthDate, bovineStatus, country, earTagId, gender, herdId, hornStatus, id, journalEntries, masterIdentifier, matriId, name, patriId, sourceFile, weight0, weight200, weight365);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BovineEntity {\n");
    
    sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
    sb.append("    bovineStatus: ").append(toIndentedString(bovineStatus)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    earTagId: ").append(toIndentedString(earTagId)).append("\n");
    sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
    sb.append("    herdId: ").append(toIndentedString(herdId)).append("\n");
    sb.append("    hornStatus: ").append(toIndentedString(hornStatus)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    journalEntries: ").append(toIndentedString(journalEntries)).append("\n");
    sb.append("    masterIdentifier: ").append(toIndentedString(masterIdentifier)).append("\n");
    sb.append("    matriId: ").append(toIndentedString(matriId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    patriId: ").append(toIndentedString(patriId)).append("\n");
    sb.append("    sourceFile: ").append(toIndentedString(sourceFile)).append("\n");
    sb.append("    weight0: ").append(toIndentedString(weight0)).append("\n");
    sb.append("    weight200: ").append(toIndentedString(weight200)).append("\n");
    sb.append("    weight365: ").append(toIndentedString(weight365)).append("\n");
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

