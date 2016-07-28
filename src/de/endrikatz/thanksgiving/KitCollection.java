package de.endrikatz.thanksgiving;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class KitCollection implements ConfigurationSerializable {

    private HashMap<String, Object> collection = new HashMap<>();

    @SuppressWarnings("unchecked")
    public KitCollection(Map<String, Object> map) {
        this.collection = (HashMap<String, Object>) map.get("collection");
    }

    public KitCollection() {
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("collection", this.collection);
        return map;
    }

    public static KitCollection deserialize(Map<String, Object> map) {
        return new KitCollection(map);
    }

    public void init() {

    }

    public Set<String> getNames() {
        return collection.keySet();
    }

    public Map<String, Object> getCollection() {
        return collection;
    }

    public boolean addCustomKit(String[] split) {

        try {
            String[][] items = new String[split.length - 1][2];

            for (int j = 0; j < split.length - 1; j++) {
                String[] val = split[j + 1].split("#");
                items[j][0] = val[0];
                if (val.length == 2) {
                    items[j][1] = val[1];
                } else {
                    items[j][1] = "64";
                }
            }

            collection.put(split[0], new Kit(split[0], items));

        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
