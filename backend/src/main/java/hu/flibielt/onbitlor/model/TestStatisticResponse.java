package hu.flibielt.onbitlor.model;

import lombok.Data;

@Data
public class TestStatisticResponse {
    private String username;
    private String firstName;
    private String lastName;
    private Long result;
}
