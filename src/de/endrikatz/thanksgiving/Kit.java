package de.endrikatz.thanksgiving;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

public class Kit implements ConfigurationSerializable {

    private String name = "empty";

    private Map<String, Object> items = new HashMap<String, Object>();

    public Kit(String string, String[][] itemKit) {
        name = string;
        for (String[] i : itemKit) {

            items.put(i[0], new Item(i));
        }
    }

    @SuppressWarnings("unchecked")
    public Kit(Map<String, Object> map) {
        this.items = (Map<String, Object>) map.get("items");
        this.name = (String) map.get("name");
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", this.items);
        map.put("name", this.name);
        return map;
    }

    public static Kit deserialize(Map<String, Object> map) {
        return new Kit(map);
    }

    public Map<String, Object> getItems() {
        return items;
    }

    public void setItems(Map<String, Object> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
