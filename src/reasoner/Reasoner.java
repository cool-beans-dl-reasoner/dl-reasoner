package reasoner;

import reasoner.expressions.ExistentialExpression;
import reasoner.expressions.Expression;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Reasoner {

  public TBox tbox;
  public SubsumptionEquivalence query;

  /**
   * Constructor for Reasoner
   * 
   * @param tbox  the list of parse expressions
   * @param query the negated query
   */
  public Reasoner(TBox tbox, SubsumptionEquivalence query) {
    this.tbox = tbox;
    this.query = query;
  }

  public boolean queryIsValid() {
    Set<Expression> cAndD = new HashSet<>();
    cAndD.add(query.lhs);
    cAndD.add(query.rhs);

    boolean databaseUpdated;
    boolean foundContradiction;
    do {
      databaseUpdated = false;
      foundContradiction = hasContradiction(cAndD, cAndD);
      if (!foundContradiction) {
        databaseUpdated |= expand(cAndD);

        System.out.println(tbox);
        print("C", cAndD);
      }
    } while (!foundContradiction && databaseUpdated);

    if (foundContradiction) {
      return true;
    }
    return false;
  }

  /**
   * Tries to find if there is a contradiction within the 2 given expressions
   * 
   * @param  lhs left-hand side expression
   * @param  rhs right-hand side expression
   * @return     true if there is a contradiction, false otherwise
   */
  public boolean hasContradiction(Set<Expression> lhs, Set<Expression> rhs) {
    // TODO: This method looks through all the expressions in lhs, and tries to find if there are any contradicting
    // expressions in the rhs.

    for (Expression expression1 : lhs) {
      for (Expression expression2 : rhs) {
        if (expression1.contradicts(expression2)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This will look wihin expressions for all expressions where the left-hand
   * side is the query and use it to expand the atomic element
   *
   * e.g. John -> look at left hand side with only John (if also rich and human)
   * look at left hand side for rich and human
   * 
   * @param  expressions the set of expressions to be expanded
   * @return 
   */
  public boolean expand(Set<Expression> expressions) {
    int originalSize = expressions.size();
    Set<Expression> newExpressions = new HashSet<>();
    for (Expression expression : expressions) {
      if (expression instanceof ExistentialExpression) {
        ExistentialExpression existentialExpression = (ExistentialExpression) expression;
        Set<Expression> newExpressions2 = new HashSet<>();
        for (Expression e : existentialExpression.expressions) {
          Set<Expression> fromTbox = tbox.get(e);
          if (fromTbox == null) {
            fromTbox = Collections.EMPTY_SET;
          }
          newExpressions2.addAll(fromTbox);
        }
        existentialExpression.addConcepts(newExpressions2);
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

  public static void print(String message, Set<Expression> expressions) {
    System.out.println(message);
    for (Expression e : expressions) {
      System.out.print("\t");
      System.out.println(e);
    }
  }
}
