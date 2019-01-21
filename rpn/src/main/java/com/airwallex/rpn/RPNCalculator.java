package com.airwallex.rpn;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

import com.airwallex.rpn.memento.MementoCaretaker;
import com.airwallex.rpn.operator.Operator;
import com.airwallex.rpn.operator.OperatorFactory;
import com.airwallex.rpn.util.CalculatorUtil;
import com.airwallex.rpn.util.InsufficientParameterException;

/**
 * Class that can be used to a console application from a Java main
 * method. it waits for user input and expects to receive strings containing
 * whitespace separated lists of numbers and operators.
 * The calculator store real numbers into stack and display it in the console .
 */
public class RPNCalculator {
	
	/** stack contains the real numbers*/
	private Stack<String> numsStack = new Stack<String>();
	/** */
	private MementoCaretaker mc = new MementoCaretaker();
	
	public void calc(String inputStr){
		int index = 0;
		StringTokenizer tokenizer = new StringTokenizer(inputStr);
		while (tokenizer.hasMoreTokens()) {
			index++;
			String input = tokenizer.nextToken();
			if (CalculatorUtil.isDecimal(input)) {
				numsStack.push(CalculatorUtil.toPlainString(input));
				mc.createMemento(numsStack);
			} else if (CalculatorUtil.isOperator(input)) {
				try{
					operate(input, index);
				} catch(InsufficientParameterException e){
					break;
				} catch(ArithmeticException e){
					System.out.println("can not divide by zero");
					break;
				}
			} else {
				System.out.println("Ilegal input parameter:" + input);
				break;
			}
		}
		System.out.print("Stack: ");
		numsStack.stream().forEach(result -> {
			System.out.print(new BigDecimal(result).setScale(10, 
					BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString()+ " ");
		});
	}
	
	public Stack<String> getNumsStack() {
		return numsStack;
	}

	private void operate(String input, int index) {
		switch (input) {
			case "clear": {
				numsStack.clear();
				mc.clearMemento();
				break;
			}
			case "undo": {
				numsStack = mc.getMemento().getStack();
				break;
			}
			case "sqrt": {
				numsStack.push(CalculatorUtil.sqrt(numsStack.pop()));
				mc.createMemento(numsStack);
				break;
			}
			default: {
				// at least two numbers required to perform operations(+-*/).
				if (numsStack.size() < 2) {
					System.out.println(String.format("Operator %s (position: %s): insucient parameters", input, index*2 - 1));
					throw new InsufficientParameterException("insucient parameters");
				}
				Operator operator = OperatorFactory.getOperator(input);
				BigDecimal result = operator.calc(new BigDecimal(numsStack.pop()), new BigDecimal(numsStack.pop()));
				numsStack.push(result.stripTrailingZeros().toPlainString());
				mc.createMemento(numsStack);
			}
		}
	}
	
	public static void main(String[] args) {
		RPNCalculator rpmCalculator = new RPNCalculator();
		Scanner input = new Scanner(System.in);
		while(input.hasNextLine()){
			String inputStr = input.nextLine();
			rpmCalculator.calc(inputStr);
			System.out.println();
			
		}
	}
}
