package solution;

import java.util.Stack;

public class Solution {
    public static double solution(String expression) {
        expression += " ";
        Stack<Double> values = new Stack<>();
        int lastSpace = 0;
        double secondValue;
        String item;
        for(int i = 0; i < expression.length(); ++i){
            if(expression.charAt(i) == ' '){
                item = expression.substring(lastSpace, i);
                item = item.replaceAll("\\s+", "");
                switch (item) {
                    case "+" -> values.push(values.pop() + values.pop());
                    case "-" -> {
                        secondValue = values.pop();
                        values.push(values.pop() - secondValue);
                    }
                    case "*" -> values.push(values.pop() * values.pop());
                    case "/" -> {
                        secondValue = values.pop();
                        values.push(values.pop() / secondValue);
                    }
                    default -> values.push(Double.parseDouble(item));
                }
                lastSpace = i;
            }
        }
        return values.peek();
    }
}
