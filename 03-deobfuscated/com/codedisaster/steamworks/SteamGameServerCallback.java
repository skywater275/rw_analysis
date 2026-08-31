/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAuth$AuthSessionResponse;
import com.codedisaster.steamworks.SteamGameServer$DenyReason;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamResult;

public interface SteamGameServerCallback {
    public void onValidateAuthTicketResponse(SteamID var1, SteamAuth$AuthSessionResponse var2, SteamID var3);

    public void onSteamServersConnected();

    public void onSteamServerConnectFailure(SteamResult var1, boolean var2);

    public void onSteamServersDisconnected(SteamResult var1);

    public void onClientApprove(SteamID var1, SteamID var2);

    public void onClientDeny(SteamID var1, SteamGameServer$DenyReason var2, String var3);

    public void onClientKick(SteamID var1, SteamGameServer$DenyReason var2);

    public void onClientGroupStatus(SteamID var1, SteamID var2, boolean var3, boolean var4);

    public void onAssociateWithClanResult(SteamResult var1);

    public void onComputeNewPlayerCompatibilityResult(SteamResult var1, int var2, int var3, int var4, SteamID var5);
}
