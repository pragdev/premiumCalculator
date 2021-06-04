package com.policy;

import com.policy.visitor.PolicyVisitor;

import java.util.ArrayList;
import java.util.Collection;

public class Policy  {

    private String number;
    private String status;
    private Collection<PolicyObject> objects = new ArrayList<>();

    public Policy(String number, String status) {
        this.number = number;
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public String getStatus() {
        return status;
    }

    public Collection<PolicyObject> getObjects() {
        return objects;
    }

    public void accept(PolicyVisitor visitor) {
        objects.forEach(policyObject -> policyObject.accept(visitor));
    }

    public void addObject(PolicyObject policyObject) {
        this.objects.add(policyObject);
    }
}
