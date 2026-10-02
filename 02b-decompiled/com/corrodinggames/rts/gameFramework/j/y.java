package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.aq;
import com.corrodinggames.rts.gameFramework.j.n;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class y implements Runnable {

   public void run() {
      com.corrodinggames.rts.gameFramework.l.aq();
      long var1 = com.corrodinggames.rts.gameFramework.l.V();
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.l.b("StartCreateOnMasterServer", "Starting create");
      boolean var19 = false;

      label135: {
         float var24;
         label136: {
            try {
               var19 = true;
               ArrayList var4 = new ArrayList(2);
               n.a(var4, "action", "add");
               String var5 = "u_" + com.corrodinggames.rts.gameFramework.f.b();
               n.a(var4, "user_id", var5);
               aq.a.a(var5, var4);
               n.a(var4, "game_name", "Unnamed");
               n.a(var4, "game_version", Integer.toString(var3.c(true)));
               if(!var3.bX.v) {
                  n.a(var4, "game_version_string", var3.v());
               } else {
                  n.a(var4, "game_version_string", "ANY");
               }

               n.a(var4, "game_version_beta", com.corrodinggames.rts.gameFramework.f.a(var3.n()));
               String var6 = var3.bX.au();
               if(var6 != null) {
                  n.a(var4, "game_mods", var6);
               }

               n.a(var4, "private_token", var3.bX.aR);
               n.a(var4, "private_token_2", com.corrodinggames.rts.gameFramework.f.b(com.corrodinggames.rts.gameFramework.f.b(var3.bX.aR)));
               n.a(var4, "confirm", com.corrodinggames.rts.gameFramework.f.b("a" + com.corrodinggames.rts.gameFramework.f.b(var3.bX.aR)));
               n.b((List)var4);
               BufferedReader var7 = n.a(var4, 15);
               String var8 = var7.readLine();
               if(var8 == null || !var8.contains("CORRODINGGAMES")) {
                  com.corrodinggames.rts.gameFramework.l.b("StartCreateOnMasterServer", "Error bad header returned from the master server: " + var8);
                  var19 = false;
                  break label135;
               }

               String var9 = var7.readLine();
               String[] var10 = var9.split(",");
               if(var10.length < 1) {
                  com.corrodinggames.rts.gameFramework.l.b("StartCreateOnMasterServer", "columns.length too short at:" + var10.length);
               }

               String var11 = var10[0];

               try {
                  com.corrodinggames.rts.gameFramework.l.b("StartCreateOnMasterServer", "Created server is:" + var11);
                  var3.bX.aS = var11;
               } catch (NumberFormatException var21) {
                  com.corrodinggames.rts.gameFramework.l.b("StartCreateOnMasterServer", "failed to load server");
                  var21.printStackTrace();
               }

               if(var10.length >= 2) {
                  try {
                     aq.f = Integer.parseInt(var10[1]);
                  } catch (NumberFormatException var20) {
                     aq.f = -1;
                  }
               }

               com.corrodinggames.rts.gameFramework.l.b("StartCreateOnMasterServer", "Completed create from master server without error");
               var19 = false;
               break label136;
            } catch (IOException var22) {
               var22.printStackTrace();
               var19 = false;
            } finally {
               if(var19) {
                  float var14 = (float)(com.corrodinggames.rts.gameFramework.l.V() - var1) / 1000000.0F;
                  com.corrodinggames.rts.gameFramework.l.b("StartCreateOnMasterServer", "create took: " + var14 + " seconds");
               }
            }

            var24 = (float)(com.corrodinggames.rts.gameFramework.l.V() - var1) / 1000000.0F;
            com.corrodinggames.rts.gameFramework.l.b("StartCreateOnMasterServer", "create took: " + var24 + " seconds");
            return;
         }

         var24 = (float)(com.corrodinggames.rts.gameFramework.l.V() - var1) / 1000000.0F;
         com.corrodinggames.rts.gameFramework.l.b("StartCreateOnMasterServer", "create took: " + var24 + " seconds");
         return;
      }

      float var25 = (float)(com.corrodinggames.rts.gameFramework.l.V() - var1) / 1000000.0F;
      com.corrodinggames.rts.gameFramework.l.b("StartCreateOnMasterServer", "create took: " + var25 + " seconds");
   }
}
