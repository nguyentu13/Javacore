package com.xtel.vngolf.api.common.mail.response;

import lombok.Data;

@Data
public class SendMailResult {
    private String transid;
    private String from;
    private boolean sendSuccess;
    private String desc;
}