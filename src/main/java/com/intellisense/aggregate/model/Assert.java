package com.intellisense.aggregate.model;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data

public class Assert {
    private Map<String, List<Double>> metricIds;
    private List<String> time;

    public Assert(Map<String, List> metrics) {
        metrics.entrySet().forEach(
                metric -> {
                    if (metric.getKey().equalsIgnoreCase("time")) {
                        this.time = metric.getValue();
                    } else {
                        if (Objects.isNull(metricIds)) {
                            metricIds = new HashMap<>();
                        }

                        metricIds.put(metric.getKey(), metric.getValue());
                    }
                }
        );
    }
}
