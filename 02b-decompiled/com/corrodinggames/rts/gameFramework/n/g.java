package com.corrodinggames.rts.gameFramework.n;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.gameFramework.n.f;
import com.corrodinggames.rts.gameFramework.n.i;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class g {

   public ArrayList a;
   public boolean b;
   public boolean c;
   public float d;
   public int e;
   public String f;
   public boolean g;
   public boolean h;
   // $FF: synthetic field
   final f i;


   public g(f var1) {
      this.i = var1;
      this.a = new ArrayList();
   }

   public boolean a(String var1) {
      var1 = var1.trim();
      com.corrodinggames.rts.gameFramework.l.e("Got:" + var1);
      if(var1.length() == 0) {
         return false;
      } else {
         com.corrodinggames.rts.gameFramework.l.e("..");
         String var2 = null;
         String var3 = null;
         String var4 = null;
         String var5 = null;
         Pattern var6;
         Matcher var7;
         if(var1.startsWith("+")) {
            var6 = Pattern.compile("\\+([^ ]*)([^\\[-]*)(\\[(.*?)\\])? *(-.*)?");
            var7 = var6.matcher(var1);
            if(!var7.matches()) {
               throw new com.corrodinggames.rts.game.b.f("Unknown wave line in map: " + var1);
            }

            var2 = var7.group(1);
            var3 = var7.group(2);
            var4 = var7.group(4);
            var5 = var7.group(5);
            com.corrodinggames.rts.gameFramework.l.e("Got o:" + var4 + " d:" + var2 + " dn:" + var3 + " units:" + var5);
         } else {
            if(!var1.startsWith("!")) {
               throw new com.corrodinggames.rts.game.b.f("Unknown wave format: " + var1);
            }

            var6 = Pattern.compile("\\!(.*)");
            var7 = var6.matcher(var1);
            if(!var7.matches()) {
               throw new com.corrodinggames.rts.game.b.f("Unknown wave line in map: " + var1);
            }

            var4 = var7.group(1);
         }

         String[] var19;
         if(var2 != null) {
            var2 = var2.trim();
            var19 = var2.split(":");
            String var20 = "0";
            String var8 = "0";
            if(var19.length == 1) {
               var20 = var19[0];
            } else {
               if(var19.length != 2) {
                  throw new com.corrodinggames.rts.game.b.f("Unknown time format in wave: " + var1);
               }

               var8 = var19[0];
               var20 = var19[1];
            }

            try {
               this.d = (float)(Integer.parseInt(var20) + Integer.parseInt(var8) * 60);
            } catch (NumberFormatException var18) {
               throw new com.corrodinggames.rts.game.b.f("Failed to parse time on: " + var1, var18);
            }
         }

         if(var3 != null) {
            var3 = var3.trim();
            this.f = var3;
            this.h = true;
         }

         int var9;
         String var10;
         String var12;
         String var13;
         String[] var21;
         int var22;
         if(var4 != null) {
            var19 = var4.split(",");
            var21 = var19;
            var22 = var19.length;

            for(var9 = 0; var9 < var22; ++var9) {
               var10 = var21[var9];
               String[] var11 = var10.split(":");
               var12 = var11[0].trim();
               var13 = null;
               if(var11.length > 1) {
                  var13 = var11[1].trim();
               }

               if("lockSpawn".equalsIgnoreCase(var12)) {
                  this.b = true;
               } else if("unlockSpawn".equalsIgnoreCase(var12)) {
                  this.c = true;
               } else if("noTimer".equalsIgnoreCase(var12)) {
                  this.g = true;
               } else if(!"paused".equalsIgnoreCase(var12) && !"win".equalsIgnoreCase(var12) && !"".equalsIgnoreCase(var12)) {
                  throw new com.corrodinggames.rts.game.b.f("Unknown wave option \'" + var12 + "\' in: " + var1);
               }
            }
         }

         if(var5 != null) {
            var5 = var5.trim();
            if(var5.startsWith("-")) {
               var5 = var5.substring(1);
            }

            var19 = var5.split(",");
            var21 = var19;
            var22 = var19.length;

            for(var9 = 0; var9 < var22; ++var9) {
               var10 = var21[var9];
               var10 = var10.trim();
               if(!var10.contains(" ")) {
                  throw new com.corrodinggames.rts.game.b.f("Unknown wave format \'" + var10 + "\' in: " + var1);
               }

               int var23 = var10.indexOf(" ");
               var12 = var10.substring(0, var23).trim();
               var13 = var10.substring(var23 + 1).trim();

               int var15;
               try {
                  var15 = Integer.parseInt(var12);
               } catch (NumberFormatException var17) {
                  throw new com.corrodinggames.rts.game.b.f("Expected starting number in wave format \'" + var10 + "\' in: " + var1);
               }

               as var14 = ar.a(var13);
               if(var14 == null) {
                  throw new com.corrodinggames.rts.game.b.f("Could not find unit \'" + var13 + "\' in: " + var1);
               }

               i var16 = new i(this.i);
               var16.b(var14, var15);
               this.a.add(var16);
            }
         }

         return true;
      }
   }

   public void a() {
      com.corrodinggames.rts.gameFramework.l.e("Activating wave");
      if(!this.i.R) {
         this.i.e();
      }

      PointF var1 = this.i.P;
      Iterator var2 = this.a.iterator();

      while(var2.hasNext()) {
         i var3 = (i)var2.next();
         var3.a(var1.a, var1.b);
      }

      if(!this.i.Q) {
         this.i.e();
      }

      if(this.b) {
         this.i.Q = true;
      }

      if(this.c) {
         this.i.Q = false;
      }

   }
}
