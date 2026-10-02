package com.corrodinggames.rts.gameFramework.m;

import com.corrodinggames.rts.gameFramework.m.o$1;

public enum p {

   a("clipPath_Path_Op", 0),
   b("clipPath_Path", 1),
   c("clipRect_float_float_float_float_Op", 2),
   d("clipRect_float_float_float_float", 3),
   e("clipRect_int_int_int_int", 4),
   f("clipRect_Rect_Op", 5),
   g("clipRect_Rect", 6),
   h("clipRect_RectF_Op", 7),
   i("clipRect_RectF", 8),
   j("concat_Matrix", 9),
   k("drawARGB_int_int_int_int", 10),
   l("drawArc_RectF_float_float_boolean_Paint", 11),
   m("drawBitmap_Bitmap_float_float_Paint", 12),
   n("drawBitmap_Bitmap_Matrix_Paint", 13),
   o("drawBitmap_Bitmap_Rect_Rect_Paint", 14),
   p("drawBitmap_Bitmap_Rect_RectF_Paint", 15),
   q("drawBitmap_intarray_int_int_float_float_int_int_boolean_Paint", 16),
   r("drawBitmap_intarray_int_int_int_int_int_int_boolean_Paint", 17),
   s("drawBitmapMesh_Bitmap_int_int_floatarray_int_intarray_int_Paint", 18),
   t("drawCircle_float_float_float_Paint", 19),
   u("drawColor_int_Mode", 20),
   v("drawColor_int", 21),
   w("drawLine_float_float_float_float_Paint", 22),
   x("drawLines_floatarray_int_int_Paint", 23),
   y("drawLines_floatarray_Paint", 24),
   z("drawOval_RectF_Paint", 25),
   A("drawPaint_Paint", 26),
   B("drawPath_Path_Paint", 27),
   C("drawPicture_Picture_Rect", 28),
   D("drawPicture_Picture_RectF", 29),
   E("drawPicture_Picture", 30),
   F("drawPoint_float_float_Paint", 31),
   G("drawPoints_floatarray_int_int_Paint", 32),
   H("drawPoints_floatarray_Paint", 33),
   I("drawPosText_chararray_int_int_floatarray_Paint", 34),
   J("drawPosText_String_floatarray_Paint", 35),
   K("drawRGB_int_int_int", 36),
   L("drawRect_float_float_float_float_Paint", 37),
   M("drawRect_Rect_Paint", 38),
   N("drawRect_RectF_Paint", 39),
   O("drawRoundRect_RectF_float_float_Paint", 40),
   P("drawText_chararray_int_int_float_float_Paint", 41),
   Q("drawText_CharSequence_int_int_float_float_Paint", 42),
   R("drawText_String_float_float_Paint", 43),
   S("drawText_String_int_int_float_float_Paint", 44),
   T("drawTextOnPath_chararray_int_int_Path_float_float_Paint", 45),
   U("drawTextOnPath_String_Path_float_float_Paint", 46),
   V("drawVertices_VertexMode_int_floatarray_int_floatarray_int_intarray_int_shortarray_int_int_Paint", 47),
   W("restore", 48),
   X("restoreToCount_int", 49),
   Y("rotate_float", 50),
   Z("rotate_float_float_float", 51),
   aa("save", 52),
   ab("saveLayer_float_float_float_float_Paint_int", 53),
   ac("saveLayer_RectF_Paint_int", 54),
   ad("saveLayerAlpha_float_float_float_float_int_int", 55),
   ae("saveLayerAlpha_RectF_int_int", 56),
   af("scale_float_float", 57),
   ag("scale_float_float_float_float", 58),
   ah("setBitmap_Bitmap", 59),
   ai("setDensity_int", 60),
   aj("setDrawFilter_DrawFilter", 61),
   ak("setMatrix_Matrix", 62),
   al("skew_float_float", 63),
   am("translate_float_float", 64),
   an("runDrawTimeCallback_DrawTimeCallback", 65),
   ao("runDrawTimeCallback_DrawTimeCallback_float_float_float_paint", 66),
   ap("flushBitmap", 67),
   aq("enterLock_object", 68),
   ar("leaveLock_object", 69),
   as("compileShader_object", 70),
   at("setShader_object", 71);
   // $FF: synthetic field
   private static final p[] au = new p[]{a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, aa, ab, ac, ad, ae, af, ag, ah, ai, aj, ak, al, am, an, ao, ap, aq, ar, as, at};


   private p(String var1, int var2) {}

   // $FF: synthetic method
   p(String var1, int var2, o$1 var3) {
      this(var1, var2);
   }

}
