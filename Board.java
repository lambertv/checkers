import java.util.HashMap;
import java.awt.Point;
import java.lang.Math;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

public class Board {
    private HashMap<Point, CheckerPiece> pieces;
    private HashMap<Point, LinkedList<Move>> board_moves;
    private PieceType turn_color;

    public Board() {
        pieces = new HashMap<Point, CheckerPiece>();
        turn_color = PieceType.BLACK;
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (((y==0 || y==2) && x%2==0) || (y==1 && x%2==1)) {
                    pieces.put(new Point(x, y), 
                               new CheckerPiece(PieceType.BLACK));
                } else if (((y==5 || y==7) && x%2==1) || (y==6 && x%2==0)) {
                    pieces.put(new Point(x, y),
                               new CheckerPiece(PieceType.RED));
                }
            }
        }
        board_moves = create_moves_map(pieces, turn_color);
    }

    public CheckerPiece get_piece(int x, int y) {
        Point point = new Point(x,y);
        if (pieces.containsKey(point)) {
            return pieces.get(point);
        } else {
            return null;
        }
    }

    public HashMap<Point, CheckerPiece> get_pieces() {
        return pieces;
    }

    public void add_piece(Point space, CheckerPiece piece) {
        pieces.put(space, piece);
    }

    public void remove_piece(Point space) {
        pieces.remove(space);
    }

    public boolean is_piece_at(Point space) {
        return pieces.containsKey(space);
    }

    public boolean is_valid_step(Point start_space, StepMovement movement) {
        boolean validity = true;
        Point end_space = new Point(start_space.x, start_space.y); 
        end_space.x += movement.x;
        end_space.y += movement.y;
        if (pieces.containsKey(end_space) || end_space.x < 0 || end_space.x > 7 || 
            end_space.y < 0 || end_space.y > 7) {
            validity = false;
        }
        return validity;
    }

    public void make_move(Move current_move) {
        CheckerPiece piece_to_move = pieces.get(new Point(current_move.get_start_space()));
        pieces.put(current_move.get_end_space(), piece_to_move);
        pieces.remove(current_move.get_start_space());
    }

    public HashMap<Point, LinkedList<Move>> get_board_moves() {
        return board_moves;
    }

    public LinkedList<Move> get_space_moves(Point space) {
        return board_moves.get(space);
    }

    private HashMap<Point, LinkedList<Move>> create_moves_map(HashMap<Point, CheckerPiece> all_pieces, PieceType turn) {
        HashMap<Point, LinkedList<Move>> moves_map = new HashMap<Point, LinkedList<Move>>();
        for (Map.Entry<Point, CheckerPiece> entry : pieces.entrySet()) {
            Point space = entry.getKey();
            CheckerPiece piece = entry.getValue();
            if (piece.get_color() == turn_color) {
                LinkedList<Move> move_list = new LinkedList<Move>();
                Move move = new Move(space);
                move_list = create_jump_moves(space, move_list, move);
                if (!move_list.isEmpty()) {
                    moves_map.put(space, move_list);
                }
            }
        }
        if (moves_map.isEmpty()) {
            for (Map.Entry<Point,CheckerPiece> entry : pieces.entrySet()) {
                Point space = entry.getKey();
                CheckerPiece piece = entry.getValue();
                if (piece.get_color() == turn_color) {
                    LinkedList<Move> step_move_list = new LinkedList<Move>();
                    for (StepMovement movement : StepMovement.values()) {
                        if (is_valid_step(space, movement)) {
                            step_move_list.add(new Move(space, movement));
                        }
                    } 
                    if (!step_move_list.isEmpty()) {
                        moves_map.put(space, step_move_list);
                    }
                }
            }
        }
                        
        return moves_map;
    }

    private LinkedList<Move> create_jump_moves(Point space, LinkedList<Move> moves_list, Move current_move) {
        for (JumpMovement movement : JumpMovement.values()) {
            if (is_valid_jump(space, movement)) {
                Move new_current_move = current_move;
                new_current_move.add(movement);
                moves_list.add(new_current_move);
                Point new_space = new Point(space.x, space.y);
                new_space.x += movement.x;
                new_space.y += movement.y;
                create_jump_moves(new_space, moves_list, new_current_move);
            }
        }
        return moves_list;
    }

    private boolean is_valid_jump(Point start_space, JumpMovement movement) {
        boolean validity = true;
        Point end_space = new Point(start_space.x, start_space.y);
        end_space.x += movement.x;
        end_space.y += movement.y;
        Point jumping_space = start_space;
        jumping_space.x += (movement.x/2);
        jumping_space.y += (movement.y/2);
        if (pieces.containsKey(end_space)) {
            validity = false;
        } else if ((!pieces.containsKey(jumping_space)) ||
                   (pieces.get(jumping_space).get_color() == turn_color)) {
            validity = false;
        }
        return validity;
    }

    public Move find_valid_move(Point target_start_space, Point target_end_space) {
        Move move = new Move(target_start_space);
        for (Map.Entry<Point, LinkedList<Move>> entry : board_moves.entrySet()) {
            Point start_space = entry.getKey();
            LinkedList<Move> move_list = entry.getValue();
            if (target_start_space.equals(start_space)) {
                ListIterator<Move> li = move_list.listIterator();
                while (li.hasNext()) {
                    Move current_move = li.next();
                    if (current_move.get_end_space().equals(target_end_space)) {
                        move = current_move;
                    }
                }
            }
        }
        return move;

    }


    public void print_moves_list() {
        for (Map.Entry<Point, LinkedList<Move>> entry : board_moves.entrySet()) {
            Point space = entry.getKey();
            System.out.printf("\nSTART SPACE: %d, %d", space.x, space.y);
            LinkedList<Move> move_list = entry.getValue();
            System.out.printf("\nSIZE OF MOVE LIST %d\n", move_list.size());
            ListIterator<Move> li = move_list.listIterator();
            while (li.hasNext()) {
                Move current_move = li.next();
                Point end_space = current_move.get_end_space();
                StepMovement step_movement = current_move.get_step_movement();
                if (step_movement != null) {
                    if (step_movement == StepMovement.BACKWARD_LEFT) {
                        System.out.println("STEP BACKWARD LEFT");
                    } else if (step_movement == StepMovement.BACKWARD_RIGHT) {
                        System.out.println("STEP BACKWARD RIGHT");
                    } else if (step_movement == StepMovement.FORWARD_RIGHT) {
                        System.out.println("STEP FORWARD RIGHT");
                    } else if (step_movement == StepMovement.FORWARD_LEFT) {
                        System.out.println("STEP FORWARD LEFT");
                    } else {
                        System.out.println("???");
                    }
                }
                System.out.printf("END SPACE: %d, %d\n", end_space.x, end_space.y);
            }
        }
    }

    public void print_pieces_list() {
        for (Map.Entry<Point, CheckerPiece> entry : pieces.entrySet()) {
            Point space = entry.getKey();
            CheckerPiece piece = entry.getValue();
            System.out.println(space.toString() + " " + piece.toString());
        }
    }
}
