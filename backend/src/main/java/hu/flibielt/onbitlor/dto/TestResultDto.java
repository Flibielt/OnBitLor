package hu.flibielt.onbitlor.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TestResultDto {

    private Long playerId;
    private Long testId;
    private Date date;
    private Integer result;
}
