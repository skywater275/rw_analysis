package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.as$1;
import com.corrodinggames.rts.game.units.custom.as$10;
import com.corrodinggames.rts.game.units.custom.as$11;
import com.corrodinggames.rts.game.units.custom.as$12;
import com.corrodinggames.rts.game.units.custom.as$13;
import com.corrodinggames.rts.game.units.custom.as$14;
import com.corrodinggames.rts.game.units.custom.as$15;
import com.corrodinggames.rts.game.units.custom.as$16;
import com.corrodinggames.rts.game.units.custom.as$17;
import com.corrodinggames.rts.game.units.custom.as$18;
import com.corrodinggames.rts.game.units.custom.as$19;
import com.corrodinggames.rts.game.units.custom.as$2;
import com.corrodinggames.rts.game.units.custom.as$3;
import com.corrodinggames.rts.game.units.custom.as$4;
import com.corrodinggames.rts.game.units.custom.as$5;
import com.corrodinggames.rts.game.units.custom.as$6;
import com.corrodinggames.rts.game.units.custom.as$7;
import com.corrodinggames.rts.game.units.custom.as$8;
import com.corrodinggames.rts.game.units.custom.as$9;
import com.corrodinggames.rts.game.units.custom.at;
import com.corrodinggames.rts.game.units.custom.au;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;

public class as implements Cloneable {

   public boolean a;
   public float b;
   public int c;
   public float d;
   public float e = 1.0F;
   public float f = 1.0F;
   public int g;
   public float h;
   public float i;
   public float j;
   public float k;
   public float l;
   public boolean m;
   public int n;
   public int o;
   public float p;
   public float q;
   public float r;
   static LinkedHashMap s = new LinkedHashMap();
   static LinkedHashMap t;


   public as(boolean var1) {
      this.a = var1;
   }

   public static VariableScope$CachedWriter a(String var0, l var1, String var2, String var3) {
      try {
         return VariableScope$CachedWriter.create(var0, new au(var1));
      } catch (bo var5) {
         throw new RuntimeException("[" + var2 + "]" + var3 + ": " + var5.getMessage(), var5);
      }
   }

   public as a() {
      try {
         as var1 = (as)super.clone();
         var1.a = false;
         return var1;
      } catch (CloneNotSupportedException var2) {
         throw new RuntimeException(var2);
      }
   }

   static void a(LinkedHashMap var0, at var1) {
      var0.put(var1.b, var1);
   }

   public as b() {
      as var1 = this.a();
      var1.a = false;
      return var1;
   }

   public static at a(int var0) {
      Iterator var1 = s.values().iterator();

      at var2;
      do {
         if(!var1.hasNext()) {
            return null;
         }

         var2 = (at)var1.next();
      } while(var0 != var2.a);

      return var2;
   }

   public static void a(j var0, as var1, at[] var2) {
      at[] var3 = var2;
      int var4 = var2.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         at var6 = var3[var5];
         double var7 = var6.a(var0, var0.y);
         double var9 = var6.a(var0, var1);
         if(var7 != var9) {
            var0.dJ();
            var6.a(var0, var9);
         }
      }

   }

   public static void a(j var0, as var1, l var2) {
      boolean var3 = true;
      boolean var4 = var1 != var2.cL;
      if(var4) {
         Iterator var5 = t.keySet().iterator();

         while(var5.hasNext()) {
            String var6 = (String)var5.next();
            at var7 = (at)t.get(var6);
            double var8 = var7.a(var0, var2.cL);
            double var10 = var7.a(var0, var1);
            if(var8 != var10) {
               var0.dJ();
               var7.a(var0, var10);
            }
         }

      }
   }

   public static void a(as var0, j var1, com.corrodinggames.rts.gameFramework.j.as var2) {
      l var3 = var1.x;
      boolean var4 = var0 != var3.cL;
      if(!var4) {
         var2.a(true);
      } else {
         var2.a(false);
         short var5 = 0;
         Iterator var6 = t.keySet().iterator();

         while(var6.hasNext()) {
            String var7 = (String)var6.next();
            at var8 = (at)t.get(var7);
            double var9 = var8.a(var1, var3.cL);
            double var11 = var8.a(var1, var0);
            if(var9 != var11) {
               ++var5;
            }
         }

         var2.a(var5);
         int var14 = 0;
         Iterator var15 = t.keySet().iterator();

         while(var15.hasNext()) {
            String var16 = (String)var15.next();
            at var17 = (at)t.get(var16);
            double var10 = var17.a(var1, var3.cL);
            double var12 = var17.a(var1, var0);
            if(var10 != var12) {
               ++var14;
               if(var5 < var14) {
                  throw new IOException("numberOfChangedFields>fieldsWritten: " + var5 + ">" + var14);
               }

               var2.a((short)var17.a);
               var2.a(var12);
               var2.a(var10);
            }
         }
      }

   }

   public static void a(j var0, com.corrodinggames.rts.gameFramework.j.k var1, int var2) {
      l var3 = var0.x;
      boolean var4 = var1.e();
      if(!var4) {
         short var5 = var1.v();

         for(int var6 = 0; var6 < var5; ++var6) {
            short var7 = var1.v();
            double var8 = var1.h();
            double var10 = var1.h();
            at var12 = a(var7);
            if(var12 == null) {
               throw new IOException("Field " + var7 + " doesn\'t exist");
            }

            var0.dJ();
            var12.a(var0, var8);
         }

      }
   }

   public static at[] a(com.corrodinggames.rts.gameFramework.utility.ab var0, String var1, String var2, at[] var3) {
      String var4 = var0.b(var1, var2, (String)null);

      try {
         return a(var4, var3);
      } catch (RuntimeException var6) {
         throw new RuntimeException("[" + var1 + "]" + var2 + ": " + var6.getMessage(), var6);
      }
   }

   public static at[] a(String var0, at[] var1) {
      if(var0 == null) {
         return var1;
      } else {
         ArrayList var2 = new ArrayList();
         String[] var3 = com.corrodinggames.rts.gameFramework.f.c(var0, ',');
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            String var6 = var3[var5];
            var6 = var6.trim();
            var6 = var6.toLowerCase(Locale.ROOT);
            at var7 = (at)t.get(var6);
            if(var2.contains(var7)) {
               throw new RuntimeException("Value: " + var6 + " is repeated");
            }

            if(var7 == null) {
               String var8 = "";

               String var10;
               for(Iterator var9 = t.keySet().iterator(); var9.hasNext(); var8 = var8 + var10) {
                  var10 = (String)var9.next();
                  if(!var8.equals("")) {
                     var8 = var8 + ", ";
                  }
               }

               throw new RuntimeException("Unknown value: " + var6 + " (Expected: " + com.corrodinggames.rts.gameFramework.f.b(var8, (int)100) + ")");
            }

            var2.add(var7);
         }

         return (at[])var2.toArray(new at[0]);
      }
   }

   // $FF: synthetic method
   public Object clone() {
      return this.a();
   }

   static {
      a(s, (at)(new as$1(s.size(), "mass")));
      a(s, (at)(new as$12(s.size(), "maxenergy")));
      a(s, (at)(new as$13(s.size(), "energy")));
      a(s, (at)(new as$14(s.size(), "maxhp")));
      a(s, (at)(new as$15(s.size(), "hp")));
      a(s, (at)(new as$16(s.size(), "maxshield")));
      a(s, (at)(new as$17(s.size(), "shield")));
      a(s, (at)(new as$18(s.size(), "shieldregen")));
      a(s, (at)(new as$19(s.size(), "armour")));
      a(s, (at)(new as$2(s.size(), "maxattackrange")));
      a(s, (at)(new as$3(s.size(), "shootdelaymultiplier")));
      a(s, (at)(new as$4(s.size(), "shootdamagemultiplier")));
      a(s, (at)(new as$5(s.size(), "movespeed")));
      a(s, (at)(new as$6(s.size(), "maxturnspeed")));
      a(s, (at)(new as$7(s.size(), "fogofwarsightrange")));
      a(s, (at)(new as$8(s.size(), "nanorange")));
      a(s, (at)(new as$9(s.size(), "selfregenrate")));
      a(s, (at)(new as$10(s.size(), "targetHeight")));
      a(s, (at)(new as$11(s.size(), "nanoFactorySpeed")));
      t = new LinkedHashMap();
      Iterator var0 = s.keySet().iterator();

      while(var0.hasNext()) {
         String var1 = (String)var0.next();
         if(!var1.equals(var1.toLowerCase(Locale.ROOT))) {
            throw new RuntimeException(var1);
         }

         at var2 = (at)s.get(var1);
         if(!var2.b()) {
            t.put(var1, var2);
         }
      }

   }
}
