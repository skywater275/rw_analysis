package android.os;

import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable.Creator;

final class Message$1 implements Creator {

   public Message a(Parcel var1) {
      Message var2 = Message.a();
      Message.a(var2, var1);
      return var2;
   }

   public Message[] a(int var1) {
      return new Message[var1];
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
