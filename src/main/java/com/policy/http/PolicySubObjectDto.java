package com.policy.http;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Introspected
public class PolicySubObjectDto {

    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    private BigDecimal sumInsured;

    @Pattern(regexp = "fire|theft")
    private String type;

    public PolicySubObjectDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(BigDecimal sumInsured) {
        this.sumInsured = sumInsured;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
