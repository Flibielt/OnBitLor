package hu.flibielt.onbitlor.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TestResultDto {

    private Long player;
    private Long test;
    private Date date;
    private Integer result;
}
