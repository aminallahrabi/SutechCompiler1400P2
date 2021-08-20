/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler2;

import static java.lang.System.out;
import java.util.Stack;

public class EvaluateString {

    /*
    While there are still tokens to be read in,
   1.1 Get the next token.
   1.2 If the token is:
       1.2.1 A number: push it onto the value stack.
       1.2.2 A variable: get its value, and push onto the value stack.
       1.2.3 A left parenthesis: push it onto the operator stack.
       1.2.4 A right parenthesis:
         1 While the thing on top of the operator stack is not a 
           left parenthesis,
             1 Pop the operator from the operator stack.
             2 Pop the value stack twice, getting two operands.
             3 Apply the operator to the operands, in the correct order.
             4 Push the result onto the value stack.
         2 Pop the left parenthesis from the operator stack, and discard it.
       1.2.5 An operator (call it thisOp):
         1 While the operator stack is not empty, and the top thing on the
           operator stack has the same or greater precedence as thisOp,
           1 Pop the operator from the operator stack.
           2 Pop the value stack twice, getting two operands.
           3 Apply the operator to the operands, in the correct order.
           4 Push the result onto the value stack.
         2 Push thisOp onto the operator stack.
2. While the operator stack is not empty,
    1 Pop the operator from the operator stack.
    2 Pop the value stack twice, getting two operands.
    3 Apply the operator to the operands, in the correct order.
    4 Push the result onto the value stack.
3. At this point the operator stack should be empty, and the value
   stack should have only one value in it, which is the final result.
     */
    public float evaluate(String expression) {
        char[] tokens = expression.toCharArray();

        // Stack for numbers: 'values'
        Stack<Float> values = new Stack<Float>();

        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<Character>();

        for (int i = 0; i < tokens.length; i++) {

            // Current token is a
            // whitespace, skip it
            if (tokens[i] == ' ') {
                continue;
            }

            // Current token is a number,
            // push it to stack for numbers
            if (tokens[i] >= '0'
                    && tokens[i] <= '9' ) {
                StringBuffer sbuf = new StringBuffer();

                // There may be more than one
                // digits in number
                while (i < tokens.length
                        && (tokens[i] >= '0'
                        && tokens[i] <= '9' ||  tokens[i]=='.')) {
                    sbuf.append(tokens[i++]);
                }
                out.println("stack values top:"+Float.parseFloat(sbuf.toString()));
                values.push(Float.parseFloat(sbuf.toString()));

                // right now the i points to
                // the character next to the digit,
                // since the for loop also increases
                // the i, we would skip one
                //  token position; we need to
                // decrease the value of i by 1 to
                // correct the offset.
                i--;
            } // Current token is an opening brace,
            // push it to 'ops'
            else if (tokens[i] == '(') {
                ops.push(tokens[i]);
            } // Closing brace encountered,
            // solve entire brace
            else if (tokens[i] == ')') {
                while (ops.peek() != '(') {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.pop();
            } // Current token is an operator.
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                // While top of 'ops' has same
                // or greater precedence to current
                // token, which is an operator.
                // Apply operator on top of 'ops'
                // to top two elements in values stack

                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek())) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                    out.println("stack (values) result:"+values.peek());

                }

                // Push current token to 'ops'.
                ops.push(tokens[i]);
                out.println("stack ops top:"+ops.peek());
            }
        }

        // Entire expression has been
        // parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty()) {
            values.push(applyOp(ops.pop(),
                    values.pop(),
                    values.pop()));
        }

        // Top of 'values' contains
        // result, return it
        return values.pop();
    }

    // Returns true if 'op2' has higher
    // or same precedence as 'op1',
    // otherwise returns false.
    public static boolean hasPrecedence(
            char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/')
                && (op2 == '+' || op2 == '-')) {
            return false;
        } else {
            return true;
        }
    }

    // A utility method to apply an
    // operator 'op' on operands 'a'
    // and 'b'. Return the result.
    public static float applyOp(char op,
            float b, float a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException(
                            "Cannot divide by zero");
                }
                return a / b;
        }
        return 0;
    }

    // Driver method to test above methods
//    public static void main(String[] args) {
//        System.out.println(EvaluateString.
//                evaluate("10 + 2 * 3 - 1"));
//        System.out.println(EvaluateString.
//                evaluate("100 * 2 + 12"));
//        System.out.println(EvaluateString.
//                evaluate("100 * ( 2 + 12 )"));
//        System.out.println(EvaluateString.
//                evaluate("100 * ( 2 + 12 ) / 14"));
//    }
}
