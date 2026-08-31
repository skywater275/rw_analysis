package android.graphics;

import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable.Creator;

final class RectF$1 implements Creator {

   public RectF a(Parcel var1) {
      RectF var2 = new RectF();
      var2.a(var1);
      return var2;
   }

   public RectF[] a(int var1) {
      return new RectF[var1];
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
