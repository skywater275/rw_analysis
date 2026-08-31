# BuildQueueAction — 方法目录

**源文件**: `03-deobfuscated\com\corrodinggames\rts\game\units\a\BuildQueueAction.java`
**方法总数**: 22

---

## `equals` — boolean equals(Object object)

- **行号**: 23-38 (16 行)
- **返回**: `boolean`
- **参数**: `Object object`

### 调用的方法
- `object.getClass()`
- `super.equals()`
- `this.getClass()`

### 访问的字段
- `a`, `b`, `getClass`

### 方法体 (前 10 行)
```java
        if (this == object) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        v v2 = (BuildQueueAction) object;
        if (this.b != v2.b) {
            return false;
        }
```

---

## `BuildQueueAction` — public BuildQueueAction(UnitTypeHandle as2)

- **行号**: 40-42 (3 行)
- **返回**: `public`
- **参数**: `UnitTypeHandle as2`

### 方法体 (前 10 行)
```java
        this(as2, 1, null);
    }

    public BuildQueueAction(UnitTypeHandle as2, int n2, Integer n3) {
        super("b_" + as2.v());
        as as3 = com.corrodinggames.rts.game.units.custom.l.c(as2);
        if (as3 != null) {
            as2 = as3;
            this.a("b_" + as2.v());
        }
```

---

## `BuildQueueAction` — public BuildQueueAction(UnitTypeHandle as2, int n2, Integer n3)

- **行号**: 44-59 (16 行)
- **返回**: `public`
- **参数**: `UnitTypeHandle as2, int n2, Integer n3`

### 调用的方法
- `as2.v()`
- `l.c()`
- `n3.intValue()`
- `this.N()`
- `this.a()`

### 访问的字段
- `N`, `a`, `b`, `g`

### 方法体 (前 10 行)
```java
        super("b_" + as2.v());
        as as3 = com.corrodinggames.rts.game.units.custom.l.c(as2);
        if (as3 != null) {
            as2 = as3;
            this.a("b_" + as2.v());
        }
        if (n2 != 1) {
            this.a(this.N() + "_" + n2);
        }
        this.a = as2;
```

---

## `i` — as i()

- **行号**: 62-64 (3 行)
- **返回**: `as`
- **参数**: ``

### 访问的字段
- `a`

### 方法体 (前 10 行)
```java
        return this.a;
    }

    @Override
    public as y() {
        return this.a;
    }

    @Override
    public int t() {
```

---

## `y` — as y()

- **行号**: 67-69 (3 行)
- **返回**: `as`
- **参数**: ``

### 访问的字段
- `a`

### 方法体 (前 10 行)
```java
        return this.a;
    }

    @Override
    public int t() {
        return this.b;
    }

    @Override
    public String a() {
```

---

## `t` — int t()

- **行号**: 72-74 (3 行)
- **返回**: `int`
- **参数**: ``

### 访问的字段
- `b`

### 方法体 (前 10 行)
```java
        return this.b;
    }

    @Override
    public String a() {
        String string = this.i().f();
        boolean bl = false;
        boolean bl2 = true;
        am am2 = am.c(this.i());
        if (this.b != 1 && am2 instanceof UnitType) {
```

---

## `a` — String a()

- **行号**: 77-90 (14 行)
- **返回**: `String`
- **参数**: ``

### 字符串常量 (语义锚点)
- `"\n\n"`

### 调用的方法
- `a.a()`
- `am.c()`
- `this.i()`

### 访问的字段
- `b`, `i`

### 方法体 (前 10 行)
```java
        String string = this.i().f();
        boolean bl = false;
        boolean bl2 = true;
        am am2 = am.c(this.i());
        if (this.b != 1 && am2 instanceof UnitType) {
            ((UnitType) am2).a(this.b);
        }
        string = string + "\n\n" + com.corrodinggames.rts.gameFramework.f.a.a(am2, false, bl, bl2);
        if (this.b != 1 && am2 instanceof UnitType) {
            ((UnitType) am2).a(1);
```

---

## `b` — String b()

- **行号**: 93-105 (13 行)
- **返回**: `String`
- **参数**: ``

### 字符串常量 (语义锚点)
- `" T-2"`
- `" T-3"`

### 调用的方法
- `this.i()`
- `this.t()`

### 访问的字段
- `i`, `t`

### 方法体 (前 10 行)
```java
        as as2 = this.i();
        String string = this.i().e();
        if (!(as2 instanceof com.corrodinggames.rts.game.units.custom.l)) {
            if (this.t() == 2) {
                string = string + " T-2";
            }
            if (this.t() == 3) {
                string = string + " T-3";
            }
        }
```

---

## `c` — int c()

- **行号**: 108-110 (3 行)
- **返回**: `int`
- **参数**: ``

### 调用的方法
- `this.B()`

### 访问的字段
- `B`

### 方法体 (前 10 行)
```java
        return this.B().a();
    }

    @Override
    public b B() {
        b b2 = this.h.a();
        if (b2 != null) {
            return b2;
        }
        return this.i().d(this.t());
```

---

## `B` — b B()

- **行号**: 113-119 (7 行)
- **返回**: `b`
- **参数**: ``

### 调用的方法
- `h.a()`
- `this.i()`
- `this.t()`

### 访问的字段
- `h`, `i`, `t`

### 方法体 (前 10 行)
```java
        b b2 = this.h.a();
        if (b2 != null) {
            return b2;
        }
        return this.i().d(this.t());
    }

    @Override
    public b r_() {
        b b2 = this.h.b();
```

---

## `r_` — b r_()

- **行号**: 122-128 (7 行)
- **返回**: `b`
- **参数**: ``

### 调用的方法
- `h.b()`
- `this.i()`

### 访问的字段
- `h`, `i`

### 方法体 (前 10 行)
```java
        b b2 = this.h.b();
        if (b2 != null) {
            return b2;
        }
        return this.i().B();
    }

    @Override
    public int b(UnitInstance am2, boolean bl) {
        return -1;
```

---

## `b` — int b(UnitInstance am2, boolean bl)

- **行号**: 131-133 (3 行)
- **返回**: `int`
- **参数**: `UnitInstance am2, boolean bl`

### 方法体 (前 10 行)
```java
        return -1;
    }

    @Override
    public u e() {
        return u.b;
    }

    @Override
    public t f() {
```

---

## `e` — u e()

- **行号**: 136-138 (3 行)
- **返回**: `u`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return u.b;
    }

    @Override
    public t f() {
        return t.e;
    }

    @Override
    public boolean n_() {
```

---

## `f` — t f()

- **行号**: 141-143 (3 行)
- **返回**: `t`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return t.e;
    }

    @Override
    public boolean n_() {
        return !this.i().C();
    }

    @Override
    public boolean g(UnitInstance am2) {
```

---

## `n_` — boolean n_()

- **行号**: 146-148 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `this.i()`

### 访问的字段
- `i`

### 方法体 (前 10 行)
```java
        return !this.i().C();
    }

    @Override
    public boolean g(UnitInstance am2) {
        l l2 = l.B();
        if ((this.i() == ar.D || this.i() == ar.C) && l2.O() && l2.bX.ay.i) {
            return true;
        }
        if (this.i().w()) {
```

---

## `g` — boolean g(UnitInstance am2)

- **行号**: 151-160 (10 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `l.B()`
- `l2.O()`
- `super.g()`
- `this.i()`

### 访问的字段
- `i`

### 方法体 (前 10 行)
```java
        l l2 = l.B();
        if ((this.i() == ar.D || this.i() == ar.C) && l2.O() && l2.bX.ay.i) {
            return true;
        }
        if (this.i().w()) {
            return true;
        }
        return super.g(am2);
    }

```

---

## `g` — boolean g()

- **行号**: 163-165 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    @Override
    public boolean u() {
        return true;
    }

    @Override
    public boolean D() {
```

---

## `u` — boolean u()

- **行号**: 168-170 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return true;
    }

    @Override
    public boolean D() {
        return false;
    }

    @Override
    public float p(UnitInstance am2) {
```

---

## `D` — boolean D()

- **行号**: 173-175 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    @Override
    public float p(UnitInstance am2) {
        if (!(am2 instanceof UnitType)) {
            return -1.0f;
        }
        y y2 = (UnitType) am2;
        am am3 = y2.X();
```

---

## `p` — float p(UnitInstance am2)

- **行号**: 178-188 (11 行)
- **返回**: `float`
- **参数**: `UnitInstance am2`

### 调用的方法
- `am3.r()`
- `this.i()`
- `y2.X()`

### 访问的字段
- `i`

### 方法体 (前 10 行)
```java
        if (!(am2 instanceof UnitType)) {
            return -1.0f;
        }
        y y2 = (UnitType) am2;
        am am3 = y2.X();
        if (am3 != null && am3.cm < 1.0f && am3.r() == this.i()) {
            return am3.cm;
        }
        return -1.0f;
    }
```

---

## `r` — boolean r(UnitInstance am2)

- **行号**: 191-193 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `h.a()`

### 访问的字段
- `h`

### 方法体 (前 10 行)
```java
        return this.h.a(am2, true);
    }

    @Override
    public boolean b(UnitInstance am2) {
        return this.h.a(am2, false);
    }
}

```

---

## `b` — boolean b(UnitInstance am2)

- **行号**: 196-198 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `h.a()`

### 访问的字段
- `h`

### 方法体 (前 10 行)
```java
        return this.h.a(am2, false);
    }
}

```

---
