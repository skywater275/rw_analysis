# AbstractBuildAction — 方法目录

**源文件**: `03-deobfuscated\com\corrodinggames\rts\game\units\a\AbstractBuildAction.java`
**方法总数**: 7

---

## `AbstractBuildAction` — public AbstractBuildAction(int n2)

- **行号**: 14-16 (3 行)
- **返回**: `public`
- **参数**: `int n2`

### 方法体 (前 10 行)
```java
        super(n2);
    }

    public AbstractBuildAction(String string) {
        super(string);
    }

    @Override
    public int b(UnitInstance am2, boolean bl) {
        if (!(am2 instanceof CarrierUnit)) {
```

---

## `AbstractBuildAction` — public AbstractBuildAction(String string)

- **行号**: 18-20 (3 行)
- **返回**: `public`
- **参数**: `String string`

### 方法体 (前 10 行)
```java
        super(string);
    }

    @Override
    public int b(UnitInstance am2, boolean bl) {
        if (!(am2 instanceof CarrierUnit)) {
            return 99;
        }
        return ((CarrierUnit) ((Object)am2)).a(this.N(), bl);
    }
```

---

## `b` — int b(UnitInstance am2, boolean bl)

- **行号**: 23-28 (6 行)
- **返回**: `int`
- **参数**: `UnitInstance am2, boolean bl`

### 调用的方法
- `this.N()`

### 访问的字段
- `N`

### 方法体 (前 10 行)
```java
        if (!(am2 instanceof CarrierUnit)) {
            return 99;
        }
        return ((CarrierUnit) ((Object)am2)).a(this.N(), bl);
    }

    @Override
    public float p(UnitInstance am2) {
        if (!(am2 instanceof CarrierUnit)) {
            return -1.0f;
```

---

## `p` — float p(UnitInstance am2)

- **行号**: 31-51 (21 行)
- **返回**: `float`
- **参数**: `UnitInstance am2`

### 调用的方法
- `l2.dw()`
- `this.d()`

### 访问的字段
- `d`

### 方法体 (前 10 行)
```java
        if (!(am2 instanceof CarrierUnit)) {
            return -1.0f;
        }
        l l2 = (CarrierUnit) ((Object)am2);
        j j2 = l2.dw();
        if (j2 == null) {
            return -1.0f;
        }
        if (!this.d(j2.j)) {
            return -1.0f;
```

---

## `K` — float K()

- **行号**: 53-55 (3 行)
- **返回**: `float`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return 0.01f;
    }

    @Override
    public boolean u() {
        return true;
    }

    @Override
    public u e() {
```

---

## `u` — boolean u()

- **行号**: 58-60 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return true;
    }

    @Override
    public u e() {
        return u.c;
    }
}

```

---

## `e` — u e()

- **行号**: 63-65 (3 行)
- **返回**: `u`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return u.c;
    }
}

```

---
