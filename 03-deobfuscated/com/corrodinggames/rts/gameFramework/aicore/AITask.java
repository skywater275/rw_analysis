/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.aicore;
import android.graphics.Color;
import android.graphics.Paint;
import com.corrodinggames.rts.game.map.MapException;
import com.corrodinggames.rts.game.map.MapSpawn;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitShield;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.LocalizedString;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.aicore.AITaskQueue;
import com.corrodinggames.rts.gameFramework.aicore.MissionEvent;
import com.corrodinggames.rts.gameFramework.aicore.TaskCondition;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public class AITask {
    public String taskId;
    public String taskName;
    public String taskDescription;
    public AITaskQueue startPosition = new AITaskQueue();
    public AITaskQueue targetPosition = new AITaskQueue();
    public CustomArrayList<TaskCondition> requiredUnits = new CustomArrayList<TaskCondition>();
    public MissionEvent triggerEvent;
    public boolean isActive;
    public boolean isRepeating;
    public boolean isCompleted;
    public int priority;
    public int maxAttempts;
    public boolean hasStarted;
    public int startDelay = -1;
    public int timeoutSeconds = Integer.MAX_VALUE;
    public int currentAttempt;
    public int successEventId = -1;
    public int failureEventId = -1;
    public int completionEventId = -1;
    public MapSpawn mapSpawnRef;
    public boolean useMapSpawn = false;
    public bp taskScript;
    public float progressMin;
    public float progressMax;
    public PlayerState playerRef;
    public LocalizedString displayMessage;
    public LocalizedString A;
    public Paint B;
    public boolean C;

    public void a(TaskCondition a2) {
        this.requiredUnits.add(a2);
    }

    public void a(String string) {
        this.mapSpawnRef.b(string);
    }

    public String b(String string) {
        return this.mapSpawnRef.b(string);
    }

    public String a(String string, String string2) {
        return this.mapSpawnRef.a(string, string2);
    }

    public boolean c(String string) {
        return this.mapSpawnRef.b(string) != null;
    }

    public int a(String string, int n2) throws MapException {
        String string2 = this.a(string, (String)null);
        if (string2 == null) {
            return n2;
        }
        try {
            return Integer.parseInt(string2);
        }
        catch (NumberFormatException numberFormatException) {
            throw this.f(string + ": Unexpected integer value:'" + string2 + "'");
        }
    }

    public int b(String string, int n2) throws MapException {
        double d;
        String string2;
        String string3 = string2 = this.b(string);
        if (string2 == null) {
            return n2;
        }
        double d2 = 1.0;
        if (string2.endsWith("ms")) {
            string2 = string2.substring(0, string2.length() - 2);
            d2 = 1.0;
        } else if (string2.endsWith("s")) {
            string2 = string2.substring(0, string2.length() - 1);
            d2 = 1000.0;
        } else {
            d2 = 1.0;
        }
        try {
            d = Double.parseDouble(string2);
        }
        catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
            throw this.f(string + ": Unexpected time:'" + string3 + "'");
        }
        return (int)(d * d2);
    }

    public float a(String string, float f2) throws MapException {
        String string2 = this.a(string, (String)null);
        if (string2 == null) {
            return f2;
        }
        try {
            return Float.parseFloat(string2);
        }
        catch (NumberFormatException numberFormatException) {
            throw this.f(string + ": Unexpected float value:'" + string2 + "'");
        }
    }

    public Integer d(String string) throws MapException {
        String string2 = this.a(string, (String)null);
        if (string2 == null) {
            return null;
        }
        try {
            return Integer.parseInt(string2);
        }
        catch (NumberFormatException numberFormatException) {
            throw this.f(string + ": Unexpected integer value:'" + string2 + "'");
        }
    }

    public Boolean e(String string) throws MapException {
        String string2 = this.a(string, (String)null);
        if (string2 == null) {
            return null;
        }
        if (string2.equalsIgnoreCase("true")) {
            return true;
        }
        if (string2.equalsIgnoreCase("false")) {
            return false;
        }
        throw this.f(string + ": Unexpected boolean value:'" + string2 + "'");
    }

    public boolean a(String string, String string2, boolean bl) throws MapException {
        Boolean bl2 = this.e(string);
        if (bl2 != null) {
            return bl2;
        }
        bl2 = this.e(string2);
        if (bl2 != null) {
            return bl2;
        }
        return bl;
    }

    public boolean a(String string, boolean bl) throws MapException {
        String string2 = this.a(string, (String)null);
        if (string2 == null) {
            return bl;
        }
        if (string2.equalsIgnoreCase("true")) {
            return true;
        }
        if (string2.equalsIgnoreCase("false")) {
            return false;
        }
        throw this.f(string + ": Unexpected boolean value:'" + string2 + "'");
    }

    public int c(String string, int n2) throws MapException {
        String string2 = this.b(string);
        if (string2 == null) {
            return n2;
        }
        if (string2.equals("")) {
            throw this.f(string + ": Unknown color:" + string2);
        }
        try {
            return Color.a(string2);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw this.f(string + ": Unknown color:" + string2);
        }
    }

    public LocalizedString a(String string, LocalizedString bb2) {
        return this.mapSpawnRef.a(string, bb2);
    }

    public boolean a(UnitInstance am2) {
        return this.mapSpawnRef.a(am2);
    }

    public MapException f(String string) {
        return this.a(string, (Exception)null);
    }

    public MapException a(String string, Exception exception) {
        string = "MapTrigger-Error (" + this.taskId + " id:" + this.taskName + "): " + string;
        NetEngine.registerRelayServer(string, false);
        if (exception == null) {
            return new MapException(string);
        }
        return new MapException(string, exception);
    }

    public void g(String string) {
        NetEngine.registerRelayServer("MapTrigger-Error (" + this.taskId + " id:" + this.taskName + " type:" + (Object)((Object)this.triggerEvent) + "): " + string, false);
    }

    public void h(String string) {
        com.corrodinggames.rts.gameFramework.GlobalState.e("MapTrigger-Debug (" + this.taskName + " type:" + (Object)((Object)this.triggerEvent) + "): " + string);
    }

    public PlayerState a() {
        return this.playerRef;
    }

    public int b() {
        return (int)this.mapSpawnRef.boundsRect.d();
    }

    public int c() {
        return (int)this.mapSpawnRef.boundsRect.e();
    }

    public boolean b(UnitInstance am2) {
        UnitShield ak2;
        PlayerState n2 = this.a();
        if (n2 != null && am2.player != n2) {
            return false;
        }
        boolean bl = this.c("onlyIfEmpty");
        return !bl || !am2.canFireAtAirTargets() || !(am2 instanceof UnitShield) || (ak2 = (UnitShield) ((Object)am2)).bB() <= 0;
    }

    public boolean d() {
        boolean bl;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        int n2 = l2.by;
        boolean bl2 = true;
        boolean bl3 = false;
        if (!this.hasStarted && this.failureEventId != -1) {
            if (this.failureEventId <= n2) {
                bl3 = true;
                this.hasStarted = true;
            } else {
                bl2 = false;
            }
        }
        if (this.startPosition.a()) {
            if (this.startPosition.b()) {
                bl3 = true;
            } else {
                bl2 = false;
            }
        }
        if (this.requiredUnits.a > 0) {
            for (TaskCondition a2 : this.requiredUnits) {
                if (!a2.a(this)) continue;
                if (a2.b(this)) {
                    bl3 = true;
                    continue;
                }
                bl2 = false;
            }
        }
        if (this.isActive) {
            bl = bl3 && bl2;
        } else {
            bl = bl3;
            if (bl2) {
                bl = true;
            }
        }
        if (bl) {
            if (this.startDelay == -1) {
                this.startDelay = n2;
            }
            if (this.completionEventId <= 0) {
                return true;
            }
            return n2 >= this.startDelay + this.completionEventId;
        }
        this.startDelay = -1;
        return false;
    }
}
