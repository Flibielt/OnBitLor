package hu.deach.etrainer.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class IgnId implements Serializable {

    private Long playerId;

    private Long gameId;

}
