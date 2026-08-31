# AbstractCutsceneAction — 方法目录

**源文件**: `03-deobfuscated\com\corrodinggames\rts\game\units\a\AbstractCutsceneAction.java`
**方法总数**: 14

---

## `AbstractCutsceneAction` — public AbstractCutsceneAction(String string)

- **行号**: 19-22 (4 行)
- **返回**: `public`
- **参数**: `String string`

### 字符串常量 (语义锚点)
- `"c__cut_"`

### 访问的字段
- `g`

### 方法体 (前 10 行)
```java
        super("c__cut_" + string);
        this.g = 0.0f;
    }

    @Override
    public int b(UnitInstance am2, boolean bl) {
        return -1;
    }

    @Override
```

---

## `b` — int b(UnitInstance am2, boolean bl)

- **行号**: 25-27 (3 行)
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

- **行号**: 30-32 (3 行)
- **返回**: `int`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return 0;
    }

    @Override
    public as i() {
        return null;
    }

    @Override
    public u e() {
```

---

## `i` — as i()

- **行号**: 35-37 (3 行)
- **返回**: `as`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return null;
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

- **行号**: 40-42 (3 行)
- **返回**: `u`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return u.i;
    }

    @Override
    public t f() {
        return t.g;
    }

    @Override
    public boolean g() {
```

---

## `f` — t f()

- **行号**: 45-47 (3 行)
- **返回**: `t`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return t.g;
    }

    @Override
    public boolean g() {
        return false;
    }

    public y K() {
        y y2 = null;
```

---

## `g` — boolean g()

- **行号**: 50-52 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    public y K() {
        y y2 = null;
        for (GameObject w2 : w.er) {
            if (!(w2 instanceof UnitType)) continue;
            y y3 = (UnitType) w2;
            if (!y3.cG) continue;
            y2 = y3;
```

---

## `K` — y K()

- **行号**: 54-63 (10 行)
- **返回**: `y`
- **参数**: ``

### 方法体 (前 10 行)
```java
        y y2 = null;
        for (GameObject w2 : w.er) {
            if (!(w2 instanceof UnitType)) continue;
            y y3 = (UnitType) w2;
            if (!y3.cG) continue;
            y2 = y3;
        }
        return y2;
    }

```

---

## `L` — boolean L()

- **行号**: 65-75 (11 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `l.B()`
- `this.K()`

### 访问的字段
- `K`

### 方法体 (前 10 行)
```java
        l l2 = l.B();
        y y2 = this.K();
        if (y2 != null) {
            if (y2 instanceof Factory) {
                return true;
            }
            return l2.bs == y2.bX;
        }
        return false;
    }
```

---

## `d` — String d()

- **行号**: 78-80 (3 行)
- **返回**: `String`
- **参数**: ``

### 调用的方法
- `this.b()`

### 访问的字段
- `b`

### 方法体 (前 10 行)
```java
        return this.b();
    }

    @Override
    public boolean h_() {
        return false;
    }

    @Override
    public boolean s() {
```

---

## `h_` — boolean h_()

- **行号**: 83-85 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    @Override
    public boolean s() {
        return !this.L();
    }

    @Override
    public boolean G() {
```

---

## `s` — boolean s()

- **行号**: 88-90 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `this.L()`

### 访问的字段
- `L`

### 方法体 (前 10 行)
```java
        return !this.L();
    }

    @Override
    public boolean G() {
        return false;
    }

    @Override
    public float l() {
```

---

## `G` — boolean G()

- **行号**: 93-95 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    @Override
    public float l() {
        if (!com.corrodinggames.rts.gameFramework.f.g.bP) {
            return 1.0f;
        }
        return 1.0f;
    }
```

---

## `l` — float l()

- **行号**: 98-103 (6 行)
- **返回**: `float`
- **参数**: ``

### 方法体 (前 10 行)
```java
        if (!com.corrodinggames.rts.gameFramework.f.g.bP) {
            return 1.0f;
        }
        return 1.0f;
    }
}

```

---
