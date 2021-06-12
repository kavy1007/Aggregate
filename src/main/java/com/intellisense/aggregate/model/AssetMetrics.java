package com.intellisense.aggregate.model;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
public class AssetMetrics {

    public AssetMetrics(Map<String, Map<String, List>> assetId) {
        assetId.entrySet().forEach(metrics -> {
            Assert assets = new Assert(metrics.getValue());
            if (Objects.isNull(this.assetId)) {
                this.assetId = new HashMap<>();
            }
            this.assetId.put(metrics.getKey(), assets);
        });
    }

    private Map<String, Assert> assetId;
}
