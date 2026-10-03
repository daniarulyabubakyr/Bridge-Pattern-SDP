# Assignment 3 — Bridge Pattern

## Student Information

- **Name:** Daniaruly Abubakir
- **Group:** SE-2527
- **Topic:** A — Drawing
- **Repository:** https://github.com/daniarulyabubakyr/Bridge-Pattern-SDP.git
- **Base commit:** `62b06cb`

---

## 1. Project Overview

This project demonstrates the Bridge design pattern in Java JDK 17.

The example uses two independently varying class hierarchies:

### Abstraction hierarchy

- `Shape`
- `Circle`
- `Square`

### Implementation hierarchy

- `Renderer`
- `VectorRenderer`
- `RasterRenderer`
- `AsciiRenderer`

The `Shape` hierarchy represents **what is being drawn**, while the `Renderer` hierarchy represents **how it is rendered**.

The Bridge pattern allows these two hierarchies to vary independently.

---

## 2. Bridge Pattern Structure

### Abstraction

`Shape` is the abstract base class for the shape hierarchy.

It stores:

- a shape ID
- a reference to the `Renderer` implementation

The concrete abstractions are:

- `Circle`
- `Square`

### Implementor

`Renderer` is the Implementor interface.

It defines:

```java
String renderCircle(int radius);
String renderSquare(int side);
```

Concrete implementations are:

- `VectorRenderer`
- `RasterRenderer`
- `AsciiRenderer`

---

## 3. Bridge Field

The Bridge field is declared in `Shape`:

```java
protected Renderer renderer;
```

The field is typed as the `Renderer` interface rather than a concrete renderer.

It is initialized through constructor injection:

```java
protected Shape(String id, Renderer renderer) {
    this.id = id;
    this.renderer = renderer;
}
```

This creates a composition relationship between the abstraction and implementation.

---

## 4. `execute()` Method

`Shape` declares the abstract method:

```java
public abstract String execute();
```

Each concrete shape delegates its rendering operation to the stored `Renderer`.

For example, `Circle` uses:

```java
@Override
public String execute() {
    return renderer.renderCircle(radius);
}
```

`Square` uses:

```java
@Override
public String execute() {
    return renderer.renderSquare(side);
}
```

The shape does not create a renderer and does not depend on a concrete renderer class.

---

## 5. `setImplementation(...)`

The implementation can be replaced at runtime using:

```java
public void setImplementation(Renderer renderer) {
    this.renderer = renderer;
}
```

This allows the same shape object to use different rendering implementations.

For example:

```java
Circle circle = new Circle(
        "C1",
        new VectorRenderer(),
        2
);

String before = circle.execute();

circle.setImplementation(new RasterRenderer());

String after = circle.execute();
```

The same `Circle` object is used before and after the implementation change.

---

## 6. T5 Runtime Switching

T5 demonstrates runtime replacement of the Bridge implementation.

The test verifies:

```java
boolean sameObject = circle == originalReference;
```

and also verifies that the shape state remains unchanged.

The actual output is:

```text
T5 PASS | sameObject=true | stateUnchanged=true
  before=VECTOR circle radius=2 | after=RASTER circle radius=2
```

This proves that:

- the same `Circle` object is used
- the ID remains unchanged
- the radius remains unchanged
- only the renderer implementation changes
- the output changes from Vector to Raster rendering

---

## 7. Extension: AsciiRenderer

After completing the base implementation with `VectorRenderer` and `RasterRenderer`, a third implementation was added:

```text
AsciiRenderer
```

The new renderer implements the existing `Renderer` interface:

```java
public class AsciiRenderer implements Renderer {

    @Override
    public String renderCircle(int radius) {
        return "ASCII circle radius=" + radius;
    }

    @Override
    public String renderSquare(int side) {
        return "ASCII square side=" + side;
    }
}
```

No existing abstraction or renderer implementation was modified for the new implementation.

The extension was added after the base commit:

```text
Base commit: 62b06cb
Extension commit: bab8e1a
```

The source diff is stored in:

```text
extension.diff
```

---

## 8. Test Cases

The application provides seven tests.

| Test | Combination | Expected result |
|---|---|---|
| T1 | Circle + VectorRenderer | `VECTOR circle radius=2` |
| T2 | Circle + RasterRenderer | `RASTER circle radius=2` |
| T3 | Square + VectorRenderer | `VECTOR square side=3` |
| T4 | Square + RasterRenderer | `RASTER square side=3` |
| T5 | Runtime switching | Same object, Vector → Raster |
| T6 | Circle + AsciiRenderer | `ASCII circle radius=2` |
| T7 | Square + AsciiRenderer | `ASCII square side=3` |

Final test result:

```text
T1 PASS | Circle + VectorRenderer | result=VECTOR circle radius=2
T2 PASS | Circle + RasterRenderer | result=RASTER circle radius=2
T3 PASS | Square + VectorRenderer | result=VECTOR square side=3
T4 PASS | Square + RasterRenderer | result=RASTER square side=3
T5 PASS | sameObject=true | stateUnchanged=true
  before=VECTOR circle radius=2 | after=RASTER circle radius=2
T6 PASS | Circle + AsciiRenderer | result=ASCII circle radius=2
T7 PASS | Square + AsciiRenderer | result=ASCII square side=3
SUMMARY: 7/7 PASS
```

---

## 9. Compilation and Execution

The project uses Java JDK 17.

Compile all source files using:

```powershell
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
```

Run the demonstration:

```powershell
java -cp out Main --demo
```

The expected final summary is:

```text
SUMMARY: 7/7 PASS
```

---

## 10. Source Files

The project contains:

```text
src/
├── Main.java
├── Renderer.java
├── VectorRenderer.java
├── RasterRenderer.java
├── AsciiRenderer.java
├── Shape.java
├── Circle.java
└── Square.java
```

Additional submission files:

```text
sources.txt
README.md
report.pdf
demo-output.txt
extension.diff
```

---

## 11. Bridge Pattern Role Map

| Pattern Role | Project Class |
|---|---|
| Abstraction | `Shape` |
| Refined Abstraction | `Circle` |
| Refined Abstraction | `Square` |
| Implementor | `Renderer` |
| Concrete Implementor | `VectorRenderer` |
| Concrete Implementor | `RasterRenderer` |
| Concrete Implementor | `AsciiRenderer` |
| Client | `Main` |

The bridge between the two hierarchies is the interface-typed field:

```java
protected Renderer renderer;
```

This allows `Shape` objects to work with different renderer implementations without creating separate subclasses for every shape-renderer combination.
