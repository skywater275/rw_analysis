package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.g;
import com.corrodinggames.rts.gameFramework.j.n;
import com.corrodinggames.rts.gameFramework.j.q;
import com.corrodinggames.rts.gameFramework.j.s;
import java.io.BufferedReader;

class q$1 extends s {

   // $FF: synthetic field
   final int a;
   // $FF: synthetic field
   final com.corrodinggames.rts.gameFramework.l b;
   // $FF: synthetic field
   final q c;


   q$1(q var1, int var2, com.corrodinggames.rts.gameFramework.l var3) {
      this.c = var1;
      this.a = var2;
      this.b = var3;
   }

   void a(BufferedReader var1, int var2, String var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      String var5 = var1.readLine();
      String var7;
      if(var5 != null && var5.contains("CORRODINGGAMES")) {
         com.corrodinggames.rts.gameFramework.l.b("LoadFromMasterServer", var2 + ": Starting load");
         int var45 = 0;

         while((var7 = var1.readLine()) != null) {
            String[] var46 = var7.split(",", -1);
            if(var46.length <= 21) {
               com.corrodinggames.rts.gameFramework.l.b("LoadFromMasterServer", var2 + ": columns.length too short at:" + var46.length);
               com.corrodinggames.rts.gameFramework.l.b("LoadFromMasterServer", var2 + ": short line is:" + var7);
            } else {
               String var9 = var46[0];
               String var10 = var46[1];
               String var11 = var46[2];
               String var12 = var46[3];
               String var13 = var46[4];
               String var14 = var46[5];
               String var15 = var46[6];
               String var16 = var46[7];
               String var17 = var46[8];
               String var18 = var46[9];
               String var19 = var46[10];
               String var20 = var46[11];
               String var21 = var46[12];
               String var22 = var46[13];
               String var23 = var46[15];
               String var24 = var46[16];
               String var25 = var46[17];
               String var26 = var46[18];
               String var27 = var46[19];
               String var28 = var46[20];
               String var29 = var46[21];
               String var30 = null;
               String var31 = null;
               String var32;
               if(var12 != null && var12.startsWith("url:") && Boolean.parseBoolean(var25)) {
                  var30 = var12.substring(4);
                  var31 = var10;
                  var32 = var30 + ";" + var10;
                  String var33 = com.corrodinggames.rts.gameFramework.f.c(var32);
                  if(!var33.equals(var13)) {
                     com.corrodinggames.rts.gameFramework.l.e("Skipping " + var26);
                     continue;
                  }
               }

               if(var26 == null || var26.trim().length() == 0) {
                  var26 = var9;
               }

               try {
                  var32 = var26;
                  Object var47 = n.f;
                  synchronized(n.f) {
                     g var34 = n.c(var32);
                     var34.c = var12;
                     var34.d = var13;
                     var34.e = var30;
                     var34.f = var31;
                     var34.g = Integer.valueOf(var14).intValue();
                     var34.h = Boolean.parseBoolean(var15);
                     var34.m = Boolean.parseBoolean(var17);
                     var34.j = var11;

                     try {
                        var34.l = Integer.parseInt(var34.j);
                     } catch (NumberFormatException var41) {
                        com.corrodinggames.rts.gameFramework.l.b("game_version_int:" + var41.getMessage());
                     }

                     var34.n = var16;
                     var34.q = var18;
                     var34.r = var19;
                     var34.s = var20;
                     var34.k = var21;
                     var34.a = Boolean.parseBoolean(var22);
                     var34.t = var23;
                     var34.u = var24;
                     var34.y = Boolean.parseBoolean(var27);
                     if("".equals(var28)) {
                        var28 = null;
                     }

                     var34.z = var28;
                     if(!var29.trim().equals("")) {
                        var34.A = Integer.valueOf(var29).intValue();
                     }

                     try {
                        var34.v = Integer.parseInt(var34.t);
                     } catch (NumberFormatException var40) {
                        com.corrodinggames.rts.gameFramework.l.b("game_player_count_int:" + var40.getMessage());
                     }

                     try {
                        var34.w = Integer.parseInt(var34.u);
                     } catch (NumberFormatException var39) {
                        com.corrodinggames.rts.gameFramework.l.b("game_max_player_count_int:" + var39.getMessage());
                     }

                     var34.x = Boolean.parseBoolean(var25);
                     if(var34.p < this.a) {
                        var34.p = this.a;
                     }

                     if(n.b(var34.b) == null) {
                        var4.bX.bi.add(var34);
                     }

                     ++var45;
                  }
               } catch (NumberFormatException var43) {
                  com.corrodinggames.rts.gameFramework.l.b("LoadFromMasterServer", var2 + ": failed to load server");
                  var43.printStackTrace();
               }
            }
         }

         com.corrodinggames.rts.gameFramework.l.b("LoadFromMasterServer", "[" + var2 + "]: Found " + var45 + " servers");
         if(var45 == 0) {
            try {
               Thread.sleep(2000L);
            } catch (InterruptedException var38) {
               var38.printStackTrace();
            }
         }

         this.e = true;
         this.c.a.run();

         try {
            Thread.sleep(2000L);
         } catch (InterruptedException var37) {
            var37.printStackTrace();
         }

         n.a(this.a, var2);
         com.corrodinggames.rts.gameFramework.l.b("LoadFromMasterServer", var2 + ": Completed load from master server without error");
      } else {
         String var6 = var2 + ": Unknown header from the master server: \'" + com.corrodinggames.rts.gameFramework.f.a(var5, (int)30) + "\'";
         com.corrodinggames.rts.gameFramework.l.b("LoadFromMasterServer", var6);
         this.d = var6;

         try {
            var7 = "";
            var7 = var7 + var5 + "\n";
            com.corrodinggames.rts.gameFramework.l.e("----------- Full response ----------");
            com.corrodinggames.rts.gameFramework.l.b("LoadFromMasterServer", "line:" + var5);

            for(String var8 = ""; (var8 = var1.readLine()) != null; var7 = var7 + var8 + "\n") {
               com.corrodinggames.rts.gameFramework.l.b("LoadFromMasterServer", "line:" + var8);
            }

            com.corrodinggames.rts.gameFramework.l.e("------------------------------------");
            n.g = var7;
         } catch (Exception var44) {
            var44.printStackTrace();
         }

      }
   }

   void a() {
      if(!this.e) {
         this.b.bX.bh = this.d;
         this.c.a.run();
      }

   }
}
