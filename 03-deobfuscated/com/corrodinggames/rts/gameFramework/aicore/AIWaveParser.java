/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.aicore;
import com.corrodinggames.rts.R;
import android.graphics.PointF;
import com.corrodinggames.rts.game.map.MapException;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.aicore.AIWaveSystem;
import com.corrodinggames.rts.gameFramework.aicore.AISpawnList;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AIWaveParser {
    public ArrayList a = new ArrayList();
    public boolean isInfinite;
    public boolean isEnabled;
    public float waveInterval;
    public int currentWaveIndex;
    public String waveName;
    public boolean debugMode;
    public boolean isPaused;
    final /* synthetic */ AIWaveSystem f;

    public AIWaveParser(AIWaveSystem f2) {
        this.f = f2;
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean reset(String string) throws MapException {
        String[] stringArray;
        String string2;
        String string3;
        string = string.trim();
        GlobalState.e("Got:" + string);
        if (string.length() == 0) {
            return false;
        }
        GlobalState.e("..");
        String string4 = null;
        String string5 = null;
        String string6 = null;
        String string7 = null;
        if (string.startsWith("+")) {
            Pattern pattern = Pattern.compile("\\+([^ ]*)([^\\[-]*)(\\[(.*?)\\])? *(-.*)?");
            Matcher matcher = pattern.matcher(string);
            if (!matcher.matches()) throw new MapException("Unknown wave line in map: " + string);
            string4 = matcher.group(1);
            string5 = matcher.group(2);
            string6 = matcher.group(4);
            string7 = matcher.group(5);
            GlobalState.e("Got o:" + string6 + " d:" + string4 + " dn:" + string5 + " units:" + string7);
        } else {
            if (!string.startsWith("!")) throw new MapException("Unknown wave format: " + string);
            Pattern pattern = Pattern.compile("\\!(.*)");
            Matcher matcher = pattern.matcher(string);
            if (!matcher.matches()) throw new MapException("Unknown wave line in map: " + string);
            string6 = matcher.group(1);
        }
        if (string4 != null) {
            string4 = string4.trim();
            String[] stringArray2 = string4.split(":");
            String string8 = "0";
            String string9 = "0";
            if (stringArray2.length == 1) {
                String string10 = stringArray2[0];
            } else {
                if (stringArray2.length != 2) throw new MapException("Unknown time format in wave: " + string);
                string9 = stringArray2[0];
                String string11 = stringArray2[1];
            }
            try {
                String var7_20 = stringArray2[stringArray2.length - 1];
                this.waveInterval = Integer.parseInt((String)var7_20) + Integer.parseInt(string9) * 60;
            }
            catch (NumberFormatException numberFormatException) {
                throw new MapException("Failed to parse time on: " + string, numberFormatException);
            }
        }
        if (string5 != null) {
            this.waveName = string5 = string5.trim();
            this.isPaused = true;
        }
        if (string6 != null) {
            String[] stringArray3;
            String[] stringArray4 = stringArray3 = string6.split(",");
            int n2 = stringArray4.length;
            for (int j = 0; j < n2; ++j) {
                String string12 = stringArray4[j];
                String[] stringArray5 = string12.split(":");
                string3 = stringArray5[0].trim();
                string2 = null;
                if (stringArray5.length > 1) {
                    string2 = stringArray5[1].trim();
                }
                if ("lockSpawn".equalsIgnoreCase(string3)) {
                    this.isInfinite = true;
                    continue;
                }
                if ("unlockSpawn".equalsIgnoreCase(string3)) {
                    this.isEnabled = true;
                    continue;
                }
                if ("noTimer".equalsIgnoreCase(string3)) {
                    this.debugMode = true;
                    continue;
                }
                if ("paused".equalsIgnoreCase(string3) || "win".equalsIgnoreCase(string3) || "".equalsIgnoreCase(string3)) continue;
                throw new MapException("Unknown wave option '" + string3 + "' in: " + string);
            }
        }
        if (string7 == null) return true;
        if ((string7 = string7.trim()).startsWith("-")) {
            string7 = string7.substring(1);
        }
        for (String string12 : stringArray = string7.split(",")) {
            int n3;
            if (!(string12 = string12.trim()).contains(" ")) {
                throw new MapException("Unknown wave format '" + string12 + "' in: " + string);
            }
            int n4 = string12.indexOf(" ");
            string3 = string12.substring(0, n4).trim();
            string2 = string12.substring(n4 + 1).trim();
            try {
                n3 = Integer.parseInt(string3);
            }
            catch (NumberFormatException numberFormatException) {
                throw new MapException("Expected starting number in wave format '" + string12 + "' in: " + string);
            }
            UnitTypeHandle as2 = UnitRegistry.a(string2);
            if (as2 == null) {
                throw new MapException("Could not find unit '" + string2 + "' in: " + string);
            }
            AISpawnList i2 = new AISpawnList(this.f);
            i2.b(as2, n3);
            this.a.add(i2);
        }
        return true;
    }

    public void reset() {
        GlobalState.e("Activating wave");
        if (!this.f.R) {
            this.f.setupWaveOrigin();
        }
        PointF pointF = this.f.P;
        for (Object object154 : this.a) {
            AISpawnList i2 = (AISpawnList)object154;
            i2.a(pointF.a, pointF.b);
        }
        if (!this.f.Q) {
            this.f.setupWaveOrigin();
        }
        if (this.isInfinite) {
            this.f.Q = true;
        }
        if (this.isEnabled) {
            this.f.Q = false;
        }
    }
}
