/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.actions;

import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.ActionFilter;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.ActionTargetType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.ui.ThemeColors;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import java.util.ArrayList;

public class BuildAction
extends GameAction {
    public GameAction buildTargetPos;
    public UnitType buildUnitType;
    public ActionFilter buildStages = ActionFilter.emptyActionFilter;
    static com.corrodinggames.rts.gameFramework.utility.UnitRegistry buildTargetPosition;
    static final com.corrodinggames.rts.gameFramework.utility.UnitRegistry buildOrderIndex;

    private void K() {
        GlobalState l2 = GlobalState.B();
        if (buildTargetPosition != null) {
            throw new RuntimeException("savedSelectedUnitsCache!=null");
        }
        buildTargetPosition = l2.bS.bZ;
        buildOrderIndex.clear();
        buildOrderIndex.a((UnitInstance) this.buildUnitType);
        l2.bS.bZ = buildOrderIndex;
    }

    private void L() {
        GlobalState l2 = GlobalState.B();
        if (buildTargetPosition == null) {
            throw new RuntimeException("savedSelectedUnitsCache==null");
        }
        l2.bS.bZ = buildTargetPosition;
        buildTargetPosition = null;
        buildOrderIndex.clear();
    }


    public float m_() {
        return super.m_();
    }

    @Override
    public int getDescription(GameAction s2) {
        return super.getDescription(s2);
    }

    @Override
    public String getLabel() {
        return this.buildTargetPos.getLabel();
    }


    public String d(UnitInstance am2) {
        return this.buildTargetPos.getDisplayString((UnitInstance) this.buildUnitType);
    }

    @Override
    public String getDescription() {
        String string = this.buildTargetPos.getDescription();
        return string;
    }

    @Override
    public String e(UnitInstance am2) {
        return this.buildTargetPos.e((UnitInstance) this.buildUnitType);
    }

    @Override
    public int getResourceCost() {
        return this.buildTargetPos.getResourceCost();
    }

    @Override
    public int getLabel(UnitInstance am2, boolean bl) {
        return this.buildTargetPos.getLabel((UnitInstance) this.buildUnitType, bl);
    }


    public boolean isBuildable() {
        return this.buildTargetPos.n_();
    }

    @Override
    public boolean getDescription(UnitInstance am2, boolean bl) {
        return this.buildTargetPos.a((UnitInstance) this.buildUnitType, bl);
    }

    @Override
    public int t() {
        return this.buildTargetPos.t();
    }


    public void f(UnitInstance am2) {
        this.buildTargetPos.f((UnitInstance) this.buildUnitType);
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }


    public boolean g(UnitInstance am2) {
        return this.buildTargetPos.g((UnitInstance) this.buildUnitType);
    }

    @Override
    public boolean u() {
        return this.buildTargetPos.u();
    }


    public boolean h() {
        return this.buildTargetPos.h();
    }

    @Override
    public UnitTypeHandle i() {
        return this.buildTargetPos.i();
    }


    public boolean g() {
        return this.buildTargetPos.g();
    }


    public ActionTargetType e() {
        return this.buildTargetPos.e();
    }


    public ActionCategory f() {
        return this.buildTargetPos.f();
    }


    public String d() {
        this.K();
        String string = this.buildTargetPos.getDisplayString();
        this.L();
        return string;
    }

    @Override
    public boolean h_() {
        return this.buildTargetPos.h_();
    }

    @Override
    public void getDescription(UnitInstance am2, ThemeColors ae2, Paint paint, Paint paint2) {
        this.K();
        this.buildTargetPos.getDescription((UnitInstance) this.buildUnitType, ae2, paint, paint2);
        this.L();
    }

    @Override
    public void getDescription(UnitInstance am2, ThemeColors ae2) {
        this.K();
        this.buildTargetPos.getDescription((UnitInstance) this.buildUnitType, ae2);
        this.L();
    }


    public Texture j() {
        return this.buildTargetPos.j();
    }


    public Texture h(UnitInstance am2) {
        return this.buildTargetPos.h(am2);
    }

    @Override
    public Rect v() {
        return this.buildTargetPos.v();
    }

    @Override
    public UnitInstance i(UnitInstance am2) {
        return this.buildTargetPos.i((UnitInstance) this.buildUnitType);
    }

    public int hashCode() {
        return this.buildTargetPos.hashCode();
    }

    public String toString() {
        return this.buildTargetPos.toString();
    }

    public BuildAction(GameAction s2, UnitType y2, ActionId c2) {  // javap: g(s,y,c) — y=UnitType (v19.133f6 修正, StopAction 为幻觉)
        super(c2);
        this.buildTargetPos = s2;
        this.buildUnitType = y2;
        this.g = this.buildTargetPos.g;
    }

    public GameAction getWrappedAction() {
        return this.buildTargetPos;
    }

    @Override
    public boolean x() {
        return this.buildTargetPos.x();
    }

    @Override
    public boolean s() {
        return this.buildTargetPos.s();
    }

    @Override
    public UnitTypeHandle y() {
        return this.buildTargetPos.y();
    }

    @Override
    public ActionId z() {
        return this.buildTargetPos.N();
    }

    @Override
    public void getDescription(UnitInstance am2, UnitInstance am3) {
        super.getDescription(am2, am3);
    }

    @Override
    public boolean a(UnitInstance am2, PlayerState n2) {
        return this.buildTargetPos.a((UnitInstance) this.buildUnitType, n2);
    }

    @Override
    public boolean A() {
        return this.buildTargetPos.A();
    }

    @Override
    public boolean getDescription(UnitInstance am2) {
        return this.buildTargetPos.getDescription((UnitInstance) this.buildUnitType);
    }

    @Override
    public CustomActionBase B() {
        return this.buildTargetPos.B();
    }

    @Override
    public String j(UnitInstance am2) {
        return this.buildTargetPos.j((UnitInstance) this.buildUnitType);
    }


    public boolean d(UnitInstance am2, boolean bl) {
        return this.buildTargetPos.getDisplayString((UnitInstance) this.buildUnitType, bl);
    }

    @Override
    public boolean k(UnitInstance am2) {
        return this.buildTargetPos.k((UnitInstance) this.buildUnitType);
    }

    @Override
    public boolean l(UnitInstance am2) {
        return this.buildTargetPos.l((UnitInstance) this.buildUnitType);
    }

    @Override
    public boolean C() {
        return this.buildTargetPos.C();
    }

    @Override
    public boolean D() {
        return this.buildTargetPos.D();
    }

    @Override
    public UnitTypeHandle E() {
        return this.buildTargetPos.E();
    }

    @Override
    public boolean F() {
        return this.buildTargetPos.F();
    }


    public boolean m(UnitInstance am2) {
        return this.buildTargetPos.m((UnitInstance) this.buildUnitType);
    }

    @Override
    public boolean checkUnitTypeFilterFor(UnitInstance am2) {
        return this.buildTargetPos.n((UnitInstance) this.buildUnitType);
    }

    @Override
    public boolean getResourceCost(UnitInstance am2, boolean bl) {
        return this.buildTargetPos.c((UnitInstance) this.buildUnitType, bl);
    }

    @Override
    public boolean o(UnitInstance am2) {
        return this.buildTargetPos.o((UnitInstance) this.buildUnitType);
    }

    @Override
    public boolean G() {
        return this.buildTargetPos.G();
    }

    @Override
    public void getResourceCost(UnitInstance am2) {
        this.buildTargetPos.c((UnitInstance) this.buildUnitType);
    }

    @Override
    public float l() {
        return this.buildTargetPos.l();
    }

    @Override
    public int m() {
        return this.buildTargetPos.m();
    }

    @Override
    public boolean H() {
        return this.buildTargetPos.H();
    }

    @Override
    public boolean I() {
        return this.buildTargetPos.I();
    }

    @Override
    public float p(UnitInstance am2) {
        return this.buildTargetPos.p((UnitInstance) this.buildUnitType);
    }


    public ArrayList q(UnitInstance am2) {
        return this.buildTargetPos.getQueuedActions((UnitInstance) this.buildUnitType);
    }


    public boolean r(UnitInstance am2) {
        if (!this.buildStages.isAvailable(this, am2)) {
            return false;
        }
        return this.buildTargetPos.r((UnitInstance) this.buildUnitType);
    }

    @Override
    public boolean getLabel(UnitInstance am2) {
        if (!this.buildStages.isAvailable(this, am2)) {
            return false;
        }
        return this.buildTargetPos.b((UnitInstance) this.buildUnitType);
    }

    @Override
    public int J() {
        return this.buildTargetPos.J();
    }

    @Override
    public boolean s(UnitInstance am2) {
        return this.buildTargetPos.s((UnitInstance) this.buildUnitType);
    }

    @Override
    public boolean t(UnitInstance am2) {
        return this.buildTargetPos.t((UnitInstance) this.buildUnitType);
    }

    public boolean getDescription(BuildAction g2) {
        return this.buildTargetPos == g2.buildTargetPos && this.buildUnitType == g2.buildUnitType && this.N() == g2.N() && this.buildStages == g2.buildStages;
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.getDescription((GameAction) object);
    }

    static {
        buildOrderIndex = new com.corrodinggames.rts.gameFramework.utility.UnitRegistry();
    }
}
