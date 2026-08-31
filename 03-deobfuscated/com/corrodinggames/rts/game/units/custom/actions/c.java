/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.actions;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;

import com.corrodinggames.rts.game.units.actions.UnitActionBase;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.actions.d;
import com.corrodinggames.rts.game.units.custom.aj;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.EffectConfig;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.ui.UnitStateTracker;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class c
extends com.corrodinggames.rts.game.units.actions.UnitActionBase {  // 02b a/c extends units/a/a (v19.133f5 修正)
    public LogicBoolean activationCondition;
    public LogicBoolean targetCondition;
    public aj primaryEffect;
    public LogicBoolean effectCondition1;
    public aj secondaryEffect;
    public LogicBoolean effectCondition2;
    public aj tertiaryEffect;
    public boolean isRepeatable;
    public LogicBoolean completionCondition;
    public CustomActionBase successBinding;
    public CustomActionBase failureBinding;
    public CustomActionBase cancelBinding;

    public static com.corrodinggames.rts.game.units.actions.UnitActionBase a(EffectConfig p2) {  // 02b a/c L78: a(p) (v19.133f5 修正)
        boolean bl2 = false;
        if (p2.f != null && p2.f != LogicBoolean.falseBoolean) {
            bl2 = true;
        }
        if (!bl2) {
            return com.corrodinggames.rts.game.units.actions.UnitActionBase.a;
        }
        c c2 = new c();
        c2.activationCondition = p2.f;
        c2.primaryEffect = aj.getString(p2.g);
        return c2;
    }


    public boolean isAffordable(UnitInstance am2) {  // 02b a/c L94: a(am) (v19.133f5 修正)
        return this.isRepeatable;
    }


    public boolean isVisible(UnitInstance am2) {  // 02b a/c L98: b(am) (v19.133f5 修正)
        return this.getb(am2, -1);
    }

    public boolean getb(UnitInstance am2, int n2) {
        if (this.targetCondition != null && (n2 == -1 || n2 == 1)) {
            if (!(am2 instanceof UnitType)) {
                com.corrodinggames.rts.gameFramework.GlobalState.reportProblem("CustomActionConfig lockedInGame:" + am2.r().i() + " is not UnitActionBase OrderableUnit unit");
                return false;
            }
            if (this.targetCondition.read((UnitType)am2)) {
                return true;
            }
        }
        if (this.effectCondition1 != null && (n2 == -1 || n2 == 2)) {  // 02b e (v19.133f5 粘连修正)
            if (!(am2 instanceof UnitType)) {
                com.corrodinggames.rts.gameFramework.GlobalState.reportProblem("CustomActionConfig lockedInGame:" + am2.r().i() + " is not UnitActionBase OrderableUnit unit");
                return false;
            }
            if (this.effectCondition1.read((UnitType)am2)) {
                return true;
            }
        }
        if (this.effectCondition2 != null && (n2 == -1 || n2 == 3)) {  // 02b g (v19.133f5 粘连修正)
            if (!(am2 instanceof UnitType)) {
                com.corrodinggames.rts.gameFramework.GlobalState.reportProblem("CustomActionConfig lockedInGame:" + am2.r().i() + " is not UnitActionBase OrderableUnit unit");
                return false;
            }
            if (this.effectCondition2.read((UnitType)am2)) {
                return true;
            }
        }
        return false;
    }


    public String getDisabledReason(UnitInstance am2) {  // 02b a/c L139: c(am) (v19.133f5 修正)
        if (this.getb(am2, 1) && this.primaryEffect != null) {
            return this.primaryEffect.b(am2);
        }
        if (this.getb(am2, 2) && this.secondaryEffect != null) {
            return this.secondaryEffect.b(am2);
        }
        if (this.getb(am2, 3) && this.tertiaryEffect != null) {
            return this.tertiaryEffect.b(am2);
        }
        return null;
    }


    public boolean isAffordable(UnitInstance am2, boolean bl2) {  // 02b a/c L143: a(am,boolean) (v19.133f5 修正)
        if (this.activationCondition != null) {
            if (!(am2 instanceof UnitType)) {
                com.corrodinggames.rts.gameFramework.GlobalState.reportProblem("CustomActionConfig isAvailable:" + am2.r().i() + " is not UnitActionBase OrderableUnit unit");
                return true;
            }
            if (bl2) {
                return UnitStateTracker.a(this.activationCondition, (UnitType)am2);  // 02b f/an (v19.133f5 修正)
            }
            return this.activationCondition.read((UnitType)am2);
        }
        return true;
    }


    public boolean isBlocked(UnitInstance am2) {  // 02b a/c L156: d(am) (v19.133f5 修正)
        if (this.completionCondition != null) {
            if (!(am2 instanceof UnitType)) {
                com.corrodinggames.rts.gameFramework.GlobalState.reportProblem("CustomActionConfig isGuiBlinking:" + am2.r().i() + " is not UnitActionBase OrderableUnit unit");
                return true;
            }
            return this.completionCondition.read((UnitType)am2);
        }
        return false;
    }


    public void a(UnitInstance am2, UnitInstance am3) {  // 02b a/c L169: a(am,am) (v19.133f5 修正)
        if (this.failureBinding != null) {
            this.failureBinding.h(am2);
        }
    }


    public CustomActionBase a() {  // 02b a/c L176: a() (v19.133f5 修正)
        return this.successBinding;
    }


    public CustomActionBase b() {
        return this.cancelBinding;  // 02b m (v19.133f5 粘连修正)
    }
    public static com.corrodinggames.rts.game.units.actions.UnitActionBase a(com.corrodinggames.rts.game.units.custom.actions.d d2) {  // 02b a/c.java L28-75 直译
        boolean bl = false;
        if (d2.isEnabledByDefault != null && d2.isEnabledByDefault != LogicBoolean.falseBoolean) {
            bl = true;
        }
        if (d2.B != null && d2.B != LogicBoolean.falseBoolean) {
            bl = true;
        }
        if (d2.D != null && d2.D != LogicBoolean.falseBoolean) {
            bl = true;
        }
        if (d2.maxSelectedUnits != null && d2.maxSelectedUnits != LogicBoolean.trueBoolean) {
            bl = true;
        }
        if (d2.aF != null && d2.aF != LogicBoolean.falseBoolean) {
            bl = true;
        }
        if (d2.ae != null) {
            bl = true;
        }
        if (d2.actionSound != null) {
            bl = true;
        }
        if (!bl) {
            return com.corrodinggames.rts.game.units.actions.UnitActionBase.a;
        }
        c c2 = new c();
        c2.activationCondition = d2.isEnabledByDefault;
        c2.primaryEffect = d2.A;
        c2.effectCondition1 = d2.B;
        c2.secondaryEffect = d2.C;
        c2.effectCondition2 = d2.D;
        c2.tertiaryEffect = d2.E;
        c2.targetCondition = d2.maxSelectedUnits;
        c2.completionCondition = d2.aF;
        c2.failureBinding = d2.ae;
        c2.successBinding = d2.actionSound;
        c2.cancelBinding = d2.actionAnimation;
        c2.isRepeatable = d2.showInPanel;
        return c2;
    }

}
