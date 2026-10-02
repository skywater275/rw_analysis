package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.x;

public abstract class w extends bq {

   public long eh;
   public static x ei = new x();
   public boolean ej;
   public boolean ek;
   public boolean el;
   public int em;
   public int en;
   public float eo;
   public float ep;
   public float eq;
   private static final com.corrodinggames.rts.gameFramework.utility.o a = new com.corrodinggames.rts.gameFramework.utility.o();
   public static final com.corrodinggames.rts.gameFramework.utility.s er = new com.corrodinggames.rts.gameFramework.utility.s("fastGameObjectList");


   public strictfp void S(int var1) {
      this.em = var1;
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.as var1) {
      var1.a(this.ej);
      var1.a(this.ek);
      var1.a(this.em);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.ej = var1.e();
      this.ek = var1.e();
      this.em = var1.f();
   }

   public strictfp w() {
      this(false);
   }

   public strictfp w(boolean var1) {
      this.ej = false;
      this.ek = false;
      this.em = 2;
      this.en = 0;
      this.eq = 0.0F;
      if(!var1) {
         l var2 = l.B();
         if(this.eh != 0L) {
            throw new RuntimeException("ID for GameObject is already set at:" + this.eh);
         }

         this.eh = var2.bX.y();
         if(this.eh == 0L) {
            throw new RuntimeException("Adding object with id:0 class:" + this.getClass().getSimpleName());
         }

         a.a((Object)this);
         er.a(this);
      } else {
         this.eh = 0L;
      }

   }

   public abstract void a(float var1);

   public abstract void a(float var1, boolean var2);

   public abstract void d(float var1);

   public abstract void e(float var1);

   public strictfp void p(float var1) {}

   public abstract boolean c(float var1);

   public abstract boolean f(float var1);

   public strictfp boolean a(l var1) {
      return true;
   }

   public strictfp void a() {
      if(this.eh != 0L) {
         a.b((Object)this);
         er.remove(this);
      }

      this.ej = true;
   }

   public static strictfp w a(long var0, Class var2, boolean var3) {
      if(var0 == -1L) {
         return null;
      } else {
         w[] var4 = er.a();
         int var5 = 0;

         for(int var6 = er.size(); var5 < var6; ++var5) {
            w var7 = var4[var5];
            if(var7.eh == var0) {
               if(var2.isInstance(var7)) {
                  return var7;
               }

               String var8 = var7.getClass().getName();
               String var9 = var2.getName();
               var8 = var8.replace("com.corrodinggames.rts.", "");
               var9 = var9.replace("com.corrodinggames.rts.", "");
               com.corrodinggames.rts.gameFramework.j.ad.g("object id:" + var0 + " was found, but with type " + var8 + " instead of " + var9);
            }
         }

         if(!var3) {
            com.corrodinggames.rts.gameFramework.j.ad.g("getFromId:" + var0 + " was not found");
         }

         return null;
      }
   }

   public static strictfp com.corrodinggames.rts.game.units.am a(long var0, boolean var2) {
      return (com.corrodinggames.rts.game.units.am)a(var0, com.corrodinggames.rts.game.units.am.class, var2);
   }

   public static strictfp com.corrodinggames.rts.game.units.y b(long var0, boolean var2) {
      return (com.corrodinggames.rts.game.units.y)a(var0, com.corrodinggames.rts.game.units.y.class, var2);
   }

   public static strictfp com.corrodinggames.rts.gameFramework.utility.o dK() {
      a.a();
      return a;
   }

   public static strictfp void dL() {
      a.a();
      com.corrodinggames.rts.game.units.am.bG();
   }

}
