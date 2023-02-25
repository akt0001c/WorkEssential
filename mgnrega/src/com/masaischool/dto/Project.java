package com.masaischool.dto;

import java.time.LocalDate;

public interface Project {
    
	
	public String getPid();
	public void setPid(String pid);
	public String getPname();
	public void setPname(String pname);
	public LocalDate getStartDate();
	public void setStartDate(LocalDate startDate);
	public GPMember getGpm();
	public void setGpm(GPMember gpm);
}
