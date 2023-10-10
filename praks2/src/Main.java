import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static int abi1(int[] a, int n){
        if(n==0)
            return 1;
        int summa=abi1(lisa(a,0),n-1);
        summa+=abi1(lisa(a,1),n-1);
        summa+=abi1(lisa(a,2),n-1);
        return summa;
    }

    public static int[] lisa(int[] a, int x){
        int[] uus=new int[a.length+1];
        int i=0;
        for (;i<a.length;i++)
            uus[i]=a[i];
        uus[i]=x;
        System.out.println(Arrays.toString(uus));
        return uus;
    }

    public static int abi2(int[] a, int i, int n){
        //i - nii palju on massiivis elemente olemas
        int summa = n;
        return summa;
    }
    public static void main(String[] args) {
        int[] a = {1, 1, 1};
        System.out.println(abi1(a, 2));
    }
}