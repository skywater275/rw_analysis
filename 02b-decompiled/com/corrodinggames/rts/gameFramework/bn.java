package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.bh;
import com.corrodinggames.rts.gameFramework.bi;
import com.corrodinggames.rts.gameFramework.bj;
import com.corrodinggames.rts.gameFramework.l;
import java.util.Iterator;

public class bn {

   private int a = -1;
   private bi[] b = new bi[bj.values().length];


   public strictfp bn() {
      this.a();
   }

   public strictfp void a() {
      for(int var1 = 0; var1 < this.b.length; ++var1) {
         this.b[var1] = new bi();
      }

   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      boolean var2 = var1.e();
      if(var2) {
         var1.a("History");
         var1.d();
         this.a = var1.f();
         boolean var3 = var1.e();
         byte var4 = var1.d();
         this.a();

         for(int var5 = 0; var5 < var4; ++var5) {
            int var6 = 0;
            int var7 = 0;
            short var8 = var1.v();

            for(int var9 = 0; var9 < var8; ++var9) {
               int var10;
               int var11;
               if(var3) {
                  var10 = var1.f() + var6;
                  var11 = var1.f() + var7;
                  var6 = var10;
                  var7 = var11;
               } else {
                  var10 = var1.f();
                  var11 = var1.f();
               }

               if(var5 < this.b.length) {
                  this.b[var5].add(new bh(var10, var11));
               }
            }
         }
      }

   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.as var1) {
      boolean var2 = true;
      var1.a(var2);
      if(var2) {
         var1.e();
         var1.c(0);
         var1.a(this.a);
         boolean var3 = true;
         var1.a(var3);
         var1.c(this.b.length);
         int var4 = 0;
         bi[] var5 = this.b;
         int var6 = var5.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            bi var8 = var5[var7];
            short var9 = (short)var8.size();
            var1.a(var9);
            int var10 = 0;
            int var11 = 0;

            for(int var12 = 0; var12 < var9; ++var12) {
               ++var4;
               bh var13 = (bh)var8.get(var12);
               if(var3) {
                  int var14 = var13.a;
                  int var15 = var13.b;
                  var1.a(var14 - var10);
                  var1.a(var15 - var11);
                  var10 = var14;
                  var11 = var15;
               } else {
                  var1.a(var13.a);
                  var1.a(var13.b);
               }
            }
         }

         l.e("TeamHistory(" + this.a + "): totalValues written:" + var4);
      }

   }

   public strictfp void a(com.corrodinggames.rts.game.n var1, int var2, boolean var3) {
      bj[] var4 = bj.values();
      int var5 = var4.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         bj var7 = var4[var6];
         int var8 = var7.e.a(var1);
         bi var9 = this.b[var7.ordinal()];
         if(var9.isEmpty() || var3 || ((bh)var9.get(var9.size() - 1)).b != var8) {
            var9.add(new bh(var2, var8));
         }
      }

   }

   public strictfp void a(int var1) {
      this.a = var1;
   }

   public strictfp int b() {
      return this.a;
   }

   public strictfp bi a(bj var1) {
      return this.b[var1.ordinal()];
   }

   public strictfp boolean c() {
      if(this.a < 0) {
         return false;
      } else {
         bi[] var1 = this.b;
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            bi var4 = var1[var3];
            if(var4.size() > 1) {
               return true;
            }
         }

         return false;
      }
   }

   public strictfp int a(bj var1, int var2) {
      return this.b[var1.ordinal()].a(var2);
   }

   public strictfp void a(bn var1) {
      for(int var2 = 0; var2 < this.b.length; ++var2) {
         this.b[var2] = this.a(this.b[var2], var1.b[var2]);
      }

   }

   private strictfp bi a(bi var1, bi var2) {
      if(var1.isEmpty()) {
         var1.addAll(var2);
         return var1;
      } else {
         bi var3 = new bi();
         int var4 = 0;
         int var5 = 0;
         int var6 = 0;
         Iterator var7 = var1.iterator();

         while(var7.hasNext()) {
            bh var8 = (bh)var7.next();
            int var9 = var8.a;
            int var10 = var8.b;
            if(var4 < var2.size()) {
               bh var11 = (bh)var2.get(var4);

               while(var11.a < var9) {
                  var6 = var11.b;
                  var3.add(new bh(var11.a, var5 + var6));
                  ++var4;
                  if(var4 < var2.size()) {
                     var11 = (bh)var2.get(var4);
                  }
               }

               if(var11.a == var9) {
                  var6 = var11.b;
                  var5 = var10;
                  var3.add(new bh(var9, var10 + var6));
                  ++var4;
               } else if(var11.a > var9) {
                  var5 = var10;
                  var3.add(new bh(var9, var10 + var6));
               }
            } else {
               var5 = var10;
               var3.add(new bh(var9, var10 + var6));
            }
         }

         while(var4 < var2.size()) {
            bh var12 = (bh)var2.get(var4);
            var6 = var12.b;
            var3.add(new bh(var12.a, var5 + var6));
            ++var4;
         }

         return var3;
      }
   }
}
