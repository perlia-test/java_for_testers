package ru.stqa.geometry.figures;

public record Triangle (double side_a, double side_b, double side_c) {

   public static void printTriangleSquare(Triangle p) {
         var text = String.format("Площадь треугольника со сторонами %f см, %f см, %f см  равна %f кв.см", p.side_a, p.side_b, p.side_c, p.Square());
         System.out.println(text);
    }


   public static void printTrianglePerimeter(Triangle s) {
         String text = String.format("Периметр треугольника со сторонами %f см, %f см, %f см равен %f см", s.side_a, s.side_b, s.side_c, s.Perimeter());
         System.out.println(text);
   }

   public double Perimeter() {
        return this.side_a+this.side_b+this.side_c;
        }

   public double Square() {
        var p = Perimeter()/2;
        return Math.sqrt(p*(p-this.side_a)*(p-this.side_b)*(p-this.side_c));
    }
}
