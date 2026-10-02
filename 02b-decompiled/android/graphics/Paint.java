package android.graphics;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint$Align;
import android.graphics.Paint$Cap;
import android.graphics.Paint$FontMetrics;
import android.graphics.Paint$Join;
import android.graphics.Paint$Style;
import android.graphics.PathEffect;
import android.graphics.Rasterizer;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.TemporaryBuffer;
import android.graphics.Typeface;
import android.graphics.Xfermode;
import java.util.Locale;

public class Paint {

   public int a;
   private ColorFilter r;
   private MaskFilter s;
   private PathEffect t;
   private Rasterizer u;
   private Shader v;
   private Typeface w;
   private Xfermode x;
   private boolean y;
   private float z;
   private float A;
   private Locale B;
   public boolean b;
   public float c;
   public float d;
   public float e;
   public int f;
   public int g;
   static final Paint$Style[] h = new Paint$Style[]{Paint$Style.a, Paint$Style.b, Paint$Style.c};
   static final Paint$Cap[] i = new Paint$Cap[]{Paint$Cap.a, Paint$Cap.b, Paint$Cap.c};
   static final Paint$Join[] j = new Paint$Join[]{Paint$Join.a, Paint$Join.b, Paint$Join.c};
   static final Paint$Align[] k = new Paint$Align[]{Paint$Align.a, Paint$Align.b, Paint$Align.c};
   int l;
   Paint$Style m;
   int n;
   float o;
   Paint$Align p;
   float q;


   public Paint() {
      this(0);
   }

   public Paint(int var1) {
      this.g = 2;
      this.m = Paint$Style.a;
      this.o = 0.0F;
      this.q = 16.0F;
      this.a = o();
      this.a();
      this.a(var1 | 1280);
      this.z = this.A = 1.0F;
      this.a(Locale.getDefault());
   }

   public Paint(Paint var1) {
      this.g = 2;
      this.m = Paint$Style.a;
      this.o = 0.0F;
      this.q = 16.0F;
      this.a = d(var1.a);
      this.b(var1);
   }

   public void a() {
      e(this.a);
      this.a(1280);
      this.n = -1;
      this.m = Paint$Style.a;
      this.q = 16.0F;
      this.p = Paint$Align.a;
      this.r = null;
      this.s = null;
      this.t = null;
      this.u = null;
      this.v = null;
      this.w = null;
      this.x = null;
      this.y = false;
      this.z = 1.0F;
      this.A = 1.0F;
      this.b = false;
      this.c = 0.0F;
      this.d = 0.0F;
      this.e = 0.0F;
      this.f = 0;
      this.g = 2;
      this.a(Locale.getDefault());
   }

   public void a(Paint var1) {
      if(this != var1) {
         a(this.a, var1.a);
         this.b(var1);
      }

   }

   private void b(Paint var1) {
      this.m = var1.m;
      this.n = var1.n;
      this.q = var1.q;
      this.p = var1.p;
      this.r = var1.r;
      this.s = var1.s;
      this.t = var1.t;
      this.u = var1.u;
      this.w = var1.w;
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.A = var1.A;
      this.b = var1.b;
      this.c = var1.c;
      this.d = var1.d;
      this.e = var1.e;
      this.f = var1.f;
      this.g = var1.g;
      this.B = var1.B;
      this.o = var1.o;
   }

   public int b() {
      return this.l;
   }

   public void a(int var1) {
      this.l = var1;
   }

   public final boolean c() {
      return (this.b() & 1) != 0;
   }

   public void a(boolean var1) {
      if(var1) {
         this.a(this.l | 1);
      } else {
         this.a(this.l & -2);
      }

   }

   public void b(boolean var1) {}

   public void c(boolean var1) {}

   public void d(boolean var1) {}

   public Paint$Style d() {
      return this.m;
   }

   public void a(Paint$Style var1) {
      this.m = var1;
   }

   public int e() {
      return this.n;
   }

   public void b(int var1) {
      this.n = var1;
   }

   public int f() {
      return Color.a(this.n);
   }

   public void c(int var1) {
      this.n = Color.a(var1, Color.b(this.n), Color.c(this.n), Color.d(this.n));
   }

   public void a(int var1, int var2, int var3, int var4) {
      this.b(var1 << 24 | var2 << 16 | var3 << 8 | var4);
   }

   public float g() {
      return this.o;
   }

   public void a(float var1) {
      this.o = var1;
   }

   public void a(Paint$Cap var1) {
      b(this.a, var1.d);
   }

   public ColorFilter h() {
      return this.r;
   }

   public ColorFilter a(ColorFilter var1) {
      byte var2 = 0;
      c(this.a, var2);
      this.r = var1;
      return var1;
   }

   public Xfermode a(Xfermode var1) {
      byte var2 = 0;
      d(this.a, var2);
      this.x = var1;
      return var1;
   }

   public Typeface i() {
      return this.w;
   }

   public Typeface a(Typeface var1) {
      this.w = var1;
      return var1;
   }

   public Paint$Align j() {
      return this.p;
   }

   public void a(Paint$Align var1) {
      this.p = var1;
   }

   public void a(Locale var1) {
      if(var1 == null) {
         throw new IllegalArgumentException("locale cannot be null");
      } else if(!var1.equals(this.B)) {
         this.B = var1;
         a(this.a, var1.toString());
      }
   }

   public float k() {
      return this.q;
   }

   public void b(float var1) {
      this.q = var1;
   }

   public float l() {
      return -this.q;
   }

   public float m() {
      return 0.0F;
   }

   public float a(Paint$FontMetrics var1) {
      return 0.0F;
   }

   public Paint$FontMetrics n() {
      Paint$FontMetrics var1 = new Paint$FontMetrics();
      this.a(var1);
      return var1;
   }

   public float a(String var1) {
      if(var1 == null) {
         throw new IllegalArgumentException("text cannot be null");
      } else if(var1.length() == 0) {
         return 0.0F;
      } else if(!this.y) {
         return (float)Math.ceil((double)this.a(var1, this.g));
      } else {
         float var2 = this.k();
         this.b(var2 * this.z);
         float var3 = this.a(var1, this.g);
         this.b(var2);
         return (float)Math.ceil((double)(var3 * this.A));
      }
   }

   private float a(String var1, int var2) {
      return (float)var1.length() * this.k();
   }

   public int a(char[] var1, int var2, int var3, float var4, float[] var5) {
      if(var1 == null) {
         throw new IllegalArgumentException("text cannot be null");
      } else if(var2 >= 0 && var1.length - var2 >= Math.abs(var3)) {
         if(var1.length != 0 && var3 != 0) {
            if(!this.y) {
               return this.a(var1, var2, var3, var4, this.g, var5);
            } else {
               float var6 = this.k();
               this.b(var6 * this.z);
               int var7 = this.a(var1, var2, var3, var4 * this.z, this.g, var5);
               this.b(var6);
               if(var5 != null) {
                  var5[0] *= this.A;
               }

               return var7;
            }
         } else {
            return 0;
         }
      } else {
         throw new ArrayIndexOutOfBoundsException();
      }
   }

   private int a(char[] var1, int var2, int var3, float var4, int var5, float[] var6) {
      float var7 = this.k();
      if(var4 > var7 * (float)var3) {
         return var3;
      } else if(var4 == 0.0F) {
         return 1;
      } else {
         int var8 = (int)(var4 / var7);
         if(var8 < 1) {
            var8 = 1;
         }

         return var8;
      }
   }

   private int a(String var1, boolean var2, float var3, int var4, float[] var5) {
      return var1.length();
   }

   public int a(CharSequence var1, int var2, int var3, boolean var4, float var5, float[] var6) {
      if(var1 == null) {
         throw new IllegalArgumentException("text cannot be null");
      } else if((var2 | var3 | var3 - var2 | var1.length() - var3) < 0) {
         throw new IndexOutOfBoundsException();
      } else if(var1.length() != 0 && var2 != var3) {
         if(var2 == 0 && var1 instanceof String && var3 == var1.length()) {
            return this.a((String)var1, var4, var5, var6);
         } else {
            char[] var7 = TemporaryBuffer.a(var3 - var2);
            a(var1, var2, var3, var7, 0);
            int var8;
            if(var4) {
               var8 = this.a(var7, 0, var3 - var2, var5, var6);
            } else {
               var8 = this.a(var7, 0, -(var3 - var2), var5, var6);
            }

            TemporaryBuffer.a(var7);
            return var8;
         }
      } else {
         return 0;
      }
   }

   public static void a(CharSequence var0, int var1, int var2, char[] var3, int var4) {
      Class var5 = var0.getClass();
      if(var5 == String.class) {
         ((String)var0).getChars(var1, var2, var3, var4);
      } else if(var5 == StringBuffer.class) {
         ((StringBuffer)var0).getChars(var1, var2, var3, var4);
      } else if(var5 == StringBuilder.class) {
         ((StringBuilder)var0).getChars(var1, var2, var3, var4);
      } else {
         for(int var6 = var1; var6 < var2; ++var6) {
            var3[var4++] = var0.charAt(var6);
         }
      }

   }

   public int a(String var1, boolean var2, float var3, float[] var4) {
      if(var1 == null) {
         throw new IllegalArgumentException("text cannot be null");
      } else if(var1.length() == 0) {
         return 0;
      } else if(!this.y) {
         return this.a(var1, var2, var3, this.g, var4);
      } else {
         float var5 = this.k();
         this.b(var5 * this.z);
         int var6 = this.a(var1, var2, var3 * this.z, this.g, var4);
         this.b(var5);
         if(var4 != null) {
            var4[0] *= this.A;
         }

         return var6;
      }
   }

   public int a(char[] var1, int var2, int var3, float[] var4) {
      if(var1 == null) {
         throw new IllegalArgumentException("text cannot be null");
      } else if((var2 | var3) >= 0 && var2 + var3 <= var1.length && var3 <= var4.length) {
         if(var1.length != 0 && var3 != 0) {
            if(!this.y) {
               return a(this.a, var1, var2, var3, this.g, var4);
            } else {
               float var5 = this.k();
               this.b(var5 * this.z);
               int var6 = a(this.a, var1, var2, var3, this.g, var4);
               this.b(var5);

               for(int var7 = 0; var7 < var6; ++var7) {
                  var4[var7] *= this.A;
               }

               return var6;
            }
         } else {
            return 0;
         }
      } else {
         throw new ArrayIndexOutOfBoundsException();
      }
   }

   public void a(String var1, int var2, int var3, Rect var4) {
      if((var2 | var3 | var3 - var2 | var1.length() - var3) < 0) {
         throw new IndexOutOfBoundsException();
      } else if(var4 == null) {
         throw new NullPointerException("need bounds Rect");
      } else {
         var4.a(0, 0, 0, (int)this.q);
      }
   }

   protected void finalize() {
      try {
         f(this.a);
      } finally {
         super.finalize();
      }

   }

   private static int o() {
      return 0;
   }

   private static int d(int var0) {
      return 0;
   }

   private static void e(int var0) {}

   private static void a(int var0, int var1) {}

   private static void b(int var0, int var1) {}

   private static int c(int var0, int var1) {
      return 0;
   }

   private static int d(int var0, int var1) {
      return 0;
   }

   private static void a(int var0, String var1) {}

   private static int a(int var0, char[] var1, int var2, int var3, int var4, float[] var5) {
      return 0;
   }

   private static void f(int var0) {}

}
