/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import android.graphics.Paint;
import android.graphics.Typeface;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.ui.aq;
import com.corrodinggames.rts.gameFramework.ui.BuildWaypoint;
import com.corrodinggames.rts.gameFramework.ui.AttackWaypoint;
import com.corrodinggames.rts.gameFramework.ui.UpgradeWaypoint;
import com.corrodinggames.rts.gameFramework.ui.Waypoint;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public strictfp class WaypointManager {
    private GlobalState a;  // 02b f/ap L16: l a (v19.133f4 ScoreEntry 幻觉修正)
    private Paint b;
    private ArrayList c = new ArrayList();

    public WaypointManager(GlobalState l2) {  // 02b f/ap L21 (v19.133f4 修正)
        this.a = l2;
        this.a();
    }

    public void a() {
        this.b = new Paint();
        this.b.a(255, 255, 255, 255);
        this.b.a(true);
        this.b.c(true);
        this.b.a(Typeface.a(Typeface.c, 1));
        this.a.a(this.b, 14.0f);
    }

    public synchronized void b() {
        this.c.clear();
    }

    public synchronized void a(UnitInstance am2) {  // 02b f/ap L39: a(am) (v19.133f4 SelectionGroup 幻觉修正)
        BuildWaypoint ar2 = new BuildWaypoint(am2.eo, am2.ep, am2.r());
        ar2.c = GlobalState.V();
        this.a(ar2);
    }

    public synchronized void b(UnitInstance am2) {  // 02b f.ap.b(am)
        UpgradeWaypoint at2 = new UpgradeWaypoint(am2.eo, am2.ep, am2.r());
        at2.c = GlobalState.V();
        this.a(at2);
    }

    public synchronized void c(com.corrodinggames.rts.game.units.UnitInstance am2) {  // 02b f/ap.java c(am) javap 铁证
        AttackWaypoint as2 = new AttackWaypoint(am2.eo, am2.ep, am2.bI());
        as2.c = GlobalState.V();
        this.a(as2);
    }

    public synchronized void a(String string) {
        aq aq2 = new aq(string);
        aq2.c = GlobalState.V();
        this.a(aq2);
    }

    public synchronized void a(String string, int n2) {
        aq aq2 = new aq(string);
        aq2.c = GlobalState.V();
        aq2.d = n2;
        aq2.i = true;
        this.a(aq2);
    }

    private void a(Waypoint au2) {
        boolean bl = false;
        for (Waypoint au3 : (java.util.Collection<Waypoint>) (java.util.Collection) this.c) {
            if (!au3.a(au2)) continue;
            au3.b(au2);
            bl = true;
            break;
        }
        if (bl) {
            Collections.sort(this.c);
        } else {
            this.c.add(0, au2);
        }
    }

    public synchronized void a(float f2) {
        this.c();
        GlobalState l2 = GlobalState.B();
        int n2 = (int)(l2.cm - 130.0f * l2.cj);
        int n3 = 20;
        int n4 = (int)(20.0f * l2.cj);
        for (Waypoint au2 : (java.util.Collection<Waypoint>) (java.util.Collection) this.c) {
            String string = au2.a();
            if (!l2.bQ.showWarLogOnScreen && !au2.i) continue;
            if (au2.c + au2.d < System.currentTimeMillis()) break;
            if (au2.h) {
                this.b.a(255, 160, 160, 160);
            } else {
                this.b.a(255, 255, 255, 255);
            }
            l2.bO.a(string, (float)n3, (float)n2, this.b);
            n2 -= n4;
        }
    }

    public synchronized void c() {
        Iterator iterator = this.c.iterator();
        while (iterator.hasNext()) {
            Waypoint au2 = (Waypoint) iterator.next();
            if (au2.c + 20000L >= System.currentTimeMillis()) continue;
            iterator.remove();
        }
    }

    public synchronized void d() {
        if (this.c.isEmpty()) {
            return;
        }
        for (Waypoint au2 : (java.util.Collection<Waypoint>) (java.util.Collection) this.c) {
            if (au2.h) continue;
            au2.h = true;
            this.a.b(au2.e, au2.f);
            break;
        }
    }

}