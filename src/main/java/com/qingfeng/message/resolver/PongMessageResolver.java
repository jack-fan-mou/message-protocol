package com.qingfeng.message.resolver;

import com.qingfeng.message.common.Message;
import com.qingfeng.message.common.MessageHead;
import com.qingfeng.message.common.MessageTypeEnum;

public class PongMessageResolver  implements Resolver {

    @Override
    public boolean support(Message message) {
        return message.getHead().getMessageType() == MessageTypeEnum.PONG;
    }

    @Override
    public Message resolve(Message message) {
        // 接收到pong消息后，不需要进行处理，直接返回一个空的message
        System.out.println("receive pong message: " + System.currentTimeMillis());
        MessageHead head = new MessageHead();
        head.setMessageType(MessageTypeEnum.EMPTY);
        Message empty = new Message();
        empty.setHead(head);
        return empty;
    }
}

