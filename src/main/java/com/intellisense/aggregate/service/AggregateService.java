package com.intellisense.aggregate.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellisense.aggregate.model.AggregateDTO;
import com.intellisense.aggregate.model.AssetMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AggregateService {
    @Autowired
    private SplitList splitList;
    @Value("${aggregate-api.url}")
    private String dataURL;
    @Autowired
    private RestTemplate restTemplate;

    public AssetMetrics getAggregates(AggregateDTO aggregateDTO) {
        AssetMetrics assetMetricsResponse = getAssertMetrics();

        assetMetricsResponse.getAssetId().values().stream().parallel()
                .forEach(assets -> {
                    assets.getMetricIds().entrySet().forEach(metrics -> {
                        List<Double> metricEntries = metrics.getValue();
                        List<List<Double>> splitEntries = splitList.split(metricEntries, aggregateDTO.getPeriod());
                        List<Double> aggregatedMetricEntries = splitEntries
                                .stream()
                                .map(splitMetrics -> {
                                    Double aggregatedMetric = splitMetrics.stream()
                                            .reduce(Double.valueOf("0"), Double::sum);
                                    return aggregatedMetric / (double) aggregateDTO.getPeriod();
                                }).parallel().collect(Collectors.toList());
                        metrics.setValue(aggregatedMetricEntries);
                    });
                    List<List<String>> timeSeriesList = splitList.split(assets.getTime(), aggregateDTO.getPeriod());
                    assets.setTime(timeSeriesList.stream()
                            .map(times -> times.get(aggregateDTO.getPeriod() - 1))
                            .collect(Collectors.toList()));
                });
        return assetMetricsResponse;
    }


    private AssetMetrics getAssertMetrics() {
//        Map s = restTemplate.getForEntity(dataURL, Map.class).getBody();
//        return new AssetMetrics(s);
        String outFile = "src/main/resources/SampleData.json";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {

            Map<String, Map<String, List>> assetMetrics =
                    objectMapper.readValue(new File(outFile), new TypeReference<Map<String, Map<String, List>>>() {
                    });
            return new AssetMetrics(assetMetrics);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }
}
