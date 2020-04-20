package cn.itcast.domain;

import java.io.Serializable;

//实现序列化方便传递数据
public class ItemInfo implements Serializable{
	private String name;
	private int acctack;
	private int life;
	private int speed;
	public ItemInfo(String name, int acctack, int life, int speed) {
		this.name = name;
		this.acctack = acctack;
		this.life = life;
		this.speed = speed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAcctack() {
		return acctack;
	}
	public void setAcctack(int acctack) {
		this.acctack = acctack;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public String toString() {
		return " [name=" + name + ", acctack=" + acctack + ", life=" + life
				+ ", speed=" + speed + "]";
	}
}
