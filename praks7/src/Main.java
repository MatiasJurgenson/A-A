import java.util.*;

public class Main {

    public static int yl1_1(int[] a, int[] b) {
        int kokku = 0;
        v: for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] == a[j]) continue v;
            }
            for (int l1 : b) {
                if (a[i] == l1) {
                    kokku++;
                    break;
                }
            }
        }
        return kokku;
    }

    public static int yl1_2(int[] a, int[] b) {
        Set<Integer> aHulk = new HashSet<>();
        for (int i : a) {
            aHulk.add(i);
        }
        Set<Integer> bHulk = new HashSet<>();
        for (int i : b) {
            bHulk.add(i);
        }
        aHulk.retainAll(bHulk);
        return aHulk.size();

    }

    public static int yl1_2_kiire(int[] a, int[] b) {
        Set<Integer> aHulk = new HashSet<>();
        for (int i : a) {
            aHulk.add(i);
        }

        int kokku = 0;
        for (int i : b) {
            if (aHulk.contains(i)){
                kokku++;
            }
        }
        return aHulk.size();

    }

    public static void yl2_loop(LinkedList<Integer> list) {
        Iterator<Integer> iter = list.iterator();

        for (int i = 0; iter.hasNext(); i++) {
            int praegune = iter.next();

            Iterator<Integer> eelmised = list.iterator();
            for (int j = 0; j < i; j++) {
                int mingiEelnev = eelmised.next();
                if (mingiEelnev == praegune) {
                    iter.remove();
                    i--;
                    break;
                }
            }
        }

    }

    public static void yl2_set(LinkedList<Integer> list) {
        Iterator<Integer> iter = list.iterator();

        Set<Integer> hulk = new HashSet<>();

        while (iter.hasNext()) {
            int praegune = iter.next();
            if (hulk.contains(praegune)) {
                iter.remove();
            } else {
                hulk.add(praegune);
            }
        }

    }

    public static int[] yl3Pask(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] + a[j] == a.length) return new int[]{i, j};
            }
        }
        return null;
    }

    public static int[] yl3Paisk(int[] a) {
        Set<Integer> hulk = new HashSet<>();
        for (int i : a) {
            hulk.add(i);
        }
        for (int i : hulk) {
            if (hulk.contains(hulk.size() - i )){
                int i1 = 0;
                int i2 = 0;
                for (int j = 0; j < a.length; j++) {
                    if (j == i) i1 = j;
                }
                for (int j = 0; j < a.length; j++) {
                    if (hulk.size() - i == i) i2 = j;
                }
                return new int[]{i1, i2};

            }
        }

        return null;
    }

    public static int[] yl3Map(int[] a) {
        int n = a.length;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < a.length; i++) {
            if (map.containsKey(n-a[i])) {
                return new int[]{map.get(n-a[i]), i};
            }
            map.put(a[i], i);
        }

        return null;
    }
    public static void main(String[] args) {
        int[] massiiv = new int[]{1,1,2,2,3,3,1};
        LinkedList<Integer> list = new LinkedList<>();
        for (int j : massiiv) {
            list.add(j);
        }
        System.out.println(list);
        yl2_set(list);
        System.out.println(list);
    }
}