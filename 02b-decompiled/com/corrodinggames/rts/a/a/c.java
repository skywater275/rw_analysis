package com.corrodinggames.rts.a.a;

import com.corrodinggames.rts.a.a.l;
import com.corrodinggames.rts.a.a.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryWriter;
import com.corrodinggames.rts.gameFramework.br;

public class c extends l {

   public void a() {
      com.corrodinggames.rts.gameFramework.l.e("Logic boolean tests");
      com.corrodinggames.rts.game.units.custom.l var1 = com.corrodinggames.rts.game.units.custom.l.b;
      this.a(var1, "number numA");
      this.a(var1, "number numB");
      this.a(var1, "number[] numArrayA");
      this.a(var1, "number[] numArrayB");
      this.a(var1, "bool[] boolArrayA");
      this.a(var1, "unit[] unitArrayA");
      com.corrodinggames.rts.game.units.custom.j var2 = com.corrodinggames.rts.game.units.custom.l.a(false, var1);
      com.corrodinggames.rts.game.units.custom.j var3 = com.corrodinggames.rts.game.units.custom.l.a(false, var1);
      var3.b(com.corrodinggames.rts.game.n.i);
      com.corrodinggames.rts.game.units.custom.j var4 = com.corrodinggames.rts.game.units.custom.l.a(false, var1);
      var4.b(com.corrodinggames.rts.game.n.i);
      var4.cu = 44.0F;
      this.a((am)var4, "numA=5");
      this.a((am)var4, "numB=7");
      this.a((am)var4, "numArrayA[0]=1");
      this.a((am)var4, "numArrayA[1]=2");
      this.a((am)var4, "numArrayA[2]=15");
      this.a((am)var4, "boolArrayA[0]=true");
      this.a((am)var4, "unitArrayA[0]=self");
      this.a((am)var4, "numArrayA[(5)]=5");
      this.a((am)var4, "numArrayA[5+5]=10");
      this.a((am)var4, "numArrayA[4+4]=8");
      this.a((am)var4, "boolArrayA[10]=true");
      this.a((am)var4, "unitArrayA[10]=self");
      com.corrodinggames.rts.gameFramework.l.e("string: " + this.d(var4, this.b("str(memory.numArrayA)")));
      this.a(var4, this.b("memory.numArrayA.get(5)"), 5.0F);
      this.a(var4, this.b("memory.numArrayA.get(10)"), 10.0F);
      this.b((y)var4, this.b("memory.boolArrayA[10]"));
      this.b((y)var4, this.b("memory.unitArrayA[10]==self"));
      this.a((am)var4, "numArrayA[memory.numArrayA.get(2)]=98");
      this.a(var4, this.b("memory.numArrayA.get(15)"), 98.0F);
      this.a((am)var4, "numArrayA[memory.numArrayA[2]]=99");
      this.a(var4, this.b("memory.numArrayA.get(15)"), 99.0F);
      this.a((am)var4, "numArrayA[((((((((6))))))))]=99");
      this.a((am)var4, "numArrayA[((((((((memory.numArrayA[2]))))))))]=88");
      this.a(var4, this.b("memory.numArrayA.get(15)"), 88.0F);
      this.b((am)var4, "numArrayA[((((((((memory.numArrayA[2])())))))]=77");
      this.b((am)var4, "numArrayA[((((((((memory.numArrayA[2])))[)))]]))]=66");
      this.b((am)var4, "numArrayA[a]=1");
      this.b((am)var4, "numArrayA[0]=\'a\'");
      this.a((am)var4, "numArrayA[9001]=5");
      this.a(var4, this.b("memory.numArrayA.size"), 9002.0F);
      this.a(var4, this.b("memory.numArrayA.length"), 9002.0F);
      this.a((am)var4, "numArrayA[11000]=5");
      this.a((am)var4, "numArrayA[10000]=5");
      this.a((am)var4, "numArrayA[10001]=6");
      this.a((am)var4, "numArrayA[9999]=42");
      this.a(var4, this.b("memory.numArrayA.get(11000)"), 0.0F);
      this.a(var4, this.b("memory.numArrayA.get(10000)"), 5.0F);
      this.a(var4, this.b("memory.numArrayA.get(10001)"), 0.0F);
      this.a(var4, this.b("memory.numArrayA.get(9999)"), 42.0F);
      this.a((am)var4, "numArrayA[21]=21");
      this.a((am)var4, "numArrayA[22]=memory.numArrayA[21]");
      this.a(var4, this.b("memory.numArrayA.get(22)"), 21.0F);
      this.a(var4, this.b("memory.numArrayA.get(0)"), 1.0F);
      this.a(var4, this.b("memory.numArrayA.get(1)"), 2.0F);
      this.a(var4, this.b("memory.numArrayA.get(5)"), 5.0F);
      this.a(var4, this.b("memory.numArrayA.get(500)"), 0.0F);
      this.a(var4, this.b("memory.numArrayA.get(9000)"), 0.0F);
      this.a("memory.numArrayA.get(\'A\')");
      this.a(var4, this.b("memory.numArrayA[0]"), 1.0F);
      this.a(var4, this.b("memory.numArrayA[1]"), 2.0F);
      this.a(var4, this.b("memory.numArrayA[0]+memory.numArrayA[1]"), 3.0F);
      this.a(var4, this.b("memory.numArrayA[0]+(memory.numArrayA[1])"), 3.0F);
      this.a(var4, this.b("(memory.numArrayA[0]+(memory.numArrayA[1]))"), 3.0F);
      this.a(var4, this.b("memory.numArrayA[5]"), 5.0F);
      this.b((y)var4, this.b("memory.numArrayA.contains(5)"));
      this.c(var4, this.b("memory.numArrayA.contains(777)"));
      this.b((y)var4, this.b("memory.numArrayA.contains(memory.numArrayA[5])"));
      this.a("memory.numArrayA.contains(\'a\')");
      this.a("memory.numArrayA.contains(true)");
      this.a("memory.numArrayA[5][5]");
      this.a("memory.numArrayA[5][5][60]");
      this.a("memory.numArrayA[5][5][[60]]");
      this.a("memory.numArrayA[5][[5]");
      this.a("memory.numArrayA[5]][5]");
      this.a("memory.numArrayA[5[]][5]");
      this.a("memory.numArrayA[[5[]][5]");
      com.corrodinggames.rts.game.units.custom.j var5 = com.corrodinggames.rts.game.units.custom.l.a(false, var1);
      var5.b(com.corrodinggames.rts.game.n.i);
      var5.eo = 10.0F;
      var5.cu = 55.0F;
      var5.cv = 500.0F;
      var4.bu = var5;
      this.a((am)var5, "numA=309");
      this.a((am)var5, "numB=409");
      com.corrodinggames.rts.game.units.custom.j var6 = com.corrodinggames.rts.game.units.custom.l.a(false, var1);
      var6.b(com.corrodinggames.rts.game.n.i);
      var6.ep = 5.0F;
      var6.cu = 66.0F;
      var6.cv = 1000.0F;
      var5.bv = var6;
      com.corrodinggames.rts.game.units.custom.j var7 = com.corrodinggames.rts.game.units.custom.l.a(false, var1);
      var7.b(com.corrodinggames.rts.game.n.i);
      var7.eo = 2.0F;
      this.a((am)var7, "numA=99");
      this.a((am)var7, "numB=88");
      com.corrodinggames.rts.game.units.custom.j var8 = com.corrodinggames.rts.game.units.custom.l.a(false, var1);
      var8.b(com.corrodinggames.rts.game.n.i);
      var8.eo = 3.0F;
      this.a((am)var8, "numA=239");
      this.a((am)var8, "numB=268");
      com.corrodinggames.rts.game.units.custom.j var9 = com.corrodinggames.rts.game.units.custom.l.a(false, var1);
      var9.b(com.corrodinggames.rts.game.n.i);
      var9.eo = 3.0F;
      var7.C(var8);
      var7.C(var9);
      com.corrodinggames.rts.game.units.custom.j var10 = com.corrodinggames.rts.game.units.custom.l.a(false, var1);
      var10.b(com.corrodinggames.rts.game.n.i);
      var10.a(com.corrodinggames.rts.game.units.custom.g.a("globalTag1, globalTag2"), false);
      var10.eo = 2.0F;
      byte var11 = 50;
      com.corrodinggames.rts.gameFramework.l.e("=== logic boolean tests == (runs:" + var11 + ")");
      Long var12 = Long.valueOf(br.a());

      for(int var13 = 0; var13 < var11; ++var13) {
         if(var13 == 1) {
            ;
         }

         this.b((y)var4, this.b("true"));
         this.c(var4, this.b("false"));
         this.b((y)var4, this.b("not false"));
         this.b((y)var4, this.b("not not true"));
         this.a(var4, this.b("5"), 5.0F);
         this.a(var4, this.b("5+5"), 10.0F);
         this.a(var4, this.b("1+2+3"), 6.0F);
         this.a(var4, this.b("2.5+2.5"), 5.0F);
         this.a(var4, this.b("10-2"), 8.0F);
         this.a(var4, this.b("((5+5)-2)*3"), 24.0F);
         this.a(var4, this.b("10/2+10*2"), 25.0F);
         this.a(var4, this.b("-5"), -5.0F);
         this.a(var4, this.b("--5"), 5.0F);
         this.a(var4, this.b("9--5"), 14.0F);
         this.a(var4, this.b("-9--5"), -4.0F);
         this.a(var4, this.b("+5"), 5.0F);
         this.a(var4, this.b("+ 5"), 5.0F);
         this.a(var4, this.b(" + 5"), 5.0F);
         this.a(var4, this.b(" ++ 5"), 5.0F);
         this.a(var4, this.b("-+5"), -5.0F);
         this.a(var4, this.b("--+5"), 5.0F);
         this.a(var4, this.b("++-5"), -5.0F);
         this.a(var4, this.b(" - - +5"), 5.0F);
         this.a(var4, this.b("9++5"), 14.0F);
         this.a("5 - ");
         this.a("5 -- ");
         this.a("5 + ");
         this.a("5 ++ ");
         this.a("5 ** 9 ");
         this.a("5 -/ 9 ");
         this.a("5 * 5 -");
         this.a(" - ");
         this.a(" -- ");
         this.a(" + ");
         this.a(" ++ ");
         this.a(var4, this.b(" \'hello\'"), "hello");
         this.a(var4, this.b(" \"hello\" "), "hello");
         this.a(var4, this.b("self.hp+1"), var4.cu + 1.0F);
         this.a(var4, this.b("self.x+1"), var4.eo + 1.0F);
         this.a(var4, this.b("self.y+1"), var4.ep + 1.0F);
         this.a(var4, this.b("self.z+1"), var4.eq + 1.0F);
         this.a(var4, this.b("int( 5.5+0.1 )"), 5.0F);
         this.a(var4, this.b("-5 * 5"), -25.0F);
         this.a(var4, this.b("-5 * self.hp"), -5.0F * var4.cu);
         this.a(var4, this.b("self.hp + -5"), var4.cu + -5.0F);
         this.a(var4, this.b("self.hp * -5"), -5.0F * var4.cu);
         this.a(var4, this.b("(self.hp ) * -5 "), -5.0F * var4.cu);
         this.a(var4, this.b("-self.hp * -5"), -5.0F * -var4.cu);
         this.a(var4, this.b("-(self.hp ) * -5 "), -5.0F * -var4.cu);
         this.a(var4, this.b("-5 * -self.hp"), -5.0F * -var4.cu);
         this.a(var4, this.b("(-5 * -self.hp)/2"), -5.0F * -var4.cu / 2.0F);
         this.a(var4, this.b("-(self.hp )"), -var4.cu);
         this.a(var4, this.b("--(self.hp )"), var4.cu);
         this.a(var4, this.b("-((self.hp ))"), -var4.cu);
         this.a(var4, this.b("-self.hp"), -var4.cu);
         this.a(var4, this.b("-0"), 0.0F);
         this.a(var4, this.b("-  1"), -1.0F);
         this.a(var4, this.b(" -  1"), -1.0F);
         this.a(var4, this.b("-0*-0"), 0.0F);
         this.a(var4, this.b("-2*2"), -4.0F);
         this.a(var4, this.b("-2-3-2"), -7.0F);
         this.c(var4, this.b("10>10"));
         this.c(var4, this.b("10<10"));
         this.b((y)var4, this.b("10>=10"));
         this.b((y)var4, this.b("10<=10"));
         this.b((y)var4, this.b("\'hello\'==\'hello\'"));
         this.b((y)var4, this.b("\'hello\'!=\'bye\'"));
         this.a("\'hello\'<\'bye\'");
         this.a("\'hello\'>\'bye\'");
         this.a("\'hello\'<=\'bye\'");
         this.a("\'hello\'>=\'bye\'");
         this.a("\'hello\'55\'bye\'");
         this.a("\'hello\'><\'bye\'");
         this.a("6><8");
         this.c(var4, this.b("not (10>5 and true)"));
         this.c(var4, this.b("not true and false"));
         this.b((y)var4, this.b("not false and true"));
         this.b((y)var4, this.b("not (false and true)"));
         this.c(var4, this.b("not (1>2 or 5>2)"));
         this.b((y)var4, this.b("(true and (false or true))"));
         this.b((y)var4, this.b(" true and   (false   or   true  )"));
         this.b((y)var4, this.b("true and((false)or(true) )"));
         this.b((y)var4, this.b("100>50+20"));
         this.b((y)var4, this.b("100>50*1.5"));
         this.b((y)var4, this.b("not (100<50*1.5)"));
         this.b((y)var4, this.b("5 < 10 < 15"));
         this.b((y)var4, this.b("false==false"));
         this.b((y)var4, this.b("true==true"));
         this.b((y)var4, this.b("false==false==false"));
         this.b((y)var4, this.b("true==true==true"));
         this.b((y)var4, this.b("false!=true!=false"));
         this.b((y)var4, this.b("true!=false!=true"));
         this.c(var4, this.b("\'test\'==null"));
         this.b((y)var4, this.b("\'test\'!=null"));
         this.c(var4, this.b("\'test\'==null==null"));
         this.b((y)var4, this.b("\'test\'!=null!=\'test2\'"));
         this.b((y)var4, this.b("self!=null"));
         this.c(var4, this.b("self==null"));
         this.b((y)var4, this.b("10==10"));
         this.b((y)var4, this.b("10.5==10.5"));
         this.b((y)var4, this.b("1/3==1/3"));
         this.c(var4, this.b("10!=10"));
         this.b((y)var4, this.b("10!=5"));
         this.a("true - true");
         this.a("true + true");
         this.a("true * true");
         this.a("true / true");
         this.a("true < 10");
         this.a("true == 10");
         this.a("true != 10");
         this.a("\'text\' == 10");
         this.a("10 == ");
         this.a("10 != ");
         this.a("10 > ");
         this.a("10 < ");
         this.a("10 >= ");
         this.a("10 <= ");
         this.a("10 ==");
         this.a("10 !=");
         this.a("10 >");
         this.a("10 <");
         this.a("10 >=");
         this.a("10 <=");
         this.a("==10");
         this.a("!=10");
         this.a(">10");
         this.a("<10");
         this.a(">=10");
         this.a("<=10");
         this.a("10.6.6");
         this.a(var4, this.b("select(true, \'A\',\'B\')"), "A");
         this.a(var4, this.b("select(false, \'A\',\'B\')"), "B");
         this.a(var4, this.b("str(5.5)"), "5.5");
         this.a(var4, this.b("str(5)"), "5");
         this.a(var4, this.b("lowercase(\'HELlo\')"), "hello");
         this.a(var4, this.b("uppercase(\'heLLo\')"), "HELLO");
         this.a(var4, this.b("lowercase(str(\'HELlo\'))"), "hello");
         this.a(var4, this.b("\'hello\'"), "hello");
         this.a(var4, this.b("\'hello\'+\' world\'"), "hello world");
         this.a(var4, this.b("\'he(llo\'+\' world\'"), "he(llo world");
         this.a(var4, this.b("\'he(llo\'+\' wor)ld\'"), "he(llo wor)ld");
         this.a("(\'hello\'+\' world\'");
         this.a("\'hello\'+)\' world\'");
         this.b((y)var4, this.b("self.hp(lessThan=9999)"));
         this.a("self.hp(lessThan=9999, lessThan=9998)");
         this.a("self..hp(lessThan=9999)");
         this.a("self...hp(lessThan=9999)");
         this.b("game.nukesEnabled()");
         this.a("game.nukesEnabled(nukesEnabled=true)");
         this.a("game.nukesEnabled(nukesEnabled=false)");
         this.a("game.nukesEnabled()==0");
         this.a("game.nukesEnabled()!=0");
         this.a("game.nukesEnabled()<0");
         this.a("game.nukesEnabled()>0");
         this.a("game.nukesEnabled()==\'true\'");
         this.a("game.nukesEnabled()!=\'true\'");
         this.a("self.nukesEnabled()");
         this.a("parent.nukesEnabled()");
         this.a("hp==44");
         this.a("5=44");
         if(var4 == com.corrodinggames.rts.game.n.i.s) {
            com.corrodinggames.rts.gameFramework.l.e("skipping for placeholderTeamUnit");
         } else {
            this.b((y)var4, this.b("self.hp==44"));
            this.b((y)var4, this.b("self.customTarget1.hp==55"));
            this.a("self.memory1.hp=55");
            this.b((y)var4, this.b("self.customTarget1.maxhp==500"));
            this.b((y)var4, this.b("customTarget1.hp==55"));
            this.b((y)var4, this.b("self.customTarget1.customTarget2.hp==66"));
            this.b((y)var4, this.b("self.customTarget1==self.customTarget1"));
            this.b((y)var4, this.b("self.customTarget1!=self"));
            this.b((y)var3, this.b("self.customTarget1==null"));
            this.b((y)var3, this.b("self.customTarget1!=self"));
            this.b((y)var3, this.b("self.parent==null"));
            this.b((y)var3, this.b("self.parent.customTarget1==null"));
            this.b((y)var3, this.b("self.parent.customTarget1==self.parent"));
            this.b((y)var3, this.b("self.parent.customTarget1!=self"));
         }

         this.a(var4, this.b("self.getOffsetAbsolute(y=10).y"), var4.ep + 10.0F);
         this.b((y)var4, this.b("self.getOffsetAbsolute(y=10).y==self.y+10"));
         this.b((y)var4, this.b("self.getOffsetRelative(y=10, height=5).height==self.height+5"));
         this.a(var4, this.b("self.getOffsetRelative(y=10, height=5).height"), var4.eq + 5.0F);
         this.a(var4, this.b("self.getOffsetAbsolute(y=10).getOffsetAbsolute(y=10).y"), var4.ep + 10.0F + 10.0F);
         this.a(var7, this.b("self.transporting().getOffsetAbsolute(x=5).x"), var8.eo + 5.0F);
         this.a(var7, this.b("self.transporting(slot=1).getOffsetAbsolute(x=5).x"), var9.eo + 5.0F);
         this.a(var7, this.b("self.transporting().parent.transporting().parent.id"), (float)var7.eh);
         this.a(var7, this.b("self.transporting().getOffsetAbsolute(x=memory.numA).x-memory.numA"), var8.eo);
         this.a(var7, this.b("self.transporting().getOffsetAbsolute(x=self.id).x-self.id"), var8.eo);
         this.a(var7, this.b("self.transporting().parent.transporting().getOffsetAbsolute(x=self.id).x-self.id"), var8.eo);
         this.a(var7, this.b("self.transporting().parent.transporting().getOffsetAbsolute(x=self.id).getOffsetAbsolute().x-self.id"), var8.eo);
         this.a(var7, this.b("self.transporting().parent.transporting().getOffsetAbsolute(x=self.id).getOffsetAbsolute(x=self.id+1).x"), var8.eo + (float)var7.eh + (float)var7.eh + 1.0F);
         this.b((y)var4, this.b("numberOfUnitsInTeam(greaterThan=-2)"));
         this.b((y)var4, this.b("NumberOfUnitsInTeam(greaterTHAN=-2)"));
         this.a((y)var4, this.b("self.noUnitInTeam()"));
         this.a((y)var4, this.b("self.hasUnitInTeam()"));
         this.a((y)var4, this.b("self.hasUnitInTeam(neutralTeam=true)"));
         this.a((y)var4, this.b("self.shield()+self.ammo()+self.hp()>-1"));
         this.a((y)var4, this.b("parent.shield()+parent.ammo()+parent.hp()>-1"));
         this.a(var4, this.b("\'hello\'+\'a\'"), "helloa");
         this.a(var4, this.b("\'hello\'+5"), "hello5");
         this.a(var4, this.b("substring(\'hello\',0,3)"), "hel");
         this.a(var4, this.b("substring(\'hello\',0,100)"), "hello");
         this.a(var4, this.b("substring(\'HEllo\',0,100)"), "HEllo");
         this.a(var4, this.b("\'HEllo\'"), "HEllo");
         this.a(var4, this.b("substring(\'aa\',0,2)+substring(\'bb\',0,2)"), "aabb");
         this.b((y)var4, this.b(" true AND true"));
         this.b((y)var4, this.b(" true aNd true"));
         this.b((y)var4, this.b(" true OR false"));
         this.b((y)var4, this.b(" true OR TRUE"));
         this.b((y)var4, this.b(" True OR  False "));
         this.b((y)var4, this.b(" True OR  (False) "));
         this.b((y)var4, this.b(" NOT FALSE "));
         this.b((y)var4, this.b(" NOT NOT NOT FALSE "));
         this.b((y)var4, this.b(" game.nukesEnabled "));
         this.b((y)var4, this.b(" GAME.NukesEnabled "));
         this.a(var4, this.b("squareRoot( 100 )"), 10.0F);
         this.a(var4, this.b("max(+1,2)"), 2.0F);
         this.a(var4, this.b("min(+1,2)"), 1.0F);
         this.a(var4, this.b("max(1,2)"), 2.0F);
         this.a(var4, this.b("min(1,2)"), 1.0F);
         this.a(var4, this.b("max( 1,2 )"), 2.0F);
         this.a(var4, this.b("min( 1,2 )"), 1.0F);
         this.a(var4, this.b("max(-1,2)"), 2.0F);
         this.a(var4, this.b("min(-1,2)"), -1.0F);
         this.a(var4, this.b("max( -1,2 )"), 2.0F);
         this.a(var4, this.b("min( -1,2 )"), -1.0F);
         this.a(var4, this.b("max( 3,1 )"), 3.0F);
         this.a(var4, this.b("min( 3,1 )"), 1.0F);
         this.a(var4, this.b("max( 3+3,4 )"), 6.0F);
         this.a(var4, this.b("min( 3+3,4 )"), 4.0F);
         this.a(var4, this.b("distanceSquared( 1,1,1,6 )"), 25.0F);
         this.a(var4, this.b("max(distanceSquared( 1,1,1,6 ), 4)"), 25.0F);
         this.a(var4, this.b("min(  distanceSquared( 1,1,1 , 6 )  , 4)"), 4.0F);
         this.b((y)var4, this.b("distanceSquared( 1,1,1,6 )>=5*5"));
         this.b((y)var4, this.b("distanceSquared( 1,1,1,6 )>4*5"));
         this.b((y)var4, this.b("distanceSquared( 1,1,6,1 )<6*5"));
         this.b((y)var4, this.b("distance( 0,0,5,0 )==5"));
         this.b((y)var4, this.b("distance( 0,1,0,6 )==5"));
         this.a(var4, this.b("customTarget1"), (am)var5);
         this.a(var4, this.b("customTarget1.self"), (am)var5);
         this.a(var4, this.b("self.customTarget1.self"), (am)var5);
         this.a(var4, this.b("customTarget1.customTarget2"), var5.bv);
         this.a(var4, this.b(" distanceBetween(customTarget1.customTarget2, customTarget1 ) "), com.corrodinggames.rts.gameFramework.f.b(var5.eo, var5.ep, var6.eo, var6.ep));
         this.a(var4, this.b(" distanceBetweenSquared(customTarget1.customTarget2, customTarget1 ) "), com.corrodinggames.rts.gameFramework.f.a(var5.eo, var5.ep, var6.eo, var6.ep));
         this.b((y)var4, this.b(" distanceBetween(self, nullUnit ) == 0 "));
         this.a(var4, this.b(" distanceBetween(customTarget1.customTarget2, nullUnit ) "), 0.0F);
         this.a(var4, this.b(" distanceBetween(nullUnit, nullUnit ) "), 0.0F);
         this.a(var4, this.b(" distanceBetween( self.getOffsetAbsolute(x=5), self.getOffsetAbsolute(x=-5) ) "), 10.0F);
         this.a(var4, this.b(" distanceBetweenSquared( self.getOffsetAbsolute(x=5), self.getOffsetAbsolute(x=-5) ) "), 100.0F);
         this.b((y)var4, this.b(" self.energy < 0.5 and customTarget2==nullUnit ", true));
         this.b((y)var4, this.b(" self.energy < 0.5 and customTarget2 == nullUnit ", true));
         this.b((y)var4, this.b(" self.energy < 0.5 and customTarget1 != nullUnit ", true));
         this.b((y)var4, this.b("parent==nullUnit and customTarget1!= nullUnit ", true));
         this.b((y)var4, this.b("parent == nullUnit and customTarget1!=nullUnit ", true));
         this.a("distanceBetween( self )");
         this.a("distanceBetween( self, 5 )");
         this.a("distanceBetween( self, nullUnit, nullUnit )");
         this.a("distanceBetween(  )");
         this.b((y)var4, this.b("\'and\'==\'and\'"));
         this.b((y)var4, this.b("\'and\'!=\'and true\'"));
         this.b((y)var4, this.b("\'hello.test\'!=\'bye\'"));
         this.b((y)var4, this.b("\'hel.lo.test\'!=\'b.ye\'"));
         this.b((y)var4, this.b("\'hel.lo.(test\'!=\'b.ye\'"));
         this.b((y)var4, this.b("\'hel.\"lo.(test\'!=\'b.ye \"and \'"));
         this.b((y)var4, this.b("\"hel.lo.\'(test2\"!=\'b.ye \"and \'"));
         this.b((y)var4, this.b("5==5"));
         this.b((y)var4, this.b("\'hel.lo.(test\'!=\'b.ye\' and 5==5"));
         this.a("distanceSquared(  )");
         this.a("distanceSquared( 1 )");
         this.a("distanceSquared( 1,1 )");
         this.a("distanceSquared( 1,1,1 )");
         this.a("distanceSquared( 1,1,1,\'test\' )");
         this.a("distanceSquared( 1,1,1,true )");
         this.a("distanceSquared( 1,1,1,null )");
         this.a("distanceSquared( 1,1,1, )");
         this.a("distanceSquared( 1,1,1,\'test\' )");
         this.a("distanceSquared( x1=1,1,1,\'test\' )");
         this.a("distanceSquared( 1,1,1,1, x1=1 )");
         this.a("distanceSquared( 1,1,1,1,1 )");
         this.a("distanceSquared( 1,1,1, x1=1 )");
         this.a("distanceSquared( 1,1,1, 1 ");
         this.a("distanceSquared( 1,1,1, 1 ))");
         this.b((y)var4, this.b("SELF.HP(lessThan=9999)"));
         this.c(var4, this.b("Self.Parent.HP(lessThan=9999)"));
         this.a("self.hasFlag( id=35 )");
         this.a("self.hasFlag( 35 )");
         this.c(var4, this.b("self.hasFlag(id=30)"));
         this.c(var4, this.b("self.hasFlag(30)"));
         this.c(var4, this.b("self.hasFlag(15+15)"));
         this.c(var4, this.b("self.hasFlag(id=15*2)"));
      }

      Long var16 = Long.valueOf(br.a());
      double var14 = br.a(var12.longValue(), var16.longValue());
      com.corrodinggames.rts.gameFramework.l.e("Took: " + var14);
      com.corrodinggames.rts.gameFramework.l.e("=== logic boolean memory tests ==");
      this.a(var1, "unit testUnit1, float testFloat1");
      this.a(var1, "unit testUnit2, float testFloat2");
      this.a(var1, "bool testBool1");
      this.a(var1, "number testNumber1");
      this.a(var1, "float  testNumber2");
      this.a(var1, "String testString");
      this.a(var1, "String nullString");
      this.a((am)var4, "testString=\'(,,((\', testFloat1=5, testFloat2=8, testBool1=true, testUnit1=self");
      this.a((am)var4, "nullString=null");
      this.b((am)var2, "testNumber1=null");
      this.b((am)var2, "testNumber2=null");
      this.b((am)var2, "testBool1=null");
      this.b((am)var2, "testNumber1=self");
      this.b((am)var2, "testBool1=5");
      com.corrodinggames.rts.gameFramework.l.e(var4.bw.debugMemory(false, true));
      this.a(var4, this.b("memory.testFloat1"), 5.0F);
      this.a(var4, this.b("memory.testFloat2"), 8.0F);
      this.b((y)var4, this.b("memory.testFloat1==5"));
      this.b((y)var4, this.b("memory.testString==\'(,,((\'"));
      this.b((y)var4, this.b("memory.testBool1"));
      this.b((y)var4, this.b("memory.testBool1==true"));
      this.b((y)var4, this.b("memory.testUnit1==self"));
      this.b((y)var4, this.b("memory.testUnit1!=nullUnit"));
      this.a("memory.testUnit1==5", true);
      this.a(var4, this.b("self.readUnitMemory(\'testFloat1\', type=\'float\')"), 5.0F);
      this.a((am)var4, "testFloat1=distance( 0,0,6,0 ), testFloat2=16");
      this.b((y)var4, this.b("memory.testFloat1==6"));
      this.b((y)var4, this.b("memory.testFloat2==16"));
      this.a((am)var4, "testUnit1=self.getOffsetAbsolute(y=10), testUnit2=self.getOffsetAbsolute(y=-10)");
      com.corrodinggames.rts.gameFramework.l.e(var4.bw.debugMemory(false, true));
      this.a(var4, this.b("distanceBetween( memory.testUnit1, memory.testUnit2)"), 20.0F);
      this.b((y)var4, this.b("distanceBetweenSquared( memory.testUnit1, memory.testUnit2)==20*20"));
      this.a(var4, this.b("globalSearchForFirstUnit(withTag=\'globalTag1\')"), (am)var10);
      this.a(var4, this.b("globalSearchForFirstUnit(withTag=\'globalTag2\')"), (am)var10);
      this.b((y)var4, this.b("globalSearchForFirstUnit()!=null"));
      this.b((y)var4, this.b("globalSearchForFirstUnit(withTag=\'globalTag1\', relation=\'enemy\')==null"));
      this.b((y)var4, this.b("globalSearchForFirstUnit(withTag=\'globalTagNo\')==null"));
      this.a("globalSearchForFirstUnit(withTag=\'globalTag1\', relation=\'XYZ\')", true);
   }

   public void a(String var1) {
      this.a(var1, false);
   }

   public void a(String var1, boolean var2) {
      try {
         com.corrodinggames.rts.game.units.custom.l var3 = com.corrodinggames.rts.game.units.custom.l.b;
         LogicBooleanLoader.parseBooleanBlock(var3, var1, false);
      } catch (RuntimeException var5) {
         if(var5.getClass() != RuntimeException.class && var5.getClass() != BooleanParseException.class) {
            throw new RuntimeException(var5);
         }

         if(var2) {
            com.corrodinggames.rts.gameFramework.l.d("assertCreateError: " + var1 + " error:" + var5.getMessage());
         }

         return;
      }

      throw new RuntimeException("assertCreateError got no error for: " + var1);
   }

   public void a(com.corrodinggames.rts.game.units.custom.l var1, String var2) {
      var1.r.defineVariables(var1, var2);
   }

   public void a(am var1, String var2) {
      try {
         com.corrodinggames.rts.game.units.custom.j var3 = (com.corrodinggames.rts.game.units.custom.j)var1;
         VariableScope$MemoryWriter var4 = VariableScope.createMemoryWriter(var2, var3.x, "testsection", "testkey");
         var4.writeToUnit(var3);
      } catch (bo var5) {
         throw new RuntimeException(var5);
      }
   }

   public void b(am var1, String var2) {
      try {
         com.corrodinggames.rts.game.units.custom.j var3 = (com.corrodinggames.rts.game.units.custom.j)var1;
         VariableScope$MemoryWriter var4 = VariableScope.createMemoryWriter(var2, var3.x, "testsection", "testkey");
         var4.writeToUnit(var3);
      } catch (RuntimeException var5) {
         return;
      } catch (bo var6) {
         return;
      }

      throw new RuntimeException("assertSetMemoryError got no error for: " + var2);
   }

   public LogicBoolean b(String var1) {
      return this.b(var1, false);
   }

   public LogicBoolean b(String var1, boolean var2) {
      try {
         com.corrodinggames.rts.game.units.custom.l var3 = com.corrodinggames.rts.game.units.custom.l.b;
         LogicBoolean var4 = LogicBooleanLoader.parseBooleanBlock(var3, var1, var2);
         return var4;
      } catch (RuntimeException var5) {
         throw new RuntimeException("Error: " + var5.getMessage() + " parsing [" + var1 + "]", var5);
      }
   }

   public void a(y var1, LogicBoolean var2) {
      if(var2.getReturnType() != LogicBoolean$ReturnType.bool) {
         throw new RuntimeException("Asset assertBooleanTrue type ==" + var2.getReturnType());
      } else {
         var2.read(var1);
      }
   }

   public void b(y var1, LogicBoolean var2) {
      if(var2.getReturnType() != LogicBoolean$ReturnType.bool) {
         throw new RuntimeException("Asset assertBooleanTrue type ==" + var2.getReturnType());
      } else {
         boolean var3 = var2.read(var1);
         if(!var3) {
            throw new RuntimeException("Asset assertBooleanTrue failed, got false for: " + var2.getMatchFailReasonForPlayer(var1));
         }
      }
   }

   public void c(y var1, LogicBoolean var2) {
      if(var2.getReturnType() != LogicBoolean$ReturnType.bool) {
         throw new RuntimeException("Asset assertBooleanFalse type ==" + var2.getReturnType());
      } else {
         n.b(var2.read(var1));
      }
   }

   public void a(y var1, LogicBoolean var2, float var3) {
      if(var2.getReturnType() != LogicBoolean$ReturnType.number) {
         throw new RuntimeException("Asset assertBooleanNumber type ==" + var2.getReturnType());
      } else {
         float var4 = var2.readNumber(var1);
         if(com.corrodinggames.rts.gameFramework.f.c(var4 - var3) > 0.001F) {
            throw new RuntimeException("Asset failed (float):" + var4 + "!=" + var3 + " for: " + var2.getMatchFailReasonForPlayer(var1));
         }
      }
   }

   public String d(y var1, LogicBoolean var2) {
      if(var2.getReturnType() != LogicBoolean$ReturnType.string) {
         throw new RuntimeException("Asset assertBooleanString type ==" + var2.getReturnType());
      } else {
         String var3 = var2.readString(var1);
         return var3;
      }
   }

   public void a(y var1, LogicBoolean var2, String var3) {
      if(var2.getReturnType() != LogicBoolean$ReturnType.string) {
         throw new RuntimeException("Asset assertBooleanString type ==" + var2.getReturnType());
      } else {
         String var4 = var2.readString(var1);
         n.a(var4, var3);
      }
   }

   public void a(y var1, LogicBoolean var2, am var3) {
      if(var2.getReturnType() != LogicBoolean$ReturnType.unit) {
         throw new RuntimeException("Asset assertBooleanUnit type ==" + var2.getReturnType());
      } else {
         am var4 = var2.readUnit(var1);
         if(var4 != var3) {
            com.corrodinggames.rts.gameFramework.l.e("class: " + var2.getClass().getName());
            throw new RuntimeException("assertBooleanUnit failed:" + am.A(var4) + "!=" + am.A(var3) + " for: " + var2.getMatchFailReasonForPlayer(var1));
         }
      }
   }
}
