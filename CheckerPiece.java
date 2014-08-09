public class CheckerPiece {
    private PieceType color;
    private int x_pos;
    private int y_pos;
    private boolean is_king = false;

    public CheckerPiece(int x_pos, int y_pos, PieceType color) {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.color = color;
    }

    public int moveTo(int x_pos, int y_pos) {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        return 1;
    }

    public int kingPiece() {
        is_king = true;
        return 1;
    }

    public boolean isKing() {
        return is_king;
    }

    public int getX() {
        return x_pos;
    }

    public int getY() {
        return y_pos;
    }

    public PieceType get_color() {
        return color;
    }
}
