package stream.application.base.dto;

import stream.entity.Person.Sex;

public class Condition {

	private final Integer low_age;

	private final Integer high_age;

	private final Sex sex;

	private Condition(Builder builder) {
		this.low_age = builder.low_age;
		this.high_age = builder.high_age;
		this.sex = builder.sex;
	}
	public static class Builder {
		private  Integer low_age;
		private  Integer high_age;
		private  Sex sex;

		public Builder() {}
		public Builder low_age(int low) { this.low_age = low; return this; }
		public Builder high_age(int high) { this.high_age = high; return this; }
		public Builder sex(Sex sex) { this.sex = sex; return this; }
		public Condition build() { return new Condition(this); }
	}

	public Integer getLow_age() {
		return low_age;
	}
	public Integer getHigh_age() {
		return high_age;
	}
	public Sex getSex() {
		return sex;
	}

}
