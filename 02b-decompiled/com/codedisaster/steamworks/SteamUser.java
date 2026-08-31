package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamAuth$BeginAuthSessionResult;
import com.codedisaster.steamworks.SteamAuth$UserHasLicenseForAppResult;
import com.codedisaster.steamworks.SteamAuthTicket;
import com.codedisaster.steamworks.SteamException;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamInterface;
import com.codedisaster.steamworks.SteamUserCallback;
import com.codedisaster.steamworks.SteamUserCallbackAdapter;
import java.nio.Buffer;

public class SteamUser extends SteamInterface {

   public SteamUser(SteamUserCallback var1) {
      super(SteamAPI.getSteamUserPointer(), createCallback(new SteamUserCallbackAdapter(var1)));
   }

   public SteamID getSteamID() {
      return new SteamID(getSteamID(this.pointer));
   }

   public SteamAuthTicket getAuthSessionTicket(Buffer var1, int[] var2) {
      if(!var1.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else {
         int var3 = getAuthSessionTicket(this.pointer, var1, var1.capacity(), var2);
         if((long)var3 != 0L) {
            var1.limit(var2[0]);
         }

         return new SteamAuthTicket((long)var3);
      }
   }

   public SteamAuth$BeginAuthSessionResult beginAuthSession(Buffer var1, SteamID var2) {
      if(!var1.isDirect()) {
         throw new SteamException("Direct buffer required!");
      } else {
         int var3 = beginAuthSession(this.pointer, var1, var1.limit(), var2.handle);
         return SteamAuth$BeginAuthSessionResult.byOrdinal(var3);
      }
   }

   public void endAuthSession(SteamID var1) {
      endAuthSession(this.pointer, var1.handle);
   }

   public void cancelAuthTicket(SteamAuthTicket var1) {
      cancelAuthTicket(this.pointer, (int)var1.handle);
   }

   public SteamAuth$UserHasLicenseForAppResult userHasLicenseForApp(SteamID var1, int var2) {
      return SteamAuth$UserHasLicenseForAppResult.byOrdinal(userHasLicenseForApp(this.pointer, var1.handle, var2));
   }

   private static native long createCallback(SteamUserCallbackAdapter var0);

   private static native long getSteamID(long var0);

   private static native int getAuthSessionTicket(long var0, Buffer var2, int var3, int[] var4);

   private static native int beginAuthSession(long var0, Buffer var2, int var3, long var4);

   private static native void endAuthSession(long var0, long var2);

   private static native void cancelAuthTicket(long var0, int var2);

   private static native int userHasLicenseForApp(long var0, long var2, int var4);
}
