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
}

