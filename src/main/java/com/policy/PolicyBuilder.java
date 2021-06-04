package com.policy;

import com.policy.subobject.Fire;
import com.policy.subobject.PolicySubObject;
import com.policy.subobject.Theft;
import io.micronaut.context.annotation.Prototype;

import java.math.BigDecimal;
import java.util.List;

@Prototype
public class PolicyBuilder {

    private Policy policy;

    public PolicySubObject makeSubObject(String name, BigDecimal sumInsured, String type) {

        if (type.equals("fire")) return new Fire(name, sumInsured);
        else if (type.equals("theft")) return new Theft(name, sumInsured);

        throw new IllegalArgumentException("invalid type");
    }

    public void addObject(String name, List<PolicySubObject> subObjects) {
        PolicyObject policyObject = new PolicyObject(name, subObjects);
        policy.addObject(policyObject);
    }

    public void makePolicy(String number, String status) {
        policy = new Policy(number, status);

    }

    public Policy get() {
        return policy;
    }
}
