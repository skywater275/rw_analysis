package com.corrodinggames.rts.gameFramework.f;

import com.corrodinggames.rts.gameFramework.bh;
import com.corrodinggames.rts.gameFramework.bi;
import com.corrodinggames.rts.gameFramework.bj;
import com.corrodinggames.rts.gameFramework.f.aa;
import com.corrodinggames.rts.gameFramework.f.ad;
import java.util.ArrayList;
import java.util.Iterator;

public class ab {

   private bj a;
   private int b;
   private int c;
   private int d;
   private ArrayList e = new ArrayList();


   public ab(bj var1, ArrayList var2) {
      this.a = var1;
      ArrayList var3 = new ArrayList();
      Iterator var4 = var2.iterator();

      while(var4.hasNext()) {
         aa var5 = (aa)var4.next();
         bi var6 = var5.a.a(var1);
         var3.add(var6);
         Iterator var7 = var6.iterator();

         while(var7.hasNext()) {
            bh var8 = (bh)var7.next();
            if(var8.b > this.b) {
               this.b = var8.b;
            }

            if(var8.b < this.c) {
               this.c = var8.b;
            }

            if(var8.a > this.d) {
               this.d = var8.a;
            }
         }
      }

      this.a(var3);
   }

   private void a(ArrayList var1) {
      int var2 = var1.size();
      ad var3 = new ad(var2);
      int[] var4 = new int[var2];
      int var5 = 0;

      boolean var12;
      do {
         ++var5;
         if(var5 > 1000000) {
            throw new RuntimeException("loopIndex: " + var5);
         }

         boolean var6 = true;

         for(int var7 = 0; var7 < var2; ++var7) {
            bi var8 = (bi)var1.get(var7);
            if(var4[var7] < var8.size()) {
               bh var9 = (bh)var8.get(var4[var7]);
               if(var9.a <= ad.a(var3)) {
                  var3.a(var7, var9.b);
                  ++var4[var7];
                  var6 = false;
               }
            }
         }

         var12 = var6;
         int var13 = Integer.MAX_VALUE;
         if(var6) {
            this.e.add(var3);

            for(int var14 = 0; var14 < var2; ++var14) {
               bi var10 = (bi)var1.get(var14);
               if(var4[var14] < var10.size()) {
                  bh var11 = (bh)var10.get(var4[var14]);
                  if(var11.a < var13) {
                     var13 = var11.a;
                     var12 = false;
                  }
               }
            }

            var3 = new ad(var13, var3);
         }
      } while(!var12);

   }

   // $FF: synthetic method
   static int a(ab var0) {
      return var0.b;
   }

   // $FF: synthetic method
   static int b(ab var0) {
      return var0.c;
   }

   // $FF: synthetic method
   static bj c(ab var0) {
      return var0.a;
   }

   // $FF: synthetic method
   static int d(ab var0) {
      return var0.d;
   }

   // $FF: synthetic method
   static ArrayList e(ab var0) {
      return var0.e;
   }
}
