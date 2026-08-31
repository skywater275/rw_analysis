package com;

import com.Element;
import java.util.ArrayList;
import java.util.HashMap;

public class ElementDocument extends Element {

   public static final int NONE = 0;
   public static final int FOCUS = 2;
   public static final int MODAL = 4;
   public String documentPath;
   public HashMap metadata;
   public boolean translatedToUnicode;
   public ArrayList pageTimers = new ArrayList(1);


   public void show() {
      this.show(2);
   }

   public native void show(int var1);

   public void showWithWorkaround(int var1) {}

   public native void hide();

   public void closeDocument() {
      this.close();
   }

   private native void close();

   public native void pullToFront();

   public native void pushToBack();

   public Object getMetadata(String var1) {
      return this.metadata == null?null:this.metadata.get(var1);
   }

   public Object getMetadata(String var1, Object var2) {
      if(this.metadata == null) {
         return var2;
      } else {
         Object var3 = this.metadata.get(var1);
         return var3 == null?var2:var3;
      }
   }

   public Float getMetadataFloat(String var1) {
      Object var2 = this.getMetadata(var1);
      return var2 instanceof Float?(Float)var2:null;
   }

   public void setMetadata(String var1, Object var2) {
      if(this.metadata == null) {
         this.metadata = new HashMap();
      }

      this.metadata.put(var1, var2);
   }

   public void setMetadataFloat(String var1, Float var2) {
      this.setMetadata(var1, var2);
   }

   public HashMap getMetadataMap() {
      if(this.metadata == null) {
         this.metadata = new HashMap();
      }

      return this.metadata;
   }
}
