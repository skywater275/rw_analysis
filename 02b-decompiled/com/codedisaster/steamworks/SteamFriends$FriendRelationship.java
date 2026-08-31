package com.codedisaster.steamworks;


public enum SteamFriends$FriendRelationship {

   None("None", 0),
   Blocked("Blocked", 1),
   Recipient("Recipient", 2),
   Friend("Friend", 3),
   RequestInitiator("RequestInitiator", 4),
   Ignored("Ignored", 5),
   IgnoredFriend("IgnoredFriend", 6),
   Suggested_DEPRECATED("Suggested_DEPRECATED", 7),
   Max("Max", 8);
   private static final SteamFriends$FriendRelationship[] values = values();
   // $FF: synthetic field
   private static final SteamFriends$FriendRelationship[] $VALUES = new SteamFriends$FriendRelationship[]{None, Blocked, Recipient, Friend, RequestInitiator, Ignored, IgnoredFriend, Suggested_DEPRECATED, Max};


   private SteamFriends$FriendRelationship(String var1, int var2) {}

   static SteamFriends$FriendRelationship byOrdinal(int var0) {
      return values[var0];
   }

}
