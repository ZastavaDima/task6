package org.example;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

public class ShapeFactory {
    public Shape shape;
    public BasicStroke stroke;
    public Paint paint;
    public int width = 25;
    public int height = 25;

    public ShapeFactory(int shape_type, Color fillColor, Color strokeColor, int strokeWidth) {
        setShape(shape_type);
        setStroke(strokeColor, strokeWidth);
        setFill(fillColor);
    }
    private void setShape(int shape_type) {
        switch (shape_type / 10) {
            case 1:
                this.shape = createStar(3, new Point(0, 0), (double)this.width / 2.0D, (double)this.width / 2.0D);
                break;
            case 8:
            default:
                throw new Error("type is unsupported");
            case 3:
                this.shape = createStar(5, new Point(0, 0), (double)this.width / 2.0D, (double)this.width / 4.0D);
                break;
            case 5:
                this.shape = new Rectangle2D.Double((double)(-this.width) / 2.0D, (double)(-this.height) / 2.0D, (double)this.width, (double)this.height);
                break;
            case 7:
                GeneralPath path = new GeneralPath();
                double tmp_height = Math.sqrt(2.0D) / 2.0D * (double)this.height;
                path.moveTo((double)(-this.width / 2), -tmp_height);
                path.lineTo(0.0D, -tmp_height);
                path.lineTo((double)(this.width / 2), tmp_height);
                path.closePath();
                this.shape = path;
                break;
            case 9:
                this.shape = new java.awt.geom.Arc2D.Double((double)(-this.width) / 2.0D, (double)(-this.height) / 2.0D, (double)this.width, (double)this.height, 30.0D, 300.0D, 2);
        }
        switch(shape_type % 10) {
            case 1:
                this.stroke = new BasicStroke(3.0F);
                break;
            case 5:
            case 6:
            default:
                throw new Error("type is nusupported");
            case 3:
                break;
            case 4:
                this.stroke = new BasicStroke(7.0F);
                break;
            case 7:
                this.paint = new GradientPaint((float)(-this.width), (float)(-this.height), Color.white, (float)this.width, (float)this.height, Color.gray, true);
                break;
            case 8:
                this.paint = Color.gray;
        }

    }
    private void setStroke(Color color, int width) {
        this.stroke = new BasicStroke(width, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND);
    }

    private void setFill(Color color) {
        this.paint = color;
    }
    /**
     * Приватний метод для створення фігури "зірка".
     *
     * @param arms Кількість гілок у зірки.
     * @param center Центр зірки.
     * @param rOuter Зовнішній радіус.
     * @param rInner Внутрішній радіус.
     * @return Створена фігурка "зірка".
     *
     * **/
    private static Shape createStar(int arms, Point center, double rOuter, double rInner) {
        double angle = 3.141592653589793D / (double)arms;
        GeneralPath path = new GeneralPath();

        for(int i = 0; i < 2 * arms; ++i) {
            double r = (i & 1) == 0 ? rOuter : rInner;
            java.awt.geom.Point2D.Double p = new java.awt.geom.Point2D.Double((double)center.x + Math.cos((double)i * angle) * r, (double)center.y + Math.sin((double)i * angle) * r);
            if (i == 0) {
                path.moveTo(p.getX(), p.getY());
            } else {
                path.lineTo(p.getX(), p.getY());
            }
        }

        path.closePath();
        return path;
    }
}