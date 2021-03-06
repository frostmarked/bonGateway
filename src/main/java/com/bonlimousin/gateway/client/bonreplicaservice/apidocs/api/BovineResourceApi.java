/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.bonlimousin.gateway.client.bonreplicaservice.apidocs.api;

import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.model.BovineEntity;
import com.bonlimousin.gateway.client.bonreplicaservice.apidocs.querymap.BovineCriteria;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-04T00:07:53.375872+02:00[Europe/Stockholm]")

@Validated
@Api(value = "BovineResource", description = "the BovineResource API")
public interface BovineResourceApi {

    /**
     * GET /api/bovines/count : countBovines
     *
     * @param birthDateEquals  (optional)
     * @param birthDateGreaterThan  (optional)
     * @param birthDateGreaterThanOrEqual  (optional)
     * @param birthDateIn0EpochSecond  (optional)
     * @param birthDateIn0Nano  (optional)
     * @param birthDateLessThan  (optional)
     * @param birthDateLessThanOrEqual  (optional)
     * @param birthDateNotEquals  (optional)
     * @param birthDateNotIn0EpochSecond  (optional)
     * @param birthDateNotIn0Nano  (optional)
     * @param birthDateSpecified  (optional)
     * @param blupIdEquals  (optional)
     * @param blupIdGreaterThan  (optional)
     * @param blupIdGreaterThanOrEqual  (optional)
     * @param blupIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param blupIdLessThan  (optional)
     * @param blupIdLessThanOrEqual  (optional)
     * @param blupIdNotEquals  (optional)
     * @param blupIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param blupIdSpecified  (optional)
     * @param bovineStatusEquals  (optional)
     * @param bovineStatusIn  (optional, default to new ArrayList&lt;&gt;())
     * @param bovineStatusNotEquals  (optional)
     * @param bovineStatusNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param bovineStatusSpecified  (optional)
     * @param countryContains  (optional)
     * @param countryDoesNotContain  (optional)
     * @param countryEquals  (optional)
     * @param countryIn  (optional, default to new ArrayList&lt;&gt;())
     * @param countryNotEquals  (optional)
     * @param countryNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param countrySpecified  (optional)
     * @param earTagIdEquals  (optional)
     * @param earTagIdGreaterThan  (optional)
     * @param earTagIdGreaterThanOrEqual  (optional)
     * @param earTagIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param earTagIdLessThan  (optional)
     * @param earTagIdLessThanOrEqual  (optional)
     * @param earTagIdNotEquals  (optional)
     * @param earTagIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param earTagIdSpecified  (optional)
     * @param genderEquals  (optional)
     * @param genderIn  (optional, default to new ArrayList&lt;&gt;())
     * @param genderNotEquals  (optional)
     * @param genderNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param genderSpecified  (optional)
     * @param herdIdEquals  (optional)
     * @param herdIdGreaterThan  (optional)
     * @param herdIdGreaterThanOrEqual  (optional)
     * @param herdIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param herdIdLessThan  (optional)
     * @param herdIdLessThanOrEqual  (optional)
     * @param herdIdNotEquals  (optional)
     * @param herdIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param herdIdSpecified  (optional)
     * @param hornStatusEquals  (optional)
     * @param hornStatusIn  (optional, default to new ArrayList&lt;&gt;())
     * @param hornStatusNotEquals  (optional)
     * @param hornStatusNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param hornStatusSpecified  (optional)
     * @param idEquals  (optional)
     * @param idGreaterThan  (optional)
     * @param idGreaterThanOrEqual  (optional)
     * @param idIn  (optional, default to new ArrayList&lt;&gt;())
     * @param idLessThan  (optional)
     * @param idLessThanOrEqual  (optional)
     * @param idNotEquals  (optional)
     * @param idNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param idSpecified  (optional)
     * @param journalEntriesIdEquals  (optional)
     * @param journalEntriesIdGreaterThan  (optional)
     * @param journalEntriesIdGreaterThanOrEqual  (optional)
     * @param journalEntriesIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param journalEntriesIdLessThan  (optional)
     * @param journalEntriesIdLessThanOrEqual  (optional)
     * @param journalEntriesIdNotEquals  (optional)
     * @param journalEntriesIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param journalEntriesIdSpecified  (optional)
     * @param masterIdentifierContains  (optional)
     * @param masterIdentifierDoesNotContain  (optional)
     * @param masterIdentifierEquals  (optional)
     * @param masterIdentifierIn  (optional, default to new ArrayList&lt;&gt;())
     * @param masterIdentifierNotEquals  (optional)
     * @param masterIdentifierNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param masterIdentifierSpecified  (optional)
     * @param matriIdEquals  (optional)
     * @param matriIdGreaterThan  (optional)
     * @param matriIdGreaterThanOrEqual  (optional)
     * @param matriIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param matriIdLessThan  (optional)
     * @param matriIdLessThanOrEqual  (optional)
     * @param matriIdNotEquals  (optional)
     * @param matriIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param matriIdSpecified  (optional)
     * @param nameContains  (optional)
     * @param nameDoesNotContain  (optional)
     * @param nameEquals  (optional)
     * @param nameIn  (optional, default to new ArrayList&lt;&gt;())
     * @param nameNotEquals  (optional)
     * @param nameNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param nameSpecified  (optional)
     * @param patriIdEquals  (optional)
     * @param patriIdGreaterThan  (optional)
     * @param patriIdGreaterThanOrEqual  (optional)
     * @param patriIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param patriIdLessThan  (optional)
     * @param patriIdLessThanOrEqual  (optional)
     * @param patriIdNotEquals  (optional)
     * @param patriIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param patriIdSpecified  (optional)
     * @param sourceFileIdEquals  (optional)
     * @param sourceFileIdGreaterThan  (optional)
     * @param sourceFileIdGreaterThanOrEqual  (optional)
     * @param sourceFileIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param sourceFileIdLessThan  (optional)
     * @param sourceFileIdLessThanOrEqual  (optional)
     * @param sourceFileIdNotEquals  (optional)
     * @param sourceFileIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param sourceFileIdSpecified  (optional)
     * @param weight0Equals  (optional)
     * @param weight0GreaterThan  (optional)
     * @param weight0GreaterThanOrEqual  (optional)
     * @param weight0In  (optional, default to new ArrayList&lt;&gt;())
     * @param weight0LessThan  (optional)
     * @param weight0LessThanOrEqual  (optional)
     * @param weight0NotEquals  (optional)
     * @param weight0NotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param weight0Specified  (optional)
     * @param weight200Equals  (optional)
     * @param weight200GreaterThan  (optional)
     * @param weight200GreaterThanOrEqual  (optional)
     * @param weight200In  (optional, default to new ArrayList&lt;&gt;())
     * @param weight200LessThan  (optional)
     * @param weight200LessThanOrEqual  (optional)
     * @param weight200NotEquals  (optional)
     * @param weight200NotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param weight200Specified  (optional)
     * @param weight365Equals  (optional)
     * @param weight365GreaterThan  (optional)
     * @param weight365GreaterThanOrEqual  (optional)
     * @param weight365In  (optional, default to new ArrayList&lt;&gt;())
     * @param weight365LessThan  (optional)
     * @param weight365LessThanOrEqual  (optional)
     * @param weight365NotEquals  (optional)
     * @param weight365NotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param weight365Specified  (optional)
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "countBovines", nickname = "countBovinesUsingGET", notes = "", response = Long.class, tags={ "bovine-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Long.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/bovines/count",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<Long> countBovinesUsingGET(@ApiParam(value = "") @Valid @RequestParam(value = "birthDate.equals", required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) OffsetDateTime birthDateEquals,@ApiParam(value = "") @Valid @RequestParam(value = "birthDate.greaterThan", required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) OffsetDateTime birthDateGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "birthDate.greaterThanOrEqual", required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) OffsetDateTime birthDateGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "birthDate.in[0].epochSecond", required = false) Long birthDateIn0EpochSecond,@ApiParam(value = "") @Valid @RequestParam(value = "birthDate.in[0].nano", required = false) Integer birthDateIn0Nano,@ApiParam(value = "") @Valid @RequestParam(value = "birthDate.lessThan", required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) OffsetDateTime birthDateLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "birthDate.lessThanOrEqual", required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) OffsetDateTime birthDateLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "birthDate.notEquals", required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) OffsetDateTime birthDateNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "birthDate.notIn[0].epochSecond", required = false) Long birthDateNotIn0EpochSecond,@ApiParam(value = "") @Valid @RequestParam(value = "birthDate.notIn[0].nano", required = false) Integer birthDateNotIn0Nano,@ApiParam(value = "") @Valid @RequestParam(value = "birthDate.specified", required = false) Boolean birthDateSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "blupId.equals", required = false) Long blupIdEquals,@ApiParam(value = "") @Valid @RequestParam(value = "blupId.greaterThan", required = false) Long blupIdGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "blupId.greaterThanOrEqual", required = false) Long blupIdGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "blupId.in", required = false) List<Long> blupIdIn,@ApiParam(value = "") @Valid @RequestParam(value = "blupId.lessThan", required = false) Long blupIdLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "blupId.lessThanOrEqual", required = false) Long blupIdLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "blupId.notEquals", required = false) Long blupIdNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "blupId.notIn", required = false) List<Long> blupIdNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "blupId.specified", required = false) Boolean blupIdSpecified,@ApiParam(value = "", allowableValues = "UNKNOWN, SOLD, MEAT, ON_FARM") @Valid @RequestParam(value = "bovineStatus.equals", required = false) String bovineStatusEquals,@ApiParam(value = "", allowableValues = "UNKNOWN, SOLD, MEAT, ON_FARM") @Valid @RequestParam(value = "bovineStatus.in", required = false) List<String> bovineStatusIn,@ApiParam(value = "", allowableValues = "UNKNOWN, SOLD, MEAT, ON_FARM") @Valid @RequestParam(value = "bovineStatus.notEquals", required = false) String bovineStatusNotEquals,@ApiParam(value = "", allowableValues = "UNKNOWN, SOLD, MEAT, ON_FARM") @Valid @RequestParam(value = "bovineStatus.notIn", required = false) List<String> bovineStatusNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "bovineStatus.specified", required = false) Boolean bovineStatusSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "country.contains", required = false) String countryContains,@ApiParam(value = "") @Valid @RequestParam(value = "country.doesNotContain", required = false) String countryDoesNotContain,@ApiParam(value = "") @Valid @RequestParam(value = "country.equals", required = false) String countryEquals,@ApiParam(value = "") @Valid @RequestParam(value = "country.in", required = false) List<String> countryIn,@ApiParam(value = "") @Valid @RequestParam(value = "country.notEquals", required = false) String countryNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "country.notIn", required = false) List<String> countryNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "country.specified", required = false) Boolean countrySpecified,@ApiParam(value = "") @Valid @RequestParam(value = "earTagId.equals", required = false) Integer earTagIdEquals,@ApiParam(value = "") @Valid @RequestParam(value = "earTagId.greaterThan", required = false) Integer earTagIdGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "earTagId.greaterThanOrEqual", required = false) Integer earTagIdGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "earTagId.in", required = false) List<Integer> earTagIdIn,@ApiParam(value = "") @Valid @RequestParam(value = "earTagId.lessThan", required = false) Integer earTagIdLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "earTagId.lessThanOrEqual", required = false) Integer earTagIdLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "earTagId.notEquals", required = false) Integer earTagIdNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "earTagId.notIn", required = false) List<Integer> earTagIdNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "earTagId.specified", required = false) Boolean earTagIdSpecified,@ApiParam(value = "", allowableValues = "HEIFER, BULL") @Valid @RequestParam(value = "gender.equals", required = false) String genderEquals,@ApiParam(value = "", allowableValues = "HEIFER, BULL") @Valid @RequestParam(value = "gender.in", required = false) List<String> genderIn,@ApiParam(value = "", allowableValues = "HEIFER, BULL") @Valid @RequestParam(value = "gender.notEquals", required = false) String genderNotEquals,@ApiParam(value = "", allowableValues = "HEIFER, BULL") @Valid @RequestParam(value = "gender.notIn", required = false) List<String> genderNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "gender.specified", required = false) Boolean genderSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "herdId.equals", required = false) Integer herdIdEquals,@ApiParam(value = "") @Valid @RequestParam(value = "herdId.greaterThan", required = false) Integer herdIdGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "herdId.greaterThanOrEqual", required = false) Integer herdIdGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "herdId.in", required = false) List<Integer> herdIdIn,@ApiParam(value = "") @Valid @RequestParam(value = "herdId.lessThan", required = false) Integer herdIdLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "herdId.lessThanOrEqual", required = false) Integer herdIdLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "herdId.notEquals", required = false) Integer herdIdNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "herdId.notIn", required = false) List<Integer> herdIdNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "herdId.specified", required = false) Boolean herdIdSpecified,@ApiParam(value = "", allowableValues = "UNKNOWN, HORNED, POLLED, SCURS, DEHORNED, DISBUDDED") @Valid @RequestParam(value = "hornStatus.equals", required = false) String hornStatusEquals,@ApiParam(value = "", allowableValues = "UNKNOWN, HORNED, POLLED, SCURS, DEHORNED, DISBUDDED") @Valid @RequestParam(value = "hornStatus.in", required = false) List<String> hornStatusIn,@ApiParam(value = "", allowableValues = "UNKNOWN, HORNED, POLLED, SCURS, DEHORNED, DISBUDDED") @Valid @RequestParam(value = "hornStatus.notEquals", required = false) String hornStatusNotEquals,@ApiParam(value = "", allowableValues = "UNKNOWN, HORNED, POLLED, SCURS, DEHORNED, DISBUDDED") @Valid @RequestParam(value = "hornStatus.notIn", required = false) List<String> hornStatusNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "hornStatus.specified", required = false) Boolean hornStatusSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "id.equals", required = false) Long idEquals,@ApiParam(value = "") @Valid @RequestParam(value = "id.greaterThan", required = false) Long idGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "id.greaterThanOrEqual", required = false) Long idGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "id.in", required = false) List<Long> idIn,@ApiParam(value = "") @Valid @RequestParam(value = "id.lessThan", required = false) Long idLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "id.lessThanOrEqual", required = false) Long idLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "id.notEquals", required = false) Long idNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "id.notIn", required = false) List<Long> idNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "id.specified", required = false) Boolean idSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "journalEntriesId.equals", required = false) Long journalEntriesIdEquals,@ApiParam(value = "") @Valid @RequestParam(value = "journalEntriesId.greaterThan", required = false) Long journalEntriesIdGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "journalEntriesId.greaterThanOrEqual", required = false) Long journalEntriesIdGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "journalEntriesId.in", required = false) List<Long> journalEntriesIdIn,@ApiParam(value = "") @Valid @RequestParam(value = "journalEntriesId.lessThan", required = false) Long journalEntriesIdLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "journalEntriesId.lessThanOrEqual", required = false) Long journalEntriesIdLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "journalEntriesId.notEquals", required = false) Long journalEntriesIdNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "journalEntriesId.notIn", required = false) List<Long> journalEntriesIdNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "journalEntriesId.specified", required = false) Boolean journalEntriesIdSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "masterIdentifier.contains", required = false) String masterIdentifierContains,@ApiParam(value = "") @Valid @RequestParam(value = "masterIdentifier.doesNotContain", required = false) String masterIdentifierDoesNotContain,@ApiParam(value = "") @Valid @RequestParam(value = "masterIdentifier.equals", required = false) String masterIdentifierEquals,@ApiParam(value = "") @Valid @RequestParam(value = "masterIdentifier.in", required = false) List<String> masterIdentifierIn,@ApiParam(value = "") @Valid @RequestParam(value = "masterIdentifier.notEquals", required = false) String masterIdentifierNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "masterIdentifier.notIn", required = false) List<String> masterIdentifierNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "masterIdentifier.specified", required = false) Boolean masterIdentifierSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "matriId.equals", required = false) Integer matriIdEquals,@ApiParam(value = "") @Valid @RequestParam(value = "matriId.greaterThan", required = false) Integer matriIdGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "matriId.greaterThanOrEqual", required = false) Integer matriIdGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "matriId.in", required = false) List<Integer> matriIdIn,@ApiParam(value = "") @Valid @RequestParam(value = "matriId.lessThan", required = false) Integer matriIdLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "matriId.lessThanOrEqual", required = false) Integer matriIdLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "matriId.notEquals", required = false) Integer matriIdNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "matriId.notIn", required = false) List<Integer> matriIdNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "matriId.specified", required = false) Boolean matriIdSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "name.contains", required = false) String nameContains,@ApiParam(value = "") @Valid @RequestParam(value = "name.doesNotContain", required = false) String nameDoesNotContain,@ApiParam(value = "") @Valid @RequestParam(value = "name.equals", required = false) String nameEquals,@ApiParam(value = "") @Valid @RequestParam(value = "name.in", required = false) List<String> nameIn,@ApiParam(value = "") @Valid @RequestParam(value = "name.notEquals", required = false) String nameNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "name.notIn", required = false) List<String> nameNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "name.specified", required = false) Boolean nameSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "patriId.equals", required = false) Integer patriIdEquals,@ApiParam(value = "") @Valid @RequestParam(value = "patriId.greaterThan", required = false) Integer patriIdGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "patriId.greaterThanOrEqual", required = false) Integer patriIdGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "patriId.in", required = false) List<Integer> patriIdIn,@ApiParam(value = "") @Valid @RequestParam(value = "patriId.lessThan", required = false) Integer patriIdLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "patriId.lessThanOrEqual", required = false) Integer patriIdLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "patriId.notEquals", required = false) Integer patriIdNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "patriId.notIn", required = false) List<Integer> patriIdNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "patriId.specified", required = false) Boolean patriIdSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "sourceFileId.equals", required = false) Long sourceFileIdEquals,@ApiParam(value = "") @Valid @RequestParam(value = "sourceFileId.greaterThan", required = false) Long sourceFileIdGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "sourceFileId.greaterThanOrEqual", required = false) Long sourceFileIdGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "sourceFileId.in", required = false) List<Long> sourceFileIdIn,@ApiParam(value = "") @Valid @RequestParam(value = "sourceFileId.lessThan", required = false) Long sourceFileIdLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "sourceFileId.lessThanOrEqual", required = false) Long sourceFileIdLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "sourceFileId.notEquals", required = false) Long sourceFileIdNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "sourceFileId.notIn", required = false) List<Long> sourceFileIdNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "sourceFileId.specified", required = false) Boolean sourceFileIdSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "weight0.equals", required = false) Integer weight0Equals,@ApiParam(value = "") @Valid @RequestParam(value = "weight0.greaterThan", required = false) Integer weight0GreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "weight0.greaterThanOrEqual", required = false) Integer weight0GreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "weight0.in", required = false) List<Integer> weight0In,@ApiParam(value = "") @Valid @RequestParam(value = "weight0.lessThan", required = false) Integer weight0LessThan,@ApiParam(value = "") @Valid @RequestParam(value = "weight0.lessThanOrEqual", required = false) Integer weight0LessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "weight0.notEquals", required = false) Integer weight0NotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "weight0.notIn", required = false) List<Integer> weight0NotIn,@ApiParam(value = "") @Valid @RequestParam(value = "weight0.specified", required = false) Boolean weight0Specified,@ApiParam(value = "") @Valid @RequestParam(value = "weight200.equals", required = false) Integer weight200Equals,@ApiParam(value = "") @Valid @RequestParam(value = "weight200.greaterThan", required = false) Integer weight200GreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "weight200.greaterThanOrEqual", required = false) Integer weight200GreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "weight200.in", required = false) List<Integer> weight200In,@ApiParam(value = "") @Valid @RequestParam(value = "weight200.lessThan", required = false) Integer weight200LessThan,@ApiParam(value = "") @Valid @RequestParam(value = "weight200.lessThanOrEqual", required = false) Integer weight200LessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "weight200.notEquals", required = false) Integer weight200NotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "weight200.notIn", required = false) List<Integer> weight200NotIn,@ApiParam(value = "") @Valid @RequestParam(value = "weight200.specified", required = false) Boolean weight200Specified,@ApiParam(value = "") @Valid @RequestParam(value = "weight365.equals", required = false) Integer weight365Equals,@ApiParam(value = "") @Valid @RequestParam(value = "weight365.greaterThan", required = false) Integer weight365GreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "weight365.greaterThanOrEqual", required = false) Integer weight365GreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "weight365.in", required = false) List<Integer> weight365In,@ApiParam(value = "") @Valid @RequestParam(value = "weight365.lessThan", required = false) Integer weight365LessThan,@ApiParam(value = "") @Valid @RequestParam(value = "weight365.lessThanOrEqual", required = false) Integer weight365LessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "weight365.notEquals", required = false) Integer weight365NotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "weight365.notIn", required = false) List<Integer> weight365NotIn,@ApiParam(value = "") @Valid @RequestParam(value = "weight365.specified", required = false) Boolean weight365Specified);


    /**
     * GET /api/bovines : getAllBovines
     *
     * @param birthDateEquals  (optional)
     * @param birthDateGreaterThan  (optional)
     * @param birthDateGreaterThanOrEqual  (optional)
     * @param birthDateIn0EpochSecond  (optional)
     * @param birthDateIn0Nano  (optional)
     * @param birthDateLessThan  (optional)
     * @param birthDateLessThanOrEqual  (optional)
     * @param birthDateNotEquals  (optional)
     * @param birthDateNotIn0EpochSecond  (optional)
     * @param birthDateNotIn0Nano  (optional)
     * @param birthDateSpecified  (optional)
     * @param blupIdEquals  (optional)
     * @param blupIdGreaterThan  (optional)
     * @param blupIdGreaterThanOrEqual  (optional)
     * @param blupIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param blupIdLessThan  (optional)
     * @param blupIdLessThanOrEqual  (optional)
     * @param blupIdNotEquals  (optional)
     * @param blupIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param blupIdSpecified  (optional)
     * @param bovineStatusEquals  (optional)
     * @param bovineStatusIn  (optional, default to new ArrayList&lt;&gt;())
     * @param bovineStatusNotEquals  (optional)
     * @param bovineStatusNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param bovineStatusSpecified  (optional)
     * @param countryContains  (optional)
     * @param countryDoesNotContain  (optional)
     * @param countryEquals  (optional)
     * @param countryIn  (optional, default to new ArrayList&lt;&gt;())
     * @param countryNotEquals  (optional)
     * @param countryNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param countrySpecified  (optional)
     * @param earTagIdEquals  (optional)
     * @param earTagIdGreaterThan  (optional)
     * @param earTagIdGreaterThanOrEqual  (optional)
     * @param earTagIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param earTagIdLessThan  (optional)
     * @param earTagIdLessThanOrEqual  (optional)
     * @param earTagIdNotEquals  (optional)
     * @param earTagIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param earTagIdSpecified  (optional)
     * @param genderEquals  (optional)
     * @param genderIn  (optional, default to new ArrayList&lt;&gt;())
     * @param genderNotEquals  (optional)
     * @param genderNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param genderSpecified  (optional)
     * @param herdIdEquals  (optional)
     * @param herdIdGreaterThan  (optional)
     * @param herdIdGreaterThanOrEqual  (optional)
     * @param herdIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param herdIdLessThan  (optional)
     * @param herdIdLessThanOrEqual  (optional)
     * @param herdIdNotEquals  (optional)
     * @param herdIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param herdIdSpecified  (optional)
     * @param hornStatusEquals  (optional)
     * @param hornStatusIn  (optional, default to new ArrayList&lt;&gt;())
     * @param hornStatusNotEquals  (optional)
     * @param hornStatusNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param hornStatusSpecified  (optional)
     * @param idEquals  (optional)
     * @param idGreaterThan  (optional)
     * @param idGreaterThanOrEqual  (optional)
     * @param idIn  (optional, default to new ArrayList&lt;&gt;())
     * @param idLessThan  (optional)
     * @param idLessThanOrEqual  (optional)
     * @param idNotEquals  (optional)
     * @param idNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param idSpecified  (optional)
     * @param journalEntriesIdEquals  (optional)
     * @param journalEntriesIdGreaterThan  (optional)
     * @param journalEntriesIdGreaterThanOrEqual  (optional)
     * @param journalEntriesIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param journalEntriesIdLessThan  (optional)
     * @param journalEntriesIdLessThanOrEqual  (optional)
     * @param journalEntriesIdNotEquals  (optional)
     * @param journalEntriesIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param journalEntriesIdSpecified  (optional)
     * @param masterIdentifierContains  (optional)
     * @param masterIdentifierDoesNotContain  (optional)
     * @param masterIdentifierEquals  (optional)
     * @param masterIdentifierIn  (optional, default to new ArrayList&lt;&gt;())
     * @param masterIdentifierNotEquals  (optional)
     * @param masterIdentifierNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param masterIdentifierSpecified  (optional)
     * @param matriIdEquals  (optional)
     * @param matriIdGreaterThan  (optional)
     * @param matriIdGreaterThanOrEqual  (optional)
     * @param matriIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param matriIdLessThan  (optional)
     * @param matriIdLessThanOrEqual  (optional)
     * @param matriIdNotEquals  (optional)
     * @param matriIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param matriIdSpecified  (optional)
     * @param nameContains  (optional)
     * @param nameDoesNotContain  (optional)
     * @param nameEquals  (optional)
     * @param nameIn  (optional, default to new ArrayList&lt;&gt;())
     * @param nameNotEquals  (optional)
     * @param nameNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param nameSpecified  (optional)
     * @param page Page number of the requested page (optional)
     * @param patriIdEquals  (optional)
     * @param patriIdGreaterThan  (optional)
     * @param patriIdGreaterThanOrEqual  (optional)
     * @param patriIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param patriIdLessThan  (optional)
     * @param patriIdLessThanOrEqual  (optional)
     * @param patriIdNotEquals  (optional)
     * @param patriIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param patriIdSpecified  (optional)
     * @param size Size of a page (optional)
     * @param sort Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional, default to new ArrayList&lt;&gt;())
     * @param sourceFileIdEquals  (optional)
     * @param sourceFileIdGreaterThan  (optional)
     * @param sourceFileIdGreaterThanOrEqual  (optional)
     * @param sourceFileIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param sourceFileIdLessThan  (optional)
     * @param sourceFileIdLessThanOrEqual  (optional)
     * @param sourceFileIdNotEquals  (optional)
     * @param sourceFileIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param sourceFileIdSpecified  (optional)
     * @param weight0Equals  (optional)
     * @param weight0GreaterThan  (optional)
     * @param weight0GreaterThanOrEqual  (optional)
     * @param weight0In  (optional, default to new ArrayList&lt;&gt;())
     * @param weight0LessThan  (optional)
     * @param weight0LessThanOrEqual  (optional)
     * @param weight0NotEquals  (optional)
     * @param weight0NotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param weight0Specified  (optional)
     * @param weight200Equals  (optional)
     * @param weight200GreaterThan  (optional)
     * @param weight200GreaterThanOrEqual  (optional)
     * @param weight200In  (optional, default to new ArrayList&lt;&gt;())
     * @param weight200LessThan  (optional)
     * @param weight200LessThanOrEqual  (optional)
     * @param weight200NotEquals  (optional)
     * @param weight200NotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param weight200Specified  (optional)
     * @param weight365Equals  (optional)
     * @param weight365GreaterThan  (optional)
     * @param weight365GreaterThanOrEqual  (optional)
     * @param weight365In  (optional, default to new ArrayList&lt;&gt;())
     * @param weight365LessThan  (optional)
     * @param weight365LessThanOrEqual  (optional)
     * @param weight365NotEquals  (optional)
     * @param weight365NotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param weight365Specified  (optional)
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "getAllBovines", nickname = "getAllBovinesUsingGET", notes = "", response = BovineEntity.class, responseContainer = "List", tags={ "bovine-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = BovineEntity.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/bovines",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<BovineEntity>> getAllBovinesUsingGET(@SpringQueryMap BovineCriteria criteria, @ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort);


    /**
     * GET /api/bovines/{id} : getBovine
     *
     * @param id id (required)
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "getBovine", nickname = "getBovineUsingGET", notes = "", response = BovineEntity.class, tags={ "bovine-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = BovineEntity.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/bovines/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<BovineEntity> getBovineUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);

}
