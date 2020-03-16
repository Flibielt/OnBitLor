package hu.flibielt.onbitlor.model;

import lombok.Data;

@Data
public class PlayerInfoResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private Integer bit;
}
