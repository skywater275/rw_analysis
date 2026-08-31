package com.codedisaster.steamworks;


public enum SteamNetworking$P2PSend {

   Unreliable("Unreliable", 0),
   UnreliableNoDelay("UnreliableNoDelay", 1),
   Reliable("Reliable", 2),
   ReliableWithBuffering("ReliableWithBuffering", 3);
   // $FF: synthetic field
   private static final SteamNetworking$P2PSend[] $VALUES = new SteamNetworking$P2PSend[]{Unreliable, UnreliableNoDelay, Reliable, ReliableWithBuffering};


   private SteamNetworking$P2PSend(String var1, int var2) {}

}
