package android.graphics;


public enum Canvas$VertexMode {

   a("TRIANGLES", 0, 0),
   b("TRIANGLE_STRIP", 1, 1),
   c("TRIANGLE_FAN", 2, 2);
   public final int d;
   // $FF: synthetic field
   private static final Canvas$VertexMode[] e = new Canvas$VertexMode[]{a, b, c};


   private Canvas$VertexMode(String var1, int var2, int var3) {
      this.d = var3;
   }

}
