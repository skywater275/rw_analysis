/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.input;

import com.codedisaster.steamworks.SteamFriends$PersonaChange;
import com.codedisaster.steamworks.SteamFriendsCallback;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamResult;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.java.input.SteamManager;

public class d
implements SteamFriendsCallback {
    SteamManager a;

    public d(SteamManager b2) {
        this.a = b2;
    }

    @Override
    public void onSetPersonaNameResponse(boolean bl, boolean bl2, SteamResult steamResult) {
    }

    @Override
    public void onPersonaStateChange(SteamID steamID, SteamFriends$PersonaChange personaChange) {
    }

    @Override
    public void onGameOverlayActivated(boolean bl) {
        GlobalState.e("onGameOverlayActivated");
    }

    @Override
    public void onGameLobbyJoinRequested(SteamID steamID, SteamID steamID2) {
    }

    @Override
    public void onAvatarImageLoaded(SteamID steamID, int n2, int n3, int n4) {
    }

    @Override
    public void onFriendRichPresenceUpdate(SteamID steamID, int n2) {
    }

    @Override
    public void onGameRichPresenceJoinRequested(SteamID steamID, String string) {
    }
}
