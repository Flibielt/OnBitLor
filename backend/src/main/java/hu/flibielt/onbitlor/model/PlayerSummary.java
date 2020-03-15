package hu.flibielt.onbitlor.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class PlayerSummary {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private Integer bit;
    private ArrayList<String> authorities;

}
