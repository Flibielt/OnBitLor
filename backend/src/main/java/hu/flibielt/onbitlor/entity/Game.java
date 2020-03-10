package hu.flibielt.onbitlor.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String name;

    @Column
    private Integer bit;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    private Set<Programming> programmings;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    private Set<InGameName> inGameNames;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    private Set<PlayerStatistic> playerStatistics;
}
