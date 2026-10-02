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
    /*
     * ★ 2026-10-01 第 69 轮（PENDING RS-48）补齐：本类原先**缺 2 个方法** ✗，
     *   且已有的 `a(String,List)` 方法体**其实是 `b` 的** ✗（据 02b `j/aq.java` 逐行核对：
     *   02b 的 `b` 用 `"-"` ✓、`a` 用 `"_"` ✗）。结构真值取自 `02b-decompiled/.../j/aq.java` ✓。
     *   ⇒ ① 原 `a` 改名 `b` ✓；② 补真正的 `a(String,List)` ✓；③ 补 `a(FFF)F`（= `lerp` ✓）。
     *   命名沿用本文件既有惯用名（`WebAPIClient.a` ↔ 02b `n.a` ✓、`GameUtils.d` ↔ 02b `f.d` ✓）。
     */
    public static float a(float var0, float var1, float var2) {
        return var0 + (var1 - var0) * var2;
    }

    public void a(String string, List list) {
        long l3 = GlobalState.V();
        WebAPIClient.a(list, h + "1", "" + l3);
        WebAPIClient.a(list, g + "2", com.corrodinggames.rts.gameFramework.GameUtils.d("_" + string + (b + c)));
        WebAPIClient.a(list, g + "3", com.corrodinggames.rts.gameFramework.GameUtils.d("_" + string + ((long) (b + c) + l3)));
    }

    public void b(String string, List list) {
        WebAPIClient.a(list, g + "3", com.corrodinggames.rts.gameFramework.GameUtils.d("-" + string + (d + e) + f));
    }

    public void c(String string, List list) {
        if (f > 1000) {
            WebAPIClient.a(list, g + "4", com.corrodinggames.rts.gameFramework.GameUtils.d("+" + string + (d + e) + f));
        }
    }
}
