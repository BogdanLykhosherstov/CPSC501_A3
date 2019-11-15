import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Tests {


    @Test
    public void serializeTest() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Serializer serializer = new Serializer();
        Document document = serializer.serialize(new ObjectA(4,6));

        assert document.getRootElement().getName().equals("serialized");

        Element child = document.getRootElement().getChildren().get(0);
        assert child.getName().equals("object");
        assert child.getAttribute("class").getValue().equals("ObjectA");
        assert child.getAttribute("id").getValue().equals("0");

        assert child.getChildren().get(0).getChildren().get(0).getText().equals("4");
        assert child.getChildren().get(1).getChildren().get(0).getText().equals("6");
    }

    @Test
    public void deserializeTest() throws Exception {

        File xmlFile = new File("test/ObjectA.xml");
        SAXBuilder saxBuilder = new SAXBuilder();

        Document docA = saxBuilder.build("test/ObjectA.xml");

        Deserializer deserializer = new Deserializer();
        Object objA = deserializer.deserialize(docA);

        assert objA.getClass().getName().equals("ObjectA");

        Field field1 = objA.getClass().getDeclaredFields()[0];
        field1.setAccessible(true);
        assert field1.get(objA).equals(4);

        Field field2 = objA.getClass().getDeclaredFields()[1];
        field2.setAccessible(true);
        assert field2.get(objA).equals(5);
    }

}
