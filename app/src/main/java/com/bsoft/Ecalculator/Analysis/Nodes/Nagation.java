package com.bsoft.Ecalculator.Analysis.Nodes;

public class Nagation extends Node{
	Node node;
	public Nagation(Node node){
		this.node = node;
	}
	
	@Override
	public Node solve(){
		return new Number(((Number)node.solve()).getValue() * -1);
	}

	@Override
	public String toString(){
		return "Nagation: -" + Double.toString(((Number)node).getValue());
	}
}
