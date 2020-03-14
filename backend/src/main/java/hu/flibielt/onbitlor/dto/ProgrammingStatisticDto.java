package hu.flibielt.onbitlor.dto;

import lombok.Data;

import java.time.Duration;
import java.util.Date;

@Data
public class ProgrammingStatisticDto {

    private Long id;
    private Long programming;
    private Long player;
    private Date date;
    private Integer score;

}
