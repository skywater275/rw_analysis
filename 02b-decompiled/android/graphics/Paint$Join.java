package android.graphics;


public enum Paint$Join {

   a("MITER", 0, 0),
   b("ROUND", 1, 1),
   c("BEVEL", 2, 2);
   final int d;
   // $FF: synthetic field
   private static final Paint$Join[] e = new Paint$Join[]{a, b, c};


   private Paint$Join(String var1, int var2, int var3) {
      this.d = var3;
   }

}
