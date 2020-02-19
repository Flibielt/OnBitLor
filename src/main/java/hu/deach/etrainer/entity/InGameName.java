package hu.deach.etrainer.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "in_game_names")
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
