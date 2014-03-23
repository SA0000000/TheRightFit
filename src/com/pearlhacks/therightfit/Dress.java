package com.pearlhacks.therightfit;

public class Dress extends Clothing {
	public float Bust;
	public float Waist;
	public Dress(String inSize, char inType, float inBust, float inWaist){
		this.Size = inSize;
		this.Type = inType;
		this.Bust = inBust;
		this.Waist = inWaist;
	}
}

