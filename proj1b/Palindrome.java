public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> stringDeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            stringDeque.addLast(word.charAt(i));
        }
        return stringDeque;
    }

    public boolean isPalindrome(String word) {
//        if (word.length() == 0 || word.length() == 1) {
//            return true;
//        } else if (word.charAt(0) != word.charAt(word.length() - 1)) {
//            return false;
//        } else {
//            return isPalindrome(word.substring(1, word.length() - 1));
//        }
        Deque dq = wordToDeque(word);
        return getIsPalindrome(dq);
    }

    private boolean getIsPalindrome(Deque dq) {
        if (dq.size() == 0 || dq.size() == 1) {
            return true;
        } else if (dq.removeFirst() != dq.removeLast()) {
            return false;
        } else {
            return getIsPalindrome(dq);
        }
    }


    public boolean isPalindrome(String word, CharacterComparator cc) {
//        if (word.length() == 0 || word.length() == 1) {
////            return true;
////        } else if (!cc.equalChars(word.charAt(0), word.charAt(word.length() - 1))) {
////            return false;
////        } else {
////            return isPalindrome(word.substring(1, word.length() - 1), cc);
////        }
        Deque dq = wordToDeque(word);
        return getIsPalindromeCC(dq, cc);
    }

    private boolean getIsPalindromeCC(Deque dq, CharacterComparator cc) {
        if (dq.size() == 0 || dq.size() == 1) {
            return true;
        } else if (!cc.equalChars((char) dq.removeFirst(), (char) dq.removeLast())) {
            return false;
        } else {
            return getIsPalindromeCC(dq, cc);
        }
    }

//    public boolean isPalindrome(String word, OffByN cc) {
//        if (word.length() == 0 || word.length() == 1) {
//            return true;
//        } else if (!cc.equalChars(word.charAt(0), word.charAt(word.length() - 1))) {
//            return false;
//        } else  {
//            return isPalindrome(word.substring(1, word.length() - 1), cc);
//        }
//    }
}
