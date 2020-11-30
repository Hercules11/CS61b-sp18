/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        Palindrome palindrome = new Palindrome();
        for (int i = 0; i < 25; i++) {
            In in = new In("../library-sp18/data/words.txt");
            OffByN cc = new OffByN(i);
            int count = 0;
            String maxString = "";
            while (!in.isEmpty()) {
                String word = in.readString();
                if (word.length() >= minLength && palindrome.isPalindrome(word, cc)) {
                    count += 1;
                    if (word.length() > maxString.length()) {
                        maxString = word;
                    }
                }
            }
            System.out.println("The number of " + i + " palindrome: " + count);
            System.out.println("The longest word of N palindrome: " + maxString);
        }
    }
    //Uncomment this class once you've written isPalindrome. */
}
