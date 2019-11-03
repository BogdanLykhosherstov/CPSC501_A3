import java.util.Scanner;

public class ObjectCreator {
    public static void main(String[] args) {

        System.out.println("Welcome to Object Creator! Select your option.");
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        System.out.println("1. A simple object with only primitives for instance variables.");
        System.out.println("2. An object that contains references to other objects.");
        System.out.println("3. An object that contains an array of primitives.");
        System.out.println("4. An object that contains an array of object references.");
        System.out.println("5. An object that uses an instance of one of Java’s collection classes to refer to several other objects. ");
        

        String option = input.nextLine();
    }
}
