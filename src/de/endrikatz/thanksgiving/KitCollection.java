package de.endrikatz.thanksgiving;

import java.util.ArrayList;

public class KitCollection {

	/* qnd :) - future: GUI ... or conf */
	private static int[] itemKitLeather = { 298, 299, 300, 301, 286 };
	private static int[] itemKitDiamond = { 310, 311, 312, 313, 276 };
	private static int[] itemKitTools = { 277, 278, 279 };
	private static int[] itemKitToolsPlus = { 276, 277, 278, 279, 310, 311,
			312, 313 };
	private static int[] itemKitMap = { 58, 339, 345 };
	private static int[] itemKitTrain = { 27, 66, 76 };

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
		collection.add(new Kit("tools+", itemKitToolsPlus));
		i++;
		collection.add(new Kit("map", itemKitMap));
		i++;
		collection.add(new Kit("train", itemKitTrain));
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

	/* temp kit */
	public boolean addCustomKit(String[] split) {

		try {
			int[] items = new int[split.length - 1];

			for (int j = 0; j < split.length - 1; j++) {
				items[j] = Integer.parseInt(split[j + 1]);
			}

			collection.add(new Kit(split[0], items));

		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
