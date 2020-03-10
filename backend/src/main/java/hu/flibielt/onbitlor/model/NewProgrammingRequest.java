package hu.flibielt.onbitlor.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NewProgrammingRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private Integer bit;
}
