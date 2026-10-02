package com.corrodinggames.rts.java.c;

import com.codedisaster.steamworks.SteamID;
import com.corrodinggames.librocket.scripts.Root;
import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.java.c.a$1;
import com.corrodinggames.rts.java.c.b;

public class a implements Runnable {

   b a;
   String b;
   SteamID c;
   SteamID d;
   long e;
   Thread f;


   public a(b var1, SteamID var2, SteamID var3, long var4) {
      this.a = var1;
      this.c = var2;
      this.d = var3;
      this.e = var4;
      this.b = var1.c.getFriendPersonaName(var2);
   }

   public void a() {
      if(this.f != null) {
         throw new RuntimeException("already started");
      } else {
         a$1 var1 = new a$1(this);
         ScriptEngine.getInstance().addRunnableToQueue(var1);
      }
   }

   public void run() {
      com.corrodinggames.rts.gameFramework.l.e("Join clicked");
      Root var1 = ScriptEngine.getInstance().getRoot();
      var1.closePopup();
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      this.a.d.joinLobby(this.d);
   }
}
