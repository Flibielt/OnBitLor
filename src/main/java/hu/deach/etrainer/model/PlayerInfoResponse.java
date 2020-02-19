package hu.deach.etrainer.model;

import lombok.Data;

import java.util.List;

@Data
public class PlayerInfoResponse {

    private String firstName;
    private String lastName;
    private String ign;
    private List<String> games;

}
