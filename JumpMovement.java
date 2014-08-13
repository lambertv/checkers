public enum JumpMovement {
    FORWARD_RIGHT (2,2),
    FORWARD_LEFT (-2,2),
    BACKWARD_RIGHT (2,-2),
    BACKWARD_LEFT (-2,-2);

    public final int x;
    public final int y;

    JumpMovement(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
