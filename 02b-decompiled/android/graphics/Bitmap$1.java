package android.graphics;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable.Creator;

final class Bitmap$1 implements Creator {

   public Bitmap a(Parcel var1) {
      Bitmap var2 = Bitmap.a(var1);
      if(var2 == null) {
         throw new RuntimeException("Failed to unparcel Bitmap");
      } else {
         return var2;
      }
   }

   public Bitmap[] a(int var1) {
      return new Bitmap[var1];
   }

   // $FF: synthetic method
   public Object[] newArray(int var1) {
      return this.a(var1);
   }

   // $FF: synthetic method
   public Object createFromParcel(Parcel var1) {
      return this.a(var1);
   }
}
