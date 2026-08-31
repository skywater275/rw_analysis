/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.platform.net;

import com.corrodinggames.rts.platform.net.TestCase;
import com.corrodinggames.rts.platform.net.TestRunner;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;

public class TestGameLogic
extends TestCase {
    public void a() {
        com.corrodinggames.rts.gameFramework.GlobalState.e("== Testing GameLogic ==");
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.by = 1000;
        TestRunner.a(PathfindingUtils.b(1000, 5));
        TestRunner.a(PathfindingUtils.b(1000, 1100));
        TestRunner.a(PathfindingUtils.b(900, 200));
        TestRunner.b(PathfindingUtils.b(-9999, 200));
        TestRunner.b(PathfindingUtils.b(1100, 200));
        TestRunner.b(PathfindingUtils.b(700, 200));
        l2.by = 1000;
        TestRunner.a(PathfindingUtils.a(500, 300));
        TestRunner.b(PathfindingUtils.a(900, 300));
    }
}
