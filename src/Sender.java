public class Sender {
    public static void main(String[] args) throws NoSuchFieldException {
        ObjectCreator c = new ObjectCreator();

        // primitive
        ObjectC obj = (ObjectC) c.createObject();
        System.out.println(obj.getClass().getName());
        int[] list = obj.getList();
        for(int i:list){
            System.out.println(i);
        }


    }
}
