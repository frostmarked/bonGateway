/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.bonlimousin.gateway.client.boncontentservice.apidocs.api;

import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.bonlimousin.gateway.client.boncontentservice.apidocs.model.TagEntity;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-04T00:04:26.742175+02:00[Europe/Stockholm]")

@Validated
@Api(value = "TagResource", description = "the TagResource API")
public interface TagResourceApi {

    /**
     * GET /api/tags/count : countTags
     *
     * @param fragmentIdEquals  (optional)
     * @param fragmentIdGreaterThan  (optional)
     * @param fragmentIdGreaterThanOrEqual  (optional)
     * @param fragmentIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param fragmentIdLessThan  (optional)
     * @param fragmentIdLessThanOrEqual  (optional)
     * @param fragmentIdNotEquals  (optional)
     * @param fragmentIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param fragmentIdSpecified  (optional)
     * @param idEquals  (optional)
     * @param idGreaterThan  (optional)
     * @param idGreaterThanOrEqual  (optional)
     * @param idIn  (optional, default to new ArrayList&lt;&gt;())
     * @param idLessThan  (optional)
     * @param idLessThanOrEqual  (optional)
     * @param idNotEquals  (optional)
     * @param idNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param idSpecified  (optional)
     * @param nameContains  (optional)
     * @param nameDoesNotContain  (optional)
     * @param nameEquals  (optional)
     * @param nameIn  (optional, default to new ArrayList&lt;&gt;())
     * @param nameNotEquals  (optional)
     * @param nameNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param nameSpecified  (optional)
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "countTags", nickname = "countTagsUsingGET", notes = "", response = Long.class, tags={ "tag-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Long.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/tags/count",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<Long> countTagsUsingGET(@ApiParam(value = "") @Valid @RequestParam(value = "fragmentId.equals", required = false) Long fragmentIdEquals,@ApiParam(value = "") @Valid @RequestParam(value = "fragmentId.greaterThan", required = false) Long fragmentIdGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "fragmentId.greaterThanOrEqual", required = false) Long fragmentIdGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "fragmentId.in", required = false) List<Long> fragmentIdIn,@ApiParam(value = "") @Valid @RequestParam(value = "fragmentId.lessThan", required = false) Long fragmentIdLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "fragmentId.lessThanOrEqual", required = false) Long fragmentIdLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "fragmentId.notEquals", required = false) Long fragmentIdNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "fragmentId.notIn", required = false) List<Long> fragmentIdNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "fragmentId.specified", required = false) Boolean fragmentIdSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "id.equals", required = false) Long idEquals,@ApiParam(value = "") @Valid @RequestParam(value = "id.greaterThan", required = false) Long idGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "id.greaterThanOrEqual", required = false) Long idGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "id.in", required = false) List<Long> idIn,@ApiParam(value = "") @Valid @RequestParam(value = "id.lessThan", required = false) Long idLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "id.lessThanOrEqual", required = false) Long idLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "id.notEquals", required = false) Long idNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "id.notIn", required = false) List<Long> idNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "id.specified", required = false) Boolean idSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "name.contains", required = false) String nameContains,@ApiParam(value = "") @Valid @RequestParam(value = "name.doesNotContain", required = false) String nameDoesNotContain,@ApiParam(value = "") @Valid @RequestParam(value = "name.equals", required = false) String nameEquals,@ApiParam(value = "") @Valid @RequestParam(value = "name.in", required = false) List<String> nameIn,@ApiParam(value = "") @Valid @RequestParam(value = "name.notEquals", required = false) String nameNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "name.notIn", required = false) List<String> nameNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "name.specified", required = false) Boolean nameSpecified);


    /**
     * POST /api/tags : createTag
     *
     * @param tagEntity tagEntity (required)
     * @return OK (status code 200)
     *         or Created (status code 201)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "createTag", nickname = "createTagUsingPOST", notes = "", response = TagEntity.class, tags={ "tag-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = TagEntity.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/tags",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<TagEntity> createTagUsingPOST(@ApiParam(value = "tagEntity" ,required=true )  @Valid @RequestBody TagEntity tagEntity);


    /**
     * DELETE /api/tags/{id} : deleteTag
     *
     * @param id id (required)
     * @return OK (status code 200)
     *         or No Content (status code 204)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     */
    @ApiOperation(value = "deleteTag", nickname = "deleteTagUsingDELETE", notes = "", tags={ "tag-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(value = "/api/tags/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteTagUsingDELETE(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    /**
     * GET /api/tags : getAllTags
     *
     * @param fragmentIdEquals  (optional)
     * @param fragmentIdGreaterThan  (optional)
     * @param fragmentIdGreaterThanOrEqual  (optional)
     * @param fragmentIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param fragmentIdLessThan  (optional)
     * @param fragmentIdLessThanOrEqual  (optional)
     * @param fragmentIdNotEquals  (optional)
     * @param fragmentIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param fragmentIdSpecified  (optional)
     * @param idEquals  (optional)
     * @param idGreaterThan  (optional)
     * @param idGreaterThanOrEqual  (optional)
     * @param idIn  (optional, default to new ArrayList&lt;&gt;())
     * @param idLessThan  (optional)
     * @param idLessThanOrEqual  (optional)
     * @param idNotEquals  (optional)
     * @param idNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param idSpecified  (optional)
     * @param nameContains  (optional)
     * @param nameDoesNotContain  (optional)
     * @param nameEquals  (optional)
     * @param nameIn  (optional, default to new ArrayList&lt;&gt;())
     * @param nameNotEquals  (optional)
     * @param nameNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param nameSpecified  (optional)
     * @param page Page number of the requested page (optional)
     * @param size Size of a page (optional)
     * @param sort Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional, default to new ArrayList&lt;&gt;())
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "getAllTags", nickname = "getAllTagsUsingGET", notes = "", response = TagEntity.class, responseContainer = "List", tags={ "tag-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = TagEntity.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/tags",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<TagEntity>> getAllTagsUsingGET(@ApiParam(value = "") @Valid @RequestParam(value = "fragmentId.equals", required = false) Long fragmentIdEquals,@ApiParam(value = "") @Valid @RequestParam(value = "fragmentId.greaterThan", required = false) Long fragmentIdGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "fragmentId.greaterThanOrEqual", required = false) Long fragmentIdGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "fragmentId.in", required = false) List<Long> fragmentIdIn,@ApiParam(value = "") @Valid @RequestParam(value = "fragmentId.lessThan", required = false) Long fragmentIdLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "fragmentId.lessThanOrEqual", required = false) Long fragmentIdLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "fragmentId.notEquals", required = false) Long fragmentIdNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "fragmentId.notIn", required = false) List<Long> fragmentIdNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "fragmentId.specified", required = false) Boolean fragmentIdSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "id.equals", required = false) Long idEquals,@ApiParam(value = "") @Valid @RequestParam(value = "id.greaterThan", required = false) Long idGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "id.greaterThanOrEqual", required = false) Long idGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "id.in", required = false) List<Long> idIn,@ApiParam(value = "") @Valid @RequestParam(value = "id.lessThan", required = false) Long idLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "id.lessThanOrEqual", required = false) Long idLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "id.notEquals", required = false) Long idNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "id.notIn", required = false) List<Long> idNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "id.specified", required = false) Boolean idSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "name.contains", required = false) String nameContains,@ApiParam(value = "") @Valid @RequestParam(value = "name.doesNotContain", required = false) String nameDoesNotContain,@ApiParam(value = "") @Valid @RequestParam(value = "name.equals", required = false) String nameEquals,@ApiParam(value = "") @Valid @RequestParam(value = "name.in", required = false) List<String> nameIn,@ApiParam(value = "") @Valid @RequestParam(value = "name.notEquals", required = false) String nameNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "name.notIn", required = false) List<String> nameNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "name.specified", required = false) Boolean nameSpecified,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort);


    /**
     * GET /api/tags/{id} : getTag
     *
     * @param id id (required)
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "getTag", nickname = "getTagUsingGET", notes = "", response = TagEntity.class, tags={ "tag-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = TagEntity.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/tags/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<TagEntity> getTagUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    /**
     * GET /api/_search/tags : searchTags
     *
     * @param query query (required)
     * @param page Page number of the requested page (optional)
     * @param size Size of a page (optional)
     * @param sort Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional, default to new ArrayList&lt;&gt;())
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "searchTags", nickname = "searchTagsUsingGET", notes = "", response = TagEntity.class, responseContainer = "List", tags={ "tag-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = TagEntity.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/_search/tags",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<TagEntity>> searchTagsUsingGET(@NotNull @ApiParam(value = "query", required = true) @Valid @RequestParam(value = "query", required = true) String query,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort);


    /**
     * PUT /api/tags : updateTag
     *
     * @param tagEntity tagEntity (required)
     * @return OK (status code 200)
     *         or Created (status code 201)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "updateTag", nickname = "updateTagUsingPUT", notes = "", response = TagEntity.class, tags={ "tag-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = TagEntity.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/tags",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.PUT)
    ResponseEntity<TagEntity> updateTagUsingPUT(@ApiParam(value = "tagEntity" ,required=true )  @Valid @RequestBody TagEntity tagEntity);

}
