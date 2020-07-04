package com.bonlimousin.gateway.client.bonreplicaservice.apidocs.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.model.BovineEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BlupEntity
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-04T00:07:53.375872+02:00[Europe/Stockholm]")

public class BlupEntity   {
  @JsonProperty("bovine")
  private BovineEntity bovine;

  @JsonProperty("d0")
  private Integer d0;

  @JsonProperty("d200")
  private Integer d200;

  @JsonProperty("d365")
  private Integer d365;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("m0")
  private Integer m0;

  @JsonProperty("m200")
  private Integer m200;

  @JsonProperty("status")
  private String status;

  @JsonProperty("t0")
  private Integer t0;

  @JsonProperty("t200")
  private Integer t200;

  @JsonProperty("t365")
  private Integer t365;

  @JsonProperty("total")
  private Integer total;

  public BlupEntity bovine(BovineEntity bovine) {
    this.bovine = bovine;
    return this;
  }

  /**
   * Get bovine
   * @return bovine
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public BovineEntity getBovine() {
    return bovine;
  }

  public void setBovine(BovineEntity bovine) {
    this.bovine = bovine;
  }

  public BlupEntity d0(Integer d0) {
    this.d0 = d0;
    return this;
  }

  /**
   * Get d0
   * minimum: 0
   * @return d0
  */
  @ApiModelProperty(value = "")

@Min(0)
  public Integer getD0() {
    return d0;
  }

  public void setD0(Integer d0) {
    this.d0 = d0;
  }

  public BlupEntity d200(Integer d200) {
    this.d200 = d200;
    return this;
  }

  /**
   * Get d200
   * minimum: 0
   * @return d200
  */
  @ApiModelProperty(value = "")

@Min(0)
  public Integer getD200() {
    return d200;
  }

  public void setD200(Integer d200) {
    this.d200 = d200;
  }

  public BlupEntity d365(Integer d365) {
    this.d365 = d365;
    return this;
  }

  /**
   * Get d365
   * minimum: 0
   * @return d365
  */
  @ApiModelProperty(value = "")

@Min(0)
  public Integer getD365() {
    return d365;
  }

  public void setD365(Integer d365) {
    this.d365 = d365;
  }

  public BlupEntity id(Long id) {
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

  public BlupEntity m0(Integer m0) {
    this.m0 = m0;
    return this;
  }

  /**
   * Get m0
   * minimum: 0
   * @return m0
  */
  @ApiModelProperty(value = "")

@Min(0)
  public Integer getM0() {
    return m0;
  }

  public void setM0(Integer m0) {
    this.m0 = m0;
  }

  public BlupEntity m200(Integer m200) {
    this.m200 = m200;
    return this;
  }

  /**
   * Get m200
   * minimum: 0
   * @return m200
  */
  @ApiModelProperty(value = "")

@Min(0)
  public Integer getM200() {
    return m200;
  }

  public void setM200(Integer m200) {
    this.m200 = m200;
  }

  public BlupEntity status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @ApiModelProperty(value = "")

@Size(min=1,max=2147483647) 
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public BlupEntity t0(Integer t0) {
    this.t0 = t0;
    return this;
  }

  /**
   * Get t0
   * minimum: 0
   * @return t0
  */
  @ApiModelProperty(value = "")

@Min(0)
  public Integer getT0() {
    return t0;
  }

  public void setT0(Integer t0) {
    this.t0 = t0;
  }

  public BlupEntity t200(Integer t200) {
    this.t200 = t200;
    return this;
  }

  /**
   * Get t200
   * minimum: 0
   * @return t200
  */
  @ApiModelProperty(value = "")

@Min(0)
  public Integer getT200() {
    return t200;
  }

  public void setT200(Integer t200) {
    this.t200 = t200;
  }

  public BlupEntity t365(Integer t365) {
    this.t365 = t365;
    return this;
  }

  /**
   * Get t365
   * minimum: 0
   * @return t365
  */
  @ApiModelProperty(value = "")

@Min(0)
  public Integer getT365() {
    return t365;
  }

  public void setT365(Integer t365) {
    this.t365 = t365;
  }

  public BlupEntity total(Integer total) {
    this.total = total;
    return this;
  }

  /**
   * Get total
   * minimum: 0
   * @return total
  */
  @ApiModelProperty(value = "")

@Min(0)
  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BlupEntity blupEntity = (BlupEntity) o;
    return Objects.equals(this.bovine, blupEntity.bovine) &&
        Objects.equals(this.d0, blupEntity.d0) &&
        Objects.equals(this.d200, blupEntity.d200) &&
        Objects.equals(this.d365, blupEntity.d365) &&
        Objects.equals(this.id, blupEntity.id) &&
        Objects.equals(this.m0, blupEntity.m0) &&
        Objects.equals(this.m200, blupEntity.m200) &&
        Objects.equals(this.status, blupEntity.status) &&
        Objects.equals(this.t0, blupEntity.t0) &&
        Objects.equals(this.t200, blupEntity.t200) &&
        Objects.equals(this.t365, blupEntity.t365) &&
        Objects.equals(this.total, blupEntity.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bovine, d0, d200, d365, id, m0, m200, status, t0, t200, t365, total);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BlupEntity {\n");
    
    sb.append("    bovine: ").append(toIndentedString(bovine)).append("\n");
    sb.append("    d0: ").append(toIndentedString(d0)).append("\n");
    sb.append("    d200: ").append(toIndentedString(d200)).append("\n");
    sb.append("    d365: ").append(toIndentedString(d365)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    m0: ").append(toIndentedString(m0)).append("\n");
    sb.append("    m200: ").append(toIndentedString(m200)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    t0: ").append(toIndentedString(t0)).append("\n");
    sb.append("    t200: ").append(toIndentedString(t200)).append("\n");
    sb.append("    t365: ").append(toIndentedString(t365)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
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

