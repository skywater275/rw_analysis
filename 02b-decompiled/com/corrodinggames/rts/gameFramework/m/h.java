package com.corrodinggames.rts.gameFramework.m;

import android.graphics.Bitmap;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.m.ae;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.m.i;
import java.io.IOException;

public class h extends e {

   public static ae x;
   public static ae y;
   public static ae z;
   public static boolean A;
   boolean B = false;
   boolean C = false;
   private e H;
   private e I;
   int D;
   int E;
   com.corrodinggames.rts.game.o F;
   public static float G;


   public static synchronized void C() {
      if(!A) {
         try {
            com.corrodinggames.rts.gameFramework.l.e("Loading team shaders...");
            x = new i("assets/shaders/pureGreenTeamColor.frag", true);
            x.a("teamColor", -1);
            x.c();
            y = new i("assets/shaders/hueAddTeamColor.frag", false);
            y.a("teamColorAmount", 0.15F);
            y.a("teamColor", -1);
            y.c();
            z = new i("assets/shaders/hueShiftTeamColor.frag", false);
            z.a("teamColor", -1);
            z.c();
         } catch (IOException var1) {
            throw new RuntimeException(var1);
         }

         A = true;
      }
   }

   public void D() {
      if(!A) {
         C();
      }

   }

   public String a() {
      return this.H == null?"LazyColoring (error sourceBitmap==null)":"LazyColoring(" + this.E + "):" + this.H.a();
   }

   public h(e var1, int var2, com.corrodinggames.rts.game.o var3, int var4) {
      if(var1 == null) {
         throw new RuntimeException("baseImage==null");
      } else {
         this.H = var1;
         this.D = var2;
         this.F = var3;
         this.E = var4;
         this.H.a((e)this);
         this.k = null;
      }
   }

   public void c(boolean var1) {
      if(com.corrodinggames.rts.gameFramework.l.az()) {
         if(var1) {
            ;
         }

         this.D();
         if(this.F == com.corrodinggames.rts.game.o.b) {
            this.a(y);
         } else if(this.F == com.corrodinggames.rts.game.o.d) {
            this.a(z);
         } else {
            this.a(x);
         }

         this.I = this.H;
         this.C = true;
      } else if(this.H.A()) {
         com.corrodinggames.rts.gameFramework.l.e("Lazy loaded bitmap using errored image: " + this.H.a());
         this.I = this.H;
      } else {
         try {
            if(var1) {
               com.corrodinggames.rts.gameFramework.l.e("Loading in lazy loaded bitmap:" + this.H.a() + " team:" + this.E);
            }

            long var2 = br.a();
            this.H.i();
            this.I = this.H.h();
            this.I.j();
            e[] var4 = new e[]{this.I};
            int[] var5 = new int[]{this.D};
            int[] var6 = new int[]{this.E};
            long var7 = br.a();
            if(this.F == com.corrodinggames.rts.game.o.b) {
               com.corrodinggames.rts.game.n.b(this.H, var4, var5);
            } else if(this.F == com.corrodinggames.rts.game.o.d) {
               com.corrodinggames.rts.game.n.a(this.H, var4, var5, var6);
            } else {
               com.corrodinggames.rts.game.n.a(this.H, var4, var5);
            }

            double var9 = (double)br.a(var7);
            this.I.p();
            this.I.s();
            this.H.q();
            this.H = null;
            double var11 = (double)br.a(var2);
            if(var11 > 1.0D) {
               com.corrodinggames.rts.gameFramework.l.e((this.F == com.corrodinggames.rts.game.o.a?"Standard ":"Hue ") + "Colouring took:" + br.a(var11) + " (" + br.a(var9) + ")");
            }

            G = (float)((double)G + var11);
         } catch (OutOfMemoryError var13) {
            com.corrodinggames.rts.gameFramework.l.e("Colouring failed with OOM");
            com.corrodinggames.rts.gameFramework.l.a(com.corrodinggames.rts.gameFramework.u.c, (Throwable)var13);
            this.I = com.corrodinggames.rts.gameFramework.l.B().bO.r();
         }

      }
   }

   public Bitmap b() {
      if(this.C && !com.corrodinggames.rts.gameFramework.l.az()) {
         com.corrodinggames.rts.gameFramework.l.e("Team shader coloring now disabled. Recoloring image: " + this.H.a());
         this.B = false;
         this.C = false;
         this.a((ae)null);
      }

      if(!this.B) {
         this.c(true);
         this.B = true;
      }

      return this.I.k;
   }

   public e c() {
      if(this.C && !com.corrodinggames.rts.gameFramework.l.az()) {
         com.corrodinggames.rts.gameFramework.l.e("Team shader coloring now disabled. Recoloring image: " + this.H.a());
         this.B = false;
         this.C = false;
         this.a((ae)null);
      }

      if(!this.B) {
         if(G > 60.0F) {
            ;
         }

         this.c(true);
         this.B = true;
      }

      if(this.I == null) {
         throw new RuntimeException("coloredBitmap==null");
      } else {
         return this.I;
      }
   }

   public void w() {
      if(!this.B) {
         this.c(false);
         this.B = true;
      }

   }

   public int u() {
      return !this.B & this.H != null?this.H.u():super.u();
   }
}
