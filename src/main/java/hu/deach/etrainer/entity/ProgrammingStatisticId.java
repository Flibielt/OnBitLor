package hu.deach.etrainer.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProgrammingStatisticId implements Serializable {

    private Long aiCompetitionId;

    private Long playerId;

    private Date date;
}
