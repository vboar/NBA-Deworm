package service.impl;

/**
 * Created by Vboar on 2015/6/15.
 */
public class Regression {

    /**
     * 一元线性回归分析
     *
     * @param x
     *            存放自变量x的n个取值
     * @param y
     *            存放与自变量x的n个取值相对应的随机变量y的观察值
     * @param n
     *            观察点数
     * @param a
     *            a(0) 返回回归系数b ,a(1)返回回归系数a
     * @param dt
     *            dt(0) 返回偏差平方和q ,dt(1)返回平均标准偏差s ,dt(2)返回回归平方和p,
     *            dt(3)返回最大偏差umax,dt(4)返回最小偏差umin,dt(5)返回偏差平均值u
     */
    public static void SPT1(double[] x, double[] y, int n, double[] a,
                            double[] dt)
    // double x[],y[],a[2],dt[6];
    {
        int i;
        double xx, yy, e, f, q, u, p, umax, umin, s;
        xx = 0.0;
        yy = 0.0;
        for (i = 0; i <= n - 1; i++) {
            xx = xx + x[i] / n;
            yy = yy + y[i] / n;
        }
        e = 0.0;
        f = 0.0;
        for (i = 0; i <= n - 1; i++) {
            q = x[i] - xx;
            e = e + q * q;
            f = f + q * (y[i] - yy);
        }
        a[1] = f / e;
        a[0] = yy - a[1] * xx;
        q = 0.0;
        u = 0.0;
        p = 0.0;
        umax = 0.0;
        umin = 1.0e+30;
        for (i = 0; i <= n - 1; i++) {
            s = a[1] * x[i] + a[0];
            q = q + (y[i] - s) * (y[i] - s);
            p = p + (s - yy) * (s - yy);
            e = Math.abs(y[i] - s);
            if (e > umax)
                umax = e;
            if (e < umin)
                umin = e;
            u = u + e / n;
        }
        dt[1] = Math.sqrt(q / n);
        dt[0] = q;
        dt[2] = p;
        dt[3] = umax;
        dt[4] = umin;
        dt[5] = u;
    }

    /**
     * 多元线性回归分析
     *
     * @param x
     *            每一列存放m个自变量的观察值
     * @param y
     *            存放随即变量y的n个观察值
     * @param m
     *            自变量的个数
     * @param n
     *            观察数据的组数
     * @param a
     *            返回回归系数a0,...,am
     * @param dt
     *            dt[0]偏差平方和q,dt[1] 平均标准偏差s dt[2]返回复相关系数r dt[3]返回回归平方和u
     * @param v
     *            返回m个自变量的偏相关系数
     */
    public static void sqt2(double[][] x, double[] y, int m, int n, double[] a,
                            double[] dt, double[] v) {
        int i, j, k, mm;
        double q, e, u, p, yy, s, r, pp;
        double[] b = new double[(m + 1) * (m + 1)];
        mm = m + 1;
        b[mm * mm - 1] = n;
        for (j = 0; j <= m - 1; j++) {
            p = 0.0;
            for (i = 0; i <= n - 1; i++)
                p = p + x[j][i];
            b[m * mm + j] = p;
            b[j * mm + m] = p;
        }
        for (i = 0; i <= m - 1; i++)
            for (j = i; j <= m - 1; j++) {
                p = 0.0;
                for (k = 0; k <= n - 1; k++)
                    p = p + x[i][k] * x[j][k];
                b[j * mm + i] = p;
                b[i * mm + j] = p;
            }
        a[m] = 0.0;
        for (i = 0; i <= n - 1; i++)
            a[m] = a[m] + y[i];
        for (i = 0; i <= m - 1; i++) {
            a[i] = 0.0;
            for (j = 0; j <= n - 1; j++)
                a[i] = a[i] + x[i][j] * y[j];
        }
        chlk(b, mm, 1, a);
        yy = 0.0;
        for (i = 0; i <= n - 1; i++)
            yy = yy + y[i] / n;
        q = 0.0;
        e = 0.0;
        u = 0.0;
        for (i = 0; i <= n - 1; i++) {
            p = a[m];
            for (j = 0; j <= m - 1; j++)
                p = p + a[j] * x[j][i];
            q = q + (y[i] - p) * (y[i] - p);
            e = e + (y[i] - yy) * (y[i] - yy);
            u = u + (yy - p) * (yy - p);
        }
        s = Math.sqrt(q / n);
        r = Math.sqrt(1.0 - q / e);
        for (j = 0; j <= m - 1; j++) {
            p = 0.0;
            for (i = 0; i <= n - 1; i++) {
                pp = a[m];
                for (k = 0; k <= m - 1; k++)
                    if (k != j)
                        pp = pp + a[k] * x[k][i];
                p = p + (y[i] - pp) * (y[i] - pp);
            }
            v[j] = Math.sqrt(1.0 - q / p);
        }
        dt[0] = q;
        dt[1] = s;
        dt[2] = r;
        dt[3] = u;
    }

    private static int chlk(double[] a, int n, int m, double[] d) {
        int i, j, k, u, v;
        if ((a[0] + 1.0 == 1.0) || (a[0] < 0.0)) {
            System.out.println("fail\n");
            return (-2);
        }
        a[0] = Math.sqrt(a[0]);
        for (j = 1; j <= n - 1; j++)
            a[j] = a[j] / a[0];
        for (i = 1; i <= n - 1; i++) {
            u = i * n + i;
            for (j = 1; j <= i; j++) {
                v = (j - 1) * n + i;
                a[u] = a[u] - a[v] * a[v];
            }
            if ((a[u] + 1.0 == 1.0) || (a[u] < 0.0)) {
                System.out.println("fail\n");
                return (-2);
            }
            a[u] = Math.sqrt(a[u]);
            if (i != (n - 1)) {
                for (j = i + 1; j <= n - 1; j++) {
                    v = i * n + j;
                    for (k = 1; k <= i; k++)
                        a[v] = a[v] - a[(k - 1) * n + i] * a[(k - 1) * n + j];
                    a[v] = a[v] / a[u];
                }
            }
        }
        for (j = 0; j <= m - 1; j++) {
            d[j] = d[j] / a[0];
            for (i = 1; i <= n - 1; i++) {
                u = i * n + i;
                v = i * m + j;
                for (k = 1; k <= i; k++)
                    d[v] = d[v] - a[(k - 1) * n + i] * d[(k - 1) * m + j];
                d[v] = d[v] / a[u];
            }
        }
        for (j = 0; j <= m - 1; j++) {
            u = (n - 1) * m + j;
            d[u] = d[u] / a[n * n - 1];
            for (k = n - 1; k >= 1; k--) {
                u = (k - 1) * m + j;
                for (i = k; i <= n - 1; i++) {
                    v = (k - 1) * n + i;
                    d[u] = d[u] - a[v] * d[i * m + j];
                }
                v = (k - 1) * n + k - 1;
                d[u] = d[u] / a[v];
            }
        }
        return (2);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        /**
         * 一元回归
         */
        // int i;
        // double[] dt=new double[6];
        // double[] a=new double[2];
        // double[] x={ 0.0,0.1,0.2,0.3,0.4,0.5,
        // 0.6,0.7,0.8,0.9,1.0};
        // double[] y={ 2.75,2.84,2.965,3.01,3.20,
        // 3.25,3.38,3.43,3.55,3.66,3.74};
        // SPT.SPT1(x,y,11,a,dt);
        // System.out.println("");
        // System.out.println("a="+a[1]+" b="+a[0]);
        // System.out.println("q="+dt[0]+" s="+dt[1]+" p="+dt[2]);
        // System.out.println(" umax="+dt[3]+" umin="+dt[4]+" u="+dt[5]);
        /**
         * 多元回归
         */
        int i;
        double[] a = new double[4];
        double[] v = new double[3];
        double[] dt = new double[4];

        double[][] x = { { 1.1, 1.0, 1.2, 1.1, 0.9 },
                { 2.0, 2.0, 1.8, 1.9, 2.1 }, { 3.2, 3.2, 3.0, 2.9, 2.9 } };
        double[] y = { 10.1, 10.2, 10.0, 10.1, 10.0 };
        Regression.sqt2(x, y, 3, 5, a, dt, v);
        for (i = 0; i <= 3; i++)
            System.out.println("a(" + i + ")=" + a[i]);
        System.out.println("q=" + dt[0] + "  s=" + dt[1] + "  r=" + dt[2]);
        for (i = 0; i <= 2; i++)
            System.out.println("v(" + i + ")=" + v[i]);
        System.out.println("u=" + dt[3]);

    }


}
