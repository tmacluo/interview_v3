package com.airwallex.rpn.memento;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Design pattern "memento" to support undo action
 * @author Tmac
 */
public class MementoCaretaker {
	private int i = -1; // index for current Memento
	private List<Memento> undoList = new ArrayList<Memento>();
	
	public void createMemento(Stack<String> stack){
		/*Stack<String> mementoStack = new Stack<String>();
		mementoStack.addAll(stack);
		stack.clone();*/
		undoList.add(new Memento((Stack<String>)stack.clone()));
		i++;
	}
	
	public Memento getMemento(){
		if(i <= -1 || undoList.size() == 0){
			return new Memento(new Stack<String>());
		}
		undoList.remove(i--);
		if(i <= -1){
			return new Memento(new Stack<String>());
		}
		return undoList.get(i);
	}
	
	public void clearMemento(){
		undoList.clear();
		i = -1;
	}
	
	
	
}
