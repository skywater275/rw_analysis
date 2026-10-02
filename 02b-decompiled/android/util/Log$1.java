package android.util;

import java.text.SimpleDateFormat;

final class Log$1 extends ThreadLocal {

   protected SimpleDateFormat a() {
      return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
   }

   // $FF: synthetic method
   protected Object initialValue() {
      return this.a();
   }
}
