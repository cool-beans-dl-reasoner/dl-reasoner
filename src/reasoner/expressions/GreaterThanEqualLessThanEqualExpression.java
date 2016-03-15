package reasoner.expressions;

import java.util.Set;

/**
 * GreaterThanEqualLessThanEqualExpression is the abstract class for both
 * {@class ExistentialExpression} and {@class UniversalExpression}. 
 * 
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public abstract class GreaterThanEqualLessThanEqualExpression extends Expression {
  public String role;
  public int count;

 /**
  * Returns GreaterThanEqualLessThanEqualExpression that represent the concept 
  *
  * @param role    
  * @param concept the concept that the GreaterThanEqualLessThanEqualExpression 
  *                will represent
  * @return        the GreaterThanEqualLessThanEqualExpression that 
  *                represents the concept
  */
  public GreaterThanEqualLessThanEqualExpression(String role, int count) {
    this.role = role;
    this.count = count;
  }

 /**
  * Returns whether two expressions contradict each other.  
  *
  * @param other the other expression to test whether this expression and the 
  *              other expression contradicts
  * @return      whether the two expressions contradicts
  */
  public abstract boolean contradicts(Set<Expression> expressions);

 /**
  * Returns the count associated with the role.
  *
  * @param other the other expression to test whether this expression and the 
  *              other expression contradicts
  * @return      whether the two expressions contradicts
  */
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

 /**
  * Returns the role and count as a String
  *
  * @return the role and count as a String
  */
  @Override
  public String toString() {
    return role + "." + count;
  }
}
