package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class UserRequest {
    @NotEmpty
    @NotBlank
    private String username;

    @NotEmpty
    @NotBlank
    private String passwd;
}
