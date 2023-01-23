package math;

public class DefiniteFunction implements Function{
    public DefiniteFunction(){}
    public double invoke(double x){
        return Math.sin(x) - x;
    }
}
