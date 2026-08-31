/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.actions;
import com.corrodinggames.rts.game.TagFilter;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.game.units.custom.actions.base.ActionCost;
import com.corrodinggames.rts.game.units.custom.actions.base.ActionSequence;
import com.corrodinggames.rts.game.units.custom.ModifierApplier;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.ActionTargetType;
import com.corrodinggames.rts.game.units.actions.AbstractBuildAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.actions.d;
import com.corrodinggames.rts.game.units.custom.actions.e;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.ActionBinding;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.ui.ActionPanel;
import com.corrodinggames.rts.gameFramework.ui.UnitStateTracker;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class g
extends AbstractBuildAction {
    public d a;
    public ActionBinding groupName;
    public e actionList = e.b;  // 02b a/g.java: public e c 闁?缂侇偉顕ч悗?e 闁哄鐭俊?(ActionCost 濞戞挸鎼径鐔烘喆?

    public g(d d2, ActionBinding v2) {
        super((String)null);
        String string = "";
        if (d2.actionRange != null) {
            string = string + d2.actionRange;
        }
        string = string + "_" + d2.a;
        if (d2.actionName != null) {
            string = d2.actionName;
        }
        this.getDescription(string);  // 02b s.java L88: a(String) = getDescription(String)
        this.a = d2;
        this.groupName = v2;
        if (d2.J != null) {
            this.groupName = d2.J;
        }
        this.actionList = d2.aN;
        if (this.actionList == e.a) {
            boolean bl2 = false;
            boolean bl3 = false;
            if (d2.ag != null && d2.ah == null) {
                bl3 = true;
            }
            if (d2.actionSound.d()) {
                bl2 = true;
                this.actionList = e.c;
            }
            this.actionList = bl2 && !bl3 ? e.c : e.d;
            if (d2.I != null) {
                this.actionList = e.e;
            }
        }
    }

    @Override
    public UnitConfig P() {  // 02b a/g.java L71: h P() (h=UnitConfig)
        return this.a.requiredUpgrade;
    }

    @Override
    public boolean F() {
        return true;
    }


    public boolean d(UnitInstance am2, boolean bl2) {
        return this.a.M;
    }

    @Override
    public boolean k(UnitInstance am2) {
        return this.a.O;
    }

    @Override
    public boolean l(UnitInstance am2) {
        return this.a.P;
    }

    @Override
    public boolean u() {
        return super.u();
    }


    public boolean a(UnitInstance am2, boolean bl2) {  // 02b a/g.java L95: a(am,boolean) (reset 濞戞捇缂氶銈夊触?
        CustomUnitType j2 = (CustomUnitType) am2;
        if (!this.a.N && j2.a(this.N(), bl2) > 0) {
            return false;
        }
        if (this.a.minSelectedUnits != null && (bl2 && this.Q() ? !this.a.minSelectedUnits.read(j2) : !this.a.minSelectedUnits.read(j2))) {  // 02b L102: an.a(u,y) 缁犫偓閸?read
            return false;
        }
        return super.a(am2, bl2);  // 02b L110: super.a(var1,var2)
    }


    public boolean g(UnitInstance am2) {
        if (this.reset(am2, -1)) {
            return true;
        }
        return super.g(am2);
    }

    public boolean reset(UnitInstance am2, int n2) {
        if (this.a.isEnabledByDefault != null && (n2 == -1 || n2 == 1)) {
            if (!(am2 instanceof CustomUnitType)) {
                GlobalState.reportProblem("CustomActionConfig lockedInGame:" + am2.r().i() + " is not ActionPanel custom unit");
                return false;
            }
            if (this.a.isEnabledByDefault.read((CustomUnitType) am2)) {
                return true;
            }
        }
        if (this.a.B != null && (n2 == -1 || n2 == 2)) {
            if (!(am2 instanceof CustomUnitType)) {
                GlobalState.reportProblem("CustomActionConfig lockedInGame:" + am2.r().i() + " is not ActionPanel custom unit");
                return false;
            }
            if (this.a.B.read((CustomUnitType) am2)) {
                return true;
            }
        }
        if (this.a.D != null && (n2 == -1 || n2 == 3)) {
            if (!(am2 instanceof CustomUnitType)) {
                GlobalState.reportProblem("CustomActionConfig lockedInGame:" + am2.r().i() + " is not ActionPanel custom unit");
                return false;
            }
            if (this.a.D.read((CustomUnitType) am2)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String j(UnitInstance am2) {
        if (this.reset(am2, 1) && this.a.A != null) {
            return this.a.A.b(am2);
        }
        if (this.reset(am2, 2) && this.a.C != null) {
            return this.a.C.b(am2);
        }
        if (this.reset(am2, 3) && this.a.E != null) {
            return this.a.E.b(am2);
        }
        return super.j(am2);
    }


    public boolean r(UnitInstance am2) {
        CustomUnitType j2 = (CustomUnitType) am2;
        if (this.a.maxSelectedUnits != null) {
            if (this.Q()) {
                return this.a.maxSelectedUnits.read(j2);  // 02b L161: an.a(v,y)
            }
            return this.a.maxSelectedUnits.read(j2);
        }
        return super.b(am2);
    }


    public boolean b(UnitInstance am2) {
        CustomUnitType j2 = (CustomUnitType) am2;
        if (this.a.maxSelectedUnits != null) {
            return this.a.maxSelectedUnits.read(j2);
        }
        return super.b(am2);
    }


    public boolean reset(UnitInstance am2, PlayerState n2) {
        if (!this.a.actionButtonPos && !this.a.showInMenu) {
            return false;
        }
        if (am2.player.d(n2)) {
            return this.a.actionButtonPos;
        }
        return this.a.showInMenu;
    }


    public CustomActionBase r_() {
        CustomActionBase b2 = this.h.b();
        if (b2 != null) {
            return b2;
        }
        return this.a.actionAnimation;
    }


    public int b(UnitInstance am2, boolean bl2) {
        if (this.a.aI) {
            return this.a.actionSound.a(am2, true);
        }
        return super.getLabel(am2, bl2);  // 02b L179: super.b(var1,var2) = getLabel(UI,boolean)
    }


    public String getDisplayString() {  // 02b a/g.java L182: d() = getDisplayString()
        return super.getDisplayString();
    }


    @Override
    public String getLabel() {
        String string = null;
        if (this.a.actionIcon != null) {
            string = this.a.actionIcon.b();
        }
        return string;
    }


    public String getDisplayString(UnitInstance am2) {  // 02b a/g.java L195: d(am) = getDisplayString(UI)
        UnitTypeHandle as2;
        String string = null;
        if (this.a.actionIcon != null) {
            string = this.a.actionIcon.b(am2);
        }
        if (this.a.actionCategory != null && (as2 = this.a.actionCategory.getTypeOrNull(am2)) != null) {
            if (string == null) {
                string = "";
            } else if (!string.equals("")) {
                string = string + " ";
            }
            string = string + as2.e();
        }
        if (this.a.requiresTarget != null) {
            if (string == null) {
                string = "";
            } else if (!string.equals("")) {
                string = string + " ";
            }
            string = string + this.a.requiresTarget.getLocalizedText();  // 02b bb.b() = getLocalizedText (L221)
        }
        return string;
    }


    public String reset() {
        String string = null;
        if (this.a.targetType != null) {
            string = this.a.targetType.b();
        }
        return string;
    }

    @Override
    public String getDescription() {  // 02b a/g.java L227-234: this.a.i.b() = targetType.b()
        String string = null;
        if (this.a.targetType != null) {
            string = this.a.targetType.b();
        }
        return string;
    }

    @Override
    public String e(UnitInstance am2) {
        UnitTypeHandle object;  // 02b L242-251: as var3 (getTypeOrNull 返回 UnitTypeHandle)
        String string = null;
        if (this.a.targetType != null) {
            string = this.a.targetType.b(am2);
        }
        if (this.a.actionPriority != null && (object = this.a.actionPriority.getTypeOrNull(am2)) != null) {
            if (string == null) {
                string = "";
            } else if (!string.equals("")) {
                string = string + " ";
            }
            string = string + object.f();
        }
        if (this.a.actionCooldown != null) {
            UnitInstance am7 = this.a.actionCooldown.getUnitReferenceOrNull(am2);
            if (am7 != null) {
                if (string == null) {
                    string = "";
                } else if (!string.equals("")) {
                    string = string + "\n\n";
                }
                boolean bl2 = false;
                String string2 = com.corrodinggames.rts.gameFramework.ui.ActionPanel.a(am7, false, false, bl2);
                string = string + string2;
            } else {
                UnitInstance am3 = this.a.actionCooldown.getUnitOrSharedUnit(am2);
                if (am3 != null) {
                    if (string == null) {
                        string = "";
                    } else if (!string.equals("")) {
                        string = string + "\n\n";
                    }
                    boolean bl3 = true;
                    String string3 = com.corrodinggames.rts.gameFramework.ui.ActionPanel.a(am3, false, false, bl3);
                    string = string + string3;
                }
            }
        }
        return string;
    }

    public boolean L() {
        return this.a.U;
    }

    @Override
    public float K() {
        if (this.a.S >= 1.0f) {
            return 1000.0f;
        }
        return this.a.S;
    }

    @Override
    public ActionTargetType e() {
        return this.a.actionCost;
    }


    public CustomActionBase B() {
        CustomActionBase b2 = this.h.a();
        if (b2 != null) {
            return b2;
        }
        return this.a.actionSound;
    }

    @Override
    public int getResourceCost() {
        return this.B().a();
    }

    @Override
    public UnitTypeHandle i() {
        if (this.groupName == null) {
            return null;
        }
        return this.groupName.c();
    }

    @Override
    public UnitTypeHandle y() {
        if (this.a.J != null) {
            return this.a.J.c();
        }
        return null;
    }

    @Override
    public UnitTypeHandle E() {
        if (this.a.I != null) {
            return this.a.I.c();
        }
        return null;
    }

    @Override
    public boolean A() {
        return true;
    }


    public boolean g() {
        return this.a.J != null;
    }


    public ActionCategory f() {  // 02b a/g.java L327: t f() (t=ActionCategory)
        return this.a.aG;
    }


    public boolean m(UnitInstance am2) {
        return this.a.G.read((CustomUnitType) am2);
    }


    public boolean n(UnitInstance am2) {  // 02b a/g.java L335: n(am) ai_isHighPriority (b 闂佹彃绉撮ˇ鏌ュ绩閻熺増鍊?
        if (this.a.F == null) {
            return false;
        }
        if (!(am2 instanceof CustomUnitType)) {
            GlobalState.b("ai_isHighPriority non customUnit:" + am2.r().i());
            return false;
        }
        return this.a.F.read((CustomUnitType) am2);
    }


    @Override
    public boolean H() {
        return this.a.K;
    }

    @Override
    public boolean I() {
        return this.a.L;
    }

    @Override
    public com.corrodinggames.rts.gameFramework.rendering.Texture j() {
        return this.a.ay;
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture h(UnitInstance am2) {
        if (this.a.aB != null && am2 instanceof CustomUnitType && !this.a.aB.read((CustomUnitType) am2)) {  // 02b L363: an.a(aB,y) 绠€鍖?read
            return null;
        }
        return this.a.az;
    }

    @Override
    public int J() {
        return this.a.aA;
    }

    @Override
    public UnitInstance i(UnitInstance am2) {
        if (this.a.aC != null) {
            UnitInstance am3 = this.a.aC.getUnitOrSharedUnit(am2);
            return am3;
        }
        return null;
    }

    @Override
    public boolean s(UnitInstance am2) {
        return this.a.aD;
    }

    @Override
    public boolean t(UnitInstance am2) {
        return this.a.aE;
    }


    public boolean reset(UnitInstance am2) {
        if (this.a.aF != null) {
            return this.a.aF.read((CustomUnitType) am2);  // 02b L388: an.a(aF,y)
        }
        return false;
    }

    @Override
    public boolean Q() {
        return this.a.actionBindings;
    }


    public void reset(UnitType y2) {
        if (this.a.ae != null) {
            // 02b a/g.java L397: an.b(var1, ae) — LagHiding 缓存调整 (02b f/an 未映射到 03, 空实现)
        }
    }
}

