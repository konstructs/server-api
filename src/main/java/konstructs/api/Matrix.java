package konstructs.api;

public final class Matrix {
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;
    private final int h;
    private final int i;

    public Matrix(int a, int b, int c, int d, int e, int f, int g, int h, int i) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    public int getD() {
        return d;
    }

    public int getE() {
        return e;
    }

    public int getF() {
        return f;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    public int getI() {
        return i;
    }

    public Position getAdg() {
        return new Position(a, d, g);
    }

    public Position getBeh() {
        return new Position(b,e,h);
    }

    public Position getCfi() {
        return new Position(c, f, i);
    }

    public Matrix multiply(Matrix m) {
        return new Matrix(
                a*m.a + b*m.d + c*m.g, a*m.b + b*m.e + c*m.h, a*m.c + b*m.f + c*m.i,
                d*m.a + e*m.d + f*m.g, d*m.b + e*m.e + f*m.h, d*m.c + e*m.f + f*m.i,
                g*m.a + h*m.d + i*m.g, g*m.b + h*m.e + i*m.h, g*m.c + h*m.f + i*m.i);
    }

    public Position multiply(Position p) {
        return new Position(a*p.getX() + b*p.getY() + c*p.getZ(),
                d*p.getX() + e*p.getY() + f*p.getZ(),
                g*p.getX() + h*p.getY() + i*p.getZ());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix matrix = (Matrix) o;

        if (a != matrix.a) return false;
        if (b != matrix.b) return false;
        if (c != matrix.c) return false;
        if (d != matrix.d) return false;
        if (e != matrix.e) return false;
        if (f != matrix.f) return false;
        if (g != matrix.g) return false;
        if (h != matrix.h) return false;
        return i == matrix.i;

    }

    @Override
    public int hashCode() {
        int result = a;
        result = 31 * result + b;
        result = 31 * result + c;
        result = 31 * result + d;
        result = 31 * result + e;
        result = 31 * result + f;
        result = 31 * result + g;
        result = 31 * result + h;
        result = 31 * result + i;
        return result;
    }

    @Override
    public String toString() {
        return "Matrix(" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                ", e=" + e +
                ", f=" + f +
                ", g=" + g +
                ", h=" + h +
                ", i=" + i +
                ')';
    }
}
