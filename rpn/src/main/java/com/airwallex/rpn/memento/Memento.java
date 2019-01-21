package com.airwallex.rpn.memento;

import java.util.Stack;

/**
 * Record the numbers for the provided stack
 * @author Tmac
 *
 */
public class Memento {
	private Stack<String> stack;

	public Memento(Stack<String> stack) {
		this.stack = stack;
	}

	public Stack<String> getStack() {
		return stack;
	}

	public void setStack(Stack<String> stack) {
		this.stack = stack;
	}
	
	
	
}
