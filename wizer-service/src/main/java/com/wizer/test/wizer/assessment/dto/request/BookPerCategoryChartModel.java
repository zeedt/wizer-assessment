package com.wizer.test.wizer.assessment.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @Builder
public class BookPerCategoryChartModel {

    private final List<BigInteger> yAxisData = new ArrayList<>();

    private final List<String> xAxisData = new ArrayList<>();


}
