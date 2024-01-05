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
}
