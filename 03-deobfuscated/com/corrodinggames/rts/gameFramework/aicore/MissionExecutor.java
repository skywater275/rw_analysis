/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.aicore;

import android.graphics.PointF;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.TagFilter;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.Command;
import com.corrodinggames.rts.gameFramework.ui.MinimapMode;
import com.corrodinggames.rts.gameFramework.ui.Notification;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.game.map.MapException;
import com.corrodinggames.rts.gameFramework.aicore.AITask;
import com.corrodinggames.rts.gameFramework.aicore.AIWaveSystem;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public class MissionExecutor {
    public static void a(AIWaveSystem f2, AITask a2) throws MapException {
        Object object;
        Object object2;
        Object object32;
        boolean bl;
        Object object42;
        Object object5;
        String string;
        GlobalState l2 = GlobalState.B();
        boolean bl2 = false;
        if (!a2.isCompleted) {
            bl2 = true;
        }
        f2.reset(a2);
        a2.isRepeating = true;
        a2.isCompleted = true;
        a2.priority = l2.by;
        boolean bl3 = false;
        if (a2.A != null) {
            string = a2.A.getLocalizedText();
            object5 = l2.bS.h.a(null, string);
            if (object5 != null) {
                int n2;
                String string2 = "globalMessage_delayPerChar";
                object42 = a2.b(string2);
                if (object42 != null) {
                    if (((String)object42).equals("slow")) {
                        ((Notification)object5).e = 18;
                    } else {
                        n2 = a2.b(string2, -1);
                        if (n2 != -1) {
                            ((Notification)object5).e = n2;
                        }
                    }
                }
                if ((n2 = a2.c("globalMessage_textColor", -1)) != -1) {
                    ((Notification)object5).f = n2;
                }
            }
            bl3 = true;
        }
        if ((string = a2.b("debugMessage")) != null) {
            a2.h("Debug: " + string);
            if (l2.bv && l2.bl) {
                object5 = "Debug: " + string;
                NetEngine.registerRelayServer((String)null, (String)object5);
            }
            bl3 = true;
        }
        if (bl = a2.a("showOnMap", false)) {
            l2.bW.a(a2.b(), a2.c(), MinimapMode.d);
            bl3 = true;
        }
        if (a2.requiredUnits.a > 0) {
            for (Object object42_76 : a2.requiredUnits) {
                if (!((TaskCondition)object42_76).c(a2)) continue;
                bl3 = true;
            }
        }
        if (a2.triggerEvent == MissionEvent.a) {
            if (bl2) {
                a2.h("objective met");
            }
            bl3 = true;
        }
        if (a2.triggerEvent == MissionEvent.k) {
            bl3 = true;
        }
        if (a2.triggerEvent == MissionEvent.i) {
            bl3 = true;
        }
        if (a2.triggerEvent == MissionEvent.j) {
            bl3 = true;
        }
        if (a2.triggerEvent == MissionEvent.g) {
            bl3 = true;
        }
        if (a2.triggerEvent == MissionEvent.h) {
            bl3 = true;
            float f3 = a2.b();
            float f4 = a2.c();
            l2.b(f3, f4);
        }
        if (a2.triggerEvent == MissionEvent.e) {
            float f5 = a2.b();
            float f6 = a2.c();
            float f7 = 0.0f;
            float f8 = 0.0f;
            object32 = a2.a();
            object2 = null;
            boolean bl4 = false;
            object = null;
            boolean bl5 = false;
            if (object32 == null) {
                a2.g("No team set, cannot spawn");
            } else if (a2.taskScript != null) {
                a2.taskScript.a(f5, f6, f7, f8, (PlayerState) object32, bl4, (UnitInstance) object2, (CustomArrayList) object, bl5);
            } else {
                a2.g("No valid unit list to spawn");
            }
            bl3 = true;
        }
        if (a2.triggerEvent == MissionEvent.c) {
            Integer n3;
            PlayerState n4 = a2.a();
            if (n4 == null) {
                a2.g("Team not set for changeCredits");
                return;
            }
            object42 = a2.d("set");
            if (object42 != null) {
                n4.o = ((Integer)object42).intValue();
            }
            if ((n3 = a2.d("add")) != null) {
                n4.d(n3.intValue());
            }
            bl3 = true;
            return;
        }
        if (a2.triggerEvent == MissionEvent.d) {
            String string3;
            PlayerState n5 = a2.a();
            if (n5 == null) {
                a2.g("Team not set for event_teamTags");
                return;
            }
            object42 = a2.a("addTeamTags", (String)null);
            if (object42 != null) {
                UnitConfig h2 = TeamTag.deserializeTags((String)object42);
                n5.b(h2);
            }
            if ((string3 = a2.a("removeTeamTags", (String)null)) != null) {
                UnitConfig h3 = TeamTag.deserializeTags(string3);
                n5.c(h3);
            }
            bl3 = true;
            return;
        }
        if (a2.triggerEvent == MissionEvent.b) {
            String string4 = a2.b("target");
            if (string4 == null) {
                AIWaveSystem.i("Move trigger has no target id:" + a2.taskId);
                return;
            }
            object42 = f2.initDefaultWaveUnits(string4);
            if (object42 == null) {
                AIWaveSystem.i("Move trigger: Cannot find target for:" + a2.taskId + " target:" + string4);
                return;
            }
            PlayerState n6 = a2.a();
            if (n6 == null) {
                AIWaveSystem.i("Team not set map trigger:" + a2.taskId);
                return;
            }
            int n7 = 0;
            object32 = l2.cf.b(n6);
            for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) UnitInstance.bE) {
                if (am2.player != n6 || !(am2 instanceof UnitType) || !a2.a(am2) || !a2.b(am2)) continue;
                object = (UnitType)am2;
                ((Command) object32).a((UnitType)object);
                ++n7;
            }
            ((Command) object32).a(((PointF)object42).a, ((PointF)object42).b);
            if (bl2) {
                f2.geti("firstActivation: move at:" + l2.by + " for teamId:" + n6.k + " to targetId:" + string4 + " (#units:" + n7 + ")");
            }
            if (a2.b("unload") != null) {
                for (Object object32_189 : UnitInstance.bE) {
                    if (((UnitInstance) object32_189).player != n6 || !(object32_189 instanceof UnitType) || !a2.a((UnitInstance) object32_189) || !a2.b((UnitInstance) object32_189) || !((UnitInstance) object32_189).canFireAtAirTargets()) continue;
                    object2 = (UnitType)object32_189;
                    Command e2 = l2.cf.b(n6);
                    e2.e = true;
                    e2.a((UnitType)object2);
                    object = ((UnitInstance) object2).applyDamage();
                    e2.a((ActionId) object);
                }
            }
            bl3 = true;
            return;
        }
        if (a2.triggerEvent == MissionEvent.f) {
            CustomArrayList m2 = new CustomArrayList();
            for (UnitInstance am3 : (java.util.Collection<UnitInstance>) (java.util.Collection) UnitInstance.bE) {
                if (!(am3 instanceof UnitType) || !a2.a(am3) || !a2.b(am3)) continue;
                m2.add(am3);
            }
            if (m2.size() > 0) {
                for (Object object212 : m2) {
                    UnitInstance am4 = (UnitInstance)object212;
                    am4.canBuild();
                    if (!(am4 instanceof UnitType) || !am4.isFactoryBuilding()) continue;
                    l2.bU.a((UnitType)am4);
                }
            }
            bl3 = true;
        }
        if (!bl3) {
            a2.h("Trigger activated with no effect");
        }
    }
}
