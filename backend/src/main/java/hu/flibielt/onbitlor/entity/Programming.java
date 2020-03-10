package hu.flibielt.onbitlor.entity;

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

    @Column
    private String description;

    @Column
    private Integer bit;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "programming")
    private Set<ProgrammingStatistic> programmingStatistics;
}
