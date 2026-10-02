package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;

public class PointF implements Parcelable {

   public float a;
   public float b;


   public PointF() {}

   public PointF(float var1, float var2) {
      this.a = var1;
      this.b = var2;
   }

   public final void a(float var1, float var2) {
      this.a = var1;
      this.b = var2;
   }

   public final void a(PointF var1) {
      this.a = var1.a;
      this.b = var1.b;
   }

   public final void b(float var1, float var2) {
      this.a += var1;
      this.b += var2;
   }

   public int describeContents() {
      return 0;
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeFloat(this.a);
      var1.writeFloat(this.b);
   }
}
