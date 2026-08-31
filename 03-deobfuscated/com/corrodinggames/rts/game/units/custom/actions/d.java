/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.actions;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.game.units.custom.actions.base.ActionCost;
import com.corrodinggames.rts.game.units.custom.actions.base.ActionSequence;
import com.corrodinggames.rts.game.units.custom.actions.base.ActionAnimation;
import com.corrodinggames.rts.game.units.custom.ModifierApplier;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.custom.actions.e;
import com.corrodinggames.rts.game.units.custom.aj;
import com.corrodinggames.rts.game.units.custom.at;
import com.corrodinggames.rts.game.units.custom.animation.AnimationVisibilityCurve;
import com.corrodinggames.rts.game.units.custom.LocalizedString;
import com.corrodinggames.rts.game.units.custom.bh;
import com.corrodinggames.rts.game.units.custom.bl;
import com.corrodinggames.rts.game.units.custom.ModUnitLoader;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$UnitReferenceOrUnitType;
import com.corrodinggames.rts.game.units.custom.AnimationReference;
import com.corrodinggames.rts.game.units.custom.UnitActionDef;
import com.corrodinggames.rts.game.units.custom.ActionBinding;
import com.corrodinggames.rts.game.units.custom.CustomVisuals;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public class d {
    public int a;
    public String actionName;
    public String actionDescription;
    public aj actionIcon;
    public UnitReference$UnitReferenceOrUnitType actionCategory;
    public UnitReference$UnitReferenceOrUnitType actionPriority;
    public UnitReference$UnitReferenceOrUnitType actionCooldown;
    public LocalizedString requiresTarget;
    public aj targetType;
    public com.corrodinggames.rts.game.units.actions.ActionTargetType actionCost = com.corrodinggames.rts.game.units.actions.ActionTargetType.c;
    public String actionRange;
    public ActionBinding costResourceType;
    public at[] actionScript;
    public boolean actionFlags;
    public boolean actionBindings;
    public float targetUnitType = -999.0f;
    public CustomActionBase actionSound;
    public CustomActionBase actionAnimation;
    public UnitConfig requiredUpgrade;  // 02b a/d.s:h
    public LogicBoolean actionHotkey;
    public LogicBoolean minSelectedUnits;
    public LogicBoolean maxSelectedUnits;
    public boolean actionButtonPos;
    public boolean showInMenu;
    public boolean showInPanel;
    public LogicBoolean isEnabledByDefault;
    public aj A;
    public LogicBoolean B;
    public aj C;
    public LogicBoolean D;
    public aj E;
    public LogicBoolean F;
    public LogicBoolean G;
    public ActionBinding H;
    public ActionBinding I;
    public ActionBinding J;
    public boolean K = false;
    public boolean L = false;
    public boolean M = true;
    public boolean N = true;
    public boolean O = false;
    public boolean P = false;
    public boolean Q;
    public at[] R;
    public float S = 0.01f;
    public boolean T = false;
    public boolean U = false;
    public AnimationReference V;
    public Float W;
    public boolean X;
    public boolean Y;
    public boolean Z;
    public ModUnitLoader aa;
    public UnitActionDef ab;
    public com.corrodinggames.rts.gameFramework.utility.CustomArrayList ac = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();  // 02b a/d.ac:m
    public Float ad;
    public CustomActionBase ae;
    public CustomActionBase af;
    public Integer ag;
    public PointF ah;
    public LogicBoolean ai;
    public bh aj;
    public int ak;
    public MovementTypeEnum al;
    public AnimationVisibilityCurve am;
    public LogicBoolean an;
    public LogicBoolean ao;
    public UnitActionDef ap;
    public UnitActionDef aq;
    public LogicBoolean ar;
    public CustomVisuals as;
    public CustomVisuals at;
    public bl au;
    public bl av;
    public bl aw;
    public bl ax;
    public com.corrodinggames.rts.gameFramework.rendering.Texture ay;
    public com.corrodinggames.rts.gameFramework.rendering.Texture az;
    public int aA;
    public LogicBoolean aB;
    public UnitReference$UnitReferenceOrUnitType aC;
    public boolean aD;
    public boolean aE;
    public LogicBoolean aF;
    public com.corrodinggames.rts.game.units.actions.ActionCategory aG = com.corrodinggames.rts.game.units.actions.ActionCategory.d;
    public LogicBoolean aH;
    public boolean aI;
    public int aJ;
    public boolean aK;
    public String aL;
    public ActionRegistry aM = ActionRegistry.a;
    public com.corrodinggames.rts.game.units.custom.actions.e aN = com.corrodinggames.rts.game.units.custom.actions.e.a;  // 02b a/d.aN:a.e (AutoFireMode)

    public String getString() {
        if (this.actionIcon != null) {
            return this.actionIcon.b();
        }
        return this.actionDescription;
    }
}
