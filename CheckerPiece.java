import java.awt.Point;

public class CheckerPiece {
    private PieceType color;
    private boolean is_king = false;

    public CheckerPiece(PieceType color) {
        this.color = color;
    }

    public int kingPiece() {
        is_king = true;
        return 1;
    }

    public boolean isKing() {
        return is_king;
    }

    public PieceType get_color() {
        return color;
    }
}
