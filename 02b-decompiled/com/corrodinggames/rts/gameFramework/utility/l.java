package com.corrodinggames.rts.gameFramework.utility;

import java.io.IOException;
import java.io.Reader;

public class l extends Reader {

   private Reader a;
   private char[] b;
   private int c;
   private int d;
   private int e;
   private int f;


   public l(Reader var1) {
      this(var1, 8192);
   }

   public l(Reader var1, int var2) {
      super(var1);
      this.e = -1;
      this.f = -1;
      if(var2 <= 0) {
         throw new IllegalArgumentException("size <= 0");
      } else {
         this.a = var1;
         this.b = new char[var2];
      }
   }

   public void close() {
      Object var1 = this.lock;
      synchronized(this.lock) {
         if(!this.c()) {
            this.a.close();
            this.b = null;
         }

      }
   }

   private int b() {
      int var1;
      if(this.e != -1 && this.c - this.e < this.f) {
         if(this.e == 0 && this.f > this.b.length) {
            var1 = this.b.length * 2;
            if(var1 > this.f) {
               var1 = this.f;
            }

            char[] var2 = new char[var1];
            System.arraycopy(this.b, 0, var2, 0, this.b.length);
            this.b = var2;
         } else if(this.e > 0) {
            System.arraycopy(this.b, this.e, this.b, 0, this.b.length - this.e);
            this.c -= this.e;
            this.d -= this.e;
            this.e = 0;
         }

         var1 = this.a.read(this.b, this.c, this.b.length - this.c);
         if(var1 != -1) {
            this.d += var1;
         }

         return var1;
      } else {
         var1 = this.a.read(this.b, 0, this.b.length);
         if(var1 > 0) {
            this.e = -1;
            this.c = 0;
            this.d = var1;
         }

         return var1;
      }
   }

   private boolean c() {
      return this.b == null;
   }

   public void mark(int var1) {
      if(var1 < 0) {
         throw new IllegalArgumentException();
      } else {
         Object var2 = this.lock;
         synchronized(this.lock) {
            this.d();
            this.f = var1;
            this.e = this.c;
         }
      }
   }

   private void d() {
      if(this.c()) {
         throw new IOException("BufferedReader is closed");
      }
   }

   public boolean markSupported() {
      return true;
   }

   public int read() {
      Object var1 = this.lock;
      synchronized(this.lock) {
         this.d();
         return this.c >= this.d && this.b() == -1?-1:this.b[this.c++];
      }
   }

   public static void a(int var0, int var1, int var2) {
      if((var1 | var2) < 0 || var1 > var0 || var0 - var1 < var2) {
         throw new IndexOutOfBoundsException();
      }
   }

   public int read(char[] var1, int var2, int var3) {
      Object var4 = this.lock;
      synchronized(this.lock) {
         this.d();
         a(var1.length, var2, var3);
         int var5 = var3;

         int var6;
         while(var5 > 0) {
            var6 = this.d - this.c;
            int var7;
            if(var6 > 0) {
               var7 = var6 >= var5?var5:var6;
               System.arraycopy(this.b, this.c, var1, var2, var7);
               this.c += var7;
               var2 += var7;
               var5 -= var7;
            }

            if(var5 == 0 || var5 < var3 && !this.a.ready()) {
               break;
            }

            if((this.e == -1 || this.c - this.e >= this.f) && var5 >= this.b.length) {
               var7 = this.a.read(var1, var2, var5);
               if(var7 > 0) {
                  var5 -= var7;
                  this.e = -1;
               }
               break;
            }

            if(this.b() == -1) {
               break;
            }
         }

         var6 = var3 - var5;
         return var6 <= 0 && var6 != var3?-1:var6;
      }
   }

   public String a() {
      Object var1 = this.lock;
      synchronized(this.lock) {
         this.d();
         if(this.c == this.d && this.b() == -1) {
            return null;
         } else {
            for(int var2 = this.c; var2 < this.d; ++var2) {
               char var3 = this.b[var2];
               if(var3 <= 13) {
                  String var4;
                  if(var3 == 10) {
                     var4 = new String(this.b, this.c, var2 - this.c);
                     this.c = var2 + 1;
                     return var4;
                  }

                  if(var3 == 13) {
                     var4 = new String(this.b, this.c, var2 - this.c);
                     this.c = var2 + 1;
                     if((this.c < this.d || this.b() != -1) && this.b[this.c] == 10) {
                        ++this.c;
                     }

                     return var4;
                  }
               }
            }

            char var9 = 0;
            StringBuilder var10 = new StringBuilder(80);
            var10.append(this.b, this.c, this.d - this.c);

            while(true) {
               this.c = this.d;
               if(var9 == 10) {
                  return var10.toString();
               }

               if(this.b() == -1) {
                  return var10.length() <= 0 && var9 == 0?null:var10.toString();
               }

               for(int var11 = this.c; var11 < this.d; ++var11) {
                  char var5 = this.b[var11];
                  if(var9 != 0) {
                     if(var9 == 13 && var5 == 10) {
                        if(var11 > this.c) {
                           var10.append(this.b, this.c, var11 - this.c - 1);
                        }

                        this.c = var11 + 1;
                        return var10.toString();
                     }

                     if(var11 > this.c) {
                        var10.append(this.b, this.c, var11 - this.c - 1);
                     }

                     this.c = var11;
                     return var10.toString();
                  }

                  if(var5 == 10 || var5 == 13) {
                     var9 = var5;
                  }
               }

               if(var9 == 0) {
                  var10.append(this.b, this.c, this.d - this.c);
               } else {
                  var10.append(this.b, this.c, this.d - this.c - 1);
               }

               try {
                  Thread.sleep(5L);
               } catch (InterruptedException var7) {
                  ;
               }
            }
         }
      }
   }

   public boolean ready() {
      Object var1 = this.lock;
      synchronized(this.lock) {
         this.d();
         return this.d - this.c > 0 || this.a.ready();
      }
   }

   public void reset() {
      Object var1 = this.lock;
      synchronized(this.lock) {
         this.d();
         if(this.e == -1) {
            throw new IOException("Invalid mark");
         } else {
            this.c = this.e;
         }
      }
   }

   public long skip(long var1) {
      if(var1 < 0L) {
         throw new IllegalArgumentException("byteCount < 0: " + var1);
      } else {
         Object var3 = this.lock;
         synchronized(this.lock) {
            this.d();
            if(var1 < 1L) {
               return 0L;
            } else if((long)(this.d - this.c) >= var1) {
               this.c = (int)((long)this.c + var1);
               return var1;
            } else {
               long var4 = (long)(this.d - this.c);

               for(this.c = this.d; var4 < var1; this.c = this.d) {
                  if(this.b() == -1) {
                     return var4;
                  }

                  if((long)(this.d - this.c) >= var1 - var4) {
                     this.c = (int)((long)this.c + (var1 - var4));
                     return var1;
                  }

                  var4 += (long)(this.d - this.c);
               }

               return var1;
            }
         }
      }
   }
}
