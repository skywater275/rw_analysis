package com.codedisaster.steamworks;


public enum SteamAuth$UserHasLicenseForAppResult {

   HasLicense("HasLicense", 0),
   DoesNotHaveLicense("DoesNotHaveLicense", 1),
   NoAuth("NoAuth", 2);
   private static final SteamAuth$UserHasLicenseForAppResult[] values = values();
   // $FF: synthetic field
   private static final SteamAuth$UserHasLicenseForAppResult[] $VALUES = new SteamAuth$UserHasLicenseForAppResult[]{HasLicense, DoesNotHaveLicense, NoAuth};


   private SteamAuth$UserHasLicenseForAppResult(String var1, int var2) {}

   static SteamAuth$UserHasLicenseForAppResult byOrdinal(int var0) {
      return values[var0];
   }

}
