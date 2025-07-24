package ru.stqa.geometry.figures;

public class Triangle {
     public static void printTriangleSquare(double side_a, double side_b, double side_c) {
         var text = String.format("Площадь треугольника со сторонами %f, %f, %f равна %f", side_a, side_b, side_c, TriangleSquare(side_a, side_b, side_c));
         System.out.println(text);
    }

       private static double TriangleSquare(double sideA, double sideB, double sideC) {
           double p = TrianglePerimeter(sideA, sideB, sideC)/2;
           return Math.round(Math.sqrt(p*(p-sideA)*(p-sideB)*(p-sideC)));
       }

     public static void printTrianglePerimeter(double side_a, double side_b, double side_c) {
         String text = String.format("Периметр треугольника со сторонами %f, %f, %f равен %f", side_a, side_b, side_c, TrianglePerimeter(side_a, side_b, side_c));
         System.out.println(text);
   }

        private static double TrianglePerimeter(double a, double b, double c) {
            return a + b + c;
        }
}
