package hu.deach.etrainer.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role roleId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "player")
    private Set<InGameName> inGameNames;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "player")
    private Set<ProgrammingStatistic> programmingStatistic;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "player")
    private Set<PlayerStatistic> playerStatistics;

}
