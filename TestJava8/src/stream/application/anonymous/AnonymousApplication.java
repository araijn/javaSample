package stream.application.anonymous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import stream.application.base.Application;
import stream.application.base.dto.Condition;
import stream.entity.Person;
import util.PredicatePerson;
import util.Range;

public class AnonymousApplication implements Application {

	private final List<Person> personList = new ArrayList<Person>();

	public AnonymousApplication() {}
	public AnonymousApplication(List<Person> srcList) {
		personList.addAll(srcList);
	}

	@Override
	public List<Person> collect(Condition condition) {

		Range range = new Range(condition.getLow_age(), condition.getHigh_age());

		return Arrays.asList(personList.stream().filter(
				new Predicate<Person>() {

					public boolean test(Person person) {
						if(condition.getSex() != null && condition.getSex() != person.getSex()) {
							return false;
						}
						if(!range.isInRange(person.getAge())) {
							return false;
						}
						return true;
					}

				}).toArray(Person[]::new));

	}

	public List<Person> collect(PredicatePerson predicatePerson) {

		return Arrays.asList(personList.stream().filter(
				/* オーバライドするメソッドが一つである場合、ラムダ式で定義できる → Java8Applicationの方を参照 */
				new Predicate<Person>() {

					public boolean test(Person person) {
						return predicatePerson.test(person);
					}
				}).toArray(Person[]::new));
	}

	public void doJob(PredicatePerson predicatePerson) {
		personList.stream().filter(
				new Predicate<Person>() {

					public boolean test(Person person) {
						return predicatePerson.test(person);
					}
				}).forEach(
				new Consumer<Person>() {

					public void accept(Person person) {
						person.execSlowJob();
					}
				});

	}

	public int maxAge(PredicatePerson predicatePerson) {
		return personList.stream().filter(
				new Predicate<Person>() {

					public boolean test(Person person) {
						return predicatePerson.test(person);
					}
				}).mapToInt(
				new ToIntFunction<Person>() {

					public int applyAsInt(Person person){
						return person.getAge();
					}
				}
			    ).max()
			     .getAsInt();
	}
}
