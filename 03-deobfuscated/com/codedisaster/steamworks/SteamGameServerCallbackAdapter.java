/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAuth$AuthSessionResponse;
import com.codedisaster.steamworks.SteamCallbackAdapter;
import com.codedisaster.steamworks.SteamGameServer$DenyReason;
import com.codedisaster.steamworks.SteamGameServerCallback;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamResult;

class SteamGameServerCallbackAdapter
extends SteamCallbackAdapter {
    SteamGameServerCallbackAdapter(SteamGameServerCallback steamGameServerCallback) {
        super(steamGameServerCallback);
    }

    void onValidateAuthTicketResponse(long l, int n, long l2) {
        ((SteamGameServerCallback)this.callback).onValidateAuthTicketResponse(new SteamID(l), SteamAuth$AuthSessionResponse.byOrdinal(n), new SteamID(l2));
    }

    void onSteamServersConnected() {
        ((SteamGameServerCallback)this.callback).onSteamServersConnected();
    }

    void onSteamServerConnectFailure(int n, boolean bl) {
        ((SteamGameServerCallback)this.callback).onSteamServerConnectFailure(SteamResult.byValue(n), bl);
    }

    void onSteamServersDisconnected(int n) {
        ((SteamGameServerCallback)this.callback).onSteamServersDisconnected(SteamResult.byValue(n));
    }

    void onClientApprove(long l, long l2) {
        ((SteamGameServerCallback)this.callback).onClientApprove(new SteamID(l), new SteamID(l2));
    }

    void onClientDeny(long l, int n, String string) {
        ((SteamGameServerCallback)this.callback).onClientDeny(new SteamID(l), SteamGameServer$DenyReason.byOrdinal(n), string);
    }

    void onClientKick(long l, int n) {
        ((SteamGameServerCallback)this.callback).onClientKick(new SteamID(l), SteamGameServer$DenyReason.byOrdinal(n));
    }

    void onClientGroupStatus(long l, long l2, boolean bl, boolean bl2) {
        ((SteamGameServerCallback)this.callback).onClientGroupStatus(new SteamID(l), new SteamID(l2), bl, bl2);
    }

    void onAssociateWithClanResult(int n) {
        ((SteamGameServerCallback)this.callback).onAssociateWithClanResult(SteamResult.byValue(n));
    }

    void onComputeNewPlayerCompatibilityResult(int n, int n2, int n3, int n4, long l) {
        ((SteamGameServerCallback)this.callback).onComputeNewPlayerCompatibilityResult(SteamResult.byValue(n), n2, n3, n4, new SteamID(l));
    }
}
