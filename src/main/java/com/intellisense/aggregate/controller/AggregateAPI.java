package com.intellisense.aggregate.controller;

import com.intellisense.aggregate.model.AggregateDTO;
import com.intellisense.aggregate.model.AssetMetrics;
import com.intellisense.aggregate.service.AggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AggregateAPI implements IAggregateAPI {
    @Autowired
    private AggregateService aggregateService;

    @Override
    public AssetMetrics aggreagedMetrics(AggregateDTO aggregateDTO) {
        return aggregateService.getAggregates(aggregateDTO);
    }
}
