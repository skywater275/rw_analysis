/*
 * v19.115i 补全: 02b units.a.j.java 直译 (静态注册表 b + PingType 构造 + 静态 a(ActionId) 工厂)
 * 02b j extends s(GameAction); 03 继承链 AbstractCutsceneAction(String) 兼容 (super("c_6_"+name))
 * 02b a.k=PingType (03 units/actions/PingType.java, 枚举 a-k)
 */
package com.corrodinggames.rts.game.units.actions;

import android.graphics.Rect;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.actions.AbstractCutsceneAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.KeyBinding;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.ArrayList;
import java.util.Iterator;

public class MapPingAction
extends AbstractCutsceneAction {
    public PingType a;   // 02b j.k a 字段
    static ArrayList b = new ArrayList();   // 02b j.b: 静态注册表
    static Rect c;   // 02b j.c

    public MapPingAction() {
        this(PingType.a);
    }

    public MapPingAction(PingType var1) {
        super("c_6_" + var1.name());
        this.a = var1;
    }

    // 02b j.a(c) L75-88: 按 ActionId 查注册表
    public static MapPingAction a(ActionId var0) {
        Iterator var1 = b.iterator();
        GameAction var2;
        do {
            if (!var1.hasNext()) {
                return null;
            }
            var2 = (GameAction)var1.next();
        } while (!var2.d(var0));
        return (MapPingAction)var2;
    }

    @Override
    public String getLabel() {   // 03 GameAction 抽象 (02b s.b() 语义: 动作名)
        return "Map Ping";
    }

    @Override
    public String getDescription() {   // 03 GameAction 抽象 (02b s.a() 语义: 动作描述)
        return "Send a map ping to your allies";
    }

    @Override
    public int getResourceCost() {   // 03 GameAction 抽象 (02b s.c() 语义: 资源消耗)
        return 0;
    }

    @Override
    public int getLabel(UnitInstance var1, boolean var2) {   // 03 GameAction 抽象 (02b j.b(am,boolean): -1)
        return -1;
    }

    public String b() {
        return "Map Ping";
    }

    public String a() {
        return "Send a map ping to your allies";
    }

    @Override
    public boolean getResourceCost(UnitInstance am2, boolean bl) {
        GlobalState l2 = GlobalState.B();
        l2.bS.setPingAction();
        return true;
    }

    @Override
    public KeyBinding M() {
        GlobalState l2 = GlobalState.B();
        return l2.bT.pingMap;  // 02b ac.java L39: ad v = b("Ping Map") = 03 pingMap
    }

    static {
        PingType[] var0 = PingType.values();
        for (PingType var3 : var0) {
            b.add(new MapPingAction(var3));
        }
        c = new Rect();
    }
}
