public enum StepMovement {
    FORWARD_RIGHT (1,1),
    FORWARD_LEFT (-1,1),
    BACKWARD_RIGHT (1,-1),
    BACKWARD_LEFT (-1,-1);

    public int x;
    public int y;

    StepMovement(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
