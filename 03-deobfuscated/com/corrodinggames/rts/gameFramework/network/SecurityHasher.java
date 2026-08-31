/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;
import com.corrodinggames.rts.gameFramework.TeamColor;

import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.PlayerConnect;
import com.corrodinggames.rts.gameFramework.network.WebAPIClient;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.List;

public class SecurityHasher {
    public static SecurityHasher a = new SecurityHasher();
    public static int b = 2;
    static int c = 3;
    static int d = 2;
    static int e = 3;
    public static int f = 4;
    static String g = "tx";
    static String h = "_";
    public static int i = 55;
    public static int j = 66;
    public static int k = 100;
    public static boolean l = true;

    public static void a(PacketDecoder c2) {
        // 02b j/aq.java L45-53: 连接完整性检查
        if (c2.N) {
            long l2 = com.corrodinggames.rts.gameFramework.GlobalState.V();
            if (com.corrodinggames.rts.gameFramework.GlobalState.B().bx > -5) {
                c2.O = com.corrodinggames.rts.gameFramework.GameUtils.a(0.0f, 0.0f, (float) k, 0.0f) > 10.0f;
            }
        }
    }
    public void a(String string, List list) {
        WebAPIClient.a(list, g + "3", com.corrodinggames.rts.gameFramework.GameUtils.d("-" + string + (d + e) + f));
    }

    public void c(String string, List list) {
        if (f > 1000) {
            WebAPIClient.a(list, g + "4", com.corrodinggames.rts.gameFramework.GameUtils.d("+" + string + (d + e) + f));
        }
    }
}
