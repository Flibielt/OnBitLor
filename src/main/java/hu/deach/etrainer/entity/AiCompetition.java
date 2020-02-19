package hu.deach.etrainer.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ai_competitions")
public class AiCompetition {

    @Id
    private Long Id;

    @Column(name = "game_id")
    private Long gameId;

    @Column
    private String name;
}
