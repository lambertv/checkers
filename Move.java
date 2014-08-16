import java.awt.Point;
import java.util.LinkedList;
import java.util.ListIterator;

public class Move {
    private Point start_space;
    private Point end_space;
    private LinkedList<JumpMovement> jump_movements;
    private StepMovement step_movement;
    private boolean is_jump;
    private LinkedList<Point> jumping_spaces;

    public Move(Move move_copy) {
        start_space = new Point(move_copy.get_start_space().x,
                                move_copy.get_start_space().y);
        end_space = new Point(move_copy.get_end_space().x,
                              move_copy.get_end_space().y);
        jumping_spaces = new LinkedList<Point>();
        ListIterator<Point> li = move_copy.get_jump_spaces().listIterator();
        while (li.hasNext()) {
            jumping_spaces.add(li.next());
        }
        jump_movements = new LinkedList<JumpMovement>();
        jump_movements = (LinkedList<JumpMovement>) move_copy.get_jump_movements().clone();
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
        System.out.println("previous spaces: " + jumping_spaces.toString());
        end_space.x += movement.x;
        end_space.y += movement.y;
        Point jumping_space = new Point(end_space.x, end_space.y);
        System.out.println("space landing on: " + end_space.toString());
        jumping_space.x -= movement.x/2;
        jumping_space.y -= movement.y/2;
        System.out.println("space jumped over: " + jumping_space.toString());
        jumping_spaces.add(jumping_space);
        System.out.println("new space list: " + jumping_spaces.toString());
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

