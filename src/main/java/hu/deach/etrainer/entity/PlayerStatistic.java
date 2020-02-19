package hu.deach.etrainer.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "player_statistics")
@IdClass(PlayerStatisticId.class)
public class PlayerStatistic {

    @Id
    @Column(name = "player_id")
    private Long playerId;

    @Id
    @Column(name = "game_id")
    private Long gameId;

    @Id
    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @Column
    private Integer win;

    @Column
    private Integer lose;

    @Column
    private Integer draw;

}
