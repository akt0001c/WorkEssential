package com.masaischool.dto;

import java.util.Objects;

public class EmployeeImpl implements Employee {

	private String empId;
	private String empName;
	private Project project;
	private int no_of_days;
	private int wages;
	
	public EmployeeImpl(){}
	public EmployeeImpl(String id,String name,int nodays,int wag,Project p){
		empId=id;
		empName=name;
		no_of_days=nodays;
		wages=wag;
		project=p;
	}
	
	@Override
	public String getEmpId() {
		return empId;
	}
	
	
	@Override
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	@Override
	public String getEmpName() {
		return empName;
	}
	
	@Override
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	@Override
	public Project getProject() {
		return project;
	}
	
	@Override
	public void setProject(Project project) {
		this.project = project;
	}
	
	@Override
	public int getNo_of_days() {
		return no_of_days;
	}
	
	@Override
	public void setNo_of_days(int no_of_days) {
		this.no_of_days = no_of_days;
	}
	
	@Override
	public int getWages() {
		return wages;
	}
	
	@Override
	public void setWages(int wages) {
		this.wages = wages;
	}
	
	
	@Override
	public String toString() {
		return "Employee Id=" + empId + ", empName=" + empName + "\n";
	}
	@Override
	public int hashCode() {
		return Objects.hash(empId, empName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeImpl other = (EmployeeImpl) obj;
		return Objects.equals(empId, other.empId) && Objects.equals(empName, other.empName);
	}
	
	
	
	
	
	
	
}
