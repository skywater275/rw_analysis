/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.weapons;
import com.corrodinggames.rts.game.units.actions.StopAction;
import com.corrodinggames.rts.game.units.actions.ActionId;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.weapons.UnitComponent;
import com.corrodinggames.rts.game.units.weapons.ComponentType;
import com.corrodinggames.rts.game.units.weapons.ComponentUpdater;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public class TimerComponent
extends UnitComponent {
    int b;
    ActionId c = ActionId.a;

    public TimerComponent() {
    }

    public TimerComponent(int n2, ActionId c2) {
        super(n2);
        int n3;
        this.c = c2;
        GlobalState l2 = GlobalState.B();
        this.b = n3 = l2.by;
    }

    @Override
    public ComponentType b() {
        return com.corrodinggames.rts.game.units.weapons.ComponentType.b;
    }

    public boolean a(ActionId c2) {
        if (this.c == ActionId.a) {
            return true;
        }
        return this.c == c2;
    }

    public float c() {
        int n2 = this.a - this.b;
        if (n2 <= 0) {
            return 1.0f;
        }
        GlobalState l2 = GlobalState.B();
        int n3 = l2.by;
        int n4 = this.a - n3;
        return (float)n4 / (float)n2;
    }

    public static void a(UnitType y2, ActionId c2, int n2) {
        GlobalState l2 = GlobalState.B();
        int n3 = l2.by + n2;
        TimerComponent e2 = new TimerComponent(n3, c2);
        ComponentUpdater.a(y2, e2);
    }

    public static int a(UnitInstance am2, ActionId c2) {
        if (!(am2 instanceof UnitType)) {
            return 0;
        }
        UnitType y2 = (UnitType)am2;
        CustomArrayList m2 = y2.bp;
        if (m2 == null) {
            return 0;
        }
        TimerComponent e2 = TimerComponent.b(am2, c2);
        if (e2 == null) {
            return 0;
        }
        int n2 = e2.d();
        return n2;
    }

    public int d() {
        GlobalState l2 = GlobalState.B();
        int n2 = l2.by;
        int n3 = this.a - n2;
        return n3;
    }

    public static TimerComponent b(UnitInstance am2, ActionId c2) {
        if (!(am2 instanceof UnitType)) {
            return null;
        }
        UnitType y2 = (UnitType)am2;
        CustomArrayList m2 = y2.bp;
        if (m2 == null) {
            return null;
        }
        GlobalState l2 = GlobalState.B();
        int n2 = l2.by;
        TimerComponent e2 = null;
        Object[] objectArray = m2.a();
        for (int i2 = m2.a - 1; i2 >= 0; --i2) {
            TimerComponent e3;
            UnitComponent a2 = (UnitComponent) objectArray[i2];
            if (!(a2 instanceof TimerComponent) || !(e3 = (TimerComponent) a2).a(c2) || e3.a <= n2) continue;
            n2 = e3.a;
            e2 = e3;
        }
        if (e2 == null) {
            return null;
        }
        return e2;
    }

    @Override
    public void a(UnitType y2, OutputNetStream as2) {
        ActionId.a(as2, this.c);
        as2.a(this.b);
        super.a(y2, as2);
    }

    @Override
    public void a(UnitType y2, InputNetStream k2) {
        this.c = ActionId.a(k2);
        if (this.c == null) {
            this.c = ActionId.a;
        }
        this.b = k2.readInt();
        super.a(y2, k2);
    }

    public static void c(UnitInstance am2, ActionId c2) {
        if (!(am2 instanceof UnitType)) {
            return;
        }
        UnitType y2 = (UnitType)am2;
        CustomArrayList m2 = y2.bp;
        if (m2 == null) {
            return;
        }
        GlobalState l2 = GlobalState.B();
        int n2 = l2.by;
        Object[] objectArray = m2.a();
        for (int i2 = m2.a - 1; i2 >= 0; --i2) {
            UnitComponent a2 = (UnitComponent) objectArray[i2];
            if (!(a2 instanceof TimerComponent)) continue;
            TimerComponent e2 = (TimerComponent) a2;
            if (c2 != ActionId.a && !e2.a(c2)) continue;
            e2.a = n2 - 1;
        }
    }


}
