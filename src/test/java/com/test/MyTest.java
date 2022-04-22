package com.test;


import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class MyTest {
    //            1 1 3 2
//            1 1 3 2
//            0.33 0.33 1 0.5
//            0.5 0.5 2 1
//
//            0.85 0.77 0.85 0.85
//            0.85 0.77 0.62 0.62
//            0.85 0.77 0.85 0.62
//            0.85 0.77 0.85 0.62
    public static void m1() {
        //        double[][] A = new double[][]{
//                {1, 1, 3, 2},
//                {1, 1, 3, 2},
//                {1.0 / 3, 1.0 / 3, 1, 1.0 / 2},
//                {1.0 / 2, 1.0 / 2, 2, 1}
//        };
//        double[] X1 = new double[]{.85, .77, .85, .85};
//        double[] X2 = new double[]{.85, .77, .62, .62};
//        double[] X3 = new double[]{.85, .77, .85, .62};
//        double[] X4 = new double[]{.85, .77, .85, .62};
        double[][] param = new double[][]{
                {0.22, 0.1, 0.55, 0.15},
                {0.22, 0.1, 0.62, 0.2},
                {0.22, 0.44, 0.85, 0.15},
                {0.31, 0.62, 0.9, 0.15}
        };
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入比较矩阵：");
        double A[][] = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                A[i][j] = sc.nextDouble();
            }
        }
        HashMap<Double, double[]> hashMap = getEigenvalueAndWeightVector(A);
        Set<Double> keySet = hashMap.keySet();
        for (Double key : keySet) {
            System.out.println("权向量：");
            double[] WeightVector = hashMap.get(key);
            System.out.print("[");
            for (double vector : WeightVector) {
                System.out.print(vector + "\t");
            }
            System.out.println("]");
            System.out.println();
        }
        System.out.println("请输入指标评分矩阵：");
        double[] X1 = new double[4];
        double[] X2 = new double[4];
        double[] X3 = new double[4];
        double[] X4 = new double[4];
        for (int i = 0; i < 4; i++) {
            X1[i] = sc.nextDouble();
        }
        for (int i = 0; i < 4; i++) {
            X2[i] = sc.nextDouble();
        }
        for (int i = 0; i < 4; i++) {
            X3[i] = sc.nextDouble();
        }
        for (int i = 0; i < 4; i++) {
            X4[i] = sc.nextDouble();
        }
        System.out.println("漏洞可利用性：");
        System.out.println("PhiFuzzy(H1): " + getPhi(A, X1, param));
        System.out.println("PhiFuzzy(H2): " + getPhi(A, X2, param));
        System.out.println("PhiFuzzy(H3): " + getPhi(A, X3, param));
        System.out.println("PhiFuzzy(H4): " + getPhi(A, X4, param));
    }

    public static void m2() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入准则层比较矩阵：");
        double A[][] = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                A[i][j] = sc.nextDouble();
            }
        }
        HashMap<Double, double[]> hashMap = getEigenvalueAndWeightVector(A);
        Set<Double> keySet = hashMap.keySet();
        double[] WeightVector=null;
        for (Double key : keySet) {
            System.out.println("权向量：");
            WeightVector = hashMap.get(key);
            System.out.print("[");
            for (double vector : WeightVector) {
                System.out.print(vector + "\t");
            }
            System.out.println("]");
            System.out.println();
        }
        double S[][][] = new double[3][5][5];
        double[][] P = new double[3][5];
        for (int i = 0; i < 3; i++) {
            System.out.println("输入第" + (i + 1) + "个决策层比较矩阵:");
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    S[i][j][k] = sc.nextDouble();
                }
            }
            HashMap<Double, double[]> hashMap1 = getEigenvalueAndWeightVector(S[i]);
            Set<Double> keySet1 = hashMap1.keySet();
            for (Double key : keySet1) {
                P[i] = hashMap1.get(key);
            }System.out.println("权向量：");
            System.out.print("[");
            for (double v:P[i]){
                System.out.print(v+"\t");
            }
            System.out.println("]");
        }
//        for (int i = 0; i < 3; i++) {
////            HashMap<Double, double[]> hashMap1 = getEigenvalueAndWeightVector(S[i]);
////            Set<Double> keySet1 = hashMap1.keySet();
////            for (Double key : keySet1) {
////                P[i] = hashMap1.get(key);
////            }
//        }
        double[] res = new double[5];
        for (int i = 0; i < 5; i++) {
            double sum = 0;
            for (int j = 0; j < 3; j++) {
                sum += P[j][i] * WeightVector[j];
            }
            res[i] = sum;
        }
        System.out.println("总信誉度得分矩阵：");
        System.out.print("[");
        for (double v : res){
            System.out.print(v+" ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        m1();
        m2();
    }

    //输入A[][] X[] param[a,b,c,d]*4 三个矩阵 输出phi
    //规定量化参数取值param[][]=[AV[a,b,c,d] PR[a,b,c,d] AC[a,b,c,d] UI[a,b,c,d]]
    //规定X[AV AC PR UI]
//    public static double getPhi(double[][] A, double[] X, double[][] param) {

    public static double getPhi(double[][] A, double[] X, double[][] param) {
        HashMap<Double, double[]> hashmap = getEigenvalueAndWeightVector(A);
        //权向量
        double[] weightVector = null;
        Set<Double> keySet = hashmap.keySet();
        for (Double key : keySet) {
            weightVector = hashmap.get(key);
        }
        //M 4*3
        double[][] M = new double[][]{
                AVPRFunction(param[0], X[0]),//AV
                ACUIFunction(param[2], X[1]),//AC
                AVPRFunction(param[1], X[2]),//PR
                ACUIFunction(param[3], X[3])//UI
        };
        double[] B = new double[3];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            double sum = 0;
            for (int j = 0; j < 4; j++) {
                sum += M[j][i] * weightVector[j];
            }
            B[index++] = sum;
        }

        double[] L = new double[]{1, 2, 3};
        int BMaxIndex = -1;
        double BMax = Integer.MIN_VALUE;
        for (int i = 0; i < B.length; i++) {
            if (B[i] > BMax) {
                BMax = B[i];
                BMaxIndex = (int) L[i];
            }
        }
        double tem = 0;
        for (int i = 0; i < 3; i++) {
            tem += B[i] * L[i];
        }
        return (BMax * BMaxIndex) / tem;
    }

    //param(a,b,c,d) res[low mid high]
    public static double[] AVPRFunction(double[] param, double x) {
        double low = Math.exp((0 - x * x) / (2 * param[0] * param[0]));
        double mid = Math.exp((0 - (x - param[1]) * (x - param[1])) / (2 * param[2] * param[2]));
        double high = Math.exp((0 - (x - 1) * (x - 1)) / (2 * param[3] * param[3]));
        return new double[]{low, mid, high};
    }

    public static double[] ACUIFunction(double[] param, double x) {
        double low = Math.exp((0 - x * x) / (2 * param[0] * param[0]));
        double mid = x < param[0] ? 0 :
                x >= param[0] && x < param[1] ? (x - param[1]) / (param[1] - param[0]) :
                        x >= param[1] && x < param[2] ? (param[2] - x) / (param[2] - param[1]) :
                                0;
        double high = Math.exp((0 - (x - 1) * (x - 1)) / 2 * param[3] * param[3]);
        return new double[]{low, mid, high};
    }

    //<Eigenvalue(lamda), WeightVector>
    public static HashMap<Double, double[]> getEigenvalueAndWeightVector(double[][] A) {
        int n = A.length;
        double[] AColSum = new double[n];
        for (int i = 0; i < n; i++) {
            AColSum[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                AColSum[i] += A[j][i];
            }
        }
        double[][] ANormalization = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ANormalization[i][j] = A[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ANormalization[i][j] /= AColSum[j];
            }
        }
        double[] ANormalizationRowSum = new double[n];
        for (int i = 0; i < n; i++) {
            ANormalizationRowSum[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ANormalizationRowSum[i] += ANormalization[i][j];
            }
        }
        //权向量w
        double[] ANormalizationRowSum_Normalization = new double[n];
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += ANormalizationRowSum[i];
        }
        for (int i = 0; i < n; i++) {
            ANormalizationRowSum_Normalization[i] = ANormalizationRowSum[i] / sum;
        }
        double[] Aw = new double[n];
        for (int i = 0; i < n; i++) {
            Aw[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Aw[i] += ANormalizationRowSum_Normalization[j] * A[i][j];
            }
        }
        //最大特征值lamda
        double lamda;
        double tem = 0;
        for (int i = 0; i < n; i++) {
            tem += Aw[i] / ANormalizationRowSum_Normalization[i];
        }
        lamda = tem / n;
        double CI = (lamda - n) / (n - 1);
        double RI = .58;
        double CR = CI / RI;
        HashMap<Double, double[]> res = new HashMap<>();
        res.put(lamda, ANormalizationRowSum_Normalization);
        return res;
    }
}