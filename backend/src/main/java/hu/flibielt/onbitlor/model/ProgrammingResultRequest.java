package hu.flibielt.onbitlor.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProgrammingResultRequest {
    @NotBlank
    private String programmingName;

    @NotBlank
    private Integer score;
}
