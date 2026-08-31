# ActionId — 方法目录

**源文件**: `03-deobfuscated\com\corrodinggames\rts\game\units\a\ActionId.java`
**方法总数**: 9

---

## `a` — c a(String string)

- **行号**: 15-23 (9 行)
- **返回**: `c`
- **参数**: `String string`

### 调用的方法
- `c.get()`
- `c.put()`

### 方法体 (前 10 行)
```java
        c c2 = (ActionId) c.get(string);
        if (c2 != null) {
            return c2;
        }
        c c3 = new ActionId(string);
        c.put(string, c3);
        return c3;
    }

    public String a() {
```

---

## `a` — String a()

- **行号**: 25-27 (3 行)
- **返回**: `String`
- **参数**: ``

### 访问的字段
- `b`

### 方法体 (前 10 行)
```java
        return this.b;
    }

    private ActionId(String string) {
        this.b = string;
    }

    public static void a(OutputNetStream as2, ActionId c2) {
        String string = null;
        if (c2 != null) {
```

---

## `ActionId` — private ActionId(String string)

- **行号**: 29-31 (3 行)
- **返回**: `private`
- **参数**: `String string`

### 访问的字段
- `b`

### 方法体 (前 10 行)
```java
        this.b = string;
    }

    public static void a(OutputNetStream as2, ActionId c2) {
        String string = null;
        if (c2 != null) {
            string = c2.b;
        }
        as2.b(string);
    }
```

---

## `a` — void a(OutputNetStream as2, ActionId c2)

- **行号**: 33-39 (7 行)
- **返回**: `void`
- **参数**: `OutputNetStream as2, ActionId c2`

### 调用的方法
- `as2.b()`

### 方法体 (前 10 行)
```java
        String string = null;
        if (c2 != null) {
            string = c2.b;
        }
        as2.b(string);
    }

    public static c a(InputNetStream k2) {
        String string = k2.j();
        if (string != null) {
```

---

## `a` — c a(InputNetStream k2)

- **行号**: 41-47 (7 行)
- **返回**: `c`
- **参数**: `InputNetStream k2`

### 调用的方法
- `c.a()`
- `k2.j()`

### 方法体 (前 10 行)
```java
        String string = k2.j();
        if (string != null) {
            return com.corrodinggames.rts.game.units.a.c.a(string);
        }
        return null;
    }

    public boolean equals(Object object) {
        return this == object;
    }
```

---

## `equals` — boolean equals(Object object)

- **行号**: 49-51 (3 行)
- **返回**: `boolean`
- **参数**: `Object object`

### 方法体 (前 10 行)
```java
        return this == object;
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public String toString() {
        return "ActionId(" + this.b + ")";
    }
```

---

## `hashCode` — int hashCode()

- **行号**: 53-55 (3 行)
- **返回**: `int`
- **参数**: ``

### 调用的方法
- `b.hashCode()`

### 访问的字段
- `b`

### 方法体 (前 10 行)
```java
        return this.b.hashCode();
    }

    public String toString() {
        return "ActionId(" + this.b + ")";
    }

    public final boolean a(ActionId c2) {
        return this == c2;
    }
```

---

## `toString` — String toString()

- **行号**: 57-59 (3 行)
- **返回**: `String`
- **参数**: ``

### 字符串常量 (语义锚点)
- `"ActionId("`

### 访问的字段
- `b`

### 方法体 (前 10 行)
```java
        return "ActionId(" + this.b + ")";
    }

    public final boolean a(ActionId c2) {
        return this == c2;
    }
}

```

---

## `a` — boolean a(ActionId c2)

- **行号**: 61-63 (3 行)
- **返回**: `boolean`
- **参数**: `ActionId c2`

### 方法体 (前 10 行)
```java
        return this == c2;
    }
}

```

---
