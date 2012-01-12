package de.endrikatz.thanksgiving;

import java.util.ArrayList;

public class KitCollection {

	/* qnd :) - future: GUI ... or conf */
	private static int[] itemKitLeather = { 298, 299, 300, 301, 286 };
	private static int[] itemKitDiamond = { 310, 311, 312, 313, 276 };
	private static int[] itemKitTools = { 277, 278, 279 };
	
	/* ... */

	private static ArrayList<Kit> collection = new ArrayList<Kit>();

	public void init() {
		/* TODO ... */
		int i = 0;
		collection.add(new Kit("leather", itemKitLeather));
		i++;
		collection.add(new Kit("diamond", itemKitDiamond));
		i++;
		collection.add(new Kit("tools", itemKitTools));
		i++;
		/* ... */
		System.out.println("Plugin added " + i + " kits");
	}

	public ArrayList<String> getNames() {
		ArrayList<String> names = new ArrayList<String>();
		for (Kit kit : collection) {
			names.add(kit.getName());
		}
		return names;
	}

	public ArrayList<Kit> getCollection() {
		return collection;
	}

}
