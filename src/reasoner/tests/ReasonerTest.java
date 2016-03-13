package reasoner.tests;

import reasoner.Reasoner;
import reasoner.SubsumptionEquivalence;
import reasoner.TBox;
import reasoner.expressions.AndExpression;
import reasoner.expressions.BottomExpression;
import reasoner.expressions.ConceptExpression;
import reasoner.expressions.ExistentialExpression;
import reasoner.expressions.Expression;
import reasoner.expressions.NotExpression;
import reasoner.expressions.UniversalExpression;

import java.lang.Character.Subset;
import java.util.HashSet;
import java.util.Set;

public class ReasonerTest {

	public static void assertAnswer(String query, boolean lhs, boolean rhs) {
		if (lhs != rhs) {
			throw new RuntimeException("Invalid answer for: " + query);
		}
	}

	public static void testManIsSubsetOfPerson() {
		ConceptExpression man = new ConceptExpression("MAN");
		ConceptExpression person = new ConceptExpression("PERSON");

		// Man is a subset of Person
		Set<Expression> manExpressions = new HashSet<>();
		manExpressions.add(person);
		TBox tbox = new TBox();
		tbox.put(man, manExpressions);

		// Is man a subset of Person? Remember to negate the D.
		SubsumptionEquivalence query = new SubsumptionEquivalence(man,
				new NotExpression(person));

		Reasoner reasoner = new Reasoner(tbox, query);
		assertAnswer("Is man a subset of person?", true,
				reasoner.queryIsValid());

	}

	public static void testAnimalSubsetOfPerson() {
		ConceptExpression animal = new ConceptExpression("ANIMAL");
		ConceptExpression person = new ConceptExpression("PERSON");

		// Tbox has no knowledge
		TBox tbox = new TBox();

		// Is animal a subset of Person? Remember to negate the D.
		SubsumptionEquivalence query = new SubsumptionEquivalence(animal,
				new NotExpression(person));

		Reasoner reasoner = new Reasoner(tbox, query);
		assertAnswer("Is animal a subset of person?", false,
				reasoner.queryIsValid());
	}

	public static void testRichMan() {
		TBox tbox = new TBox();
		ConceptExpression man = new ConceptExpression("MAN");
		ConceptExpression rich = new ConceptExpression("RICH");
		AndExpression richAndMan = new AndExpression(rich, man);
		ConceptExpression richMan = new ConceptExpression("RICH_MAN");

		// Richman is equal to richAndMan
		Set<Expression> richManExpressions = new HashSet<>();
		richManExpressions.add(richAndMan);
		tbox.put(richMan, richManExpressions);

		SubsumptionEquivalence query = new SubsumptionEquivalence(richMan,
				new NotExpression(richAndMan));
		Reasoner reasoner = new Reasoner(tbox, query);
		assertAnswer("Is richMan a subset of richAndMan?", true,
				reasoner.queryIsValid());

	}

	public static void teststudents() {
		TBox tbox = new TBox();
		ConceptExpression undergrad = new ConceptExpression(
				"UndergraduateStudents");
		ConceptExpression grad = new ConceptExpression("GraduateStudents");
		AndExpression undergradandgrad = new AndExpression(undergrad, grad);
		ConceptExpression students = new ConceptExpression("Students");

		// Students are equal to undergrad and grad students
		Set<Expression> studentsExpressions = new HashSet<>();
		studentsExpressions.add(undergradandgrad);
		tbox.put(students, studentsExpressions);

		SubsumptionEquivalence query = new SubsumptionEquivalence(students,
				new NotExpression(undergradandgrad));
		Reasoner reasoner = new Reasoner(tbox, query);
		assertAnswer("Are undergrad and grad students subset of students?",
				true, reasoner.queryIsValid());

	}

	public static void fluffyeatsKibbles() {
		TBox tBox = new TBox();
		ConceptExpression fluffy = new ConceptExpression("Fluffy");
		ConceptExpression kibbles = new ConceptExpression("Kibbles");
		ExistentialExpression exst = new ExistentialExpression("eats", kibbles);
		Set<Expression> subExpressions = new HashSet<>();
		subExpressions.add(exst);
		tBox.put(fluffy, subExpressions);
		SubsumptionEquivalence query = new SubsumptionEquivalence(fluffy,
				new NotExpression(exst));
		Reasoner reasoner = new Reasoner(tBox, query);
		assertAnswer("Fluffy eats Kibbles?", true, reasoner.queryIsValid());

	}

	public static void JohneatsonlyFruits() {
		TBox tBox = new TBox();
		ConceptExpression john = new ConceptExpression("John");
		ConceptExpression fruits = new ConceptExpression("Fruits");
		UniversalExpression uni = new UniversalExpression("eats", fruits);
		Set<Expression> subExpressions = new HashSet<>();
		subExpressions.add(uni);
		tBox.put(john, subExpressions);
		SubsumptionEquivalence query = new SubsumptionEquivalence(john,
				new NotExpression(uni));
		Reasoner reasoner = new Reasoner(tBox, query);
		assertAnswer("John eats only Fruits?", true, reasoner.queryIsValid());

	}

	public static void fluffylikesfish() {
		TBox tBox = new TBox();
		ConceptExpression fluffy = new ConceptExpression("Fluffy");
		ConceptExpression fish = new ConceptExpression("Fish");
		ExistentialExpression exst = new ExistentialExpression("likes", fish);
		Set<Expression> subExpressions = new HashSet<>();
		subExpressions.add(exst);
		tBox.put(fluffy, subExpressions);
		SubsumptionEquivalence query = new SubsumptionEquivalence(fluffy,
				new NotExpression(exst));
		Reasoner reasoner = new Reasoner(tBox, query);
		assertAnswer("Fluffy likes Fish?", true, reasoner.queryIsValid());

	}

	public static void fluffylikesGuacomole() {
		TBox tBox = new TBox();
		ConceptExpression fluffy = new ConceptExpression("Fluffy");
		ConceptExpression guacomole = new ConceptExpression("Guacomole");
		ExistentialExpression exst = new ExistentialExpression("likes",
				guacomole);
		Set<Expression> subExpressions = new HashSet<>();
		subExpressions.add(exst);
		tBox.put(fluffy, subExpressions);
		SubsumptionEquivalence query = new SubsumptionEquivalence(fluffy,
				new NotExpression(exst));
		Reasoner reasoner = new Reasoner(tBox, query);
		assertAnswer("Fluffy likes Guacomole?", true, reasoner.queryIsValid());

	}

	public static void BritishgotoBar() {
		TBox tBox = new TBox();
		ConceptExpression british = new ConceptExpression("British");
		ConceptExpression bar = new ConceptExpression("Bar");
		ExistentialExpression exst = new ExistentialExpression("goto", bar);
		Set<Expression> subExpressions = new HashSet<>();
		subExpressions.add(exst);
		tBox.put(british, subExpressions);
		SubsumptionEquivalence query = new SubsumptionEquivalence(british,
				new NotExpression(exst));
		Reasoner reasoner = new Reasoner(tBox, query);
		assertAnswer("British go to bar?", true, reasoner.queryIsValid());

	}

	public static void JohnlikesIcecreams() {
		TBox tBox = new TBox();
		ConceptExpression john = new ConceptExpression("John");
		ConceptExpression icecreams = new ConceptExpression("Icecreams");
		ExistentialExpression exst = new ExistentialExpression("likes",
				icecreams);
		Set<Expression> subExpressions = new HashSet<>();
		subExpressions.add(exst);
		tBox.put(john, subExpressions);
		SubsumptionEquivalence query = new SubsumptionEquivalence(john,
				new NotExpression(exst));
		Reasoner reasoner = new Reasoner(tBox, query);
		assertAnswer("John likes Icecreams?", true, reasoner.queryIsValid());

	}

	public static void BritishersCarryUmbrella() {
		TBox tBox = new TBox();
		ConceptExpression british = new ConceptExpression("British");
		ConceptExpression umbrella = new ConceptExpression("Umbrella");
		ExistentialExpression exst = new ExistentialExpression("carry",
				umbrella);
		Set<Expression> subExpressions = new HashSet<>();
		subExpressions.add(exst);
		tBox.put(british, subExpressions);
		SubsumptionEquivalence query = new SubsumptionEquivalence(british,
				new NotExpression(exst));
		Reasoner reasoner = new Reasoner(tBox, query);
		assertAnswer("British carry umbrellas?", true, reasoner.queryIsValid());

	}

	public static void testjohnIsSubsetofBritish() {
		TBox tbox = new TBox();
		ConceptExpression john = new ConceptExpression("John");
		ConceptExpression british = new ConceptExpression("British");
		Set<Expression> subsetExpressions = new HashSet<>();
		subsetExpressions.add(john);
		tbox.put(british, subsetExpressions);
		SubsumptionEquivalence query = new SubsumptionEquivalence(john,
				new NotExpression(british));
		Reasoner reasoner = new Reasoner(tbox, query);
		assertAnswer("Is john subset of British?", true,
				reasoner.queryIsValid());
	}

	public static void testRichWoman() {
		TBox tbox = new TBox();
		ConceptExpression man = new ConceptExpression("MAN");
		ConceptExpression rich = new ConceptExpression("RICH");
		AndExpression richAndMan = new AndExpression(rich, man);
		ConceptExpression richMan = new ConceptExpression("RICH_MAN");
		// Richman is equal to richAndMan
		Set<Expression> richManExpressions = new HashSet<>();
		richManExpressions.add(richAndMan);
		tbox.put(richMan, richManExpressions);

		ConceptExpression richWoman = new ConceptExpression("RICH_WOMAN");
		AndExpression richAndNotRichMan = new AndExpression(rich,
				new NotExpression(richAndMan));
		// Richwoman is equal to rich and not richMan
		Set<Expression> richWomanExpressions = new HashSet<>();
		richWomanExpressions.add(richAndNotRichMan);
		tbox.put(richWoman, richWomanExpressions);

		SubsumptionEquivalence query = new SubsumptionEquivalence(richWoman,
				new NotExpression(rich));
		Reasoner reasoner = new Reasoner(tbox, query);
		assertAnswer("Is richWoman a subset of rich?", true,
				reasoner.queryIsValid());

		query = new SubsumptionEquivalence(richWoman, new NotExpression(man));
		reasoner = new Reasoner(tbox, query);
		assertAnswer("Is richWoman a subset of man?", false,
				reasoner.queryIsValid());
	}

}
