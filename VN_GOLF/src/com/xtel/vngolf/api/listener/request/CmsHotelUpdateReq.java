package com.xtel.vngolf.api.listener.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CmsHotelUpdateReq extends BaseReq {
	private int id;
	private String name;
	private String description;
	
}
