/*
 * v19.133f11: 02b gameFramework/j/a.java 聊天日志 (ChatLog) 最小实现
 * 03 侧原缺失; InGameActivity L268 引用 GameEngine.aC.a() 获取消息文本
 */
package com.corrodinggames.rts.game;

public class ChatLog {  // 02b j/a.java: 聊天消息队列

    private final java.util.concurrent.ConcurrentLinkedQueue a = new java.util.concurrent.ConcurrentLinkedQueue();

    public String a() {  // 02b j/a.java L61-68: 拼接消息文本 (简化 TODO)
        return "";
    }

    public java.util.concurrent.ConcurrentLinkedQueue b() {  // 02b L72-74
        return this.a;
    }

    public void c() {  // 02b L97-99: 清空
        this.a.clear();
    }
}
