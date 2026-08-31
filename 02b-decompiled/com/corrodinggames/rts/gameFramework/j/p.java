package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.aq;
import com.corrodinggames.rts.gameFramework.j.n;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

class p implements Runnable {

   public void run() {
      com.corrodinggames.rts.gameFramework.l.aq();
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.l.b("GetOwnInfoRunnable", "Starting getOwnInfoFromMasterServer");

      try {
         ArrayList var2 = new ArrayList(2);
         var2.add(new BasicNameValuePair("action", "self_info"));
         n.a(var2, "port", Integer.toString(var1.bX.m));
         n.a(var2, "id", var1.bX.aS);
         aq.a.b(var1.bX.aS, var2);
         aq.a.c(var1.bX.aS, var2);
         BufferedReader var3 = n.a((List)var2);
         String var4 = var3.readLine();
         if(var4 == null || !var4.contains("CORRODINGGAMES")) {
            com.corrodinggames.rts.gameFramework.l.b("GetOwnInfoRunnable", "Error bad header returned from the master server: " + var4);
            return;
         }

         String var5;
         while((var5 = var3.readLine()) != null) {
            String[] var6 = var5.split(",");
            if(var6.length <= 1) {
               com.corrodinggames.rts.gameFramework.l.b("GetOwnInfoRunnable", "columns.length too short at:" + var6.length);
            } else {
               String var7 = var6[0];
               String var8 = var6[1];

               try {
                  com.corrodinggames.rts.gameFramework.l.b("GetOwnInfoRunnable", "got info");
                  var1.bX.a(true, var7, Boolean.valueOf(Boolean.parseBoolean(var8)));
               } catch (NumberFormatException var10) {
                  com.corrodinggames.rts.gameFramework.l.b("GetOwnInfoRunnable", "failed to load server");
                  var10.printStackTrace();
               }
            }
         }

         com.corrodinggames.rts.appFramework.p.l();
         com.corrodinggames.rts.gameFramework.l.b("GetOwnInfoRunnable", "Completed load from master server without error");
      } catch (ClientProtocolException var11) {
         var1.bX.a(false, (String)null, (Boolean)null);
         var11.printStackTrace();
      } catch (IOException var12) {
         var1.bX.a(false, (String)null, (Boolean)null);
         var12.printStackTrace();
      } catch (Exception var13) {
         var1.bX.a(false, (String)null, (Boolean)null);
         com.corrodinggames.rts.gameFramework.l.a("GetOwnInfoRunnable Failed", var13);
      }

   }
}
