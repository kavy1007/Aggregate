package com.intellisense.aggregate.unittests;

import com.intellisense.aggregate.AggregateApplication;
import com.intellisense.aggregate.model.AggregateDTO;
import com.intellisense.aggregate.model.AssetMetrics;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = AggregateApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestAggregateAPI {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testAggregateAPI() {
        AggregateDTO aggregateDTO = new AggregateDTO();
        aggregateDTO.setPeriod(60);
        ResponseEntity assetMetricsResponse = aggregateAPI(aggregateDTO);
        Assert.assertEquals(HttpStatus.OK, assetMetricsResponse.getStatusCode());
    }

    private ResponseEntity aggregateAPI(AggregateDTO aggregateDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(aggregateDTO, headers);
        return restTemplate.exchange(
                "http://localhost:" + randomServerPort + "/aggregate",
                HttpMethod.POST,
                httpEntity,
                AssetMetrics.class);
    }
}
