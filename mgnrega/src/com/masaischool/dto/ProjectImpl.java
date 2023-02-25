package com.masaischool.dto;

import java.time.LocalDate;
import java.util.Objects;

public class ProjectImpl implements Project {
    
	private String pid;
	private String pname;
	private LocalDate startDate;
	GPMember gpm;
	
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public GPMember getGpm() {
		return gpm;
	}
	public void setGpm(GPMember gpm) {
		this.gpm = gpm;
	}
	
	
	@Override
	public String toString() {
		return "Project  id=" + pid + ", Project Name=" + pname + ", start Date=" + startDate + ", AssignTo=" + gpm + "\n";
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(pid, pname);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectImpl other = (ProjectImpl) obj;
		return Objects.equals(pid, other.pid) && Objects.equals(pname, other.pname);
	}
	
	
	
	
}
