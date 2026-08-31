/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.actions;
import com.corrodinggames.rts.game.units.actions.GameAction;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.game.units.actions.ActionFilter;
import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.ActionTargetType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.ui.ThemeColors;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

public class ActionWrapper
extends GameAction {
    GameAction wrappedAction;

    public GameAction q_() {
        return this.wrappedAction;
    }
    ActionFilter actionPriority = ActionFilter.emptyActionFilter;
    boolean actionCooldown;
    public int d = 0;
    public boolean e;
    public final int int2 = Color.a(255, 50, 50, 50);


    public float getSortPriority() {
        return this.wrappedAction.m_();
    }


    public int getDescription(GameAction s2) {
        return super.getDescription(s2);
    }

    @Override
    public String getLabel() {
        return this.wrappedAction.b();
    }


    public String getDisplayString(UnitInstance am2) {
        return this.wrappedAction.getDisplayString(am2);
    }


    public String getDescription() {
        return this.wrappedAction.getDescription();
    }

    @Override
    public String e(UnitInstance am2) {
        return this.wrappedAction.e(am2);
    }

    @Override
    public int getResourceCost() {
        return 0;
    }

    @Override
    public int getLabel(UnitInstance am2, boolean bl) {
        return this.wrappedAction.getLabel(am2, bl);
    }


    public boolean isBuildable() {
        return this.wrappedAction.n_();
    }


    public boolean a(UnitInstance am2, boolean bl) {
        if (this.actionCooldown) {
            return this.wrappedAction.a(am2, bl);
        }
        return true;
    }

    @Override
    public int t() {
        return this.wrappedAction.t();
    }


    public void f(UnitInstance am2) {
        this.wrappedAction.f(am2);
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }


    public boolean g(UnitInstance am2) {
        return this.wrappedAction.g(am2);
    }

    @Override
    public boolean getLabel(UnitInstance am2) {
        if (!this.actionPriority.isAvailable(this, am2)) {
            return false;
        }
        return this.wrappedAction.getLabel(am2);
    }

    @Override
    public boolean u() {
        return this.wrappedAction.u();
    }


    public boolean h() {
        return this.wrappedAction.h();
    }

    @Override
    public UnitTypeHandle i() {
        return this.wrappedAction.i();
    }


    public boolean g() {
        return this.wrappedAction.g();
    }


    public ActionTargetType e() {
        return this.wrappedAction.e();
    }


    public ActionCategory f() {
        return this.wrappedAction.f();
    }


    public String getDisplayString() {
        return this.wrappedAction.getDisplayString();
    }

    @Override
    public boolean h_() {
        return this.wrappedAction.h_();
    }


    public void getDescription(UnitInstance am2, ThemeColors ae2, Paint paint, Paint paint2) {
        this.wrappedAction.getDescription(am2, ae2, paint, paint2);
    }


    public void getDescription(UnitInstance am2, ThemeColors ae2) {
        this.wrappedAction.getDescription(am2, ae2);
        UnitTypeHandle as2 = this.wrappedAction.i();
        if (as2 != null && as2 instanceof UnitBuildAction) {
            ModUnitRegistry l2 = (ModUnitRegistry) as2;
            if (l2.J != null) {
                String string = l2.J.a();
                string = GameUtils.a(string, 30);
                ae2.a("\n(mod: " + string + ")", this.int2, true);
            }
        }
    }


    public Texture j() {
        return this.wrappedAction.j();
    }


    public Texture h(UnitInstance am2) {
        return this.wrappedAction.h(am2);
    }

    @Override
    public Rect v() {
        return this.wrappedAction.v();
    }

    @Override
    public UnitInstance i(UnitInstance am2) {
        return this.wrappedAction.i(am2);
    }

    public int hashCode() {
        return this.wrappedAction.hashCode();
    }

    public String toString() {
        return this.wrappedAction.toString();
    }

    public ActionWrapper(GameAction s2, ActionFilter b2) {
        this(s2, b2, false);
    }

    public ActionWrapper(GameAction s2, ActionFilter b2, boolean bl2) {
        super(s2.N());
        this.wrappedAction = s2;
        this.actionPriority = b2;
        this.g = this.wrappedAction.g;
        this.actionCooldown = bl2;
    }

    public GameAction getWrappedAction() {
        return this.wrappedAction;
    }

    @Override
    public boolean x() {
        return true;
    }

    @Override
    public boolean s() {
        if (!this.actionPriority.isAvailable(this, null)) {
            return false;
        }
        if (this.actionCooldown) {
            return this.wrappedAction.s();
        }
        return true;
    }

    @Override
    public UnitTypeHandle y() {
        return this.wrappedAction.y();
    }

    @Override
    public boolean getResourceCost(UnitInstance am2, boolean bl2) {
        return this.wrappedAction.c(am2, bl2);
    }


    public boolean getDescription(UnitInstance am2) {
        return this.wrappedAction.getDescription(am2);
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.getDescription((GameAction) object);
    }
}
