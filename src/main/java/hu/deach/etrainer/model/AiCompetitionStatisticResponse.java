package hu.deach.etrainer.model;

import lombok.Data;

import java.util.Date;

@Data
public class AiCompetitionStatisticResponse {

    private Date date;
    private String time;
    private Integer score;

}
