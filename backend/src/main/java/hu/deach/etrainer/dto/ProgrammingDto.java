package hu.deach.etrainer.dto;

import lombok.Data;

@Data
public class ProgrammingDto {

    private Long id;
    private Long gameId;
    private String name;
    private String description;
    private Integer bit;

}
