package com.corrodinggames.rts.java;

import com.corrodinggames.librocket.scripts.ScriptEngine;
import com.corrodinggames.rts.java.Main;
import com.corrodinggames.rts.java.i$1;
import com.corrodinggames.rts.java.i$2;
import java.awt.Toolkit;
import org.lwjgl.Sys;

public class i extends com.corrodinggames.rts.gameFramework.n {

   Main a;


   public i(Main var1) {
      this.a = var1;
   }

   public void a(String var1, int var2) {
      com.corrodinggames.rts.gameFramework.l.e("slick queuing-alert:" + var1);
      ScriptEngine.getInstance().addRunnableToQueue(new i$1(this, var1));
   }

   public void a(String var1, String var2) {
      com.corrodinggames.rts.gameFramework.l.e("slick queuing-messageBox:" + var2);
      ScriptEngine.getInstance().addRunnableToQueue(new i$2(this, var2, var1));
   }

   public void a(String var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      if(var3 == null || !var3.bj) {
         if(this.a.j != null) {
            this.a.j.a(var1, var2);
         }

      }
   }

   public void d() {
      com.corrodinggames.rts.gameFramework.l.e("refreshModDisplay");
      ScriptEngine.getInstance().addScriptToQueue("mods.refreshModList()");
   }

   public void a(Throwable var1) {
      this.a(var1, true);
   }

   public void a(Throwable var1, boolean var2) {
      try {
         com.corrodinggames.rts.gameFramework.l.e("----------- onGameCrash ----------");
         Toolkit.getDefaultToolkit();
         String var3 = com.corrodinggames.rts.gameFramework.l.b(var1);
         String var4 = var3 + "\nCheck logs for more details";
         com.corrodinggames.rts.gameFramework.l.e("Error message: " + var4);
         if(com.corrodinggames.rts.a.a.a()) {
            com.corrodinggames.rts.gameFramework.l.e("onGameCrash: Not showing popup message due to active debugSocket");
            System.exit(1);
            return;
         }

         if(var1 != null && var1 instanceof OutOfMemoryError && !com.corrodinggames.rts.game.i.b) {
            var4 = var4 + " (You are also using the 32 bit version, switching to the 64 bit version might help with out of memory)";
         }

         Sys.alert("Crash", var4);

         try {
            Thread.sleep(1000L);
         } catch (InterruptedException var6) {
            var6.printStackTrace();
         }

         com.corrodinggames.rts.gameFramework.l.e("onGameCrash: end");
      } catch (Throwable var7) {
         com.corrodinggames.rts.gameFramework.l.e("exception showing message");
         var7.printStackTrace();
      }

   }

   public boolean b() {
      return !com.corrodinggames.rts.gameFramework.l.B().I() && !this.a.p.b();
   }

   public boolean c() {
      return com.corrodinggames.rts.a.a.a();
   }
}
