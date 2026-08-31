# ActionWrapper — 方法目录

**源文件**: `03-deobfuscated\com\corrodinggames\rts\game\units\a\ActionWrapper.java`
**方法总数**: 39

---

## `m_` — float m_()

- **行号**: 30-32 (3 行)
- **返回**: `float`
- **参数**: ``

### 调用的方法
- `wrappedAction.m_()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.m_();
    }

    @Override
    public int a(GameAction s2) {
        return this.wrappedAction.a(s2);
    }

    @Override
    public String b() {
```

---

## `a` — int a(GameAction s2)

- **行号**: 35-37 (3 行)
- **返回**: `int`
- **参数**: `GameAction s2`

### 调用的方法
- `wrappedAction.a()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.a(s2);
    }

    @Override
    public String b() {
        return this.wrappedAction.b();
    }

    @Override
    public String d(UnitInstance am2) {
```

---

## `b` — String b()

- **行号**: 40-42 (3 行)
- **返回**: `String`
- **参数**: ``

### 调用的方法
- `wrappedAction.b()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.b();
    }

    @Override
    public String d(UnitInstance am2) {
        return this.wrappedAction.d(am2);
    }

    @Override
    public String a() {
```

---

## `d` — String d(UnitInstance am2)

- **行号**: 45-47 (3 行)
- **返回**: `String`
- **参数**: `UnitInstance am2`

### 调用的方法
- `wrappedAction.d()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.d(am2);
    }

    @Override
    public String a() {
        String string = this.wrappedAction.a();
        return string;
    }

    @Override
```

---

## `a` — String a()

- **行号**: 50-53 (4 行)
- **返回**: `String`
- **参数**: ``

### 调用的方法
- `wrappedAction.a()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        String string = this.wrappedAction.a();
        return string;
    }

    @Override
    public String e(UnitInstance am2) {
        return this.wrappedAction.e(am2);
    }

    @Override
```

---

## `e` — String e(UnitInstance am2)

- **行号**: 56-58 (3 行)
- **返回**: `String`
- **参数**: `UnitInstance am2`

### 调用的方法
- `wrappedAction.e()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.e(am2);
    }

    @Override
    public int c() {
        return 0;
    }

    @Override
    public int b(UnitInstance am2, boolean bl) {
```

---

## `c` — int c()

- **行号**: 61-63 (3 行)
- **返回**: `int`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return 0;
    }

    @Override
    public int b(UnitInstance am2, boolean bl) {
        return this.wrappedAction.b(am2, bl);
    }

    @Override
    public boolean n_() {
```

---

## `b` — int b(UnitInstance am2, boolean bl)

- **行号**: 66-68 (3 行)
- **返回**: `int`
- **参数**: `UnitInstance am2, boolean bl`

### 调用的方法
- `wrappedAction.b()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.b(am2, bl);
    }

    @Override
    public boolean n_() {
        return this.wrappedAction.n_();
    }

    @Override
    public boolean a(UnitInstance am2, boolean bl) {
```

---

## `n_` — boolean n_()

- **行号**: 71-73 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `wrappedAction.n_()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.n_();
    }

    @Override
    public boolean a(UnitInstance am2, boolean bl) {
        if (this.actionCooldown) {
            return this.wrappedAction.a(am2, bl);
        }
        return true;
    }
```

---

## `a` — boolean a(UnitInstance am2, boolean bl)

- **行号**: 76-81 (6 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2, boolean bl`

### 调用的方法
- `wrappedAction.a()`

### 访问的字段
- `actionCooldown`, `wrappedAction`

### 方法体 (前 10 行)
```java
        if (this.actionCooldown) {
            return this.wrappedAction.a(am2, bl);
        }
        return true;
    }

    @Override
    public int t() {
        return this.wrappedAction.t();
    }
```

---

## `t` — int t()

- **行号**: 84-86 (3 行)
- **返回**: `int`
- **参数**: ``

### 调用的方法
- `wrappedAction.t()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.t();
    }

    @Override
    public void f(UnitInstance am2) {
        this.wrappedAction.f(am2);
    }

    @Override
    public boolean equals(Object object) {
```

---

## `f` — void f(UnitInstance am2)

- **行号**: 89-91 (3 行)
- **返回**: `void`
- **参数**: `UnitInstance am2`

### 调用的方法
- `wrappedAction.f()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        this.wrappedAction.f(am2);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ActionWrapper) {
            return this.wrappedAction.equals(((ActionWrapper) object).a);
        }
        return false;
    }
```

---

## `equals` — boolean equals(Object object)

- **行号**: 94-99 (6 行)
- **返回**: `boolean`
- **参数**: `Object object`

### 调用的方法
- `wrappedAction.equals()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        if (object instanceof ActionWrapper) {
            return this.wrappedAction.equals(((ActionWrapper) object).a);
        }
        return false;
    }

    @Override
    public boolean g(UnitInstance am2) {
        return this.wrappedAction.g(am2);
    }
```

---

## `g` — boolean g(UnitInstance am2)

- **行号**: 102-104 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `wrappedAction.g()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.g(am2);
    }

    @Override
    public boolean b(UnitInstance am2) {
        if (!this.actionPriority.isAvailable(this, am2)) {
            return false;
        }
        return this.wrappedAction.b(am2);
    }
```

---

## `b` — boolean b(UnitInstance am2)

- **行号**: 107-112 (6 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `actionPriority.isAvailable()`
- `wrappedAction.b()`

### 访问的字段
- `actionPriority`, `wrappedAction`

### 方法体 (前 10 行)
```java
        if (!this.actionPriority.isAvailable(this, am2)) {
            return false;
        }
        return this.wrappedAction.b(am2);
    }

    @Override
    public boolean u() {
        return this.wrappedAction.u();
    }
```

---

## `u` — boolean u()

- **行号**: 115-117 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `wrappedAction.u()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.u();
    }

    @Override
    public boolean h() {
        return this.wrappedAction.h();
    }

    @Override
    public as i() {
```

---

## `h` — boolean h()

- **行号**: 120-122 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `wrappedAction.h()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.h();
    }

    @Override
    public as i() {
        return this.wrappedAction.i();
    }

    @Override
    public boolean g() {
```

---

## `i` — as i()

- **行号**: 125-127 (3 行)
- **返回**: `as`
- **参数**: ``

### 调用的方法
- `wrappedAction.i()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.i();
    }

    @Override
    public boolean g() {
        return this.wrappedAction.g();
    }

    @Override
    public u e() {
```

---

## `g` — boolean g()

- **行号**: 130-132 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `wrappedAction.g()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.g();
    }

    @Override
    public u e() {
        return this.wrappedAction.e();
    }

    @Override
    public t f() {
```

---

## `e` — u e()

- **行号**: 135-137 (3 行)
- **返回**: `u`
- **参数**: ``

### 调用的方法
- `wrappedAction.e()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.e();
    }

    @Override
    public t f() {
        return this.wrappedAction.f();
    }

    @Override
    public String d() {
```

---

## `f` — t f()

- **行号**: 140-142 (3 行)
- **返回**: `t`
- **参数**: ``

### 调用的方法
- `wrappedAction.f()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.f();
    }

    @Override
    public String d() {
        return this.wrappedAction.d();
    }

    @Override
    public boolean h_() {
```

---

## `d` — String d()

- **行号**: 145-147 (3 行)
- **返回**: `String`
- **参数**: ``

### 调用的方法
- `wrappedAction.d()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.d();
    }

    @Override
    public boolean h_() {
        return this.wrappedAction.h_();
    }

    @Override
    public void a(UnitInstance am2, ThemeColors ae2, Paint paint, Paint paint2) {
```

---

## `h_` — boolean h_()

- **行号**: 150-152 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `wrappedAction.h_()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.h_();
    }

    @Override
    public void a(UnitInstance am2, ThemeColors ae2, Paint paint, Paint paint2) {
        this.wrappedAction.a(am2, ae2, paint, paint2);
    }

    @Override
    public void a(UnitInstance am2, ThemeColors ae2) {
```

---

## `a` — void a(UnitInstance am2, ThemeColors ae2, Paint paint, Paint paint2)

- **行号**: 155-157 (3 行)
- **返回**: `void`
- **参数**: `UnitInstance am2, ThemeColors ae2, Paint paint, Paint paint2`

### 调用的方法
- `wrappedAction.a()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        this.wrappedAction.a(am2, ae2, paint, paint2);
    }

    @Override
    public void a(UnitInstance am2, ThemeColors ae2) {
        this.wrappedAction.a(am2, ae2);
        as as2 = this.wrappedAction.i();
        if (as2 != null && as2 instanceof ModUnitRegistry) {
            l l2 = (ModUnitRegistry) as2;
            if (l2.J != null) {
```

---

## `a` — void a(UnitInstance am2, ThemeColors ae2)

- **行号**: 160-171 (12 行)
- **返回**: `void`
- **参数**: `UnitInstance am2, ThemeColors ae2`

### 字符串常量 (语义锚点)
- `"\n(mod: "`

### 调用的方法
- `J.a()`
- `ae2.a()`
- `f.a()`
- `wrappedAction.a()`
- `wrappedAction.i()`

### 访问的字段
- `f`, `wrappedAction`

### 方法体 (前 10 行)
```java
        this.wrappedAction.a(am2, ae2);
        as as2 = this.wrappedAction.i();
        if (as2 != null && as2 instanceof ModUnitRegistry) {
            l l2 = (ModUnitRegistry) as2;
            if (l2.J != null) {
                String string = l2.J.a();
                string = com.corrodinggames.rts.gameFramework.f.a(string, 30);
                ae2.a("\n(mod: " + string + ")", this.f, true);
            }
        }
```

---

## `j` — e j()

- **行号**: 174-176 (3 行)
- **返回**: `e`
- **参数**: ``

### 调用的方法
- `wrappedAction.j()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.j();
    }

    @Override
    public e h(UnitInstance am2) {
        return this.wrappedAction.h(am2);
    }

    @Override
    public Rect v() {
```

---

## `h` — e h(UnitInstance am2)

- **行号**: 179-181 (3 行)
- **返回**: `e`
- **参数**: `UnitInstance am2`

### 调用的方法
- `wrappedAction.h()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.h(am2);
    }

    @Override
    public Rect v() {
        return this.wrappedAction.v();
    }

    @Override
    public am i(UnitInstance am2) {
```

---

## `v` — Rect v()

- **行号**: 184-186 (3 行)
- **返回**: `Rect`
- **参数**: ``

### 调用的方法
- `wrappedAction.v()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.v();
    }

    @Override
    public am i(UnitInstance am2) {
        return this.wrappedAction.i(am2);
    }

    public int hashCode() {
        return this.wrappedAction.hashCode();
```

---

## `i` — am i(UnitInstance am2)

- **行号**: 189-191 (3 行)
- **返回**: `am`
- **参数**: `UnitInstance am2`

### 调用的方法
- `wrappedAction.i()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.i(am2);
    }

    public int hashCode() {
        return this.wrappedAction.hashCode();
    }

    public String toString() {
        return this.wrappedAction.toString();
    }
```

---

## `hashCode` — int hashCode()

- **行号**: 193-195 (3 行)
- **返回**: `int`
- **参数**: ``

### 调用的方法
- `wrappedAction.hashCode()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.hashCode();
    }

    public String toString() {
        return this.wrappedAction.toString();
    }

    public ActionWrapper(GameAction s2, ActionFilter b2) {
        this(s2, b2, false);
    }
```

---

## `toString` — String toString()

- **行号**: 197-199 (3 行)
- **返回**: `String`
- **参数**: ``

### 调用的方法
- `wrappedAction.toString()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.toString();
    }

    public ActionWrapper(GameAction s2, ActionFilter b2) {
        this(s2, b2, false);
    }

    public ActionWrapper(GameAction s2, ActionFilter b2, boolean bl2) {
        super(s2.N());
        this.wrappedAction = s2;
```

---

## `ActionWrapper` — public ActionWrapper(GameAction s2, ActionFilter b2)

- **行号**: 201-203 (3 行)
- **返回**: `public`
- **参数**: `GameAction s2, ActionFilter b2`

### 方法体 (前 10 行)
```java
        this(s2, b2, false);
    }

    public ActionWrapper(GameAction s2, ActionFilter b2, boolean bl2) {
        super(s2.N());
        this.wrappedAction = s2;
        this.actionPriority = b2;
        this.actionActive(this.wrappedAction.N());
        this.g = this.wrappedAction.g;
        this.actionCooldown = bl2;
```

---

## `ActionWrapper` — public ActionWrapper(GameAction s2, ActionFilter b2, boolean bl2)

- **行号**: 205-212 (8 行)
- **返回**: `public`
- **参数**: `GameAction s2, ActionFilter b2, boolean bl2`

### 调用的方法
- `s2.N()`
- `this.actionActive()`
- `wrappedAction.N()`

### 访问的字段
- `actionActive`, `actionCooldown`, `actionPriority`, `g`, `wrappedAction`

### 方法体 (前 10 行)
```java
        super(s2.N());
        this.wrappedAction = s2;
        this.actionPriority = b2;
        this.actionActive(this.wrappedAction.N());
        this.g = this.wrappedAction.g;
        this.actionCooldown = bl2;
    }

    public s q_() {
        return this.wrappedAction;
```

---

## `q_` — s q_()

- **行号**: 214-216 (3 行)
- **返回**: `s`
- **参数**: ``

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction;
    }

    @Override
    public boolean x() {
        return true;
    }

    @Override
    public boolean s() {
```

---

## `x` — boolean x()

- **行号**: 219-221 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return true;
    }

    @Override
    public boolean s() {
        if (!this.actionPriority.isAvailable(this, null)) {
            return false;
        }
        if (this.actionCooldown) {
            return this.wrappedAction.s();
```

---

## `s` — boolean s()

- **行号**: 224-232 (9 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `actionPriority.isAvailable()`
- `wrappedAction.s()`

### 访问的字段
- `actionCooldown`, `actionPriority`, `wrappedAction`

### 方法体 (前 10 行)
```java
        if (!this.actionPriority.isAvailable(this, null)) {
            return false;
        }
        if (this.actionCooldown) {
            return this.wrappedAction.s();
        }
        return true;
    }

    @Override
```

---

## `y` — as y()

- **行号**: 235-237 (3 行)
- **返回**: `as`
- **参数**: ``

### 调用的方法
- `wrappedAction.y()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.y();
    }

    @Override
    public boolean c(UnitInstance am2, boolean bl2) {
        return this.wrappedAction.c(am2, bl2);
    }

    @Override
    public boolean a(UnitInstance am2) {
```

---

## `c` — boolean c(UnitInstance am2, boolean bl2)

- **行号**: 240-242 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2, boolean bl2`

### 调用的方法
- `wrappedAction.c()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.c(am2, bl2);
    }

    @Override
    public boolean a(UnitInstance am2) {
        return this.wrappedAction.a(am2);
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
```

---

## `a` — boolean a(UnitInstance am2)

- **行号**: 245-247 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `wrappedAction.a()`

### 访问的字段
- `wrappedAction`

### 方法体 (前 10 行)
```java
        return this.wrappedAction.a(am2);
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.wrappedAction((GameAction) object);
    }
}

```

---
