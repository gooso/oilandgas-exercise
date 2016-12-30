package com.varghese.oilandgas.util;

/**
 * Created by newuser on 12/15/16.
 * Copied from http://introcs.cs.princeton.edu/java/97data/LinearRegression.java.html
 */
public class LinearRegression {

    public static long getZeroPrediction(long[] x, long[] y) {
        int n = 0;

        // first pass: read in data, compute xbar and ybar
        double sumx = 0.0, sumy = 0.0, sumx2 = 0.0;

       /* x[0] = 43;
        x[1] = 21;
        x[2] = 25;
        x[3] = 42;
        x[4] = 57;
        x[5] = 59;

        y[0] = 99;
        y[1] = 65;
        y[2] = 79;
        y[3] = 75;
        y[4] = 87;
        y[5] = 81;*/



        for(n=0; n<=x.length-1; n++) {
            sumx  += x[n];
            sumx2 += x[n] * x[n];
            sumy  += y[n];
        }

        System.out.println("N: "+n);

        double xbar = sumx / n;
        double ybar = sumy / n;

        // second pass: compute summary statistics
        double xxbar = 0.0, yybar = 0.0, xybar = 0.0;
        for (int i = 0; i < n; i++) {
            xxbar += (x[i] - xbar) * (x[i] - xbar);
            yybar += (y[i] - ybar) * (y[i] - ybar);
            xybar += (x[i] - xbar) * (y[i] - ybar);
        }
        double beta1 = xybar / xxbar;
        double beta0 = ybar - beta1 * xbar;

        // print results
        System.out.println("y   = " + beta1 + " * x + " + beta0);

        double subtract = (0-beta0);
        double result = subtract/beta1;

        return (long)result;

    }
    public static void main(String[] args) {
        int MAXN = 1000;
        int n = 0;
        double[] x = new double[MAXN];
        double[] y = new double[MAXN];

        // first pass: read in data, compute xbar and ybar
        double sumx = 0.0, sumy = 0.0, sumx2 = 0.0;

        x[0] = 43;
        x[1] = 21;
        x[2] = 25;
        x[3] = 42;
        x[4] = 57;
        x[5] = 59;

        y[0] = 99;
        y[1] = 65;
        y[2] = 79;
        y[3] = 75;
        y[4] = 87;
        y[5] = 81;



        for(n=0; n<=5; n++) {
            sumx  += x[n];
            sumx2 += x[n] * x[n];
            sumy  += y[n];
        }

        System.out.println("N: "+n);

        double xbar = sumx / n;
        double ybar = sumy / n;

        // second pass: compute summary statistics
        double xxbar = 0.0, yybar = 0.0, xybar = 0.0;
        for (int i = 0; i < n; i++) {
            xxbar += (x[i] - xbar) * (x[i] - xbar);
            yybar += (y[i] - ybar) * (y[i] - ybar);
            xybar += (x[i] - xbar) * (y[i] - ybar);
        }
        double beta1 = xybar / xxbar;
        double beta0 = ybar - beta1 * xbar;

        // print results
        System.out.println("y   = " + beta1 + " * x + " + beta0);

        // analyze results
        int df = n - 2;
        double rss = 0.0;      // residual sum of squares
        double ssr = 0.0;      // regression sum of squares
        for (int i = 0; i < n; i++) {
            double fit = beta1*x[i] + beta0;
            rss += (fit - y[i]) * (fit - y[i]);
            ssr += (fit - ybar) * (fit - ybar);
        }
        double R2    = ssr / yybar;
        double svar  = rss / df;
        double svar1 = svar / xxbar;
        double svar0 = svar/n + xbar*xbar*svar1;
        System.out.println("R^2                 = " + R2);
        System.out.println("std error of beta_1 = " + Math.sqrt(svar1));
        System.out.println("std error of beta_0 = " + Math.sqrt(svar0));
        svar0 = svar * sumx2 / (n * xxbar);
        System.out.println("std error of beta_0 = " + Math.sqrt(svar0));

        System.out.println("SSTO = " + yybar);
        System.out.println("SSE  = " + rss);
        System.out.println("SSR  = " + ssr);
    }
}