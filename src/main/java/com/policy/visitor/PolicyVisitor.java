package com.policy.visitor;

import com.policy.premium.Premium;
import com.policy.subobject.Fire;
import com.policy.subobject.Theft;

public interface PolicyVisitor {

    public Premium getPremium();

    public void visit(Theft theft);

    public void visit(Fire fire);
}
