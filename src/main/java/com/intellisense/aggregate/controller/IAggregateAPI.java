package com.intellisense.aggregate.controller;

import com.intellisense.aggregate.model.AggregateDTO;
import com.intellisense.aggregate.model.AssetMetrics;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1")
@Api(value = "Aggregate Module")
public interface IAggregateAPI {
    @ApiOperation(value = "Aggregates the provided data from minute values into " +
            " averaged values based on an aggregate time period specified in the request")
    @PostMapping("/aggregate")
    AssetMetrics aggreagedMetrics(@RequestBody AggregateDTO aggregateDTO);
}
