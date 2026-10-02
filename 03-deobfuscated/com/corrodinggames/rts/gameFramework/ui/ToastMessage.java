/*
 * 02b f/m.java 直译 (f/m = ToastMessage 显示容器, 03 侧新建)
 */
package com.corrodinggames.rts.gameFramework.ui;

import android.graphics.Paint;
import android.graphics.Typeface;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class ToastMessage {

    public static int a = 95;
    InGameUI b;
    GlobalState c;
    Paint d;
    boolean e;
    ArrayList f = new ArrayList();

    ToastMessage(GlobalState globalState, InGameUI inGameUI) {  // 02b f/m.java L22-26
        this.b = inGameUI;
        this.c = globalState;
        this.a();
    }

    public void a() {  // 02b L28-35 Paint 初始化 (game-lib 混淆 Paint: a/setARGB, a/setAntiAlias, c/setFilterBitmap, a/setTypeface)
        this.d = new Paint();
        this.d.a(255, 255, 255, 255);
        this.d.a(true);
        this.d.c(true);
        this.d.a(Typeface.a(Typeface.c, 1));
        this.c.a(this.d, 16.0f);  // GlobalState.a(Paint,float)
    }

    public synchronized void b() {  // 02b L37-40
        this.e = false;
        this.f.clear();
    }

    public synchronized void c() {  // 02b L42-52
        Iterator iterator = this.f.iterator();
        while (iterator.hasNext()) {
            Notification notification = (Notification) iterator.next();
            if (this.f.size() > 15) {
                iterator.remove();
            }
        }
    }

    public synchronized Notification a(String string, String string2) {  // 02b L54-73
        GlobalState globalState = GlobalState.B();
        Notification notification = new Notification();
        notification.a = string;
        notification.b = string2;
        notification.c = System.currentTimeMillis();
        notification.d = (new SimpleDateFormat("HH:mm:ss")).format(new Date());
        if (string != null && !string.equals("") && !globalState.bQ.showPlayerChatInGame) {
            if (!this.e) {
                this.e = true;
                this.a((String)null, "[WARNING: A player send a chat message, but you have chat muted in your settings]");
            }
            return notification;
        }
        this.c();
        this.f.add(notification);
        return notification;
    }

    public synchronized int a(float f2, int n2) {  // 02b L75-115 (var3.a() 触摸判断深水区简化 TODO)
        GlobalState globalState = GlobalState.B();
        this.c();
        byte by = 20;
        int n3 = (int)(20.0f * globalState.cj);
        boolean bl = false;  // TODO 02b L80: var3.a() 触摸模式 — 简化
        for (int i = this.f.size() - 1; i >= 0; --i) {
            Notification notification = (Notification) this.f.get(i);
            if (notification.b()) {
                String string;
                if (notification.a != null && !notification.a.equals("")) {
                    string = notification.a + ": " + notification.b;
                } else {
                    string = notification.b;
                }
                if (bl) {
                    string = notification.d + ": " + string;
                }
                if (notification.e > 0) {
                    int n4 = notification.a();
                    int n5 = n4 / notification.e;
                    if (n5 < 0) {
                        n5 = 0;
                    }
                    if (n5 < string.length()) {
                        string = string.substring(0, n5);
                    }
                }
                this.d.b(notification.f);
                globalState.bO.a(string, (float)by, (float)n2, this.d);
                n2 += n3;
            }
        }
        return n2;
    }
}
