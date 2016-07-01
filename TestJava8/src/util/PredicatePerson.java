package util;

import stream.application.base.dto.Condition;
import stream.entity.Person;

public class PredicatePerson {

	private final Condition condition;
	private final Range range;

	public PredicatePerson(Condition condition) {
		this.condition = condition;
		this.range = new Range(condition.getLow_age(), condition.getHigh_age());
	}
	public boolean test(Person person) {
		if(condition.getSex() != null && condition.getSex() != person.getSex()) {
			return false;
		}
		if(!range.isInRange(person.getAge())) {
			return false;
		}
		return true;
	}
}
