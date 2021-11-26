package com.example.WebLab3.entity;

import lombok.Data;
import org.kopitubruk.util.json.JSONUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Data
public class Hit {
    private Double x;
    private Double y;
    private Double r;
    private String currentTime;
    private Double executionTime;
    private Boolean result;

    public String getTableX() {
        return String.format("%.3f", getX());
    }

    public String getTableY() {
        return String.format("%.3f", getY());
    }

    public String getTableR() {
        return String.format("%.3f", getR());
    }

    public String getTableResult() {
        return getResult().toString().toUpperCase(Locale.ROOT);
    }

    public String jsonHit() {
        return JSONUtil.toJSON(this.getMap());
    }

    private Map<String, String> getMap() {
        Map<String, String> fields = new HashMap<>();
        fields.put("x", String.valueOf(x));
        fields.put("y", String.valueOf(y));
        fields.put("r", String.valueOf(r));
        fields.put("currentTime", String.valueOf(currentTime));
        fields.put("executionTime", String.valueOf(executionTime));
        fields.put("result", String.valueOf(result));
        return fields;
    }
}