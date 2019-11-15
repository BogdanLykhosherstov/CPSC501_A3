import java.util.Scanner;

public class ObjectCreator {

    Scanner input = new Scanner(System.in);  // Create a Scanner object

    public Object createObject() {

        System.out.println("Select your option: ");

        System.out.println("1. A simple object with only primitives for instance variables.");
        System.out.println("2. An object that contains references to other objects.");
        System.out.println("3. An object that contains an array of primitives.");
        System.out.println("4. An object that contains an array of object references.");
        System.out.println("5. An object that uses an instance of one of Java’s collection classes to refer to several other objects. ");
        System.out.println("6. Exit. ");

        String option = input.nextLine();

        Object object = null;

        switch (option) {
            case ("1"):
                object = createObjectA();
                break;
            case ("2"):
                object = createObjectB();
                break;
            case ("3"):
                object = createObjectC();
                break;
            case ("4"):
                object = createObjectD();
                break;
            case ("5"):
                object = createObjectE();
                break;
            case ("6"):
                //object will equal null if nothing is selected
                break;

        }
        System.out.println(" Object Created\n");
        return object;
    }
    private Object createObjectA(){
        System.out.println(" -- Creating new object (ObjectA)");
        System.out.println(" -- Enter the first value for integer field");
        int x = input.nextInt();

        System.out.println(" -- Enter the second value for integer field");
        int y =  input.nextInt();

        return new ObjectA(x,y);

    }

    private Object createObjectB(){

        System.out.println(" Creating object of reference");
        ObjectA objA = (ObjectA) createObjectA();

        return new ObjectB(objA);

    }

    private Object createObjectC(){

        System.out.println(" Enter size of the array: ");
        int size = input.nextInt();
        int [] list = new int[size];

        for(int i=0;i<size;i++){
            System.out.println(" Enter integer value for an array item:");
            int element = input.nextInt();
            list[i]=element;
        }

        return new ObjectC(list);

    }

    private Object createObjectD(){

        System.out.println(" Enter size of the object array: ");
        int size = input.nextInt();
        ObjectA [] list = new ObjectA[size];

        for(int i=0;i<size;i++){

            ObjectA element = (ObjectA) createObjectA();
            list[i]=element;
        }

        return new ObjectD(list);

    }
    private Object createObjectE(){

        System.out.println(" Enter size of the object collection: ");
        int size = input.nextInt();
        ObjectE obj = new ObjectE();

        for(int i=0;i<size;i++){
            ObjectA element = (ObjectA) createObjectA();
            obj.addElement(element);
        }

        return obj;

    }
}
