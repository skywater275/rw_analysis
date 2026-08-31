/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.aicore;

import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.Typeface;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.map.MapException;
import com.corrodinggames.rts.game.map.MapSpawn;
import com.corrodinggames.rts.game.units.custom.LocalizedString;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.aicore.AITask;
import com.corrodinggames.rts.gameFramework.aicore.TeamTagDetect;
import com.corrodinggames.rts.gameFramework.aicore.UnitCountCondition;
import com.corrodinggames.rts.gameFramework.aicore.MissionEvent;
import com.corrodinggames.rts.gameFramework.aicore.AIWaveSystem;

public class MissionParser {
    public static AITask a(AIWaveSystem f2, MapSpawn a2) throws MapException {
        try {
            String string;
            MissionEvent e2;
            String string2;
            GlobalState l2 = GlobalState.B();
            String string3 = a2.unitTypeName;
            if (string3 == null) {
                string3 = "NULL";
            }
            if ((string2 = a2.b("id")) != null && !string2.equals("")) {
                string3 = string2;
            }
            string3 = string3.trim();
            String string4 = a2.teamName;
            if (string4 != null) {
                e2 = MissionEvent.a(string4);
                if (e2 == null) {
                    AIWaveSystem.c("Error: Unknown type:" + string4 + " found on " + string3);
                    return null;
                }
            } else {
                AIWaveSystem.c("Error: no type field set for: " + string3);
                return null;
            }
            AITask a3 = new AITask();
            a3.mapSpawnRef = a2;
            a3.triggerEvent = e2;
            a3.taskName = string3;
            int n2 = 0;
            for (AITask a4 : f2.pendingSpawnQueue) {
                if (!a4.taskName.equalsIgnoreCase(a3.taskName)) continue;
                ++n2;
            }
            a3.taskDescription = a3.taskName;
            if (n2 != 0) {
                a3.taskDescription = a3.taskDescription + "_" + n2;
            }
            a3.taskId = a2.unitTypeName;
            Integer n3 = a3.d("team");
            if (n3 != null) {
                a3.playerRef = PlayerState.u(n3);
                if (a3.playerRef == null) {
                    a3.g("Cannot find team:" + n3);
                    return null;
                }
            }
            a3.failureEventId = a3.b("delay", a3.failureEventId);
            a3.currentAttempt = a3.b("repeatDelay", a3.currentAttempt);
            a3.timeoutSeconds = a3.a("repeatCount", a3.timeoutSeconds);
            a3.successEventId = a3.b("resetActivationAfter", a3.successEventId);
            a3.startPosition.b = a3.isActive = a3.a("allToActivate", false);
            a3.completionEventId = a3.b("warmup", a3.completionEventId);
            a3.A = a3.a("globalMessage", (LocalizedString) null);
            a3.progressMin = a3.a("textOffsetX", 0.0f);
            a3.progressMax = a3.a("textOffsetY", 0.0f);
            if (a3.triggerEvent == MissionEvent.g || a3.triggerEvent == MissionEvent.a) {
                a3.displayMessage = a3.a("text", (LocalizedString) null);
            }
            if (a3.triggerEvent == MissionEvent.g) {
                f2.i = true;
                a3.B = new Paint();
                a3.B.a(true);
                a3.B.a(Paint$Align.b);
                a3.B.a(Typeface.a(Typeface.c, 1));
                int n4 = a3.c("textColor", -1);
                a3.B.b(n4);
                int n5 = a3.a("textSize", 20);
                l2.b(a3.B, (float)n5);
                if (a3.B.f() == 0) {
                    a3.g("Text has an alpha of 0");
                }
                if ((string = a3.b("style")) != null && !string.equals("")) {
                    if (string.equalsIgnoreCase("arrow")) {
                        a3.C = true;
                    } else {
                        a3.g("Unknown style: " + string);
                    }
                }
            }
            if (a3.triggerEvent == MissionEvent.e) {
                String string5 = a3.b("spawnUnits");
                String string6 = "<unitAdd>";
                string = "spawnUnits";
                try {
                    a3.taskScript = bp.a(string5, string6, string);
                }
                catch (bo bo2) {
                    AIWaveSystem.c(bo2.getMessage());
                    return null;
                }
                if (a3.a() == null) {
                    a3.g("No team set");
                }
            }
            if (a3.triggerEvent == MissionEvent.d) {
                a3.a("addTeamTags");
                a3.a("removeTeamTags");
            }
            if (a3.triggerEvent == MissionEvent.c) {
                a3.a("add");
                a3.a("set");
            }
            if (a3.triggerEvent == MissionEvent.i) {
                a3.a(UnitCountCondition.d(a3));
            }
            if (a3.triggerEvent == MissionEvent.j) {
                a3.a(TeamTagDetect.d(a3));
            }
            a3.a("comment");
            a3.a("team");
            a3.a("globalMessage");
            a3.a("globalMessage_delayPerChar");
            a3.a("globalMessage_textColor");
            a3.a("debugMessage");
            a3.a("showOnMap");
            a3.a("text");
            a3.a("target");
            a3.a("onlyIfEmpty");
            if (a3.triggerEvent == MissionEvent.b) {
                a3.a("unload");
            }
            if (a3.triggerEvent == MissionEvent.f) {
                a3.a("onlyIfEmpty");
            }
            return a3;
        }
        catch (RuntimeException runtimeException) {
            throw new MapException("Error while reading: " + a2.b(), runtimeException);
        }
    }
}
