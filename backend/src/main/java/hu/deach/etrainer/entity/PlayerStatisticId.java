package hu.deach.etrainer.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PlayerStatisticId implements Serializable {

    private Long player;

    private Long game;

    private Date fromDate;
}
