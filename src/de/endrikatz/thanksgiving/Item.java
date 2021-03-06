package de.endrikatz.thanksgiving;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

public class Item implements ConfigurationSerializable {

    private String id;
    private int count;

    public Item(String[] i) {
        this.id = i[0];
        this.count = Integer.parseInt(i[1]);
    }

    public Item(Map<String, Object> map) {
        this.id = (String) map.get("id");
        this.count = (Integer) map.get("count");
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.id);
        map.put("count", this.count);
        return map;
    }

    public static Item deserialize(Map<String, Object> map) {
        return new Item(map);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
