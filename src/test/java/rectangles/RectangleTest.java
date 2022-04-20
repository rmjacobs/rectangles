package rectangles;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertTrue;

public class RectangleTest {

    @Test
    public void rectangleIntersectsOnLeftSide() {
        Rectangle rect1 = new Rectangle(new Point(1, 10), new Point(5, 5));
        Rectangle rect2 = new Rectangle(new Point(0, 9), new Point(4, 6));

        assertThat(rect1.intersectsWith(rect2)).isTrue();
    }

    @Test
    public void rectangleIntersectsOnRightSide() {
        Rectangle rect1 = new Rectangle(new Point(1, 10), new Point(5, 5));
        Rectangle rect2 = new Rectangle(new Point(4, 9), new Point(6, 6));

        assertThat(rect1.intersectsWith(rect2)).isTrue();
    }

    @Test
    public void rectangleIntersectsOnBottom() {
        Rectangle rect1 = new Rectangle(new Point(1, 10), new Point(5, 5));
        Rectangle rect2 = new Rectangle(new Point(2, 6), new Point(6, 4));

        assertThat(rect1.intersectsWith(rect2)).isTrue();
    }

    @Test
    public void rectangleIntersectsOnTop() {
        Rectangle rect1 = new Rectangle(new Point(1, 10), new Point(5, 5));
        Rectangle rect2 = new Rectangle(new Point(3, 9), new Point(6, 6));

        assertThat(rect1.intersectsWith(rect2)).isTrue();
    }

    @Test
    public void rectangleDoesNotIntersectWhenToTheLeft() {
        Rectangle rect1 = new Rectangle(new Point(5, 10), new Point(10, 5));
        Rectangle rect2 = new Rectangle(new Point(0, 9), new Point(4, 6));

        assertThat(rect1.intersectsWith(rect2)).isFalse();
    }

    @Test
    public void rectangleDoesNotIntersectWhenToTheRight() {
        Rectangle rect1 = new Rectangle(new Point(5, 10), new Point(10, 5));
        Rectangle rect2 = new Rectangle(new Point(11, 9), new Point(4, 6));

        assertThat(rect1.intersectsWith(rect2)).isFalse();
    }

    @Test
    public void rectangleDoesNotIntersectWhenAbove() {
        Rectangle rect1 = new Rectangle(new Point(5, 10), new Point(10, 5));
        Rectangle rect2 = new Rectangle(new Point(15, 9), new Point(4, 11));

        assertThat(rect1.intersectsWith(rect2)).isFalse();
    }

    @Test
    public void rectangleDoesNotIntersectWhenBelow() {
        Rectangle rect1 = new Rectangle(new Point(15, 9), new Point(4, 11));
        Rectangle rect2 = new Rectangle(new Point(5, 10), new Point(10, 5));


        assertThat(rect1.intersectsWith(rect2)).isFalse();
    }

    @Test
    public void testRectangleIsNotWithinARectangle() {
        Rectangle rect1 = new Rectangle(new Point(1, 10), new Point(5, 5));
        Rectangle rect2 = new Rectangle(new Point(0, 9), new Point(4, 6));

        assertThat(rect1.contains(rect2)).isFalse();
    }

    @Test
    public void testRectangleContainsARectangle() {
        Rectangle rect1 = new Rectangle(new Point(1, 10), new Point(5, 5));
        Rectangle rect2 = new Rectangle(new Point(2, 9), new Point(4, 6));

        assert (rect1.contains(rect2) == true);
    }

    @Test
    public void testRectangleDoesNotContainAnOverlappingRectangle() {
        Rectangle rect1 = new Rectangle(new Point(1, 10), new Point(5, 5));
        Rectangle rect2 = new Rectangle(new Point(4, 9), new Point(6, 6));

        assertThat(rect1.contains(rect2)).isFalse();
    }

    @Test
    public void testRectangleDoesNotContainAdjacentRectangle() {
        Rectangle r1 = new Rectangle(new Point(1, 6), new Point(6, 3));
        Rectangle r2 = new Rectangle(new Point(2, 5), new Point(4, 1));

        assertThat(r1.contains(r2)).isFalse();
    }

    @Test
    public void intersectingPointsOnBottom() {
        Rectangle r1 = new Rectangle(new Point(1, 6), new Point(6, 3));
        Rectangle r2 = new Rectangle(new Point(2, 5), new Point(4, 2));
        Rectangle r3 = new Rectangle(new Point(2, 5), new Point(7, 2));
        Rectangle r4 = new Rectangle(new Point(0, 5), new Point(3, 2));

        List<Point> pointList = r1.intersectingPoints(r2); // two intersecting points on bottom
        List<Point> expectedIntersectingPoints = new ArrayList<>();
        expectedIntersectingPoints.add(new Point(2, 3));
        expectedIntersectingPoints.add(new Point(4, 3));

        assertThat(pointList.size()).isEqualTo(2);
        assertThat(pointList).hasSameElementsAs(expectedIntersectingPoints);

        expectedIntersectingPoints = new ArrayList<>();
        expectedIntersectingPoints.add(new Point(2, 3));
        expectedIntersectingPoints.add(new Point(6, 5));
        pointList = r1.intersectingPoints(r3); // only the left side intersects bottom

        assertThat(pointList.size()).isEqualTo(2);
        assertThat(pointList).hasSameElementsAs(expectedIntersectingPoints);

        expectedIntersectingPoints = new ArrayList<>();
        expectedIntersectingPoints.add(new Point(3, 3));
        expectedIntersectingPoints.add(new Point(1, 5));

        pointList = r1.intersectingPoints(r4); // only the right side intersects bottom
        assertThat(pointList.size()).isEqualTo(2);
        assertThat(pointList).hasSameElementsAs(expectedIntersectingPoints);

    }

    @Test
    public void intersectingPointsOnTop() {
        Rectangle r1 = new Rectangle(new Point(1, 6), new Point(6, 3));
        Rectangle r2 = new Rectangle(new Point(2, 8), new Point(4, 4));
        Rectangle r3 = new Rectangle(new Point(2, 8), new Point(8, 4));
        Rectangle r4 = new Rectangle(new Point(0, 8), new Point(4, 4));

        List<Point> pointList = r1.intersectingPoints(r2); // two intersecting points on bottom
        List<Point> expectedIntersectingPoints = new ArrayList<>();
        expectedIntersectingPoints.add(new Point(2,6));
        expectedIntersectingPoints.add(new Point(4,6));
        assertThat(pointList.size()).isEqualTo(2);
        assertThat(pointList).hasSameElementsAs(expectedIntersectingPoints);

        pointList = r1.intersectingPoints(r3); // only the left side intersects
        expectedIntersectingPoints = new ArrayList<>();
        expectedIntersectingPoints.add(new Point(2,6));
        expectedIntersectingPoints.add(new Point(6,4));

        assertThat(pointList.size()).isEqualTo(2);
        assertThat(pointList).hasSameElementsAs(expectedIntersectingPoints);

        pointList = r1.intersectingPoints(r4); // only the right side intersectsexpectedIntersectingPoints = new ArrayList<>();
        expectedIntersectingPoints = new ArrayList<>();
        expectedIntersectingPoints.add(new Point(4,6));
        expectedIntersectingPoints.add(new Point(1,4));

        assertThat(pointList.size()).isEqualTo(2);
        assertThat(pointList).hasSameElementsAs(expectedIntersectingPoints);

    }

    @Test
    public void intersectingPointsOnLeft() {
        Rectangle r1 = new Rectangle(new Point(1, 6), new Point(6, 3));
        Rectangle r2 = new Rectangle(new Point(0, 5), new Point(4, 4));
        Rectangle r3 = new Rectangle(new Point(0, 5), new Point(4, 1));
        Rectangle r4 = new Rectangle(new Point(0, 7), new Point(4, 4));

        List<Point> pointList = r1.intersectingPoints(r2); // two intersecting points on left
        List<Point> expectedIntersectingPoints = new ArrayList<>();
        expectedIntersectingPoints.add(new Point(1,5));
        expectedIntersectingPoints.add(new Point(1,4));

        assertThat(pointList.size()).isEqualTo(2);
        assertThat(pointList).hasSameElementsAs(expectedIntersectingPoints);

        pointList = r1.intersectingPoints(r3); // only the top intersects
        expectedIntersectingPoints = new ArrayList<>();
        expectedIntersectingPoints.add(new Point(1, 5));
        expectedIntersectingPoints.add(new Point(4, 3));

        assertThat(pointList.size()).isEqualTo(2);
        assertThat(pointList).hasSameElementsAs(expectedIntersectingPoints);

        pointList = r1.intersectingPoints(r4); // only the bottom intersects
        expectedIntersectingPoints = new ArrayList<>();
        expectedIntersectingPoints.add(new Point(1, 4));
        expectedIntersectingPoints.add(new Point(4, 6));

        assertThat(pointList.size()).isEqualTo(2);
        assertThat(pointList).hasSameElementsAs(expectedIntersectingPoints);

    }

    @Test
    public void intersectingPointsOnRight() {
        Rectangle r1 = new Rectangle(new Point(1, 6), new Point(5, 2));
        Rectangle r2 = new Rectangle(new Point(4, 5), new Point(7, 3));
        Rectangle r3 = new Rectangle(new Point(4, 5), new Point(7, 1));
        Rectangle r4 = new Rectangle(new Point(4, 8), new Point(7, 3));

        List<Point> pointList = r1.intersectingPoints(r2); // two intersecting points on left
        List<Point> expectedIntersectingPoints = new ArrayList<>();
        expectedIntersectingPoints.add(new Point(5, 5));
        expectedIntersectingPoints.add(new Point( 5,3 ));

        assertThat(pointList.size()).isEqualTo(2);
        assertThat(pointList).hasSameElementsAs(expectedIntersectingPoints);

        pointList = r1.intersectingPoints(r3); // only the top intersects
        expectedIntersectingPoints = new ArrayList<>();
        expectedIntersectingPoints.add(new Point(5, 5));
        expectedIntersectingPoints.add(new Point(4, 2));
        assertThat(pointList.size()).isEqualTo(2);
        assertThat(pointList).hasSameElementsAs(expectedIntersectingPoints);

        pointList = r1.intersectingPoints(r4); // only the bottom intersects
        expectedIntersectingPoints = new ArrayList<>();
        expectedIntersectingPoints.add(new Point(5, 3));
        expectedIntersectingPoints.add(new Point(4, 6));

        assertThat(pointList.size()).isEqualTo(2);
        assertThat(pointList).hasSameElementsAs(expectedIntersectingPoints);

    }

    @Test
    public void intersectingPointsOnTopAndBottom() {
        Rectangle r1 = new Rectangle(new Point(1, 6), new Point(5, 2));
        Rectangle r2 = new Rectangle(new Point(2, 7), new Point(4, 1));

        List<Point> pointList = r1.intersectingPoints(r2); // two intersecting points on left
        List<Point> expectedIntersectingPoints = new ArrayList<>();
        expectedIntersectingPoints.add(new Point(2, 6));
        expectedIntersectingPoints.add(new Point( 4,6 ));
        expectedIntersectingPoints.add(new Point(2, 2));
        expectedIntersectingPoints.add(new Point( 4,2 ));

        assertThat(pointList.size()).isEqualTo(4);
        assertThat(pointList).hasSameElementsAs(expectedIntersectingPoints);
    }

    @Test
    public void intersectingPointsOnLeftAndRight() {
        Rectangle r1 = new Rectangle(new Point(1, 6), new Point(5, 2));
        Rectangle r2 = new Rectangle(new Point(0, 5), new Point(6, 3));


        List<Point> pointList = r1.intersectingPoints(r2); // two intersecting points on left
        List<Point> expectedIntersectingPoints = new ArrayList<>();
        expectedIntersectingPoints.add(new Point(1, 3));
        expectedIntersectingPoints.add(new Point( 1,5 ));
        expectedIntersectingPoints.add(new Point(5, 3));
        expectedIntersectingPoints.add(new Point( 5,5 ));

        assertThat(pointList.size()).isEqualTo(4);
        assertThat(pointList).hasSameElementsAs(expectedIntersectingPoints);

    }


    @Test
    public void checkBottomAdjacency() {
        Rectangle r1 = new Rectangle(new Point(5, 10), new Point(15, 5));
        Rectangle r2 = new Rectangle(new Point(2, 5), new Point(4, 1)); // partial bottom
        Rectangle r3 = new Rectangle(new Point(5, 5), new Point(15, 1)); // proper bottom
        Rectangle r4 = new Rectangle(new Point(6, 5), new Point(14, 1)); // sub line
        Rectangle r5 = new Rectangle(new Point(6, 4), new Point(14, 1)); // none

        Adjacency adjacency = r1.getAdjacency( r2);
        assertThat(adjacency).isEqualTo(Adjacency.PARTIAL);

        adjacency = r1.getAdjacency(r3);
        assertThat(adjacency).isEqualTo(Adjacency.PROPER);

        adjacency = r1.getAdjacency(r4);
        assertThat(adjacency).isEqualTo(Adjacency.SUB_LINE);

        adjacency = r1.getAdjacency(r5);
        assertThat(adjacency).isEqualTo(Adjacency.NONE);

    }

    @Test
    public void checkRightAdjacency() {
        Rectangle r1 = new Rectangle(new Point(5, 10), new Point(15, 5));
        Rectangle r2 = new Rectangle(new Point(15, 12), new Point(16, 8)); // partial
        Rectangle r3 = new Rectangle(new Point(15, 10), new Point(16, 5)); // proper
        Rectangle r4 = new Rectangle(new Point(15, 9), new Point(17, 6)); // sub line
        Rectangle r5 = new Rectangle(new Point(16, 4), new Point(14, 1)); // none

        Adjacency adjacency = r1.getAdjacency(r2);
        assertThat(adjacency).isEqualTo(Adjacency.PARTIAL);

        adjacency = r1.getAdjacency(r3);
        assertThat(adjacency).isEqualTo(Adjacency.PROPER);

        adjacency = r1.getAdjacency(r4);
        assertThat(adjacency).isEqualTo(Adjacency.SUB_LINE);

        adjacency = r1.getAdjacency(r5);
        assertThat(adjacency).isEqualTo(Adjacency.NONE);
    }

    @Test
    public void checkTopAdjacency() {
        Rectangle r1 = new Rectangle(new Point(5, 10), new Point(15, 5));
        Rectangle r2 = new Rectangle(new Point(4, 12), new Point(12, 10)); // partial
        Rectangle r3 = new Rectangle(new Point(5, 12), new Point(15, 10)); // proper
        Rectangle r4 = new Rectangle(new Point(7, 12), new Point(13, 10)); // sub line
        Rectangle r5 = new Rectangle(new Point(7, 14), new Point(13, 11)); // none

        Adjacency adjacency = r1.getAdjacency(r2);
        assertThat(adjacency).isEqualTo(Adjacency.PARTIAL);

        adjacency = r1.getAdjacency(r3);
        assertThat(adjacency).isEqualTo(Adjacency.PROPER);

        adjacency = r1.getAdjacency(r4);
        assertThat(adjacency).isEqualTo(Adjacency.SUB_LINE);

        adjacency = r1.getAdjacency(r5);
        assertThat(adjacency).isEqualTo(Adjacency.NONE);
    }

    @Test
    public void checkLeftAdjacency() {
        Rectangle r1 = new Rectangle(new Point(5, 10), new Point(15, 5));
        Rectangle r2 = new Rectangle(new Point(2, 15), new Point(5, 6)); // partial
        Rectangle r3 = new Rectangle(new Point(2, 10), new Point(5, 5)); // proper
        Rectangle r4 = new Rectangle(new Point(2, 9), new Point(5, 6)); // sub line
        Rectangle r5 = new Rectangle(new Point(2, 9), new Point(4, 6)); // none

        Adjacency adjacency = r1.getAdjacency(r2);
        assertThat(adjacency).isEqualTo(Adjacency.PARTIAL);

        adjacency = r1.getAdjacency(r3);
        assertThat(adjacency).isEqualTo(Adjacency.PROPER);

        adjacency = r1.getAdjacency(r4);
        assertThat(adjacency).isEqualTo(Adjacency.SUB_LINE);

        adjacency = r1.getAdjacency(r5);
        assertThat(adjacency).isEqualTo(Adjacency.NONE);
    }
}

