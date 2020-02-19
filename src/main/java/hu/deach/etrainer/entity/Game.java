package hu.deach.etrainer.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "games")
public class Game {

    @Id
    private Long Id;

    @Column
    private String name;
}
