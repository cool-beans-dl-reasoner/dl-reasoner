package reasoner.expressions;

import java.util.HashSet;

/**
 * ExistentialExpression is representative of "there exists" in description logic terminology.
 * An ExistentialExpression can be created by passing in a "Role" (represented as a String)
 * with a "Concept" which is represented as an {@class ConceptExpression}.
 *
 * Example usage:
 *
 * ExistentialExpression goToBar = new ExistentialExpression("goTo", new ConceptExpression("BAR"));
 * 
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public class ExistentialExpression extends ExistentialUniversalExpression {

 /**
  * Constructs a new ExistentialExpression object.
  *
  * @param role The role. ie) "goTo"
  * @param concept The concept. ie) "BAR"
  */
  public ExistentialExpression(String role, Expression concept) {
    super(role, concept);
  }

  /**
   * {@inheritDoc}
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
