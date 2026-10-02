package android.graphics;

import android.graphics.Rect;
import android.graphics.RectF$1;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class RectF implements Parcelable {

   public float a;
   public float b;
   public float c;
   public float d;
   public static final Creator e = new RectF$1();


   public RectF() {}

   public RectF(float var1, float var2, float var3, float var4) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
      this.d = var4;
   }

   public RectF(RectF var1) {
      this.a = var1.a;
      this.b = var1.b;
      this.c = var1.c;
      this.d = var1.d;
   }

   public RectF(Rect var1) {
      this.a = (float)var1.a;
      this.b = (float)var1.b;
      this.c = (float)var1.c;
      this.d = (float)var1.d;
   }

   public String toString() {
      return "RectF(" + this.a + ", " + this.b + ", " + this.c + ", " + this.d + ")";
   }

   public final boolean a() {
      return this.a >= this.c || this.b >= this.d;
   }

   public final float b() {
      return this.c - this.a;
   }

   public final float c() {
      return this.d - this.b;
   }

   public final float d() {
      return (this.a + this.c) * 0.5F;
   }

   public final float e() {
      return (this.b + this.d) * 0.5F;
   }

   public void f() {
      this.a = this.c = this.b = this.d = 0.0F;
   }

   public void a(float var1, float var2, float var3, float var4) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
      this.d = var4;
   }

   public void a(RectF var1) {
      this.a = var1.a;
      this.b = var1.b;
      this.c = var1.c;
      this.d = var1.d;
   }

   public void a(Rect var1) {
      this.a = (float)var1.a;
      this.b = (float)var1.b;
      this.c = (float)var1.c;
      this.d = (float)var1.d;
   }

   public void a(float var1, float var2) {
      this.a += var1;
      this.b += var2;
      this.c += var1;
      this.d += var2;
   }

   public boolean b(float var1, float var2) {
      return this.a < this.c && this.b < this.d && var1 >= this.a && var1 < this.c && var2 >= this.b && var2 < this.d;
   }

   public boolean b(float var1, float var2, float var3, float var4) {
      if(this.a < var3 && var1 < this.c && this.b < var4 && var2 < this.d) {
         if(this.a < var1) {
            this.a = var1;
         }

         if(this.b < var2) {
            this.b = var2;
         }

         if(this.c > var3) {
            this.c = var3;
         }

         if(this.d > var4) {
            this.d = var4;
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean b(RectF var1) {
      return this.b(var1.a, var1.b, var1.c, var1.d);
   }

   public static boolean a(RectF var0, RectF var1) {
      return var0.a < var1.c && var1.a < var0.c && var0.b < var1.d && var1.b < var0.d;
   }

   public void c(float var1, float var2) {
      if(var1 < this.a) {
         this.a = var1;
      } else if(var1 > this.c) {
         this.c = var1;
      }

      if(var2 < this.b) {
         this.b = var2;
      } else if(var2 > this.d) {
         this.d = var2;
      }

   }

   public void g() {
      float var1;
      if(this.a > this.c) {
         var1 = this.a;
         this.a = this.c;
         this.c = var1;
      }

      if(this.b > this.d) {
         var1 = this.b;
         this.b = this.d;
         this.d = var1;
      }

   }

   public int describeContents() {
      return 0;
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeFloat(this.a);
      var1.writeFloat(this.b);
      var1.writeFloat(this.c);
      var1.writeFloat(this.d);
   }

   public void a(Parcel var1) {
      this.a = var1.readFloat();
      this.b = var1.readFloat();
      this.c = var1.readFloat();
      this.d = var1.readFloat();
   }

}
