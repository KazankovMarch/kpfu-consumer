package ru.kpfu.consumer.model;

import javax.persistence.*;

@Entity
public class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "metricGenerator")
    @SequenceGenerator(name = "metricGenerator", sequenceName = "metric_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
