package com.intellisense.aggregate.controller;

import com.intellisense.aggregate.model.AggregateDTO;
import com.intellisense.aggregate.model.AssetMetrics;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface IAggregateAPI {
    @PostMapping("/aggregate")
    AssetMetrics getEmployeeById(@RequestBody AggregateDTO aggregateDTO);
}
