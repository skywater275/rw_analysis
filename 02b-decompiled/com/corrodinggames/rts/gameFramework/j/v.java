package com.corrodinggames.rts.gameFramework.j;

import android.os.Build.VERSION;
import com.corrodinggames.rts.gameFramework.j.n;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class v implements Runnable {

   String a;
   String b;


   public void run() {
      long var1 = com.corrodinggames.rts.gameFramework.l.V();
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.l.b("SendErrorReport", "Starting");
      boolean var15 = false;

      label131: {
         float var19;
         label132: {
            label133: {
               try {
                  var15 = true;
                  ArrayList var4 = new ArrayList(2);
                  n.a(var4, "action", "error_report");
                  n.a(var4, "game_version", Integer.toString(var3.c(false)));
                  n.a(var4, "game_version_internal", Integer.toString(var3.c(true)));
                  n.a(var4, "game_version_string", var3.r());
                  n.a(var4, "package_name", var3.l());
                  n.a(var4, "installation_source", var3.m());
                  String var5 = "" + VERSION.SDK_INT;
                  if(com.corrodinggames.rts.gameFramework.l.av()) {
                     var5 = "s:0;";
                     if(com.corrodinggames.rts.gameFramework.o.a.a().e()) {
                        var5 = "s:1;";
                     }
                  }

                  String var6;
                  if(com.corrodinggames.rts.gameFramework.l.av() || com.corrodinggames.rts.gameFramework.l.aZ) {
                     var6 = System.getProperty("os.name") + " - " + System.getProperty("os.version");
                     n.a(var4, "system_version", var6);
                  }

                  n.a(var4, "sdk_version", var5);
                  n.a(var4, "device_model", var3.G());
                  n.a(var4, "build_version", var3.H());
                  n.a(var4, "release_version", com.corrodinggames.rts.gameFramework.f.a(com.corrodinggames.rts.gameFramework.l.as));
                  n.a(var4, "dedicated_server", com.corrodinggames.rts.gameFramework.f.a(com.corrodinggames.rts.gameFramework.l.aU));
                  var6 = "NA";
                  if(var3.bX != null) {
                     var6 = var3.bX.aR;
                  }

                  n.a(var4, "private_token", var6);
                  n.a(var4, "private_token_2", com.corrodinggames.rts.gameFramework.f.b(com.corrodinggames.rts.gameFramework.f.b(var6)));
                  n.a(var4, "message", this.a);
                  n.a(var4, "stacktrace", this.b);
                  com.corrodinggames.rts.gameFramework.l.b("SendErrorReport", "making request");
                  BufferedReader var7 = n.a((List)var4);
                  String var8 = var7.readLine();
                  if(var8 == null || !var8.contains("CORRODINGGAMES")) {
                     com.corrodinggames.rts.gameFramework.l.b("StartCreateOnMasterServer", "Error bad header returned from the master server: " + var8);
                     var15 = false;
                     break label131;
                  }

                  com.corrodinggames.rts.gameFramework.l.b("SendErrorReport", "Send trace successfully");
                  var15 = false;
                  break label133;
               } catch (IOException var16) {
                  var16.printStackTrace();
                  var15 = false;
                  break label132;
               } catch (Exception var17) {
                  var17.printStackTrace();
                  var15 = false;
               } finally {
                  if(var15) {
                     float var11 = (float)(com.corrodinggames.rts.gameFramework.l.V() - var1) / 1000000.0F;
                     com.corrodinggames.rts.gameFramework.l.b("SendErrorReport", "took: " + var11 + " seconds");
                  }
               }

               var19 = (float)(com.corrodinggames.rts.gameFramework.l.V() - var1) / 1000000.0F;
               com.corrodinggames.rts.gameFramework.l.b("SendErrorReport", "took: " + var19 + " seconds");
               return;
            }

            var19 = (float)(com.corrodinggames.rts.gameFramework.l.V() - var1) / 1000000.0F;
            com.corrodinggames.rts.gameFramework.l.b("SendErrorReport", "took: " + var19 + " seconds");
            return;
         }

         var19 = (float)(com.corrodinggames.rts.gameFramework.l.V() - var1) / 1000000.0F;
         com.corrodinggames.rts.gameFramework.l.b("SendErrorReport", "took: " + var19 + " seconds");
         return;
      }

      float var9 = (float)(com.corrodinggames.rts.gameFramework.l.V() - var1) / 1000000.0F;
      com.corrodinggames.rts.gameFramework.l.b("SendErrorReport", "took: " + var9 + " seconds");
   }
}
