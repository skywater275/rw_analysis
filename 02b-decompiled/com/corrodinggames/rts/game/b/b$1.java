package com.corrodinggames.rts.game.b;

import com.corrodinggames.rts.game.b.b;
import java.io.ByteArrayInputStream;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

class b$1 implements EntityResolver {

   // $FF: synthetic field
   final b a;


   strictfp b$1(b var1) {
      this.a = var1;
   }

   public strictfp InputSource resolveEntity(String var1, String var2) {
      return new InputSource(new ByteArrayInputStream(new byte[0]));
   }
}
