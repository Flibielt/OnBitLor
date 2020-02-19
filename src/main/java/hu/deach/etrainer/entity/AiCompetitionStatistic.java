package hu.deach.etrainer.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;

@Data
@Entity
@Table(name = "ai_competition_statistics")
@IdClass(AiCompetitionStatisticId.class)
public class AiCompetitionStatistic {

    @Id
    @Column(name = "ai_competition_id")
    private Long aiCompetitionId;

    @Id
    @Column(name = "player_id")
    private Long playerId;

    @Column
    private Date date;

    @Column
    private Duration time;

    @Column
    private Integer score;

}
