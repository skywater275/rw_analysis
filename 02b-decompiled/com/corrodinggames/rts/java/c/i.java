package com.corrodinggames.rts.java.c;

import com.codedisaster.steamworks.SteamPublishedFileID;
import com.codedisaster.steamworks.SteamResult;

public class i {

   SteamPublishedFileID a;
   Runnable b;


   public void a(SteamResult var1) {
      com.corrodinggames.rts.gameFramework.l.b("PendingDownload onFinish for: " + this.a);
      if(this.b != null) {
         this.b.run();
      }

   }
}
