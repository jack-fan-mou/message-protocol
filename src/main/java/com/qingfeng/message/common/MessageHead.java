package com.qingfeng.message.common;


public class MessageHead {
    /**
     * 魔数
     */
    private int magicNumber = 0x5201314;
    /**
     * 主版本
     */
    private byte mainVersion = 1;
    /**
     * 子版本
     */
    private byte subVersion = 2;
    /**
     * 修该版本
     */
    private byte modifyVersion =4;
    /**
     * sessionId
     */
    private String sessionId;
    /**
     * 消息类型
     */
    private MessageTypeEnum messageType;
    /**
     * 附件
     */

    public int getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    public byte getMainVersion() {
        return mainVersion;
    }

    public void setMainVersion(byte mainVersion) {
        this.mainVersion = mainVersion;
    }

    public byte getSubVersion() {
        return subVersion;
    }

    public void setSubVersion(byte subVersion) {
        this.subVersion = subVersion;
    }

    public byte getModifyVersion() {
        return modifyVersion;
    }

    public void setModifyVersion(byte modifyVersion) {
        this.modifyVersion = modifyVersion;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public MessageTypeEnum getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageTypeEnum messageType) {
        this.messageType = messageType;
    }


}
