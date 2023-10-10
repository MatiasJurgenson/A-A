import java.util.ArrayDeque;
import java.util.Deque;

public class yl1 {
    public static boolean kasPalindroom(String sõne) {
        Deque<Character> magasin = new ArrayDeque<>();
        for (int i = 0; i < sõne.length(); i++) {
            char praegune = sõne.charAt(i);
            if(Character.isAlphabetic(praegune)) {
                magasin.push(praegune);
            }

        }
        for (int lenght = 0; lenght < sõne.length() ; lenght++) {
            if (magasin.pop() != sõne.charAt(lenght)) {
                return false;
            }
        }

        return true;
    }
    public static void main(String[] args) {
        System.out.println(kasPalindroom("kirik"));
        System.out.println(kasPalindroom("kiriki"));
    }
}
