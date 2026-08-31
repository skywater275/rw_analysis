package android.app;

import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import java.util.ArrayList;

public class Application extends ContextWrapper implements ComponentCallbacks2 {

   private ArrayList b = new ArrayList();
   private ArrayList c = new ArrayList();
   private ArrayList d = null;


   public Application() {
      super((Context)null);
   }

   public void onConfigurationChanged(Configuration var1) {
      Object[] var2 = this.a();
      if(var2 != null) {
         for(int var3 = 0; var3 < var2.length; ++var3) {
            ((ComponentCallbacks)var2[var3]).onConfigurationChanged(var1);
         }
      }

   }

   public void onLowMemory() {
      Object[] var1 = this.a();
      if(var1 != null) {
         for(int var2 = 0; var2 < var1.length; ++var2) {
            ((ComponentCallbacks)var1[var2]).onLowMemory();
         }
      }

   }

   public void onTrimMemory(int var1) {
      Object[] var2 = this.a();
      if(var2 != null) {
         for(int var3 = 0; var3 < var2.length; ++var3) {
            Object var4 = var2[var3];
            if(var4 instanceof ComponentCallbacks2) {
               ((ComponentCallbacks2)var4).onTrimMemory(var1);
            }
         }
      }

   }

   private Object[] a() {
      Object[] var1 = null;
      ArrayList var2 = this.b;
      synchronized(this.b) {
         if(this.b.size() > 0) {
            var1 = this.b.toArray();
         }

         return var1;
      }
   }
}
