package hu.flibielt.onbitlor.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class TestRequest {
    @NotBlank
    @Size(max = 255)
    private String name;
}
