package com.example.WebLab3.utility;

import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.interfaces.ValuesFormatter;
import org.kopitubruk.util.json.JSONUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HitValuesFormatter implements ValuesFormatter<Double, Boolean, Hit> {

    @Override
    public String getTableValue(Double aCoordinate) {
        return String.format("%.3f", aCoordinate);
    }

    @Override
    public String getUpperCaseResult(Boolean aResult) {
        return aResult.toString().toUpperCase(Locale.ROOT);
    }

    @Override
    public String getJSONForm(Hit aHit) {
        return JSONUtil.toJSON(getMap(aHit));
    }

    private Map<String, String> getMap(Hit aHit) {
        Map<String, String> fields = new HashMap<>();
        fields.put("x", String.valueOf(aHit.getX()));
        fields.put("y", String.valueOf(aHit.getY()));
        fields.put("r", String.valueOf(aHit.getR()));
        fields.put("currentTime", String.valueOf(aHit.getCurrentTime()));
        fields.put("executionTime", String.valueOf(aHit.getExecutionTime()));
        fields.put("result", String.valueOf(aHit.getResult()));
        return fields;
    }
}