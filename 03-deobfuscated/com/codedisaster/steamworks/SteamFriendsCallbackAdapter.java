/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamCallbackAdapter;
import com.codedisaster.steamworks.SteamFriends$PersonaChange;
import com.codedisaster.steamworks.SteamFriendsCallback;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamResult;

class SteamFriendsCallbackAdapter
extends SteamCallbackAdapter {
    private static final SteamFriends$PersonaChange[] personaChangeValues = SteamFriends$PersonaChange.values();

    SteamFriendsCallbackAdapter(SteamFriendsCallback steamFriendsCallback) {
        super(steamFriendsCallback);
    }

    void onSetPersonaNameResponse(boolean bl, boolean bl2, int n) {
        ((SteamFriendsCallback)this.callback).onSetPersonaNameResponse(bl, bl2, SteamResult.byValue(n));
    }

    void onPersonaStateChange(long l, int n) {
        SteamID steamID = new SteamID(l);
        for (SteamFriends$PersonaChange steamFriends$PersonaChange : personaChangeValues) {
            if (!SteamFriends$PersonaChange.isSet(steamFriends$PersonaChange, n)) continue;
            ((SteamFriendsCallback)this.callback).onPersonaStateChange(steamID, steamFriends$PersonaChange);
        }
    }

    void onGameOverlayActivated(boolean bl) {
        ((SteamFriendsCallback)this.callback).onGameOverlayActivated(bl);
    }

    void onGameLobbyJoinRequested(long l, long l2) {
        ((SteamFriendsCallback)this.callback).onGameLobbyJoinRequested(new SteamID(l), new SteamID(l2));
    }

    void onAvatarImageLoaded(long l, int n, int n2, int n3) {
        ((SteamFriendsCallback)this.callback).onAvatarImageLoaded(new SteamID(l), n, n2, n3);
    }

    void onFriendRichPresenceUpdate(long l, int n) {
        ((SteamFriendsCallback)this.callback).onFriendRichPresenceUpdate(new SteamID(l), n);
    }

    void onGameRichPresenceJoinRequested(long l, String string) {
        ((SteamFriendsCallback)this.callback).onGameRichPresenceJoinRequested(new SteamID(l), string);
    }
}
