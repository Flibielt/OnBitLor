package hu.flibielt.onbitlor.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Integer bit;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "test")
    private Set<TestResult> testResults;
}
