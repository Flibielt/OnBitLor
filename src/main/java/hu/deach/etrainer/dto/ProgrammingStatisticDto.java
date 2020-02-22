package hu.deach.etrainer.dto;

import lombok.Data;

import java.time.Duration;
import java.util.Date;

@Data
public class ProgrammingStatisticDto {

    private Long programmingId;
    private Long playerId;
    private Date date;
    private Duration duration;
    private Integer score;

}
