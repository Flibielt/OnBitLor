package hu.flibielt.onbitlor.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TestResultId implements Serializable {

    private Long player;

    private Long test;

}
