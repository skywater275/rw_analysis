package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAuth$AuthSessionResponse;
import com.codedisaster.steamworks.SteamCallbackAdapter;
import com.codedisaster.steamworks.SteamGameServer$DenyReason;
import com.codedisaster.steamworks.SteamGameServerCallback;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamResult;

class SteamGameServerCallbackAdapter extends SteamCallbackAdapter {

   SteamGameServerCallbackAdapter(SteamGameServerCallback var1) {
      super(var1);
   }

   void onValidateAuthTicketResponse(long var1, int var3, long var4) {
      ((SteamGameServerCallback)this.callback).onValidateAuthTicketResponse(new SteamID(var1), SteamAuth$AuthSessionResponse.byOrdinal(var3), new SteamID(var4));
   }

   void onSteamServersConnected() {
      ((SteamGameServerCallback)this.callback).onSteamServersConnected();
   }

   void onSteamServerConnectFailure(int var1, boolean var2) {
      ((SteamGameServerCallback)this.callback).onSteamServerConnectFailure(SteamResult.byValue(var1), var2);
   }

   void onSteamServersDisconnected(int var1) {
      ((SteamGameServerCallback)this.callback).onSteamServersDisconnected(SteamResult.byValue(var1));
   }

   void onClientApprove(long var1, long var3) {
      ((SteamGameServerCallback)this.callback).onClientApprove(new SteamID(var1), new SteamID(var3));
   }

   void onClientDeny(long var1, int var3, String var4) {
      ((SteamGameServerCallback)this.callback).onClientDeny(new SteamID(var1), SteamGameServer$DenyReason.byOrdinal(var3), var4);
   }

   void onClientKick(long var1, int var3) {
      ((SteamGameServerCallback)this.callback).onClientKick(new SteamID(var1), SteamGameServer$DenyReason.byOrdinal(var3));
   }

   void onClientGroupStatus(long var1, long var3, boolean var5, boolean var6) {
      ((SteamGameServerCallback)this.callback).onClientGroupStatus(new SteamID(var1), new SteamID(var3), var5, var6);
   }

   void onAssociateWithClanResult(int var1) {
      ((SteamGameServerCallback)this.callback).onAssociateWithClanResult(SteamResult.byValue(var1));
   }

   void onComputeNewPlayerCompatibilityResult(int var1, int var2, int var3, int var4, long var5) {
      ((SteamGameServerCallback)this.callback).onComputeNewPlayerCompatibilityResult(SteamResult.byValue(var1), var2, var3, var4, new SteamID(var5));
   }
}
