/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polifoniaLib;

import processing.core.PApplet;

/**
 *
 * @author Cristian
 */
public class PLevelt extends PApplet {

    private double d = 0.24;
    private double b1 = 3.5;
    private double b2 = 5.57;
    private double k = 0.021;
    private double m = 19;
    private Timbre timb;

    public PLevelt() {
    }

    public PLevelt(Timbre tm) {
        timb = tm;
    }

    public float disDef(float f1, float f2) {
        float disonancia = 0;
        int n1 = timb.size();
        int n2 = timb.size();
        float[][] L = new float[n1][n2];
        float[][] M = new float[n1][n2];
        float[][] X = new float[n1][n2];
        float[][] Y = new float[n1][n2];

        // Matriz de coeficientes de armonicos
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                L[i][j] = Math.min(timb.get(i), timb.get(j));
//                System.out.println("L: "+L[i][j]);
            }
        }
        // matrices XYZ
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                M[i][j] = g(i * f1, j * f2);
                X[i][j] = g(i * f1, j * f1);
                Y[i][j] = g(i * f2, j * f2);
//                System.out.println("M: " + M[i][j]);
//                System.out.println("X: " + X[i][j]);
//                System.out.println("Y: " + Y[i][j]);
            }
        }
        // Suma de disonancia
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                disonancia += L[i][j] * (M[i][j] + 0.5 * X[i][j] + 0.5 * Y[i][j]);
            }
        }
        return disonancia;
    }

    public float disDef(float fx, float fy, float fz) {
        float disonancia = 0;
        int n1 = timb.size();
        int n2 = timb.size();
        float[][] L = new float[n1][n2];
        float[][] XX = new float[n1][n2];
        float[][] YY = new float[n1][n2];
        float[][] ZZ = new float[n1][n2];
        float[][] XY = new float[n1][n2];
        float[][] XZ = new float[n1][n2];
        float[][] YZ = new float[n1][n2];


        // Matriz de coeficientes de armonicos
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                L[i][j] = Math.min(timb.get(i), timb.get(j));
//                System.out.println("L: "+L[i][j]);
            }
        }
        // matrices XYZ
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                XX[i][j] = g(i * fx, j * fx);
                YY[i][j] = g(i * fy, j * fy);
                ZZ[i][j] = g(i * fz, j * fz);
                XY[i][j] = g(i * fx, j * fy);
                XZ[i][j] = g(i * fx, j * fz);
                YZ[i][j] = g(i * fy, j * fz);
//                System.out.println("M: " + M[i][j]);
//                System.out.println("X: " + X[i][j]);
//                System.out.println("Y: " + Y[i][j]);
            }
        }
        // Suma de disonancia
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                disonancia += L[i][j] * (XY[i][j] + XZ[i][j] + YZ[i][j]
                        + 0.5 * XX[i][j] + 0.5 * YY[i][j] + 0.5 * ZZ[i][j]);
            }
        }
        return disonancia;
    }

    public float disDef(float fx, float fy, float fz, float fw) {
        float disonancia = 0;
        int n1 = timb.size();
        int n2 = timb.size();
        float[][] L = new float[n1][n2];
        float[][] XX = new float[n1][n2];
        float[][] YY = new float[n1][n2];
        float[][] ZZ = new float[n1][n2];
        float[][] WW = new float[n1][n2];

        float[][] XY = new float[n1][n2];
        float[][] XZ = new float[n1][n2];
        float[][] XW = new float[n1][n2];
        float[][] YZ = new float[n1][n2];
        float[][] YW = new float[n1][n2];
        float[][] ZW = new float[n1][n2];

        // Matriz de coeficientes de armonicos
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                L[i][j] = Math.min(timb.get(i), timb.get(j));
            }
        }
        // matrices XYZ
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                XX[i][j] = g(i * fx, j * fx);
                YY[i][j] = g(i * fy, j * fy);
                ZZ[i][j] = g(i * fz, j * fz);
                WW[i][j] = g(i * fw, j * fw);

                XY[i][j] = g(i * fx, j * fy);
                XZ[i][j] = g(i * fx, j * fz);
                XW[i][j] = g(i * fx, j * fw);

                YZ[i][j] = g(i * fy, j * fz);
                YW[i][j] = g(i * fy, j * fw);

                ZW[i][j] = g(i * fz, j * fw);
//                System.out.println("M: " + M[i][j]);
//                System.out.println("X: " + X[i][j]);
//                System.out.println("Y: " + Y[i][j]);
            }
        }
        // Suma de disonancia
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                disonancia += L[i][j] * (XY[i][j] + XZ[i][j] + XW[i][j]
                        + YZ[i][j] + YW[i][j] + ZW[i][j]
                        + 0.5 * XX[i][j] + 0.5 * YY[i][j]
                        + 0.5 * ZZ[i][j] + 0.5 * WW[i][j]);
            }
        }
        return disonancia;
    }

    public float disDef(float fx, float fy, float fz, float fw, float fv) {
        float disonancia = 0;
        int n1 = timb.size();
        int n2 = timb.size();
        float[][] L = new float[n1][n2];
        float[][] XX = new float[n1][n2];
        float[][] YY = new float[n1][n2];
        float[][] ZZ = new float[n1][n2];
        float[][] WW = new float[n1][n2];
        float[][] VV = new float[n1][n2];

        float[][] XY = new float[n1][n2];
        float[][] XZ = new float[n1][n2];
        float[][] XW = new float[n1][n2];
        float[][] XV = new float[n1][n2];

        float[][] YZ = new float[n1][n2];
        float[][] YW = new float[n1][n2];
        float[][] YV = new float[n1][n2];

        float[][] ZW = new float[n1][n2];
        float[][] ZV = new float[n1][n2];

        float[][] WV = new float[n1][n2];


        // Matriz de coeficientes de armonicos
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                L[i][j] = Math.min(timb.get(i), timb.get(j));
            }
        }
        // matrices XYZ
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                XX[i][j] = g(i * fx, j * fx);
                YY[i][j] = g(i * fy, j * fy);
                ZZ[i][j] = g(i * fz, j * fz);
                WW[i][j] = g(i * fw, j * fw);
                VV[i][j] = g(i * fv, j * fv);

                XY[i][j] = g(i * fx, j * fy);
                XZ[i][j] = g(i * fx, j * fz);
                XW[i][j] = g(i * fx, j * fw);
                XV[i][j] = g(i * fx, j * fv);

                YZ[i][j] = g(i * fy, j * fz);
                YW[i][j] = g(i * fy, j * fw);
                YV[i][j] = g(i * fy, j * fv);

                ZW[i][j] = g(i * fz, j * fw);
                ZV[i][j] = g(i * fz, j * fv);

                WV[i][j] = g(i * fw, j * fv);
//                System.out.println("M: " + M[i][j]);
//                System.out.println("X: " + X[i][j]);
//                System.out.println("Y: " + Y[i][j]);
            }
        }
        // Suma de disonancia
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                disonancia += L[i][j] * (XY[i][j] + XZ[i][j] + XW[i][j] + XV[i][j]
                        + YZ[i][j] + YW[i][j] + YV[i][j]
                        + ZW[i][j] + ZV[i][j] + WV[i][j]
                        + 0.5 * XX[i][j] + 0.5 * YY[i][j]
                        + 0.5 * ZZ[i][j] + 0.5 * WW[i][j]
                        + 0.5 * VV[i][j]);
            }
        }

        return disonancia;
    }

    public float disDef(float fx, float fy, float fz, float fw, float fv, float fu) {
        float disonancia = 0;
        int n1 = timb.size();
        int n2 = timb.size();
        float[][] L = new float[n1][n2];
        float[][] XX = new float[n1][n2];
        float[][] YY = new float[n1][n2];
        float[][] ZZ = new float[n1][n2];
        float[][] WW = new float[n1][n2];
        float[][] VV = new float[n1][n2];
        float[][] UU = new float[n1][n2];

        float[][] XY = new float[n1][n2];
        float[][] XZ = new float[n1][n2];
        float[][] XW = new float[n1][n2];
        float[][] XV = new float[n1][n2];
        float[][] XU = new float[n1][n2];

        float[][] YZ = new float[n1][n2];
        float[][] YW = new float[n1][n2];
        float[][] YV = new float[n1][n2];
        float[][] YU = new float[n1][n2];

        float[][] ZW = new float[n1][n2];
        float[][] ZV = new float[n1][n2];
        float[][] ZU = new float[n1][n2];

        float[][] WV = new float[n1][n2];
        float[][] WU = new float[n1][n2];

        float[][] VU = new float[n1][n2];


        // Matriz de coeficientes de armonicos
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                L[i][j] = Math.min(timb.get(i), timb.get(j));
            }
        }
        // matrices XYZ
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                XX[i][j] = g(i * fx, j * fx);
                YY[i][j] = g(i * fy, j * fy);
                ZZ[i][j] = g(i * fz, j * fz);
                WW[i][j] = g(i * fw, j * fw);
                VV[i][j] = g(i * fv, j * fv);
                UU[i][j] = g(i * fu, j * fu);

                XY[i][j] = g(i * fx, j * fy);
                XZ[i][j] = g(i * fx, j * fz);
                XW[i][j] = g(i * fx, j * fw);
                XV[i][j] = g(i * fx, j * fv);
                XU[i][j] = g(i * fx, j * fu);

                YZ[i][j] = g(i * fy, j * fz);
                YW[i][j] = g(i * fy, j * fw);
                YV[i][j] = g(i * fy, j * fv);
                YU[i][j] = g(i * fy, j * fu);

                ZW[i][j] = g(i * fz, j * fw);
                ZV[i][j] = g(i * fz, j * fv);
                ZU[i][j] = g(i * fz, j * fu);

                WV[i][j] = g(i * fw, j * fv);
                WU[i][j] = g(i * fw, j * fu);

                VU[i][j] = g(i * fv, j * fu);

//                System.out.println("M: " + M[i][j]);
//                System.out.println("X: " + X[i][j]);
//                System.out.println("Y: " + Y[i][j]);
            }
        }
        // Suma de disonancia
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                disonancia += L[i][j] * (XY[i][j] + XZ[i][j] + XW[i][j] + XV[i][j] + XU[i][j]
                        + YZ[i][j] + YW[i][j] + YV[i][j] + YU[i][j]
                        + ZW[i][j] + ZV[i][j]
                        + WV[i][j] + WU[i][j]
                        + WV[i][j] + VU[i][j]
                        + 0.5 * XX[i][j] + 0.5 * YY[i][j]
                        + 0.5 * ZZ[i][j] + 0.5 * WW[i][j]
                        + 0.5 * VV[i][j] + 0.5 * UU[i][j]);
            }
        }

        return disonancia;
    }

    public float dis(Timbre t1, Timbre t2) {
        float disonancia = 0;
        int n1 = t1.size();
        int n2 = t2.size();
        float[][] L = new float[n1][n2];
        float[][] M = new float[n1][n2];
        float[][] X = new float[n1][n2];
        float[][] Y = new float[n1][n2];

        // Matriz de coeficientes de armonicos
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                L[i][j] = Math.min(t1.get(i), t2.get(j));
//                System.out.println("L: "+L[i][j]);
            }
        }
        // matrices XYZ
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                M[i][j] = g(i * t1.getFund(), j * t2.getFund());
                X[i][j] = g(i * t1.getFund(), j * t1.getFund());
                Y[i][j] = g(i * t2.getFund(), j * t2.getFund());
//                System.out.println("M: " + M[i][j]);
//                System.out.println("X: " + X[i][j]);
//                System.out.println("Y: " + Y[i][j]);
            }
        }
        // Suma de disonancia
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                disonancia += L[i][j] * (M[i][j] + 0.5 * X[i][j] + 0.5 * Y[i][j]);
            }
        }

        return disonancia;
    }

    private float g(float f1, float f2) {
        double reg;
        double p = s(f1, f2);
        reg = Math.exp(-b1 * p) - Math.exp(-b2 * p);
//        System.out.println("g: " + reg);
        return (float) reg;
    }

    private float s(double f1, double f2) {
        // Suponemos fy>fx
        double fmax = Math.max(f1, f2);
        double fmin = Math.min(f1, f2);
        float s = (float) (d * (fmax - fmin) / (k * fmin + m));
//        System.out.println("s: " + s);
        return s;
    }

    public void paint() {

        Timbre t1 = new Timbre(440.0, 1.0, 0.5, 0.4, 0.3, 0.2, 0.1);
        Timbre t2 = new Timbre(440.0, 1.0, 0.6, 1.0, 1.0, 1.0, 1.0);
//        t2=t1;
        noLoop();
        float pas = 0;
        int col = 0;
        smooth();
//        noStroke();
        //        colorMode(HSB);
//        for (int j = 440; j < 880; j++) {
        for (int i = 440; i < 880; i++) {
//                t1.setFund(j);
            t2.setFund(i);
            line(i - 400, pas, i + 1 - 400, 1000 * dis(t1, t2));
            pas = 1000 * dis(t1, t2);
//                col = (int) map(pl.dis(t1, t2), 0, 1, 0, 255);
//                fill(col);
//                rect((i - 440) * 1, (j - 440) * 1, 1, 1);

//                println(col);
            println(pas);
        }

    }
}
