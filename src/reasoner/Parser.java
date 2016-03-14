package reasoner;

import reasoner.expressions.*;

import java.util.*;
import java.lang.String;


//{equivalent, subset, intersect, not, all, exists}
public class Parser {

  TBox tbox;
  private final static int MINIMUM_AMOUNT_OF_TOKENS = 3;
  private final static int DOES_NOT_EXIST = -1;
  private final static int INDEX_OF_ROLE = 1;
  private final static int INDEX_OF_UE_CONCEPT = 3;

  public Parser() {
    tbox = new TBox();
  }

  public void parseLine(String line) {
    String[] tokens1 = line.split(" ");
    ArrayList<String> tokens =  new ArrayList<>(Arrays.asList(tokens1));

    if(tokens.size() < MINIMUM_AMOUNT_OF_TOKENS){
      System.out.println("not enough tokens");
      return;
    }

    String keyword = tokens.get(1);
    ConceptExpression c = new ConceptExpression(tokens.get(0));
    if(!tokens.get(1).equals("equivalent") && !tokens.get(1).equals("subset")){
      //illegal syntax
      System.out.println("Second word must be equivalent or subset");
      return;
    }

    Expression d = parse(tokens.subList(2, tokens.size()));
    //check if c has already been introduced
    Set<Expression> allD = tbox.get(c);
    if (allD == null) { // C is not in the TBox
      allD = new HashSet<>();
    }
    allD.add(d);
    tbox.put(c, allD);
    System.out.println(tbox);

  }//end parseLine

  public Expression parse(List<String> expression) {
    int unionIndex = expression.indexOf("union");
    int intersectIndex = expression.indexOf("intersect");

    if (unionIndex == DOES_NOT_EXIST && intersectIndex == DOES_NOT_EXIST) {
      String keyword = expression.get(0);

      if (keyword.equals("exists") || keyword.equals("forall")) {
        String role = expression.get(INDEX_OF_ROLE);
        List<String> concept = expression.subList(INDEX_OF_UE_CONCEPT, expression.size());

        String first = concept.get(0);
        Expression expr = null;

        if (first.equals("not")) {
          expr = new NotExpression(new ConceptExpression(concept.get(1)));
        } else {
          expr = new ConceptExpression(first);
        }

        if (keyword.equals("exists")) {
          return new ExistentialExpression(role, expr);
        } else if (keyword.equals("forall")) {
          return new UniversalExpression(role, expr);
        }
      }
    }
    else {
      Expression lhs = parse(expression.subList(0, unionIndex));
      Expression rhs = parse(expression.subList(unionIndex + 1, expression.size()));

      if (unionIndex == DOES_NOT_EXIST) {
        System.out.println("first");
        return new IntersectExpression(lhs, rhs);
      }
      else if (intersectIndex == DOES_NOT_EXIST) {
        System.out.println("second");
        return new UnionExpression(lhs, rhs);
      }
      else if (intersectIndex < unionIndex) {
        System.out.println("third");
        return new IntersectExpression(lhs, rhs);
      }
      else if (unionIndex < intersectIndex) {
        System.out.println("third");

        return new UnionExpression(lhs, rhs);
      }
    }

    return new ConceptExpression("hi");
  }


//    else if {
//
//    }
//    else {
//      return new ConceptExpression()
//    }

  }

//  public Expression parse(List<String> expression) {
//    String keyword = expression.get(0);
//    String role = expression.get(INDEX_OF_ROLE);
//    List<String> concept = expression.subList(INDEX_OF_UE_CONCEPT, expression.size());
//
//    String first = concept.get(0);
//    Expression expr = null;
//
//    if (first.equals("not")) {
//      expr = new NotExpression(new ConceptExpression(concept.get(1)));
//    } else {
//      expr = new ConceptExpression(first);
//    }
//
//    System.out.println(expression.get(0));
//    if (keyword.equals("exists")) {
//      return new ExistentialExpression(role, expr);
//    }
//    else if (keyword.equals("forall")) {
//      return new UniversalExpression(role, expr);
//    }
//    else {
//      return new ConceptExpression("not implemented");
//    }
//  }
//  private Expression parse(List<String> tokens) {
//
//    String first = tokens.get(0);
//    int keywordIndex = 1;
//    NotExpression n = null;
//
////    todo - check for end cases
//    if (first.equals("not")) {
//      n = new NotExpression(new ConceptExpression(tokens.get(keywordIndex)));
//      ++keywordIndex;
//    }
//
//    String keyword = tokens.get(keywordIndex);
//
//    if (keyword.equals("intersect")) {
//      if (n != null) {
//        return new IntersectExpression(n,
//                parse(tokens.subList(keywordIndex + 1), tokens.size()));
//      } else {
//        return new IntersectExpression(new ConceptExpression(tokens.get(keywordIndex - 1)),
//                parse(tokens.subList(keywordIndex + 1), tokens.size()));
//      }
//    }
//    else if (keyword.equals("union")) {
//      if (n != null) {
//        return new UnionExpression(n,
//                parse(tokens.subList(keywordIndex + 1), tokens.size()));
//      } else {
//        return new UnionExpression(new ConceptExpression(tokens.get(keywordIndex - 1)),
//                parse(tokens.subList(keywordIndex + 1), tokens.size()));
//      }
//    }
//    else if (keyword.equals("existential")) {
//
//    }
//    else if (keyword.equals("universal")) {
//
//    }
//    else {
//      return new SubsumptionEquivalence(new ConceptExpression(tokens.get(0)),
//              parse(tokens.subList(index + 1)), tokens.size());
//    }
//
//  }//end parse


//  public TBox getTBox() {
//    return this.tbox;
//  }

//  }




