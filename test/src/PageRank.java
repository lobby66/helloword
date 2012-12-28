public class PageRank {
    public static void main(String[] args) {
        /*定义一个最大误差*/
        double MAX = 0.000000000000001;
        
        /*定义一个权值*/
        double RIGHT = 0.5;
        
        /*
        页面连接图，形式如：
        
            A     B    C
        A    0    1    1
        B    0    0    1
        C    1    0    0
        
        矩阵的意义为，从列看，如果列的某一个元素到行的某一元素有链出，则该元素值为1，
        否则为0，自己是不会有连向自己的，所以对角线上的元素为0.
        上面的矩阵即是例子所给的页面的对应的Graph.
        */
        int[][] links = 
        {
        	{0,1,1,0,1},
        	{1,0,0,0,0},
        	{1,0,0,1,1},
        	{1,0,0,0,0},
        	{0,1,1,0,0}
        };
        
        /*测试用数据
        {
        {0, 1, 1},
        {0, 0, 1},
        {1, 0, 0}
        {0, 1, 1, 1},
        {0, 0, 1, 0},
        {1, 0, 0, 1},
        {1, 1, 1, 1}
        }
        
        */
        int len = links.length;
        
        //各点的总链出数量
        int[] linkOut = new int[len];
        for(int k = 0; k<len; k ++) {
            for(int l = 0; l < len; l++) {
                linkOut[k] += links[k][l];
            }
        }
        
        /*用来存储PR值的结果*/
        double[] pr = new double[len];
        
        //临时变量
        double[] init = new double[len];
        
        /*对数组进行初始化*/
        for(int i=0; i < len; i++ ) {
            init[i] = 0.0;
        }
        
        //先做一次PageRank运算。
        pr = doPageRank(init, linkOut, links, RIGHT);
        
        //把得到的值反复运算，只到得到要得到的精度为止。
        //int m = 1;
        while(!checkPrecision(pr, init, MAX)) {
            //System.out.println(m++);
            System.arraycopy(pr, 0, init, 0, len);
            pr = doPageRank(pr, linkOut, links, RIGHT);
            /*
                for(int i = 0; i < len; i++) {
                System.out.println("PR(" + i + ") = " + pr[i]);                
            }
            System.out.println();
            */
        }
        for(int i = 0; i < len; i++) {
            System.out.println("PR(" + (char)(i + 65) + ") = " + pr[i]);
        }
    }
    
    /**
    * 这个函数用来验证两个数组相对应的误差是不是都一定的精度范围内。两个数组的长度必须一样。
    *@param checked 要检察的数组
    *@param ref 参照的数组
    *@param precision 要求的精度
    *@return result 检察的结果，如果对应的数组元素的误差都在精度范围内，则返回true,否则返回fals。
    */
    public static boolean checkPrecision(double[] checked, double[] ref, double precision) {
        /*如果两个数组的长度不一样，则没比较，所以返回false,并且退出。*/
        if(checked.length != ref.length) {
            System.out.println("lenth is not the same!");
            return false;
        }
        
        /*先把结果设为true*/
        boolean result = true;
        int len = ref.length;
        for(int i=0; i<len; i++) {
            /*如果有一对的误差的绝对值超过要求的精度，则把结果设为false并且返回。*/
            if(Math.abs(checked[i] - ref[i]) > precision) {
                result = false;
                break;
            }
        }
        return result;
    }
    
    /**
    *这个函数是计算page rank的值的，其中links必须为n x n的方阵，且要有初始值数组init的长度也为n.
    *@param init 假设的初始值或是上一次计算不满足要求的结果。
    *@param linkOut 页面的连出数组。
    *@param links 页面连接的Graph
    *@param right 所设置的权值。
    *@param pr 经计算后的值数组。
    */
    public static double[] doPageRank(double[] init, int[] linkOut, int[][] links, double right) {
        /*如果init的长度不和links的长度相等，则返回空值。
        */
        if(init.length != linkOut.length) {
            return null;
        }
        
        int len = init.length;
        
        //要返回的结果数组
        double[] pr = new double[len];
        
        //临时变量
        double temp = 0;
        
        //循环变量
        int i = 0, j = 0;
        for(i = 0; i<len; i++) {
            //pr[i] = right;
            j = 0;
            temp = 0;
            for(j = 0; j < len; j++) {
                //只能计算非本页面，和连出数不为0的页面,并且那个页面有连到本页面的值，即是links[j][i] !=0。
                if((i != j) && (linkOut[j] != 0) && (links[j][i] != 0)) {
                    temp += init[j]/linkOut[j];
                }            
            }
            pr[i] = right + (1 - right)* temp;
        }
        return pr;
    }
}