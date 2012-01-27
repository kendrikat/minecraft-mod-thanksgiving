package de.endrikatz.thanksgiving;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

public class KitCollection implements ConfigurationSerializable {

	private HashMap<String, Object> collection = new HashMap<String, Object>();

	@SuppressWarnings("unchecked")
	public KitCollection(Map<String, Object> map) {
		this.collection = (HashMap<String, Object>) map.get("collection");
	}

	public KitCollection() {
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<String, Object>();
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
			int[][] items = new int[split.length - 1][2];

			for (int j = 0; j < split.length - 1; j++) {
				String[] val = new String[2];
				val = split[j + 1].split(":");
				items[j][0] = Integer.parseInt(val[0]);
				if (val.length == 2) {
					items[j][1] = Integer.parseInt(val[1]);
				} else {
					items[j][1] = 64;
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
