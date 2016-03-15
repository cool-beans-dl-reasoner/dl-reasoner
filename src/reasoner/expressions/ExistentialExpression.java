package reasoner.expressions;

import java.util.HashSet;

/**
 * ExistentialExpression is in the DL dialect that connects a 
 * "Role" with a "Concept".
 *
 * Here Role is defined as a structure. The way we model "Concept" is a set
 * of Expressions, and this makes it easier to determine if contradictions exist.
 *
 * Example usage:
 *
 * ExistentialExpression goToBar = 
 *      new ExistentialExpression("goTo", new ConceptExpression("BAR"));
 *
 * If for instance, we have BAR equivalent PUB, we can use {@method addConcept} 
 * to add PUB to the list of expressions.
 * 
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public class ExistentialExpression extends ExistentialUniversalExpression {

 /**
  * Returns ExistentialExpression that represent the concept 
  *
  * @param role    
  * @param concept the concept that the ExistentialExpression will represent
  * @return        the ExistentialExpression that represents the concept
  */
  public ExistentialExpression(String role, Expression concept) {
    super(role, concept);
  }

 /**
  * Returns whether two existential expressions are equal each other  
  *
  * @param other the other expression to test whether this expression and the 
  *              other expression are equal
  * @return      whether the two expressions are equal
  */
  @Override
  public boolean equals(Object other) {
    if (other instanceof ExistentialExpression) {
      ExistentialExpression o = (ExistentialExpression) other;
      if (o.role.equals(this.role)) {
        HashSet<Expression> combined = new HashSet<>();
        combined.addAll(this.expressions);
        combined.removeAll(o.expressions);
        return combined.isEmpty();
      }
    }
    return false;
  }
}
