package com.policy.http;

import com.policy.PolicyBuilder;
import com.policy.premium.Premium;
import com.policy.premium.PremiumCalculator;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.stream.Collectors;

@Prototype
@Controller("premium")
@Validated
public class PremiumCalculatorController {

    private PremiumCalculator premiumCalculator;
    private PolicyBuilder policyBuilder;

    @Inject
    public PremiumCalculatorController(PremiumCalculator premiumCalculator, PolicyBuilder policyBuilder) {
        this.premiumCalculator = premiumCalculator;
        this.policyBuilder = policyBuilder;
    }

    @Post
    public Premium calculatePremium(@Body @Valid PolicyDto policy) {

        policyBuilder.makePolicy(policy.getNumber(), policy.getStatus());
        policy.getObjects().forEach(objectDto -> {
            var subObjects = objectDto.getSubObjects().stream().map(subObjectDto ->
                    policyBuilder.makeSubObject(subObjectDto.getName(), subObjectDto.getSumInsured(), subObjectDto.getType()))
                    .collect(Collectors.toList());
            policyBuilder.addObject(objectDto.getName(), subObjects);
        });

        return premiumCalculator.calculate(policyBuilder.get());
    }

}
