package android.graphics;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable.Creator;

final class Point$1 implements Creator {

   public Point a(Parcel var1) {
      Point var2 = new Point();
      var2.a(var1);
      return var2;
   }

   public Point[] a(int var1) {
      return new Point[var1];
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
