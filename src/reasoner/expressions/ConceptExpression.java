package reasoner.expressions;

/**
 * ConceptExpression represents a Concept expression in description logic terminology.
 * 
 * Example usage:
 *
 * ConceptExpression bar = new ConceptExpression("BAR");
 *
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public class ConceptExpression extends Expression {
  public String concept;

 /**
  * Returns ConceptExpression that represent the concept 
  *
  * @param concept the concept that the ConceptExpression will represent
  * @return        the ConceptExpression that represents the concept
  */
  public ConceptExpression(String concept) {
    this.concept = concept;
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
    if (other instanceof NotExpression) {
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
    if (other instanceof ConceptExpression) {
      return concept.equals(((ConceptExpression) other).concept);
    }
    return super.equals(other);
  }

 /**
  * Returns the hash code of the concept. This hash code will be used for the 
  * hash map(s) in the TBox.
  *
  * @return the hash code of the concept
  */
  @Override
  public int hashCode() {
    return concept.hashCode();
  }

 /**
  * Returns the concept as a String
  *
  * @return the concept as a String
  */
  @Override
  public String toString() {
    return concept;
  }
}
