package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.n;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class aa implements Runnable {

   public void run() {
      com.corrodinggames.rts.gameFramework.l.aq();
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();

      try {
         ArrayList var2 = new ArrayList(2);
         n.a(var2, "action", "update");
         String var3 = var1.bX.aS;
         if(var3 == null) {
            com.corrodinggames.rts.gameFramework.l.b("startUpdateOnMasterServer", "No game id");
            return;
         }

         n.a(var2, "id", var3);
         n.a(var2, "private_token", var1.bX.aR);
         if(com.corrodinggames.rts.gameFramework.l.ax()) {
            n.a(var2, "check_port", "false");
         }

         n.b((List)var2);
         BufferedReader var4 = n.a((List)var2);
         String var5 = var4.readLine();
         if(var5 == null || !var5.contains("CORRODINGGAMES")) {
            com.corrodinggames.rts.gameFramework.l.b("startUpdateOnMasterServer", "Error bad header returned from the master server: " + var5);
            return;
         }

         String var6 = var4.readLine();
         if(!"GAME UPDATED".equals(var6)) {
            com.corrodinggames.rts.gameFramework.l.b("startUpdateOnMasterServer", "Update server response was:" + var6);
         }
      } catch (IOException var7) {
         var7.printStackTrace();
      }

   }
}
