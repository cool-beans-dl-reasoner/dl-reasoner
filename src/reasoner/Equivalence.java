package reasoner;

import reasoner.expressions.Expression;

public class Equivalence {

  Expression lhs;
  Expression rhs;

  public Equivalence(Expression lhs, Expression rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }
}
