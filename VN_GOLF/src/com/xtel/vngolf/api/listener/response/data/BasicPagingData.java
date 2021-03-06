package com.xtel.vngolf.api.listener.response.data;

import com.tbv.utils.db.entities.PagingEntity;

public class BasicPagingData {
	
	protected PagingEntity page_info;
	protected Object items;
	
	public BasicPagingData(PagingEntity page_info, Object items) {
		this.page_info = page_info;
		this.items = items;
	}

	public PagingEntity getPage_info() {
		return page_info;
	}

	public void setPage_info(PagingEntity page_info) {
		this.page_info = page_info;
	}

	public Object getItems() {
		return items;
	}

	public void setItems(Object items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "BasePagingData [page_info=" + page_info + ", items=" + items
				+ "]";
	}
	
}
