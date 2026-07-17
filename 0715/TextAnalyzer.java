import java.util.*;

public class TextAnalyzer {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String rawText = readValidLine(sc);

        int rawCount = rawText.length();
        System.out.println("原始字元數：" + rawCount);

        
        String trimmedText = rawText.trim();
        int effectiveCount = trimmedText.length();
        System.out.println("trim() 後有效字元數：" + effectiveCount);

       
        String[] words = splitWords(trimmedText);

        
        System.out.println("單字數量：" + words.length);

        int vowelCount = countVowels(trimmedText);
        System.out.println("母音總數（a,e,i,o,u）：" + vowelCount);

        
        String longestWord = findLongestWord(words);
        System.out.println("最長單字：" + longestWord);

        
        System.out.println("請輸入要查詢的關鍵字：");
        String keyword = sc.nextLine();
        int keywordCount = countKeyword(words, keyword);
        System.out.println("關鍵字「" + keyword + "」出現次數：" + keywordCount);

        sc.close();
    }

    
    public static String readValidLine(Scanner sc) {
        String input = "";
        boolean isValid = false;

        while (isValid == false) {
            System.out.println("請輸入一行非空白文字：");
            input = sc.nextLine();

            if (input.trim().isEmpty()) {
                System.out.println("輸入不可以是空字串或全空白，請重新輸入。");
            } else {
                isValid = true;
            }
        }

        return input;
    }

   
    public static String[] splitWords(String text) {
        return text.split("\\s+");
    }

    public static int countVowels(String text) {
        int count = 0;
        String lowerText = text.toLowerCase();

        for (int i = 0; i < lowerText.length(); i++) {
            char currentChar = lowerText.charAt(i);

            if (currentChar == 'a' || currentChar == 'e' || currentChar == 'i'
                    || currentChar == 'o' || currentChar == 'u') {
                count++;
            }
        }

        return count;
    }

    public static String findLongestWord(String[] words) {
        String longest = "";

        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > longest.length()) {
                longest = words[i];
            }
        }

        return longest;
    }

    public static int countKeyword(String[] words, String keyword) {
        int count = 0;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase(keyword)) {
                count++;
            }
        }

        return count;
    }
}
