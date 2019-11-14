import java.util.ArrayList;

public class ObjectD {

    ArrayList<ObjectA> list;

    public ObjectD(){
        this.list = new ArrayList<ObjectA>();
    }

    public void createElement(ObjectA a1){
        list.add(a1);
    }

    public ArrayList<ObjectA> getList() {
        return list;
    }
}
