package com.qingfeng.message.resolver;

import com.qingfeng.message.common.Message;

public interface Resolver {

    boolean support(Message message);

    Message resolve(Message message);
}
