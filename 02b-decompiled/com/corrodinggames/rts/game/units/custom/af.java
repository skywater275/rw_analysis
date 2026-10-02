package com.corrodinggames.rts.game.units.custom;


public enum af {

   a("created", 0),
   b("completeAndActive", 1),
   c("destroyed", 2),
   d("killedAnyUnit", 3),
   e("queuedUnitFinished", 4),
   f("queueItemAdded", 5),
   g("queueItemCancelled", 6),
   h("teleported", 7),
   i("touchTargetSuccess", 8),
   j("newWaypointGivenByPlayer", 9),
   k("teamChanged", 10),
   l("transportingNewUnit", 11),
   m("transportUnloadedOrRemovedUnit", 12),
   n("tookDamage", 13),
   o("enteredTransport", 14),
   p("leftTransport", 15),
   q("newMessage", 16),
   r("attachmentRemoved", 17);
   // $FF: synthetic field
   private static final af[] s = new af[]{a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r};


   private strictfp af(String var1, int var2) {}

}
