package com.qingfeng.message.client;

import com.qingfeng.message.common.Message;
import com.qingfeng.message.common.MessageHead;
import com.qingfeng.message.common.MessageTypeEnum;
import com.qingfeng.message.server.MessageServerHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class MessageClientHandler extends MessageServerHandler {

    // 创建一个线程，模拟用户发送消息
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 对于客户端，在建立连接之后，在一个独立线程中模拟用户发送数据给服务端
        executor.execute(new MessageSender(ctx));
    }

    /**
     * 这里userEventTriggered()主要是在一些用户事件触发时被调用，这里我们定义的事件是进行心跳检测的
     * ping和pong消息，当前触发器会在指定的触发器指定的时间返回内如果客户端没有被读取消息或者没有写入
     * 消息到管道，则会触发当前方法
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                // 一定时间内，当前服务没有发生读取事件，也即没有消息发送到当前服务来时，
                // 其会发送一个Ping消息到服务器，以等待其响应Pong消息
                Message message = new Message();
                MessageHead head = new MessageHead();
                head.setMessageType(MessageTypeEnum.PING);
                message.setHead(head);
                ctx.writeAndFlush(message);
            } else if (event.state() == IdleState.WRITER_IDLE) {
                // 如果当前服务在指定时间内没有写入消息到管道，则关闭当前管道
                ctx.close();
            }
        }
    }


    private static final class MessageSender implements Runnable {

        private static final AtomicLong counter = new AtomicLong(1);
        private volatile ChannelHandlerContext ctx;

        public MessageSender(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // 模拟随机发送消息的过程
                    TimeUnit.SECONDS.sleep(new Random().nextInt(3));
                    Message message = new Message();
                    MessageHead head = new MessageHead();
                    head.setMessageType(MessageTypeEnum.REQUEST);
                    message.setHead(head);
                    long count = counter.getAndIncrement();
                    message.setBody("this is client" + count + " message.");
                    message.addAttachment("name", "wang wu" + count);

                    ctx.writeAndFlush(message);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
