/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.platform.net;
import com.corrodinggames.rts.game.units.UnitCategory;

import com.corrodinggames.rts.platform.net.TestCase;
import com.corrodinggames.rts.platform.net.TestRunner;
import com.corrodinggames.rts.gameFramework.pathfinding.AStarSearch;
import com.corrodinggames.rts.gameFramework.pathfinding.FastNodeQueue;
import com.corrodinggames.rts.gameFramework.pathfinding.NodeQueue;

public class k
extends TestCase {
    public com.corrodinggames.rts.gameFramework.pathfinding.AStarNode a(int n2) {
        com.corrodinggames.rts.gameFramework.pathfinding.AStarNode n3 = new com.corrodinggames.rts.gameFramework.pathfinding.AStarNode();
        n3.a((short)n2, (short)0);
        n3.a(0, 0, 0);
        return n3;
    }

    public void a() {
        com.corrodinggames.rts.gameFramework.GlobalState.e("== Testing FastNodeQueue ==");
        FastNodeQueue d2 = new FastNodeQueue();
        this.a(d2);
        com.corrodinggames.rts.gameFramework.GlobalState.e("== Testing FastNodeQueue2 ==");
        NodeQueue e2 = new NodeQueue();
        this.a(e2);
    }

    public void a(AStarSearch j2) {
        int n2;
        com.corrodinggames.rts.gameFramework.pathfinding.AStarNode n3 = this.a(1);
        com.corrodinggames.rts.gameFramework.pathfinding.AStarNode n4 = this.a(2);
        com.corrodinggames.rts.gameFramework.pathfinding.AStarNode n5 = this.a(3);
        com.corrodinggames.rts.gameFramework.pathfinding.AStarNode n6 = this.a(4);
        com.corrodinggames.rts.gameFramework.GlobalState.e("sequential");
        j2.b();
        j2.a(n3);
        j2.a(n4);
        j2.a(n5);
        j2.a(n6);
        TestRunner.a(j2.a(), n3);
        TestRunner.a(j2.a(), n4);
        TestRunner.a(j2.a(), n5);
        TestRunner.a(j2.a(), n6);
        com.corrodinggames.rts.gameFramework.GlobalState.e("reverse sequence");
        j2.b();
        j2.a(n6);
        j2.a(n5);
        j2.a(n4);
        j2.a(n3);
        TestRunner.a(j2.a(), n3);
        TestRunner.a(j2.a(), n4);
        TestRunner.a(j2.a(), n5);
        TestRunner.a(j2.a(), n6);
        com.corrodinggames.rts.gameFramework.GlobalState.e("sequential with noise");
        j2.b();
        j2.a(n3);
        for (n2 = 0; n2 < 1000; ++n2) {
            j2.a(this.a(100 + n2));
        }
        TestRunner.a(j2.a(), n3);
        j2.a(n4);
        j2.a(n5);
        for (n2 = 0; n2 < 1000; ++n2) {
            j2.a(this.a(100 + n2));
        }
        TestRunner.a(j2.a(), n4);
        j2.a(n6);
        TestRunner.a(j2.a(), n5);
        TestRunner.a(j2.a(), n6);
        com.corrodinggames.rts.gameFramework.GlobalState.e("reverse sequence with noise");
        j2.b();
        j2.a(n6);
        j2.a(n5);
        for (n2 = 0; n2 < 1000; ++n2) {
            j2.a(this.a(100 + n2));
        }
        j2.a(n4);
        for (n2 = 0; n2 < 1000; ++n2) {
            j2.a(this.a(100 + n2));
        }
        j2.a(n3);
        TestRunner.a(j2.a(), n3);
        TestRunner.a(j2.a(), n4);
        TestRunner.a(j2.a(), n5);
        TestRunner.a(j2.a(), n6);
    }
}
