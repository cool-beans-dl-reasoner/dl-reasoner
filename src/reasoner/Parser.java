package reasoner;

import reasoner.expressions.AndExpression;
import reasoner.expressions.ConceptExpression;
import reasoner.expressions.Expression;
import reasoner.expressions.NotExpression;

import java.lang.reflect.Array;
import java.util.*;


//{equivalent, subset, intersect, not, all, exists}
public class Parser {

  TBox tbox;
  private final static int MINIMUM_AMOUNT_OF_TOKENS = 3;

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

    ConceptExpression c = new ConceptExpression(tokens.get(0));
    if(!tokens.get(1).equals("equivalent") && !tokens.get(1).equals("subset")){
      //illegal syntax
      System.out.println("Second word must be equivalent or subset");
      return;
    }

    Expression d = parse(tokens);
    //check if c has already been introduced
    Set<Expression> allD = tbox.get(c);
    if (allD == null) { // C is not in the TBox
      allD = new HashSet<>();
    }
    allD.add(d);
    tbox.put(c, allD);
    System.out.println(tbox);

  }//end parseLine

  private Expression parse(List<String> tokens){

    String first = tokens.get(0);
    int keywordIndex = 1;


//    todo - check for end cases
    if (first.equals("not")) {
      NotExpression n = new NotExpression(new ConceptExpression(tokens.get(keywordIndex)));
      ++keywordIndex;
    }

    String keyword = tokens.get(keywordIndex);

// bug - fix not expression
    if (keyword.equals("intersect")) {
      return new AndExpression(new ConceptExpression(tokens.get(keywordIndex - 1)),
              parse(tokens.subList(keywordIndex + 1)));
    }
    else if (keyword.equals("union")) {
      return new AndExpression(new ConceptExpression(tokens.get(keywordIndex - 1)),
              parse(tokens.subList(keywordIndex + 1)));
    }
    else {
      return new SubsumptionEquivalence(ConceptExpression(tokens.get(0)),
              parse(tokens.subList(index + 1)));
    }

  }//end parse


  public TBox getTBox() {
    return this.tbox;
  }

}
