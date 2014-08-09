import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.awt.Point;
import java.util.Collection;

public class Board {
    private HashMap<Point, CheckerPiece> pieces;

    public Board() {
        pieces = new HashMap<Point, CheckerPiece>();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (((y==0 || y==2) && x%2==0) || (y==1 && x%2==1)) {
                    pieces.put(new Point(x, y), 
                               new CheckerPiece(x, y, PieceType.BLACK));
                } else if (((y==5 || y==7) && x%2==1) || (y==6 && x%2==0)) {
                    pieces.put(new Point(x, y),
                               new CheckerPiece(x, y, PieceType.RED));
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

    public LinkedList<CheckerPiece> get_pieces() {
        Collection<CheckerPiece> myCollection = pieces.values();
        LinkedList<CheckerPiece> piece_list = new LinkedList<CheckerPiece>(myCollection);
        return piece_list;
    }

    public void add_piece(CheckerPiece piece) {
        Point point = new Point(piece.getX(), piece.getY());
        pieces.put(point, piece);
    }

    public void remove_piece(int x, int y) {
        pieces.remove(new Point(x,y));
    }

    public void print_pieces() {
        Collection<CheckerPiece> myCollection = pieces.values();
        LinkedList<CheckerPiece> piece_list = new LinkedList<CheckerPiece>(myCollection);
        ListIterator<CheckerPiece> listIterator = piece_list.listIterator();
        while (listIterator.hasNext()) {
           CheckerPiece current_piece = listIterator.next();
           int x = current_piece.getX();
           int y = current_piece.getY();
           if (current_piece.get_color() == PieceType.BLACK) {
               System.out.printf("\nBlack piece at: %d, %d", x, y);
           } else {
               System.out.printf("\nRed piece at:   %d, %d", x, y);
           }
        }
    }

           

}
