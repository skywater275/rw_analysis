/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.Projectile;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.NetworkPlayer;
import com.corrodinggames.rts.game.HumanPlayer;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.custom.ModLoader;
import com.corrodinggames.rts.game.units.custom.bd;
import com.corrodinggames.rts.game.units.Factory;
import com.corrodinggames.rts.gameFramework.BaseGameObject;
import com.corrodinggames.rts.gameFramework.ExtraManager;
import com.corrodinggames.rts.gameFramework.GamePhase;
import com.corrodinggames.rts.gameFramework.filesystem.FileLoader;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.ByteArrayPacketBuilder;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.aicore.AIWaveSystem;
import com.corrodinggames.rts.gameFramework.ProjectileType2;
import com.corrodinggames.rts.gameFramework.utility.AssetStream;
import com.corrodinggames.rts.gameFramework.utility.DequeList;
import com.corrodinggames.rts.gameFramework.GameObject;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

public strictfp class GameSaver {
    public static boolean a = false;
    final boolean b;
    int c;
    int d;

    public GameSaver() {
        if (!l.as) {
            // empty if block
        }
        this.b = false;
        this.c = -9999;
        this.d = -9999;
    }

    public File a(String string, boolean bl) {
        return GameSaver.a(string, "saves/", bl);
    }

    public static File a(String string, String string2, boolean bl) {
        return com.corrodinggames.rts.gameFramework.filesystem.FileLoader.a(string, string2, bl);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void b(String string, boolean bl) {
        boolean bl2;
        GlobalState l2;
        block21: {
            l2 = GlobalState.B();
            String string2 = string;
            if (string2 != null && !string2.endsWith(".rwsave")) {
                string2 = string2 + ".rwsave";
            }
            String string3 = "SD card";
            File file = null;
            bl2 = false;
            try {
                FilterOutputStream filterOutputStream;
                file = this.a(string2 + ".tmp", true);
                if (file.exists()) {
                    file = this.a(string2 + ".tmp2", true);
                }
                File file2 = this.a(string2, true);
                string3 = file2.getAbsolutePath();
                GlobalState.e("Saving game to: " + string3);
                OutputStream outputStream = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.a(file, false);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
                if (!a) {
                    filterOutputStream = new DataOutputStream(bufferedOutputStream);
                    OutputNetStream as2 = new OutputNetStream((DataOutputStream)filterOutputStream);
                    try {
                        this.a(as2);
                    }
                    finally {
                        filterOutputStream.close();
                        bufferedOutputStream.close();
                        outputStream.close();
                    }
                }
                filterOutputStream = new PrintStream(bufferedOutputStream);
                ByteArrayPacketBuilder aw2 = new ByteArrayPacketBuilder((PrintStream)filterOutputStream);  // 02b j/aw (TextPacketBuilder 为错误副本)
                try {
                    this.a(aw2);
                }
                finally {
                    ((PrintStream)filterOutputStream).close();
                    bufferedOutputStream.close();
                    outputStream.close();
                }
                GlobalState.reportProblem("DEBUG plain text save created");
                if (bl && GlobalState.at() && com.corrodinggames.rts.gameFramework.filesystem.FileLoader.i(file2.getAbsolutePath())) {
                    GlobalState.e("Autosave file already exists: " + file2.getAbsolutePath());
                    boolean bl3 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.b(file2);
                    if (!bl3) {
                        GlobalState.e("Old autosave failed to delete");
                    }
                }
                GlobalState.e("Finished writing save, renaming to final filename");
                boolean bl4 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.b(file, file2);
                if (!bl4) {
                    GlobalState.e("Failed to rename to final file");
                    throw new IOException("Failed to rename to final file. Check file permissions of storage.");
                }
                com.corrodinggames.rts.gameFramework.filesystem.FileLoader.reset(file2);  // 02b e/a.c(File)
                bl2 = true;
            }
            catch (Exception exception) {
                if (bl) {
                    GlobalState.e("Auto save failed: " + exception.getMessage());
                    return;
                }
                exception.printStackTrace();
                String string4 = GameUtils.b(exception);
                String string5 = "Error saving game, please check permissions, disk space, etc. (" + string4 + ")";
                l2.a(string5, 1);
                if (file != null && com.corrodinggames.rts.gameFramework.filesystem.FileLoader.i(file.getAbsolutePath())) {
                    GlobalState.e("saveGame: Removing temp save file after crash");
                    com.corrodinggames.rts.gameFramework.filesystem.FileLoader.b(file);
                }
            }
            catch (OutOfMemoryError outOfMemoryError) {
                outOfMemoryError.printStackTrace();
                String string6 = "Error. Run out of memory error while saving game to " + string3 + ".";
                l2.a(string6, 1);
                if (file == null || !com.corrodinggames.rts.gameFramework.filesystem.FileLoader.i(file.getAbsolutePath())) break block21;
                GlobalState.e("saveGame: Removing temp save file after crash");
                com.corrodinggames.rts.gameFramework.filesystem.FileLoader.b(file);
            }
        }
        if (bl2) {
            if (bl) {
                l2.bS.i.a("Auto Saved", 1000);
            } else {
                l2.bS.h.a(null, "Game saved");
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void a(OutputNetStream as2) throws IOException {
        GlobalState l2 = GlobalState.B();
        long l3 = System.currentTimeMillis();
        GlobalState.b("GameSaver", "saveCurrentMap took:" + (System.currentTimeMillis() - l3));
        long l4 = System.currentTimeMillis();
        try {
            Object object;
            BaseGameObject bq22;
            as2.c("rustedWarfareSave");
            int n2 = l2.c(true);
            as2.a(n2);
            as2.a(96);
            as2.a(l2.ar);
            as2.a("saveCompression", true);
            as2.e("customUnitsBlock");
            ModUnitRegistry.a(as2);
            as2.a("customUnitsBlock");
            as2.e("gameSetup");
            boolean bl2 = l2.bX.B || l2.bX.F;
            as2.a(l2.bX.B);
            as2.a(l2.bX.F);
            as2.a(bl2);
            if (bl2) {
                l2.bX.registerRelayServer(as2);
            }
            as2.a("gameSetup");
            as2.c(l2.dl);
            boolean bl3 = l2.dm != null;
            as2.a(bl3);
            if (bl3) {
                GlobalState.e("Writing remote map steam into save");
                as2.a(l2.dm);
            }
            as2.a(l2.by);
            as2.a(l2.cy + l2.cI);
            as2.a(l2.cz + l2.cJ);
            as2.a(l2.cV);
            as2.a(l2.bV.a);  // 02b L186: var2.bV.a (isDead 为幻觉名)
            as2.a(0);
            as2.e();
            l2.bL.a(as2);
            as2.a(l2.bv);
            as2.a(l2.bL.tileHeight);
            as2.a(l2.bL.fogGrid);
            as2.a(l2.bL.visibilityGrid);
            as2.a(l2.ce != null);
            if (l2.ce != null) {
                l2.ce.serializeToStream(as2);  // 02b L196: n/f.a(as)
            }
            as2.e();
            int n3 = -1;
            if (l2.bs != null) {
                n3 = l2.bs.k;
            }
            as2.a(n3);
            as2.a(PlayerState.c);
            for (int i2 = 0; i2 < PlayerState.c; ++i2) {
                bq22 = PlayerState.u(i2);
                as2.a(bq22 instanceof com.corrodinggames.rts.game.ai.AIStrategy);
                as2.a(bq22 instanceof NetworkPlayer);  // 02b game/c=NetworkPlayer
                as2.a(bq22 != null);
                if (bq22 == null) continue;
                ((PlayerState) bq22).b(as2);  // 02b L216: n.b(as)
            }
            if (!l2.bS.e) {
                // empty if block
            }
            as2.d("Section: unit shells");
            as2.a(w.er.size());
            for (BaseGameObject bq22_236 : (java.util.Collection<BaseGameObject>) (java.util.Collection) w.er) {
                if (bq22_236 == null) {
                    throw new RuntimeException("Found null in fastGameObjectList");
                }
                if (bq22_236 instanceof com.corrodinggames.rts.game.units.UnitInstance) {
                    object = (UnitInstance) bq22_236;  // 02b L237: (am)var17
                    if (((UnitInstance) object).r() instanceof UnitTypeHandle) {  // 02b L238: r() instanceof ar
                        as2.c(1);
                        as2.a((Enum) ((UnitTypeHandle) ((UnitInstance) object).r()));  // 02b L240: a((Enum)((ar)r()))
                    } else {
                        if (!(((UnitInstance) object).r() instanceof ModUnitRegistry)) throw new IOException("Unhandled getUnitType on save:" + ((UnitInstance) object).r().getClass().toString());
                        as2.c(3);
                        String string = ((ModUnitRegistry)((UnitInstance) object).r()).M;
                        as2.c(string);
                    }
                } else {
                    as2.c(2);
                    if (bq22_236 instanceof Projectile) {
                        as2.c(1);
                    } else if (bq22_236 instanceof MovementController) {
                        as2.c(2);
                    } else if (bq22_236 instanceof com.corrodinggames.rts.gameFramework.effects.DrawEffect) {
                        as2.c(3);
                    } else {
                        object = null;
                        if (bq22_236.getClass() == null) throw new IOException("Unhandled class on save: " + (String)object);
                        object = bq22_236.getClass().toString();
                        throw new IOException("Unhandled class on save: " + (String)object);
                    }
                }
                as2.a(((GameObject) bq22_236).eh);  // 02b w.eh
            }
            as2.d("Section: CurrentUnitId");
            as2.a(l2.bX.getCurrentGameId());
            l2.bV.a(as2);  // 02b L273: var2.bV.a(var1)
            l2.bS.serializeToStream(as2);  // 02b L274: f/g.a(as)
            l2.bY.a(as2);
            for (int i3 = 0; i3 < PlayerState.c; ++i3) {
                bq22 = PlayerState.u(i3);
                if (bq22 == null) continue;
                ((PlayerState) bq22).serializeToStream(as2);  // 02b L280: n.a(as)  // 02b L280: n.a(as)
            }
            as2.e();
            for (BaseGameObject bq22_279 : (java.util.Collection<BaseGameObject>) (java.util.Collection) w.er) {
                if (as2.f()) {
                    object = bq22_279.getClass().getSimpleName();
                    if (bq22_279 instanceof com.corrodinggames.rts.game.units.UnitInstance) {
                        object = ((UnitInstance) bq22_279).r().i();  // 02b L299: am.r().i()
                    }
                    as2.d("Saving unit:" + (String)object + " (id" + ((GameObject) bq22_279).eh + ")");
                }
                ((GameObject) bq22_279).a(as2);  // 02b L305: w.a(as)
                as2.e();
            }
            as2.a("saveCompression");
            as2.e();
            as2.c("<SAVE END>");
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            throw iOException;
        }
        GlobalState.b("GameSaver", "saveGame took:" + (System.currentTimeMillis() - l4));
    }

    public String a(String string) {
        if (string == null) {
            return null;
        }
        if (string.equals("maps/normal/l010;mission_1__-__Dividing_River.tmx")) {
            return "maps/normal/l010;[demo]mission_1__-__Dividing_River.tmx";
        }
        if (string.equals("maps/normal/l030;mission_3__-__Crossfire.tmx")) {
            return "maps/normal/l030;[demo]mission_3__-__Crossfire.tmx";
        }
        return string;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean c(String string, boolean bl2) {
        boolean bl3;
        GlobalState l2 = GlobalState.B();
        File file = this.a(string, false);
        if (file.isDirectory()) {
            l2.a("Could not load, is FileLoader directory", 1);
            return false;
        }
        AssetStream j2 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.k(file.getAbsolutePath());  // 02b utility/j=AssetStream
        if (j2 == null) {
            l2.a("Could not load, failed to open: " + com.corrodinggames.rts.gameFramework.filesystem.FileLoader.d(file.getAbsolutePath()), 1);
            return false;
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(j2);
        DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
        InputNetStream k2 = new InputNetStream(dataInputStream);
        try {
            bl3 = this.a(k2, bl2, false, false);
        }
        catch (Throwable throwable) {
            try {
                dataInputStream.close();
                bufferedInputStream.close();
                j2.close();  // AssetStream extends InputStream
                throw throwable;
            }
            catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        }
        try {
            dataInputStream.close();
            bufferedInputStream.close();
            ((InputStream)j2).close();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        return bl3;
    }

    public void a(String string, OutputNetStream as2) throws IOException {
        File file = this.a(string, false);
        if (file == null) {
            throw new IOException("Failed to get game save: " + string);
        }
        as2.a(file);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public synchronized boolean a(InputNetStream k2, boolean bl2, boolean bl3, boolean bl4) {
        GlobalState l2 = GlobalState.B();
        try {
            boolean bl5;
            String string;
            ExtraManager br2 = l2.cd;
            if (this.b) {
                br2.a(GamePhase.y);  // 02b bs=GamePhase
            }
            ArrayList<Long> arrayList = null;
            if (bl4) {
                arrayList = new ArrayList<Long>();
                for (Object object : l2.bS.bZ) {
                    arrayList.add(((GameObject) object).eh);  // MusicController 为幻觉名
                }
            }
            try {
                string = k2.readString();
            }
            catch (RuntimeException eOFException) {
                eOFException.printStackTrace();
                String string2 = "Failed to load save. (End of file trying to read header)";
                GlobalState.b(string2);
                l2.a(string2, 1);
                return false;
            }
            // 02b: catch(IOException) 合并入上方 catch(RuntimeException) (v19.133f96 清理)
            if (!string.equals("rustedWarfareSave")) {
                Object object;
                GlobalState.b("Map Load: Header is not correct:" + string.substring(0, Math.min(string.length(), 50)));
                object = "Failed to load save. (Could not find correct header)";
                if (string.equals("rustedWarfareReplay")) {
                    object = "Failed to load save. (This file appears to be FileLoader replay file, not FileLoader save file)";
                }
                GlobalState.b((String)object);
                l2.a((String)object, 1);
                return false;
            }
            k2.readInt();
            int n2 = k2.readInt();
            GlobalState.b("gameSaver", "Loading save from version: " + n2);
            k2.a(n2);
            if (n2 > 96) {
                l2.a("Cannot load: This save was made with FileLoader newer game", 1);
                return false;
            }
            if (n2 >= 5) {
                k2.readBoolean();
            }
            if (n2 >= 23) {
                br2.a(GamePhase.B);
                k2.a("saveCompression", true);
                br2.b(GamePhase.B);
            }
            if (n2 >= 54) {
                k2.b("customUnitsBlock");
                if (l2.cb.j() && !bl4) {
                    GlobalState.e("Loading mods from replay");
                    try {
                        ModUnitRegistry.a(k2);
                    }
                    catch (com.corrodinggames.rts.game.units.custom.bd bd2) {  // 02b custom/bd (ReplayFrame 为幻觉名)
                        GlobalState.e("Replay load: Missing unit:" + bd2.getMessage() + " d:" + bd2.amountValue);  // 02b bo.b
                        l2.i(bd2.getMessage() + ", this is likely to cause the replay to desync (reverting to default units & mods)");
                        com.corrodinggames.rts.game.units.custom.ModLoader.reloadAllMods(true);  // 02b L443: custom/ag.b(true) (KeyTrigger 为幻觉名)
                    }
                }
                k2.d("customUnitsBlock");
            }
            Integer n3 = null;
            Integer n4 = null;
            if (l2.cb.j() && bl4) {
                n3 = l2.bB;
                n4 = l2.bC;
            }
            if (n2 >= 56) {
                boolean bl6;
                boolean bl7;
                k2.b("gameSetup");
                bl5 = bl7 = k2.readBoolean();
                boolean bl8 = false;
                if (n2 >= 94) {
                    bl8 = k2.readBoolean();
                    bl5 = k2.readBoolean();
                }
                boolean bl9 = bl6 = l2.cb.j() || !l2.bX.B;
                if (bl6 && !bl4 && bl5) {
                    GlobalState.e("Using game rules from save");
                    l2.cb.O = true;
                    l2.bX.registerRelayServer(k2);
                    n3 = l2.bB;
                    n4 = l2.bC;
                    if (!(!bl7 && !bl8 || l2.bX.F || l2.bX.B || l2.cb.j())) {
                        GlobalState.e("Enabling use of singlePlayer rules from saved game.");
                        l2.bX.F = true;
                    }
                }
                k2.d("gameSetup");
            }
            l2.dm = null;
            String string4 = k2.readString();
            string4 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.o(string4);
            l2.dl = this.a(string4);
            bl5 = false;
            if (n2 >= 72 && (bl5 = k2.readBoolean())) {
                GlobalState.e("Reading remote map stream");
                l2.dm = k2.u();
            }
            if (l2.bX.B && !l2.bX.C && bl4 && l2.bX.aB != null && !bl5) {
                l2.dl = "";
                l2.dm = l2.bX.aB;
            }
            br2.a(GamePhase.z);
            if (bl4) {
                l2.a(true, true, com.corrodinggames.rts.gameFramework.GameStateEnum.c);  // 02b s.c
                if (GlobalState.at()) {
                    l2.dv = true;
                }
            } else {
                l2.a(true, com.corrodinggames.rts.gameFramework.GameStateEnum.c);
            }
            if (!l2.bL.cameraLocked) {
                GlobalState.e("Not loading save because map failed to load");
                return false;
            }
            if (n3 != null) {
                l2.bB = n3;
            }
            if (n4 != null) {
                n4 = l2.bC;
            }
            GlobalState l3 = l2;
            synchronized (l3) {
                int n5;
                GameObject w2;
                Object object;
                PlayerState n6;
                int n7;
                int n8;
                int n9;
                boolean bl10;
                br2.b(GamePhase.z);
                l2.by = k2.readInt();
                float f2 = k2.readFloat();
                float f3 = k2.readFloat();
                float f4 = k2.readFloat();
                if (!bl4) {
                    l2.b(f2, f3);
                    l2.cV = f4;
                }
                if (n2 >= 18) {
                    l2.bV.a = k2.readInt();  // 02b L536: bV.a (isDead 为幻觉名)
                }
                k2.readInt();
                if (n2 >= 19) {
                    k2.a("end of setup");
                }
                l2.bL.a(k2);
                if (n2 >= 86) {
                    bl10 = k2.readBoolean();
                    boolean bl11 = k2.readBoolean();
                    boolean bl12 = k2.readBoolean();
                    boolean bl13 = k2.readBoolean();
                    if (!bl2 && !bl10) {
                        l2.bL.tileHeight = bl11;
                        l2.bL.fogGrid = bl12;
                        l2.bL.visibilityGrid = bl13;
                    }
                }
                if (bl10 = k2.readBoolean()) {
                    if (l2.ce == null) {
                        GlobalState.b("gameSaver", "making new mission engine on load, this shouldn't happen");
                        l2.ce = new com.corrodinggames.rts.gameFramework.aicore.AIWaveSystem();  // 02b L562: n.f (MusicController 为幻觉名)
                        l2.ce.reset(false);  // 02b L563: ce.a(false) (03 名 reset)
                    }
                    l2.ce.reset(k2);  // 02b L566: n/f.a(k) (03 名 reset)
                }
                if (n2 >= 19) {
                    k2.a("start of teams");
                }
                GlobalState.b("gameSaver", "loading teams");
                PlayerState[] nArray = new PlayerState[PlayerState.e];
                n9 = -1;
                if (n2 >= 36) {
                    n9 = k2.readInt();
                }
                n8 = 8;
                if (n2 >= 49) {
                    n8 = k2.readInt();
                    PlayerState.b(n8, false);
                    for (n7 = 0; n7 < PlayerState.c; ++n7) {
                        if (n7 < n8 || bl2 || (n6 = PlayerState.u(n7)) == null) continue;
                        n6.updateResourceDisplay();
                    }
                }
                for (n7 = 0; n7 < n8; ++n7) {
                    boolean bl12;
                    PlayerState n10 = n6 = PlayerState.u(n7);
                    boolean bl13 = k2.readBoolean();
                    boolean bl14 = false;
                    if (n2 >= 7) {
                        bl14 = k2.readBoolean();
                    }
                    if (bl12 = k2.readBoolean()) {
                        if (bl13) {
                            if (n6 == null || !(n6 instanceof com.corrodinggames.rts.game.ai.AIStrategy)) {
                                if (bl2 && !bl4 && n6 != null) {
                                    GlobalState.b("Would replace team:" + n7 + " with AI, writing to dummy AI");
                                    nArray[n7] = n6 = new com.corrodinggames.rts.game.ai.AIStrategy(n7, false);
                                } else {
                                    if (bl4) {
                                        GlobalState.b("Adding new AI " + n7 + " on resync");
                                    }
                                    n6 = new com.corrodinggames.rts.game.ai.AIStrategy(n7);
                                }
                            }
                        } else if (bl14) {
                            if (n6 == null || !(n6 instanceof NetworkPlayer)) {
                                if (bl2) {
                                    GlobalState.b("Replacing team:" + n7 + " with NetworkedPlayer");
                                }
                                n6 = new NetworkPlayer(n7);
                            }
                        } else if (n6 == null || !(n6 instanceof HumanPlayer)) {
                            if (bl2) {
                                GlobalState.b("Replacing team:" + n7 + " with Player");
                                if (n6 != null) {
                                    n6.c("Existing");
                                }
                            }
                            n6 = new HumanPlayer(n7);
                        }
                        Integer n11 = n6.z;
                        if (n2 >= 2) {
                            n6.b(k2);
                        } else {
                            n6.c(k2);
                        }
                        if (bl4) continue;
                        n6.i();
                        if (bl2) {
                            n6.z = n11;
                            n6.c("networkLoad aiDifficultyOverride=" + n11);
                            l2.bX.registerRelayServer(n6);
                            l2.bX.m(n6);
                        }
                        if (n10 == null || n6 == n10) continue;
                        n10.c("Transfering team stats");
                        n10.o = n6.o;
                        n10.getTeamStatModifiers().a(n6.getTeamStatModifiers());
                        continue;
                    }
                    if (bl2 && !l2.cb.j()) {
                        GlobalState.b("GameSaver: Would normally remove team:" + n7 + "");
                        nArray[n7] = PlayerState.g;
                        continue;
                    }
                    PlayerState n12 = PlayerState.u(n7);
                    if (n12 == null) continue;
                    n12.updateResourceDisplay();
                }
                n7 = 0;
                boolean bl15 = false;
                l2.bX.updateAllAINames();
                if (l2.cb.j()) {
                    l2.bs = PlayerState.i;
                } else if (l2.bX.B) {
                    int n13;
                    if (l2.bX.z != null && (n13 = l2.bX.z.k) != -3) {
                        PlayerState n14 = PlayerState.u(n13);
                        if (n14 == null) {
                            throw new RuntimeException("GameSaver: Cannot relink player team: " + n13);
                        }
                        l2.bs = n14;
                    }
                } else if (n9 != -1 && n9 != -3) {
                    l2.bs = PlayerState.u(n9);
                } else {
                    for (int i2 = 0; i2 < PlayerState.c; ++i2) {
                        if (!(PlayerState.u(i2) instanceof HumanPlayer)) continue;
                        l2.bs = PlayerState.u(i2);
                    }
                }
                DequeList o2 = GameObject.dK();
                for (GameObject w3 : (java.util.Collection<GameObject>) (java.util.Collection) o2) {
                    w3.a();
                }
                if (l2.aa()) {
                    o2 = GameObject.dK();
                    for (GameObject w4 : (java.util.Collection<GameObject>) (java.util.Collection) o2) {
                        if (w4.eh != 0L) continue;
                        if (!(w4 instanceof com.corrodinggames.rts.game.units.UnitInstance)) throw new RuntimeException("GameLoad preload: Found object in list with id:0");
                        GlobalState.e("object: " + ((com.corrodinggames.rts.game.units.UnitInstance) w4).c());
                        throw new RuntimeException("GameLoad preload: Found object in list with id:0");
                    }
                }
                boolean bl16 = false;
                int n15 = k2.readInt();
                for (int i3 = 0; i3 < n15; ++i3) {
                    Object w5 = null;
                    byte by = k2.d();
                    if (by == 1) {
                        UnitRegistry ar2 = (UnitRegistry) k2.b(UnitRegistry.class);
                        if (ar2 == UnitRegistry.Y) {
                            if (l2.cb.j() || l2.bv) {
                                GlobalState.e("Creating DebugEditorBuilder for replay");
                                w5 = new Factory(false);
                                object = l2.bS.getDebugFactory();
                                if (object == null || ((Factory) object).ej) {
                                    GlobalState.e("Relinking editor");
                                    l2.bS.a((Factory) w5);
                                }
                            } else {
                                GlobalState.e("Creating DebugEditorBuilder for load");
                                w5 = new Factory(false);
                                n7 = 1;
                            }
                        } else {
                            w5 = ar2.a();
                        }
                    } else if (by == 3) {
                        Object object2;
                        String string5 = k2.readString();
                        object = ModUnitRegistry.n(string5);
                        if (object == null) {
                            object2 = "Could not find custom unit:" + string5;
                            GlobalState.e((String)object2);
                            if (!bl16) {
                                bl16 = true;
                                NetEngine.g((String)object2);
                            }
                            if ((object = ModUnitRegistry.b) == null) {
                                throw new RuntimeException("Could not find custom unit:" + string5 + " and missingPlaceHolder is null");
                            }
                        }
                        if ((object2 = ModUnitRegistry.getCreditCost((com.corrodinggames.rts.game.units.UnitTypeHandle)object)) != null) {
                            if (object2 instanceof ModUnitRegistry) {
                                object = (ModUnitRegistry)object2;
                            } else {
                                GlobalState.b("replacement not FileLoader custom unit:" + ((com.corrodinggames.rts.game.units.UnitTypeHandle)object2).i());
                            }
                        }
                        w5 = ((ModUnitRegistry)object).a();
                    } else {
                        if (by != 2) throw new IOException("Unhandled basic type on load:" + by);
                        byte by2 = k2.d();
                        if (by2 == 1) {
                            w5 = new Projectile();
                        } else if (by2 == 2) {
                            w5 = new MovementController(false);
                        } else {
                            if (by2 != 3) throw new IOException("Unhandled gameType on load:" + by2);
                            w5 = new com.corrodinggames.rts.gameFramework.effects.DrawEffect(l2.bR);
                        }
                    }
                    ((GameObject) w5).eh = k2.i();
                    if (((GameObject) w5).eh == 0L) {
                        GlobalState.b("GameSaver: Adding object with id==0");
                        if (w5 instanceof com.corrodinggames.rts.game.units.UnitInstance) {
                            GlobalState.b(((com.corrodinggames.rts.game.units.UnitInstance) w5).toFullDebugString());
                        }
                        bl15 = true;
                    }
                    com.corrodinggames.rts.gameFramework.GameObject.dL();  // 02b: w.dL() (EffectConfig 幻觉名修正)
                }
                if (n2 >= 3) {
                    long l4 = k2.i();
                    if (l4 <= 0L) {
                        GlobalState.a("GameLoad: Trying to set next unit id <= 0: " + l4);
                        l4 = 100000L;
                    }
                    l2.bX.a(l4);
                } else {
                    l2.bX.a(100000L);
                }
                if (n2 >= 24) {
                    l2.bV.a(k2);
                }
                if (n2 >= 4) {
                    l2.bS.a(k2, bl4);
                }
                if (n2 >= 57) {
                    l2.bY.a(k2, bl4);
                }
                if (n2 >= 7) {
                    for (int i4 = 0; i4 < n8; ++i4) {
                        PlayerState n16 = PlayerState.u(i4);
                        if (nArray[i4] != null && (n16 = nArray[i4]) == PlayerState.g) {
                            n16 = null;
                        }
                        if (n16 == null) continue;
                        Integer n17 = n16.z;
                        n16.c(k2);
                        if (bl4) continue;
                        if (bl2) {
                            n16.z = n17;
                            n16.c("networkLoad2 aiDifficultyOverride=" + n17);
                        }
                        l2.bX.registerRelayServer(n16);
                        l2.bX.m(n16);
                    }
                } else if (n2 >= 2) {
                    // empty if block
                }
                if (n2 >= 10) {
                    k2.a("Pre-unit data");
                }
                br2.a(GamePhase.A);
                DequeList o3 = GameObject.dK();
                GlobalState.b("gameSaver", "Loading unit data for " + o3.size() + " objects.");
                for (GameObject w6 : (java.util.Collection<GameObject>) (java.util.Collection) o3) {
                    w6.a(k2);
                    if (n2 < 10) continue;
                    k2.a("post unit: " + w6.getClass().toString() + " with id:" + w6.eh);
                }
                GlobalState.b("gameSaver", "Loading unit data done.");
                br2.b(GamePhase.A);
                if (n7 != 0) {
                    DequeList o4 = GameObject.dK();
                    Iterator iterator = o4.iterator();
                    while (iterator.hasNext()) {
                        GameObject w7 = (GameObject) iterator.next();
                        if (!(w7 instanceof com.corrodinggames.rts.game.units.UnitInstance)) continue;
                        object = (com.corrodinggames.rts.game.units.UnitInstance) w7;
                        if (l2.cb.j() || l2.bv || ((com.corrodinggames.rts.game.units.UnitInstance) object).r() != UnitRegistry.Y) continue;
                        ((com.corrodinggames.rts.game.units.UnitInstance) object).ci();
                    }
                }
                if (n2 >= 23) {
                    k2.d("saveCompression");
                }
                if (n2 >= 19) {
                    k2.a("End of Save");
                    k2.readString();
                }
                GlobalState.b("gameSaver", "Checking for ID overlaps");
                int n18 = 0;
                boolean bl17 = true;
                if (bl17) {
                    GameObject[] wArray = GameObject.er.a();
                    int n19 = GameObject.er.size();
                    for (int i5 = 0; i5 < n19; ++i5) {
                        w2 = wArray[i5];
                        if (w2.eh == 0L) {
                            GlobalState.b("GameSaver: Fixing object with zero id.");
                            w2.eh = l2.bX.getNextUnitId();
                        }
                        for (int i6 = i5 + 1; i6 < n19; ++i6) {
                            GameObject w8 = wArray[i6];
                            if (w2 == w8 || w2.eh != w8.eh) continue;
                            ++n18;
                            w8.eh = l2.bX.getNextUnitId();
                        }
                    }
                }
                GlobalState.b("gameSaver", "clearing out dead units.");
                GlobalState.e("Unit.fastLiveUnitList before:" + UnitInstance.bE.size());
                Iterator iterator = UnitInstance.bE.iterator();
                while (iterator.hasNext()) {
                    UnitInstance am2 = (UnitInstance) iterator.next();
                    if (!am2.bV) continue;
                    iterator.remove();
                }
                GlobalState.e("Unit.fastLiveUnitList after:" + UnitInstance.bE.size());
                if (n18 > 0) {
                    if (n2 <= 2) {
                        l2.a("Warning: " + n18 + " errors were found in this save, this is due to FileLoader bug in the old version", 1);
                    } else {
                        l2.a("Warning: " + n18 + " errors were found in this save", 1);
                    }
                }
                GlobalState.b("gameSaver", "Fixing map cost.");
                l2.bU.a((UnitType)null);
                l2.bU.b();
                GlobalState.b("gameSaver", "Fixing map cost done.");
                PlayerState.markAllPlayersDirty();
                for (n5 = 0; n5 < PlayerState.c; ++n5) {
                    PlayerState n20 = PlayerState.u(n5);
                    if (n20 == null) continue;
                    n20.d(false);
                }
                PlayerState.e();
                GlobalState.b("gameSaver", "Rebuilt unit caches");
                PlayerState.markAllPlayersDirty();
                PlayerState.onResourceChanged();
                PlayerState.i.d(false);
                PlayerState.h.d(false);
                for (n5 = 0; n5 < PlayerState.c; ++n5) {
                    PlayerState n21 = PlayerState.u(n5);
                    if (n21 == null || !(n21 instanceof com.corrodinggames.rts.game.ai.AIStrategy)) continue;
                    com.corrodinggames.rts.game.ai.AIStrategy a2 = (com.corrodinggames.rts.game.ai.AIStrategy)n21;
                    a2.aq();
                }
                if (arrayList != null) {
                    l2.bS.y();
                    Iterator iterator2 = arrayList.iterator();
                    while (iterator2.hasNext()) {
                        long l5 = (Long)iterator2.next();
                        w2 = GameObject.a(l5, true);
                        if (w2 == null) continue;
                        l2.bS.k((com.corrodinggames.rts.game.units.UnitInstance) w2);
                    }
                }
                if (l2.aa()) {
                    DequeList o5 = GameObject.dK();
                    for (GameObject w9 : (java.util.Collection<GameObject>) (java.util.Collection) o5) {
                        if (w9.eh != 0L) continue;
                        if (!(w9 instanceof com.corrodinggames.rts.game.units.UnitInstance)) throw new RuntimeException("GameLoad postload: Found object in list with id:0");
                        GlobalState.e("object: " + ((com.corrodinggames.rts.game.units.UnitInstance) w9).c());
                        throw new RuntimeException("GameLoad postload: Found object in list with id:0");
                    }
                }
                GlobalState.e("--- Save file load complete ---");
                GlobalState.e("GameObject.fastGameObjectList:" + GameObject.er.size());
                GlobalState.e("Unit.fastLiveUnitList:" + UnitInstance.bE.size());
                if (!bl4) {
                    l2.cb.a(bl4);
                }
                if (!this.b) return true;
                br2.b(GamePhase.y);
                br2.a(true, true);
                return true;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            GlobalState.e("Save load error, clearing all units");
            DequeList o6 = GameObject.dK();
            for (GameObject w10 : (java.util.Collection<GameObject>) (java.util.Collection) o6) {
                if (w10.eh == 0L) {
                    w10.eh = l2.bX.getNextUnitId();
                }
                w10.a();
            }
            throw new RuntimeException(exception);
        }
    }

    public boolean b(String string) {
        GlobalState.e("Deleting: " + string);
        String string2 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.o(string);
        if (string2.contains("\\") || string2.contains("/")) {
            GlobalState.e("Cannot get save with path: " + string);
            return false;
        }
        File file = this.a(string, true);
        boolean bl2 = com.corrodinggames.rts.gameFramework.filesystem.FileLoader.b(file);
        File file2 = this.a(string + ".map", true);
        com.corrodinggames.rts.gameFramework.filesystem.FileLoader.b(file2);
        if (!bl2) {
            GlobalState.e("Failed to delete: " + file.getAbsolutePath());
            GlobalState.B().i("Failed to delete: " + file.getAbsolutePath());
        }
        return true;
    }

    public void a(boolean bl2) {
        GlobalState l2 = GlobalState.B();
        if (!bl2) {
            this.c = -9999;
            this.d = -9999;
        }
    }

    public boolean a() {
        GlobalState l2 = GlobalState.B();
        if (!l2.bQ.autosaving) {
            return false;
        }
        if (GlobalState.aU()) {
            return false;
        }
        if (!l2.bG || l2.bH || l2.cb.j()) {
            return false;
        }
        return !l2.M();
    }

    public void b() {
        int n2 = 300000;
        GlobalState l2 = GlobalState.B();
        if (!this.a()) {
            return;
        }
        if (this.d == -9999) {
            this.c = l2.by;
            this.d = l2.by;
        }
        if (this.d + n2 < l2.by) {
            this.d = l2.by;
            long l3 = br.a();
            this.c();
            double d2 = br.a(l3);
            GlobalState.e("Autosaved (" + br.a(d2) + ")");
        }
    }

    public void c() {
        this.b("autosave", true);
    }
}
