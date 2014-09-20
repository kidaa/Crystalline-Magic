package CrystallineApi.Elements;

import java.util.ArrayList;
import java.util.HashMap;

public class ElementRegistry {

    public static ArrayList<ElementBase> Elements = new ArrayList<ElementBase>();
    public static HashMap<String, ElementBase> ElementsId = new HashMap<String, ElementBase>();


    public static void RegisterElement(ElementBase el){
        Elements.add(el);
        ElementsId.put(el.Id, el);
    }

    public static ElementBase GetElement(String id){
        return ElementsId.get(id);
    }

}
