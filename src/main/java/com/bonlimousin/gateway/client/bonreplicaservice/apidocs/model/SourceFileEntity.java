package com.bonlimousin.gateway.client.bonreplicaservice.apidocs.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SourceFileEntity
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-04T00:07:53.375872+02:00[Europe/Stockholm]")

public class SourceFileEntity   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("outcome")
  private String outcome;

  @JsonProperty("processed")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime processed;

  @JsonProperty("zipFile")
  private byte[] zipFile;

  @JsonProperty("zipFileContentType")
  private String zipFileContentType;

  public SourceFileEntity id(Long id) {
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

  public SourceFileEntity name(String name) {
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

  public SourceFileEntity outcome(String outcome) {
    this.outcome = outcome;
    return this;
  }

  /**
   * Get outcome
   * @return outcome
  */
  @ApiModelProperty(value = "")


  public String getOutcome() {
    return outcome;
  }

  public void setOutcome(String outcome) {
    this.outcome = outcome;
  }

  public SourceFileEntity processed(OffsetDateTime processed) {
    this.processed = processed;
    return this;
  }

  /**
   * Get processed
   * @return processed
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getProcessed() {
    return processed;
  }

  public void setProcessed(OffsetDateTime processed) {
    this.processed = processed;
  }

  public SourceFileEntity zipFile(byte[] zipFile) {
    this.zipFile = zipFile;
    return this;
  }

  /**
   * Get zipFile
   * @return zipFile
  */
  @ApiModelProperty(value = "")

@Pattern(regexp="^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$") 
  public byte[] getZipFile() {
    return zipFile;
  }

  public void setZipFile(byte[] zipFile) {
    this.zipFile = zipFile;
  }

  public SourceFileEntity zipFileContentType(String zipFileContentType) {
    this.zipFileContentType = zipFileContentType;
    return this;
  }

  /**
   * Get zipFileContentType
   * @return zipFileContentType
  */
  @ApiModelProperty(value = "")


  public String getZipFileContentType() {
    return zipFileContentType;
  }

  public void setZipFileContentType(String zipFileContentType) {
    this.zipFileContentType = zipFileContentType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SourceFileEntity sourceFileEntity = (SourceFileEntity) o;
    return Objects.equals(this.id, sourceFileEntity.id) &&
        Objects.equals(this.name, sourceFileEntity.name) &&
        Objects.equals(this.outcome, sourceFileEntity.outcome) &&
        Objects.equals(this.processed, sourceFileEntity.processed) &&
        Objects.equals(this.zipFile, sourceFileEntity.zipFile) &&
        Objects.equals(this.zipFileContentType, sourceFileEntity.zipFileContentType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, outcome, processed, zipFile, zipFileContentType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SourceFileEntity {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    outcome: ").append(toIndentedString(outcome)).append("\n");
    sb.append("    processed: ").append(toIndentedString(processed)).append("\n");
    sb.append("    zipFile: ").append(toIndentedString(zipFile)).append("\n");
    sb.append("    zipFileContentType: ").append(toIndentedString(zipFileContentType)).append("\n");
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

