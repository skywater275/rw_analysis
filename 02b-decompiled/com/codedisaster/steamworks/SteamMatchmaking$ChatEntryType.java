package com.codedisaster.steamworks;


public enum SteamMatchmaking$ChatEntryType {

   Invalid("Invalid", 0, 0),
   ChatMsg("ChatMsg", 1, 1),
   Typing("Typing", 2, 2),
   InviteGame("InviteGame", 3, 3),
   Emote("Emote", 4, 4),
   LeftConversation("LeftConversation", 5, 6),
   Entered("Entered", 6, 7),
   WasKicked("WasKicked", 7, 8),
   WasBanned("WasBanned", 8, 9),
   Disconnected("Disconnected", 9, 10),
   HistoricalChat("HistoricalChat", 10, 11),
   Reserved1("Reserved1", 11, 12),
   Reserved2("Reserved2", 12, 13),
   LinkBlocked("LinkBlocked", 13, 14);
   private final int code;
   private static final SteamMatchmaking$ChatEntryType[] values = values();
   // $FF: synthetic field
   private static final SteamMatchmaking$ChatEntryType[] $VALUES = new SteamMatchmaking$ChatEntryType[]{Invalid, ChatMsg, Typing, InviteGame, Emote, LeftConversation, Entered, WasKicked, WasBanned, Disconnected, HistoricalChat, Reserved1, Reserved2, LinkBlocked};


   private SteamMatchmaking$ChatEntryType(String var1, int var2, int var3) {
      this.code = var3;
   }

   static SteamMatchmaking$ChatEntryType byCode(int var0) {
      SteamMatchmaking$ChatEntryType[] var1 = values;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         SteamMatchmaking$ChatEntryType var4 = var1[var3];
         if(var4.code == var0) {
            return var4;
         }
      }

      return Invalid;
   }

}
