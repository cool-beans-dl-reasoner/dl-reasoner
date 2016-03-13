package reasoner.expressions;

import java.util.Set;

public class GreaterThanOrEqualToExpression extends GreaterThanEqualLessThanEqualExpression {
  public GreaterThanOrEqualToExpression(String role, int count) {
    super(role, count);
  }

  @Override
  public boolean contradicts(Set<Expression> expressions) {
    int numberInstancesOfThisRole = getNumberInstancesOfThisRole(expressions);
    return count <= numberInstancesOfThisRole;
  }

  @Override
  public String toString() {
    return ">=" + super.toString();
  }
}
