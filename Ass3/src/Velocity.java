//ID 316044809


/**
 * The class Velocity.
 */
public class Velocity {
    /**
     * The X speed.
     */
    private double xSpeed;
    /**
     * The Y speed.
     */
    private double ySpeed;

    /**
     * Instantiates a new Velocity.
     *
     * @param xSpeed the x speed
     * @param ySpeed the y speed
     */
    public Velocity(final double xSpeed, final double ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    /**
     * Create a new velocity from angle and speed.
     *
     * @param angle the angle of the velocity
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(final double angle, final double speed) {
        // rotating the angle to cartesian coordinate
        final double theta = Math.toRadians(angle - 90);
        // calculating the new XY speeds
        final double xSpeed = speed * Math.cos(theta);
        final double ySpeed = speed * Math.sin(theta);
        return new Velocity(xSpeed, ySpeed);
    }

    /**
     * Apply to point.
     *
     * @param p the point to apply velocity to
     * @return the point after the applied
     */
    public Point applyToPoint(final Point p) {
        return new Point(p.getX() + this.xSpeed, p.getY() + this.ySpeed);
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public double getXSpeed() {
        return xSpeed;
    }

    /**
     * Sets speed.
     *
     * @param newXSpeed the new x speed
     */
    public void setXSpeed(final double newXSpeed) {
        this.xSpeed = newXSpeed;
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public double getYSpeed() {
        return ySpeed;
    }

    /**
     * Sets speed.
     *
     * @param newYSpeed the new y speed
     */
    public void setYSpeed(final double newYSpeed) {
        this.ySpeed = newYSpeed;
    }

    /**
     * Reverse  the xSpeed.
     */
    public void reverseX() {
        this.xSpeed *= -1;
    }

    /**
     * Reverse the ySpeed.
     */
    public void reverseY() {
        this.ySpeed *= -1;
    }

    @Override
    public String toString() {
        return "Velocity{" + "xSpeed=" + xSpeed + ", ySpeed=" + ySpeed + '}';
    }
}
