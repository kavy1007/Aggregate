package com.intellisense.aggregate.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SplitList<T> {

    public List<List<T>> split(List<T> metricEntries, int period) {
        int start = 0;
        int end = period;
        List<List<T>> splitEntries = new ArrayList<>();
        while (end <= metricEntries.size()) {
            splitEntries.add(metricEntries.subList(start, end));
            start = end;
            end += period;
        }
        return splitEntries;
    }
}
