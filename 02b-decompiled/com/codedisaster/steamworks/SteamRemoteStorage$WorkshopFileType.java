package com.codedisaster.steamworks;


public enum SteamRemoteStorage$WorkshopFileType {

   Community("Community", 0),
   Microtransaction("Microtransaction", 1),
   Collection("Collection", 2),
   Art("Art", 3),
   Video("Video", 4),
   Screenshot("Screenshot", 5),
   Game("Game", 6),
   Software("Software", 7),
   Concept("Concept", 8),
   WebGuide("WebGuide", 9),
   IntegratedGuide("IntegratedGuide", 10),
   Merch("Merch", 11),
   ControllerBinding("ControllerBinding", 12),
   SteamworksAccessInvite("SteamworksAccessInvite", 13),
   SteamVideo("SteamVideo", 14),
   GameManagedItem("GameManagedItem", 15);
   private static final SteamRemoteStorage$WorkshopFileType[] values = values();
   // $FF: synthetic field
   private static final SteamRemoteStorage$WorkshopFileType[] $VALUES = new SteamRemoteStorage$WorkshopFileType[]{Community, Microtransaction, Collection, Art, Video, Screenshot, Game, Software, Concept, WebGuide, IntegratedGuide, Merch, ControllerBinding, SteamworksAccessInvite, SteamVideo, GameManagedItem};


   private SteamRemoteStorage$WorkshopFileType(String var1, int var2) {}

   static SteamRemoteStorage$WorkshopFileType byOrdinal(int var0) {
      return values[var0];
   }

}
