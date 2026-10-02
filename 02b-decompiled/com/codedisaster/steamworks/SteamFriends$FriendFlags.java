package com.codedisaster.steamworks;

import java.util.Collection;
import java.util.Iterator;

public enum SteamFriends$FriendFlags {

   None("None", 0, 0),
   Blocked("Blocked", 1, 1),
   FriendshipRequested("FriendshipRequested", 2, 2),
   Immediate("Immediate", 3, 4),
   ClanMember("ClanMember", 4, 8),
   OnGameServer("OnGameServer", 5, 16),
   RequestingFriendship("RequestingFriendship", 6, 128),
   RequestingInfo("RequestingInfo", 7, 256),
   Ignored("Ignored", 8, 512),
   IgnoredFriend("IgnoredFriend", 9, 1024),
   ChatMember("ChatMember", 10, 4096),
   All("All", 11, '\uffff');
   private final int bits;
   // $FF: synthetic field
   private static final SteamFriends$FriendFlags[] $VALUES = new SteamFriends$FriendFlags[]{None, Blocked, FriendshipRequested, Immediate, ClanMember, OnGameServer, RequestingFriendship, RequestingInfo, Ignored, IgnoredFriend, ChatMember, All};


   private SteamFriends$FriendFlags(String var1, int var2, int var3) {
      this.bits = var3;
   }

   static int asBits(Collection var0) {
      int var1 = 0;

      SteamFriends$FriendFlags var3;
      for(Iterator var2 = var0.iterator(); var2.hasNext(); var1 |= var3.bits) {
         var3 = (SteamFriends$FriendFlags)var2.next();
      }

      return var1;
   }

   // $FF: synthetic method
   static int access$000(SteamFriends$FriendFlags var0) {
      return var0.bits;
   }

}
