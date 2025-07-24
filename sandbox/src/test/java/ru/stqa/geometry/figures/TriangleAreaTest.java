package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleAreaTest {
    @Test
      public void canCalculateArea() {
         var s = new Triangle(2.0, 2.0, 2.0);
        Assertions.assertEquals(2.0, s.Square());
     }
}
