package com.bsoft.Ecalculator.Analysis.Nodes;

public class Expression extends Node{
	private Node right, left;
	private Sign sign;
	
	public Expression(Node left, Sign sign, Node right){
		this.left = left;
		this.right = right;
		this.sign = sign;
	}

	@Override
	public Node solve(){
		double initLeft = ((Number)left.solve()).getValue(), initRight = ((Number)right.solve()).getValue();
		switch(sign.getSign()){
			case '+':
				return new Number(initLeft + initRight);
			case '-':
				return new Number(initLeft - initRight);
			case '÷':
				return new Number(initLeft / initRight);
			case '×':
				return new Number(initLeft * initRight);
		}
		return null;
	}

	@Override
	public String toString(){
		return "Expresdion : (" + left.toString() + " " + sign + " " + right.toString() + " )";
	}
}
