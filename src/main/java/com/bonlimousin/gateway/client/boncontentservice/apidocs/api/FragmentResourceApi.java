/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.bonlimousin.gateway.client.boncontentservice.apidocs.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bonlimousin.gateway.client.boncontentservice.apidocs.model.FragmentEntity;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.querymap.FragmentCriteria;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-04T00:04:26.742175+02:00[Europe/Stockholm]")

@Validated
@Api(value = "FragmentResource", description = "the FragmentResource API")
public interface FragmentResourceApi {

    /**
     * GET /api/fragments/count : countFragments
     *
     * @param captionContains  (optional)
     * @param captionDoesNotContain  (optional)
     * @param captionEquals  (optional)
     * @param captionIn  (optional, default to new ArrayList&lt;&gt;())
     * @param captionNotEquals  (optional)
     * @param captionNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param captionSpecified  (optional)
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
     * @param ingressContains  (optional)
     * @param ingressDoesNotContain  (optional)
     * @param ingressEquals  (optional)
     * @param ingressIn  (optional, default to new ArrayList&lt;&gt;())
     * @param ingressNotEquals  (optional)
     * @param ingressNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param ingressSpecified  (optional)
     * @param localizedFragmentIdEquals  (optional)
     * @param localizedFragmentIdGreaterThan  (optional)
     * @param localizedFragmentIdGreaterThanOrEqual  (optional)
     * @param localizedFragmentIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param localizedFragmentIdLessThan  (optional)
     * @param localizedFragmentIdLessThanOrEqual  (optional)
     * @param localizedFragmentIdNotEquals  (optional)
     * @param localizedFragmentIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param localizedFragmentIdSpecified  (optional)
     * @param nameContains  (optional)
     * @param nameDoesNotContain  (optional)
     * @param nameEquals  (optional)
     * @param nameIn  (optional, default to new ArrayList&lt;&gt;())
     * @param nameNotEquals  (optional)
     * @param nameNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param nameSpecified  (optional)
     * @param orderNoEquals  (optional)
     * @param orderNoGreaterThan  (optional)
     * @param orderNoGreaterThanOrEqual  (optional)
     * @param orderNoIn  (optional, default to new ArrayList&lt;&gt;())
     * @param orderNoLessThan  (optional)
     * @param orderNoLessThanOrEqual  (optional)
     * @param orderNoNotEquals  (optional)
     * @param orderNoNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param orderNoSpecified  (optional)
     * @param storyIdEquals  (optional)
     * @param storyIdGreaterThan  (optional)
     * @param storyIdGreaterThanOrEqual  (optional)
     * @param storyIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param storyIdLessThan  (optional)
     * @param storyIdLessThanOrEqual  (optional)
     * @param storyIdNotEquals  (optional)
     * @param storyIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param storyIdSpecified  (optional)
     * @param tagIdEquals  (optional)
     * @param tagIdGreaterThan  (optional)
     * @param tagIdGreaterThanOrEqual  (optional)
     * @param tagIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param tagIdLessThan  (optional)
     * @param tagIdLessThanOrEqual  (optional)
     * @param tagIdNotEquals  (optional)
     * @param tagIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param tagIdSpecified  (optional)
     * @param templateEquals  (optional)
     * @param templateIn  (optional, default to new ArrayList&lt;&gt;())
     * @param templateNotEquals  (optional)
     * @param templateNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param templateSpecified  (optional)
     * @param titleContains  (optional)
     * @param titleDoesNotContain  (optional)
     * @param titleEquals  (optional)
     * @param titleIn  (optional, default to new ArrayList&lt;&gt;())
     * @param titleNotEquals  (optional)
     * @param titleNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param titleSpecified  (optional)
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
    @ApiOperation(value = "countFragments", nickname = "countFragmentsUsingGET", notes = "", response = Long.class, tags={ "fragment-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Long.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/fragments/count",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<Long> countFragmentsUsingGET(@ApiParam(value = "") @Valid @RequestParam(value = "caption.contains", required = false) String captionContains,@ApiParam(value = "") @Valid @RequestParam(value = "caption.doesNotContain", required = false) String captionDoesNotContain,@ApiParam(value = "") @Valid @RequestParam(value = "caption.equals", required = false) String captionEquals,@ApiParam(value = "") @Valid @RequestParam(value = "caption.in", required = false) List<String> captionIn,@ApiParam(value = "") @Valid @RequestParam(value = "caption.notEquals", required = false) String captionNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "caption.notIn", required = false) List<String> captionNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "caption.specified", required = false) Boolean captionSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "height.equals", required = false) Integer heightEquals,@ApiParam(value = "") @Valid @RequestParam(value = "height.greaterThan", required = false) Integer heightGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "height.greaterThanOrEqual", required = false) Integer heightGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "height.in", required = false) List<Integer> heightIn,@ApiParam(value = "") @Valid @RequestParam(value = "height.lessThan", required = false) Integer heightLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "height.lessThanOrEqual", required = false) Integer heightLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "height.notEquals", required = false) Integer heightNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "height.notIn", required = false) List<Integer> heightNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "height.specified", required = false) Boolean heightSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "id.equals", required = false) Long idEquals,@ApiParam(value = "") @Valid @RequestParam(value = "id.greaterThan", required = false) Long idGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "id.greaterThanOrEqual", required = false) Long idGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "id.in", required = false) List<Long> idIn,@ApiParam(value = "") @Valid @RequestParam(value = "id.lessThan", required = false) Long idLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "id.lessThanOrEqual", required = false) Long idLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "id.notEquals", required = false) Long idNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "id.notIn", required = false) List<Long> idNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "id.specified", required = false) Boolean idSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "ingress.contains", required = false) String ingressContains,@ApiParam(value = "") @Valid @RequestParam(value = "ingress.doesNotContain", required = false) String ingressDoesNotContain,@ApiParam(value = "") @Valid @RequestParam(value = "ingress.equals", required = false) String ingressEquals,@ApiParam(value = "") @Valid @RequestParam(value = "ingress.in", required = false) List<String> ingressIn,@ApiParam(value = "") @Valid @RequestParam(value = "ingress.notEquals", required = false) String ingressNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "ingress.notIn", required = false) List<String> ingressNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "ingress.specified", required = false) Boolean ingressSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "localizedFragmentId.equals", required = false) Long localizedFragmentIdEquals,@ApiParam(value = "") @Valid @RequestParam(value = "localizedFragmentId.greaterThan", required = false) Long localizedFragmentIdGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "localizedFragmentId.greaterThanOrEqual", required = false) Long localizedFragmentIdGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "localizedFragmentId.in", required = false) List<Long> localizedFragmentIdIn,@ApiParam(value = "") @Valid @RequestParam(value = "localizedFragmentId.lessThan", required = false) Long localizedFragmentIdLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "localizedFragmentId.lessThanOrEqual", required = false) Long localizedFragmentIdLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "localizedFragmentId.notEquals", required = false) Long localizedFragmentIdNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "localizedFragmentId.notIn", required = false) List<Long> localizedFragmentIdNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "localizedFragmentId.specified", required = false) Boolean localizedFragmentIdSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "name.contains", required = false) String nameContains,@ApiParam(value = "") @Valid @RequestParam(value = "name.doesNotContain", required = false) String nameDoesNotContain,@ApiParam(value = "") @Valid @RequestParam(value = "name.equals", required = false) String nameEquals,@ApiParam(value = "") @Valid @RequestParam(value = "name.in", required = false) List<String> nameIn,@ApiParam(value = "") @Valid @RequestParam(value = "name.notEquals", required = false) String nameNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "name.notIn", required = false) List<String> nameNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "name.specified", required = false) Boolean nameSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "orderNo.equals", required = false) Integer orderNoEquals,@ApiParam(value = "") @Valid @RequestParam(value = "orderNo.greaterThan", required = false) Integer orderNoGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "orderNo.greaterThanOrEqual", required = false) Integer orderNoGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "orderNo.in", required = false) List<Integer> orderNoIn,@ApiParam(value = "") @Valid @RequestParam(value = "orderNo.lessThan", required = false) Integer orderNoLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "orderNo.lessThanOrEqual", required = false) Integer orderNoLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "orderNo.notEquals", required = false) Integer orderNoNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "orderNo.notIn", required = false) List<Integer> orderNoNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "orderNo.specified", required = false) Boolean orderNoSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "storyId.equals", required = false) Long storyIdEquals,@ApiParam(value = "") @Valid @RequestParam(value = "storyId.greaterThan", required = false) Long storyIdGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "storyId.greaterThanOrEqual", required = false) Long storyIdGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "storyId.in", required = false) List<Long> storyIdIn,@ApiParam(value = "") @Valid @RequestParam(value = "storyId.lessThan", required = false) Long storyIdLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "storyId.lessThanOrEqual", required = false) Long storyIdLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "storyId.notEquals", required = false) Long storyIdNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "storyId.notIn", required = false) List<Long> storyIdNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "storyId.specified", required = false) Boolean storyIdSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "tagId.equals", required = false) Long tagIdEquals,@ApiParam(value = "") @Valid @RequestParam(value = "tagId.greaterThan", required = false) Long tagIdGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "tagId.greaterThanOrEqual", required = false) Long tagIdGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "tagId.in", required = false) List<Long> tagIdIn,@ApiParam(value = "") @Valid @RequestParam(value = "tagId.lessThan", required = false) Long tagIdLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "tagId.lessThanOrEqual", required = false) Long tagIdLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "tagId.notEquals", required = false) Long tagIdNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "tagId.notIn", required = false) List<Long> tagIdNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "tagId.specified", required = false) Boolean tagIdSpecified,@ApiParam(value = "", allowableValues = "V1, V2, V3") @Valid @RequestParam(value = "template.equals", required = false) String templateEquals,@ApiParam(value = "", allowableValues = "V1, V2, V3") @Valid @RequestParam(value = "template.in", required = false) List<String> templateIn,@ApiParam(value = "", allowableValues = "V1, V2, V3") @Valid @RequestParam(value = "template.notEquals", required = false) String templateNotEquals,@ApiParam(value = "", allowableValues = "V1, V2, V3") @Valid @RequestParam(value = "template.notIn", required = false) List<String> templateNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "template.specified", required = false) Boolean templateSpecified,@ApiParam(value = "") @Valid @RequestParam(value = "title.contains", required = false) String titleContains,@ApiParam(value = "") @Valid @RequestParam(value = "title.doesNotContain", required = false) String titleDoesNotContain,@ApiParam(value = "") @Valid @RequestParam(value = "title.equals", required = false) String titleEquals,@ApiParam(value = "") @Valid @RequestParam(value = "title.in", required = false) List<String> titleIn,@ApiParam(value = "") @Valid @RequestParam(value = "title.notEquals", required = false) String titleNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "title.notIn", required = false) List<String> titleNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "title.specified", required = false) Boolean titleSpecified,@ApiParam(value = "", allowableValues = "ROLE_ADMIN, ROLE_USER, ROLE_ANONYMOUS") @Valid @RequestParam(value = "visibility.equals", required = false) String visibilityEquals,@ApiParam(value = "", allowableValues = "ROLE_ADMIN, ROLE_USER, ROLE_ANONYMOUS") @Valid @RequestParam(value = "visibility.in", required = false) List<String> visibilityIn,@ApiParam(value = "", allowableValues = "ROLE_ADMIN, ROLE_USER, ROLE_ANONYMOUS") @Valid @RequestParam(value = "visibility.notEquals", required = false) String visibilityNotEquals,@ApiParam(value = "", allowableValues = "ROLE_ADMIN, ROLE_USER, ROLE_ANONYMOUS") @Valid @RequestParam(value = "visibility.notIn", required = false) List<String> visibilityNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "visibility.specified", required = false) Boolean visibilitySpecified,@ApiParam(value = "") @Valid @RequestParam(value = "width.equals", required = false) Integer widthEquals,@ApiParam(value = "") @Valid @RequestParam(value = "width.greaterThan", required = false) Integer widthGreaterThan,@ApiParam(value = "") @Valid @RequestParam(value = "width.greaterThanOrEqual", required = false) Integer widthGreaterThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "width.in", required = false) List<Integer> widthIn,@ApiParam(value = "") @Valid @RequestParam(value = "width.lessThan", required = false) Integer widthLessThan,@ApiParam(value = "") @Valid @RequestParam(value = "width.lessThanOrEqual", required = false) Integer widthLessThanOrEqual,@ApiParam(value = "") @Valid @RequestParam(value = "width.notEquals", required = false) Integer widthNotEquals,@ApiParam(value = "") @Valid @RequestParam(value = "width.notIn", required = false) List<Integer> widthNotIn,@ApiParam(value = "") @Valid @RequestParam(value = "width.specified", required = false) Boolean widthSpecified);


    /**
     * POST /api/fragments : createFragment
     *
     * @param fragmentEntity fragmentEntity (required)
     * @return OK (status code 200)
     *         or Created (status code 201)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "createFragment", nickname = "createFragmentUsingPOST", notes = "", response = FragmentEntity.class, tags={ "fragment-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = FragmentEntity.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/fragments",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<FragmentEntity> createFragmentUsingPOST(@ApiParam(value = "fragmentEntity" ,required=true )  @Valid @RequestBody FragmentEntity fragmentEntity);


    /**
     * DELETE /api/fragments/{id} : deleteFragment
     *
     * @param id id (required)
     * @return OK (status code 200)
     *         or No Content (status code 204)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     */
    @ApiOperation(value = "deleteFragment", nickname = "deleteFragmentUsingDELETE", notes = "", tags={ "fragment-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(value = "/api/fragments/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteFragmentUsingDELETE(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    /**
     * GET /api/fragments : getAllFragments
     *
     * @param captionContains  (optional)
     * @param captionDoesNotContain  (optional)
     * @param captionEquals  (optional)
     * @param captionIn  (optional, default to new ArrayList&lt;&gt;())
     * @param captionNotEquals  (optional)
     * @param captionNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param captionSpecified  (optional)
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
     * @param ingressContains  (optional)
     * @param ingressDoesNotContain  (optional)
     * @param ingressEquals  (optional)
     * @param ingressIn  (optional, default to new ArrayList&lt;&gt;())
     * @param ingressNotEquals  (optional)
     * @param ingressNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param ingressSpecified  (optional)
     * @param localizedFragmentIdEquals  (optional)
     * @param localizedFragmentIdGreaterThan  (optional)
     * @param localizedFragmentIdGreaterThanOrEqual  (optional)
     * @param localizedFragmentIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param localizedFragmentIdLessThan  (optional)
     * @param localizedFragmentIdLessThanOrEqual  (optional)
     * @param localizedFragmentIdNotEquals  (optional)
     * @param localizedFragmentIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param localizedFragmentIdSpecified  (optional)
     * @param nameContains  (optional)
     * @param nameDoesNotContain  (optional)
     * @param nameEquals  (optional)
     * @param nameIn  (optional, default to new ArrayList&lt;&gt;())
     * @param nameNotEquals  (optional)
     * @param nameNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param nameSpecified  (optional)
     * @param orderNoEquals  (optional)
     * @param orderNoGreaterThan  (optional)
     * @param orderNoGreaterThanOrEqual  (optional)
     * @param orderNoIn  (optional, default to new ArrayList&lt;&gt;())
     * @param orderNoLessThan  (optional)
     * @param orderNoLessThanOrEqual  (optional)
     * @param orderNoNotEquals  (optional)
     * @param orderNoNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param orderNoSpecified  (optional)
     * @param page Page number of the requested page (optional)
     * @param size Size of a page (optional)
     * @param sort Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional, default to new ArrayList&lt;&gt;())
     * @param storyIdEquals  (optional)
     * @param storyIdGreaterThan  (optional)
     * @param storyIdGreaterThanOrEqual  (optional)
     * @param storyIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param storyIdLessThan  (optional)
     * @param storyIdLessThanOrEqual  (optional)
     * @param storyIdNotEquals  (optional)
     * @param storyIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param storyIdSpecified  (optional)
     * @param tagIdEquals  (optional)
     * @param tagIdGreaterThan  (optional)
     * @param tagIdGreaterThanOrEqual  (optional)
     * @param tagIdIn  (optional, default to new ArrayList&lt;&gt;())
     * @param tagIdLessThan  (optional)
     * @param tagIdLessThanOrEqual  (optional)
     * @param tagIdNotEquals  (optional)
     * @param tagIdNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param tagIdSpecified  (optional)
     * @param templateEquals  (optional)
     * @param templateIn  (optional, default to new ArrayList&lt;&gt;())
     * @param templateNotEquals  (optional)
     * @param templateNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param templateSpecified  (optional)
     * @param titleContains  (optional)
     * @param titleDoesNotContain  (optional)
     * @param titleEquals  (optional)
     * @param titleIn  (optional, default to new ArrayList&lt;&gt;())
     * @param titleNotEquals  (optional)
     * @param titleNotIn  (optional, default to new ArrayList&lt;&gt;())
     * @param titleSpecified  (optional)
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
    @ApiOperation(value = "getAllFragments", nickname = "getAllFragmentsUsingGET", notes = "", response = FragmentEntity.class, responseContainer = "List", tags={ "fragment-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = FragmentEntity.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/fragments",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<FragmentEntity>> getAllFragmentsUsingGET(@SpringQueryMap FragmentCriteria criteria, @ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort);


    /**
     * GET /api/fragments/{id} : getFragment
     *
     * @param id id (required)
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "getFragment", nickname = "getFragmentUsingGET", notes = "", response = FragmentEntity.class, tags={ "fragment-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = FragmentEntity.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/fragments/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<FragmentEntity> getFragmentUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    /**
     * GET /api/_search/fragments : searchFragments
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
    @ApiOperation(value = "searchFragments", nickname = "searchFragmentsUsingGET", notes = "", response = FragmentEntity.class, responseContainer = "List", tags={ "fragment-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = FragmentEntity.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/_search/fragments",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<FragmentEntity>> searchFragmentsUsingGET(@NotNull @ApiParam(value = "query", required = true) @Valid @RequestParam(value = "query", required = true) String query,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort);


    /**
     * PUT /api/fragments : updateFragment
     *
     * @param fragmentEntity fragmentEntity (required)
     * @return OK (status code 200)
     *         or Created (status code 201)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "updateFragment", nickname = "updateFragmentUsingPUT", notes = "", response = FragmentEntity.class, tags={ "fragment-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = FragmentEntity.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/fragments",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.PUT)
    ResponseEntity<FragmentEntity> updateFragmentUsingPUT(@ApiParam(value = "fragmentEntity" ,required=true )  @Valid @RequestBody FragmentEntity fragmentEntity);

}
