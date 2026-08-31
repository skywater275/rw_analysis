package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.Root$TableRow;
import java.util.ArrayList;

public class Root$TableData {

   public ArrayList rows = new ArrayList();


   public boolean same(Root$TableData var1, boolean var2) {
      if(this.rows.size() != var1.rows.size()) {
         return false;
      } else {
         for(int var3 = 0; var3 < this.rows.size(); ++var3) {
            Root$TableRow var4 = (Root$TableRow)this.rows.get(var3);
            Root$TableRow var5 = (Root$TableRow)var1.rows.get(var3);
            if(!var4.same(var5, var2)) {
               return false;
            }
         }

         return true;
      }
   }
}
