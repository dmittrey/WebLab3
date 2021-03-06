package com.example.WebLab3.entity;

import com.example.WebLab3.interfaces.ValuesFormatter;
import com.example.WebLab3.utility.HitValuesFormatter;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "HITS")
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Transient
    private ValuesFormatter<Double, Boolean, Hit> hitValuesFormatter = new HitValuesFormatter();

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