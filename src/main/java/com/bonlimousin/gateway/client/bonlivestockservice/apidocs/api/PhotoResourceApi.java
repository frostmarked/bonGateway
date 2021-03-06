/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.bonlimousin.gateway.client.bonlivestockservice.apidocs.api;

import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.model.PhotoEntity;
import com.bonlimousin.gateway.client.bonlivestockservice.apidocs.querymap.PhotoCriteria;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-03T23:56:19.029760+02:00[Europe/Stockholm]")

@Validated
@Api(value = "PhotoResource", description = "the PhotoResource API")
public interface PhotoResourceApi {

    /**
     * GET /api/photos/count : countPhotos
     *
     * @param captionContains  (optional)
     * @param captionDoesNotContain  (optional)
     * @param captionEquals  (optional)
     * @param captionIn  (optional, default to new ArrayList&lt;&gt;())
     * @param captionNotEquals  (optional)
     * @param captionNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param captionSpecified  (optional)
     * @param cattleIdEquals  (optional)
     * @param cattleIdGreaterThan  (optional)
     * @param cattleIdGreaterThanOrEqual  (optional)
     * @param cattleIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param cattleIdLessThan  (optional)
     * @param cattleIdLessThanOrEqual  (optional)
     * @param cattleIdNotEquals  (optional)
     * @param cattleIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param cattleIdSpecified  (optional)
     * @param heightEquals  (optional)
     * @param heightGreaterThan  (optional)
     * @param heightGreaterThanOrEqual  (optional)
     * @param heightIn  (optional, default to new ArrayList&lt;&gt;())
     * @param heightLessThan  (optional)
     * @param heightLessThanOrEqual  (optional)
     * @param heightNotEquals  (optional)
     * @param heightNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param heightSpecified  (optional)
     * @param idEquals  (optional)
     * @param idGreaterThan  (optional)
     * @param idGreaterThanOrEqual  (optional)
     * @param idIn  (optional, default to new ArrayList&lt;&gt;())
     * @param idLessThan  (optional)
     * @param idLessThanOrEqual  (optional)
     * @param idNotEquals  (optional)
     * @param idNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param idSpecified  (optional)
     * @param takenEquals  (optional)
     * @param takenGreaterThan  (optional)
     * @param takenGreaterThanOrEqual  (optional)
     * @param takenIn0EpochSecond  (optional)
     * @param takenIn0Nano  (optional)
     * @param takenLessThan  (optional)
     * @param takenLessThanOrEqual  (optional)
     * @param takenNotEquals  (optional)
     * @param takenNotIn0EpochSecond  (optional)
     * @param takenNotIn0Nano  (optional)
     * @param takenSpecified  (optional)
     * @param visibilityEquals  (optional)
     * @param visibilityIn  (optional, default to new ArrayList&lt;&gt;())
     * @param visibilityNotEquals  (optional)
     * @param visibilityNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param visibilitySpecified  (optional)
     * @param widthEquals  (optional)
     * @param widthGreaterThan  (optional)
     * @param widthGreaterThanOrEqual  (optional)
     * @param widthIn  (optional, default to new ArrayList&lt;&gt;())
     * @param widthLessThan  (optional)
     * @param widthLessThanOrEqual  (optional)
     * @param widthNotEquals  (optional)
     * @param widthNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param widthSpecified  (optional)
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "countPhotos", nickname = "countPhotosUsingGET", notes = "", response = Long.class, tags={ "photo-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Long.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/photos/count",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<Long> countPhotosUsingGET(@ApiParam(value = "") @Valid @RequestParam(value = "caption.contains", required = false) String captionContains,@ApiParam(value = "") @Valid @RequestParam(value = "caption.doesNotContain", required = false) String captionDoesNotContain,@ApiParam(value = "") @Valid @RequestParam(value = "caption.equals", required = false) String captionEquals,@ApiParam(value = "") @Valid @RequestParam(value = "caption.in", required = false) List<String> captionIn,@ApiParam(value = "") @Valid @RequestParam(value = "caption.notEquals", required = false) String captionNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "caption.notIn", required = false) List<String> captionNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "caption.specified", required = false) Boolean captionSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "cattleId.equals", required = false) Long cattleIdEquals,@ApiParam(value = "") @Valid @RequestParam(value = "cattleId.greaterThan", required = false) Long cattleIdGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "cattleId.greaterThanOrEqual", required = false) Long cattleIdGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "cattleId.in", required = false) List<Long> cattleIdIn,@ApiParam(value = "") @Valid @RequestParam(value = "cattleId.lessThan", required = false) Long cattleIdLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "cattleId.lessThanOrEqual", required = false) Long cattleIdLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "cattleId.notEquals", required = false) Long cattleIdNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "cattleId.notIn", required = false) List<Long> cattleIdNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "cattleId.specified", required = false) Boolean cattleIdSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "height.equals", required = false) Integer heightEquals,@ApiParam(value = "") @Valid @RequestParam(value = "height.greaterThan", required = false) Integer heightGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "height.greaterThanOrEqual", required = false) Integer heightGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "height.in", required = false) List<Integer> heightIn,@ApiParam(value = "") @Valid @RequestParam(value = "height.lessThan", required = false) Integer heightLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "height.lessThanOrEqual", required = false) Integer heightLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "height.notEquals", required = false) Integer heightNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "height.notIn", required = false) List<Integer> heightNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "height.specified", required = false) Boolean heightSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "id.equals", required = false) Long idEquals,@ApiParam(value = "") @Valid @RequestParam(value = "id.greaterThan", required = false) Long idGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "id.greaterThanOrEqual", required = false) Long idGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "id.in", required = false) List<Long> idIn,@ApiParam(value = "") @Valid @RequestParam(value = "id.lessThan", required = false) Long idLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "id.lessThanOrEqual", required = false) Long idLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "id.notEquals", required = false) Long idNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "id.notIn", required = false) List<Long> idNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "id.specified", required = false) Boolean idSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "taken.equals", required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) OffsetDateTime takenEquals,@ApiParam(value = "") @Valid @RequestParam(value = "taken.greaterThan", required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) OffsetDateTime takenGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "taken.greaterThanOrEqual", required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) OffsetDateTime takenGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "taken.in[0].epochSecond", required = false) Long takenIn0EpochSecond,@ApiParam(value = "") @Valid @RequestParam(value = "taken.in[0].nano", required = false) Integer takenIn0Nano,@ApiParam(value = "") @Valid @RequestParam(value = "taken.lessThan", required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) OffsetDateTime takenLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "taken.lessThanOrEqual", required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) OffsetDateTime takenLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "taken.notEquals", required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) OffsetDateTime takenNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "taken.notIn[0].epochSecond", required = false) Long takenNotIn0EpochSecond,@ApiParam(value = "") @Valid @RequestParam(value = "taken.notIn[0].nano", required = false) Integer takenNotIn0Nano,@ApiParam(value = "") @Valid @RequestParam(value = "taken.specified", required = false) Boolean takenSpecified,@ApiParam(value = "", allowableValues = "ROLE_ADMIN, ROLE_USER, ROLE_ANONYMOUS") @Valid @RequestParam(value = "visibility.equals", required = false) String visibilityEquals,@ApiParam(value = "", allowableValues = "ROLE_ADMIN, ROLE_USER, ROLE_ANONYMOUS") @Valid @RequestParam(value = "visibility.in", required = false) List<String> visibilityIn,@ApiParam(value = "", allowableValues = "ROLE_ADMIN, ROLE_USER, ROLE_ANONYMOUS") @Valid @RequestParam(value = "visibility.notEquals", required = false) String visibilityNotEquals,@ApiParam(value = "", allowableValues = "ROLE_ADMIN, ROLE_USER, ROLE_ANONYMOUS") @Valid @RequestParam(value = "visibility.notIn", required = false) List<String> visibilityNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "visibility.specified", required = false) Boolean visibilitySpecified,@ApiParam(value = "") @Valid @RequestParam(value = "width.equals", required = false) Integer widthEquals,@ApiParam(value = "") @Valid @RequestParam(value = "width.greaterThan", required = false) Integer widthGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "width.greaterThanOrEqual", required = false) Integer widthGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "width.in", required = false) List<Integer> widthIn,@ApiParam(value = "") @Valid @RequestParam(value = "width.lessThan", required = false) Integer widthLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "width.lessThanOrEqual", required = false) Integer widthLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "width.notEquals", required = false) Integer widthNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "width.notIn", required = false) List<Integer> widthNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "width.specified", required = false) Boolean widthSpecified);


    /**
     * POST /api/photos : createPhoto
     *
     * @param photoEntity photoEntity (required)
     * @return OK (status code 200)
     *         or Created (status code 201)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "createPhoto", nickname = "createPhotoUsingPOST", notes = "", response = PhotoEntity.class, tags={ "photo-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = PhotoEntity.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/photos",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<PhotoEntity> createPhotoUsingPOST(@ApiParam(value = "photoEntity" ,required=true )  @Valid @RequestBody PhotoEntity photoEntity);


    /**
     * DELETE /api/photos/{id} : deletePhoto
     *
     * @param id id (required)
     * @return OK (status code 200)
     *         or No Content (status code 204)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     */
    @ApiOperation(value = "deletePhoto", nickname = "deletePhotoUsingDELETE", notes = "", tags={ "photo-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(value = "/api/photos/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deletePhotoUsingDELETE(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    /**
     * GET /api/photos : getAllPhotos
     *
     * @param captionContains  (optional)
     * @param captionDoesNotContain  (optional)
     * @param captionEquals  (optional)
     * @param captionIn  (optional, default to new ArrayList&lt;&gt;())
     * @param captionNotEquals  (optional)
     * @param captionNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param captionSpecified  (optional)
     * @param cattleIdEquals  (optional)
     * @param cattleIdGreaterThan  (optional)
     * @param cattleIdGreaterThanOrEqual  (optional)
     * @param cattleIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param cattleIdLessThan  (optional)
     * @param cattleIdLessThanOrEqual  (optional)
     * @param cattleIdNotEquals  (optional)
     * @param cattleIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param cattleIdSpecified  (optional)
     * @param heightEquals  (optional)
     * @param heightGreaterThan  (optional)
     * @param heightGreaterThanOrEqual  (optional)
     * @param heightIn  (optional, default to new ArrayList&lt;&gt;())
     * @param heightLessThan  (optional)
     * @param heightLessThanOrEqual  (optional)
     * @param heightNotEquals  (optional)
     * @param heightNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param heightSpecified  (optional)
     * @param idEquals  (optional)
     * @param idGreaterThan  (optional)
     * @param idGreaterThanOrEqual  (optional)
     * @param idIn  (optional, default to new ArrayList&lt;&gt;())
     * @param idLessThan  (optional)
     * @param idLessThanOrEqual  (optional)
     * @param idNotEquals  (optional)
     * @param idNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param idSpecified  (optional)
     * @param page Page number of the requested page (optional)
     * @param size Size of a page (optional)
     * @param sort Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional, default to new ArrayList&lt;&gt;())
     * @param takenEquals  (optional)
     * @param takenGreaterThan  (optional)
     * @param takenGreaterThanOrEqual  (optional)
     * @param takenIn0EpochSecond  (optional)
     * @param takenIn0Nano  (optional)
     * @param takenLessThan  (optional)
     * @param takenLessThanOrEqual  (optional)
     * @param takenNotEquals  (optional)
     * @param takenNotIn0EpochSecond  (optional)
     * @param takenNotIn0Nano  (optional)
     * @param takenSpecified  (optional)
     * @param visibilityEquals  (optional)
     * @param visibilityIn  (optional, default to new ArrayList&lt;&gt;())
     * @param visibilityNotEquals  (optional)
     * @param visibilityNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param visibilitySpecified  (optional)
     * @param widthEquals  (optional)
     * @param widthGreaterThan  (optional)
     * @param widthGreaterThanOrEqual  (optional)
     * @param widthIn  (optional, default to new ArrayList&lt;&gt;())
     * @param widthLessThan  (optional)
     * @param widthLessThanOrEqual  (optional)
     * @param widthNotEquals  (optional)
     * @param widthNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param widthSpecified  (optional)
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "getAllPhotos", nickname = "getAllPhotosUsingGET", notes = "", response = PhotoEntity.class, responseContainer = "List", tags={ "photo-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = PhotoEntity.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/photos",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<PhotoEntity>> getAllPhotosUsingGET(@SpringQueryMap PhotoCriteria criteria, @ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort);


    /**
     * GET /api/photos/{id} : getPhoto
     *
     * @param id id (required)
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "getPhoto", nickname = "getPhotoUsingGET", notes = "", response = PhotoEntity.class, tags={ "photo-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = PhotoEntity.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/photos/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<PhotoEntity> getPhotoUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    /**
     * PUT /api/photos : updatePhoto
     *
     * @param photoEntity photoEntity (required)
     * @return OK (status code 200)
     *         or Created (status code 201)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "updatePhoto", nickname = "updatePhotoUsingPUT", notes = "", response = PhotoEntity.class, tags={ "photo-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = PhotoEntity.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/photos",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.PUT)
    ResponseEntity<PhotoEntity> updatePhotoUsingPUT(@ApiParam(value = "photoEntity" ,required=true )  @Valid @RequestBody PhotoEntity photoEntity);

}
