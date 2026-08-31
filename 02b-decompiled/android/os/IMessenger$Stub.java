package android.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IMessenger;
import android.os.Message;
import android.os.Parcel;

public abstract class IMessenger$Stub extends Binder implements IMessenger {

   public IMessenger$Stub() {
      this.attachInterface(this, "android.os.IMessenger");
   }

   public IBinder asBinder() {
      return this;
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) {
      switch(var1) {
      case 1:
         var2.enforceInterface("android.os.IMessenger");
         Message var5;
         if(0 != var2.readInt()) {
            var5 = (Message)Message.m.createFromParcel(var2);
         } else {
            var5 = null;
         }

         this.a(var5);
         return true;
      case 1598968902:
         var3.writeString("android.os.IMessenger");
         return true;
      default:
         return super.onTransact(var1, var2, var3, var4);
      }
   }
}
