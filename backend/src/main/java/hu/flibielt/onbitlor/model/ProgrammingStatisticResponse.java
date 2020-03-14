package hu.flibielt.onbitlor.model;

import lombok.Data;

import java.util.Date;

@Data
public class ProgrammingStatisticResponse {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private Date date;
    private Integer score;
}
