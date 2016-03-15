package reasoner.expressions;

import java.util.Set;

/**
 * ExistentialUniversalExpression is super class to both
 * {@class ExistentialExpression} and {@class UniversalExpression}.
 * It adds base functionality that is common to both of the classes.
 * 
 * GreaterThanEqualLessThanEqualExpression is the abstract class for both
 * {@class ExistentialExpression} and {@class UniversalExpression}. 
 * 
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
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
