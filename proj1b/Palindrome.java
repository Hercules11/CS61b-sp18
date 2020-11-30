public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> stringDeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            stringDeque.addLast(word.charAt(i));
        }
        return stringDeque;
    }

    boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else if (word.charAt(0) != word.charAt(word.length() - 1)) {
            return false;
        } else {
            return isPalindrome(word.substring(1, word.length() - 1));
        }

    }

    boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else if (!cc.equalChars(word.charAt(0), word.charAt(word.length() - 1))) {
            return false;
        } else {
            return isPalindrome(word.substring(1, word.length() - 1), cc);
        }
    }

    boolean isPalindrome(String word, OffByN cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else if (!cc.equalChars(word.charAt(0), word.charAt(word.length() - 1))) {
            return false;
        } else  {
            return isPalindrome(word.substring(1, word.length() - 1), cc);
        }
    }
}
