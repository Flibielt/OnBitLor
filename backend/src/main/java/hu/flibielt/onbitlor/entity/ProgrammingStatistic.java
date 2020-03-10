package hu.flibielt.onbitlor.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "programming_statistics")
@IdClass(ProgrammingStatisticId.class)
public class ProgrammingStatistic {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programming_id")
    private Programming programming;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @Column
    private Date date;

    @Column
    private Integer score;

}
