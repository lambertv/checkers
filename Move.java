import java.awt.Point;
import java.util.LinkedList;

public class Move {
    private boolean valid;
    private Point start_space;
    private Point end_space;

    public Move(Point start_space, Point end_space) {
        this.start_space = start_space;
        this.end_space = end_space;
    }

    public Point get_start_space() {
        return start_space;
    }

    public Point get_end_space() {
        return end_space;
    }

}

