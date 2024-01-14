package lib;

public final class Utils {
    /**Calculate the velocity vector from (x,y) to (a,b)
     * @param x the x position of the origin point
     * @param y the y position of the origin point
     * @param a the x position of the direction point
     * @param b the y position of the direction point
     * @param velocity the velocity of the mouvement
     * @return the calculated vector. vector[0] equal the x parameter and vector[1] the y parameter.
     */
    public static double[] velocityVector(double x, double y, double a, double b, double velocity){
        double vx= (a-x);
        double vy= (b-y);
        double normalized= Math.sqrt(Math.pow(vx,2)+Math.pow(vy,2));
        double[] vpos= new double[2];
        vpos[0]= (vx / normalized) * velocity;
        vpos[1]= (vy / normalized) * velocity;
        return vpos;
    }

    /**@return the distance beetween the two given point*/
    public static double distance(Position a, Position b){
        return Math.sqrt(Math.pow((b.getX()-a.getX()),2)+Math.pow((b.getY()-a.getY()),2));
    }

    /**@return the magnitude of the vector (x,y)*/
    public static double magnitudeVector(double x, double y){
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }

    /**@return the angle between the vector (x1,y1) and (x2,y2)*/
    public static double angleBetweenVector(double x1, double y1, double x2, double y2){
        //Theorème d’Al-Kashi
        return Math.toDegrees(
                Math.acos(
                                (x1*x2 + y1*y2) / (magnitudeVector(x1,y1) * magnitudeVector(x2,y2)) ) ) ;
    }

    /**Rotate a point
     * @param point the point to rotate
     * @param center the center of the rotation
     * @param angle the angle of the rotation (in Degree)*/
    public static void rotatePoint(Position point, Position center, double angle){
        double[] newCord= rotatePoint(point.getX(),point.getY(),center.getX(),center.getY(), angle);
        point.setXY(newCord[0],newCord[1]);
    }

    /**Calculate the new position of a point after rotation.
     * @param xP  the x pos of the point to rotate
     * @param yP the ypos of the point to rotate
     * @param xCenter the x pos of the center of the rotation
     * @param yCenter the y pos of the center of the rotation
     * @param angle the angle of the rotation (in Degree)
     * @return the calculated position of the rotated point. pos[0] is the x position and pos[1] the y position*/
    public static double[] rotatePoint(double xP, double yP, double xCenter, double yCenter, double angle){
        angle = Math.toRadians(angle*Math.PI / 180);
        xP = xP - xCenter;
        yP = yP - yCenter;
        double[] newPoint = new double[2];
        newPoint[0] = xP * Math.cos (angle) + yP * Math.sin (angle) + xCenter;
        newPoint[1] = - xP * Math.sin (angle) + yP * Math.cos (angle) + yCenter;
        return newPoint;
    }
}
