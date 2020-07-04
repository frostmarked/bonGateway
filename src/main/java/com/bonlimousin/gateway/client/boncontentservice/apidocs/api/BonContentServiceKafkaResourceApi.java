/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.bonlimousin.gateway.client.boncontentservice.apidocs.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bonlimousin.gateway.client.boncontentservice.apidocs.model.PublishResult;
import com.bonlimousin.gateway.client.boncontentservice.apidocs.model.SseEmitter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-04T00:04:26.742175+02:00[Europe/Stockholm]")

@Validated
@Api(value = "BonContentServiceKafkaResource", description = "the BonContentServiceKafkaResource API")
public interface BonContentServiceKafkaResourceApi {

    /**
     * GET /api/bon-content-service-kafka/consume : consume
     *
     * @param consumerParams consumerParams (required)
     * @param topic topic (required)
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "consume", nickname = "consumeUsingGET", notes = "", response = SseEmitter.class, tags={ "bon-content-service-kafka-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = SseEmitter.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/bon-content-service-kafka/consume",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<SseEmitter> consumeUsingGET(@NotNull @ApiParam(value = "consumerParams", required = true, defaultValue = "null") @Valid @RequestParam(value = "consumerParams", required = true, defaultValue="null") Object consumerParams,@NotNull @ApiParam(value = "topic", required = true) @Valid @RequestParam(value = "topic", required = true) List<String> topic);


    /**
     * POST /api/bon-content-service-kafka/publish/{topic} : publish
     *
     * @param message message (required)
     * @param topic topic (required)
     * @param key key (optional)
     * @return OK (status code 200)
     *         or Created (status code 201)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @ApiOperation(value = "publish", nickname = "publishUsingPOST", notes = "", response = PublishResult.class, tags={ "bon-content-service-kafka-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = PublishResult.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/bon-content-service-kafka/publish/{topic}",
        produces = "*/*", 
        method = RequestMethod.POST)
    ResponseEntity<PublishResult> publishUsingPOST(@NotNull @ApiParam(value = "message", required = true) @Valid @RequestParam(value = "message", required = true) String message,@ApiParam(value = "topic",required=true) @PathVariable("topic") String topic,@ApiParam(value = "key") @Valid @RequestParam(value = "key", required = false) String key);

}
