package android.graphics;


public enum Bitmap$Config {

   a("ALPHA_8", 0, 2),
   b("RGB_565", 1, 4),
   @Deprecated
   c("ARGB_4444", 2, 5),
   d("ARGB_8888", 3, 6);
   final int e;
   private static Bitmap$Config[] f = new Bitmap$Config[]{null, null, a, null, b, c, d};
   // $FF: synthetic field
   private static final Bitmap$Config[] g = new Bitmap$Config[]{a, b, c, d};


   private Bitmap$Config(String var1, int var2, int var3) {
      this.e = var3;
   }

   static Bitmap$Config a(int var0) {
      return f[var0];
   }

}
