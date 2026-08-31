/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;
import com.corrodinggames.rts.gameFramework.GameObject;
import com.corrodinggames.rts.game.map.MapEngine;

import network.reliableudp.ReliableSocket;
import com.corrodinggames.librocket.scripts.DebugUI$1;
import com.corrodinggames.librocket.scripts.MainUIController;
import com.corrodinggames.librocket.scripts.ScriptContext;
import com.corrodinggames.rts.platform.a;
import com.corrodinggames.rts.platform.net.TestRunner;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.TreeDecoration;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.CarrierUnit;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.ExtraManager;
import com.corrodinggames.rts.gameFramework.Command;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.MatchConfig;
import com.corrodinggames.rts.gameFramework.network.GameModeEnum;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.pathfinding.AStarSearch;
import com.corrodinggames.rts.gameFramework.pathfinding.FastNodeQueue;
import com.corrodinggames.rts.gameFramework.pathfinding.NodePool;
import com.corrodinggames.rts.gameFramework.pathfinding.PathNode;
import com.corrodinggames.rts.gameFramework.GameObject;
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

public class DebugUI
extends ScriptContext {
    MainUIController root;
    boolean allFeatures;
    ConcurrentLinkedQueue backgroundClientConnections;
    Thread backgroundConnectionThread;
    Runnable backgroundConnectionRunnable = new DebugUI$1(this);
    boolean forceNonThreaded = true;

    public DebugUI(MainUIController root) {
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
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bX.a(string);
    }

    public void setDdosProtection(boolean bl) {
        com.corrodinggames.rts.gameFramework.network.ServerListener.listenEnabled = bl;
    }

    public void lookAt(float f2, float f3) {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
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
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        UnitTypeHandle as2 = UnitRegistry.a(string);
        if (as2 == null) {
            this.root.logWarn("Could not find type:" + string);
            return null;
        }
        UnitInstance am2 = as2.a();
        am2.eo = f2;
        am2.ep = f3;
        try {
            am2.setTeamInternalById(n2);
        }
        catch (com.corrodinggames.rts.game.map.MapException f4) {
            throw new RuntimeException(f4);
        }
        com.corrodinggames.rts.game.PlayerState.c(am2);
        am2.cK = true;
        if (bl) {
            l2.b(f2, f3);
        }
        return am2.eh;
    }

    public int getMaxCustomUnitTypeId() {
        return com.corrodinggames.rts.game.units.custom.ModUnitRegistry.d.size();
    }

    public Long createCustomUnitFromTypeId(int n2, float f2, float f3, int n3, boolean bl2) {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.game.units.custom.ModUnitRegistry l3 = (com.corrodinggames.rts.game.units.custom.ModUnitRegistry)com.corrodinggames.rts.game.units.custom.ModUnitRegistry.d.get(n2);
        UnitInstance am2 = l3.a();
        am2.eo = f2;
        am2.ep = f3;
        try {
            am2.setTeamInternalById(n3);
        }
        catch (com.corrodinggames.rts.game.map.MapException f4) {
            throw new RuntimeException(f4);
        }
        com.corrodinggames.rts.game.PlayerState.c(am2);
        am2.cK = true;
        if (bl2) {
            l2.b(f2, f3);
        }
        return am2.eh;
    }

    public void enableFeatures(String string) {
        String string2 = com.corrodinggames.rts.gameFramework.GameUtils.e(string);
        if (string2.startsWith("221FC410BD29D786")) {
            this.allFeatures = true;
            com.corrodinggames.rts.gameFramework.commands.DebugServer.d = true;
            return;
        }
        throw new RuntimeException("unknown");
    }

    public void selectNextUnit() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        UnitInstance am2 = null;
        boolean bl2 = false;
        java.util.Iterator iterator = UnitInstance.bF().iterator();
        while (iterator.hasNext()) {
            UnitInstance am3 = (UnitInstance)iterator.next();
            UnitInstance am4;
            if (!(am3 instanceof UnitInstance) || (am4 = am3) instanceof TreeDecoration || am4.t()) continue;
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
        java.util.Iterator iterator = com.corrodinggames.rts.gameFramework.GameObject.dK().iterator();  // 02b Debug L198: w.dK() (F27 gameFramework/w=GameObject)
        while (iterator.hasNext()) {
            GameObject w2 = (GameObject)iterator.next();
            w2.a();
        }
    }

    public void killAllUnits() {
        java.util.Iterator iterator = UnitInstance.bF().iterator();
        while (iterator.hasNext()) {
            UnitInstance am2 = (UnitInstance)iterator.next();
            if (!(am2 instanceof UnitInstance)) continue;
            am2.cu = -1.0f;  // 02b Debug.java L213: var2.cu = -1.0F
        }
    }

    public boolean backgroundCurrentClientConnection() {
        if (!this.allFeatures) {
            return false;
        }
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (!l2.bX.B) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Not networked");
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
        for (com.corrodinggames.rts.gameFramework.network.PacketDecoder c2 : l2.bX.aM) {
            c2.t = true;
            this.backgroundClientConnections.add(c2);
            l2.bX.aM.remove(c2);
        }
        l2.bX.m("backgrounded");
        l2.bX.B = true;
        return true;
    }

    public boolean isTeamWipedOut(int n2) {
        com.corrodinggames.rts.game.PlayerState n3 = com.corrodinggames.rts.game.PlayerState.u(n2);
        if (n3 == null) {
            this.root.logWarn("Could not find team:" + n2);
            return true;
        }
        return n3.G;
    }

    public boolean isTeamDefeated(int n2) {
        com.corrodinggames.rts.game.PlayerState n3 = com.corrodinggames.rts.game.PlayerState.u(n2);
        if (n3 == null) {
            this.root.logWarn("Could not find team:" + n2);
            return true;
        }
        return n3.G;
    }

    public boolean isTeamInVictory(int n2) {
        com.corrodinggames.rts.game.PlayerState n3 = com.corrodinggames.rts.game.PlayerState.u(n2);
        if (n3 == null) {
            this.root.logWarn("Could not find team:" + n2);
            return false;
        }
        return n3.H;
    }

    public String getPlayerName(int n2) {
        com.corrodinggames.rts.game.PlayerState n3 = com.corrodinggames.rts.game.PlayerState.u(n2);
        if (n3 == null) {
            this.root.logWarn("Could not find team:" + n2);
            return null;
        }
        return n3.v;
    }

    public String getQueryStringOfPlayer(int n2) {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.game.PlayerState n3 = com.corrodinggames.rts.game.PlayerState.u(n2);
        if (n3 == null) {
            this.root.logWarn("Could not find team:" + n2);
            return null;
        }
        com.corrodinggames.rts.gameFramework.network.PacketDecoder c2 = l2.bX.c(n3);
        if (c2 == null) {
            this.root.logWarn("Found team but could not find connection for team:" + n2);
            return null;
        }
        return c2.o;
    }

    public boolean setTeamCredits(int n2, int n3) {
        com.corrodinggames.rts.game.PlayerState n4 = com.corrodinggames.rts.game.PlayerState.u(n2);
        if (n4 == null) {
            this.root.logWarn("Could not find team:" + n2);
            return false;
        }
        n4.o = n3;
        return true;
    }

    public boolean setTeamAllyGroup(int n2, int n3) {
        com.corrodinggames.rts.game.PlayerState n4 = com.corrodinggames.rts.game.PlayerState.u(n2);
        if (n4 == null) {
            this.root.logWarn("Could not find team:" + n2);
            return false;
        }
        n4.r = n3;
        return true;
    }

    public void giveUpgradeToAllUnits() {
        for (Object am2o : UnitInstance.bF()) {
            UnitInstance am2 = (UnitInstance)am2o;  // F17: raw DequeList 显式 cast (02b L335)
            ActionId c2;
            UnitType y2;
            GameAction s2;
            if (!(am2 instanceof UnitType) || (s2 = (y2 = (UnitType)am2).a(c2 = y2.cm())) == null) continue;
            y2.a(s2, false);
        }
    }

    public void giveAllActionsToAllUnits() {
        for (Object am2o : UnitInstance.bF()) {
            UnitInstance am2 = (UnitInstance)am2o;  // F17: raw DequeList 显式 cast (02b L335)
            if (!(am2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType)am2;
            for (GameAction s2 : (java.util.Collection<GameAction>) (java.util.Collection) y2.N()) {
                y2.a(s2, false);
            }
        }
    }

    public void completeAllUnitsQueues() {
        for (Object am2o : UnitInstance.bF()) {
            UnitInstance am2 = (UnitInstance)am2o;  // F17: raw DequeList 显式 cast (02b L335)
            if (!(am2 instanceof CarrierUnit)) continue;
            CarrierUnit l2 = (CarrierUnit) ((Object)am2);
            l2.dz();
        }
    }

    public boolean moveAllUnitsOnTeam(int n2, float f2, float f3) {
        com.corrodinggames.rts.game.PlayerState n3 = com.corrodinggames.rts.game.PlayerState.u(n2);
        if (n3 == null) {
            this.root.logWarn("Could not find team:" + n2);
            return false;
        }
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        Command e2 = l2.cf.b(n3);
        for (Object am2o : UnitInstance.bF()) {
            UnitInstance am2 = (UnitInstance)am2o;  // F17: raw DequeList 显式 cast (02b L335)
            if (!(am2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType)am2;
            if (y2.player != n3) continue;
            e2.a(y2);
        }
        e2.a(f2, f3);
        return true;
    }

    public void showMessage(String string) {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
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
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.cV = f2;
    }

    public boolean isNetworkGameActive() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return l2.N();
    }

    public int getLocalPlayerId() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return l2.bX.z.k;
    }

    public int numberOfHumanPlayers() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return l2.bX.getHumanPlayerCount();
    }

    public int numberOfPlayersPlusAI() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return l2.bX.getTotalPlayerCount();
    }

    public int numberOfPlayerConnections() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return l2.bX.getConnectedPlayerCount();
    }

    public boolean enableFastSync() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bX.ai = 30;
        return true;
    }

    public boolean enableExtraNetworkDebug() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bX.g = true;
        return true;
    }

    public boolean throwIfAnyPlayerNotInSync() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bX.checkAllForDesync();
        return true;
    }

    public boolean enableFastResyncTimer() {
        NetEngine.c = true;
        return true;
    }

    public boolean enablePauseOnDesync() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bX.aj = true;
        return true;
    }

    public boolean networkSetIncomeMultiplier(float f2) {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        MatchConfig ah2 = l2.bX.kickTeam();
        ah2.h = f2;
        l2.bX.registerRelayServer(ah2);  // 02b Debug L611: bX.a(ah)=MatchConfig 发送 (F26)
        return true;
    }

    public boolean networkSetPortNumber(int n2) {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bQ.networkPort = n2;
        return true;
    }

    public boolean networkSetUdp(boolean bl2) {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bQ.udpInMultiplayer = bl2;
        return true;
    }

    public boolean networkDisconnect() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bX.m("debug");
        return true;
    }

    public boolean networkAbort() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        for (com.corrodinggames.rts.gameFramework.network.PacketDecoder c2 : l2.bX.aM) {
            if (!(c2.d instanceof ReliableSocket)) continue;
            com.corrodinggames.rts.gameFramework.GlobalState.e("Closing: " + c2.g());
            ((ReliableSocket) c2.d).d();
        }
        l2.bX.m("debug");
        return true;
    }

    public boolean disableNetworkOwnInfo() {
        NetEngine.r = false;  // 02b Debug L521: ad=NetEngine 静态字段
        return true;
    }

    public boolean networkPause() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bX.aj = true;
        l2.bX.ak = true;
        return true;
    }

    public boolean plainTextDebugSave(boolean bl2) {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.GameSaver.a = bl2;
        return true;
    }

    public boolean checkDesync(int n2) {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
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
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return l2.bX.ap;
    }

    public int getNumberOfDesyncPasses() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return l2.bX.aq;
    }

    public int getNumberOfResyncSendsOrRecv() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return l2.bX.ar;
    }

    public boolean setMultiplayerMap(int n2, String string) {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        MatchConfig ah2 = l2.bX.ay;
        ah2.a = GameModeEnum.values()[n2];  // MatchConfig.a=GameModeEnum (ai 幻觉)
        ah2.b = string;
        return true;
    }

    public boolean setMultiplayerSave(String string) {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        MatchConfig ah2 = l2.bX.ay;
        ah2.a = GameModeEnum.c;
        ah2.b = string;
        return true;
    }

    public void generateNewClientId() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bX.generateClientId();
    }

    public void disableFog() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
    }

    public void overrideDeltaSpeed(float f2) {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bu = f2;
    }

    public void setGameSetting(String string, String string2) {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bQ.setValueDynamic(string, string2);
    }

    public void setNetworkaiDifficulty(int n2) {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        MatchConfig ah2 = l2.bX.kickTeam();
        ah2.f = n2;
        l2.bX.registerRelayServer(ah2);  // 02b Debug L611: bX.a(ah)=MatchConfig 发送 (F26)
    }

    public void setNetworkStartingUnits(int n2) {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        MatchConfig ah2 = l2.bX.kickTeam();
        ah2.g = n2;
        l2.bX.registerRelayServer(ah2);  // 02b Debug L611: bX.a(ah)=MatchConfig 发送 (F26)
    }

    public void startRandomUnitDesyncTest() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        Command e2 = l2.cf.b();
        e2.i = com.corrodinggames.rts.game.PlayerState.i;
        e2.r = true;
        e2.u = 1;  // 02b Debug L619: var2.u = 1 (DebugDesyncDetector 模式, DebugUI$1 幻觉)
        l2.bX.registerRelayServer(e2);  // 02b Debug L620/629: bX.a(e)=Command 发送 (F26)
    }

    public void startRandomUnitStressTest() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        Command e2 = l2.cf.b();
        e2.i = com.corrodinggames.rts.game.PlayerState.i;
        e2.r = true;
        e2.u = 2;
        l2.bX.registerRelayServer(e2);  // 02b Debug L620/629: bX.a(e)=Command 发送 (F26)
    }

    public void runAllUnitTests() {
        this.root.logWarn("Running unit tests..");
        TestRunner n2 = new TestRunner();
        n2.a();
    }

    public void runAllLeakTests() {
        this.root.logWarn("Running leak tests..");
        com.corrodinggames.rts.platform.net.b b2 = new com.corrodinggames.rts.platform.net.b();  // 02b Debug L640: a/a/b (networkSocks 测试)
        b2.a();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean loadSaveFromSystemPath(String string) throws IOException {
        boolean bl2;
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        File file = new File(string);
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
        InputNetStream k2 = new InputNetStream(dataInputStream);
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
        for (Object n2o : com.corrodinggames.rts.game.PlayerState.c()) {
            com.corrodinggames.rts.game.PlayerState n2 = (com.corrodinggames.rts.game.PlayerState)n2o;  // F17: raw ArrayList 显式 cast (02b L670)
            if (!n2.validateTeamTracker()) continue;
            throw new RuntimeException("Team cache difference on team:" + n2.k);
        }
    }

    public void setPathSpeedConf(boolean bl2) {
        this.forceNonThreaded = bl2;
    }

    public float getPathSpeed(int n2, float f2, float f3, float f4, float f5) {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        MapEngine b2 = l2.bL;
        ArrayList<com.corrodinggames.rts.gameFramework.pathfinding.PathCostCalculator> arrayList = new ArrayList<com.corrodinggames.rts.gameFramework.pathfinding.PathCostCalculator>();
        b2.a(f4, f5);
        int n3 = b2.scrollPixelX;
        int n4 = b2.scrollPixelY;
        long l3 = ExtraManager.a();
        FastNodeQueue.a = 0;
        FastNodeQueue.b = 0;
        FastNodeQueue.c = 0;
        FastNodeQueue.d = 0;
        FastNodeQueue.e = 0;
        FastNodeQueue.f = 0;
        FastNodeQueue.g = 0;
        FastNodeQueue.h = 0.0;
        FastNodeQueue.i = 0.0;
        NodePool.c = 0;
        FastNodeQueue.u = 0;
        for (int i2 = 0; i2 < n2; ++i2) {
            com.corrodinggames.rts.gameFramework.pathfinding.PathCostCalculator k2 = l2.bU.a(false);
            b2.a(f2, f3);
            k2.a(MovementTypeEnum.b, (short)b2.scrollPixelX, (short)b2.scrollPixelY, null, false);
            b2.a(f4, f5);
            k2.a((short)b2.scrollPixelX, (short)b2.scrollPixelY, (short)0);
            k2.p = true;
            k2.q = 0;
            k2.r = false;
            l2.bU.a(k2, false, this.forceNonThreaded);
            arrayList.add(k2);
        }
        if (!this.forceNonThreaded) {
            return -1.0f;
        }
        float f6 = ExtraManager.a(l3);
        int n5 = -1;
        for (com.corrodinggames.rts.gameFramework.pathfinding.PathCostCalculator k3 : arrayList) {
            Object object;
            LinkedList linkedList = k3.a();
            int n6 = 0;
            for (Object object2 : linkedList) {
                ++n6;
            }
            if (n5 != -1 && n5 != n6) {
                object = "pathDistance inconsistency detected:" + n5 + "!=" + n6;
                com.corrodinggames.rts.gameFramework.GlobalState.b((String)object);
            }
            object = (PathNode)linkedList.getLast();
            if (((PathNode)object).a != n3 || ((PathNode)object).b != n4) {
                Object object2;
                object2 = "path did not react goal, got to:" + ((PathNode)object).a + "," + ((PathNode)object).b + " (vs " + n3 + ", " + n4 + ")";
                com.corrodinggames.rts.gameFramework.GlobalState.b((String)object2);
            }
            n5 = n6;
        }
        com.corrodinggames.rts.gameFramework.GlobalState.b("hotBufferWatermark:" + FastNodeQueue.a + ", nodesAdded:" + FastNodeQueue.d + ", mainQueueWatermark:" + FastNodeQueue.b + ", backlogWatermark:" + FastNodeQueue.c + ", scannedA:" + FastNodeQueue.e + ", scannedB:" + FastNodeQueue.f + ", scannedC:" + FastNodeQueue.g + ", time:" + ExtraManager.a(FastNodeQueue.i) + "/" + ExtraManager.a(FastNodeQueue.h) + ", dirtyPeak:" + FastNodeQueue.u + ", dis:" + n5);
        if (NodePool.c != 0) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("newNodesCreated:" + NodePool.c);
        }
        return f6;
    }

    public void muteSounds() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bM.b = true;
        l2.bN.f();
    }

    public void pong() {
    }
}
