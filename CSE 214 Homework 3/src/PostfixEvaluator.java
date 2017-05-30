/**
 * @author Daniel Garcia
 */
import java.util.EmptyStackException;
import java.util.Scanner;

public class PostfixEvaluator<E> {

    public int evaluate(char[] infix) throws Exception {
        InfixToPostfixConverter expression = new InfixToPostfixConverter();
        String exp = expression.convert(infix);
        Stack<Double> numStack = new Stack<Double>();
        String num = "";
        int i = 0;
        while (i < exp.length()) {
            char c = exp.charAt(i);
            if (c == ' ') {
                i++;
            }
            else {
                if (!(isOperator(Character.toString(c)))) {
                    if (exp.charAt(i + 1) == ' ') {
                        num += Character.toString(c);
                        numStack.push(Double.parseDouble(num));
                        num = "";
                    }
                    else {
                        num += Character.toString(c);
                    }
                    i++;
                }
                else if (isOperator(Character.toString(c))) {
                    Double num2 = numStack.pop();
                    Double num1 = numStack.pop();
                    Double result = 0.0;
                    if (c == '+') result = num1 + num2;
                    else if (c == '-') result = num1 - num2;
                    else if (c == '*') result = num1 * num2;
                    else {
                        if (num2 != 0) {
                            result = num1 / num2;
                        }
                        else {
                            throw new ArithmeticException();
                        }
                    }
                    numStack.push(result);
                    i++;
                }
            }
        }
        int answer = (int) Math.round(numStack.pop());
        return answer;
    }

    public boolean isOperator(String i) {
        if (i.equals("+") || i.equals("-")) return true;
        else if (i.equals("*") || i.equals("/")) return true;
        else return false;
    }

    public static void main(String[] args) throws Exception{
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter an infix expression (Enter \"q\" or \"Q\" to end prompt): ");
        String exp = input.nextLine();
        exp = exp.replaceAll("\\s+", "");
        if (exp.equals("Q")) exp = "q";
        if (!(exp.equals("q"))) {
            char[] charArray = exp.toCharArray();
            try {
                System.out.println(new InfixToPostfixConverter<>().convert(charArray));
            }
            catch (EmptyStackException e) {
                System.out.println("Parentheses are not balanced. Expression is formatted incorrectly. Please try again. \n");
                main(args);
            }
            catch (NumberFormatException e) {
                System.out.println("Expression is formatted incorrectly. Please try again. \n");
                main(args);
            }
            catch (Exception e) {
                System.out.println("Expression is formatted incorrectly. Please try again. \n");
                main(args);
            }
            try {
                System.out.println(new PostfixEvaluator<>().evaluate(charArray) + "\n");
                main(args);
            }
            catch (ArithmeticException e) {
                System.out.println("A number cannot be divided by 0. Please try again. \n");
                main(args);
            }
        }
    }

}
