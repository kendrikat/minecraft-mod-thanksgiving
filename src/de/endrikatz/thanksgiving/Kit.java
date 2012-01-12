package de.endrikatz.thanksgiving;

import java.util.ArrayList;

public class Kit {

	private String name = "empty";

	private ArrayList<Integer> items = new ArrayList<Integer>();

	public Kit(String string, int[] itemKitLeather) {
		name = string;
		for (int i : itemKitLeather) {
			items.add(i);
		}
	}

	public ArrayList<Integer> getItems() {
		return items;
	}

	public void setItems(ArrayList<Integer> items) {
		this.items = items;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
