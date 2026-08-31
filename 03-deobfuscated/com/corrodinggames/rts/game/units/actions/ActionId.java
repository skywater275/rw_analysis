/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.actions;

import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import java.util.HashMap;

public class ActionId {
    private static final HashMap c = new HashMap();
    public static final ActionId a = ActionId.a("-1");
    String b;

    public static ActionId a(String string) {
        ActionId c2 = (ActionId)c.get(string);
        if (c2 != null) {
            return c2;
        }
        ActionId c3 = new ActionId(string);
        c.put(string, c3);
        return c3;
    }

    public String a() {
        return this.b;
    }

    private ActionId(String string) {
        this.b = string;
    }

    public static void a(OutputNetStream as2, ActionId c2) {
        String string = null;
        if (c2 != null) {
            string = c2.b;
        }
        as2.b(string);
    }

    public static ActionId a(InputNetStream k2) {
        String string = k2.j();
        if (string != null) {
            return ActionId.a(string);
        }
        return null;
    }

    public boolean equals(Object object) {
        return this == object;
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public String toString() {
        return "ActionId(" + this.b + ")";
    }

    public final boolean a(ActionId c2) {
        return this == c2;
    }
}
