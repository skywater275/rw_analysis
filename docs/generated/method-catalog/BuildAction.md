# BuildAction — 方法目录

**源文件**: `03-deobfuscated\com\corrodinggames\rts\game\units\a\BuildAction.java`
**方法总数**: 69

---

## `K` — void K()

- **行号**: 30-39 (10 行)
- **返回**: `void`
- **参数**: ``

### 字符串常量 (语义锚点)
- `"savedSelectedUnitsCache!=null"`

### 调用的方法
- `e.a()`
- `e.clear()`
- `l.B()`

### 访问的字段
- `buildUnitType`

### 方法体 (前 10 行)
```java
        l l2 = l.B();
        if (d != null) {
            throw new RuntimeException("savedSelectedUnitsCache!=null");
        }
        d = l2.bS.bZ;
        e.clear();
        e.a(this.buildUnitType);
        l2.bS.bZ = e;
    }

```

---

## `L` — void L()

- **行号**: 41-49 (9 行)
- **返回**: `void`
- **参数**: ``

### 字符串常量 (语义锚点)
- `"savedSelectedUnitsCache==null"`

### 调用的方法
- `e.clear()`
- `l.B()`

### 方法体 (前 10 行)
```java
        l l2 = l.B();
        if (d == null) {
            throw new RuntimeException("savedSelectedUnitsCache==null");
        }
        l2.bS.bZ = d;
        d = null;
        e.clear();
    }

    @Override
```

---

## `m_` — float m_()

- **行号**: 52-54 (3 行)
- **返回**: `float`
- **参数**: ``

### 调用的方法
- `super.m_()`

### 方法体 (前 10 行)
```java
        return super.m_();
    }

    @Override
    public int a(GameAction s2) {
        return super.a(s2);
    }

    @Override
    public String b() {
```

---

## `a` — int a(GameAction s2)

- **行号**: 57-59 (3 行)
- **返回**: `int`
- **参数**: `GameAction s2`

### 调用的方法
- `super.a()`

### 方法体 (前 10 行)
```java
        return super.a(s2);
    }

    @Override
    public String b() {
        return this.buildTargetPos.b();
    }

    @Override
    public String d(UnitInstance am2) {
```

---

## `b` — String b()

- **行号**: 62-64 (3 行)
- **返回**: `String`
- **参数**: ``

### 调用的方法
- `buildTargetPos.b()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.b();
    }

    @Override
    public String d(UnitInstance am2) {
        return this.buildTargetPos.d(this.buildUnitType);
    }

    @Override
    public String a() {
```

---

## `d` — String d(UnitInstance am2)

- **行号**: 67-69 (3 行)
- **返回**: `String`
- **参数**: `UnitInstance am2`

### 调用的方法
- `buildTargetPos.d()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.d(this.buildUnitType);
    }

    @Override
    public String a() {
        String string = this.buildTargetPos.a();
        return string;
    }

    @Override
```

---

## `a` — String a()

- **行号**: 72-75 (4 行)
- **返回**: `String`
- **参数**: ``

### 调用的方法
- `buildTargetPos.a()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        String string = this.buildTargetPos.a();
        return string;
    }

    @Override
    public String e(UnitInstance am2) {
        return this.buildTargetPos.e(this.buildUnitType);
    }

    @Override
```

---

## `e` — String e(UnitInstance am2)

- **行号**: 78-80 (3 行)
- **返回**: `String`
- **参数**: `UnitInstance am2`

### 调用的方法
- `buildTargetPos.e()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.e(this.buildUnitType);
    }

    @Override
    public int c() {
        return this.buildTargetPos.c();
    }

    @Override
    public int b(UnitInstance am2, boolean bl) {
```

---

## `c` — int c()

- **行号**: 83-85 (3 行)
- **返回**: `int`
- **参数**: ``

### 调用的方法
- `buildTargetPos.c()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.c();
    }

    @Override
    public int b(UnitInstance am2, boolean bl) {
        return this.buildTargetPos.b(this.buildUnitType, bl);
    }

    @Override
    public boolean n_() {
```

---

## `b` — int b(UnitInstance am2, boolean bl)

- **行号**: 88-90 (3 行)
- **返回**: `int`
- **参数**: `UnitInstance am2, boolean bl`

### 调用的方法
- `buildTargetPos.b()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.b(this.buildUnitType, bl);
    }

    @Override
    public boolean n_() {
        return this.buildTargetPos.n_();
    }

    @Override
    public boolean a(UnitInstance am2, boolean bl) {
```

---

## `n_` — boolean n_()

- **行号**: 93-95 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `buildTargetPos.n_()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.n_();
    }

    @Override
    public boolean a(UnitInstance am2, boolean bl) {
        return this.buildTargetPos.a((UnitInstance) this.buildUnitType, bl);
    }

    @Override
    public int t() {
```

---

## `a` — boolean a(UnitInstance am2, boolean bl)

- **行号**: 98-100 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2, boolean bl`

### 调用的方法
- `buildTargetPos.a()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.a((UnitInstance) this.buildUnitType, bl);
    }

    @Override
    public int t() {
        return this.buildTargetPos.t();
    }

    @Override
    public void f(UnitInstance am2) {
```

---

## `t` — int t()

- **行号**: 103-105 (3 行)
- **返回**: `int`
- **参数**: ``

### 调用的方法
- `buildTargetPos.t()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.t();
    }

    @Override
    public void f(UnitInstance am2) {
        this.buildTargetPos.f(this.buildUnitType);
    }

    @Override
    public boolean equals(Object object) {
```

---

## `f` — void f(UnitInstance am2)

- **行号**: 108-110 (3 行)
- **返回**: `void`
- **参数**: `UnitInstance am2`

### 调用的方法
- `buildTargetPos.f()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        this.buildTargetPos.f(this.buildUnitType);
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }

    @Override
    public boolean g(UnitInstance am2) {
```

---

## `equals` — boolean equals(Object object)

- **行号**: 113-115 (3 行)
- **返回**: `boolean`
- **参数**: `Object object`

### 调用的方法
- `super.equals()`

### 方法体 (前 10 行)
```java
        return super.equals(object);
    }

    @Override
    public boolean g(UnitInstance am2) {
        return this.buildTargetPos.g(this.buildUnitType);
    }

    @Override
    public boolean u() {
```

---

## `g` — boolean g(UnitInstance am2)

- **行号**: 118-120 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `buildTargetPos.g()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.g(this.buildUnitType);
    }

    @Override
    public boolean u() {
        return this.buildTargetPos.u();
    }

    @Override
    public boolean h() {
```

---

## `u` — boolean u()

- **行号**: 123-125 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `buildTargetPos.u()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.u();
    }

    @Override
    public boolean h() {
        return this.buildTargetPos.h();
    }

    @Override
    public as i() {
```

---

## `h` — boolean h()

- **行号**: 128-130 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `buildTargetPos.h()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.h();
    }

    @Override
    public as i() {
        return this.buildTargetPos.i();
    }

    @Override
    public boolean g() {
```

---

## `i` — as i()

- **行号**: 133-135 (3 行)
- **返回**: `as`
- **参数**: ``

### 调用的方法
- `buildTargetPos.i()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.i();
    }

    @Override
    public boolean g() {
        return this.buildTargetPos.g();
    }

    @Override
    public u e() {
```

---

## `g` — boolean g()

- **行号**: 138-140 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `buildTargetPos.g()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.g();
    }

    @Override
    public u e() {
        return this.buildTargetPos.e();
    }

    @Override
    public t f() {
```

---

## `e` — u e()

- **行号**: 143-145 (3 行)
- **返回**: `u`
- **参数**: ``

### 调用的方法
- `buildTargetPos.e()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.e();
    }

    @Override
    public t f() {
        return this.buildTargetPos.f();
    }

    @Override
    public String d() {
```

---

## `f` — t f()

- **行号**: 148-150 (3 行)
- **返回**: `t`
- **参数**: ``

### 调用的方法
- `buildTargetPos.f()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.f();
    }

    @Override
    public String d() {
        this.K();
        String string = this.buildTargetPos.d();
        this.L();
        return string;
    }
```

---

## `d` — String d()

- **行号**: 153-158 (6 行)
- **返回**: `String`
- **参数**: ``

### 调用的方法
- `buildTargetPos.d()`
- `this.K()`
- `this.L()`

### 访问的字段
- `K`, `L`, `buildTargetPos`

### 方法体 (前 10 行)
```java
        this.K();
        String string = this.buildTargetPos.d();
        this.L();
        return string;
    }

    @Override
    public boolean h_() {
        return this.buildTargetPos.h_();
    }
```

---

## `h_` — boolean h_()

- **行号**: 161-163 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `buildTargetPos.h_()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.h_();
    }

    @Override
    public void a(UnitInstance am2, ThemeColors ae2, Paint paint, Paint paint2) {
        this.K();
        this.buildTargetPos.a(this.buildUnitType, ae2, paint, paint2);
        this.L();
    }

```

---

## `a` — void a(UnitInstance am2, ThemeColors ae2, Paint paint, Paint paint2)

- **行号**: 166-170 (5 行)
- **返回**: `void`
- **参数**: `UnitInstance am2, ThemeColors ae2, Paint paint, Paint paint2`

### 调用的方法
- `buildTargetPos.a()`
- `this.K()`
- `this.L()`

### 访问的字段
- `K`, `L`, `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        this.K();
        this.buildTargetPos.a(this.buildUnitType, ae2, paint, paint2);
        this.L();
    }

    @Override
    public void a(UnitInstance am2, ThemeColors ae2) {
        this.K();
        this.buildTargetPos.a((UnitInstance) this.buildUnitType, ae2);
        this.L();
```

---

## `a` — void a(UnitInstance am2, ThemeColors ae2)

- **行号**: 173-177 (5 行)
- **返回**: `void`
- **参数**: `UnitInstance am2, ThemeColors ae2`

### 调用的方法
- `buildTargetPos.a()`
- `this.K()`
- `this.L()`

### 访问的字段
- `K`, `L`, `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        this.K();
        this.buildTargetPos.a((UnitInstance) this.buildUnitType, ae2);
        this.L();
    }

    @Override
    public e j() {
        return this.buildTargetPos.j();
    }

```

---

## `j` — e j()

- **行号**: 180-182 (3 行)
- **返回**: `e`
- **参数**: ``

### 调用的方法
- `buildTargetPos.j()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.j();
    }

    @Override
    public e h(UnitInstance am2) {
        return this.buildTargetPos.h(am2);
    }

    @Override
    public Rect v() {
```

---

## `h` — e h(UnitInstance am2)

- **行号**: 185-187 (3 行)
- **返回**: `e`
- **参数**: `UnitInstance am2`

### 调用的方法
- `buildTargetPos.h()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.h(am2);
    }

    @Override
    public Rect v() {
        return this.buildTargetPos.v();
    }

    @Override
    public am i(UnitInstance am2) {
```

---

## `v` — Rect v()

- **行号**: 190-192 (3 行)
- **返回**: `Rect`
- **参数**: ``

### 调用的方法
- `buildTargetPos.v()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.v();
    }

    @Override
    public am i(UnitInstance am2) {
        return this.buildTargetPos.i(this.buildUnitType);
    }

    public int hashCode() {
        return this.buildTargetPos.hashCode();
```

---

## `i` — am i(UnitInstance am2)

- **行号**: 195-197 (3 行)
- **返回**: `am`
- **参数**: `UnitInstance am2`

### 调用的方法
- `buildTargetPos.i()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.i(this.buildUnitType);
    }

    public int hashCode() {
        return this.buildTargetPos.hashCode();
    }

    public String toString() {
        return this.buildTargetPos.toString();
    }
```

---

## `hashCode` — int hashCode()

- **行号**: 199-201 (3 行)
- **返回**: `int`
- **参数**: ``

### 调用的方法
- `buildTargetPos.hashCode()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.hashCode();
    }

    public String toString() {
        return this.buildTargetPos.toString();
    }

    public BuildAction(GameAction s2, UnitType y2, ActionId c2) {
        super(c2);
        this.buildTargetPos = s2;
```

---

## `toString` — String toString()

- **行号**: 203-205 (3 行)
- **返回**: `String`
- **参数**: ``

### 调用的方法
- `buildTargetPos.toString()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.toString();
    }

    public BuildAction(GameAction s2, UnitType y2, ActionId c2) {
        super(c2);
        this.buildTargetPos = s2;
        this.buildUnitType = y2;
        this.g = this.buildTargetPos.g;
    }

```

---

## `BuildAction` — public BuildAction(GameAction s2, UnitType y2, ActionId c2)

- **行号**: 207-212 (6 行)
- **返回**: `public`
- **参数**: `GameAction s2, UnitType y2, ActionId c2`

### 访问的字段
- `buildTargetPos`, `buildUnitType`, `g`

### 方法体 (前 10 行)
```java
        super(c2);
        this.buildTargetPos = s2;
        this.buildUnitType = y2;
        this.g = this.buildTargetPos.g;
    }

    public s p_() {
        return this.buildTargetPos;
    }

```

---

## `p_` — s p_()

- **行号**: 214-216 (3 行)
- **返回**: `s`
- **参数**: ``

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos;
    }

    @Override
    public boolean x() {
        return this.buildTargetPos.x();
    }

    @Override
    public boolean s() {
```

---

## `x` — boolean x()

- **行号**: 219-221 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `buildTargetPos.x()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.x();
    }

    @Override
    public boolean s() {
        return this.buildTargetPos.s();
    }

    @Override
    public as y() {
```

---

## `s` — boolean s()

- **行号**: 224-226 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `buildTargetPos.s()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.s();
    }

    @Override
    public as y() {
        return this.buildTargetPos.y();
    }

    @Override
    public c z() {
```

---

## `y` — as y()

- **行号**: 229-231 (3 行)
- **返回**: `as`
- **参数**: ``

### 调用的方法
- `buildTargetPos.y()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.y();
    }

    @Override
    public c z() {
        return this.buildTargetPos.N();
    }

    @Override
    public void a(UnitInstance am2, UnitInstance am3) {
```

---

## `z` — c z()

- **行号**: 234-236 (3 行)
- **返回**: `c`
- **参数**: ``

### 调用的方法
- `buildTargetPos.N()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.N();
    }

    @Override
    public void a(UnitInstance am2, UnitInstance am3) {
        super.a(am2, am3);
    }

    @Override
    public boolean a(UnitInstance am2, PlayerState n2) {
```

---

## `a` — void a(UnitInstance am2, UnitInstance am3)

- **行号**: 239-241 (3 行)
- **返回**: `void`
- **参数**: `UnitInstance am2, UnitInstance am3`

### 调用的方法
- `super.a()`

### 方法体 (前 10 行)
```java
        super.a(am2, am3);
    }

    @Override
    public boolean a(UnitInstance am2, PlayerState n2) {
        return this.buildTargetPos.a((UnitInstance) this.buildUnitType, n2);
    }

    @Override
    public boolean A() {
```

---

## `a` — boolean a(UnitInstance am2, PlayerState n2)

- **行号**: 244-246 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2, PlayerState n2`

### 调用的方法
- `buildTargetPos.a()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.a((UnitInstance) this.buildUnitType, n2);
    }

    @Override
    public boolean A() {
        return this.buildTargetPos.A();
    }

    @Override
    public boolean a(UnitInstance am2) {
```

---

## `A` — boolean A()

- **行号**: 249-251 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `buildTargetPos.A()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.A();
    }

    @Override
    public boolean a(UnitInstance am2) {
        return this.buildTargetPos.a((UnitInstance) this.buildUnitType);
    }

    @Override
    public com.corrodinggames.rts.game.units.custom.d.b B() {
```

---

## `a` — boolean a(UnitInstance am2)

- **行号**: 254-256 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `buildTargetPos.a()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.a((UnitInstance) this.buildUnitType);
    }

    @Override
    public com.corrodinggames.rts.game.units.custom.d.b B() {
        return this.buildTargetPos.B();
    }

    @Override
    public String j(UnitInstance am2) {
```

---

## `B` — com.corrodinggames.rts.game.units.custom.d.b B()

- **行号**: 259-261 (3 行)
- **返回**: `com.corrodinggames.rts.game.units.custom.d.b`
- **参数**: ``

### 调用的方法
- `buildTargetPos.B()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.B();
    }

    @Override
    public String j(UnitInstance am2) {
        return this.buildTargetPos.j(this.buildUnitType);
    }

    @Override
    public boolean d(UnitInstance am2, boolean bl) {
```

---

## `j` — String j(UnitInstance am2)

- **行号**: 264-266 (3 行)
- **返回**: `String`
- **参数**: `UnitInstance am2`

### 调用的方法
- `buildTargetPos.j()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.j(this.buildUnitType);
    }

    @Override
    public boolean d(UnitInstance am2, boolean bl) {
        return this.buildTargetPos.d(this.buildUnitType, bl);
    }

    @Override
    public boolean k(UnitInstance am2) {
```

---

## `d` — boolean d(UnitInstance am2, boolean bl)

- **行号**: 269-271 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2, boolean bl`

### 调用的方法
- `buildTargetPos.d()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.d(this.buildUnitType, bl);
    }

    @Override
    public boolean k(UnitInstance am2) {
        return this.buildTargetPos.k(this.buildUnitType);
    }

    @Override
    public boolean l(UnitInstance am2) {
```

---

## `k` — boolean k(UnitInstance am2)

- **行号**: 274-276 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `buildTargetPos.k()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.k(this.buildUnitType);
    }

    @Override
    public boolean l(UnitInstance am2) {
        return this.buildTargetPos.l(this.buildUnitType);
    }

    @Override
    public boolean C() {
```

---

## `l` — boolean l(UnitInstance am2)

- **行号**: 279-281 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `buildTargetPos.l()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.l(this.buildUnitType);
    }

    @Override
    public boolean C() {
        return this.buildTargetPos.C();
    }

    @Override
    public boolean D() {
```

---

## `C` — boolean C()

- **行号**: 284-286 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `buildTargetPos.C()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.C();
    }

    @Override
    public boolean D() {
        return this.buildTargetPos.D();
    }

    @Override
    public as E() {
```

---

## `D` — boolean D()

- **行号**: 289-291 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `buildTargetPos.D()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.D();
    }

    @Override
    public as E() {
        return this.buildTargetPos.E();
    }

    @Override
    public boolean F() {
```

---

## `E` — as E()

- **行号**: 294-296 (3 行)
- **返回**: `as`
- **参数**: ``

### 调用的方法
- `buildTargetPos.E()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.E();
    }

    @Override
    public boolean F() {
        return this.buildTargetPos.F();
    }

    @Override
    public boolean m(UnitInstance am2) {
```

---

## `F` — boolean F()

- **行号**: 299-301 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `buildTargetPos.F()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.F();
    }

    @Override
    public boolean m(UnitInstance am2) {
        return this.buildTargetPos.m(this.buildUnitType);
    }

    @Override
    public boolean n(UnitInstance am2) {
```

---

## `m` — boolean m(UnitInstance am2)

- **行号**: 304-306 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `buildTargetPos.m()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.m(this.buildUnitType);
    }

    @Override
    public boolean n(UnitInstance am2) {
        return this.buildTargetPos.n(this.buildUnitType);
    }

    @Override
    public boolean c(UnitInstance am2, boolean bl) {
```

---

## `n` — boolean n(UnitInstance am2)

- **行号**: 309-311 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `buildTargetPos.n()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.n(this.buildUnitType);
    }

    @Override
    public boolean c(UnitInstance am2, boolean bl) {
        return this.buildTargetPos.c(this.buildUnitType, bl);
    }

    @Override
    public boolean o(UnitInstance am2) {
```

---

## `c` — boolean c(UnitInstance am2, boolean bl)

- **行号**: 314-316 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2, boolean bl`

### 调用的方法
- `buildTargetPos.c()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.c(this.buildUnitType, bl);
    }

    @Override
    public boolean o(UnitInstance am2) {
        return this.buildTargetPos.o(this.buildUnitType);
    }

    @Override
    public boolean G() {
```

---

## `o` — boolean o(UnitInstance am2)

- **行号**: 319-321 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `buildTargetPos.o()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.o(this.buildUnitType);
    }

    @Override
    public boolean G() {
        return this.buildTargetPos.G();
    }

    @Override
    public void c(UnitInstance am2) {
```

---

## `G` — boolean G()

- **行号**: 324-326 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `buildTargetPos.G()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.G();
    }

    @Override
    public void c(UnitInstance am2) {
        this.buildTargetPos.c(this.buildUnitType);
    }

    @Override
    public float l() {
```

---

## `c` — void c(UnitInstance am2)

- **行号**: 329-331 (3 行)
- **返回**: `void`
- **参数**: `UnitInstance am2`

### 调用的方法
- `buildTargetPos.c()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        this.buildTargetPos.c(this.buildUnitType);
    }

    @Override
    public float l() {
        return this.buildTargetPos.l();
    }

    @Override
    public int m() {
```

---

## `l` — float l()

- **行号**: 334-336 (3 行)
- **返回**: `float`
- **参数**: ``

### 调用的方法
- `buildTargetPos.l()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.l();
    }

    @Override
    public int m() {
        return this.buildTargetPos.m();
    }

    @Override
    public boolean H() {
```

---

## `m` — int m()

- **行号**: 339-341 (3 行)
- **返回**: `int`
- **参数**: ``

### 调用的方法
- `buildTargetPos.m()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.m();
    }

    @Override
    public boolean H() {
        return this.buildTargetPos.H();
    }

    @Override
    public boolean I() {
```

---

## `H` — boolean H()

- **行号**: 344-346 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `buildTargetPos.H()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.H();
    }

    @Override
    public boolean I() {
        return this.buildTargetPos.I();
    }

    @Override
    public float p(UnitInstance am2) {
```

---

## `I` — boolean I()

- **行号**: 349-351 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `buildTargetPos.I()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.I();
    }

    @Override
    public float p(UnitInstance am2) {
        return this.buildTargetPos.p(this.buildUnitType);
    }

    @Override
    public ArrayList q(UnitInstance am2) {
```

---

## `p` — float p(UnitInstance am2)

- **行号**: 354-356 (3 行)
- **返回**: `float`
- **参数**: `UnitInstance am2`

### 调用的方法
- `buildTargetPos.p()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.p(this.buildUnitType);
    }

    @Override
    public ArrayList q(UnitInstance am2) {
        return this.buildTargetPos.q(this.buildUnitType);
    }

    @Override
    public boolean r(UnitInstance am2) {
```

---

## `q` — ArrayList q(UnitInstance am2)

- **行号**: 359-361 (3 行)
- **返回**: `ArrayList`
- **参数**: `UnitInstance am2`

### 调用的方法
- `buildTargetPos.q()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.q(this.buildUnitType);
    }

    @Override
    public boolean r(UnitInstance am2) {
        if (!this.buildStages.isAvailable(this, am2)) {
            return false;
        }
        return this.buildTargetPos.r(this.buildUnitType);
    }
```

---

## `r` — boolean r(UnitInstance am2)

- **行号**: 364-369 (6 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `buildStages.isAvailable()`
- `buildTargetPos.r()`

### 访问的字段
- `buildStages`, `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        if (!this.buildStages.isAvailable(this, am2)) {
            return false;
        }
        return this.buildTargetPos.r(this.buildUnitType);
    }

    @Override
    public boolean b(UnitInstance am2) {
        if (!this.buildStages.isAvailable(this, am2)) {
            return false;
```

---

## `b` — boolean b(UnitInstance am2)

- **行号**: 372-377 (6 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `buildStages.isAvailable()`
- `buildTargetPos.b()`

### 访问的字段
- `buildStages`, `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        if (!this.buildStages.isAvailable(this, am2)) {
            return false;
        }
        return this.buildTargetPos.b(this.buildUnitType);
    }

    @Override
    public int J() {
        return this.buildTargetPos.J();
    }
```

---

## `J` — int J()

- **行号**: 380-382 (3 行)
- **返回**: `int`
- **参数**: ``

### 调用的方法
- `buildTargetPos.J()`

### 访问的字段
- `buildTargetPos`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.J();
    }

    @Override
    public boolean s(UnitInstance am2) {
        return this.buildTargetPos.s(this.buildUnitType);
    }

    @Override
    public boolean t(UnitInstance am2) {
```

---

## `s` — boolean s(UnitInstance am2)

- **行号**: 385-387 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `buildTargetPos.s()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.s(this.buildUnitType);
    }

    @Override
    public boolean t(UnitInstance am2) {
        return this.buildTargetPos.t(this.buildUnitType);
    }

    public boolean a(BuildAction g2) {
        return this.buildTargetPos == g2.a && this.buildUnitType == g2.b && this.N() == g2.N() && this.buildStages == g2.c;
```

---

## `t` — boolean t(UnitInstance am2)

- **行号**: 390-392 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `buildTargetPos.t()`

### 访问的字段
- `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos.t(this.buildUnitType);
    }

    public boolean a(BuildAction g2) {
        return this.buildTargetPos == g2.a && this.buildUnitType == g2.b && this.N() == g2.N() && this.buildStages == g2.c;
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.buildTargetPos((GameAction) object);
```

---

## `a` — boolean a(BuildAction g2)

- **行号**: 394-396 (3 行)
- **返回**: `boolean`
- **参数**: `BuildAction g2`

### 调用的方法
- `g2.N()`
- `this.N()`

### 访问的字段
- `N`, `buildStages`, `buildTargetPos`, `buildUnitType`

### 方法体 (前 10 行)
```java
        return this.buildTargetPos == g2.a && this.buildUnitType == g2.b && this.N() == g2.N() && this.buildStages == g2.c;
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.buildTargetPos((GameAction) object);
    }

    static {
        e = new com.corrodinggames.rts.gameFramework.utility.u();
```

---
