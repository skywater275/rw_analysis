package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.SettingsEngine;
import com.corrodinggames.rts.gameFramework.l;
import java.lang.Thread.UncaughtExceptionHandler;

public class i implements UncaughtExceptionHandler {

   private UncaughtExceptionHandler a;


   i(UncaughtExceptionHandler var1) {
      this.a = var1;
   }

   public synchronized void uncaughtException(Thread var1, Throwable var2) {
      boolean var3 = false;
      boolean var4 = false;
      l var5 = null;

      try {
         l.dQ = null;
         l.dR = null;
         l.dP = null;
         System.gc();

         try {
            l.e("uncaughtException start");
            var5 = l.B();
            if(var5 != null && var2 instanceof OutOfMemoryError) {
               l.e("Freeing memory");

               try {
                  com.corrodinggames.rts.game.b.b.al = null;
                  if(var5.bL != null) {
                     var5.bL = null;
                  }

                  if(var5.bN != null) {
                     var5.bN.i();
                     var5.bN = null;
                  }

                  System.gc();
                  l.e("uncaughtException: Memory freed");
               } catch (Throwable var17) {
                  l.e("exception freeing memory");
                  var17.printStackTrace();
               }
            }

            l.a("gameEngine:uncaughtExceptionHandler", var2);
            String var6 = l.a(var2);
            boolean var7 = false;
            boolean var8 = false;
            if(var5 != null) {
               SettingsEngine var9 = var5.bQ;
               if(var9 != null) {
                  var7 = var9.sendReports;
               } else {
                  l.e("CustomExceptionHandler: no settings");
               }
            } else {
               l.e("CustomExceptionHandler: no game");
            }

            if(l.dO) {
               l.e("CustomExceptionHandler: a crash was already sent");
               var7 = false;
               var8 = true;
            }

            l.dO = true;
            if(var7) {
               try {
                  l.e("Starting errorReport");
                  com.corrodinggames.rts.gameFramework.j.n.a("uncaughtException", var6);
                  l.e("waiting");
                  Thread.sleep(800L);
               } catch (InterruptedException var16) {
                  var16.printStackTrace();
               }
            }

            if(!var8 && var5 != null && var5.dH != null) {
               var5.dH.a(var2);
            }

            var4 = true;
            l.e("fatal", var6);
         } catch (Exception var18) {
            l.e("exception sending crash");
            var18.printStackTrace();
         }

         if(var5 != null) {
            if(var5.dH != null && var5.dH.a()) {
               l.e("gameCrashesDontExit=true");
               var3 = true;
               return;
            }

            if(var5.bX != null && var5.bX.B) {
               l.e("Sending disconnect");
               var5.bX.c("Game crash");
            }
         }

         if(!l.az) {
            if(this.a != null) {
               l.e("CustomExceptionHandler: sending to: defaultUEH.uncaughtException");
               this.a.uncaughtException(var1, var2);
               l.e("CustomExceptionHandler: back from: defaultUEH.uncaughtException");
            } else {
               l.e("CustomExceptionHandler: defaultUEH==null");
               System.exit(2);
            }
         }

         l.av = var2;
         var3 = true;
      } catch (Throwable var19) {
         l.e("Exception in uncaughtException");
         var19.printStackTrace();
      } finally {
         if(!var3) {
            l.e("Crash was not handled, exiting");
            Runtime.getRuntime().halt(1);
         }

      }

   }
}
