package reasoner;

import reasoner.expressions.Expression;

public class SubsumptionEquivalence {

  Expression lhs;
  Expression rhs;

  public SubsumptionEquivalence(Expression lhs, Expression rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }
}
