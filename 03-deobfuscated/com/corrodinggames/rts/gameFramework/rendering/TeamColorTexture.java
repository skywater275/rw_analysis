/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.rendering;
import com.corrodinggames.rts.gameFramework.KeyCodeMapper;

import android.graphics.Bitmap;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.GameMode;
import com.corrodinggames.rts.gameFramework.ExtraManager;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Shader;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.rendering.FileShader;
import com.corrodinggames.rts.gameFramework.ResourceDomainEnum;
import java.io.IOException;

public class TeamColorTexture
extends Texture {
    public static Shader x;
    public static Shader y;
    public static Shader z;
    public static boolean A;
    boolean B = false;
    boolean C = false;
    private Texture H;
    private Texture I;
    int D;
    int E;
    GameMode F;
    public static float G;

    public static synchronized void C() {
        if (A) {
            return;
        }
        try {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Loading team shaders...");
            x = new FileShader("assets/shaders/pureGreenTeamColor.frag", true);
            x.a("teamColor", -1);
            x.c();
            y = new FileShader("assets/shaders/hueAddTeamColor.frag", false);
            y.a("teamColorAmount", 0.15f);
            y.a("teamColor", -1);
            y.c();
            z = new FileShader("assets/shaders/hueShiftTeamColor.frag", false);
            z.a("teamColor", -1);
            z.c();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        A = true;
    }

    public void D() {
        if (!A) {
            C();
        }
    }


    public String a() {
        if (this.H == null) {
            return "LazyColoring (error sourceBitmap==null)";
        }
        return "LazyColoring(" + this.E + "):" + this.H.a();
    }

    public TeamColorTexture(Texture e2, int n2, GameMode o2, int n3) {
        if (e2 == null) {
            throw new RuntimeException("baseImage==null");
        }
        this.H = e2;
        this.D = n2;
        this.F = o2;
        this.E = n3;
        this.H.setRenderTarget(this);
        this.k = null;
    }

    public void c(boolean bl) {
        if (com.corrodinggames.rts.gameFramework.GlobalState.isTeamShadersSupported()) {
            if (bl) {
                // empty if block
            }
            this.D();
            if (this.F == com.corrodinggames.rts.game.GameMode.b) {
                this.setRenderTarget(y);
            } else if (this.F == com.corrodinggames.rts.game.GameMode.d) {
                this.setRenderTarget(z);
            } else {
                this.setRenderTarget(x);
            }
            this.I = this.H;
            this.C = true;
            return;
        }
        if (this.H.A()) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Lazy loaded bitmap using errored image: " + this.H.a());
            this.I = this.H;
            return;
        }
        try {
            if (bl) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("Loading in lazy loaded bitmap:" + this.H.a() + " team:" + this.E);
            }
            long l2 = ExtraManager.a();
            this.H.i();
            this.I = this.H.h();
            this.I.j();
            Texture[] eArray = new Texture[]{this.I};
            int[] nArray = new int[]{this.D};
            int[] nArray2 = new int[]{this.E};
            long l3 = ExtraManager.a();
            if (this.F == com.corrodinggames.rts.game.GameMode.b) {
                com.corrodinggames.rts.game.PlayerState.b(this.H, eArray, nArray);
            } else if (this.F == com.corrodinggames.rts.game.GameMode.d) {
                com.corrodinggames.rts.game.PlayerState.a(this.H, eArray, nArray, nArray2);
            } else {
                com.corrodinggames.rts.game.PlayerState.a(this.H, eArray, nArray);
            }
            double d2 = ExtraManager.a(l3);
            this.I.p();
            this.I.s();
            this.H.q();
            this.H = null;
            double d3 = ExtraManager.a(l2);
            if (d3 > 1.0) {
                com.corrodinggames.rts.gameFramework.GlobalState.e((this.F == com.corrodinggames.rts.game.GameMode.a ? "Standard " : "Hue ") + "Colouring took:" + ExtraManager.a(d3) + " (" + ExtraManager.a(d2) + ")");
            }
            G = (float)((double)G + d3);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Colouring failed with OOM");
            com.corrodinggames.rts.gameFramework.GlobalState.a(com.corrodinggames.rts.gameFramework.ResourceDomainEnum.c, (Throwable)outOfMemoryError);
            this.I = com.corrodinggames.rts.gameFramework.GlobalState.B().bO.r();
        }
    }


    public Bitmap b() {
        if (this.C && !com.corrodinggames.rts.gameFramework.GlobalState.isTeamShadersSupported()) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Team shader coloring now disabled. Recoloring image: " + this.H.a());
            this.B = false;
            this.C = false;
            this.setRenderTarget((Shader) null);
        }
        if (!this.B) {
            this.c(true);
            this.B = true;
        }
        return this.I.k;
    }

    @Override
    public Texture c() {
        if (this.C && !com.corrodinggames.rts.gameFramework.GlobalState.isTeamShadersSupported()) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Team shader coloring now disabled. Recoloring image: " + this.H.a());
            this.B = false;
            this.C = false;
            this.setRenderTarget((Shader) null);
        }
        if (!this.B) {
            if (G > 60.0f) {
                // empty if block
            }
            this.c(true);
            this.B = true;
        }
        if (this.I == null) {
            throw new RuntimeException("coloredBitmap==null");
        }
        return this.I;
    }

    @Override
    public void w() {
        if (!this.B) {
            this.c(false);
            this.B = true;
        }
    }

    @Override
    public int u() {
        if (!this.B & this.H != null) {
            return this.H.u();
        }
        return super.u();
    }
}
