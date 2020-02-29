package hu.deach.etrainer.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TestResultId implements Serializable {

    private Long player;

    private Long test;

}
