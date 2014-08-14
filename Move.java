import java.awt.Point;
import java.util.LinkedList;

public class Move {
    private Point start_space;
    private Point end_space;
    private LinkedList<JumpMovement> jump_movements;
    private StepMovement step_movement;
    private boolean is_jump;
    private LinkedList<Point> jumping_spaces;

    public Move(Move move_copy) {
        start_space = move_copy.get_start_space();
        end_space = move_copy.get_end_space();
        jumping_spaces = move_copy.get_jump_spaces();
        jump_movements = move_copy.get_jump_movements();
        step_movement = move_copy.get_step_movement();
        is_jump = move_copy.is_jump();
    }

    public Move(Point start_space) {
        this.start_space = start_space;
        end_space = new Point(start_space.x, start_space.y);
        jumping_spaces = new LinkedList<Point>();
        jump_movements = new LinkedList<JumpMovement>();
        step_movement = null;
        is_jump = false;
    }

    public Move(Point start_space, StepMovement movement) {
        this.start_space = start_space;
        step_movement = movement;
        end_space = new Point(start_space.x, start_space.y);
        end_space.x += movement.x;
        end_space.y += movement.y;
        jump_movements = null;
        jumping_spaces = null;
        is_jump = false;
    }

    public void add(JumpMovement movement) {
        jump_movements.add(movement);
        end_space.x += movement.x;
        end_space.y += movement.y;
        Point jumping_space = new Point(end_space.x, end_space.y);
        jumping_space.x -= movement.x/2;
        jumping_space.y -= movement.y/2;
        jumping_spaces.add(jumping_space);
        is_jump = true;
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

    public boolean is_jump() {
        return is_jump;
    }

    public LinkedList<Point> get_jump_spaces() {
        return jumping_spaces;
    }

    public LinkedList<JumpMovement> get_jump_movements() {
        return jump_movements;
    }

}

