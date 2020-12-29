package com.shengsiyuan.dp.principle.ocp.improve;

public class Ocp {

    public static void main(String[] args) {
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());
        graphicEditor.drawShape(new Triangle());
        graphicEditor.drawShape(new OtherShape());
    }
}

// 这是一个用于绘图的类 (这里是使用方）
class GraphicEditor {

    // 接收Shape对象，然后根据type，绘制不同的图形
    public void drawShape(Shape s) {
        s.draw();
    }
}

abstract class Shape {

    abstract void draw();
}


// 提供方
class Rectangle extends Shape {



    public void draw() {
        System.out.println("绘制矩形");
    }
}

// 提供方
class Circle extends Shape {

    public void draw() {
        System.out.println("绘制圆形");
    }
}

class Triangle extends Shape {


    public void draw() {
        System.out.println("绘制三角形");
    }
}

class OtherShape extends Shape {

    public void draw() {
        System.out.println("绘制其他图形");
    }
}
