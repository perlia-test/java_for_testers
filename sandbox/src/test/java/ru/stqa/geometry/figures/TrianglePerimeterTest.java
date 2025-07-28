package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrianglePerimeterTest {
    @Test
    public void CanCalculatePerimeter() {
        var result = new Triangle(2.0, 2.0, 2.0);
        Assertions.assertEquals(6.0, result.Perimeter());
    }

}
