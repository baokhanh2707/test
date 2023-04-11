package ss1_introduction_to_java.excercise;

import java.util.Scanner;

public class CurrencyConversion {
    public static void main(String[] args) {
      public static int soNguyenTo(int n) {
        int sum = 0;
        if (n === 2) {
            sum+= n;
        } else if(n > 2) {
            for (int i = 2; i < Math.sqrt(n); i++) {
                if (n % i === 0) {
                    sum +=n;
                }
            }
        }
        return sum;
    }
}
