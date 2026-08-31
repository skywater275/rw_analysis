/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import android.content.Context;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.bb;
import com.corrodinggames.rts.gameFramework.bc;
import com.corrodinggames.rts.gameFramework.bd;
import com.corrodinggames.rts.gameFramework.e;
import com.corrodinggames.rts.gameFramework.e.a;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.ak;
import com.corrodinggames.rts.gameFramework.j.al;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.w;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public strictfp class ba {
    String a = "replays/";
    public static boolean b = true;
    public static boolean c = true;
    public static boolean d = false;
    public static boolean e = true;
    public static boolean f = false;
    public ak g = new ak();
    public boolean h;
    int i;
    int j;
    boolean k;
    int l;
    boolean m;
    public boolean n = false;
    public int o;
    public int p;
    public int q;
    public String r;
    boolean s;
    private volatile boolean P;
    String t;
    boolean u;
    public int v = 1;
    bd w;
    bd x;
    int y;
    int z;
    int A;
    int B;
    InputStream C;
    BufferedInputStream D;
    DataInputStream E;
    k F;
    OutputStream G;
    BufferedOutputStream H;
    DataOutputStream I;
    as J;
    bb K;
    Thread L;
    Object M = new Object();
    public boolean N = false;
    public boolean O;

    public static void a(String string) {
        com.corrodinggames.rts.gameFramework.l.e("Replay: " + string);
    }

    public static void b(String string) {
        com.corrodinggames.rts.gameFramework.l.b("Replay: " + string);
    }

    public static void a(String string, Exception exception) {
        com.corrodinggames.rts.gameFramework.l.a("Replay: " + string, (Throwable)exception);
    }

    public File a(String string, boolean bl) {
        File file = com.corrodinggames.rts.gameFramework.e.a.a(string, this.a, bl);
        return file;
    }

    public void a(Context context) {
    }

    public void a() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bt = l2.bt != 0.0f ? 0.0f : 1.0f;
    }

    public void b() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bt = l2.bt == 1.0f ? 2.0f : (l2.bt == 2.0f ? 4.0f : (l2.bt == 4.0f ? 8.0f : (l2.bt == 8.0f ? 16.0f : (l2.bt == 16.0f ? 32.0f : (l2.bt == 32.0f ? 64.0f : (l2.bt == 64.0f ? 1.0f : 1.0f))))));
    }

    public void a(int n2, String string, String string2, int n3) {
        bb bb2 = this.K;
        if (this.P && !this.u) {
            if (string2.startsWith("-t ")) {
                // empty if block
            }
            bd bd2 = new bd();
            bd2.a = n3;
            bd2.g = new bc();
            bd2.g.a = n2;
            bd2.g.b = string;
            bd2.g.c = string2;
            if (bb2 == null) {
                com.corrodinggames.rts.gameFramework.l.g("Failed to record chat message, replay might have already stopped");
                return;
            }
            bb2.a(bd2);
        }
    }

    public void a(byte[] byArray, int n2, int n3, int n4, float f2, float f3) {
        bb bb2 = this.K;
        if (this.P && !this.u) {
            bd bd2 = new bd();
            bd2.a = n2;
            bd2.f = byArray;
            bd2.h = n3;
            bd2.i = n4;
            bd2.j = f2;
            bd2.k = f3;
            if (bb2 == null) {
                com.corrodinggames.rts.gameFramework.l.g("Failed to save resync, replay might have already stopped");
                return;
            }
            bb2.a(bd2);
        }
    }

    public void c() {
        if (f) {
            this.d();
        }
    }

    public void a(e e2, int n2) {
        bb bb2 = this.K;
        if (this.P && !this.u) {
            if (bb2 == null) {
                com.corrodinggames.rts.gameFramework.l.g("Failed to record command, replay might have already stopped");
                return;
            }
            Object object = new bd();
            ((bd)object).e = e2.f();
            ((bd)object).a = n2;
            bb2.a((bd)object);
            ++this.j;
            if (this.j > 5) {
                this.j = 0;
                object = com.corrodinggames.rts.gameFramework.l.B();
                bd bd2 = new bd();
                bd2.c = this.f();
                bd2.a = ((l)object).bx;
                bb2.a(bd2);
            }
        }
    }

    public void d() {
        if (this.P && !this.u) {
            this.g.b();
            this.a(this.g, true);
        }
    }

    public void a(ak ak2) {
        this.a(ak2, false);
    }

    public void a(ak ak2, boolean bl) {
        if (this.P && !this.u) {
            l l2 = com.corrodinggames.rts.gameFramework.l.B();
            bd bd2 = new bd();
            as as2 = new as();
            try {
                int n2 = 0;
                if (bl) {
                    ++n2;
                }
                as2.c(n2);
                as2.a(ak2.b.size());
                for (al al2 : ak2.b) {
                    as2.a(al2.b);
                }
            }
            catch (IOException iOException) {
                throw new RuntimeException(iOException);
            }
            bd2.d = as2.d();
            bd2.a = l2.bx;
            this.K.a(bd2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void e() {
        Object object = this.M;
        synchronized (object) {
            try {
                if (this.K != null) {
                    this.K.a();
                    try {
                        this.L.join();
                    }
                    catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    this.P = false;
                    this.K = null;
                    this.L = null;
                }
                if (this.G != null) {
                    this.I.flush();
                    this.I.close();
                    this.H.flush();
                    this.H.close();
                    this.G.flush();
                    this.G.close();
                }
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
            finally {
                this.G = null;
                this.H = null;
                this.I = null;
                this.J = null;
            }
            this.s = false;
            this.P = false;
            this.u = false;
            this.t = null;
            this.i = 0;
            this.j = 0;
            this.k = false;
            this.l = 0;
            this.m = false;
            this.y = 0;
            this.v = 1;
            this.z = 0;
            this.A = 0;
            this.B = 0;
            this.o = -1;
            this.p = 0;
            this.q = -1;
            this.r = null;
            try {
                if (this.C != null) {
                    this.E.close();
                    this.D.close();
                    this.C.close();
                }
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
            finally {
                this.C = null;
                this.D = null;
                this.E = null;
                this.F = null;
            }
        }
    }

    public long f() {
        long l2 = 0L;
        for (w w2 : com.corrodinggames.rts.gameFramework.w.er) {
            if (!(w2 instanceof y)) continue;
            y y2 = (y)w2;
            l2 = (long)((float)l2 + y2.eo * 1000.0f);
            l2 = (long)((float)l2 + y2.ep * 1000.0f);
            l2 = (long)((float)l2 + y2.cu * 1.0f);
            l2 += y2.eh;
        }
        return l2;
    }

    public void g() {
        if (!this.N) {
            this.e();
        }
    }

    public boolean c(String string) {
        File file = this.a(string, false);
        return this.a(string, file);
    }

    private void l() {
        for (int i2 = 0; i2 < com.corrodinggames.rts.game.n.c; ++i2) {
            n n2 = com.corrodinggames.rts.game.n.k(i2);
            if (n2 == null || !(n2 instanceof com.corrodinggames.rts.game.a.a)) continue;
            ((com.corrodinggames.rts.game.a.a)n2).aX = true;
        }
    }

    public boolean a(String string, File file) {
        if (this.P) {
            if (this.u) {
                com.corrodinggames.rts.gameFramework.l.b("startReplayingFile: A replay is already playing");
            } else {
                com.corrodinggames.rts.gameFramework.l.b("startReplayingFile: A replay is already saving");
            }
        }
        this.e();
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.e();
        l2.bX.q();
        this.w = null;
        this.s = false;
        this.P = true;
        this.u = true;
        this.t = string;
        try {
            if (file.isDirectory()) {
                com.corrodinggames.rts.gameFramework.l.e("File is a directory: " + file.getAbsolutePath());
                String string2 = "Cannot load replay: Target is a folder, instead of a file";
                com.corrodinggames.rts.gameFramework.l.e(string2);
                l2.a(string2, 1);
                return false;
            }
            this.C = com.corrodinggames.rts.gameFramework.e.a.a(file);
            if (this.C == null) {
                String string3 = "Cannot load replay: Failed to read replay file";
                com.corrodinggames.rts.gameFramework.l.e(string3);
                l2.a(string3, 1);
                return false;
            }
            this.D = new BufferedInputStream(this.C);
            this.E = new DataInputStream(this.D);
            this.F = new k(this.E);
            String string4 = this.F.l();
            if (!string4.equals("rustedWarfareReplay")) {
                com.corrodinggames.rts.gameFramework.l.e("Header is not correct:" + string4);
                String string5 = "Cannot load replay: File is missing header (check if this file is a replay)";
                com.corrodinggames.rts.gameFramework.l.e(string5);
                l2.a(string5, 1);
                return false;
            }
            int n2 = this.F.f();
            int n3 = this.F.f();
            ba.a("Loading save from version: " + n3);
            this.F.a(n3);
            String string6 = this.F.l();
            if (!(n3 == 96 && n2 == l2.c(true) || this.n)) {
                String string7 = "Cannot load replay: This replay was recording with a different version: " + string6;
                if (com.corrodinggames.rts.gameFramework.l.av()) {
                    string7 = string7 + " (You can use the beta tab in steam to switch to old versions)";
                }
                l2.a(string7, 1);
                ba.a("Replay version: " + n3 + " (" + n2 + ")");
                ba.a("GameSaver.thisSaveVersion: 96 (" + l2.c(true) + ")");
                if (!com.corrodinggames.rts.gameFramework.l.aG) {
                    this.P = false;
                    return false;
                }
            }
            this.q = n3;
            this.r = string6;
            this.F.e();
            this.F.b("gamesave");
            this.O = false;
            this.N = true;
            ba.a("Loading replay initial save");
            l2.ca.a(this.F, false, false, false);
            this.N = false;
            this.F.d("gamesave");
            if (!this.O) {
                ba.a("ReplayEngine: --- No game setup read ----");
                l2.bX.ay.i = true;
                l2.bB = l2.bC = l2.bQ.teamUnitCapHostedGame;
            }
            if (!this.h) {
                this.l();
            }
            ba.a("--- Reply settings ---");
            ba.a("Unit cap: " + l2.bC);
            ba.a(l2.bX.ay.b());
            ba.a("Starting frame:" + l2.bx);
            if (!this.h) {
                for (int i2 = 0; i2 < com.corrodinggames.rts.game.n.c; ++i2) {
                    n n4 = com.corrodinggames.rts.game.n.k(i2);
                    if (n4 == null || n4.v == null) continue;
                    String string8 = "Player '" + n4.v + "' playing as " + n4.N().toLowerCase() + " (team:" + n4.h() + ")";
                    l2.bS.h.a("", string8);
                }
            }
            if (com.corrodinggames.rts.gameFramework.l.aw) {
                ad.g("Warning: editor will desync checksums.");
                l2.bv = true;
                l2.bl = true;
                l2.bn = true;
            }
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        return true;
    }

    public void a(boolean bl) {
        if (com.corrodinggames.rts.gameFramework.l.aW ? !com.corrodinggames.rts.gameFramework.l.bd : !com.corrodinggames.rts.gameFramework.l.bc) {
            return;
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.bX.B && !bl && !this.N && l2.bQ.saveMultiplayerReplays) {
            String string = l2.al() + " [v" + l2.v() + "] (" + com.corrodinggames.rts.gameFramework.f.a("d MMM yyyy HH.mm.ss") + ").replay";
            this.d(string);
        }
    }

    public void d(String string) {
        ba.a("Recording replay to: " + string);
        if (this.P) {
            if (this.u) {
                ba.b("startSaving: A replay is already playing");
            } else {
                ba.b("startSaving: A replay is already saving");
            }
        }
        this.e();
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        f = l2.bQ.replayTracing;
        if (f) {
            l2.bX.j("Warning traceChecksumsWriting is on. Large replay file size will be created.");
        }
        this.s = false;
        this.P = true;
        this.u = false;
        this.t = string;
        try {
            File file = this.a(string, true);
            this.G = com.corrodinggames.rts.gameFramework.e.a.a(file, false);
            if (this.G == null) {
                ba.b("Failed to create replay file at:" + file.getAbsolutePath());
                com.corrodinggames.rts.gameFramework.l.B().i("Failed to create replay file (Replay recording will be disabled)");
                this.e();
                return;
            }
            this.H = new BufferedOutputStream(this.G);
            this.I = new DataOutputStream(this.H);
            this.J = new as(this.I);
            this.J.c("rustedWarfareReplay");
            int n2 = l2.c(true);
            this.J.a(n2);
            this.J.a(96);
            this.J.c(l2.v());
            this.J.a(l2.ar);
            this.J.e("gamesave");
            l2.ca.a(this.J);
            this.J.a("gamesave");
            this.I.flush();
            this.K = new bb(this);
            this.L = new Thread(this.K);
            this.L.start();
        }
        catch (IOException iOException) {
            ba.a("Failed to start recording replay", iOException);
            com.corrodinggames.rts.gameFramework.l.B().i("Failed to start recording replay: " + iOException.getMessage());
            this.e();
        }
        catch (Exception exception) {
            ba.a("Failed to start recording replay (Non IOException)", exception);
            com.corrodinggames.rts.gameFramework.l.B().i("Failed to start recording replay (Non IOException): " + exception.getMessage());
            this.e();
        }
    }

    public boolean h() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        String string = this.F.x();
        if ("rc".equals(string)) {
            ++this.y;
            bd bd2 = new bd();
            bd2.a = this.F.f();
            e e2 = l2.cf.b();
            e2.a(this.F);
            e2.a = true;
            bd2.e = e2;
            this.F.d("rc");
            this.w = bd2;
            ++this.p;
            this.o = bd2.a;
            if (c) {
                ba.a("updateGameFrame: Command: " + e2.i.v + " (" + e2.i.k + ") count:" + e2.d() + " id:" + this.y);
                if (e2.j != null) {
                    ba.a("updateGameFrame: Waypoint: " + e2.j.d().name());
                    if (e2.j.a() != null) {
                        ba.a("updateGameFrame: Build Type: " + e2.j.a().i());
                    }
                }
                if (com.corrodinggames.rts.game.units.a.s.c(e2.k)) {
                    ba.a("updateGameFrame: SpecialAction: " + e2.k.a());
                }
                if (e2.n != null) {
                    ba.a("updateGameFrame: SetAttackMode: " + (Object)((Object)e2.n));
                }
                if (e2.g) {
                    ba.a("updateGameFrame: stopOrUndo is set");
                }
                if (e2.r) {
                    if (e2.s != 0.0f) {
                        ba.a("updateGameFrame: changeStepRate:" + e2.s);
                    }
                    if (e2.u != 0) {
                        ba.a("updateGameFrame: systemAction_action:" + e2.u);
                    }
                }
                ba.a("updateGameFrame: ------");
            }
        } else if ("wait".equals(string)) {
            bd bd3 = new bd();
            bd3.a = this.F.f();
            bd3.b = true;
            this.w = bd3;
            this.F.d("wait");
        } else if ("cs".equals(string)) {
            int n2 = this.F.f();
            long l3 = this.F.i();
            if (!this.n) {
                if (l2.bx != n2) {
                    com.corrodinggames.rts.gameFramework.l.b("replay:updateGameFrame", "expected:" + n2 + " got:" + l2.bx);
                }
                if (this.f() != l3) {
                    ba.b("checksum: checksums don't match!!");
                    ba.b("checksum: game frameNumber:" + l2.bx);
                    ba.b("checksum: Replay checksum:" + l3);
                    ba.b("checksum: Game checksum  :" + this.f());
                    ++this.l;
                    if (!this.k) {
                        this.k = true;
                        l2.bS.h.a("", "Error: This replay might be out of sync");
                    }
                } else {
                    ba.a("checksum: checksums are matching frameNumber:" + l2.bx);
                }
            }
            this.F.d("cs");
        } else if ("es".equals(string)) {
            int n3 = this.F.f();
            if (!this.n) {
                if (l2.bx != n3) {
                    com.corrodinggames.rts.gameFramework.l.b("replay.updateGameFrame: expected:" + n3 + " got:" + l2.bx);
                }
                k k2 = new k(this.F.t());
                byte by = k2.d();
                boolean bl = false;
                if (com.corrodinggames.rts.game.units.custom.d.b.a((int)by, 1)) {
                    bl = true;
                }
                if (bl) {
                    com.corrodinggames.rts.gameFramework.l.e("replay: -trace checksum-");
                } else {
                    com.corrodinggames.rts.gameFramework.l.e("replay: -long checksum-");
                }
                l2.bX.d();
                int n4 = k2.f();
                for (al al2 : l2.bX.am.b) {
                    long l4 = k2.i();
                    if (!this.m && l4 == al2.b) {
                        ba.a("extraChecksum: " + al2.a + " Checksum [" + n3 + "]. " + l4 + " == " + al2.b + " (ok)");
                    }
                    if (l4 == al2.b) continue;
                    if (this.l < 150) {
                        ba.b("extraChecksum: " + al2.a + " Checksum [" + n3 + "]. " + l4 + " != " + al2.b + " (failed)");
                    }
                    ++this.l;
                }
            }
            this.m = true;
            this.F.d("es");
        } else if ("resync".equals(string)) {
            int n5 = this.F.f();
            com.corrodinggames.rts.gameFramework.l.e("Loading resync from replay");
            if (l2.bx != n5) {
                com.corrodinggames.rts.gameFramework.l.b("replay:resync", "expected:" + n5 + " got:" + l2.bx);
            }
            int n6 = this.F.f();
            int n7 = this.F.f();
            float f2 = this.F.g();
            float f3 = this.F.g();
            k k3 = new k(this.F.t());
            l2.ca.a(k3, true, true, true);
            this.l();
            l2.bx = n6;
            l2.by = n7;
            l2.bX.am.a = 0L;
            if ((double)f2 < 0.1) {
                ad.a("replay setCurrentStepRate:" + f2 + " is too small", true);
            }
            l2.bX.a(f2, "replay");
            l2.bX.J = f3;
            this.F.d("resync");
        } else if ("chat".equals(string)) {
            bd bd4 = new bd();
            bd4.a = this.F.f();
            bd4.g = new bc();
            bd4.g.a = this.F.f();
            bd4.g.b = this.F.j();
            bd4.g.c = this.F.j();
            this.w = bd4;
            this.F.d("chat");
        } else {
            if ("end".equals(string)) {
                com.corrodinggames.rts.gameFramework.l.b("replay:updateGameFrame", "end of replay block found");
                l2.bS.h.a("", "Replay has ended");
                if (!l2.bv) {
                    this.s = true;
                    l2.bt = 0.25f;
                    com.corrodinggames.rts.gameFramework.l.B().bS.G();
                } else {
                    this.s = false;
                    this.P = false;
                    this.u = false;
                    h h2 = l2.bS.i();
                    if (h2 != null) {
                        l2.bs = h2.bX;
                    }
                }
                this.F.d("end");
                com.corrodinggames.rts.gameFramework.l.e("number of replay commands issued:" + this.z);
                return false;
            }
            if ("endReplayMetaData".equals(string)) {
                this.F.d("endReplayMetaData");
            } else {
                com.corrodinggames.rts.gameFramework.l.b("updateGameFrame", "Unknown command block:" + string);
                this.F.d(string);
            }
        }
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void a(float f2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.s) {
            return;
        }
        if (!this.P || !this.u) return;
        while (true) {
            if (this.w == null) {
                try {
                    boolean bl = this.h();
                    if (!bl) {
                        return;
                    }
                }
                catch (IOException iOException) {
                    com.corrodinggames.rts.gameFramework.l.b("updateGameFrame", "IOException, read of replay?");
                    iOException.printStackTrace();
                    l2.bt = 0.25f;
                    if (!this.s && this.P) {
                        l2.bS.h.a("", "Replay ended (unexpected)");
                    }
                    this.s = true;
                    return;
                }
            }
            if (this.w != null) {
                if (this.n) {
                    this.w = null;
                    continue;
                }
                if (b && this.w != null && this.x != this.w) {
                    this.x = this.w;
                    com.corrodinggames.rts.gameFramework.l.e("replay: upcoming in " + (this.w.a - l2.bx) + " command:" + (this.w.e != null));
                }
                if (this.w.b && this.z == 0) {
                    com.corrodinggames.rts.gameFramework.l.e("updateGameFrame: replay: Skipping wait on first resync without commands to avoid delay");
                    this.w = null;
                    continue;
                }
                if (l2.bx >= this.w.a) {
                    if (this.w.e != null) {
                        if (l2.bx > this.w.a) {
                            com.corrodinggames.rts.gameFramework.l.b("updateGameFrame: replay incorrect frameNumber, skipping command:" + l2.bx + " vs " + this.w.a);
                        } else {
                            if (d) {
                                n n2 = this.w.e.p;
                                if (n2 == null) {
                                    com.corrodinggames.rts.gameFramework.l.e("Precommand Team: commandingPlayer==null");
                                    if (this.w.e.i != null) {
                                        com.corrodinggames.rts.gameFramework.l.e("Precommand Team id:" + this.w.e.i.k + " credits:" + this.w.e.i.o);
                                    }
                                } else {
                                    com.corrodinggames.rts.gameFramework.l.e("Precommand Team id:" + this.w.e.p.k + " credits:" + this.w.e.p.o + " count:" + this.w.e.p.w() + " max:" + this.w.e.p.x());
                                }
                            }
                            if (this.w.e.r && this.w.e.u != 0) {
                                com.corrodinggames.rts.gameFramework.l.b("replay:issueCommand", "systemAction_action:" + this.w.e.u);
                            }
                            this.w.e.k();
                            if (d) {
                                n n3 = this.w.e.p;
                                if (n3 != null) {
                                    com.corrodinggames.rts.gameFramework.l.e("Postcommand credits:" + this.w.e.p.o + " count:" + this.w.e.p.w() + " max:" + this.w.e.p.x());
                                } else if (this.w.e.i != null) {
                                    com.corrodinggames.rts.gameFramework.l.e("Postcommand Team id:" + this.w.e.i.k + " credits:" + this.w.e.i.o);
                                }
                            }
                            ++this.z;
                        }
                    } else if (this.w.g != null) {
                        bc bc2 = this.w.g;
                        boolean bl = false;
                        if (bc2.c == null) {
                            bl = true;
                        } else {
                            if (bc2.c.startsWith("-i ")) {
                                bl = true;
                            }
                            if (bc2.c.equals("<All players ready>")) {
                                bl = true;
                            }
                            if (bc2.c.equals("--too many desync errors, suppressing output--")) {
                                bl = true;
                            }
                            if (bc2.c.startsWith("desync:")) {
                                bl = true;
                            }
                        }
                        if (!l2.bQ.replaysShowRecordedChat) {
                            bl = true;
                        }
                        if (bl) {
                            com.corrodinggames.rts.gameFramework.l.b("replay:updateGameFrame", "Skipping message: " + bc2.b + ":" + bc2.c);
                        } else {
                            com.corrodinggames.rts.gameFramework.l.b("replay:updateGameFrame", "message: " + bc2.b + ":" + bc2.c);
                            l2.bS.h.a(bc2.b, bc2.c);
                        }
                    } else if (this.w.b) {
                        if (c) {
                            // empty if block
                        }
                    } else {
                        com.corrodinggames.rts.gameFramework.l.b("updateGameFrame", "error: lastReadCommand null action");
                    }
                    this.w = null;
                    continue;
                }
            }
            if (this.w != null) return;
        }
    }

    public void e(String string) {
        File file;
        boolean bl;
        com.corrodinggames.rts.gameFramework.l.e("ReplayEngine deleteGame: " + string);
        String string2 = com.corrodinggames.rts.gameFramework.e.a.o(string);
        if (string2.contains("\\") || string2.contains("/")) {
            com.corrodinggames.rts.gameFramework.l.e("Cannot get replay with path: " + string);
            return;
        }
        File file2 = this.a(string, true);
        com.corrodinggames.rts.gameFramework.l.e("ReplayEngine path: " + file2.getAbsolutePath());
        if (!file2.exists()) {
            com.corrodinggames.rts.gameFramework.l.e("ReplayEngine deleteGame: file doesn't exist");
        }
        if (!(bl = com.corrodinggames.rts.gameFramework.e.a.b(file2))) {
            com.corrodinggames.rts.gameFramework.l.e("ReplayEngine deleteGame: failed to delete: " + file2.getAbsolutePath());
        }
        if ((file = this.a(string + ".map", true)).exists()) {
            com.corrodinggames.rts.gameFramework.e.a.b(file);
        }
    }

    public boolean i() {
        return this.P;
    }

    public boolean j() {
        return this.P && this.u;
    }

    public boolean k() {
        return this.P && !this.u;
    }

    static /* synthetic */ boolean a(ba ba2) {
        return ba2.P;
    }

    static /* synthetic */ boolean a(ba ba2, boolean bl) {
        ba2.P = bl;
        return ba2.P;
    }
}
