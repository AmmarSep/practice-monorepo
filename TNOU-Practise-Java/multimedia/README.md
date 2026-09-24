# TNOU MCA-P5 Lab 5 - Multimedia Systems

## Practical Questions (Concise & Easy to Remember)

### Programs

1. **Q1_EnvironmentMapSphere.java** - Transform environment map to sphere surface
   - Uses pixel-by-pixel mapping with gradient colors
   - Simulates 3D sphere with environment reflection

2. **Q2_FillEllipsePattern.java** - Fill ellipse interior with pattern
   - Checkerboard/stripe pattern fill
   - Uses ellipse equation for boundary detection

3. **Q3_ChangePatternSize.java** - Resize rectangular fill pattern
   - Nearest neighbor resizing algorithm
   - Shows original and resized patterns side-by-side

4. **Q4_ThickLinesCaps.java** - Thick lines with cap styles (Menu-based)
   - Butt Cap, Round Cap, Square Cap options
   - Uses BasicStroke with menu selection

5. **Q5_BlinkingHighlight.java** - Blinking highlight effect
   - Thread-based animation (500ms interval)
   - Semi-transparent yellow overlay

6. **Q6_ScanConvertEllipse.java** - Scan convert ellipse to solid color
   - Midpoint Ellipse Algorithm
   - Efficient scan-line filling

7. **Q7_ReflectTriangle.java** - Reflect triangle w.r.t X-axis
   - Reflection formula: y' = 2×axis - y
   - Shows original and reflected triangles

8. **Q8_Animate2DObject.java** - Animate 2D car object
   - Moving car with wheels and windows
   - Boundary detection and direction reversal

9. **Q9_BallBouncing.java** - Ball bouncing with random numbers
   - Random initial position, speed, and color
   - Color changes on wall collision

10. **Q10_MovingCirclesLines.java** - Moving colored circles + random lines
    - Multiple circles moving independently
    - Random lines generated dynamically

---

## Quick Compilation & Execution

```bash
cd multimedia
javac Q1_EnvironmentMapSphere.java
java Q1_EnvironmentMapSphere
```

Replace Q1 with Q2, Q3, etc. for other programs.

---

## Key Concepts for Exam

### Graphics Techniques
- **Environment Mapping**: Texture mapping on 3D surfaces
- **Pattern Fill**: Pixel-by-pixel pattern application
- **Scan Conversion**: Converting geometric shapes to pixels
- **Reflection**: y' = 2×axis - y (X-axis), x' = 2×axis - x (Y-axis)

### Animation
- Use `Thread` or `Timer` for animations
- `repaint()` for screen updates
- Sleep interval: 10-50ms for smooth animation

### Stroke & Caps
- `BasicStroke(width, CAP_TYPE, JOIN_TYPE)`
- CAP_BUTT, CAP_ROUND, CAP_SQUARE

### Ellipse Equation
- (x-cx)²/rx² + (y-cy)²/ry² ≤ 1

---

## Important Tips
- All programs use **JFrame** and **JPanel**
- Graphics2D for advanced rendering
- Thread.sleep() for animation timing
- Random class for randomization

**Prepare these programs for quick recall in practical exams!**
