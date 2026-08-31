/*
 * Decompiled with CFR 0.152.
 * 02 原稿: gameFramework/b.java (v19.109 重建 — 碰撞分组)
 */
package com.corrodinggames.rts.gameFramework;

import java.util.HashMap;

public strictfp class CollisionGroup {
    public byte a;
    HashMap b = new HashMap();

    public void a(CollisionGroup b2) {
        this.b.put(b2.a, b2);
    }
}
