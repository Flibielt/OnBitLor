package hu.deach.etrainer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(IgnId.class)
public class InGameName {

    @Id
    @Column(name = "player_id")
    private Long playerId;

    @Id
    @Column(name = "game_id")
    private Long gameId;

    @Column
    private String ign;
}
