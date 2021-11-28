package com.example.WebLab3.interfaces;

import com.example.WebLab3.entity.Hit;

public interface HitValuesFormatterInterface {

    String getTableValue(Double aCoordinate);

    String getUpperCaseResult(Boolean aResult);

    String getJSONForm(Hit aHit);
}