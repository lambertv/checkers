import java.util.HashMap;
import java.awt.Point;
import java.lang.Math;

public class Board {
    private HashMap<Point, CheckerPiece> pieces;

    public Board() {
        pieces = new HashMap<Point, CheckerPiece>();
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

    public boolean is_valid_move(Move move) {
        boolean validity = true;
        if ((move.get_end_space().x % 2 == 0 && move.get_end_space().y % 2 != 0) ||
            (move.get_end_space().x % 2 == 1 && move.get_end_space().y % 2 != 1)) {
            validity = false;
        }
        if (pieces.containsKey(move.get_end_space())) {
            validity = false;
        }
        //if (Math.abs(move.get_end_space().x - move.get_start_space().x) > 1 ||
        //    Math.abs(move.get_end_space().y - move.get_start_space().y) > 1) {
        //        validity = false;
        //}
        return validity;
    }

    public void make_move(Move current_move) {
        if (!is_valid_move(current_move)) {
            return;
        }
        CheckerPiece piece_to_move = pieces.get(new Point(current_move.get_start_space()));
        pieces.put(current_move.get_end_space(), piece_to_move);
        pieces.remove(current_move.get_start_space());
    }

}
