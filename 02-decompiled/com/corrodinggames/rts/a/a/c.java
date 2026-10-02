/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.a.a;

import com.corrodinggames.rts.a.a.l;
import com.corrodinggames.rts.a.a.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryWriter;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.f;

public class c
extends l {
    public void a() {
        com.corrodinggames.rts.gameFramework.l.e("Logic boolean tests");
        com.corrodinggames.rts.game.units.custom.l l2 = com.corrodinggames.rts.game.units.custom.l.b;
        this.a(l2, "number numA");
        this.a(l2, "number numB");
        this.a(l2, "number[] numArrayA");
        this.a(l2, "number[] numArrayB");
        this.a(l2, "bool[] boolArrayA");
        this.a(l2, "unit[] unitArrayA");
        j j2 = com.corrodinggames.rts.game.units.custom.l.a(false, l2);
        j j3 = com.corrodinggames.rts.game.units.custom.l.a(false, l2);
        j3.b(com.corrodinggames.rts.game.n.i);
        j j4 = com.corrodinggames.rts.game.units.custom.l.a(false, l2);
        j4.b(com.corrodinggames.rts.game.n.i);
        j4.cu = 44.0f;
        this.a((am)j4, "numA=5");
        this.a((am)j4, "numB=7");
        this.a((am)j4, "numArrayA[0]=1");
        this.a((am)j4, "numArrayA[1]=2");
        this.a((am)j4, "numArrayA[2]=15");
        this.a((am)j4, "boolArrayA[0]=true");
        this.a((am)j4, "unitArrayA[0]=self");
        this.a((am)j4, "numArrayA[(5)]=5");
        this.a((am)j4, "numArrayA[5+5]=10");
        this.a((am)j4, "numArrayA[4+4]=8");
        this.a((am)j4, "boolArrayA[10]=true");
        this.a((am)j4, "unitArrayA[10]=self");
        com.corrodinggames.rts.gameFramework.l.e("string: " + this.d(j4, this.b("str(memory.numArrayA)")));
        this.a((y)j4, this.b("memory.numArrayA.get(5)"), 5.0f);
        this.a((y)j4, this.b("memory.numArrayA.get(10)"), 10.0f);
        this.b(j4, this.b("memory.boolArrayA[10]"));
        this.b(j4, this.b("memory.unitArrayA[10]==self"));
        this.a((am)j4, "numArrayA[memory.numArrayA.get(2)]=98");
        this.a((y)j4, this.b("memory.numArrayA.get(15)"), 98.0f);
        this.a((am)j4, "numArrayA[memory.numArrayA[2]]=99");
        this.a((y)j4, this.b("memory.numArrayA.get(15)"), 99.0f);
        this.a((am)j4, "numArrayA[((((((((6))))))))]=99");
        this.a((am)j4, "numArrayA[((((((((memory.numArrayA[2]))))))))]=88");
        this.a((y)j4, this.b("memory.numArrayA.get(15)"), 88.0f);
        this.b((am)j4, "numArrayA[((((((((memory.numArrayA[2])())))))]=77");
        this.b((am)j4, "numArrayA[((((((((memory.numArrayA[2])))[)))]]))]=66");
        this.b((am)j4, "numArrayA[a]=1");
        this.b((am)j4, "numArrayA[0]='a'");
        this.a((am)j4, "numArrayA[9001]=5");
        this.a((y)j4, this.b("memory.numArrayA.size"), 9002.0f);
        this.a((y)j4, this.b("memory.numArrayA.length"), 9002.0f);
        this.a((am)j4, "numArrayA[11000]=5");
        this.a((am)j4, "numArrayA[10000]=5");
        this.a((am)j4, "numArrayA[10001]=6");
        this.a((am)j4, "numArrayA[9999]=42");
        this.a((y)j4, this.b("memory.numArrayA.get(11000)"), 0.0f);
        this.a((y)j4, this.b("memory.numArrayA.get(10000)"), 5.0f);
        this.a((y)j4, this.b("memory.numArrayA.get(10001)"), 0.0f);
        this.a((y)j4, this.b("memory.numArrayA.get(9999)"), 42.0f);
        this.a((am)j4, "numArrayA[21]=21");
        this.a((am)j4, "numArrayA[22]=memory.numArrayA[21]");
        this.a((y)j4, this.b("memory.numArrayA.get(22)"), 21.0f);
        this.a((y)j4, this.b("memory.numArrayA.get(0)"), 1.0f);
        this.a((y)j4, this.b("memory.numArrayA.get(1)"), 2.0f);
        this.a((y)j4, this.b("memory.numArrayA.get(5)"), 5.0f);
        this.a((y)j4, this.b("memory.numArrayA.get(500)"), 0.0f);
        this.a((y)j4, this.b("memory.numArrayA.get(9000)"), 0.0f);
        this.a("memory.numArrayA.get('A')");
        this.a((y)j4, this.b("memory.numArrayA[0]"), 1.0f);
        this.a((y)j4, this.b("memory.numArrayA[1]"), 2.0f);
        this.a((y)j4, this.b("memory.numArrayA[0]+memory.numArrayA[1]"), 3.0f);
        this.a((y)j4, this.b("memory.numArrayA[0]+(memory.numArrayA[1])"), 3.0f);
        this.a((y)j4, this.b("(memory.numArrayA[0]+(memory.numArrayA[1]))"), 3.0f);
        this.a((y)j4, this.b("memory.numArrayA[5]"), 5.0f);
        this.b(j4, this.b("memory.numArrayA.contains(5)"));
        this.c(j4, this.b("memory.numArrayA.contains(777)"));
        this.b(j4, this.b("memory.numArrayA.contains(memory.numArrayA[5])"));
        this.a("memory.numArrayA.contains('a')");
        this.a("memory.numArrayA.contains(true)");
        this.a("memory.numArrayA[5][5]");
        this.a("memory.numArrayA[5][5][60]");
        this.a("memory.numArrayA[5][5][[60]]");
        this.a("memory.numArrayA[5][[5]");
        this.a("memory.numArrayA[5]][5]");
        this.a("memory.numArrayA[5[]][5]");
        this.a("memory.numArrayA[[5[]][5]");
        j j5 = com.corrodinggames.rts.game.units.custom.l.a(false, l2);
        j5.b(com.corrodinggames.rts.game.n.i);
        j5.eo = 10.0f;
        j5.cu = 55.0f;
        j5.cv = 500.0f;
        j4.bu = j5;
        this.a((am)j5, "numA=309");
        this.a((am)j5, "numB=409");
        j j6 = com.corrodinggames.rts.game.units.custom.l.a(false, l2);
        j6.b(com.corrodinggames.rts.game.n.i);
        j6.ep = 5.0f;
        j6.cu = 66.0f;
        j6.cv = 1000.0f;
        j5.bv = j6;
        j j7 = com.corrodinggames.rts.game.units.custom.l.a(false, l2);
        j7.b(com.corrodinggames.rts.game.n.i);
        j7.eo = 2.0f;
        this.a((am)j7, "numA=99");
        this.a((am)j7, "numB=88");
        j j8 = com.corrodinggames.rts.game.units.custom.l.a(false, l2);
        j8.b(com.corrodinggames.rts.game.n.i);
        j8.eo = 3.0f;
        this.a((am)j8, "numA=239");
        this.a((am)j8, "numB=268");
        j j9 = com.corrodinggames.rts.game.units.custom.l.a(false, l2);
        j9.b(com.corrodinggames.rts.game.n.i);
        j9.eo = 3.0f;
        j7.C(j8);
        j7.C(j9);
        j j10 = com.corrodinggames.rts.game.units.custom.l.a(false, l2);
        j10.b(com.corrodinggames.rts.game.n.i);
        j10.a(g.a("globalTag1, globalTag2"), false);
        j10.eo = 2.0f;
        int n2 = 50;
        com.corrodinggames.rts.gameFramework.l.e("=== logic boolean tests == (runs:" + n2 + ")");
        Long l3 = br.a();
        for (int i2 = 0; i2 < n2; ++i2) {
            j j11 = j4;
            if (i2 == 1) {
                // empty if block
            }
            this.b(j11, this.b("true"));
            this.c(j11, this.b("false"));
            this.b(j11, this.b("not false"));
            this.b(j11, this.b("not not true"));
            this.a((y)j11, this.b("5"), 5.0f);
            this.a((y)j11, this.b("5+5"), 10.0f);
            this.a((y)j11, this.b("1+2+3"), 6.0f);
            this.a((y)j11, this.b("2.5+2.5"), 5.0f);
            this.a((y)j11, this.b("10-2"), 8.0f);
            this.a((y)j11, this.b("((5+5)-2)*3"), 24.0f);
            this.a((y)j11, this.b("10/2+10*2"), 25.0f);
            this.a((y)j11, this.b("-5"), -5.0f);
            this.a((y)j11, this.b("--5"), 5.0f);
            this.a((y)j11, this.b("9--5"), 14.0f);
            this.a((y)j11, this.b("-9--5"), -4.0f);
            this.a((y)j11, this.b("+5"), 5.0f);
            this.a((y)j11, this.b("+ 5"), 5.0f);
            this.a((y)j11, this.b(" + 5"), 5.0f);
            this.a((y)j11, this.b(" ++ 5"), 5.0f);
            this.a((y)j11, this.b("-+5"), -5.0f);
            this.a((y)j11, this.b("--+5"), 5.0f);
            this.a((y)j11, this.b("++-5"), -5.0f);
            this.a((y)j11, this.b(" - - +5"), 5.0f);
            this.a((y)j11, this.b("9++5"), 14.0f);
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
            this.a((y)j11, this.b(" 'hello'"), "hello");
            this.a((y)j11, this.b(" \"hello\" "), "hello");
            this.a((y)j11, this.b("self.hp+1"), j11.cu + 1.0f);
            this.a((y)j11, this.b("self.x+1"), j11.eo + 1.0f);
            this.a((y)j11, this.b("self.y+1"), j11.ep + 1.0f);
            this.a((y)j11, this.b("self.z+1"), j11.eq + 1.0f);
            this.a((y)j11, this.b("int( 5.5+0.1 )"), 5.0f);
            this.a((y)j11, this.b("-5 * 5"), -25.0f);
            this.a((y)j11, this.b("-5 * self.hp"), -5.0f * j11.cu);
            this.a((y)j11, this.b("self.hp + -5"), j11.cu + -5.0f);
            this.a((y)j11, this.b("self.hp * -5"), -5.0f * j11.cu);
            this.a((y)j11, this.b("(self.hp ) * -5 "), -5.0f * j11.cu);
            this.a((y)j11, this.b("-self.hp * -5"), -5.0f * -j11.cu);
            this.a((y)j11, this.b("-(self.hp ) * -5 "), -5.0f * -j11.cu);
            this.a((y)j11, this.b("-5 * -self.hp"), -5.0f * -j11.cu);
            this.a((y)j11, this.b("(-5 * -self.hp)/2"), -5.0f * -j11.cu / 2.0f);
            this.a((y)j11, this.b("-(self.hp )"), -j11.cu);
            this.a((y)j11, this.b("--(self.hp )"), j11.cu);
            this.a((y)j11, this.b("-((self.hp ))"), -j11.cu);
            this.a((y)j11, this.b("-self.hp"), -j11.cu);
            this.a((y)j11, this.b("-0"), 0.0f);
            this.a((y)j11, this.b("-  1"), -1.0f);
            this.a((y)j11, this.b(" -  1"), -1.0f);
            this.a((y)j11, this.b("-0*-0"), 0.0f);
            this.a((y)j11, this.b("-2*2"), -4.0f);
            this.a((y)j11, this.b("-2-3-2"), -7.0f);
            this.c(j11, this.b("10>10"));
            this.c(j11, this.b("10<10"));
            this.b(j11, this.b("10>=10"));
            this.b(j11, this.b("10<=10"));
            this.b(j11, this.b("'hello'=='hello'"));
            this.b(j11, this.b("'hello'!='bye'"));
            this.a("'hello'<'bye'");
            this.a("'hello'>'bye'");
            this.a("'hello'<='bye'");
            this.a("'hello'>='bye'");
            this.a("'hello'55'bye'");
            this.a("'hello'><'bye'");
            this.a("6><8");
            this.c(j11, this.b("not (10>5 and true)"));
            this.c(j11, this.b("not true and false"));
            this.b(j11, this.b("not false and true"));
            this.b(j11, this.b("not (false and true)"));
            this.c(j11, this.b("not (1>2 or 5>2)"));
            this.b(j11, this.b("(true and (false or true))"));
            this.b(j11, this.b(" true and   (false   or   true  )"));
            this.b(j11, this.b("true and((false)or(true) )"));
            this.b(j11, this.b("100>50+20"));
            this.b(j11, this.b("100>50*1.5"));
            this.b(j11, this.b("not (100<50*1.5)"));
            this.b(j11, this.b("5 < 10 < 15"));
            this.b(j11, this.b("false==false"));
            this.b(j11, this.b("true==true"));
            this.b(j11, this.b("false==false==false"));
            this.b(j11, this.b("true==true==true"));
            this.b(j11, this.b("false!=true!=false"));
            this.b(j11, this.b("true!=false!=true"));
            this.c(j11, this.b("'test'==null"));
            this.b(j11, this.b("'test'!=null"));
            this.c(j11, this.b("'test'==null==null"));
            this.b(j11, this.b("'test'!=null!='test2'"));
            this.b(j11, this.b("self!=null"));
            this.c(j11, this.b("self==null"));
            this.b(j11, this.b("10==10"));
            this.b(j11, this.b("10.5==10.5"));
            this.b(j11, this.b("1/3==1/3"));
            this.c(j11, this.b("10!=10"));
            this.b(j11, this.b("10!=5"));
            this.a("true - true");
            this.a("true + true");
            this.a("true * true");
            this.a("true / true");
            this.a("true < 10");
            this.a("true == 10");
            this.a("true != 10");
            this.a("'text' == 10");
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
            this.a((y)j11, this.b("select(true, 'A','B')"), "A");
            this.a((y)j11, this.b("select(false, 'A','B')"), "B");
            this.a((y)j11, this.b("str(5.5)"), "5.5");
            this.a((y)j11, this.b("str(5)"), "5");
            this.a((y)j11, this.b("lowercase('HELlo')"), "hello");
            this.a((y)j11, this.b("uppercase('heLLo')"), "HELLO");
            this.a((y)j11, this.b("lowercase(str('HELlo'))"), "hello");
            this.a((y)j11, this.b("'hello'"), "hello");
            this.a((y)j11, this.b("'hello'+' world'"), "hello world");
            this.a((y)j11, this.b("'he(llo'+' world'"), "he(llo world");
            this.a((y)j11, this.b("'he(llo'+' wor)ld'"), "he(llo wor)ld");
            this.a("('hello'+' world'");
            this.a("'hello'+)' world'");
            this.b(j11, this.b("self.hp(lessThan=9999)"));
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
            this.a("game.nukesEnabled()=='true'");
            this.a("game.nukesEnabled()!='true'");
            this.a("self.nukesEnabled()");
            this.a("parent.nukesEnabled()");
            this.a("hp==44");
            this.a("5=44");
            if (j11 == com.corrodinggames.rts.game.n.i.s) {
                com.corrodinggames.rts.gameFramework.l.e("skipping for placeholderTeamUnit");
            } else {
                this.b(j11, this.b("self.hp==44"));
                this.b(j11, this.b("self.customTarget1.hp==55"));
                this.a("self.memory1.hp=55");
                this.b(j11, this.b("self.customTarget1.maxhp==500"));
                this.b(j11, this.b("customTarget1.hp==55"));
                this.b(j11, this.b("self.customTarget1.customTarget2.hp==66"));
                this.b(j11, this.b("self.customTarget1==self.customTarget1"));
                this.b(j11, this.b("self.customTarget1!=self"));
                this.b(j3, this.b("self.customTarget1==null"));
                this.b(j3, this.b("self.customTarget1!=self"));
                this.b(j3, this.b("self.parent==null"));
                this.b(j3, this.b("self.parent.customTarget1==null"));
                this.b(j3, this.b("self.parent.customTarget1==self.parent"));
                this.b(j3, this.b("self.parent.customTarget1!=self"));
            }
            this.a((y)j11, this.b("self.getOffsetAbsolute(y=10).y"), j11.ep + 10.0f);
            this.b(j11, this.b("self.getOffsetAbsolute(y=10).y==self.y+10"));
            this.b(j11, this.b("self.getOffsetRelative(y=10, height=5).height==self.height+5"));
            this.a((y)j11, this.b("self.getOffsetRelative(y=10, height=5).height"), j11.eq + 5.0f);
            this.a((y)j11, this.b("self.getOffsetAbsolute(y=10).getOffsetAbsolute(y=10).y"), j11.ep + 10.0f + 10.0f);
            this.a((y)j7, this.b("self.transporting().getOffsetAbsolute(x=5).x"), j8.eo + 5.0f);
            this.a((y)j7, this.b("self.transporting(slot=1).getOffsetAbsolute(x=5).x"), j9.eo + 5.0f);
            this.a((y)j7, this.b("self.transporting().parent.transporting().parent.id"), j7.eh);
            this.a((y)j7, this.b("self.transporting().getOffsetAbsolute(x=memory.numA).x-memory.numA"), j8.eo);
            this.a((y)j7, this.b("self.transporting().getOffsetAbsolute(x=self.id).x-self.id"), j8.eo);
            this.a((y)j7, this.b("self.transporting().parent.transporting().getOffsetAbsolute(x=self.id).x-self.id"), j8.eo);
            this.a((y)j7, this.b("self.transporting().parent.transporting().getOffsetAbsolute(x=self.id).getOffsetAbsolute().x-self.id"), j8.eo);
            this.a((y)j7, this.b("self.transporting().parent.transporting().getOffsetAbsolute(x=self.id).getOffsetAbsolute(x=self.id+1).x"), j8.eo + (float)j7.eh + (float)j7.eh + 1.0f);
            this.b(j11, this.b("numberOfUnitsInTeam(greaterThan=-2)"));
            this.b(j11, this.b("NumberOfUnitsInTeam(greaterTHAN=-2)"));
            this.a(j11, this.b("self.noUnitInTeam()"));
            this.a(j11, this.b("self.hasUnitInTeam()"));
            this.a(j11, this.b("self.hasUnitInTeam(neutralTeam=true)"));
            this.a(j11, this.b("self.shield()+self.ammo()+self.hp()>-1"));
            this.a(j11, this.b("parent.shield()+parent.ammo()+parent.hp()>-1"));
            this.a((y)j11, this.b("'hello'+'a'"), "helloa");
            this.a((y)j11, this.b("'hello'+5"), "hello5");
            this.a((y)j11, this.b("substring('hello',0,3)"), "hel");
            this.a((y)j11, this.b("substring('hello',0,100)"), "hello");
            this.a((y)j11, this.b("substring('HEllo',0,100)"), "HEllo");
            this.a((y)j11, this.b("'HEllo'"), "HEllo");
            this.a((y)j11, this.b("substring('aa',0,2)+substring('bb',0,2)"), "aabb");
            this.b(j11, this.b(" true AND true"));
            this.b(j11, this.b(" true aNd true"));
            this.b(j11, this.b(" true OR false"));
            this.b(j11, this.b(" true OR TRUE"));
            this.b(j11, this.b(" True OR  False "));
            this.b(j11, this.b(" True OR  (False) "));
            this.b(j11, this.b(" NOT FALSE "));
            this.b(j11, this.b(" NOT NOT NOT FALSE "));
            this.b(j11, this.b(" game.nukesEnabled "));
            this.b(j11, this.b(" GAME.NukesEnabled "));
            this.a((y)j11, this.b("squareRoot( 100 )"), 10.0f);
            this.a((y)j11, this.b("max(+1,2)"), 2.0f);
            this.a((y)j11, this.b("min(+1,2)"), 1.0f);
            this.a((y)j11, this.b("max(1,2)"), 2.0f);
            this.a((y)j11, this.b("min(1,2)"), 1.0f);
            this.a((y)j11, this.b("max( 1,2 )"), 2.0f);
            this.a((y)j11, this.b("min( 1,2 )"), 1.0f);
            this.a((y)j11, this.b("max(-1,2)"), 2.0f);
            this.a((y)j11, this.b("min(-1,2)"), -1.0f);
            this.a((y)j11, this.b("max( -1,2 )"), 2.0f);
            this.a((y)j11, this.b("min( -1,2 )"), -1.0f);
            this.a((y)j11, this.b("max( 3,1 )"), 3.0f);
            this.a((y)j11, this.b("min( 3,1 )"), 1.0f);
            this.a((y)j11, this.b("max( 3+3,4 )"), 6.0f);
            this.a((y)j11, this.b("min( 3+3,4 )"), 4.0f);
            this.a((y)j11, this.b("distanceSquared( 1,1,1,6 )"), 25.0f);
            this.a((y)j11, this.b("max(distanceSquared( 1,1,1,6 ), 4)"), 25.0f);
            this.a((y)j11, this.b("min(  distanceSquared( 1,1,1 , 6 )  , 4)"), 4.0f);
            this.b(j11, this.b("distanceSquared( 1,1,1,6 )>=5*5"));
            this.b(j11, this.b("distanceSquared( 1,1,1,6 )>4*5"));
            this.b(j11, this.b("distanceSquared( 1,1,6,1 )<6*5"));
            this.b(j11, this.b("distance( 0,0,5,0 )==5"));
            this.b(j11, this.b("distance( 0,1,0,6 )==5"));
            this.a((y)j11, this.b("customTarget1"), j5);
            this.a((y)j11, this.b("customTarget1.self"), j5);
            this.a((y)j11, this.b("self.customTarget1.self"), j5);
            this.a((y)j11, this.b("customTarget1.customTarget2"), j5.bv);
            this.a((y)j11, this.b(" distanceBetween(customTarget1.customTarget2, customTarget1 ) "), f.b(j5.eo, j5.ep, j6.eo, j6.ep));
            this.a((y)j11, this.b(" distanceBetweenSquared(customTarget1.customTarget2, customTarget1 ) "), f.a(j5.eo, j5.ep, j6.eo, j6.ep));
            this.b(j11, this.b(" distanceBetween(self, nullUnit ) == 0 "));
            this.a((y)j11, this.b(" distanceBetween(customTarget1.customTarget2, nullUnit ) "), 0.0f);
            this.a((y)j11, this.b(" distanceBetween(nullUnit, nullUnit ) "), 0.0f);
            this.a((y)j11, this.b(" distanceBetween( self.getOffsetAbsolute(x=5), self.getOffsetAbsolute(x=-5) ) "), 10.0f);
            this.a((y)j11, this.b(" distanceBetweenSquared( self.getOffsetAbsolute(x=5), self.getOffsetAbsolute(x=-5) ) "), 100.0f);
            this.b(j11, this.b(" self.energy < 0.5 and customTarget2==nullUnit ", true));
            this.b(j11, this.b(" self.energy < 0.5 and customTarget2 == nullUnit ", true));
            this.b(j11, this.b(" self.energy < 0.5 and customTarget1 != nullUnit ", true));
            this.b(j11, this.b("parent==nullUnit and customTarget1!= nullUnit ", true));
            this.b(j11, this.b("parent == nullUnit and customTarget1!=nullUnit ", true));
            this.a("distanceBetween( self )");
            this.a("distanceBetween( self, 5 )");
            this.a("distanceBetween( self, nullUnit, nullUnit )");
            this.a("distanceBetween(  )");
            this.b(j11, this.b("'and'=='and'"));
            this.b(j11, this.b("'and'!='and true'"));
            this.b(j11, this.b("'hello.test'!='bye'"));
            this.b(j11, this.b("'hel.lo.test'!='b.ye'"));
            this.b(j11, this.b("'hel.lo.(test'!='b.ye'"));
            this.b(j11, this.b("'hel.\"lo.(test'!='b.ye \"and '"));
            this.b(j11, this.b("\"hel.lo.'(test2\"!='b.ye \"and '"));
            this.b(j11, this.b("5==5"));
            this.b(j11, this.b("'hel.lo.(test'!='b.ye' and 5==5"));
            this.a("distanceSquared(  )");
            this.a("distanceSquared( 1 )");
            this.a("distanceSquared( 1,1 )");
            this.a("distanceSquared( 1,1,1 )");
            this.a("distanceSquared( 1,1,1,'test' )");
            this.a("distanceSquared( 1,1,1,true )");
            this.a("distanceSquared( 1,1,1,null )");
            this.a("distanceSquared( 1,1,1, )");
            this.a("distanceSquared( 1,1,1,'test' )");
            this.a("distanceSquared( x1=1,1,1,'test' )");
            this.a("distanceSquared( 1,1,1,1, x1=1 )");
            this.a("distanceSquared( 1,1,1,1,1 )");
            this.a("distanceSquared( 1,1,1, x1=1 )");
            this.a("distanceSquared( 1,1,1, 1 ");
            this.a("distanceSquared( 1,1,1, 1 ))");
            this.b(j11, this.b("SELF.HP(lessThan=9999)"));
            this.c(j11, this.b("Self.Parent.HP(lessThan=9999)"));
            this.a("self.hasFlag( id=35 )");
            this.a("self.hasFlag( 35 )");
            this.c(j11, this.b("self.hasFlag(id=30)"));
            this.c(j11, this.b("self.hasFlag(30)"));
            this.c(j11, this.b("self.hasFlag(15+15)"));
            this.c(j11, this.b("self.hasFlag(id=15*2)"));
        }
        Long l4 = br.a();
        double d2 = br.a(l3, (long)l4);
        com.corrodinggames.rts.gameFramework.l.e("Took: " + d2);
        com.corrodinggames.rts.gameFramework.l.e("=== logic boolean memory tests ==");
        this.a(l2, "unit testUnit1, float testFloat1");
        this.a(l2, "unit testUnit2, float testFloat2");
        this.a(l2, "bool testBool1");
        this.a(l2, "number testNumber1");
        this.a(l2, "float  testNumber2");
        this.a(l2, "String testString");
        this.a(l2, "String nullString");
        this.a((am)j4, "testString='(,,((', testFloat1=5, testFloat2=8, testBool1=true, testUnit1=self");
        this.a((am)j4, "nullString=null");
        this.b((am)j2, "testNumber1=null");
        this.b((am)j2, "testNumber2=null");
        this.b((am)j2, "testBool1=null");
        this.b((am)j2, "testNumber1=self");
        this.b((am)j2, "testBool1=5");
        com.corrodinggames.rts.gameFramework.l.e(j4.bw.debugMemory(false, true));
        this.a((y)j4, this.b("memory.testFloat1"), 5.0f);
        this.a((y)j4, this.b("memory.testFloat2"), 8.0f);
        this.b(j4, this.b("memory.testFloat1==5"));
        this.b(j4, this.b("memory.testString=='(,,(('"));
        this.b(j4, this.b("memory.testBool1"));
        this.b(j4, this.b("memory.testBool1==true"));
        this.b(j4, this.b("memory.testUnit1==self"));
        this.b(j4, this.b("memory.testUnit1!=nullUnit"));
        this.a("memory.testUnit1==5", true);
        this.a((y)j4, this.b("self.readUnitMemory('testFloat1', type='float')"), 5.0f);
        this.a((am)j4, "testFloat1=distance( 0,0,6,0 ), testFloat2=16");
        this.b(j4, this.b("memory.testFloat1==6"));
        this.b(j4, this.b("memory.testFloat2==16"));
        this.a((am)j4, "testUnit1=self.getOffsetAbsolute(y=10), testUnit2=self.getOffsetAbsolute(y=-10)");
        com.corrodinggames.rts.gameFramework.l.e(j4.bw.debugMemory(false, true));
        this.a((y)j4, this.b("distanceBetween( memory.testUnit1, memory.testUnit2)"), 20.0f);
        this.b(j4, this.b("distanceBetweenSquared( memory.testUnit1, memory.testUnit2)==20*20"));
        this.a((y)j4, this.b("globalSearchForFirstUnit(withTag='globalTag1')"), j10);
        this.a((y)j4, this.b("globalSearchForFirstUnit(withTag='globalTag2')"), j10);
        this.b(j4, this.b("globalSearchForFirstUnit()!=null"));
        this.b(j4, this.b("globalSearchForFirstUnit(withTag='globalTag1', relation='enemy')==null"));
        this.b(j4, this.b("globalSearchForFirstUnit(withTag='globalTagNo')==null"));
        this.a("globalSearchForFirstUnit(withTag='globalTag1', relation='XYZ')", true);
    }

    public void a(String string) {
        this.a(string, false);
    }

    public void a(String string, boolean bl2) {
        try {
            com.corrodinggames.rts.game.units.custom.l l2 = com.corrodinggames.rts.game.units.custom.l.b;
            LogicBoolean logicBoolean = LogicBooleanLoader.parseBooleanBlock(l2, string, false);
        }
        catch (RuntimeException runtimeException) {
            if (runtimeException.getClass() != RuntimeException.class && runtimeException.getClass() != BooleanParseException.class) {
                throw new RuntimeException(runtimeException);
            }
            if (bl2) {
                com.corrodinggames.rts.gameFramework.l.d("assertCreateError: " + string + " error:" + runtimeException.getMessage());
            }
            return;
        }
        throw new RuntimeException("assertCreateError got no error for: " + string);
    }

    public void a(com.corrodinggames.rts.game.units.custom.l l2, String string) {
        l2.r.defineVariables(l2, string);
    }

    public void a(am am2, String string) {
        try {
            j j2 = (j)am2;
            VariableScope$MemoryWriter variableScope$MemoryWriter = VariableScope.createMemoryWriter(string, j2.x, "testsection", "testkey");
            variableScope$MemoryWriter.writeToUnit(j2);
        }
        catch (bo bo2) {
            throw new RuntimeException(bo2);
        }
    }

    public void b(am am2, String string) {
        try {
            j j2 = (j)am2;
            VariableScope$MemoryWriter variableScope$MemoryWriter = VariableScope.createMemoryWriter(string, j2.x, "testsection", "testkey");
            variableScope$MemoryWriter.writeToUnit(j2);
        }
        catch (RuntimeException runtimeException) {
            return;
        }
        catch (bo bo2) {
            return;
        }
        throw new RuntimeException("assertSetMemoryError got no error for: " + string);
    }

    public LogicBoolean b(String string) {
        return this.b(string, false);
    }

    public LogicBoolean b(String string, boolean bl2) {
        try {
            com.corrodinggames.rts.game.units.custom.l l2 = com.corrodinggames.rts.game.units.custom.l.b;
            LogicBoolean logicBoolean = LogicBooleanLoader.parseBooleanBlock(l2, string, bl2);
            return logicBoolean;
        }
        catch (RuntimeException runtimeException) {
            throw new RuntimeException("Error: " + runtimeException.getMessage() + " parsing [" + string + "]", runtimeException);
        }
    }

    public void a(y y2, LogicBoolean logicBoolean) {
        if (logicBoolean.getReturnType() != LogicBoolean$ReturnType.bool) {
            throw new RuntimeException("Asset assertBooleanTrue type ==" + (Object)((Object)logicBoolean.getReturnType()));
        }
        logicBoolean.read(y2);
    }

    public void b(y y2, LogicBoolean logicBoolean) {
        if (logicBoolean.getReturnType() != LogicBoolean$ReturnType.bool) {
            throw new RuntimeException("Asset assertBooleanTrue type ==" + (Object)((Object)logicBoolean.getReturnType()));
        }
        boolean bl2 = logicBoolean.read(y2);
        if (!bl2) {
            throw new RuntimeException("Asset assertBooleanTrue failed, got false for: " + logicBoolean.getMatchFailReasonForPlayer(y2));
        }
    }

    public void c(y y2, LogicBoolean logicBoolean) {
        if (logicBoolean.getReturnType() != LogicBoolean$ReturnType.bool) {
            throw new RuntimeException("Asset assertBooleanFalse type ==" + (Object)((Object)logicBoolean.getReturnType()));
        }
        n.b(logicBoolean.read(y2));
    }

    public void a(y y2, LogicBoolean logicBoolean, float f2) {
        if (logicBoolean.getReturnType() != LogicBoolean$ReturnType.number) {
            throw new RuntimeException("Asset assertBooleanNumber type ==" + (Object)((Object)logicBoolean.getReturnType()));
        }
        float f3 = logicBoolean.readNumber(y2);
        if (f.c(f3 - f2) > 0.001f) {
            throw new RuntimeException("Asset failed (float):" + f3 + "!=" + f2 + " for: " + logicBoolean.getMatchFailReasonForPlayer(y2));
        }
    }

    public String d(y y2, LogicBoolean logicBoolean) {
        if (logicBoolean.getReturnType() != LogicBoolean$ReturnType.string) {
            throw new RuntimeException("Asset assertBooleanString type ==" + (Object)((Object)logicBoolean.getReturnType()));
        }
        String string = logicBoolean.readString(y2);
        return string;
    }

    public void a(y y2, LogicBoolean logicBoolean, String string) {
        if (logicBoolean.getReturnType() != LogicBoolean$ReturnType.string) {
            throw new RuntimeException("Asset assertBooleanString type ==" + (Object)((Object)logicBoolean.getReturnType()));
        }
        String string2 = logicBoolean.readString(y2);
        n.a(string2, string);
    }

    public void a(y y2, LogicBoolean logicBoolean, am am2) {
        if (logicBoolean.getReturnType() != LogicBoolean$ReturnType.unit) {
            throw new RuntimeException("Asset assertBooleanUnit type ==" + (Object)((Object)logicBoolean.getReturnType()));
        }
        am am3 = logicBoolean.readUnit(y2);
        if (am3 != am2) {
            com.corrodinggames.rts.gameFramework.l.e("class: " + logicBoolean.getClass().getName());
            throw new RuntimeException("assertBooleanUnit failed:" + am.A(am3) + "!=" + am.A(am2) + " for: " + logicBoolean.getMatchFailReasonForPlayer(y2));
        }
    }
}
