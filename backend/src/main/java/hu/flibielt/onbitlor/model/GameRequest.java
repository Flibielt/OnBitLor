package hu.flibielt.onbitlor.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class GameRequest {

    @NotBlank
    @Size(max = 100)
    private String name;

}
