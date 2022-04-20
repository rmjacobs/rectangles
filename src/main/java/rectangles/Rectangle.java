package rectangles;

import java.util.ArrayList;
import java.util.List;

public class Rectangle {
    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.leftX = topLeft.getX();
        this.topY = topLeft.getY();
        this.rightX = bottomRight.getX();
        this.bottomY = bottomRight.getY();
    }

    Point topLeft;
    Point bottomRight;
    int leftX;
    int topY;
    int rightX;
    int bottomY;

    private int getWidth(){
        if(this.leftX == this.rightX) {
            return 0;
        }
        return this.leftX + this.rightX;
    }

    private int getHeight() {
        if (this.bottomY == this.topY) {
            return 0;
        }
        return this.bottomY + topY;

    }

    /*
        Returns true if the given rectangle is completely contained within this rectangle.
     */
    public boolean contains(Rectangle rectangle) {
        if(rectangle == null) {
            return false;
        }
        if(rectangle.leftX > this.leftX &&
                rectangle.topY < this.topY &&
                rectangle.rightX < this.rightX &&
                rectangle.bottomY > this.bottomY) {
            return true;
        }
        return false;
    }

    /*
        Returns a collection of points where the rectangles intersect. The collection
        will be empty if there are not intersecting points, thus checking for an empty collection
        could also be used to determine if the rectangles intersect.
     */
    public List<Point> intersectingPoints(Rectangle r2) {
        List<Point> points = new ArrayList<>();
        if(this == null || r2 == null) {
            return points;
        }
        if(this.intersectsWith(r2)){
            points.addAll(getIntersectingPointsOnLeft(this, r2));
            points.addAll(getIntersectingPointsOnRight(this, r2));
            points.addAll(getIntersectingPointsOnTop(this, r2));
            points.addAll(getIntersectingPointsOnBottom(this, r2));
        }
        return points;
    }

    /*
        Returns true if there is an intersection with the rectangle
     */
    public boolean intersectsWith(Rectangle rectangle) {
        if(rectangle == null) {
            return false;
        }
        // Above OR below
        if(rectangle.topY <= this.bottomY ||
                rectangle.bottomY >= this.topY){
            return false;
        }

        // To the right OR to the left
        if(rectangle.leftX >= this.rightX ||
                rectangle.rightX <= this.leftX ) {
            return false;
        }
        // Either rectangle is a line.
        if(rectangle.getHeight() == 0 || rectangle.getWidth() ==0 || this.getHeight() == 0 || this.getWidth() == 0) {
            return false;
        }
        return true;
    }

    /*
        Given two rectangles, return their adjacency type. If they are not adjacent, type is none.
     */
    public Adjacency getAdjacency(Rectangle r2) {
        if(this == null || r2 == null) {
            return Adjacency.NONE;
        }

        if(r2.topY == this.bottomY || r2.bottomY == this.topY) {
            if(r2.leftX > this.leftX && r2.rightX < this.rightX) {
                return Adjacency.SUB_LINE;
            } else if(r2.getWidth() == this.getWidth()) {
                return Adjacency.PROPER;
            } else {
                return Adjacency.PARTIAL;
            }
        }

        if(r2.leftX == this.rightX || r2.rightX == this.leftX) {
            if(r2.bottomY > this.bottomY && r2.topY < this.topY) {
                return Adjacency.SUB_LINE;
            } else if(r2.getHeight() == this.getHeight()) {
                return Adjacency.PROPER;
            } else {
                return Adjacency.PARTIAL;
            }
        }

        return Adjacency.NONE;
    }

    private List<Point> getIntersectingPointsOnBottom(Rectangle r1, Rectangle r2) {
        List<Point> points = new ArrayList<>();

        if(r1.intersectsWith(r2) &&
                r2.leftX > r1.leftX &&
                r2.leftX < r1.rightX &&
                r2.topY > r1.bottomY &&
                r2.bottomY < r1.bottomY){
            points.add(new Point(r2.leftX,r1.bottomY));
        }
        if(r1.intersectsWith(r2) &&
                r2.rightX > r1.leftX &&
                r2.rightX < r1.rightX &&
                r2.topY > r1.bottomY &&
                r2.bottomY < r1.bottomY
        ) {
            points.add(new Point(r2.rightX,r1.bottomY));
        }
        return points;
    }

    private List<Point> getIntersectingPointsOnTop(Rectangle r1, Rectangle r2) {
        List<Point> points = new ArrayList<>();

        if(r1.intersectsWith(r2) &&
                r2.leftX > r1.leftX &&
                r2.leftX < r1.rightX &&
                r2.topY > r1.topY &&
                r2.bottomY < r1.topY){
            points.add(new Point(r2.leftX,r1.topY));
        }
        if(r1.intersectsWith(r2) &&
                r2.rightX > r1.leftX &&
                r2.rightX < r1.rightX &&
                r2.topY > r1.topY &&
                r2.bottomY < r1.topY
        ) {
            points.add(new Point(r2.rightX,r1.topY));
        }
        return points;
    }

    private List<Point> getIntersectingPointsOnRight(Rectangle r1, Rectangle r2) {
        List<Point> points = new ArrayList<>();

        if(r1.intersectsWith(r2) &&
                r2.bottomY < r1.topY &&
                r2.bottomY > r1.bottomY &&
                r2.leftX < r1.rightX &&
                r2.rightX > r1.rightX){
            points.add(new Point(r1.rightX,r2.bottomY));
        }
        if(r1.intersectsWith(r2) &&
                r2.topY < r1.topY &&
                r2.topY > r1.bottomY &&
                r2.leftX < r1.rightX &&
                r2.rightX > r1.rightX
        ) {
            points.add(new Point(r1.rightX,r2.topY));
        }
        return points;
    }

    private List<Point> getIntersectingPointsOnLeft(Rectangle r1, Rectangle r2) {
        List<Point> points = new ArrayList<>();

        if(r1.intersectsWith(r2) &&
                r2.bottomY < r1.topY &&
                r2.bottomY > r1.bottomY &&
                r2.leftX < r1.leftX &&
                r2.rightX > r1.leftX){
            points.add(new Point(r1.leftX,r2.bottomY));
        }
        if(r1.intersectsWith(r2) &&
                r2.topY < r1.topY &&
                r2.topY > r1.bottomY &&
                r2.leftX < r1.leftX &&
                r2.rightX > r1.leftX
        ) {
            points.add(new Point(r1.leftX,r2.topY));
        }
        return points;
    }
}
