import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.IdentityHashMap;

public class Serializer {

    private IdentityHashMap<Object,Integer> identityMap = new IdentityHashMap<>();
    private Document document;
    private Integer id = 0;

    public Document serialize(Object object) throws IllegalAccessException {

        document = new Document();
        //REFACTOR POTENTIAL: move set root outside of constructor
        document.setRootElement(new Element("serialized"));

        serializeObject(object);
        return document;

    }

    private Integer getObjectId(Object obj) {
        int objectId = id;

        if (identityMap.containsKey(obj)) {
            objectId = identityMap.get(obj);
        } else {
            identityMap.put(obj, objectId);
            id++;
        }
        return objectId;
    }

    public Element serializeArray(Object object) throws IllegalAccessException {

        Element objectElement = createObjectArrayElement(object, getObjectId(object));

        Class type = object.getClass().getComponentType();

        for (int i = 0; i < Array.getLength(object); i++) {

            if (Array.get(object, i) == null) {
                Element nullElement = createValueElement(null);
                objectElement.addContent(nullElement);
            } else if (type.isPrimitive()) {
                Element valueElement = createValueElement(Array.get(object, i));
                objectElement.addContent(valueElement);
            } else {
                Element referenceElement = createReferenceElement(getObjectId(Array.get(object, i)).toString());
                objectElement.addContent(referenceElement);
                serializeObject(Array.get(object, i));
            }
        }

        return objectElement;
    }


    public void serializeObject(Object object) throws IllegalAccessException {

        Element objectEl = createObjectElement(object,id);
        Integer id = getObjectId(object);

        if (object.getClass().isArray()) {
            objectEl = serializeArray(object);
        } else {

            Field[] fields = object.getClass().getDeclaredFields();
            for (Field f : fields) {

                f.setAccessible(true);
                Element fieldElement = createFieldElement(f);
                Object value = f.get(object);

                if (f.getType().isPrimitive()) {
                    Element element = createValueElement(value);
                    fieldElement.addContent(element);
                } else {
                    Element referenceElement = createReferenceElement(getObjectId(value).toString());
                    fieldElement.addContent(referenceElement);
                    serializeObject(value);
                }
                objectEl.addContent(fieldElement);
            }
        }

        document.getRootElement().addContent(objectEl);
    }
    

    private Element createObjectElement(Object obj, Integer id) {
        Element objectElement = new Element("object");
        objectElement.setAttribute(new Attribute("class", obj.getClass().getName()));
        objectElement.setAttribute(new Attribute("id", id.toString()));
        return objectElement;
    }

    private Element createObjectArrayElement(Object obj, Integer id) {
        Element objectElement = new Element("object");
        objectElement.setAttribute(new Attribute("class", obj.getClass().getName()));
        objectElement.setAttribute(new Attribute("id", id.toString()));
        objectElement.setAttribute(new Attribute("length", Integer.toString(Array.getLength(obj))));
        return objectElement;
    }

    private Element createFieldElement(Field field) {
        Element fieldElement = new Element("field");
        fieldElement.setAttribute(new Attribute("name", field.getName()));
        fieldElement.setAttribute(new Attribute("declaringclass", field.getDeclaringClass().getName()));
        return fieldElement;
    }

    private Element createValueElement(Object valueObj) {
        Element valueElement = new Element("value");
        if (valueObj == null) {
            valueElement.setText("null");
        } else {
            valueElement.setText(valueObj.toString());
        }
        return valueElement;
    }

    private Element createReferenceElement(String id) {
        Element referenceElement = new Element("reference");
        referenceElement.setText(id);
        return referenceElement;
    }


}
