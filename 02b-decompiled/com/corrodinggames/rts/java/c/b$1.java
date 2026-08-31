package com.corrodinggames.rts.java.c;

import com.codedisaster.steamworks.SteamID;
import com.corrodinggames.librocket.scripts.Root;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.java.c.b;
import com.corrodinggames.rts.java.c.k;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;

class b$1 implements Runnable {

   // $FF: synthetic field
   final SteamID a;
   // $FF: synthetic field
   final b b;


   b$1(b var1, SteamID var2) {
      this.b = var1;
      this.a = var2;
   }

   public void run() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();

      String var3;
      try {
         this.b.a("connectTo runnable start");
         Root var2 = ScriptEngine.getInstance().getRoot();
         var1.bX.b("starting new");
         this.b.n = this.a;
         this.b.p = this.b.d.getLobbyOwner(this.b.n);
         var3 = var1.bQ.lastNetworkPlayerName;
         String var4 = com.corrodinggames.rts.gameFramework.o.a.a().c();
         if(var4 != null && var3 == null) {
            var3 = var4.replace(" ", "_");
            var3 = com.corrodinggames.rts.gameFramework.f.a(var3, (int)20);
         }

         var1.bX.y = var3;
         k var5 = new k(this.b, this.b.p);
         this.b.l.put(this.b.p, var5);
         var1.bX.a((Socket)var5);

         com.corrodinggames.rts.gameFramework.j.c var7;
         for(Iterator var6 = var1.bX.aM.iterator(); var6.hasNext(); var7.i = true) {
            var7 = (com.corrodinggames.rts.gameFramework.j.c)var6.next();
         }

         this.b.a("connected");
         var2.showBattleroom();
         this.b.a("connectTo runnable end");
      } catch (IOException var8) {
         var3 = var8.getMessage();
         var1.c(var3, "Connection failed");
         var8.printStackTrace();
      }

   }
}
