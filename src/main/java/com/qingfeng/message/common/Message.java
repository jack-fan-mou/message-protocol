package com.qingfeng.message.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息
 */
public class Message {
    /**
     * 消息头
     */
    private MessageHead head;
    /**
     * 附件
     */
    private Map<String, String> attachments = new HashMap<>();
    /**
     * 消息内容
     */
    private String body;

    public MessageHead getHead() {
        return head;
    }

    public void setHead(MessageHead head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    public Map<String, String> getAttachments() {
        return Collections.unmodifiableMap(attachments);
    }

    public void setAttachments(Map<String, String> attachments) {
        this.attachments.clear();
        if (null != attachments) {
            this.attachments.putAll(attachments);
        }
    }

    public void addAttachment(String key, String value) {
        attachments.put(key, value);
    }
}
