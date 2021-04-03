package StoneKopeloffProject.utililty;

public class Validation {
    /**
     * Method that checks if a string contains valid characters for a name
     *
     * @param str the string to check
     * @return true if its valid, false otherwise
     */
    public static boolean validateString(String str) {
        str = str.toLowerCase();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!(ch >= 'a' && ch <= 'z')) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method that checks if an email address is valid
     *
     * @param str the email address
     * @return true if its valid false otherwise
     */
    //credit https://www.geeksforgeeks.org/check-email-address-valid-not-java/ for the regex used below
    public static boolean validateEmail(String str) {
        if (str.matches("^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$")) {
            return true;
        }
        return false;
    }

}
