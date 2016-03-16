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
    addToTBox(line, convertToExpression(line));
  }

  private Expression convertToExpression(String line) {
    String[] tokens1 = line.split(" ");
    ArrayList<String> tokens =  new ArrayList<>(Arrays.asList(tokens1));

    if(tokens.size() < MINIMUM_AMOUNT_OF_TOKENS) {
      System.out.println("not enough tokens");
      return null;
    }

    String keyword = tokens.get(1);
    ConceptExpression c = new ConceptExpression(tokens.get(0));
    if(!tokens.get(1).equals("equivalent") && !tokens.get(1).equals("subset")){
      //illegal syntax
      System.out.println("Second word must be equivalent or subset");
      return null;
    }

    return parse(tokens.subList(2, tokens.size()));
  }

  private void addToTBox(String line, Expression d) {

    if (d == null) {
      return;
    }
    String[] tokens1 = line.split(" ");
    ArrayList<String> tokens =  new ArrayList<>(Arrays.asList(tokens1));
    ConceptExpression c = new ConceptExpression(tokens.get(0));

    //check if c has already been introduced
    Set<Expression> allD = tbox.get(c);
    if (allD == null) { // C is not in the TBox
      allD = new HashSet<>();
    }
    allD.add(d);
    tbox.put(c, allD);
    System.out.println(tbox);
  }



  public Expression parse(List<String> expression) {
    int unionIndex = expression.indexOf("union");
    int intersectIndex = expression.indexOf("intersect");
    if (unionIndex == DOES_NOT_EXIST && intersectIndex == DOES_NOT_EXIST) {
      String keyword = expression.get(0);
      if (keyword.equals("top")) {
        return new TopExpression();
      }
      if (keyword.equals("bottom")) {
        return new BottomExpression();
      }
      if (keyword.equals("exists") || keyword.equals("forall")) {
        String role = expression.get(INDEX_OF_ROLE);
        List<String> concept = expression.subList(INDEX_OF_UE_CONCEPT, expression.size());
        Expression expr = parse(concept);
        if (keyword.equals("exists")) {
          return new ExistentialExpression(role, expr);
        } else if (keyword.equals("forall")) {
          return new UniversalExpression(role, expr);
        }
      }
      else if (keyword.equals("lessthanorequalto")) {
        return new LessThanOrEqualToExpression(expression.get(3),
                Integer.parseInt(expression.get(1)));
      }
      else if (keyword.equals("greaterthanorequalto")) {
        return new GreaterThanOrEqualToExpression(expression.get(3),
                Integer.parseInt(expression.get(1)));
      }
      else {
        String first = expression.get(0);
        if (first.equals("not")) {
          return new NotExpression(new ConceptExpression(expression.get(1)));
        }
        else {
          return new ConceptExpression(first);
        }
      }
    }
    else if (unionIndex == DOES_NOT_EXIST) {
      Expression lhs = parse(expression.subList(0, intersectIndex));
      Expression rhs = parse(expression.subList(intersectIndex + 1, expression.size()));
      return new IntersectExpression(lhs, rhs);
    }
    else if (intersectIndex == DOES_NOT_EXIST) {
      Expression lhs = parse(expression.subList(0, unionIndex));
      Expression rhs = parse(expression.subList(unionIndex + 1, expression.size()));
      return new UnionExpression(lhs, rhs);
    }
    else if (intersectIndex < unionIndex) {
      Expression lhs = parse(expression.subList(0, intersectIndex));
      Expression rhs = parse(expression.subList(intersectIndex + 1, expression.size()));
      return new IntersectExpression(lhs, rhs);
    }
    else {
      Expression lhs = parse(expression.subList(0, unionIndex));
      Expression rhs = parse(expression.subList(unionIndex + 1, expression.size()));
      return new UnionExpression(lhs, rhs);
    }
  return null;
  }



  public String negateD(String d) {
    int unionIndex = d.indexOf("union");
    int intersectIndex = d.indexOf("intersect");

    if (unionIndex == DOES_NOT_EXIST && intersectIndex == DOES_NOT_EXIST) {
      String keyword = d.substring(0, d.indexOf(" ")); //get first keyword

      if (keyword.equals("exists") || keyword.equals("forall")) {
        int dotIndex = d.indexOf("dot");
        String lhs = d.substring(0, dotIndex+2); //gets the "dot" and the space
        String concept = d.substring(dotIndex+2, d.length());

        String expr = negateD(concept);

        d = lhs + expr;
        if (keyword.equals("exists")) {
          return d.replaceAll("exists", "forall");
        }
        else if (keyword.equals("forall")) {
          return d.replaceAll("forall", "exists");
        }
      }
      else if (keyword.equals("lessthanorequalto")) {
        return d.replaceAll("lessthanorequalto", "greaterthanorequalto");
      }
      else if (keyword.equals("greaterthanorequalto")) {
        return d.replaceAll("greaterthanorequalto", "lessthanorequalto");
      }
      else if (keyword.equals("top")) {
        return d;
      }
      else if (keyword.equals("bottom")) {
        return d;
      }
      else {
        String first = d.substring(0, d.indexOf(" "));

        if (first.equals("not")) {
          return "not " + d;
        }
        else {
          return d;
        }
      }
    }
    else {
      if (unionIndex == DOES_NOT_EXIST) {
        String lhs = negateD(d.substring(0, intersectIndex));
        String rhs = negateD(d.substring(intersectIndex + 1, d.length()));

        return lhs + "union" + rhs;
      }
      else if (intersectIndex == DOES_NOT_EXIST) {
        String lhs = negateD(d.substring(0, unionIndex));
        String rhs = negateD(d.substring(unionIndex + 1, d.length()));

        return lhs + "intersect" + rhs;
      }
      else if (intersectIndex < unionIndex) {
        String lhs = negateD(d.substring(0, intersectIndex));
        String rhs = negateD(d.substring(intersectIndex + 1, d.length()));

        return lhs + "union" + rhs;
      }
      else if (unionIndex < intersectIndex) {
        String lhs = negateD(d.substring(0, unionIndex));
        String rhs = negateD(d.substring(unionIndex + 1, d.length()));

        return lhs + "intersect" + rhs;
      }
    }
    return  null;
  }

//
//  public SubsumptionEquivalence parseQuery(String line) {
//
//  }

  public TBox getTBox() {
    return this.tbox;
  }

}//end Parser




