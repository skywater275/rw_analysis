/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import a.a.h;
import com.corrodinggames.librocket.scripts.Debug$1;
import com.corrodinggames.librocket.scripts.Root;
import com.corrodinggames.librocket.scripts.ScriptContext;
import com.corrodinggames.rts.a.a;
import com.corrodinggames.rts.a.a.n;
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.al;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.l;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.e;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.ah;
import com.corrodinggames.rts.gameFramework.j.ai;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.k.d;
import com.corrodinggames.rts.gameFramework.k.m;
import com.corrodinggames.rts.gameFramework.k.p;
import com.corrodinggames.rts.gameFramework.w;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import sun.management.VMManagement;

public class Debug
extends ScriptContext {
    Root root;
    boolean allFeatures;
    ConcurrentLinkedQueue backgroundClientConnections;
    Thread backgroundConnectionThread;
    Runnable backgroundConnectionRunnable = new Debug$1(this);
    boolean forceNonThreaded = true;

    Debug(Root root) {
        this.root = root;
    }

    public int currentPid() {
        try {
            RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
            Field field = runtimeMXBean.getClass().getDeclaredField("jvm");
            field.setAccessible(true);
            VMManagement vMManagement = (VMManagement)field.get(runtimeMXBean);
            Method method = vMManagement.getClass().getDeclaredMethod("getProcessId", new Class[0]);
            method.setAccessible(true);
            int n = (Integer)method.invoke(vMManagement, new Object[0]);
            return n;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return -1;
        }
    }

    public void setLocalPlayerName(String string) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bX.a(string);
    }

    public void setDdosProtection(boolean bl) {
        com.corrodinggames.rts.gameFramework.j.ao.b = bl;
    }

    public void lookAt(float f2, float f3) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.b(f2, f3);
    }

    public void createManyUnits(String string, float f2, float f3, int n2, boolean bl, int n3) {
        int n4 = 0;
        int n5 = 0;
        for (int j = 0; j < n3; ++j) {
            if ((n4 += 9) > 400) {
                n4 = 0;
                n5 += 9;
            }
            this.createUnit(string, f2 + (float)n4, f3 + (float)n5, n2, j == 0 ? bl : false);
        }
    }

    public Long createUnit(String string, float f2, float f3, int n2, boolean bl) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        as as2 = ar.a(string);
        if (as2 == null) {
            this.root.logWarn("Could not find type:" + string);
            return null;
        }
        am am2 = as2.a();
        am2.eo = f2;
        am2.ep = f3;
        try {
            am2.Q(n2);
        }
        catch (com.corrodinggames.rts.game.b.f f4) {
            throw new RuntimeException(f4);
        }
        com.corrodinggames.rts.game.n.c(am2);
        am2.cK = true;
        if (bl) {
            l2.b(f2, f3);
        }
        return am2.eh;
    }

    public int getMaxCustomUnitTypeId() {
        return com.corrodinggames.rts.game.units.custom.l.d.size();
    }

    public Long createCustomUnitFromTypeId(int n2, float f2, float f3, int n3, boolean bl2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        com.corrodinggames.rts.game.units.custom.l l3 = (com.corrodinggames.rts.game.units.custom.l)com.corrodinggames.rts.game.units.custom.l.d.get(n2);
        am am2 = l3.a();
        am2.eo = f2;
        am2.ep = f3;
        try {
            am2.Q(n3);
        }
        catch (com.corrodinggames.rts.game.b.f f4) {
            throw new RuntimeException(f4);
        }
        com.corrodinggames.rts.game.n.c(am2);
        am2.cK = true;
        if (bl2) {
            l2.b(f2, f3);
        }
        return am2.eh;
    }

    public void enableFeatures(String string) {
        String string2 = f.e(string);
        if (string2.startsWith("221FC410BD29D786")) {
            this.allFeatures = true;
            a.d = true;
            return;
        }
        throw new RuntimeException("unknown");
    }

    public void selectNextUnit() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        am am2 = null;
        boolean bl2 = false;
        for (am am3 : am.bF()) {
            am am4;
            if (!(am3 instanceof am) || (am4 = am3) instanceof al || am4.t()) continue;
            if (am2 == null) {
                am2 = am4;
            }
            if (bl2) {
                am2 = am4;
                break;
            }
            bl2 = am4.cG;
        }
        l2.bS.y();
        if (am2 != null) {
            l2.bS.j(am2);
        }
    }

    public void removeAllUnits() {
        for (w w2 : w.dK()) {
            w2.a();
        }
    }

    public void killAllUnits() {
        for (am am2 : am.bF()) {
            if (!(am2 instanceof am)) continue;
            am am3 = am2;
            am3.cu = -1.0f;
        }
    }

    public boolean backgroundCurrentClientConnection() {
        if (!this.allFeatures) {
            return false;
        }
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (!l2.bX.B) {
            com.corrodinggames.rts.gameFramework.l.e("Not networked");
            return false;
        }
        if (l2.bX.C) {
            throw new RuntimeException("server=true");
        }
        if (this.backgroundConnectionThread == null) {
            this.backgroundConnectionThread = new Thread(this.backgroundConnectionRunnable);
            this.backgroundConnectionThread.start();
        }
        if (this.backgroundClientConnections == null) {
            this.backgroundClientConnections = new ConcurrentLinkedQueue();
        }
        for (com.corrodinggames.rts.gameFramework.j.c c2 : l2.bX.aM) {
            c2.t = true;
            this.backgroundClientConnections.add(c2);
            l2.bX.aM.remove(c2);
        }
        l2.bX.b("backgrounded");
        l2.bX.B = true;
        return true;
    }

    public boolean isTeamWipedOut(int n2) {
        com.corrodinggames.rts.game.n n3 = com.corrodinggames.rts.game.n.k(n2);
        if (n3 == null) {
            this.root.logWarn("Could not find team:" + n2);
            return true;
        }
        return n3.G;
    }

    public boolean isTeamDefeated(int n2) {
        com.corrodinggames.rts.game.n n3 = com.corrodinggames.rts.game.n.k(n2);
        if (n3 == null) {
            this.root.logWarn("Could not find team:" + n2);
            return true;
        }
        return n3.G;
    }

    public boolean isTeamInVictory(int n2) {
        com.corrodinggames.rts.game.n n3 = com.corrodinggames.rts.game.n.k(n2);
        if (n3 == null) {
            this.root.logWarn("Could not find team:" + n2);
            return false;
        }
        return n3.H;
    }

    public String getPlayerName(int n2) {
        com.corrodinggames.rts.game.n n3 = com.corrodinggames.rts.game.n.k(n2);
        if (n3 == null) {
            this.root.logWarn("Could not find team:" + n2);
            return null;
        }
        return n3.v;
    }

    public String getQueryStringOfPlayer(int n2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        com.corrodinggames.rts.game.n n3 = com.corrodinggames.rts.game.n.k(n2);
        if (n3 == null) {
            this.root.logWarn("Could not find team:" + n2);
            return null;
        }
        com.corrodinggames.rts.gameFramework.j.c c2 = l2.bX.c(n3);
        if (c2 == null) {
            this.root.logWarn("Found team but could not find connection for team:" + n2);
            return null;
        }
        return c2.o;
    }

    public boolean setTeamCredits(int n2, int n3) {
        com.corrodinggames.rts.game.n n4 = com.corrodinggames.rts.game.n.k(n2);
        if (n4 == null) {
            this.root.logWarn("Could not find team:" + n2);
            return false;
        }
        n4.o = n3;
        return true;
    }

    public boolean setTeamAllyGroup(int n2, int n3) {
        com.corrodinggames.rts.game.n n4 = com.corrodinggames.rts.game.n.k(n2);
        if (n4 == null) {
            this.root.logWarn("Could not find team:" + n2);
            return false;
        }
        n4.r = n3;
        return true;
    }

    public void giveUpgradeToAllUnits() {
        for (am am2 : am.bF()) {
            c c2;
            y y2;
            s s2;
            if (!(am2 instanceof y) || (s2 = (y2 = (y)am2).a(c2 = y2.cm())) == null) continue;
            y2.a(s2, false);
        }
    }

    public void giveAllActionsToAllUnits() {
        for (am am2 : am.bF()) {
            if (!(am2 instanceof y)) continue;
            y y2 = (y)am2;
            for (s s2 : y2.N()) {
                y2.a(s2, false);
            }
        }
    }

    public void completeAllUnitsQueues() {
        for (am am2 : am.bF()) {
            if (!(am2 instanceof l)) continue;
            l l2 = (l)((Object)am2);
            l2.dz();
        }
    }

    public boolean moveAllUnitsOnTeam(int n2, float f2, float f3) {
        com.corrodinggames.rts.game.n n3 = com.corrodinggames.rts.game.n.k(n2);
        if (n3 == null) {
            this.root.logWarn("Could not find team:" + n2);
            return false;
        }
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        e e2 = l2.cf.b(n3);
        for (am am2 : am.bF()) {
            if (!(am2 instanceof y)) continue;
            y y2 = (y)am2;
            if (y2.bX != n3) continue;
            e2.a(y2);
        }
        e2.a(f2, f3);
        return true;
    }

    public void showMessage(String string) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (string == null || string.trim().equals("")) {
            return;
        }
        string = string.replace("\\n", "\n");
        l2.bX.m(string);
    }

    public String unicodeTest1() {
        return "start \u00a5123 \u061c end";
    }

    public void setZoom(float f2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.cV = f2;
    }

    public boolean isNetworkGameActive() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return l2.N();
    }

    public int getLocalPlayerId() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return l2.bX.z.k;
    }

    public int numberOfHumanPlayers() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return l2.bX.an();
    }

    public int numberOfPlayersPlusAI() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return l2.bX.ao();
    }

    public int numberOfPlayerConnections() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return l2.bX.B();
    }

    public boolean enableFastSync() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bX.ai = 30;
        return true;
    }

    public boolean enableExtraNetworkDebug() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bX.g = true;
        return true;
    }

    public boolean throwIfAnyPlayerNotInSync() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bX.x();
        return true;
    }

    public boolean enableFastResyncTimer() {
        ad.c = true;
        return true;
    }

    public boolean enablePauseOnDesync() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bX.aj = true;
        return true;
    }

    public boolean networkSetIncomeMultiplier(float f2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        ah ah2 = l2.bX.e();
        ah2.h = f2;
        l2.bX.a(ah2);
        return true;
    }

    public boolean networkSetPortNumber(int n2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bQ.networkPort = n2;
        return true;
    }

    public boolean networkSetUdp(boolean bl2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bQ.udpInMultiplayer = bl2;
        return true;
    }

    public boolean networkDisconnect() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bX.b("debug");
        return true;
    }

    public boolean networkAbort() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        for (com.corrodinggames.rts.gameFramework.j.c c2 : l2.bX.aM) {
            if (!(c2.d instanceof h)) continue;
            com.corrodinggames.rts.gameFramework.l.e("Closing: " + c2.g());
            ((h)c2.d).d();
        }
        l2.bX.b("debug");
        return true;
    }

    public boolean disableNetworkOwnInfo() {
        ad.r = false;
        return true;
    }

    public boolean networkPause() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bX.aj = true;
        l2.bX.ak = true;
        return true;
    }

    public boolean plainTextDebugSave(boolean bl2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        com.corrodinggames.rts.gameFramework.y.a = bl2;
        return true;
    }

    public boolean checkDesync(int n2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.bX.ap != 0) {
            throw new RuntimeException("numberOfDesyncErrors==" + l2.bX.ap);
        }
        if (l2.bX.aq < n2) {
            throw new RuntimeException("game.network.numberOfDesyncPasses:" + l2.bX.aq + "<" + n2);
        }
        this.root.logDebug("numberOfDesyncPasses:" + l2.bX.aq);
        return true;
    }

    public int getNumberOfDesyncErrors() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return l2.bX.ap;
    }

    public int getNumberOfDesyncPasses() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return l2.bX.aq;
    }

    public int getNumberOfResyncSendsOrRecv() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return l2.bX.ar;
    }

    public boolean setMultiplayerMap(int n2, String string) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        ah ah2 = l2.bX.ay;
        ah2.a = ai.values()[n2];
        ah2.b = string;
        return true;
    }

    public boolean setMultiplayerSave(String string) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        ah ah2 = l2.bX.ay;
        ah2.a = ai.c;
        ah2.b = string;
        return true;
    }

    public void generateNewClientId() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bX.Y();
    }

    public void disableFog() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
    }

    public void overrideDeltaSpeed(float f2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bu = f2;
    }

    public void setGameSetting(String string, String string2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bQ.setValueDynamic(string, string2);
    }

    public void setNetworkaiDifficulty(int n2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        ah ah2 = l2.bX.e();
        ah2.f = n2;
        l2.bX.a(ah2);
    }

    public void setNetworkStartingUnits(int n2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        ah ah2 = l2.bX.e();
        ah2.g = n2;
        l2.bX.a(ah2);
    }

    public void startRandomUnitDesyncTest() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        e e2 = l2.cf.b();
        e2.i = com.corrodinggames.rts.game.n.i;
        e2.r = true;
        e2.u = 1;
        l2.bX.a(e2);
    }

    public void startRandomUnitStressTest() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        e e2 = l2.cf.b();
        e2.i = com.corrodinggames.rts.game.n.i;
        e2.r = true;
        e2.u = 2;
        l2.bX.a(e2);
    }

    public void runAllUnitTests() {
        this.root.logWarn("Running unit tests..");
        n n2 = new n();
        n2.a();
    }

    public void runAllLeakTests() {
        this.root.logWarn("Running leak tests..");
        com.corrodinggames.rts.a.a.b b2 = new com.corrodinggames.rts.a.a.b();
        b2.a();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean loadSaveFromSystemPath(String string) {
        boolean bl2;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        File file = new File(string);
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
        k k2 = new k(dataInputStream);
        try {
            bl2 = l2.ca.a(k2, false, false, false);
        }
        catch (Throwable throwable) {
            try {
                dataInputStream.close();
                bufferedInputStream.close();
                fileInputStream.close();
                throw throwable;
            }
            catch (IOException iOException) {
                throw new RuntimeException(iOException);
            }
        }
        dataInputStream.close();
        bufferedInputStream.close();
        fileInputStream.close();
        return bl2;
    }

    public void checkTeamCaches() {
        for (com.corrodinggames.rts.game.n n2 : com.corrodinggames.rts.game.n.c()) {
            if (!n2.t()) continue;
            throw new RuntimeException("Team cache difference on team:" + n2.k);
        }
    }

    public void setPathSpeedConf(boolean bl2) {
        this.forceNonThreaded = bl2;
    }

    public float getPathSpeed(int n2, float f2, float f3, float f4, float f5) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        b b2 = l2.bL;
        ArrayList<com.corrodinggames.rts.gameFramework.k.k> arrayList = new ArrayList<com.corrodinggames.rts.gameFramework.k.k>();
        b2.a(f4, f5);
        int n3 = b2.T;
        int n4 = b2.U;
        long l3 = br.a();
        d.a = 0;
        d.b = 0;
        d.c = 0;
        d.d = 0;
        d.e = 0;
        d.f = 0;
        d.g = 0;
        d.h = 0.0;
        d.i = 0.0;
        m.c = 0;
        d.u = 0;
        for (int i2 = 0; i2 < n2; ++i2) {
            com.corrodinggames.rts.gameFramework.k.k k2 = l2.bU.a(false);
            b2.a(f2, f3);
            k2.a(ao.b, (short)b2.T, (short)b2.U, null, false);
            b2.a(f4, f5);
            k2.a((short)b2.T, (short)b2.U, (short)0);
            k2.p = true;
            k2.q = 0;
            k2.r = false;
            l2.bU.a(k2, false, this.forceNonThreaded);
            arrayList.add(k2);
        }
        if (!this.forceNonThreaded) {
            return -1.0f;
        }
        float f6 = br.a(l3);
        int n5 = -1;
        for (com.corrodinggames.rts.gameFramework.k.k k3 : arrayList) {
            Object object;
            LinkedList linkedList = k3.a();
            int n6 = 0;
            for (Object object2 : linkedList) {
                ++n6;
            }
            if (n5 != -1 && n5 != n6) {
                object = "pathDistance inconsistency detected:" + n5 + "!=" + n6;
                com.corrodinggames.rts.gameFramework.l.b((String)object);
            }
            object = (p)linkedList.getLast();
            if (((p)object).a != n3 || ((p)object).b != n4) {
                Object object2;
                object2 = "path did not react goal, got to:" + ((p)object).a + "," + ((p)object).b + " (vs " + n3 + ", " + n4 + ")";
                com.corrodinggames.rts.gameFramework.l.b((String)object2);
            }
            n5 = n6;
        }
        com.corrodinggames.rts.gameFramework.l.b("hotBufferWatermark:" + d.a + ", nodesAdded:" + d.d + ", mainQueueWatermark:" + d.b + ", backlogWatermark:" + d.c + ", scannedA:" + d.e + ", scannedB:" + d.f + ", scannedC:" + d.g + ", time:" + br.a(d.i) + "/" + br.a(d.h) + ", dirtyPeak:" + d.u + ", dis:" + n5);
        if (m.c != 0) {
            com.corrodinggames.rts.gameFramework.l.b("newNodesCreated:" + m.c);
        }
        return f6;
    }

    public void muteSounds() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bM.b = true;
        l2.bN.f();
    }

    public void pong() {
    }
}
