package hu.flibielt.onbitlor.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ProgrammingRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private Integer bit;
}
