package com.policy;

import com.policy.subobject.PolicySubObject;
import com.policy.visitor.PolicyVisitor;

import java.util.ArrayList;
import java.util.Collection;

public class PolicyObject {

    private String name;
    private Collection<PolicySubObject> subObjects = new ArrayList<>();

    public PolicyObject(String name) {
        this.name = name;
    }

    public PolicyObject(String name, Collection<PolicySubObject> subObjects) {
        this(name);
        this.subObjects = subObjects;
    }

    public String getName() {
        return name;
    }

    public Collection<PolicySubObject> getSubObjects() {
        return subObjects;
    }

    public void accept(PolicyVisitor visitor) {
        subObjects.forEach(policySubObject -> policySubObject.accept(visitor));
    }
}
