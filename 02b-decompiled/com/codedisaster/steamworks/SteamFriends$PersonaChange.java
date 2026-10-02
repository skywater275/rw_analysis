package com.codedisaster.steamworks;


public enum SteamFriends$PersonaChange {

   Name("Name", 0, 1),
   Status("Status", 1, 2),
   ComeOnline("ComeOnline", 2, 4),
   GoneOffline("GoneOffline", 3, 8),
   GamePlayed("GamePlayed", 4, 16),
   GameServer("GameServer", 5, 32),
   Avatar("Avatar", 6, 64),
   JoinedSource("JoinedSource", 7, 128),
   LeftSource("LeftSource", 8, 256),
   RelationshipChanged("RelationshipChanged", 9, 512),
   NameFirstSet("NameFirstSet", 10, 1024),
   FacebookInfo("FacebookInfo", 11, 2048),
   Nickname("Nickname", 12, 4096),
   SteamLevel("SteamLevel", 13, 8192);
   private final int bits;
   // $FF: synthetic field
   private static final SteamFriends$PersonaChange[] $VALUES = new SteamFriends$PersonaChange[]{Name, Status, ComeOnline, GoneOffline, GamePlayed, GameServer, Avatar, JoinedSource, LeftSource, RelationshipChanged, NameFirstSet, FacebookInfo, Nickname, SteamLevel};


   private SteamFriends$PersonaChange(String var1, int var2, int var3) {
      this.bits = var3;
   }

   static boolean isSet(SteamFriends$PersonaChange var0, int var1) {
      return (var0.bits & var1) == var0.bits;
   }

}
