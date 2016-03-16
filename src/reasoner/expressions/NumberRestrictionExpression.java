package reasoner.expressions;

import java.util.Set;

/**
 * NumberRestrictionExpression is the abstract class for both
 * {@class ExistentialExpression} and {@class UniversalExpression}. 
 *
 * This class represents a general number restriction placed upon a Role in description logic.
 *
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public abstract class NumberRestrictionExpression extends Expression {
  public String role;
  public int count;

 /**
  * Constructs a new NumberRestrictionExpression object.
  *
  * Note: Do not use explicitly call this constructor. Construct a subclass instead.
  *
  * @param role The Role.
  * @param count The number restriction.
  */
  protected NumberRestrictionExpression(String role, int count) {
    this.role = role;
    this.count = count;
  }

 /**
  * Returns whether any of the set of expressions contradicts this expression.
  */
  public abstract boolean contradicts(Set<Expression> expressions);

 /**
  * @param expressions A set of expressions.
  * @return the number of times this {@param role} appears in the set of expressions.
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
  * {@inheritDoc}
  */
  @Override
  public String toString() {
    return role + "." + count;
  }
}
