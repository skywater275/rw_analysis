package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAPIWarningMessageHook;
import com.codedisaster.steamworks.SteamCallbackAdapter;
import com.codedisaster.steamworks.SteamUtilsCallback;

class SteamUtilsCallbackAdapter extends SteamCallbackAdapter {

   private SteamAPIWarningMessageHook messageHook;


   SteamUtilsCallbackAdapter(SteamUtilsCallback var1) {
      super(var1);
   }

   void setWarningMessageHook(SteamAPIWarningMessageHook var1) {
      this.messageHook = var1;
   }

   void onWarningMessage(int var1, String var2) {
      if(this.messageHook != null) {
         this.messageHook.onWarningMessage(var1, var2);
      }

   }

   void onSteamShutdown() {
      ((SteamUtilsCallback)this.callback).onSteamShutdown();
   }
}
