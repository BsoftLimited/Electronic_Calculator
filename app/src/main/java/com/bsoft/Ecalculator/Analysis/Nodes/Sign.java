package com.bsoft.Ecalculator.Analysis.Nodes;

public class Sign extends Node{
	char sign;
	public Sign(char sign){this.sign = sign;}
	public char getSign(){ return sign;}

	@Override
	public String toString(){
		return "Sign: " + sign;
	}
}
