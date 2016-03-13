package reasoner.expressions;

import java.util.Set;

public abstract class GreaterThanEqualLessThanEqualExpression extends Expression {
  public String role;
  public int count;

  public GreaterThanEqualLessThanEqualExpression(String role, int count) {
    this.role = role;
    this.count = count;
  }

  public abstract boolean contradicts(Set<Expression> expressions);

  public int getNumberInstancesOfThisRole(Set<Expression> expressions) {
    int numberInstancesOfThisRole = 0;
    for (Expression e : expressions) {
      if (e instanceof ExistentialUniversalExpression) {
        if (((ExistentialUniversalExpression) e).role.equals(this.role)) {
          numberInstancesOfThisRole += 1;
        }
      }
    }
    return numberInstancesOfThisRole;
  }

  @Override
  public String toString() {
    return role + "." + count;
  }
}
