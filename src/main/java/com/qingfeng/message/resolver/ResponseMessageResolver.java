package com.qingfeng.message.resolver;

import com.qingfeng.message.common.Message;
import com.qingfeng.message.common.MessageHead;
import com.qingfeng.message.common.MessageTypeEnum;

import java.util.concurrent.atomic.AtomicInteger;

public class ResponseMessageResolver implements Resolver {

    private static final AtomicInteger counter = new AtomicInteger(1);

    @Override
    public boolean support(Message message) {
        return message.getHead().getMessageType() == MessageTypeEnum.RESPONSE;
    }

    @Override
    public Message resolve(Message message) {
        // 接收到对方服务的响应消息之后，对响应消息进行处理，这里主要是将其打印出来
        int index = counter.getAndIncrement();
        System.out.println("[trx: " + message.getHead().getSessionId() + "]"
                + index + ". receive response: " + message.getBody());
        System.out.println("[trx: " + message.getHead().getSessionId() + "]"
                + index + ". attachments: " + message.getAttachments());

        // 响应消息不需要向对方服务再发送响应，因而这里写入一个空消息
        MessageHead head = new MessageHead();
        head.setMessageType(MessageTypeEnum.EMPTY);

        Message empty = new Message();
        empty.setHead(head);
        return empty;
    }
}
