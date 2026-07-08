public class LoginCheck {
    public static void main(String[] args) {
        String username = "admin";
        String password = "1234";

        String inputUsername = "admin";
        String inputPassword = "1234";

        boolean isUsernameCorrect = username.equals(inputUsername); //比較兩個字串的內容是不是一模一樣(true，內容一樣)
        boolean isPasswordCorrect = password.equals(inputPassword); //這行一樣是true
        boolean login = isUsernameCorrect && isPasswordCorrect;

        System.out.println("帳號正確: " + isUsernameCorrect);
        System.out.println("密碼正確: " + isPasswordCorrect);
        System.out.println("登入結果: " + login);
    }
}
