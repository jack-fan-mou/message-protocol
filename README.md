## Netty 自定义协议
#### 包括自定义协议和心跳监听协议

名称字段字节数描述<br> 
魔数&nbsp;      magicNumber 4    一个固定的数字，一般用于指定当前字节序列是当前类型的协议，比如Java生成的class文件起始就使用0xCAFEBABE作为其标识符，对于本服务，这里将其定义为0x5201314<br><br>
主版本号   mainVersion 1 当前服务器版本代码的主版本号<br><br>
次版本号   subVersion  1  当前服务器版本的次版本号<br><br>
修订版本号 modifyVersion 1  当前服务器版本的修订版本号<br><br>
会话id&nbsp;   sessionId 36     当前请求的会话id，用于将请求和响应串联到一起<br><br>
消息类型    messageType 1   请求：1，表示当前是一个请求消息；响应：2，表示当前是一个响应消息； ping ：3，表示当前是一个Ping消息；Pong：4，表示当前是一个Pong消息；Empty：5，表示当前是一个空消息，该消息不会写入数据管道中；<br><br>
附加数据长度&nbsp;  attachments  不定 附加消息是字符串类型的键值对来表示的，这里首先使用2个字节记录键值对的个数，然后对于每个键和值，都首先使用4个字节记录其长度，然后是具体的数据<br><br>
消息体长度&nbsp;   length 4    字节记录了消息体的长度消息体body不定消息体，服务之间交互所发送或接收的数据，其长度有前面的length指定<br><br>

 基于【<a href="https://my.oschina.net/zhangxufeng/blog/3043768">Netty 自定义协议</a>】 升级完整版本
 
