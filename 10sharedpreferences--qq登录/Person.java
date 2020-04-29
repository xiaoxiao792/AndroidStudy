package cn.itcast.serialize;

public class Person {
	private String name;
	private Integer age;
	private Integer score;
	public Person(String name, Integer age, Integer score) {
		super();
		this.name = name;
		this.age = age;
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", score=" + score
				+ "]";
	}

}
