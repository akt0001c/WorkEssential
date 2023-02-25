package com.masaischool.dto;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class GPMemberImpl implements GPMember {
    Set<Project> sets;
    private String Mname;
    private char gender;
    private int age;
    private LocalDate joinDate;
    private String username;
    private String password;
    
    
	public String getMname() {
		return Mname;
	}
	public void setMname(String mname) {
		Mname = mname;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public LocalDate getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
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
	@Override
	public String toString() {
		return "GPMember name=" + Mname + ", gender=" + gender + ", age=" + age + ", joinDate=" + joinDate + "\n";
	}
	@Override
	public int hashCode() {
		return Objects.hash(Mname, age, gender);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GPMemberImpl other = (GPMemberImpl) obj;
		return Objects.equals(Mname, other.Mname) && age == other.age && gender == other.gender;
	}
	
	
    
    
}
