package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleParamTest {

    @Test
    void CheckForNegativeSide() {
        try {
            new Triangle(5.0, -2.0, 6.0);
            Assertions.fail();
        }
        catch (IllegalArgumentException exception) {
            //Исключение сработало. Успех        }

        }
    }

    @Test
    void CheckTriangleRule () {
        try {
            new Triangle (1.0, 1.0, 4.0);
            Assertions.fail();
        }
        catch (IllegalArgumentException exception) {
            //Исключение сработало. Успех
        }
    }

    @Test
    void CheckTriangleInEquality() {
        var Trgl_1 = new Triangle(7.0, 6.0, 5.0);
        var Trgl_2 = new Triangle(6.0, 5.0, 5.0);
        Assertions.assertFalse(Trgl_1.equals(Trgl_2));

    }

    @Test
    void CheckTriangleEquality() {
        var Trgl_1 = new Triangle(5.0, 6.0, 7.0);
        var Trgl_2 = new Triangle(7.0, 5.0, 6.0);
        Assertions.assertTrue(Trgl_1.equals(Trgl_2));
    }
}

