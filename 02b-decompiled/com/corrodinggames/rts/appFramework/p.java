package com.corrodinggames.rts.appFramework;

import android.os.Handler;
import com.corrodinggames.rts.appFramework.b;
import com.corrodinggames.rts.appFramework.p$1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class p extends b {

   static p c;
   final Handler d;
   private Runnable e;


   public static void l() {
      if(c != null) {
         c.d.a(c.e);
      }

   }

   public static ArrayList m() {
      Object var0 = com.corrodinggames.rts.gameFramework.j.n.f;
      synchronized(com.corrodinggames.rts.gameFramework.j.n.f) {
         com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
         ArrayList var2 = new ArrayList();
         Iterator var3 = var1.bX.bi.iterator();

         while(var3.hasNext()) {
            com.corrodinggames.rts.gameFramework.j.g var4 = (com.corrodinggames.rts.gameFramework.j.g)var3.next();
            var2.add(var4);
         }

         Collections.sort(var2, new p$1());
         return var2;
      }
   }
}
