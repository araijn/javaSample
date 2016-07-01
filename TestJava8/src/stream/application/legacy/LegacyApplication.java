package stream.application.legacy;

import java.util.ArrayList;
import java.util.List;

import stream.application.base.Application;
import stream.application.base.dto.Condition;
import stream.entity.Person;
import util.PredicatePerson;
import util.Range;

public class LegacyApplication implements Application{

	private final List<Person> personList = new ArrayList<Person>();

	public LegacyApplication() {}
	public LegacyApplication(List<Person> srcList) {
		personList.addAll(srcList);
	}

	public List<Person> collect(Condition condition) {
		List<Person>  resultList= new ArrayList<Person>();

		Range range = new Range(condition.getLow_age(), condition.getHigh_age());
		for (Person person : personList) {
			if(condition.getSex() != null && condition.getSex() != person.getSex()) {
				continue;
			}
			if(!range.isInRange(person.getAge())) {
				continue;
			}
			resultList.add(person);
		}
		return resultList;
	}

	public List<Person> collect(PredicatePerson predicatePerson) {
		List<Person>  resultList= new ArrayList<Person>();
		for (Person person : personList) {
			if(predicatePerson.test(person)) {
				resultList.add(person);
			}
		}

		return resultList;
	}

	public void doJob(PredicatePerson predicatePerson) {
		List<Person> targetList = this.collect(predicatePerson);
		for (Person person : targetList) {
			person.execSlowJob();
		}
	}

	public int maxAge(PredicatePerson predicatePerson){
		List<Person> targetList = this.collect(predicatePerson);
		int max = 0;
		for(Person person: targetList) {
			if(max < person.getAge()) {
				max = person.getAge();
			}
		}

		return max;
	}
}
