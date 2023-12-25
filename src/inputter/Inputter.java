package inputter;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Inputter {

    public static Scanner sc = new Scanner(System.in);

    public static int inputInt(String msg) {
        while (true) {
            System.out.println(msg);
            int x = Integer.parseInt(sc.nextLine());
            return x;
        }
    }
    public static String inputStr(String msg) {
        String x;
        while (true) {
            System.out.println(msg);
            x = sc.nextLine().trim();
            return x;
        }
    }
    public static String inputStr(String msg, String pattern) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim();
            if (Pattern.matches(pattern, input)) 
                return input;
            else
                System.out.println("Invalid format. Please enter a valid value.");
        }
    }
    public static String inputNonBlankStr(String msg) {
        String x;
        while (true) {
            System.out.println( msg );
            x = sc.nextLine().trim();
            if (x.isEmpty() == false ) 
                return x;
            else System.out.println("Invalid ! Input again.\n");
        }
    }
    public static double inputDoublle(String msg) {
        while (true) {
            System.out.println(msg);
            double x = Double.parseDouble(sc.nextLine());
            if(x>0) return x;
            else System.out.println("Invalid ! Input again.\n");
        }
    }
    

    
}
