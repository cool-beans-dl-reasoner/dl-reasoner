package reasoner;

import reasoner.expressions.Expression;

import java.util.HashSet;
import java.util.Set;

public class Reasoner {

  public TBox tbox;
  public SubsumptionEquivalence query;

  public Reasoner(TBox tbox, SubsumptionEquivalence query) {
    this.tbox = tbox;
    this.query = query;
  }

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

  public boolean expand(Set<Expression> expressions) {
    int originalSize = expressions.size();

    for (Expression expression : expressions) {
      Set<Expression> equivalentOrSubsumptions = tbox.get(expression);
      if (equivalentOrSubsumptions == null) {
        equivalentOrSubsumptions = new HashSet<>();
      }
      expressions.addAll(equivalentOrSubsumptions);
      tbox.put(expression, equivalentOrSubsumptions);
    }
    return originalSize != expressions.size();
  }

  public static void print(String message, Set<Expression> expressions) {
    System.out.println(message);
    for (Expression e : expressions) {
      System.out.print("\t");
      System.out.println(e);
    }
  }

  public boolean queryIsValid() {
    Set<Expression> cExpanded = new HashSet<>();
    Set<Expression> dExpanded = new HashSet<>();
    cExpanded.add(query.lhs);
    dExpanded.add(query.rhs);

    boolean databaseUpdated;
    boolean foundContradiction;
    do {
      databaseUpdated = false;
      foundContradiction = hasContradiction(cExpanded, dExpanded);
      if (!foundContradiction) {
        // Expand C and D
        databaseUpdated |= expand(cExpanded);
        databaseUpdated |= expand(dExpanded);

//        print(tbox);
//        print("C", cExpanded);
//        print("D", dExpanded);
      }
    } while (!foundContradiction && databaseUpdated);

    if (foundContradiction) {
      return true;
    }
    return false;
  }
}
