package com.bsoft.Ecalculator.Analysis.Nodes;

public class Number extends Node{
	double value;
	public Number(double value){ this.value = value; }
	public double getValue()
	{ return value; }

	@Override
	public String toString(){
		return "Number: " + Double.toString(value);
	}
}
