package reasoner;

import reasoner.expressions.ExistentialUniversalExpression;
import reasoner.expressions.Expression;
import reasoner.expressions.NumberRestrictionExpression;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Reasoner {

  public TBox tbox;
  public SubsumptionEquivalence query;

  /**
   * Constructs a new Reasoner.
   * 
   * @param tbox  The terminology box.
   * @param query A subsumption query (with D already negated).
   */
  public Reasoner(TBox tbox, SubsumptionEquivalence query) {
    this.tbox = tbox;
    this.query = query;
  }

  /**
   * Uses the query and the TBox to try and find a contradiction.
   * If the knowledge base can no longer grow, and a contradiction has not been made,
   * then we claim that the query is valid.
   *
   * @return True if the query is valid, false otherwise.
   */
  public boolean queryIsValid() {
    Set<Expression> expressions = new HashSet<>();
    expressions.add(query.lhs);
    expressions.add(query.rhs);

    boolean databaseUpdated;
    boolean foundContradiction;
    do {
      databaseUpdated = false;
      foundContradiction = hasContradiction(expressions, expressions);
      if (!foundContradiction) {
        databaseUpdated |= expand(expressions);
        //System.out.println(tbox);
        //print(expressions);
      }
    } while (!foundContradiction && databaseUpdated);

    if (foundContradiction) {
      return true;
    }
    return false;
  }

  /**
   * Looks through both sets of expressions and tries to find a contradiction.
   * 
   * @param  lhs A set of expressions.
   * @param  rhs A set of expressions.
   * @return     true if there is a contradiction between the two sets, false otherwise
   */
  public boolean hasContradiction(Set<Expression> lhs, Set<Expression> rhs) {
    for (Expression expression1 : lhs) {
      if (expression1 instanceof NumberRestrictionExpression) {
        NumberRestrictionExpression e = (NumberRestrictionExpression) expression1;
        if (e.contradicts(lhs) || e.contradicts(rhs)) {
          return true;
        }
      }
      for (Expression expression2 : rhs) {
        if (expression1.contradicts(expression2)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method takes a set of expressions, and uses it to expand and "add knowledge"
   * to our Terminology Box (TBOX).
   * @param  expressions the set of expressions to be expanded
   * @return True if the knowledge base grew, false otherwise.
   */
  public boolean expand(Set<Expression> expressions) {
    int originalSize = expressions.size();
    Set<Expression> newExpressions = new HashSet<>();
    for (Expression expression : expressions) {
      if (expression instanceof ExistentialUniversalExpression) {
        ExistentialUniversalExpression existentialUniversalExpression = (ExistentialUniversalExpression) expression;
        Set<Expression> newExpressions2 = new HashSet<>();
        for (Expression e : existentialUniversalExpression.expressions) {
          Set<Expression> fromTbox = tbox.get(e);
          if (fromTbox == null) {
            fromTbox = Collections.EMPTY_SET;
          }
          newExpressions2.addAll(fromTbox);
        }
        existentialUniversalExpression.addConcepts(newExpressions2);
      } else {
        Set<Expression> equivalentOrSubsumptions = tbox.get(expression);
        if (equivalentOrSubsumptions == null) {
          equivalentOrSubsumptions = new HashSet<>();
        }
        newExpressions.addAll(equivalentOrSubsumptions);
        tbox.put(expression, equivalentOrSubsumptions);
      }
    }
    expressions.addAll(newExpressions);
    return originalSize != expressions.size();
  }

  /**
   * Helper method to print out a set of expressions.
   * @param expressions Set of expressions to print.
   */
  public static void print(Set<Expression> expressions) {
    for (Expression e : expressions) {
      System.out.print("\t");
      System.out.println(e);
    }
  }
}
