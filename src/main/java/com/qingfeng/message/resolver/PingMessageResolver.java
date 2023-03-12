package com.qingfeng.message.resolver;

import com.qingfeng.message.common.Message;
import com.qingfeng.message.common.MessageHead;
import com.qingfeng.message.common.MessageTypeEnum;

public class PingMessageResolver implements Resolver {

    @Override
    public boolean support(Message message) {
        return message.getHead().getMessageType() == MessageTypeEnum.PING;
    }

    @Override
    public Message resolve(Message message) {
        // 接收到ping消息后，返回一个pong消息返回
        System.out.println("receive ping message: " + System.currentTimeMillis());
        Message pong = new Message();
        MessageHead head = new MessageHead();
        head.setMessageType(MessageTypeEnum.PONG);
        pong.setHead(head);
        return pong;
    }
}