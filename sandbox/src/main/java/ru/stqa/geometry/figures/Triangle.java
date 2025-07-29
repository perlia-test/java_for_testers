package ru.stqa.geometry.figures;

import java.util.Objects;

public record Triangle (double side_a, double side_b, double side_c) {

public Triangle {
        if (side_a <= 0 || side_b <= 0 || side_c <= 0) {
            throw new IllegalArgumentException("Длина стороны треугольника имеет недопустимое значение");
        }
        else {
            if ( (side_a+side_b < side_c) || (side_a+side_c < side_b) || (side_b+side_c < side_a) ) {
                throw new IllegalArgumentException("Треугольник не существует");
            }
        }
}


//Сравнение треугольников
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
              return (Double.compare(this.side_a, triangle.side_a) == 0
                   && Double.compare(this.side_b, triangle.side_b) == 0
                   && Double.compare(this.side_c, triangle.side_c) == 0)
                ||   (Double.compare(this.side_a, triangle.side_b) == 0
                   && Double.compare(this.side_b, triangle.side_c) == 0
                   && Double.compare(this.side_c, triangle.side_a) == 0)
                ||   (Double.compare(this.side_a, triangle.side_c) == 0
                   && Double.compare(this.side_b, triangle.side_a) == 0
                   && Double.compare(this.side_c, triangle.side_b) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }


//Вывести площадь треугольника на экран
    public static void printTriangleSquare(Triangle p) {
         var text = String.format("Площадь треугольника со сторонами %f см, %f см, %f см  равна %f кв.см", p.side_a, p.side_b, p.side_c, p.Square());
         System.out.println(text);
    }


//Вывести периметр треугольника на экран
   public static void printTrianglePerimeter(Triangle s) {
         String text = String.format("Периметр треугольника со сторонами %f см, %f см, %f см равен %f см", s.side_a, s.side_b, s.side_c, s.Perimeter());
         System.out.println(text);
   }

//Вычисление периметра треугольника
   public double Perimeter() {
       return (this.side_a + this.side_b + this.side_c);
   }

//Вычисление площади треугольника по формуле Герона
   public double Square() {
        var p = Perimeter()/2;
        return Math.sqrt(p*(p-this.side_a)*(p-this.side_b)*(p-this.side_c));
    }
}
