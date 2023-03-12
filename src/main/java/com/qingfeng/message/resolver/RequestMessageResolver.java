package com.qingfeng.message.resolver;

import com.qingfeng.message.common.Message;
import com.qingfeng.message.common.MessageHead;
import com.qingfeng.message.common.MessageTypeEnum;

import java.util.concurrent.atomic.AtomicInteger;

public class RequestMessageResolver implements Resolver {

    private static final AtomicInteger counter = new AtomicInteger(1);

    @Override
    public boolean support(Message message) {
        return message.getHead().getMessageType() == MessageTypeEnum.REQUEST;
    }

    @Override
    public Message resolve(Message message) {
        // 接收到request消息之后，对消息进行处理，这里主要是将其打印出来
        int index = counter.getAndIncrement();
        System.out.println("[trx: " + message.getHead().getSessionId() + "]"
                + index + ". receive request: " + message.getBody());
        System.out.println("[trx: " + message.getHead().getSessionId() + "]"
                + index + ". attachments: " + message.getAttachments());

        // 处理完成后，生成一个响应消息返回
        MessageHead response = new MessageHead();
        response.setMessageType(MessageTypeEnum.RESPONSE);
        Message msg = new Message();
        msg.setHead(response);
        msg.setBody("how are you!");
        msg.addAttachment("name", "zhangsan");
        msg.addAttachment("age", "18");
        return msg;
    }
}
