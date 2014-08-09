import java.util.LinkedList;
import java.util.ListIterator;

public class Board {
    private LinkedList<CheckerPiece> pieces;

    public Board() {
        pieces = new LinkedList<CheckerPiece>();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (((y==0 || y==2) && x%2==0) || (y==1 && x%2==1)) {
                    pieces.add(new CheckerPiece(x, y, PieceType.BLACK));
                } else if (((y==5 || y==7) && x%2==1) || (y==6 && x%2==0)) {
                    pieces.add(new CheckerPiece(x, y, PieceType.RED));
                }
            }
        }
    }

    public CheckerPiece get_checker_piece(int x, int y) {
        ListIterator<CheckerPiece> listIterator = pieces.listIterator();
        while (listIterator.hasNext()) {
            CheckerPiece current_piece = listIterator.next();
            if (current_piece.getX() == x && current_piece.getY() == y) {
                return current_piece;
            }
        }
        return null;
    }

    public LinkedList<CheckerPiece> get_pieces() {
        return pieces;
    }

}
