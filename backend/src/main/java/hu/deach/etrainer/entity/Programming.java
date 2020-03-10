package hu.deach.etrainer.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "programming")
public class Programming {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @Column
    private String description;

    @Column
    private Integer bit;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "programming")
    private Set<ProgrammingStatistic> programmingStatistics;
}
