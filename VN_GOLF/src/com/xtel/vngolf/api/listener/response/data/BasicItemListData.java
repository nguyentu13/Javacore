package com.xtel.vngolf.api.listener.response.data;


public class BasicItemListData {
	
	protected Object items;

	public BasicItemListData(Object items) {
		this.items = items;
	}

	public Object getItems() {
		return items;
	}

	public void setItems(Object items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "ItemListData [items=" + items + "]";
	}
	
}
