package hu.deach.etrainer.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PlayerStatisticId implements Serializable {

    private Long playerId;

    private Long gameId;

    private Date fromDate;
}
