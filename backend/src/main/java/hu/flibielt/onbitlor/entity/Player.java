package hu.flibielt.onbitlor.entity;

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

    @Column
    private Integer bit;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "player")
    private Set<InGameName> inGameNames;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "player")
    private Set<ProgrammingStatistic> programmingStatistic;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "player")
    private Set<PlayerStatistic> playerStatistics;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "player")
    private Set<TestResult> testResults;

}
