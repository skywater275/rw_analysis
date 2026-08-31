package com.corrodinggames.rts.game;

import com.corrodinggames.rts.game.p;
import com.corrodinggames.rts.game.t;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import java.util.Iterator;

public final class s {

   public int a = 5;
   public int b;
   public int c;
   public int d;
   public int e;
   public int f;
   public int g;
   public com.corrodinggames.rts.game.units.custom.e.f h = new com.corrodinggames.rts.game.units.custom.e.f();
   public com.corrodinggames.rts.game.units.custom.e.f i = new com.corrodinggames.rts.game.units.custom.e.f();
   public com.corrodinggames.rts.game.units.custom.e.f j = new com.corrodinggames.rts.game.units.custom.e.f();
   public com.corrodinggames.rts.game.units.custom.e.f k = new com.corrodinggames.rts.game.units.custom.e.f();
   public com.corrodinggames.rts.game.units.custom.e.f l = new com.corrodinggames.rts.game.units.custom.e.f();
   public boolean m;
   public int n;
   public int o;
   public t p = new t();
   public t q = new t();


   public void a(am var1) {
      ++this.d;
      if(var1.cm < 1.0F) {
         ++this.f;
      } else {
         ++this.c;
      }

      as var2 = var1.r();
      if(!var2.k()) {
         ++this.b;
      }

      if(!this.m && !var1.u() && var1.r().y()) {
         this.m = true;
      }

      com.corrodinggames.rts.game.units.custom.d.b var3 = var1.dq();
      if(var3 != null) {
         this.k.a(var3, 0.0D, Double.MAX_VALUE);
         this.l.a(var3, -1.7976931348623157E308D, 0.0D);
      }

      if(var1 instanceof com.corrodinggames.rts.game.units.d.l) {
         com.corrodinggames.rts.game.units.d.l var4 = (com.corrodinggames.rts.game.units.d.l)var1;
         int var5 = var4.f(false);
         this.b += var5;
         this.e += var5;
         if(var5 != 0) {
            this.a(var4);
         }
      }

      this.c(var1);
      float var8 = var1.cy();
      if(var8 != 0.0F && var1.cm >= 1.0F) {
         this.g = (int)((float)this.g + var8);
      }

      com.corrodinggames.rts.game.units.custom.e.f var9 = var1.cA();
      if(!var9.c() && var1.cm >= 1.0F) {
         this.h.b(var9);
         this.i.a(var9, 0.0D, Double.MAX_VALUE);
         this.j.a(var9, -1.7976931348623157E308D, 0.0D);
      }

      if(var1.cU()) {
         int var6 = var1.cM().b();
         com.corrodinggames.rts.game.units.custom.d.b var7 = var2.B();
         if(var7 != null) {
            var6 += var7.b();
         }

         if(var2.j()) {
            this.o += var6;
         } else {
            this.n += var6;
         }
      }

   }

   public void b(am var1) {
      --this.d;
      if(var1.cm < 1.0F) {
         --this.f;
      } else {
         --this.c;
      }

      as var2 = var1.r();
      if(!var2.k()) {
         --this.b;
      }

      com.corrodinggames.rts.game.units.custom.d.b var3 = var1.dq();
      if(var3 != null) {
         this.k.b(var3, 0.0D, Double.MAX_VALUE);
         this.l.b(var3, -1.7976931348623157E308D, 0.0D);
      }

      if(var1 instanceof com.corrodinggames.rts.game.units.d.l) {
         com.corrodinggames.rts.game.units.d.l var4 = (com.corrodinggames.rts.game.units.d.l)var1;
         int var5 = var4.f(false);
         this.b -= var5;
         this.e -= var5;
         if(var5 != 0) {
            this.b(var4);
         }
      }

      this.d(var1);
      float var8 = var1.cy();
      if(var8 != 0.0F && var1.cm >= 1.0F) {
         this.g = (int)((float)this.g - var8);
      }

      com.corrodinggames.rts.game.units.custom.e.f var9 = var1.cA();
      if(!var9.c() && var1.cm >= 1.0F) {
         this.h.c(var9);
         this.i.b(var9, 0.0D, Double.MAX_VALUE);
         this.j.b(var9, -1.7976931348623157E308D, 0.0D);
      }

      if(var1.cU()) {
         int var6 = var1.cM().b();
         com.corrodinggames.rts.game.units.custom.d.b var7 = var2.B();
         if(var7 != null) {
            var6 += var7.b();
         }

         if(var2.j()) {
            this.o -= var6;
         } else {
            this.n -= var6;
         }
      }

   }

   private final void c(am var1) {
      com.corrodinggames.rts.game.units.custom.h var2 = var1.de();
      if(var2 != null) {
         com.corrodinggames.rts.game.units.custom.g[] var3 = var2.a;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            com.corrodinggames.rts.game.units.custom.g var6 = var3[var5];
            p var7 = this.a(var6);
            if(var1.cm < 1.0F) {
               ++var7.c;
            } else {
               ++var7.b;
            }
         }
      }

   }

   private final void d(am var1) {
      com.corrodinggames.rts.game.units.custom.h var2 = var1.de();
      if(var2 != null) {
         com.corrodinggames.rts.game.units.custom.g[] var3 = var2.a;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            com.corrodinggames.rts.game.units.custom.g var6 = var3[var5];
            p var7 = this.a(var6);
            if(var1.cm < 1.0F) {
               --var7.c;
            } else {
               --var7.b;
            }
         }
      }

   }

   private final void a(com.corrodinggames.rts.game.units.d.l var1) {
      com.corrodinggames.rts.gameFramework.utility.m var2 = var1.dx();
      if(var2.a != 0) {
         Iterator var3 = var2.iterator();

         while(var3.hasNext()) {
            com.corrodinggames.rts.game.units.d.j var4 = (com.corrodinggames.rts.game.units.d.j)var3.next();
            if(var4.f) {
               as var5 = var4.g;
               if(var5 != null) {
                  com.corrodinggames.rts.game.units.custom.h var6 = var5.x();
                  if(var6 != null) {
                     com.corrodinggames.rts.game.units.custom.g[] var7 = var6.a;
                     int var8 = var7.length;

                     for(int var9 = 0; var9 < var8; ++var9) {
                        com.corrodinggames.rts.game.units.custom.g var10 = var7[var9];
                        p var11 = this.a(var10);
                        var11.d += var4.a;
                     }
                  }
               }
            }
         }
      }

   }

   private final void b(com.corrodinggames.rts.game.units.d.l var1) {
      com.corrodinggames.rts.gameFramework.utility.m var2 = var1.dx();
      if(var2.a != 0) {
         Iterator var3 = var2.iterator();

         while(var3.hasNext()) {
            com.corrodinggames.rts.game.units.d.j var4 = (com.corrodinggames.rts.game.units.d.j)var3.next();
            if(var4.f) {
               as var5 = var4.g;
               if(var5 != null) {
                  com.corrodinggames.rts.game.units.custom.h var6 = var5.x();
                  if(var6 != null) {
                     com.corrodinggames.rts.game.units.custom.g[] var7 = var6.a;
                     int var8 = var7.length;

                     for(int var9 = 0; var9 < var8; ++var9) {
                        com.corrodinggames.rts.game.units.custom.g var10 = var7[var9];
                        p var11 = this.a(var10);
                        var11.d -= var4.a;
                     }
                  }
               }
            }
         }
      }

   }

   public final p a(com.corrodinggames.rts.game.units.custom.g var1) {
      p[] var2 = this.q.b;
      int var3 = 0;

      for(int var4 = this.q.c; var3 < var4; ++var3) {
         p var5 = var2[var3];
         if(var5.a == var1) {
            return var5;
         }

         if(var5.a == null) {
            var5.a = var1;
            return var5;
         }
      }

      p var6 = new p();
      var6.a = var1;
      this.q.a(var6);
      return var6;
   }
}
