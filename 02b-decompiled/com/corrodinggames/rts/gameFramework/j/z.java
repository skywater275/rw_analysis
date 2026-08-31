package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.n;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class z implements Runnable {

   public void run() {
      com.corrodinggames.rts.gameFramework.l.aq();
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.l.b("startRemoveOnMasterServer", "Starting remove");

      try {
         ArrayList var2 = new ArrayList(2);
         n.a(var2, "action", "remove");
         String var3 = var1.bX.aS;
         if(var3 == null) {
            com.corrodinggames.rts.gameFramework.l.b("startRemoveOnMasterServer", "No game id");
            return;
         }

         n.a(var2, "id", var3);
         n.a(var2, "private_token", var1.bX.aR);
         BufferedReader var4 = n.a((List)var2);
         String var5 = var4.readLine();
         if(var5 == null || !var5.contains("CORRODINGGAMES")) {
            com.corrodinggames.rts.gameFramework.l.b("startRemoveOnMasterServer", "Error bad header returned from the master server: " + var5);
            return;
         }

         String var6 = var4.readLine();
         com.corrodinggames.rts.gameFramework.l.b("startRemoveOnMasterServer", "Remove server response was:" + var6);
         com.corrodinggames.rts.gameFramework.l.b("startRemoveOnMasterServer", "Completed load from master server without error");
      } catch (IOException var7) {
         com.corrodinggames.rts.gameFramework.l.b("startRemoveOnMasterServer", "Remove failed");
         var7.printStackTrace();
      }

   }
}
