/*
 * 02b j/s.java 直译: 服务器列表加载回调抽象类 (ServerListLoader$1 父类)
 */
package com.corrodinggames.rts.gameFramework.network;

import java.io.BufferedReader;
import java.io.IOException;

abstract class ServerListCallback {
    String d;
    boolean e;
    int f;

    /* 子类 ServerListLoader\.a 抛 IOException → 父类声明 */
    abstract void a(BufferedReader bufferedReader, int n2, String string) throws IOException;

    abstract void a();
}
