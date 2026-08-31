package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.y;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

final class l$4 implements com.corrodinggames.rts.gameFramework.utility.e {

   public strictfp void a(com.corrodinggames.rts.gameFramework.utility.a var1) {
      if(l.dT) {
         l.b("activeANRWatchDog: ANR already detected");
      }

      l.dT = true;
      l.b("activeANRWatchDog: ANR detected");
      String var2 = l.a((Throwable)var1);
      com.corrodinggames.rts.gameFramework.j.n.a("detectedANR", var2);

      try {
         Thread.sleep(400L);
      } catch (InterruptedException var7) {
         var7.printStackTrace();
      }

      try {
         File var3 = y.a("lastFreeze", "", true);
         FileOutputStream var4 = new FileOutputStream(var3);
         PrintStream var5 = new PrintStream(var4);
         var5.print(var2);
         var5.close();
         var4.close();
      } catch (IOException var6) {
         throw new RuntimeException(var6);
      }
   }
}
