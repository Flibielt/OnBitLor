package hu.flibielt.onbitlor.dto;

import lombok.Data;

import java.time.Duration;
import java.util.Date;

@Data
public class ProgrammingStatisticDto {

    private Long id;
    private Long programmingId;
    private Long playerId;
    private Date date;
    private Integer score;

}
