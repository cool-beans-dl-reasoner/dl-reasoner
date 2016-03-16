package reasoner.expressions;

/**
 * Expression is the base abstract class that all other Expression classes will extend
 * from.
 *
 * Known subclasses include:
 * {@class ConceptExpression},
 * {@class NotExpression},
 * {@class UnionExpression},
 * {@class IntersectionExpression},
 * {@class IntersectUnionExpression},
 * {@class ExistentialUniversalExpression}
 * {@class GreaterThanEqualLessThanEqualExpression},
 * {@class GreaterThanEqualToExpression},
 * {@class LessThanEqualToExpression},
 * {@class BottomExpression},
 * {@class TopExpression},
 *
 *
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public abstract class Expression {

 /**
  * {@inheritDoc}
  */
  public boolean contradicts(Expression other) {
    if (other instanceof NotExpression) {
      return other.contradicts(this);
    }
    return false;
  }
}
