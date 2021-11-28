package com.example.WebLab3.entity;

import com.example.WebLab3.interfaces.HitValuesFormatterInterface;
import com.example.WebLab3.utility.HitValuesFormatter;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Hit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;

    private Double x;
    private Double y;
    private Double r;
    private String currentTime;
    private Double executionTime;
    private Boolean result;
    private String sessionId;

    @Transient
    private HitValuesFormatterInterface hitValuesFormatter = new HitValuesFormatter();

    public String getTableX() {
        return hitValuesFormatter.getTableValue(x);
    }

    public String getTableY() {
        return hitValuesFormatter.getTableValue(y);
    }

    public String getTableR() {
        return hitValuesFormatter.getTableValue(r);
    }

    public String getTableResult() {
        return hitValuesFormatter.getUpperCaseResult(result);
    }

    public String jsonHit() {
        return hitValuesFormatter.getJSONForm(this);
    }
}