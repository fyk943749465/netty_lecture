package com.shengsiyuan.dp.principle.ocp;

public class Ocp {
    public static void main(String[] args) {

        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());
    }
}


// 这是一个用于绘图的类 (这里是使用方）
class GraphicEditor {

    // 接收Shape对象，然后根据type，绘制不同的图形
    public void drawShape(Shape s) {
        if(s.getMType() == 1) {
            drawRectangle(s);
        } else if (s.getMType() == 2) {
            drawCricle(s);
        }
    }

    private void drawRectangle(Shape r) {
        System.out.println("绘制矩形");
    }

    private void drawCricle(Shape r) {
        System.out.println("绘制圆形");
    }

}

class Shape {
    private int m_type;
    Shape(int m) {
        this.m_type = m;
    }

    public int getMType() {
        return this.m_type;
    }
}


// 提供方
class Rectangle extends Shape {

    Rectangle() {
        super(1);
    }
}

// 提供方
class Circle extends Shape {

    Circle() {
        super(2);
    }
}