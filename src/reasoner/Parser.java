package reasoner;

import reasoner.expressions.*;

import java.util.*;
import java.lang.String;

/**
 * Parser takes a line of input from System.in and turns it into an expression
 * and adds it to the TBox
 *
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public class Parser {

  TBox tbox;
  private final static int MINIMUM_AMOUNT_OF_TOKENS = 3;
  private final static int DOES_NOT_EXIST = -1;
  private final static int INDEX_OF_ROLE = 1;
  private final static int INDEX_OF_UE_CONCEPT = 3;

 /**
  * Returns a Parser object to parse through the lines of knowledge and the 
  * query
  *
  * @return the Parser to parse through the strings
  */
  public Parser() {
    tbox = new TBox();
  }

 /**
  * Parses through an expression, represented as a string, and adds it to the 
  * TBox. Called only when parsing through knowledge and not the query
  *
  * @param line the line to be parsed
  */
  public void parseLine(String line) {
    addToTBox(line, convertToExpression(line));
  }

 /**
  * Converts the expression, represented as a string, into an Expression object,
  * which will be used to evaluate the query
  *
  * @param line the line to be converted into an Expression object
  * @return     the Expression object that represents the expression
  */
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

 /**
  * Adds an expression to the TBox. @param line and @param d represent the same
  * expression, but in different forms. line is a String object and d is an 
  * Expression object
  *
  * @param line the expression, as a String, to be added to the TBox
  * @param d    the expression, as an Expression object, to be added to the TBox
  */
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

 /**
  * Takes in an expression as a List of String and then goes through each toekn
  * in the list returns an Expression that represents the expression
  *
  * @param expression the string to be converted into an Expression
  * @return           the Expression that represents the List of String
  */
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
        } 
        else if (keyword.equals("forall")) {
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

 /**
  * Parses the query and returns a SubsumptionEquivalence to be evaluated 
  *
  * @param line the string that holds the query
  * @return     the SubsumptionEquivalence that holds the query
  */
  public SubsumptionEquivalence parseQuery(String line) {
    String[] tokens1 = line.split(" ");
    ArrayList<String> tokens =  new ArrayList<>(Arrays.asList(tokens1));

    SubsumptionEquivalence se =
            new SubsumptionEquivalence(new ConceptExpression(tokens.get(0)), convertToExpression(line));
    return se.negateRhs();
  }
}




