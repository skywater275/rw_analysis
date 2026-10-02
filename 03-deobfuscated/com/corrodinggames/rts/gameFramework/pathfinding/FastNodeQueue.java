/*
 * Decompiled with CFR 0.152.
 * 02 原稿: gameFramework/k/d.java (v19.110 拆分自 03 AStarSearch.java 的错误合并)
 * 测试日志铁证: "Testing FastNodeQueue" — 静态统计字段 a-u 为 DebugUI 性能显示
 */
package com.corrodinggames.rts.gameFramework.pathfinding;

import com.corrodinggames.rts.gameFramework.pathfinding.AStarSearch;
import com.corrodinggames.rts.gameFramework.pathfinding.NodePool;
import com.corrodinggames.rts.gameFramework.pathfinding.AStarNode;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import java.util.PriorityQueue;

public strictfp final class FastNodeQueue
extends AStarSearch {
    public static int a;
    public static int b;
    public static int c;
    public static int d;
    public static int e;
    public static int f;
    public static int g;
    public static double h;
    public static double i;
    int j;
    int k;
    int l;
    int m;
    int n;
    AStarNode[] o = new AStarNode[975];
    CustomArrayList p = new CustomArrayList(100);
    final PriorityQueue q = new PriorityQueue();
    final CustomArrayList r = new CustomArrayList(300);
    int s;
    int t;
    public static int u;

    private void c() {
        int n2;
        int n3;
        if (this.k == this.m) {
            this.d();
            return;
        }
        int n4 = this.n;
        AStarNode[] nArray = this.o;
        if (this.j == -2) {
            for (n3 = 0; n3 <= n4; ++n3) {
                AStarNode n5 = nArray[n3];
                n2 = n5.gScore;
                if (this.k != n2) continue;
                this.j = n3;
                this.k = n2;
                return;
            }
        }
        n3 = -1;
        int n6 = Integer.MAX_VALUE;
        for (n2 = 0; n2 <= n4; ++n2) {
            AStarNode n7 = nArray[n2];
            int n8 = n7.gScore;
            if (n6 <= n8) continue;
            n3 = n2;
            n6 = n8;
        }
        if (this.k != n6) {
            ++g;
        }
        this.j = n3;
        this.k = n6;
    }

    private void a(int n2, AStarNode n3) {
        this.o[n2] = n3;
        int n4 = n3.gScore;
        if (this.j == -1 || this.k >= n4) {
            if (this.k > n4) {
                // empty if block
            }
            if (this.k != n4) {
                ++g;
            }
            this.j = n2;
            this.k = n4;
        }
        if (this.l == -1 || this.m < n4) {
            this.l = n2;
            this.m = n4;
        }
    }

    private void d() {
        this.j = -1;
        this.k = Integer.MAX_VALUE;
        this.l = -1;
        this.m = Integer.MIN_VALUE;
        for (int i = 0; i <= this.n; ++i) {
            AStarNode n2 = this.o[i];
            if (n2 == null) {
                GlobalState.e("n:" + i);
                GlobalState.e("lowestBufferLastIndex:" + this.n);
                throw new RuntimeException("null with n:" + i + ", lowestBufferLastIndex:" + this.n);
            }
            int n3 = n2.gScore;
            if (this.k > n3) {
                this.j = i;
                this.k = n3;
            }
            if (this.m >= n3) continue;
            this.l = i;
            this.m = n3;
        }
    }

    private void e() {
        if (this.n < 30) {
            AStarNode n2;
            AStarNode n3 = (AStarNode)this.q.poll();
            if (n3 != null) {
                this.b(n3);
            }
            if ((n2 = (AStarNode)this.q.peek()) != null) {
                this.s = n2.gScore;
            }
            return;
        }
        this.s = Integer.MAX_VALUE;
        AStarNode n4 = (AStarNode)this.q.peek();
        if (n4 != null) {
            this.s = n4.gScore;
        }
    }

    public FastNodeQueue() {
        this.f();
    }

    private void b(AStarNode n2) {
        ++this.n;
        this.a(this.n, n2);
        if (this.n > a) {
            a = this.n;
        }
    }

    private void c(AStarNode n2) {
        this.q.offer(n2);
        if (n2.gScore < this.s) {
            this.s = n2.gScore;
        }
        if (this.q.size() > b) {
            b = this.q.size();
        }
    }

    @Override
    public void a(AStarNode n2) {
        ++d;
        boolean bl = false;
        if (this.n < this.o.length - 1) {
            bl = true;
        }
        if (bl) {
            if (n2.gScore <= this.s) {
                this.b(n2);
                return;
            }
            this.c(n2);
            return;
        }
        if (n2.gScore < this.m) {
            AStarNode n3 = this.o[this.l];
            this.o[this.l] = n2;
            this.d();
            this.c(n3);
            return;
        }
        this.c(n2);
    }

    @Override
    public AStarNode a() {
        if (this.j == -2) {
            int n2 = this.k;
            this.c();
            ++this.t;
            if (u < this.t) {
                u = this.t;
            }
            ++e;
            if (n2 == this.k) {
                ++f;
            }
        } else {
            this.t = 0;
        }
        if (this.k < this.s && this.j != -1) {
            AStarNode[] nArray = this.o;
            AStarNode n3 = nArray[this.j];
            if (this.n != this.j) {
                nArray[this.j] = nArray[this.n];
                nArray[this.n] = null;
            } else {
                nArray[this.n] = null;
            }
            --this.n;
            this.j = -2;
            return n3;
        }
        AStarNode n4 = (AStarNode)this.q.poll();
        this.e();
        return n4;
    }

    @Override
    public void b() {
        this.a((NodePool)null);
    }

    public void a(NodePool m2) {
        for (int i = 0; i < this.o.length; ++i) {
            if (this.o[i] == null) continue;
            if (m2 != null) {
                m2.a(this.o[i]);
            }
            this.o[i] = null;
        }
        this.n = -1;
        for (AStarNode n2 : (java.util.Collection<AStarNode>) (java.util.Collection) this.q) {
            if (m2 == null) continue;
            m2.a(n2);
        }
        this.q.clear();
        this.f();
    }

    private void f() {
        this.j = -1;
        this.k = Integer.MAX_VALUE;
        this.l = -1;
        this.m = Integer.MIN_VALUE;
        this.s = Integer.MAX_VALUE;
    }
}
