import java.util.Stack;

public class Calculator {
	
	public enum Operator { ADD, SUBTRACT, MULTIPLY,  DIVIDE, BLANK};
	
	/*
	 * main function
	 * two stacks
	 * for loop
	 * 	extract integer => parseNextNumber()
	 * 	push the number into the stack
	 * 	increment iterator
	 * 	extract operator => parseNextOperator()
	 * 	collapseStack
	 *  push the operator into the stack
	 */
	public static double calculate ( String sequence ) {
		
		Stack<Double> numStack = new Stack<>();
		Stack<Operator> opStack = new Stack<>();
		try {
			for ( int i = 0; i < sequence.length(); i++ ) {
				
				int num = parseNextNum(sequence, i);
				numStack.push((double) num);
				
				// increment the i by the Integer.toString(num).length()
				i += Integer.toString(num).length();
				if ( i > sequence.length() ) {
					break;
				}
				
				Operator op = parseNextOperator(sequence, i);
				collapseStack(op, numStack, opStack);
				opStack.push(op);
				
				
				
			}
		} catch ( NumberFormatException ex) {
			return Integer.MIN_VALUE;
		}
		collapseStack(Operator.BLANK, numStack, opStack);
		
		// when to return the final result
		if ( numStack.size() == 1 && opStack.size() == 0) {
			return numStack.pop();
		}
		
		return 0;
	}

	/*
	 * parseNextNumber()
	 */
	private static int parseNextNum(String sequence, int i) {
		
		StringBuilder sb = new StringBuilder();
		/*
		 * while loop
		 */
		while ( i < sequence.length() && Character.isDigit(sequence.charAt(i)) ) {
			sb.append(sequence.charAt(i));
			i++;
		}
		
		return Integer.parseInt(sb.toString());
	}
	
	/*
	 * parseNextOperator()
	 */
	private static Operator parseNextOperator(String sequence, int i) {
		
		if ( i < sequence.length() ) {
			
			char op = sequence.charAt(i);
			
			switch (op) {
				case '+':
					return Operator.ADD;
				case '-':
					return Operator.SUBTRACT;
				case '*':
					return Operator.MULTIPLY;
				case '/':
					return Operator.DIVIDE;
			}
		}
		
		return Operator.BLANK;
	}
	
	/*
	 * collapseStack()
	 * 
	 * compare op with opStack.peek(), if <= , then collapse!
	 * 
	 */
	private static void collapseStack(Operator op, Stack<Double> numStack, Stack<Operator> opStack) {
		
		while ( numStack.size() >= 2 && opStack.size() >= 1) {
			
			if ( priorityOfOperator(op) <= priorityOfOperator(opStack.peek()) ) {
				double second = numStack.pop();
				double first = numStack.pop();
				Operator currOp = opStack.pop();
				double collapsed = applyOP(first, second, currOp);
				numStack.push(collapsed);
			} else {
				break;
			}
		} 
		
	}

	private static double applyOP(double first, double second, Operator currOp) {
		switch (currOp) {
			case ADD:
				return (first + second);
			case SUBTRACT:
				return (second - first);
			case MULTIPLY:
				return (second * first);
			case DIVIDE:
				return (second / first);			
				
		}
				
		return second;
	}

	private static int priorityOfOperator(Operator op) {
		switch (op) {
			case ADD:
				return 1;
			case SUBTRACT:
				return 1;
			case MULTIPLY:
				return 2;
			case DIVIDE:
				return 2;
		}
		return 0;
	}
	
	
	public static void main ( String[] args )  {
		
		String seq = "2 - 6 - 7 * 8 / 2 + 5";
		System.out.println(calculate(seq));
	}

}
