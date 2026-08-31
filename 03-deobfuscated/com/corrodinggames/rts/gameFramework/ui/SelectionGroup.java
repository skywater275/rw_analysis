/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.GameObject;
import com.corrodinggames.rts.gameFramework.BaseGameObject;
import com.corrodinggames.rts.gameFramework.ui.ActionPanel;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.GameObject;
import java.util.ArrayList;
import java.util.Iterator;

public class SelectionGroup
extends BaseGameObject {
    private final ActionPanel i;
    public ArrayList<com.corrodinggames.rts.game.units.UnitInstance> a = new ArrayList<com.corrodinggames.rts.game.units.UnitInstance>();  // 02b f/am.java: ArrayList a (v19.132 泛型化)
    public float b;
    public long c;
    public float d;
    public float e;
    public float f;
    public boolean g;
    public boolean h;

    public SelectionGroup(ActionPanel a2, boolean bl) {
        this.i = a2;
        this.g = bl;
    }

    public void a() {
        com.corrodinggames.rts.game.units.UnitInstance am2 = null;
        for (com.corrodinggames.rts.game.units.UnitInstance am3 : this.a) {
            boolean bl;
            if (am3.isDead || am3.cN != null || !(bl = this.i.a.j(am3)) || !am3.canMove()) continue;
            am2 = am3;
        }
        if (this.c > GlobalState.V() - 700L && am2 != null) {
            this.i.b.b(am2.eo, am2.ep);
        }
        this.c = GlobalState.V();
    }


    @Override
    public void serializeToStream(OutputNetStream as2) {
        this.d();
        as2.a(this.b);
        as2.a(this.c);
        int n2 = this.a.size();
        as2.a(n2);
        for (com.corrodinggames.rts.game.units.UnitInstance am2 : this.a) {
            as2.a(am2);
        }
        as2.c(0);
    }

    public void readFromPacket(InputNetStream k2) {
        this.b = k2.readFloat();
        this.c = k2.i();
        this.a.clear();
        int n2 = k2.readInt();
        for (int i2 = 0; i2 < n2; ++i2) {
            com.corrodinggames.rts.game.units.UnitInstance am2 = k2.o();
            if (am2 == null) continue;
            this.a.add(am2);
        }
        k2.d();
    }

    public void b() {
        this.a.clear();
    }

    public void c() {
        for (GameObject w2 : GameObject.er) {  // 02b w.er (F27: gameFramework/w=GameObject)
            if (!(w2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType)w2;
            if (!y2.cG || this.a.contains(y2)) continue;
            this.a.add(y2);
        }
    }

    public void d() {
        if (this.a.size() == 0) {
            return;
        }
        Iterator iterator = this.a.iterator();
        while (iterator.hasNext()) {
            UnitInstance am2 = (UnitInstance)iterator.next();
            if (!am2.isDead) continue;
            iterator.remove();
        }
    }

    public void e() {
        if (this.a.size() == 0) {
            return;
        }
        ArrayList<UnitInstance> arrayList = new ArrayList<UnitInstance>();
        for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) this.a) {
            UnitInstance am3 = GameObject.a(am2.eh, true);  // 02b w.a(long,boolean)
            if (am3 == null || am3.isDead) continue;
            arrayList.add(am3);
        }
        this.a = arrayList;
    }
}