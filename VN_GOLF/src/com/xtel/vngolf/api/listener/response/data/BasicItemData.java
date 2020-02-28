package com.xtel.vngolf.api.listener.response.data;


public class BasicItemData {

	protected Object item;
	
	public BasicItemData(Object item) {
		this.item = item;
	}

	public Object getItem() {
		return item;
	}

	public void setItem(Object item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "ItemData [item=" + item + "]";
	}

	
	
}
