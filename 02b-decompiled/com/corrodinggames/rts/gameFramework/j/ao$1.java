package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.ao;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

class ao$1 extends a.a.c {

   // $FF: synthetic field
   final ao a;


   strictfp ao$1(ao var1) {
      this.a = var1;
   }

   public strictfp boolean a(SocketAddress var1) {
      if(var1 instanceof InetSocketAddress) {
         return this.a.a(((InetSocketAddress)var1).getAddress(), false);
      } else {
         com.corrodinggames.rts.gameFramework.l.e("AcceptFilter: Unhandled SocketAddress type:" + var1.getClass().getName());
         return true;
      }
   }
}
