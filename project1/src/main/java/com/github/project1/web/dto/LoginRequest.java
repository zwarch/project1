package com.github.project1.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@Getter
public class LoginRequest {
    @ApiModelProperty(name="email", value="email", example = "abc@gmail.com") private String email;
    @ApiModelProperty(name="password", value="password", example = "pass") private String password;
}