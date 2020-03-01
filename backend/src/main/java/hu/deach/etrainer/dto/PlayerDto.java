package hu.deach.etrainer.dto;

import lombok.Data;

@Data
public class PlayerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

}
