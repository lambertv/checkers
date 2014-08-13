import java.awt.Point;
import java.util.LinkedList;

public class Move {
    private Point start_space;
    private Point end_space;
    private LinkedList<JumpMovement> jump_movements;
    private StepMovement step_movement;

    public Move(Point start_space) {
        this.start_space = start_space;
        end_space = new Point(start_space.x, start_space.y);
        jump_movements = new LinkedList<JumpMovement>();
        step_movement = null;
    }

    public Move(Point start_space, StepMovement movement) {
        this.start_space = start_space;
        step_movement = movement;
        end_space = new Point(start_space.x, start_space.y);
        end_space.x += movement.x;
        end_space.y += movement.y;
        jump_movements = null;
    }

    public void add(JumpMovement movement) {
        jump_movements.add(movement);
        end_space.x += movement.x;
        end_space.y += movement.y;
    }

    public Point get_start_space() {
        return start_space;
    }

    public Point get_end_space() {
        return end_space;
    }

    public StepMovement get_step_movement() {
        return step_movement;
    }

}

