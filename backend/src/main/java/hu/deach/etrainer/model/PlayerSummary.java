package hu.deach.etrainer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerSummary {

    private Long id;
    private String username;
    private String name;

}
