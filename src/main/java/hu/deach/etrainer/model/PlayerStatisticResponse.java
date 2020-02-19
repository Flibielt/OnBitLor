package hu.deach.etrainer.model;

import lombok.Data;

import java.util.Date;

@Data
public class PlayerStatisticResponse {

    private Date fromDate;
    private Date toDate;
    private Integer win;
    private Integer lose;
    private Integer draw;

}
