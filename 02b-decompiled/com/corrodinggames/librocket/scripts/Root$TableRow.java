package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.Root$TableCell;
import com.corrodinggames.rts.gameFramework.f;
import java.util.ArrayList;

public class Root$TableRow {

   public ArrayList tableCells = new ArrayList();
   public Runnable androidOnclick;
   public String librocketOnClick;
   public String extraClasses;


   public void addClass(String var1) {
      if(this.extraClasses == null) {
         this.extraClasses = var1;
      } else {
         this.extraClasses = this.extraClasses + " " + var1;
      }

   }

   public Root$TableCell addCell(String var1) {
      Root$TableCell var2 = new Root$TableCell(var1);
      this.tableCells.add(var2);
      return var2;
   }

   public void setLibrocketOnClick(String var1) {
      this.librocketOnClick = var1;
   }

   public void setAndroidOnClick(Runnable var1) {
      this.androidOnclick = var1;
   }

   public boolean same(Root$TableRow var1, boolean var2) {
      if(f.d(this.librocketOnClick, var1.librocketOnClick) && f.d(this.extraClasses, var1.extraClasses)) {
         if(this.tableCells.size() != var1.tableCells.size()) {
            return false;
         } else {
            for(int var3 = 0; var3 < this.tableCells.size(); ++var3) {
               Root$TableCell var4 = (Root$TableCell)this.tableCells.get(var3);
               Root$TableCell var5 = (Root$TableCell)var1.tableCells.get(var3);
               if(!var4.same(var5, var2)) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }
}
