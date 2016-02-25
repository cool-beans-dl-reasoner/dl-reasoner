package reasoner.expressions;

public class ConceptExpression extends Expression {
  public String concept;

  public ConceptExpression(String concept) {
    this.concept = concept;
  }

  @Override
  public boolean contradicts(Expression other) {
    if (other instanceof NotExpression) {
      return other.contradicts(this);
    }
    return false;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof ConceptExpression) {
      return concept.equals(((ConceptExpression) other).concept);
    }
    return super.equals(other);
  }

  @Override
  public int hashCode() {
    return concept.hashCode();
  }

  @Override
  public String toString() {
    return concept;
  }
}
