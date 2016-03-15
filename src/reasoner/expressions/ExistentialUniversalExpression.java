package reasoner.expressions;

import java.util.HashSet;
import java.util.Set;

/**
 * ExistentialUniversalExpression is super class to both
 * {@class ExistentialExpression} and {@class UniversalExpression}.
 * It adds base functionality that is common to both of the classes.
 * 
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public class ExistentialUniversalExpression extends Expression {
  public String role;
  public HashSet<Expression> expressions;

 /**
  * Returns ExistentialUniversalExpression that represent the concept 
  *
  * @param role    
  * @param concept the concept that the ExistensialExpression will represent
  * @return        the ExistentialUniversalExpression that 
  *                represents the concept
  */
  protected ExistentialUniversalExpression(String role, Expression concept) {
    this.role = role;
    expressions = new HashSet<>();
    expressions.add(concept);
  }

  public void addConcept(Expression concept) {
    expressions.add(concept);
  }

 /**
  * Returns whether two concept expressions contradict each other  
  *
  * @param other the other expression to test whether this expression and the 
  *              other expression contradicts
  * @return      whether the two expressions contradicts
  */
  @Override
  public boolean contradicts(Expression other) {
    if (other instanceof ExistentialUniversalExpression) {
      ExistentialUniversalExpression o = (ExistentialUniversalExpression) other;
      if (o.role.equals(role)) {
        for (Expression e : expressions) {
          for (Expression e2 : o.expressions) {
            if (e.contradicts(e2)) {
              return true;
            }
          }
        }
      }
    } else if (other instanceof NotExpression) {
      return other.contradicts(this);
    }
    return false;
  }

 /**
  * Returns whether two concept expressions are equal each other  
  *
  * @param other the other expression to test whether this expression and the 
  *              other expression are equal
  * @return      whether the two expressions are equal
  */
  @Override
  public boolean equals(Object other) {
    throw new RuntimeException("equals should be handled by subclasses ExistentialExpression or UniversalExpression");
  }

 /**
  * Returns the hash code of the concept. This hash code will be used for the 
  * hash map(s) in the TBox.
  *
  * @return the hash code of the concept
  */
  @Override
  public int hashCode() {
    int hashCode = role.hashCode();
    for (Expression concept : expressions) {
      hashCode += concept.hashCode();
    }
    return hashCode;
  }

 /**
  * Returns the concept as a String
  *
  * @return the concept as a String
  */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(role + "." + "[");
    for (Expression concept : expressions) {
      sb.append(concept);
      sb.append(",");
    }
    sb.deleteCharAt(sb.length() - 1);
    sb.append("]");
    return sb.toString();
  }

  /**
   * 
   * 
   * @param expressions the set of expressions to be added to another set of
   *                    of expressions
   */
  public void addConcepts(Set<Expression> expressions) {
    this.expressions.addAll(expressions);
  }
}
