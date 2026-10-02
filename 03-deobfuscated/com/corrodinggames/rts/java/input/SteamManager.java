/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.input;
import com.corrodinggames.rts.gameFramework.network.PacketDecoder;
import com.corrodinggames.rts.java.GameWindow;

import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamException;
import com.codedisaster.steamworks.SteamFriends;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamMatchmaking;
import com.codedisaster.steamworks.SteamMatchmaking$LobbyType;
import com.codedisaster.steamworks.SteamNetworking;
import com.codedisaster.steamworks.SteamNetworking$API;
import com.codedisaster.steamworks.SteamUGC;
import com.codedisaster.steamworks.SteamUtils;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.steamworks.SteamEngine;
import com.corrodinggames.rts.java.input.c;
import com.corrodinggames.rts.java.input.d;
import com.corrodinggames.rts.java.input.e;
import com.corrodinggames.rts.java.input.f;
import com.corrodinggames.rts.java.input.SteamWorkshop;
import com.corrodinggames.rts.java.input.k;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;

public class SteamManager
extends SteamEngine {
    d b;
    SteamFriends c;
    SteamMatchmaking d;
    e e;
    f f;
    SteamWorkshop g;
    SteamNetworking h;
    c i;
    SteamUtils j;
    boolean k = false;
    HashMap l = new HashMap();
    ByteBuffer m;
    SteamID n;
    boolean o;
    SteamID p;

    public SteamWorkshop n() {
        return this.g;
    }

    @Override
    public void b() {
        if (this.k) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("SteamEngine - init already called");
            return;
        }
        this.k = true;
        com.corrodinggames.rts.gameFramework.GlobalState.e("SteamEngine - java steamEngine init()");
        try {
            SteamUGC steamUGC;
            if (!SteamAPI.init()) {
                com.corrodinggames.rts.gameFramework.GlobalState.b("steamAPI init failed");
                this.d();
                return;
            }
            this.m = ByteBuffer.allocateDirect(100000);
            this.b = new d(this);
            this.c = new SteamFriends(this.b);
            this.e = new e(this);
            this.d = new SteamMatchmaking(this.e);
            this.f = new f(this);
            this.h = new SteamNetworking(this.f, SteamNetworking$API.Client);
            this.g = new SteamWorkshop(this);
            try {
                steamUGC = new SteamUGC(this.g.a());
            }
            catch (RuntimeException runtimeException) {
                runtimeException.printStackTrace();
                throw new SteamException("Failed to create workshop");
            }
            this.g.a(steamUGC);
            this.i = new c(this);
            this.j = new SteamUtils(this.i);
        }
        catch (SteamException steamException) {
            steamException.printStackTrace();
            this.d();
        }
    }

    @Override
    public void a(float f2) {
        SteamAPI.runCallbacks();
        if (this.h != null) {
            int n2;
            if (com.corrodinggames.rts.gameFramework.GlobalState.aK != null) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("Joining game from commandline invite:" + com.corrodinggames.rts.gameFramework.GlobalState.aK);
                long l2 = Long.parseLong(com.corrodinggames.rts.gameFramework.GlobalState.aK);
                com.corrodinggames.rts.gameFramework.GlobalState.aK = null;
                SteamID steamID = SteamID.createFromNativeHandle(l2);
                this.d.joinLobby(steamID);
            }
            while ((n2 = this.h.isP2PPacketAvailable(0)) != 0) {
                if (n2 > this.m.capacity()) {
                    com.corrodinggames.rts.gameFramework.GlobalState.b("nextPacketSize:" + n2 + " larger then byteBuffer:" + this.m.capacity() + " resizing");
                    this.m = ByteBuffer.allocateDirect(n2);
                }
                SteamID steamID = new SteamID();
                try {
                    k k2;
                    this.m.clear();
                    int n3 = this.h.readP2PPacket(steamID, this.m, 0);
                    if (n3 == 0) {
                        com.corrodinggames.rts.gameFramework.GlobalState.b("readP2PPacket with rtn==" + n3);
                    }
                    if ((k2 = (k)this.l.get(steamID)) != null && k2.isClosed()) {
                        com.corrodinggames.rts.gameFramework.GlobalState.b("Removing stale steam socket");
                        this.l.remove(steamID);
                        k2 = null;
                    }
                    if (k2 == null) {
                        this.b(steamID);
                        k2 = (k)this.l.get(steamID);
                    }
                    if (k2 == null) {
                        com.corrodinggames.rts.gameFramework.GlobalState.e("Could not find remote ID steamSocket: " + steamID);
                        continue;
                    }
                    int n4 = this.m.limit();
                    byte[] byArray = new byte[n4];
                    this.m.get(byArray);
                    k2.c.a(byArray);
                }
                catch (SteamException steamException) {
                    steamException.printStackTrace();
                }
            }
        }
    }

    @Override
    public void d() {
        com.corrodinggames.rts.gameFramework.GlobalState.b("JavaSteamEngine: disableSteam");
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2 != null) {
            l2.i("Steam connection failed.");
        } else {
            com.corrodinggames.rts.gameFramework.GlobalState.e("cannot show alert game has not been created");
        }
        com.corrodinggames.rts.gameFramework.steamworks.SteamEngine.a = new SteamEngine();
    }

    @Override
    public String c() {
        return this.c.getPersonaName();
    }

    @Override
    public boolean f() {
        return false;
    }

    public void a(String string) {
        com.corrodinggames.rts.gameFramework.GlobalState.e("Steam: " + string);
    }

    public void b(String string) {
        com.corrodinggames.rts.gameFramework.GlobalState.b("Steam: " + string);
    }

    @Override
    public void i() {
        this.a("createLobby");
        if (this.n != null) {
            this.b("createLobby: activeLobby!=null");
        }
        this.d.createLobby(SteamMatchmaking$LobbyType.FriendsOnly, 10);
    }

    public synchronized void a(SteamID steamID) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.n = steamID;
    }

    public com.corrodinggames.rts.gameFramework.network.PacketDecoder b(SteamID steamID) {
        com.corrodinggames.rts.gameFramework.GlobalState.e("addPeer: " + steamID);
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        k k2 = (k)this.l.get(steamID);
        if (k2 != null) {
            if (k2.isClosed()) {
                this.l.remove(steamID);
            } else {
                this.b("addPeer, user already exists");
                try {
                    k2.close();
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
        }
        k k3 = new k(this, steamID);
        com.corrodinggames.rts.gameFramework.network.PacketDecoder c2 = new com.corrodinggames.rts.gameFramework.network.PacketDecoder(l2.bX, k3);
        try {
            c2.i = true;
            c2.d();
            l2.bX.aM.add(c2);
            this.l.put(steamID, k3);
            l2.bX.sendSync();
            return c2;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            c2.a("crash");
            return null;
        }
    }

    public void c(SteamID steamID) {
        com.corrodinggames.rts.gameFramework.GlobalState.e("connectTo: " + steamID);
        k k2 = (k)this.l.get(steamID);
        if (k2 != null) {
            if (k2.isClosed()) {
                this.l.remove(steamID);
            } else {
                this.b("connectTo, user already exists");
                try {
                    k2.close();
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (!this.o) {
            SteamID steamID2 = steamID;
            SteamManager$1 b$1 = new SteamManager$1(this, steamID2);
            ScriptEngine.getInstance().addRunnableToQueue(b$1);
        } else {
            this.a("connectTo as server?");
            this.b(steamID);
        }
    }

    @Override
    public void j() {
        this.a("stopLobby");
        if (this.n == null) {
            this.b("stopLobby: activeLobby==null");
        } else {
            this.d.leaveLobby(this.n);
        }
        this.a("stopLobby: activeSteamSockets:" + this.l.size());
        for (k k2 : (java.util.Collection<k>) (java.util.Collection) this.l.values()) {
            try {
                k2.close();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
        this.l.clear();
        this.n = null;
        this.p = null;
    }

    @Override
    public void g() {
        if (this.n == null) {
            // empty if block
        }
        if (this.n == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.B().i("Error: No steam lobby has been started");
            return;
        }
        this.c.activateGameOverlayInviteDialog(this.n);
    }

    @Override
    public void k() {
        this.g.c();
    }

    @Override
    public void l() {
        this.g.d();
    }

    @Override
    public void m() {
        this.n().b();
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.mods.ModInfo b2) {
        this.n().c(b2);
    }

    @Override
    public void b(com.corrodinggames.rts.gameFramework.mods.ModInfo b2) {
        this.n().b(b2);
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.mods.ModInfo b2, boolean bl, String string) {
        this.n().a(b2, bl, string);
    }
}
