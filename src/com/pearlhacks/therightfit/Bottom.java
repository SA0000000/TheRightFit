package com.pearlhacks.therightfit;

public class Bottom extends Clothing {
	public float Waist;
	public float Hip;
	public float Inseam;
	public Bottom(String inSize, char inType, float inWaist, float inHip, float inInseam){
		this.Size = inSize;
		this.Type = inType;
		this.Waist = inWaist;
		this.Hip = inHip;
		this.Inseam = inInseam;
	}
}
