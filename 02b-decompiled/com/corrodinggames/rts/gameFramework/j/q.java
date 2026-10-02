package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.n;
import com.corrodinggames.rts.gameFramework.j.o;
import com.corrodinggames.rts.gameFramework.j.q$1;
import java.util.ArrayList;
import java.util.Timer;
import org.apache.http.message.BasicNameValuePair;

class q implements Runnable {

   Runnable a;


   q(Runnable var1) {
      this.a = var1;
   }

   public void run() {
      com.corrodinggames.rts.gameFramework.l.aq();
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.l.b("LoadFromMasterServer", "Starting load");
      int var2 = n.e++;

      try {
         Timer var3 = new Timer();
         var3.schedule(new o(var2), 5000L);
         ArrayList var4 = new ArrayList(2);
         var4.add(new BasicNameValuePair("action", "list"));
         var4.add(new BasicNameValuePair("game_version", Integer.toString(var1.c(true))));
         var4.add(new BasicNameValuePair("game_version_beta", com.corrodinggames.rts.gameFramework.f.a(var1.n())));
         boolean var5 = false;
         n.a(var4, false, new q$1(this, var2, var1));
      } catch (Exception var6) {
         var6.printStackTrace();
         var1.a("Error getting game list from server", 1);
      }

   }
}
