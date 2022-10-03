package algorithm;

import java.util.Stack;

public class ShutingYard {
    private static boolean letterOrDigit(char c)
    {
        return Character.isLetterOrDigit(c);
    }

    static int getPrecedence(char c)
    {
        if (c == '+' || c == '-')
            return 1;
        else if (c == '*' || c == '/')
            return 2;
        else
            return -1;
    }
    static boolean LeftAssociativity(char c) {
        return c == '+' || c == '-' || c == '/' || c == '*';
    }

    public static String shutingYard(String expression)
    {
        Stack<Character> stack = new Stack<>();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);
            if (letterOrDigit(c) || c == '.')
                output.append(c);
            else if (c == '(')
                stack.push(c);
            else if (c == ')') {
                while (!stack.isEmpty()
                        && stack.peek() != '(')
                    output.append(" ").append(stack.pop());
                stack.pop();
            }
            else {
                while (!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek()) && LeftAssociativity(c)) {
                    output.append(" ").append(stack.pop());
                }
                output.append(" ");
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            output.append(" ").append(stack.pop());
        }
        return output.toString();
    }
}
