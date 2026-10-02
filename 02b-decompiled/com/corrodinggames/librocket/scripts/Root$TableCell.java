package com.corrodinggames.librocket.scripts;

import com.corrodinggames.rts.gameFramework.f;

public class Root$TableCell {

   public String text;
   public String classes;
   public String librocketOnClick;
   public Integer color;


   public void setLibrocketOnClick(String var1) {
      this.librocketOnClick = var1;
   }

   public Root$TableCell(String var1) {
      this.text = var1;
   }

   public void addClass(String var1) {
      if(this.classes != null) {
         this.classes = this.classes + " " + var1;
      } else {
         this.classes = var1;
      }

   }

   public boolean same(Root$TableCell var1, boolean var2) {
      return f.d(this.classes, var1.classes) && f.d(this.librocketOnClick, var1.librocketOnClick) && f.a(this.color, var1.color)?var2 || f.d(this.text, var1.text):false;
   }
}
