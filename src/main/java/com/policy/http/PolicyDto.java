package com.policy.http;

import io.micronaut.core.annotation.Introspected;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;

@Introspected
public class PolicyDto {

    @NotBlank
    private String number;

    @NotBlank
    private String status;

    @Valid
    private Collection<PolicyObjectDto> objects = new ArrayList<>();

    public PolicyDto() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Collection<PolicyObjectDto> getObjects() {
        return objects;
    }

    public void setObjects(Collection<PolicyObjectDto> objects) {
        this.objects = objects;
    }
}
