package com.masaischool.dto;

public interface Employee {
	public String getEmpId();
	public void setEmpId(String empId);
	public String getEmpName();
	public void setEmpName(String empName);
	public Project getProject() ;
	public void setProject(Project project);
	public int getNo_of_days();
	public void setNo_of_days(int no_of_days) ;
	public int getWages();
	public void setWages(int wages) ;
}
