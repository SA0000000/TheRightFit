package com.pearlhacks.therightfit;

import java.util.List;

public class BrandMeasurements {
	public String Name;
	public List<Bottom> bList;
	public List<Top> tList;
	public List<Dress> dList;
	public BrandMeasurements(String inName, List<Bottom> bList, List<Top> tList, List<Dress> dList){
		this.Name = inName;
		this.bList = bList;
		this.dList = dList;
		this.tList = tList;
	}
}