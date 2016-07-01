package stream.application.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import stream.application.base.Application;
import stream.application.base.dto.Condition;
import stream.entity.Person;
import util.PredicatePerson;
import util.Range;

public class Java8Application implements Application {

	private final List<Person> personList = new ArrayList<Person>();

	public Java8Application() {}
	public Java8Application(List<Person> srcList) {
		personList.addAll(srcList);
	}

	@Override
	public List<Person> collect(Condition condition) {

		Range range = new Range(condition.getLow_age(), condition.getHigh_age());

		return personList.stream().filter(
				/* 変数 -> {} の形でラムダ式を定義 return一文だけの場合はreturnも省略可 */
				person -> {
					if(condition.getSex() != null && condition.getSex() != person.getSex()) {
						return false;
					}
					if(!range.isInRange(person.getAge())) {
						return false;
					}
					return true;
				}).collect(Collectors.toList());

	}

	public List<Person> collect(PredicatePerson predicatePerson) {

		return personList.stream()
				.filter(person -> predicatePerson.test(person))    /* ラムダ式による省略記法 */
				.collect(Collectors.toList());
	}

	public void doJob(PredicatePerson predicatePerson) {
		/* parallelStreamではfilterの結果を並列で実行する */
		personList.parallelStream()
			.filter(person -> predicatePerson.test(person))
			.forEach(person -> person.execSlowJob());

		/* 同時実行数を制御したい場合
		 * ForkJoinPool(2)は実行するスレッド数が2という意味ではないが上限制限はされる模様
		 * 共通プールが利用可能のときはそれも使っているみたい
		 */

//		ForkJoinPool forkJoinPool = new ForkJoinPool(2);
//		forkJoinPool.execute(() ->
//			personList.parallelStream()
//				.filter(person -> predicatePerson.test(person))
//				.forEach(person -> person.execSlowJob()));
//
//		while (!forkJoinPool.awaitQuiescence(30, TimeUnit.SECONDS));

	}

	public int maxAge(PredicatePerson predicatePerson) {
		return personList.stream()
				.filter(person -> predicatePerson.test(person))  /* 条件で絞り込み*/
				.mapToInt(person -> person.getAge())             /* int型 Streamへ変換 */
				.max()                                           /* 最大値を取得 */
				.getAsInt();                                     /* Optional<T>でラップされている */
	}
}
