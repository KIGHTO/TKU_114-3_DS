import java.util.*;
import java.util.Stack;

public class BracketValidationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("");
            System.out.println("===== 括號驗證系統 =====");
            System.out.println("請輸入要驗證的字串（輸入 exit 結束）：");
            String input = sc.nextLine();

            if (input.equals("exit")) {
                System.out.println("程式結束");
                break;
            }

            boolean result = isValid(input);

            if (result) {
                System.out.println("驗證結果：括號配對正確");
            } else {
                System.out.println("驗證結果：括號配對錯誤");
            }
        }

        sc.close();
    }

    public static boolean isValid(String input) {
        Stack<Character> bracketStack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (isOpenBracket(c)) {
                bracketStack.push(c);
            } else if (isCloseBracket(c)) {
                if (bracketStack.isEmpty()) {
                    return false;
                }

                char top = bracketStack.pop();

                if (!isMatchingPair(top, c)) {
                    return false;
                }
            }
        }

        if (!bracketStack.isEmpty()) {
            return false;
        }

        return true;
    }

    public static boolean isOpenBracket(char c) {
        if (c == '(' || c == '[' || c == '{') {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isCloseBracket(char c) {
        if (c == ')' || c == ']' || c == '}') {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isMatchingPair(char open, char close) {
        if (open == '(' && close == ')') {
            return true;
        } else if (open == '[' && close == ']') {
            return true;
        } else if (open == '{' && close == '}') {
            return true;
        } else {
            return false;
        }
    }
}
