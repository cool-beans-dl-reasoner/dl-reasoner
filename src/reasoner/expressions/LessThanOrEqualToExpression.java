package reasoner.expressions;

import java.util.Set;

/**
 * LessThanOrEqualToExpression represents greater than or equal to in
 * description logic
 * 
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public class LessThanOrEqualToExpression 
  extends GreaterThanEqualLessThanEqualExpression {

 /**
  * Returns LessThanOrEqualToExpression that represent the concept 
  * less than or equal to
  * 
  * e.g. "<= 2.goto" where goto is the role and 2 is the count
  *
  * @param role    
  * @param concept the concept that the LessThanOrEqualToExpression 
  *                will represent
  * @return        the LessThanOrEqualToExpression that 
  *                represents the concept
  */
  public LessThanOrEqualToExpression(String role, int count) {
    super(role, count);
  }

  @Override
  public boolean contradicts(Set<Expression> expressions) {
    int numberInstancesOfThisRole = getNumberInstancesOfThisRole(expressions);
    return count >= numberInstancesOfThisRole;
  }

 /**
  * Returns whether two expressions contradict each other.  
  *
  * @param other the other expression to test whether this expression and the 
  *              other expression contradicts
  * @return      whether the two expressions contradicts
  */
  @Override
  public String toString() {
    return "<=" + super.toString();
  }
}
