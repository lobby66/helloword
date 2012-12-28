
import java.util.Scanner;

public class PageRank2{

        /**
         * @param args
         */
        double D = 0.85;  //消耗因子(即alpha)
        double ERROR = 0.00001;  //收敛阈值
        int[][] groupI;  //初始矩阵I
        
        double[][] groupS;   //S矩阵
        
        
        double[] handleInput(Scanner s) {
                //generate groupS and attrPR
                int n = s.nextInt();
                groupI = new int[n][n];
                groupS = new double[n][n];
                for(int i=0; i<n; i++) {
                        for(int j=0; j<n; j++) {
                                groupI[i][j] = s.nextInt();
                                //System.out.print(groupS[i][j] + ",");
                        }
                }
                
                int[] tmp = new int[n];
                for(int i=0; i<n; i++) {
                        tmp[i] = 0;
                }
                for(int i=0; i<n; i++) {
                        for(int j=0; j<n; j++) {
                                tmp[i] += groupI[i][j];
                        }        
                }
                
                for(int i=0; i<n; i++) {
                        for(int j=0; j<n; j++) {
                                groupS[i][j] = (double)groupI[j][i]/(double)tmp[j];
                        }        
                }
                
                double initPR[] = new double[n];  //初始特征向量，每个元素设为1
                for(int i=0; i<n; i++) {
                        initPR[i] = 1;
                }
                return initPR;
        }
        
        double normQ(double[] Q) {     //特征向量的模
                double norm = 0;
                for(int i=0; i<Q.length; i++) {
                        norm += Q[i]*Q[i];
                }
                return Math.sqrt(norm); 
        }
        double[] pageRankProcess(double[] origQ) {
                //
                int size = origQ.length;
                double[] difQ = new double[size];
                double[] newQ = new double[size];
                
                int step=0;
                do {
                        step++;
                        for(int i=0; i<size; i++) {
                                newQ[i] = (1-D)/size;
                        }
                         for(int i=0; i<size; i++) {
                                for(int j=0; j<size; j++) {
                                        newQ[i] += D*(groupS[i][j]*origQ[j]);        
                                }
                                difQ[i] = newQ[i] - origQ[i];
                        }
                         origQ = newQ.clone();
                         System.out.print("Step " + step + ":");
                         for(int i=0; i<size; i++) {   //输出每一轮的特征向量值
                                 if(i<size-1) {
                                         System.out.print(newQ[i] + ", ");
                                 } else {
                                         System.out.println(newQ[i]);
                                 }
                         }
                         //System.out.println("----" + normQ(difQ));
                } while(normQ(difQ) > ERROR);
                        
                return newQ;
                        
        }
        public static void main(String[] args) {
                // TODO Auto-generated method stub
                Scanner scanner = new Scanner(System.in);
                PageRank2 pr = new PageRank2();
                double[] initPR = pr.handleInput(scanner);
                
                double[] res = pr.pageRankProcess(initPR);
                /*for(double r : res) {
                        System.out.print(r + ", ");
                }*/
        }

}