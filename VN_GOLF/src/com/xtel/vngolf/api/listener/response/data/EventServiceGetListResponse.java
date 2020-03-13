package com.xtel.vngolf.api.listener.response.data;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class EventServiceGetListResponse {
    private int id;
    private int category_id;
    private String category_name;
    private Date event_date;
    private String event_author;
    private int event_priority;
    private String event_title;
    private String event_content;
    private int language_id;
    private String language_name;
    private String language_sign;
    private String language_description;
    private int status;
}
