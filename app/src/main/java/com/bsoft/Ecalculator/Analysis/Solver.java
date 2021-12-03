package com.bsoft.Ecalculator.Analysis;

import java.util.*;
import android.util.*;
import com.bsoft.Ecalculator.Analysis.Nodes.*;

public class Solver{
	Node node;
	double memory;
	public Solver(String input, double memory){
		this.memory = memory;
		node = this.parse(new Lexer(input).getNodes());
		Log.e("family", node.toString());
	}
	
	private Node parse(List<Node> nodes){
		return lookForSubs(nodes);
	}
	
	private Node lookForSubs(List<Node> nodes){
		Node left = lookForFactors(nodes);
		while(!nodes.isEmpty() &&  nodes.get(0) instanceof Sign && check((Sign)nodes.get(0), "-+")){
			Sign init = (Sign) nodes.get(0);
			nodes.remove(0);
			left = new Expression(left, init, lookForFactors(nodes));
		}
		return left;
	}
	
	private Node lookForFactors(List<Node> nodes){
		Node left = lookForNumber(nodes);
		while(!nodes.isEmpty() && nodes.get(0) instanceof Sign && check((Sign)nodes.get(0), "×÷")){
			Sign init = (Sign) nodes.get(0);
			nodes.remove(0);
			left = new Expression(left, init, lookForNumber(nodes));
		}
		return left;
	}
	
	private Node lookForNumber(List<Node> nodes){
		Node init = nodes.get(0);
		if(init instanceof Sign && check((Sign)init, "-")){
			nodes.remove(0);
			return new Nagation((com.bsoft.Ecalculator.Analysis.Nodes.Number) lookForNumber(nodes));
		}else if(init instanceof Ans){
			nodes.remove(0);
			return new com.bsoft.Ecalculator.Analysis.Nodes.Number(memory);
		}
		nodes.remove(0);
		return init;
	}
	
	private boolean check(Sign sign, String values){
		for(char c : values.toCharArray()){
			if(c == sign.getSign()){ return true; }
		}
		return false;
	}
	
	public double getAns(){ return ((com.bsoft.Ecalculator.Analysis.Nodes.Number)node.solve()).getValue(); }
}
