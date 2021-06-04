package com.policy.http;

import io.micronaut.core.annotation.Introspected;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;



@Introspected
public class PolicyObjectDto {

    @NotBlank
    private String name;

    @Valid
    private Collection<PolicySubObjectDto> subObjects = new ArrayList<>();

    public PolicyObjectDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<PolicySubObjectDto> getSubObjects() {
        return subObjects;
    }

    public void setSubObjects(Collection<PolicySubObjectDto> subObjects) {
        this.subObjects = subObjects;
    }
}
