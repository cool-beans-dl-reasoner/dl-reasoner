package reasoner.expressions;

import java.util.HashSet;

/**
 * UniversalExpression is in the DL dialect that connects a "Role" with a "Concept".
 *
 * Here Role is defined as a structure. The way we model "Concept" is a set
 * of Expressions, and this makes it easier to determine if contradictions exist.
 *
 * Example usage:
 *
 * UniversalExpression goToBar = new UniversalExpression("goTo", new ConceptExpression("BAR"));
 *
 * If for instance, we have BAR equivalent PUB, we can use {@method addConcept} to add PUB to the list of expressions.
 */
public class UniversalExpression extends ExistentialUniversalExpression {

  public UniversalExpression(String role, Expression concept) {
    super(role, concept);
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof UniversalExpression) {
      UniversalExpression o = (UniversalExpression) other;
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
