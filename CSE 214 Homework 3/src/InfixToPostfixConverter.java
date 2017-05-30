import java.util.EmptyStackException;

/**
 * @author Daniel Garcia
 */
public class InfixToPostfixConverter<E> {

    public String convert (char[] infix) throws Exception {
        Stack<E> opStack = new Stack<E>();
        Stack<E> parStack = new Stack<E>();
        String postfixExpression = "";
        opStack.push((E) "$");
        String topOp = "";
        String num = "";
        int i = 0;
        if (!(Character.isDigit(infix[infix.length - 1]) || infix[infix.length - 1] == ')')) {
            throw new Exception();
        }
        while (i < infix.length) {
            if (Character.isDigit(infix[i]) || infix[i] == '.') {
                if (!(i + 1 == infix.length)) {
                    if (isOperator(Character.toString(infix[i + 1]))) {
                        num += Character.toString(infix[i]);
                        postfixExpression += num + " ";
                        num = "";
                    }
                    else if (Character.isDigit(infix[i + 1])) {
                        num += Character.toString(infix[i]);
                    }
                    else if (infix[i + 1] == '.') {
                        if (infix[i] == '.') {
                            throw new NumberFormatException();
                        }
                        num += Character.toString(infix[i]);
                    }
                    else {
                        throw new NumberFormatException();
                    }
                    i++;
                }
                else {
                    num += Character.toString(infix[i]);
                    postfixExpression += num + " ";
                    num = "";
                    i++;
                }
            }
            else if (infix[i] == '(' || infix[i] == '[' || infix[i] == '{') {
                infix[i] = '(';
                if (infix[i + 1] == '+' || infix[i + 1] == '-' || infix[i + 1] == '*' || infix[i + 1] == '/') {
                    throw new Exception();
                }
                opStack.push((E) Character.toString(infix[i]));
                i++;
                parStack.push((E) Character.toString(infix[i]));
            }
            else if (infix[i] == ')' || infix[i] == ']' || infix[i] == '}') {
                infix[i] = ')';
                if (parStack.isEmpty()) {
                    throw new EmptyStackException();
                }
                else {
                    topOp = (String) opStack.pop();
                    while (!(topOp.equals("("))) {
                        postfixExpression += topOp + " ";
                        topOp = (String) opStack.pop();
                    }
                    i++;
                    parStack.pop();
                }
            }
            else if (infix[i] == '+' || infix[i] == '-' || infix[i] == '*' || infix[i] == '/') {
                if (infix[i + 1] == '+' || infix[i + 1] == '-' || infix[i + 1] == '*' || infix[i + 1] == '/') {
                    throw new Exception();
                }
                topOp = (String) opStack.peek();
                while (precedence(topOp) >= precedence(Character.toString(infix[i]))) {
                    postfixExpression += opStack.pop() + " ";
                    topOp = (String) opStack.peek();
                }
                opStack.push((E) Character.toString(infix[i]));
                i++;
            }
            else {
                throw new NumberFormatException();
            }
        }
        if (!(parStack.isEmpty())) {
            throw new EmptyStackException();
        }
        topOp = (String) opStack.pop();
        while (!(topOp.equals("$"))) {
            postfixExpression += topOp + " ";
            topOp = (String) opStack.pop();
        }
        return postfixExpression;
    }

    public int precedence(String i) throws Exception {
        if (i.equals("$")) return 0;
        else if (i.equals("(") || i.equals("[") || i.equals("{")) return 1;
        else if (i.equals("+") || i.equals("-")) return 2;
        else if (i.equals("*") || i.equals("/")) return 3;
        else throw new Exception("Incorrect operator.");
    }

    public boolean isOperator(String i) {
        if (i.equals("(") || i.equals("[") || i.equals("{") || i.equals(")") || i.equals("]") || i.equals("}")) return true;
        else if (i.equals("+") || i.equals("-")) return true;
        else if (i.equals("*") || i.equals("/")) return true;
        else return false;
    }

}
