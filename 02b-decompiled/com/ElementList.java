package com;

import com.Element;
import java.util.ArrayList;

public class ElementList {

   ArrayList elements = new ArrayList();


   public Element getFirst() {
      return this.elements.size() > 0?(Element)this.elements.get(0):null;
   }
}
