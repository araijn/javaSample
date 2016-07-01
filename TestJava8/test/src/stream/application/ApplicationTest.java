package stream.application;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import stream.application.anonymous.AnonymousApplication;
import stream.application.base.Application;
import stream.application.base.dto.Condition.Builder;
import stream.application.java8.Java8Application;
import stream.application.legacy.LegacyApplication;
import stream.entity.Person;
import util.PredicatePerson;

public class ApplicationTest {

	@Test
	public void test_legacyApplication() throws Exception {

		testSuite(new LegacyApplication(createTestList()));

	}

	@Test
	public void test_anonymousApplicationApplication() throws Exception {

		testSuite(new AnonymousApplication(createTestList()));

	}

	@Test
	public void test_java8ApplicationApplication() throws Exception {

		testSuite(new Java8Application(createTestList()));

	}

	protected void testSuite(Application testTarget) throws Exception {
		test_collect(testTarget);
		test_collect_withPredicatePerson(testTarget);
		test_doJob(testTarget);
		test_maxAge(testTarget);
	}
	protected void test_collect(Application testTarget) throws Exception {

		assertList(Arrays.asList("Bab", "May", "Tony", "Ruka", "Juny", "Takuto", "Genan", "Kin"),
				testTarget.collect((new Builder()).build()));
		assertList(Arrays.asList("Bab", "Tony", "Takuto", "Genan"),
				testTarget.collect((new Builder()).sex(Person.Sex.MALE).build()));
		assertList(Arrays.asList("Bab", "May", "Tony", "Ruka", "Juny"),
				testTarget.collect((new Builder()).high_age(10).build()));
		assertList(Arrays.asList("Genan", "Kin"),
				testTarget.collect((new Builder()).low_age(99).build()));
		assertList(Arrays.asList("Takuto"),
				testTarget.collect((new Builder()).high_age(19).low_age(10).sex(Person.Sex.MALE).build()));
	}

	protected void test_collect_withPredicatePerson(Application testTarget) throws Exception {

		assertList(Arrays.asList("Bab", "May", "Tony", "Ruka", "Juny", "Takuto", "Genan", "Kin"),
				testTarget.collect(new PredicatePerson((new Builder()).build())));
		assertList(Arrays.asList("Bab", "Tony", "Takuto", "Genan"),
				testTarget.collect(new PredicatePerson((new Builder()).sex(Person.Sex.MALE).build())));
		assertList(Arrays.asList("Bab", "May", "Tony", "Ruka", "Juny"),
				testTarget.collect(new PredicatePerson((new Builder()).high_age(10).build())));
		assertList(Arrays.asList("Genan", "Kin"),
				testTarget.collect(new PredicatePerson((new Builder()).low_age(99).build())));
		assertList(Arrays.asList("Takuto"),
				testTarget.collect(new PredicatePerson((new Builder()).high_age(19).low_age(10).sex(Person.Sex.MALE).build())));
	}

	protected void test_doJob(Application testTarget) throws Exception {
		long startTime = System.currentTimeMillis();

//		testTarget.doJob(new PredicatePerson((new Builder()).sex(Person.Sex.MALE).build()));
		testTarget.doJob(new PredicatePerson((new Builder()).build()));
		System.out.println(testTarget.getClass() + ":"  + (System.currentTimeMillis() - startTime));
	}

	protected void test_maxAge(Application testTarget) throws Exception {
		assertEquals(120, testTarget.maxAge(new PredicatePerson((new Builder()).build())));
		assertEquals(99, testTarget.maxAge(new PredicatePerson((new Builder()).sex(Person.Sex.MALE).build())));
		assertEquals(10, testTarget.maxAge(new PredicatePerson((new Builder()).high_age(10).build())));
		assertEquals(120, testTarget.maxAge(new PredicatePerson((new Builder()).low_age(99).build())));
		assertEquals(19, testTarget.maxAge(new PredicatePerson((new Builder()).high_age(19).low_age(10).sex(Person.Sex.MALE).build())));
	}

	protected List<Person> createTestList() {
		List<Person> testList = new ArrayList<Person>();
		testList.add(new Person(0, Person.Sex.MALE, "Bab"));
		testList.add(new Person(0, Person.Sex.FEMALE, "May"));
		testList.add(new Person(1, Person.Sex.MALE, "Tony"));
		testList.add(new Person(9, Person.Sex.FEMALE, "Ruka"));
		testList.add(new Person(10, Person.Sex.FEMALE, "Juny"));
		testList.add(new Person(19, Person.Sex.MALE, "Takuto"));
		testList.add(new Person(99, Person.Sex.MALE, "Genan"));
		testList.add(new Person(120, Person.Sex.FEMALE, "Kin"));

		return testList;
	}

	protected void assertList(List<String> expectedNames, List<Person> actualList)  throws Exception {

		assertEquals(expectedNames.size(), actualList.size());
		Set<String> expetedNames = new HashSet<String>(expectedNames);
		Set<String> actualNames = new HashSet<String>();
		for(Person person : actualList) {
			actualNames.add(person.getName());
		}

		assertTrue(expetedNames.containsAll(actualNames));

	}
}
