package com.codedisaster.steamworks;


public enum SteamHTTP$HTTPStatusCode {

   Invalid("Invalid", 0, 0),
   Continue("Continue", 1, 100),
   SwitchingProtocols("SwitchingProtocols", 2, 101),
   OK("OK", 3, 200),
   Created("Created", 4, 201),
   Accepted("Accepted", 5, 202),
   NonAuthoritative("NonAuthoritative", 6, 203),
   NoContent("NoContent", 7, 204),
   ResetContent("ResetContent", 8, 205),
   PartialContent("PartialContent", 9, 206),
   MultipleChoices("MultipleChoices", 10, 300),
   MovedPermanently("MovedPermanently", 11, 301),
   Found("Found", 12, 302),
   SeeOther("SeeOther", 13, 303),
   NotModified("NotModified", 14, 304),
   UseProxy("UseProxy", 15, 305),
   TemporaryRedirect("TemporaryRedirect", 16, 307),
   BadRequest("BadRequest", 17, 400),
   Unauthorized("Unauthorized", 18, 401),
   PaymentRequired("PaymentRequired", 19, 402),
   Forbidden("Forbidden", 20, 403),
   NotFound("NotFound", 21, 404),
   MethodNotAllowed("MethodNotAllowed", 22, 405),
   NotAcceptable("NotAcceptable", 23, 406),
   ProxyAuthRequired("ProxyAuthRequired", 24, 407),
   RequestTimeout("RequestTimeout", 25, 408),
   Conflict("Conflict", 26, 409),
   Gone("Gone", 27, 410),
   LengthRequired("LengthRequired", 28, 411),
   PreconditionFailed("PreconditionFailed", 29, 412),
   RequestEntityTooLarge("RequestEntityTooLarge", 30, 413),
   RequestURITooLong("RequestURITooLong", 31, 414),
   UnsupportedMediaType("UnsupportedMediaType", 32, 415),
   RequestedRangeNotSatisfiable("RequestedRangeNotSatisfiable", 33, 416),
   ExpectationFailed("ExpectationFailed", 34, 417),
   Unknown4xx("Unknown4xx", 35, 418),
   TooManyRequests("TooManyRequests", 36, 429),
   InternalServerError("InternalServerError", 37, 500),
   NotImplemented("NotImplemented", 38, 501),
   BadGateway("BadGateway", 39, 502),
   ServiceUnavailable("ServiceUnavailable", 40, 503),
   GatewayTimeout("GatewayTimeout", 41, 504),
   HTTPVersionNotSupported("HTTPVersionNotSupported", 42, 505),
   Unknown5xx("Unknown5xx", 43, 599);
   private final int code;
   private static final SteamHTTP$HTTPStatusCode[] values = values();
   // $FF: synthetic field
   private static final SteamHTTP$HTTPStatusCode[] $VALUES = new SteamHTTP$HTTPStatusCode[]{Invalid, Continue, SwitchingProtocols, OK, Created, Accepted, NonAuthoritative, NoContent, ResetContent, PartialContent, MultipleChoices, MovedPermanently, Found, SeeOther, NotModified, UseProxy, TemporaryRedirect, BadRequest, Unauthorized, PaymentRequired, Forbidden, NotFound, MethodNotAllowed, NotAcceptable, ProxyAuthRequired, RequestTimeout, Conflict, Gone, LengthRequired, PreconditionFailed, RequestEntityTooLarge, RequestURITooLong, UnsupportedMediaType, RequestedRangeNotSatisfiable, ExpectationFailed, Unknown4xx, TooManyRequests, InternalServerError, NotImplemented, BadGateway, ServiceUnavailable, GatewayTimeout, HTTPVersionNotSupported, Unknown5xx};


   private SteamHTTP$HTTPStatusCode(String var1, int var2, int var3) {
      this.code = var3;
   }

   static SteamHTTP$HTTPStatusCode byValue(int var0) {
      SteamHTTP$HTTPStatusCode[] var1 = values;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         SteamHTTP$HTTPStatusCode var4 = var1[var3];
         if(var4.code == var0) {
            return var4;
         }
      }

      return Invalid;
   }

}
