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
public class GreaterThanOrEqualToExpression 
  extends GreaterThanEqualLessThanEqualExpression {

   /**
  * Returns ExistentialUniversalExpression that represent the concept 
  *
  * @param role    
  * @param concept the concept that the ExistensialExpression will represent
  * @return        the ExistentialUniversalExpression that 
  *                represents the concept
  */
  public GreaterThanOrEqualToExpression(String role, int count) {
    super(role, count);
  }

 /**
  * Returns whether two expressions contradict each other.  
  *
  * @param other the other expression to test whether this expression and the 
  *              other expression contradicts
  * @return      whether the two expressions contradicts
  */
  @Override
  public boolean contradicts(Set<Expression> expressions) {
    int numberInstancesOfThisRole = getNumberInstancesOfThisRole(expressions);
    return count <= numberInstancesOfThisRole;
  }

 /**
  * Returns the role and count as a String
  *
  * @return the role and count as a String
  */
  @Override
  public String toString() {
    return ">=" + super.toString();
  }
}
