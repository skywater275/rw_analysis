/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.platform.net;

import com.corrodinggames.rts.platform.net.TestCase;
import com.corrodinggames.rts.platform.net.TestRunner;
import com.corrodinggames.rts.gameFramework.ExtraManager;
import com.corrodinggames.rts.gameFramework.GameUtils;

public class TestMath
extends TestCase {
    int a;

    public void a() {
        int n2;
        com.corrodinggames.rts.gameFramework.GlobalState.e("Running unit tests - maths (v3)");
        GameUtils.smoothstep(100.0f, 100.0f);
        GameUtils.smoothstep(0.0f, 100.0f);
        GameUtils.smoothstep(100.0f, 0.0f);
        GameUtils.smoothstep(0.0f, -100.0f);
        GameUtils.smoothstep(-100.0f, 0.0f);
        GameUtils.smoothstep(0.0f, 0.0f);
        com.corrodinggames.rts.gameFramework.GlobalState.e("fast_atan2 - NaN");
        GameUtils.smoothstep(Float.NaN, 0.0f);
        GameUtils.smoothstep(0.0f, Float.NaN);
        GameUtils.smoothstep(Float.NaN, Float.NaN);
        com.corrodinggames.rts.gameFramework.GlobalState.e("fast_atan2 - Max");
        GameUtils.smoothstep(Float.MAX_VALUE, 0.0f);
        GameUtils.smoothstep(Float.MIN_VALUE, 0.0f);
        GameUtils.smoothstep(0.0f, Float.MAX_VALUE);
        GameUtils.smoothstep(0.0f, Float.MIN_VALUE);
        com.corrodinggames.rts.gameFramework.GlobalState.e("fast_atan2 - NaN+Max");
        GameUtils.smoothstep(Float.MAX_VALUE, Float.NaN);
        GameUtils.smoothstep(Float.MIN_VALUE, Float.MAX_VALUE);
        GameUtils.smoothstep(Float.MAX_VALUE, Float.MIN_VALUE);
        GameUtils.smoothstep(900000.0f, 900000.0f);
        GameUtils.smoothstep(3.4028236E33f, 3.4028236E33f);
        GameUtils.smoothstep(3.4028236E34f, 3.4028236E34f);
        GameUtils.smoothstep(3.4028234E35f, 3.4028234E35f);
        GameUtils.smoothstep(3.4028236E36f, 3.4028236E36f);
        GameUtils.smoothstep(3.4028235E37f, 3.4028235E37f);
        GameUtils.smoothstep(Float.MAX_VALUE, Float.MAX_VALUE);
        com.corrodinggames.rts.gameFramework.GlobalState.e("fast_atan2 - max,max");
        GameUtils.smoothstep(Float.MAX_VALUE, Float.MAX_VALUE);
        GameUtils.smoothstep(Float.MIN_VALUE, Float.MIN_VALUE);
        com.corrodinggames.rts.gameFramework.GlobalState.e("cos/sin");
        TestRunner.a(GameUtils.cosFast(0.0f), 1.0f);
        TestRunner.a(GameUtils.cosFast(360.0f), 1.0f);
        TestRunner.a(GameUtils.cosFast(10800.0f), 1.0f);
        TestRunner.a(GameUtils.cosFast(45.0f), 0.70710677f);
        TestRunner.a(GameUtils.cosFast(90.0f), 0.0f);
        TestRunner.a(GameUtils.cosFast(450.0f), 0.0f);
        TestRunner.a(GameUtils.cosFast(10890.0f), 0.0f);
        TestRunner.a(GameUtils.sinFast(0.0f), 0.0f);
        TestRunner.a(GameUtils.sinFast(90.0f), 1.0f);
        GameUtils.cosFast(-999999.0f);
        GameUtils.cosFast(999999.0f);
        GameUtils.cosFast(Float.MAX_VALUE);
        GameUtils.cosFast(Float.MIN_VALUE);
        GameUtils.sinFast(Float.MAX_VALUE);
        GameUtils.sinFast(Float.MIN_VALUE);
        com.corrodinggames.rts.gameFramework.GlobalState.e("diff sin(0):  " + String.format("%.12f", Float.valueOf(GameUtils.sinFast(0.0f) - (float)StrictMath.sin(0.0))));
        com.corrodinggames.rts.gameFramework.GlobalState.e("diff sin(45): " + String.format("%.12f", Float.valueOf(GameUtils.sinFast(45.0f) - (float)StrictMath.sin(0.7853981633974483))));
        com.corrodinggames.rts.gameFramework.GlobalState.e("diff sin(90): " + String.format("%.12f", Float.valueOf(GameUtils.sinFast(90.0f) - (float)StrictMath.sin(1.5707963267948966))));
        com.corrodinggames.rts.gameFramework.GlobalState.e("diff sin(180):" + String.format("%.12f", Float.valueOf(GameUtils.sinFast(180.0f) - (float)StrictMath.sin(Math.PI))));
        com.corrodinggames.rts.gameFramework.GlobalState.e("diff sin(360):" + String.format("%.12f", Float.valueOf(GameUtils.sinFast(360.0f) - (float)StrictMath.sin(Math.PI * 2))));
        com.corrodinggames.rts.gameFramework.GlobalState.e("Testing squareroot");
        for (n2 = 0; n2 < 1005; ++n2) {
            TestRunner.a((float)GameUtils.a(n2), GameUtils.d(GameUtils.a((float)n2)));
        }
        n2 = 5;
        int n3 = 0;
        com.corrodinggames.rts.gameFramework.GlobalState.e("=== cos/sin tests (runs:" + n2 + ")");
        Long l2 = ExtraManager.a();
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < 2000; ++j) {
                if (GameUtils.cosFast(j) == 0.0f) {
                    ++n3;
                }
                if (GameUtils.sinFast(j) != 0.0f) continue;
                ++n3;
            }
        }
        Long l3 = ExtraManager.a();
        double d2 = ExtraManager.a(l2, (long)l3);
        this.a += n3;
        com.corrodinggames.rts.gameFramework.GlobalState.e("Took: " + d2);
    }
}
