package com.masaischool.dto;

import java.time.LocalDate;

public interface GPMember {

	public String getMname();
	public void setMname(String mname);
	public String getGender();
	public void setGender(String gender);
	public int getAge();
	public void setAge(int age);
	public LocalDate getJoinDate();
	public void setJoinDate(LocalDate joinDate);
	public String getUsername();
	public void setUsername(String username);
	public String getPassword();
	public void setPassword(String password);
}
