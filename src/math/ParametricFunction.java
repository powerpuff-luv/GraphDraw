package math;

public class ParametricFunction implements ParametricCurve{

    public ParametricFunction(){}
    public double invokeX(double t){
        return -2 * Math.cos(t) + 3 * Math.cos(Math.abs(2 * t / 3));
    }

    public double invokeY(double t){
        return -2 * Math.sin(t) + 3 * Math.sin(Math.abs(2 * t / 3));
    }
}
