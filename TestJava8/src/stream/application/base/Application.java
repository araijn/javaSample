package stream.application.base;

import java.util.List;

import stream.application.base.dto.Condition;
import stream.entity.Person;
import util.PredicatePerson;


public interface Application {

	public List<Person> collect(Condition condition) ;

	public List<Person> collect(PredicatePerson predicatePerson) ;

	public void doJob(PredicatePerson predicatePerson) ;

	public int maxAge(PredicatePerson predicatePerson);
}
