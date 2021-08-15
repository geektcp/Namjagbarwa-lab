package com.geektcp.alpha.algorithm.arithmetic;

/**
 * @author tanghaiyang on 2019/2/21.
 */
public class TreeNode {
    private double data;
    private char operation;
    private TreeNode left;
    private TreeNode right;

    /*
     * recursively construct the tree
     */
    public TreeNode(String expression) {
        char[] exc = toCharArrayTrimOutParenthes(expression);
        if (exc == null) {
            return;
        }
        exc = syntaxCheck(exc);
        int length = exc.length;

        int index = 0;

        if (hasOperation(exc)) {
            int parenthes = 0;

            for (int i = length - 1; i >= 0; i--) {

                if (exc[i] == '(') {
                    parenthes--;
                } else if (exc[i] == ')') {
                    parenthes++;
                }

                if (parenthes > 0) {
                    continue;
                }

                if (exc[i] == '*' || exc[i] == '/') {
                    index = i;
                } else if (exc[i] == '+' || exc[i] == '-') {
                    index = i;
                    break;
                }

            }
            if (isOperation(exc[index])) {
                operation = exc[index];
            }
            StringBuilder sbleft = new StringBuilder();
            StringBuilder sbright = new StringBuilder();

            for (int i = 0; i < index; i++) {
                sbleft.append(exc[i]);
            }
            for (int i = index + 1; i < length; i++) {
                sbright.append(exc[i]);
            }
            left = new TreeNode(sbleft.toString());
            right = new TreeNode(sbright.toString());

        } else {
            StringBuilder value = new StringBuilder();
            value.append(exc);
            data = Double.parseDouble(value.toString());
        }
    }

    /*
     * check the expression's syntax if there is two or more continuous
     * operations,print out syntax error add '0' before the '-' if it's a
     * negative
     */
    public char[] syntaxCheck(char[] cArray) {
        boolean flag = false;
        if (isOperation(cArray[0])) {
            char[] checkedArray = new char[cArray.length + 1];
            checkedArray[0] = '0';
            for (int i = 0; i < cArray.length; i++) {
                checkedArray[i + 1] = cArray[i];
            }
            cArray = checkedArray;
        }
        for (int i = 0; i < cArray.length; i++) {
            if (isOperation(cArray[i])) {
                if (flag == true) {
                    System.out.println("syntaxError");
                }
                flag = true;
            } else {
                flag = false;
            }
        }

        return cArray;
    }

    /*
     * is there operations in the char array if there is,return true. if
     * not,return false
     */
    public boolean hasOperation(char[] cArray) {
        for (int i = 0; i < cArray.length; i++) {
            if (isOperation(cArray[i])) {
                return true;
            }

        }
        return false;
    }

    public boolean isOperation(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');

    }

    /*
     * trim the out most useless parentheses and return a char array
     */
    public char[] toCharArrayTrimOutParenthes(String src) {

        if (src.length() == 0) {
            return null;
        }
        String result = src;
        while (result.charAt(0) == '('
                && result.charAt(result.length() - 1) == ')') {
            int parenthes = 0;
            for (int i = 0; i < result.length() - 1; i++) {
                if (result.charAt(i) == '(') {
                    parenthes++;
                } else if (result.charAt(i) == ')') {
                    parenthes--;
                }
                if (parenthes == 0) {
                    return result.toCharArray();
                }
            }
            result = result.substring(1, result.length() - 1);

        }

        return result.toCharArray();
    }

    // recursively calculate
    public double calculate() {
        double result = 0;
        if (left == null && right == null) {
            result = data;
        } else {
            double leftResult = left.calculate();
            double rightResult = right.calculate();
            switch (operation) {
                case '+':
                    result = leftResult + rightResult;
                    break;
                case '-':
                    result = leftResult - rightResult;
                    break;
                case '*':
                    result = leftResult * rightResult;
                    break;
                case '/':
                    result = leftResult / rightResult;
                    break;
                default:
                    break;
            }
        }
        return result;
    }

}
