package hu.flibielt.onbitlor.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TestResultRequest {
    @NotBlank
    private String name;

    @NotBlank
    private Integer score;
}
