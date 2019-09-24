package entity;

import java.sql.Timestamp;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Student {
	private int id;
	private String name;
	private int sex;
	private int age;
	private String username;
	private String password;
	@JSONField(format="yyyy-MM-dd")
	private Date brithday;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp creaTime;
	
	public Student(int id, String name, int sex, int age, String username, String password, Date brithday,
			Timestamp creaTime) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.username = username;
		this.password = password;
		this.brithday = brithday;
		this.creaTime = creaTime;
	}
	public Student() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBrithday() {
		return brithday;
	}
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	public Timestamp getCreaTime() {
		return creaTime;
	}
	public void setCreaTime(Timestamp creaTime) {
		this.creaTime = creaTime;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex + ", age=" + age + ", username=" + username
				+ ", password=" + password + ", brithday=" + brithday + ", creaTime=" + creaTime + "]";
	}
	
}
