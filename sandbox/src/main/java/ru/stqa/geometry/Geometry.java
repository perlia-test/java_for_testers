package ru.stqa.geometry;

import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Triangle.printTrianglePerimeter(new Triangle(3.0, 3.0, 2.0));
        Triangle.printTriangleSquare(new Triangle(3.0, 3.0, 2.0));
    }


}