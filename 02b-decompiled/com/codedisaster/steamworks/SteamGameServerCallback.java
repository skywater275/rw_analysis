package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAuth$AuthSessionResponse;
import com.codedisaster.steamworks.SteamGameServer$DenyReason;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamResult;

public interface SteamGameServerCallback {

   void onValidateAuthTicketResponse(SteamID var1, SteamAuth$AuthSessionResponse var2, SteamID var3);

   void onSteamServersConnected();

   void onSteamServerConnectFailure(SteamResult var1, boolean var2);

   void onSteamServersDisconnected(SteamResult var1);

   void onClientApprove(SteamID var1, SteamID var2);

   void onClientDeny(SteamID var1, SteamGameServer$DenyReason var2, String var3);

   void onClientKick(SteamID var1, SteamGameServer$DenyReason var2);

   void onClientGroupStatus(SteamID var1, SteamID var2, boolean var3, boolean var4);

   void onAssociateWithClanResult(SteamResult var1);

   void onComputeNewPlayerCompatibilityResult(SteamResult var1, int var2, int var3, int var4, SteamID var5);
}
