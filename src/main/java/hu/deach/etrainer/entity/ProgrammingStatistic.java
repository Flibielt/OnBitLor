package hu.deach.etrainer.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;

@Data
@Entity
@Table(name = "programming_statistics")
@IdClass(ProgrammingStatisticId.class)
public class ProgrammingStatistic {

    /*
    @Id
    @Column(name = "programming_id")
    private Long programmingId;

    @Id
    @Column(name = "player_id")
    private Long playerId;*/

    @Column
    private Date date;

    @Column
    private Duration time;

    @Column
    private Integer score;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programming_id")
    private Programming programming;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

}
