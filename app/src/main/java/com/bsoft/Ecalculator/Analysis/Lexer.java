package com.bsoft.Ecalculator.Analysis;

import java.util.*;
import android.util.*;
import com.bsoft.Ecalculator.Analysis.Nodes.*;

public class Lexer{
	private char current;
	private int index;
	private String input;
	private List<Node> nodes;
	
	public Lexer(String input){
		this.input = input;
		this.nodes = new ArrayList<>();
		index = - 1;
		while(hasNext()){
			if(Character.isDigit(current)){
				nodes.add(this.getNumber());
			}else if(Character.isAlphabetic(current)){
				nodes.add(this.getAns());
			}
			if(index < input.length() && !Character.isDigit(current)){
				nodes.add(new Sign(current));
			}
		}
	}
	
	private boolean hasNext(){
		if(++index < input.length()){
			current = input.charAt(index);
			if(current == ' '){ return hasNext(); }
			return true;
		}
		return false;
	}
	
	private com.bsoft.Ecalculator.Analysis.Nodes.Number getNumber(){
		StringBuilder init = new StringBuilder();
		init.append(current);
		while(hasNext() && (Character.isDigit(current) || current == '.')){
			init.append(current);
		}
		return new com.bsoft.Ecalculator.Analysis.Nodes.Number(Double.parseDouble(init.toString()));
	}
	
	private Ans getAns(){
		StringBuilder init = new StringBuilder();
		init.append(current);
		while(hasNext() && Character.isAlphabetic(current)){
			init.append(current);
		}
		return init.toString().equals("Ans") ? new Ans() : null;
	}
	
	public List<Node> getNodes(){ return nodes; }
}
