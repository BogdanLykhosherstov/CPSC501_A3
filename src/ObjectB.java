public class ObjectB {

    private ObjectA a1;

    public ObjectB() {}

    // setter
    public ObjectB(ObjectA a1) {
        this.a1 = a1;
    }

    // getter
    public ObjectA getA() {
        return a1;
    }
}