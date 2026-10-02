package com.corrodinggames.rts.java.c;

import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamNetworking$P2PSessionError;
import com.codedisaster.steamworks.SteamNetworkingCallback;
import com.corrodinggames.rts.java.c.b;
import com.corrodinggames.rts.java.c.k;
import java.io.IOException;

public class f implements SteamNetworkingCallback {

   b a;


   public f(b var1) {
      this.a = var1;
   }

   public void onP2PSessionConnectFail(SteamID var1, SteamNetworking$P2PSessionError var2) {
      com.corrodinggames.rts.gameFramework.l.e("onP2PSessionConnectFail:" + var2);
      k var3 = (k)this.a.l.get(var1);
      if(var3 != null && !var3.isClosed()) {
         com.corrodinggames.rts.gameFramework.l.e("onP2PSessionConnectFail: closing active socket");

         try {
            var3.close();
         } catch (IOException var5) {
            var5.printStackTrace();
         }
      }

   }

   public void onP2PSessionRequest(SteamID var1) {
      com.corrodinggames.rts.gameFramework.l.e("onP2PSessionRequest:" + var1);
      this.a.h.acceptP2PSessionWithUser(var1);
   }
}
