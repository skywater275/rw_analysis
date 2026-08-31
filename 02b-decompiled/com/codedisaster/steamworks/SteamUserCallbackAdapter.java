package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAuth$AuthSessionResponse;
import com.codedisaster.steamworks.SteamCallbackAdapter;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamUserCallback;

class SteamUserCallbackAdapter extends SteamCallbackAdapter {

   SteamUserCallbackAdapter(SteamUserCallback var1) {
      super(var1);
   }

   void onValidateAuthTicket(long var1, int var3, long var4) {
      ((SteamUserCallback)this.callback).onValidateAuthTicket(new SteamID(var1), SteamAuth$AuthSessionResponse.byOrdinal(var3), new SteamID(var4));
   }

   void onMicroTxnAuthorization(int var1, long var2, boolean var4) {
      ((SteamUserCallback)this.callback).onMicroTxnAuthorization(var1, var2, var4);
   }
}
