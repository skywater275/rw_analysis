package com.codedisaster.steamworks;


public enum SteamResult {

   OK("OK", 0, 1),
   Fail("Fail", 1, 2),
   NoConnection("NoConnection", 2, 3),
   InvalidPassword("InvalidPassword", 3, 5),
   LoggedInElsewhere("LoggedInElsewhere", 4, 6),
   InvalidProtocolVer("InvalidProtocolVer", 5, 7),
   InvalidParam("InvalidParam", 6, 8),
   FileNotFound("FileNotFound", 7, 9),
   Busy("Busy", 8, 10),
   InvalidState("InvalidState", 9, 11),
   InvalidName("InvalidName", 10, 12),
   InvalidEmail("InvalidEmail", 11, 13),
   DuplicateName("DuplicateName", 12, 14),
   AccessDenied("AccessDenied", 13, 15),
   Timeout("Timeout", 14, 16),
   Banned("Banned", 15, 17),
   AccountNotFound("AccountNotFound", 16, 18),
   InvalidSteamID("InvalidSteamID", 17, 19),
   ServiceUnavailable("ServiceUnavailable", 18, 20),
   NotLoggedOn("NotLoggedOn", 19, 21),
   Pending("Pending", 20, 22),
   EncryptionFailure("EncryptionFailure", 21, 23),
   InsufficientPrivilege("InsufficientPrivilege", 22, 24),
   LimitExceeded("LimitExceeded", 23, 25),
   Revoked("Revoked", 24, 26),
   Expired("Expired", 25, 27),
   AlreadyRedeemed("AlreadyRedeemed", 26, 28),
   DuplicateRequest("DuplicateRequest", 27, 29),
   AlreadyOwned("AlreadyOwned", 28, 30),
   IPNotFound("IPNotFound", 29, 31),
   PersistFailed("PersistFailed", 30, 32),
   LockingFailed("LockingFailed", 31, 33),
   LogonSessionReplaced("LogonSessionReplaced", 32, 34),
   ConnectFailed("ConnectFailed", 33, 35),
   HandshakeFailed("HandshakeFailed", 34, 36),
   IOFailure("IOFailure", 35, 37),
   RemoteDisconnect("RemoteDisconnect", 36, 38),
   ShoppingCartNotFound("ShoppingCartNotFound", 37, 39),
   Blocked("Blocked", 38, 40),
   Ignored("Ignored", 39, 41),
   NoMatch("NoMatch", 40, 42),
   AccountDisabled("AccountDisabled", 41, 43),
   ServiceReadOnly("ServiceReadOnly", 42, 44),
   AccountNotFeatured("AccountNotFeatured", 43, 45),
   AdministratorOK("AdministratorOK", 44, 46),
   ContentVersion("ContentVersion", 45, 47),
   TryAnotherCM("TryAnotherCM", 46, 48),
   PasswordRequiredToKickSession("PasswordRequiredToKickSession", 47, 49),
   AlreadyLoggedInElsewhere("AlreadyLoggedInElsewhere", 48, 50),
   Suspended("Suspended", 49, 51),
   Cancelled("Cancelled", 50, 52),
   DataCorruption("DataCorruption", 51, 53),
   DiskFull("DiskFull", 52, 54),
   RemoteCallFailed("RemoteCallFailed", 53, 55),
   PasswordUnset("PasswordUnset", 54, 56),
   ExternalAccountUnlinked("ExternalAccountUnlinked", 55, 57),
   PSNTicketInvalid("PSNTicketInvalid", 56, 58),
   ExternalAccountAlreadyLinked("ExternalAccountAlreadyLinked", 57, 59),
   RemoteFileConflict("RemoteFileConflict", 58, 60),
   IllegalPassword("IllegalPassword", 59, 61),
   SameAsPreviousValue("SameAsPreviousValue", 60, 62),
   AccountLogonDenied("AccountLogonDenied", 61, 63),
   CannotUseOldPassword("CannotUseOldPassword", 62, 64),
   InvalidLoginAuthCode("InvalidLoginAuthCode", 63, 65),
   AccountLogonDeniedNoMail("AccountLogonDeniedNoMail", 64, 66),
   HardwareNotCapableOfIPT("HardwareNotCapableOfIPT", 65, 67),
   IPTInitError("IPTInitError", 66, 68),
   ParentalControlRestricted("ParentalControlRestricted", 67, 69),
   FacebookQueryError("FacebookQueryError", 68, 70),
   ExpiredLoginAuthCode("ExpiredLoginAuthCode", 69, 71),
   IPLoginRestrictionFailed("IPLoginRestrictionFailed", 70, 72),
   AccountLockedDown("AccountLockedDown", 71, 73),
   AccountLogonDeniedVerifiedEmailRequired("AccountLogonDeniedVerifiedEmailRequired", 72, 74),
   NoMatchingURL("NoMatchingURL", 73, 75),
   BadResponse("BadResponse", 74, 76),
   RequirePasswordReEntry("RequirePasswordReEntry", 75, 77),
   ValueOutOfRange("ValueOutOfRange", 76, 78),
   UnexpectedError("UnexpectedError", 77, 79),
   Disabled("Disabled", 78, 80),
   InvalidCEGSubmission("InvalidCEGSubmission", 79, 81),
   RestrictedDevice("RestrictedDevice", 80, 82),
   RegionLocked("RegionLocked", 81, 83),
   RateLimitExceeded("RateLimitExceeded", 82, 84),
   AccountLoginDeniedNeedTwoFactor("AccountLoginDeniedNeedTwoFactor", 83, 85),
   ItemDeleted("ItemDeleted", 84, 86),
   AccountLoginDeniedThrottle("AccountLoginDeniedThrottle", 85, 87),
   TwoFactorCodeMismatch("TwoFactorCodeMismatch", 86, 88),
   TwoFactorActivationCodeMismatch("TwoFactorActivationCodeMismatch", 87, 89),
   AccountAssociatedToMultiplePartners("AccountAssociatedToMultiplePartners", 88, 90),
   NotModified("NotModified", 89, 91),
   NoMobileDevice("NoMobileDevice", 90, 92),
   TimeNotSynced("TimeNotSynced", 91, 93),
   SmsCodeFailed("SmsCodeFailed", 92, 94),
   AccountLimitExceeded("AccountLimitExceeded", 93, 95),
   AccountActivityLimitExceeded("AccountActivityLimitExceeded", 94, 96),
   PhoneActivityLimitExceeded("PhoneActivityLimitExceeded", 95, 97),
   RefundToWallet("RefundToWallet", 96, 98),
   EmailSendFailure("EmailSendFailure", 97, 99),
   NotSettled("NotSettled", 98, 100),
   NeedCaptcha("NeedCaptcha", 99, 101),
   GSLTDenied("GSLTDenied", 100, 102),
   GSOwnerDenied("GSOwnerDenied", 101, 103),
   InvalidItemType("InvalidItemType", 102, 104),
   IPBanned("IPBanned", 103, 105),
   GLSTExpired("GLSTExpired", 104, 106),
   UnknownErrorCode_NotImplementedByAPI("UnknownErrorCode_NotImplementedByAPI", 105, 0);
   private final int code;
   private static final SteamResult[] valuesLookupTable;
   // $FF: synthetic field
   private static final SteamResult[] $VALUES = new SteamResult[]{OK, Fail, NoConnection, InvalidPassword, LoggedInElsewhere, InvalidProtocolVer, InvalidParam, FileNotFound, Busy, InvalidState, InvalidName, InvalidEmail, DuplicateName, AccessDenied, Timeout, Banned, AccountNotFound, InvalidSteamID, ServiceUnavailable, NotLoggedOn, Pending, EncryptionFailure, InsufficientPrivilege, LimitExceeded, Revoked, Expired, AlreadyRedeemed, DuplicateRequest, AlreadyOwned, IPNotFound, PersistFailed, LockingFailed, LogonSessionReplaced, ConnectFailed, HandshakeFailed, IOFailure, RemoteDisconnect, ShoppingCartNotFound, Blocked, Ignored, NoMatch, AccountDisabled, ServiceReadOnly, AccountNotFeatured, AdministratorOK, ContentVersion, TryAnotherCM, PasswordRequiredToKickSession, AlreadyLoggedInElsewhere, Suspended, Cancelled, DataCorruption, DiskFull, RemoteCallFailed, PasswordUnset, ExternalAccountUnlinked, PSNTicketInvalid, ExternalAccountAlreadyLinked, RemoteFileConflict, IllegalPassword, SameAsPreviousValue, AccountLogonDenied, CannotUseOldPassword, InvalidLoginAuthCode, AccountLogonDeniedNoMail, HardwareNotCapableOfIPT, IPTInitError, ParentalControlRestricted, FacebookQueryError, ExpiredLoginAuthCode, IPLoginRestrictionFailed, AccountLockedDown, AccountLogonDeniedVerifiedEmailRequired, NoMatchingURL, BadResponse, RequirePasswordReEntry, ValueOutOfRange, UnexpectedError, Disabled, InvalidCEGSubmission, RestrictedDevice, RegionLocked, RateLimitExceeded, AccountLoginDeniedNeedTwoFactor, ItemDeleted, AccountLoginDeniedThrottle, TwoFactorCodeMismatch, TwoFactorActivationCodeMismatch, AccountAssociatedToMultiplePartners, NotModified, NoMobileDevice, TimeNotSynced, SmsCodeFailed, AccountLimitExceeded, AccountActivityLimitExceeded, PhoneActivityLimitExceeded, RefundToWallet, EmailSendFailure, NotSettled, NeedCaptcha, GSLTDenied, GSOwnerDenied, InvalidItemType, IPBanned, GLSTExpired, UnknownErrorCode_NotImplementedByAPI};


   private SteamResult(String var1, int var2, int var3) {
      this.code = var3;
   }

   public static SteamResult byValue(int var0) {
      return var0 < valuesLookupTable.length?valuesLookupTable[var0]:UnknownErrorCode_NotImplementedByAPI;
   }

   static {
      SteamResult[] var0 = values();
      int var1 = 0;
      SteamResult[] var2 = var0;
      int var3 = var0.length;

      int var4;
      SteamResult var5;
      for(var4 = 0; var4 < var3; ++var4) {
         var5 = var2[var4];
         var1 = Math.max(var1, var5.code);
      }

      valuesLookupTable = new SteamResult[var1 + 1];
      var2 = var0;
      var3 = var0.length;

      for(var4 = 0; var4 < var3; ++var4) {
         var5 = var2[var4];
         valuesLookupTable[var5.code] = var5;
      }

   }
}
