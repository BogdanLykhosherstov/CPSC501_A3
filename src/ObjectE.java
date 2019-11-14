import java.util.ArrayList;

public class ObjectE {

    ArrayList<ObjectA> list;

    public ObjectE(){
        this.list = new ArrayList<ObjectA>();
    }

    // adder
    public void addElement(ObjectA a1){
        list.add(a1);
    }

    // getter
    public ArrayList<ObjectA> getList() {
        return list;
    }
}
