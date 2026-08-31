package android.view;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.view.KeyEvent;

final class KeyEvent$1 implements Creator {

   public KeyEvent a(Parcel var1) {
      var1.readInt();
      return KeyEvent.a(var1);
   }

   public KeyEvent[] a(int var1) {
      return new KeyEvent[var1];
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
