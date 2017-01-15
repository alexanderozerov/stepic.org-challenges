package complex;


public class ComplexNumber {
    private final double re;
    private final double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        ComplexNumber cn = (ComplexNumber) o;
        if (this.getIm() != cn.getIm()) {
            return false;
        }
        if (this.getRe() != cn.getRe()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (Double.doubleToLongBits(this.re) - (Double.doubleToLongBits(this.re) >>> 32));
        result *= (int) (Double.doubleToLongBits(this.im) - (Double.doubleToLongBits(this.im) >>> 32));
        return result;
    }
}
