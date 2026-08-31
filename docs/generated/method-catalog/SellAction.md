# SellAction — 方法目录

**源文件**: `03-deobfuscated\com\corrodinggames\rts\game\units\a\SellAction.java`
**方法总数**: 20

---

## `SellAction` — public SellAction(UnitTypeHandle as2)

- **行号**: 26-30 (5 行)
- **返回**: `public`
- **参数**: `UnitTypeHandle as2`

### 调用的方法
- `as2.v()`

### 访问的字段
- `g`, `unitType`

### 方法体 (前 10 行)
```java
        super("s_" + as2.v());
        this.g = -9999.0f;
        this.unitType = as2;
    }

    @Override
    public int b(UnitInstance am2, boolean bl) {
        return -1;
    }

```

---

## `b` — int b(UnitInstance am2, boolean bl)

- **行号**: 33-35 (3 行)
- **返回**: `int`
- **参数**: `UnitInstance am2, boolean bl`

### 方法体 (前 10 行)
```java
        return -1;
    }

    @Override
    public int c() {
        return 0;
    }

    @Override
    public as i() {
```

---

## `c` — int c()

- **行号**: 38-40 (3 行)
- **返回**: `int`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return 0;
    }

    @Override
    public as i() {
        return this.unitType;
    }

    @Override
    public u e() {
```

---

## `i` — as i()

- **行号**: 43-45 (3 行)
- **返回**: `as`
- **参数**: ``

### 访问的字段
- `unitType`

### 方法体 (前 10 行)
```java
        return this.unitType;
    }

    @Override
    public u e() {
        return u.i;
    }

    @Override
    public t f() {
```

---

## `e` — u e()

- **行号**: 48-50 (3 行)
- **返回**: `u`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return u.i;
    }

    @Override
    public t f() {
        if (l.at() && !com.corrodinggames.rts.gameFramework.f.g.bO) {
            return t.h;
        }
        return t.g;
    }
```

---

## `f` — t f()

- **行号**: 53-58 (6 行)
- **返回**: `t`
- **参数**: ``

### 调用的方法
- `l.at()`

### 方法体 (前 10 行)
```java
        if (l.at() && !com.corrodinggames.rts.gameFramework.f.g.bO) {
            return t.h;
        }
        return t.g;
    }

    @Override
    public boolean g() {
        return false;
    }
```

---

## `g` — boolean g()

- **行号**: 61-63 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    @Override
    public boolean c(UnitInstance am2, boolean bl) {
        l l2 = l.B();
        if (!bl) {
            if (l2.bS.q() == 1) {
                return false;
            }
```

---

## `c` — boolean c(UnitInstance am2, boolean bl)

- **行号**: 66-88 (23 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2, boolean bl`

### 调用的方法
- `am3.r()`
- `am4.r()`
- `bS.l()`
- `bS.q()`
- `l.B()`

### 访问的字段
- `unitType`

### 方法体 (前 10 行)
```java
        l l2 = l.B();
        if (!bl) {
            if (l2.bS.q() == 1) {
                return false;
            }
            boolean bl2 = false;
            for (UnitInstance am3 : am.bE) {
                if (!am3.cG || am3.r() == this.unitType) continue;
                l2.bS.l(am3);
                bl2 = true;
```

---

## `d` — String d()

- **行号**: 91-99 (9 行)
- **返回**: `String`
- **参数**: ``

### 字符串常量 (语义锚点)
- `"UnitInfo"`
- `"Editor"`

### 调用的方法
- `l.B()`
- `unitType.e()`

### 访问的字段
- `firstUnit`, `unitCount`, `unitType`

### 方法体 (前 10 行)
```java
        String string = "UnitInfo";
        l l2 = l.B();
        if (this.firstUnit instanceof Factory) {
            return "Editor";
        }
        string = "" + this.unitType.e() + " x" + this.unitCount;
        return string;
    }

    @Override
```

---

## `b` — String b()

- **行号**: 102-104 (3 行)
- **返回**: `String`
- **参数**: ``

### 字符串常量 (语义锚点)
- `"UnitInfo"`

### 方法体 (前 10 行)
```java
        return "UnitInfo";
    }

    @Override
    public String w(UnitInstance am2) {
        if (this.firstUnit instanceof Factory) {
            return "Editor";
        }
        return this.unitType.e();
    }
```

---

## `w` — String w(UnitInstance am2)

- **行号**: 107-112 (6 行)
- **返回**: `String`
- **参数**: `UnitInstance am2`

### 字符串常量 (语义锚点)
- `"Editor"`

### 调用的方法
- `unitType.e()`

### 访问的字段
- `firstUnit`, `unitType`

### 方法体 (前 10 行)
```java
        if (this.firstUnit instanceof Factory) {
            return "Editor";
        }
        return this.unitType.e();
    }

    @Override
    public boolean h_() {
        return true;
    }
```

---

## `h_` — boolean h_()

- **行号**: 115-117 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return true;
    }

    @Override
    public boolean s() {
        return true;
    }

    @Override
    public boolean u() {
```

---

## `s` — boolean s()

- **行号**: 120-122 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return true;
    }

    @Override
    public boolean u() {
        return true;
    }

    @Override
    public boolean C() {
```

---

## `u` — boolean u()

- **行号**: 125-127 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return true;
    }

    @Override
    public boolean C() {
        return true;
    }

    @Override
    public String a() {
```

---

## `C` — boolean C()

- **行号**: 130-132 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return true;
    }

    @Override
    public String a() {
        String string = "";
        if (this.firstUnit instanceof Factory) {
            return "";
        }
        if (this.hasMixedTypes) {
```

---

## `a` — String a()

- **行号**: 135-144 (10 行)
- **返回**: `String`
- **参数**: ``

### 调用的方法
- `unitType.f()`

### 访问的字段
- `firstUnit`, `hasMixedTypes`, `unitType`

### 方法体 (前 10 行)
```java
        String string = "";
        if (this.firstUnit instanceof Factory) {
            return "";
        }
        if (this.hasMixedTypes) {
            string = "(Left click to exclusively select / Right click to unselect)\n";
        }
        return string + this.unitType.f();
    }

```

---

## `K` — void K()

- **行号**: 146-170 (25 行)
- **返回**: `void`
- **参数**: ``

### 调用的方法
- `bZ.a()`
- `bZ.size()`
- `l.B()`
- `y2.r()`

### 访问的字段
- `firstUnit`, `hasMixedTypes`, `lastUpdateTick`, `unitCount`, `unitType`

### 方法体 (前 10 行)
```java
        l l2 = l.B();
        if (this.lastUpdateTick == l2.bS.Y) {
            return;
        }
        this.lastUpdateTick = l2.bS.Y;
        this.unitCount = 0;
        this.hasMixedTypes = false;
        this.firstUnit = null;
        am[] amArray = l2.bS.bZ.a();
        int n2 = l2.bS.bZ.size();
```

---

## `m_` — float m_()

- **行号**: 173-175 (3 行)
- **返回**: `float`
- **参数**: ``

### 访问的字段
- `g`, `unitCount`

### 方法体 (前 10 行)
```java
        return this.g - (float)this.unitCount;
    }

    @Override
    public boolean G() {
        return true;
    }

    @Override
    public boolean o_() {
```

---

## `G` — boolean G()

- **行号**: 178-180 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return true;
    }

    @Override
    public boolean o_() {
        return true;
    }
}

```

---

## `o_` — boolean o_()

- **行号**: 183-185 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return true;
    }
}

```

---
