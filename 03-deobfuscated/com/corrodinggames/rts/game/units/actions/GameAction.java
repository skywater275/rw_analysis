/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.actions;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.UnitActionBase;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.actions.RallyPointAction;
import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.ActionTargetType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.actions.AutoFireMode;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.KeyBinding;
import com.corrodinggames.rts.gameFramework.ui.ThemeColors;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.ArrayList;

public abstract class GameAction
implements Comparable {
    public float g = -999.0f;
    // v19.112 补全 (javap 铁证: a.s r_() = h.b()==null ? null : h.b())
    public com.corrodinggames.rts.game.units.custom.resources.CustomActionBase r_() {
        return this.h.isAffordable() == null ? null : this.h.isAffordable();  // 02b s.r_() L144-146: h.b() = UnitActionBase.isAffordable()
    }
    public UnitActionBase h = com.corrodinggames.rts.game.units.actions.UnitActionBase.a;
    public static final ActionId i = ActionId.a;
    private ActionId a;
    private CustomActionBase b;

    public float m_() {
        if (this instanceof RallyPointAction) {
            return -100.0f;
        }
        if (this.g != -999.0f) {
            return this.g;
        }
        UnitTypeHandle as2 = this.i();
        if (as2 != null && this.g()) {
            return as2.g();
        }
        return 1.0f;
    }

    public int getDescription(GameAction s2) {
        if (s2 == null) {
            return 0;
        }
        float f = this.m_() - s2.m_();
        if (f < 0.0f) {
            return -1;
        }
        if (f > 0.0f) {
            return 1;
        }
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        GameAction s2 = (GameAction) object;
        return this.a.equals(s2.a);
    }

    public static final boolean getLabel(ActionId c2) {
        return c2 == null || c2 == i;
    }

    public static final boolean b(ActionId c2) {  // 02b s.java b(a.c): null 或 i 检查
        return c2 == null || c2 == i;
    }

    public static final boolean c(ActionId c2) {  // 02b s.java c(a.c) = !b(a.c)
        return !b(c2);
    }

    public static final boolean getResourceCost(ActionId c2) {
        return !getLabel(c2);
    }

    public static boolean getDescription(GameAction s2, GameAction s3) {
        return s2 == s3;
    }

    public final boolean getDisplayString(ActionId c2) {
        return this.a == c2;
    }

    public GameAction(int n2) {
        this.getDescription(String.valueOf(n2));
    }

    public GameAction(String string) {
        this.getDescription(string);
    }

    public GameAction(ActionId c2) {
        this.e(c2);
    }

    public final void getDescription(String string) {
        this.a = ActionId.a(string);
    }

    public final void e(ActionId c2) {
        this.a = c2;
    }

    public final ActionId N() {
        return this.a;
    }

    public ActionId z() {
        return this.N();
    }

    public final String getActionIdString() {
        if (this.a == null) {
            return "<null index>";
        }
        return this.a.a();
    }

    public abstract String getLabel();

    public abstract String getDescription();

    public final String O() {  // 02b a/s L104-106: 索引描述
        return this.a == null ? "<null index>" : this.a.a();
    }


    public UnitConfig P() {  // 02b s.java L112: public h P() — h=UnitConfig 铁证 (02b j.java L4172: g.a(TeamTag,h) = TeamTag.deserializeTags(TeamTag,UnitConfig))
        return null;
    }

    public String getDisplayString(UnitInstance am2) {
        return this.getLabel();
    }

    public String e(UnitInstance am2) {
        return this.getDescription();
    }

    public abstract int getResourceCost();

    public CustomActionBase B() {
        CustomActionBase b2 = this.h.isAffordable();
        if (b2 != null) {
            return b2;
        }
        int n2 = this.getResourceCost();
        if (n2 == 0) {
            return CustomActionBase.a;
        }
        if (this.b == null || this.b.a() != n2) {
            this.b = CustomActionBase.a(n2);
        }
        return this.b;
    }

    public CustomActionBase getSecondaryResourceComponent() {
        if (this.h.isVisible() != null) {
            return this.h.isVisible();
        }
        return null;
    }

    public abstract int getLabel(UnitInstance var1, boolean var2);

    public boolean n_() {
        return false;
    }

    public String j(UnitInstance am2) {
        return this.h.getDisabledReason(am2);
    }

    public void getDescription(UnitInstance am2, UnitInstance am3) {
        this.h.isAffordable(am2, am3);
    }

    public boolean getDisplayString(UnitInstance am2, boolean bl) {
        return true;
    }

    public boolean k(UnitInstance am2) {
        return false;
    }

    public boolean l(UnitInstance am2) {
        return false;
    }

    public boolean getDescription(UnitInstance am2, boolean bl) {
        if (this.g(am2)) {
            return false;
        }
        if (com.corrodinggames.rts.game.units.weapons.TimerComponent.a(am2, this.N()) > 0) {
            return false;
        }
        if (bl) {
            return this.B().c(am2, this.Q());
        }
        return this.B().b(am2);  // 02b s.java L179: B().b(var1)
    }

    public boolean canShowAction(UnitInstance am2) {
        return this.getLabel(am2);
    }

    public boolean n(UnitInstance am2) {  // 02b s.n(am): 默认 false
        return false;
    }

    public boolean m(UnitInstance am2) {  // 02b s.m(am): 默认 false
        return false;
    }

    public boolean r(UnitInstance am2) {
        return this.getLabel(am2);
    }

    public boolean g(UnitInstance am2) {
        return this.h.isVisible(am2);
    }

    public boolean u(UnitInstance am2) {
        return this.h.isAffordable(am2);
    }

    public boolean getLabel(UnitInstance am2) {
        return this.h.isAffordable(am2, false);
    }

    public boolean a(UnitInstance am2, PlayerState n2) {
        return false;
    }

    public boolean u() {
        return false;
    }

    public boolean h() {
        return false;
    }

    public boolean C() {
        return false;
    }

    public boolean D() {
        return true;
    }

    public boolean A() {
        return false;
    }

    public abstract UnitTypeHandle i();

    public abstract boolean g();

    public abstract ActionTargetType e();

    public abstract ActionCategory f();

    public UnitTypeHandle y() {
        return null;
    }

    public UnitTypeHandle E() {
        return null;
    }

    public boolean F() {
        return false;
    }

    public int t() {
        return 1;
    }

    public boolean checkUnitTypeFilterFor(UnitInstance am2) {
        return false;
    }

    public com.corrodinggames.rts.game.units.custom.actions.AutoFireMode v(UnitInstance am2) {  // 02b s.java L254-256: v(am) → custom.a.e (AutoFireMode 为幻觉返回类型)
        return null;
    }

    public String getDisplayString() {
        String string = null;
        GlobalState l2 = GlobalState.B();
        int n2 = 0;
        UnitInstance[] amArray = l2.bS.bZ.a();
        int n3 = l2.bS.bZ.size();
        for (int i2 = 0; i2 < n3; ++i2) {
            int n4;
            UnitInstance am2 = amArray[i2];
            if (!(am2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType) am2;
            if (string == null) {
                string = this.getDisplayString(y2);
            }
            if ((n4 = this.getLabel(y2, true)) == -1 || n4 == 0) continue;
            n2 += n4;
        }
        if (string == null) {
            string = this.getLabel();
        }
        if (n2 != -1 && n2 != 0) {
            string = string + " (" + n2 + ")";
        }
        return string;
    }

    public boolean h_() {
        return true;
    }

    public String getLabelForUnitTarget(UnitInstance am2) {
        return this.getDisplayString(am2);
    }

    public void getDescription(UnitInstance am2, ThemeColors ae2, Paint paint, Paint paint2) {
        CustomActionBase b2;
        int n2;
        Object object;
        Paint paint3 = ae2.g;
        if (paint != null) {
            ae2.a(paint);
        }
        if (this.h_() && (object = this.getLabelForUnitTarget(am2)) != null && !((String)object).equals("")) {
            ae2.b((String)object);
        }
        if (paint != null) {
            ae2.a(paint3);
        }
        object = this.f();
        CustomActionBase b3 = this.B();
        if (!b3.c() && object != ActionCategory.i) {
            boolean bl = true;
            ae2.b(" (");
            UnitInstance am3 = null;
            n2 = 0;
            if (paint2 != null) {
                am3 = am2;
                n2 = paint2.e();
            }
            b3.a(ae2, false, true, 5, bl, am3, n2);
            ae2.b(")");
        }
        if ((b2 = this.getSecondaryResourceComponent()) != null && !b2.c() && object != ActionCategory.i) {
            boolean bl = true;
            ae2.b(" (");
            n2 = 0;
            b2.a(ae2, false, true, 5, bl, null, n2);
            ae2.b(")");
        }
    }

    public void getDescription(UnitInstance am2, ThemeColors ae2) {
        String string;
        String string2 = com.corrodinggames.rts.gameFramework.ui.ActionPanel.a(this, false);
        if (string2 != null && !"".equals(string2)) {
            string2 = string2.trim();
            ae2.b("\n" + string2);
        }
        if ((string = this.e(am2)) != null && !"".equals(string)) {
            string = string.trim();
            ae2.b("\n" + string);
        }
    }

    public boolean getResourceCost(UnitInstance am2, boolean bl) {
        return false;
    }

    public boolean c(UnitInstance am2, boolean bl) {
        return false;
    }

    public Texture j() {
        if (this.f() == ActionCategory.c) {
            return GlobalState.B().bS.bk;
        }
        return null;
    }

    public Texture h(UnitInstance am2) {
        return null;
    }

    public int J() {
        return Color.a(100, 255, 255, 255);
    }

    public Rect v() {
        return null;
    }

    public UnitInstance i(UnitInstance am2) {
        return null;
    }

    public boolean s(UnitInstance am2) {
        return true;
    }

    public boolean t(UnitInstance am2) {
        return true;
    }

    public boolean getDescription(UnitInstance am2) {
        return this.h.isBlocked(am2);
    }

    public boolean b(UnitInstance am2) {  // 02b a/s.b(am): h.a(am,false)
        return this.h.a(am2, false);
    }

    public boolean s() {
        return false;
    }

    public boolean o(UnitInstance am2) {
        return true;
    }

    public boolean G() {
        return false;
    }

    public void getResourceCost(UnitInstance am2) {
    }

    public void c(UnitInstance am2) {
    }

    public void f(UnitInstance am2) {
    }

    public float l() {
        return 1.0f;
    }

    public int m() {
        return -1;
    }

    public boolean H() {
        return false;
    }

    public boolean I() {
        return false;
    }

    public boolean x() {
        return false;
    }

    public float p(UnitInstance am2) {
        return -1.0f;
    }

    public ArrayList getQueuedActions(UnitInstance am2) {
        return null;
    }

    public KeyBinding M() {
        return null;
    }

    public boolean isAlwaysVisible() {
        return false;
    }

    public boolean Q() {
        return false;
    }

    public boolean o_() {
        return false;
    }

    public void a(UnitType y2) {
    }

    public boolean getDescription(float f2, float f3) {
        return false;
    }

    public boolean p() {
        return false;
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.getDescription((GameAction) object);
    }

    public void a(UnitInstance am2, UnitInstance am3) {  // 02b a.s.a(am,am)
        this.h.a(am2, am3);
    }

    public boolean a(UnitInstance am2, boolean bl) {  // 02b a.s.a(am,boolean) (简化)
        return false;
    }


   // 02b units.a.s.d(c) 字节码: getfield a + if_acmpne (MapPingAction.a(ActionId) 注册表匹配使用)
   public final boolean d(ActionId var1) {
      return this.a == var1;
   }

    public String b() {  // 02b a/s.java L108 抽象: 动作名 (03 默认空实现, 子类 override)
        return "";
    }

}
