/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.appFramework;

import android.os.Handler;
import com.corrodinggames.rts.appFramework.GameActivity;
import com.corrodinggames.rts.appFramework.LicenseValidator$1;
import com.corrodinggames.rts.gameFramework.network.GameServerInfo;
import com.corrodinggames.rts.gameFramework.network.WebAPIClient;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.ArrayList;
import java.util.Collections;

public class p
extends GameActivity {
    static p c;
    /* synthetic 字段: 字节码 appFramework/p.class 无 <init> 构造器 (全部方法 static),
       final Handler d 无赋值路径 (恒为 null), 去 final 匹配 javap 事实 (同 DialogHelper 模式) */
    Handler d;
    private Runnable e;

    public static void l() {
        if (c != null) {
            c.d.a(c.e);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ArrayList m() {
        Object object = WebAPIClient.f;
        synchronized (object) {
            GlobalState l2 = GlobalState.B();
            ArrayList<GameServerInfo> arrayList = new ArrayList<GameServerInfo>();
            for (GameServerInfo g2 : l2.bX.bi) {
                arrayList.add(g2);
            }
            Collections.sort(arrayList, new p$1());
            return arrayList;
        }
    }
}
