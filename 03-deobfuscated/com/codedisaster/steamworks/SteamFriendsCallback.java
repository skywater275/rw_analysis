/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamFriends$PersonaChange;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamResult;

public interface SteamFriendsCallback {
    public void onSetPersonaNameResponse(boolean var1, boolean var2, SteamResult var3);

    public void onPersonaStateChange(SteamID var1, SteamFriends$PersonaChange var2);

    public void onGameOverlayActivated(boolean var1);

    public void onGameLobbyJoinRequested(SteamID var1, SteamID var2);

    public void onAvatarImageLoaded(SteamID var1, int var2, int var3, int var4);

    public void onFriendRichPresenceUpdate(SteamID var1, int var2);

    public void onGameRichPresenceJoinRequested(SteamID var1, String var2);
}
