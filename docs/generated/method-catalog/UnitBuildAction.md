# UnitBuildAction — 方法目录

**源文件**: `03-deobfuscated\com\corrodinggames\rts\game\units\a\UnitBuildAction.java`
**方法总数**: 13

---

## `UnitBuildAction` — public UnitBuildAction(UnitTypeHandle as2)

- **行号**: 17-19 (3 行)
- **返回**: `public`
- **参数**: `UnitTypeHandle as2`

### 方法体 (前 10 行)
```java
        this(as2, -999.0f);
    }

    public UnitBuildAction(UnitTypeHandle as2, float f2) {
        super("u_" + as2.v());
        as as3 = com.corrodinggames.rts.game.units.custom.l.c(as2);
        if (as3 != null) {
            as2 = as3;
            this.unitType("u_" + as2.v());
        }
```

---

## `UnitBuildAction` — public UnitBuildAction(UnitTypeHandle as2, float f2)

- **行号**: 21-30 (10 行)
- **返回**: `public`
- **参数**: `UnitTypeHandle as2, float f2`

### 调用的方法
- `as2.v()`
- `l.c()`
- `this.unitType()`

### 访问的字段
- `g`, `unitType`

### 方法体 (前 10 行)
```java
        super("u_" + as2.v());
        as as3 = com.corrodinggames.rts.game.units.custom.l.c(as2);
        if (as3 != null) {
            as2 = as3;
            this.unitType("u_" + as2.v());
        }
        this.g = f2;
        this.unitType = as2;
    }

```

---

## `a` — String a()

- **行号**: 33-39 (7 行)
- **返回**: `String`
- **参数**: ``

### 字符串常量 (语义锚点)
- `"\n\n"`

### 调用的方法
- `a.a()`
- `am.c()`
- `unitType.f()`

### 访问的字段
- `unitType`

### 方法体 (前 10 行)
```java
        String string = this.unitType.f();
        boolean bl = false;
        boolean bl2 = true;
        string = string + "\n\n" + com.corrodinggames.rts.gameFramework.f.a.a(am.c(this.unitType), false, bl, bl2);
        return string;
    }

    @Override
    public String b() {
        return this.unitType.e();
```

---

## `b` — String b()

- **行号**: 42-44 (3 行)
- **返回**: `String`
- **参数**: ``

### 调用的方法
- `unitType.e()`

### 访问的字段
- `unitType`

### 方法体 (前 10 行)
```java
        return this.unitType.e();
    }

    @Override
    public int c() {
        return this.B().a();
    }

    @Override
    public b B() {
```

---

## `c` — int c()

- **行号**: 47-49 (3 行)
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
        return this.unitType.u();
```

---

## `B` — b B()

- **行号**: 52-58 (7 行)
- **返回**: `b`
- **参数**: ``

### 调用的方法
- `h.a()`
- `unitType.u()`

### 访问的字段
- `h`, `unitType`

### 方法体 (前 10 行)
```java
        b b2 = this.h.a();
        if (b2 != null) {
            return b2;
        }
        return this.unitType.u();
    }

    @Override
    public b r_() {
        b b2 = this.h.b();
```

---

## `r_` — b r_()

- **行号**: 61-67 (7 行)
- **返回**: `b`
- **参数**: ``

### 调用的方法
- `h.b()`
- `unitType.B()`

### 访问的字段
- `h`, `unitType`

### 方法体 (前 10 行)
```java
        b b2 = this.h.b();
        if (b2 != null) {
            return b2;
        }
        return this.unitType.B();
    }

    @Override
    public as i() {
        return this.unitType;
```

---

## `i` — as i()

- **行号**: 70-72 (3 行)
- **返回**: `as`
- **参数**: ``

### 访问的字段
- `unitType`

### 方法体 (前 10 行)
```java
        return this.unitType;
    }

    @Override
    public float K() {
        return this.unitType.D();
    }

    @Override
    public t f() {
```

---

## `K` — float K()

- **行号**: 75-77 (3 行)
- **返回**: `float`
- **参数**: ``

### 调用的方法
- `unitType.D()`

### 访问的字段
- `unitType`

### 方法体 (前 10 行)
```java
        return this.unitType.D();
    }

    @Override
    public t f() {
        return t.d;
    }

    @Override
    public boolean n_() {
```

---

## `f` — t f()

- **行号**: 80-82 (3 行)
- **返回**: `t`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return t.d;
    }

    @Override
    public boolean n_() {
        return !this.unitType.C();
    }

    @Override
    public boolean g(UnitInstance am2) {
```

---

## `n_` — boolean n_()

- **行号**: 85-87 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `unitType.C()`

### 访问的字段
- `unitType`

### 方法体 (前 10 行)
```java
        return !this.unitType.C();
    }

    @Override
    public boolean g(UnitInstance am2) {
        if (this.i().w()) {
            return true;
        }
        return super.g(am2);
    }
```

---

## `g` — boolean g(UnitInstance am2)

- **行号**: 90-95 (6 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `super.g()`
- `this.i()`

### 访问的字段
- `i`

### 方法体 (前 10 行)
```java
        if (this.i().w()) {
            return true;
        }
        return super.g(am2);
    }

    @Override
    public boolean g() {
        return true;
    }
```

---

## `g` — boolean g()

- **行号**: 98-100 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return true;
    }
}

```

---
