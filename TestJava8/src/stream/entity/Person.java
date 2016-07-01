package stream.entity;


public class Person {

	public enum Sex { FEMALE, MALE}

	private int age;

	private Sex sex;

	private String name;


	public Person(int age, Sex sex, String name) {
		super();
		this.age = age;
		this.sex = sex;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public void execSlowJob() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


