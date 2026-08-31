/*
 * Decompiled with CFR 0.152.
 * 02 原稿: gameFramework/k/j.java (v19.110 拆分 — 03 曾错误合并 k.j 声明与 k.d 实现)
 * k.j = 抽象队列基类 (a添加/a取出/b清空), 实现类: FastNodeQueue(k.d) / NodeQueue(k.e)
 */
package com.corrodinggames.rts.gameFramework.pathfinding;

import com.corrodinggames.rts.gameFramework.pathfinding.AStarNode;

public abstract class AStarSearch {
    public abstract void a(AStarNode var1);

    public abstract AStarNode a();

    public abstract void b();
}
