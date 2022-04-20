package rectangles;

import java.util.Objects;

/*
    Defines a point by its x and y coordinates
 */
public class Point {
    public Point(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    private int X;
    private int Y;

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
    @Override
    public boolean equals(Object o) {
        if(this ==o ) {
            return true;
        }
        if(o == null) {
            return false;
        }
        if(getClass() != o.getClass()){
            return false;
        }
        Point p = (Point) o;
        return Objects.equals(X, p.X) && Objects.equals(Y, p.Y);
    }
}
