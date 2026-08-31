package android.graphics;


public enum Bitmap$CompressFormat {

   a("JPEG", 0, 0),
   b("PNG", 1, 1),
   c("WEBP", 2, 2);
   final int d;
   // $FF: synthetic field
   private static final Bitmap$CompressFormat[] e = new Bitmap$CompressFormat[]{a, b, c};


   private Bitmap$CompressFormat(String var1, int var2, int var3) {
      this.d = var3;
   }

}
