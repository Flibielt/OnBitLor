package hu.deach.etrainer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PlayerStatisticDto {

    private Long playerId;
    private Long gameId;
    private Date fromDate;
    private Date toDate;
    private Integer win;
    private Integer lose;
    private Integer draw;

}
