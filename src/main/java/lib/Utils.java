package lib;

public final class Utils {
    public static double[] velocityVector(double x, double y, double a, double b, double velocity){
        double vx= (a-x);
        double vy= (b-y);
        double normalized= Math.sqrt(Math.pow(vx,2)+Math.pow(vy,2));
        double[] vpos= new double[2];
        vpos[0]= (vx / normalized) * velocity;
        vpos[1]= (vy / normalized) * velocity;
        return vpos;
    }

    public static double distance(Position a, Position b){
        return Math.sqrt(Math.pow((b.getX()-a.getX()),2)+Math.pow((b.getY()-a.getY()),2));
    }

    public static boolean isTouching(GraphicalObject obj, double x, double y){
        Position posObj= obj.getPos();
        double radius= obj.getRadius();
        double objX= posObj.getX();
        double objY= posObj.getY();
        return (objY - radius <= y && y <= objY + radius
                && objX - radius <= x && x <= objX + radius);
    }

    public static double magnitudeVector(Position v){
        return magnitudeVector(v.getX(),v.getY());
    }

    public static double magnitudeVector(double x, double y){
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }

    public static double angleBeetweenVector(Position v1,Position v2){
        return angleBeetweenVector(v1.getX(),v1.getY(),v2.getX(),v2.getY());
    }

    public static double angleBeetweenVector(double x1, double y1,double x2, double y2){
        return Math.toDegrees(
                Math.acos(
                                (x1*x2 + y1*y2) / (magnitudeVector(x1,y1) * magnitudeVector(x2,y2)) ) ) ;
    }

    public static void rotateVector(Position vector, double angle){
        angle= Math.toRadians(angle);
        double cosAng= Math.cos(angle);
        double singAng= Math.sin(angle);
        double newX= vector.getX()*cosAng - vector.getY()*singAng;
        double newY= vector.getX()*singAng + vector.getY()*cosAng;
        vector.setXY(newX,newY);
    }

    public static double[] rotateVector(double x, double y, double angle){
        angle= Math.toRadians(angle);
        double cosAng= Math.cos(angle);
        double singAng= Math.sin(angle);
        double[] newVector= new double[2];
        newVector[0]= x*cosAng - y*singAng;
        newVector[1]= x*singAng + y*cosAng;
        return newVector;
    }
}
