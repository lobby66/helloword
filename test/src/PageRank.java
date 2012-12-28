public class PageRank {
    public static void main(String[] args) {
        /*����һ��������*/
        double MAX = 0.000000000000001;
        
        /*����һ��Ȩֵ*/
        double RIGHT = 0.5;
        
        /*
        ҳ������ͼ����ʽ�磺
        
            A     B    C
        A    0    1    1
        B    0    0    1
        C    1    0    0
        
        ���������Ϊ�����п�������е�ĳһ��Ԫ�ص��е�ĳһԪ�������������Ԫ��ֵΪ1��
        ����Ϊ0���Լ��ǲ����������Լ��ģ����ԶԽ����ϵ�Ԫ��Ϊ0.
        ����ľ���������������ҳ��Ķ�Ӧ��Graph.
        */
        int[][] links = 
        {
        	{0,1,1,0,1},
        	{1,0,0,0,0},
        	{1,0,0,1,1},
        	{1,0,0,0,0},
        	{0,1,1,0,0}
        };
        
        /*����������
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
        
        //���������������
        int[] linkOut = new int[len];
        for(int k = 0; k<len; k ++) {
            for(int l = 0; l < len; l++) {
                linkOut[k] += links[k][l];
            }
        }
        
        /*�����洢PRֵ�Ľ��*/
        double[] pr = new double[len];
        
        //��ʱ����
        double[] init = new double[len];
        
        /*��������г�ʼ��*/
        for(int i=0; i < len; i++ ) {
            init[i] = 0.0;
        }
        
        //����һ��PageRank���㡣
        pr = doPageRank(init, linkOut, links, RIGHT);
        
        //�ѵõ���ֵ�������㣬ֻ���õ�Ҫ�õ��ľ���Ϊֹ��
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
    * �������������֤�����������Ӧ������ǲ��Ƕ�һ���ľ��ȷ�Χ�ڡ���������ĳ��ȱ���һ����
    *@param checked Ҫ��������
    *@param ref ���յ�����
    *@param precision Ҫ��ľ���
    *@return result ���Ľ���������Ӧ������Ԫ�ص����ھ��ȷ�Χ�ڣ��򷵻�true,���򷵻�fals��
    */
    public static boolean checkPrecision(double[] checked, double[] ref, double precision) {
        /*�����������ĳ��Ȳ�һ������û�Ƚϣ����Է���false,�����˳���*/
        if(checked.length != ref.length) {
            System.out.println("lenth is not the same!");
            return false;
        }
        
        /*�Ȱѽ����Ϊtrue*/
        boolean result = true;
        int len = ref.length;
        for(int i=0; i<len; i++) {
            /*�����һ�Ե����ľ���ֵ����Ҫ��ľ��ȣ���ѽ����Ϊfalse���ҷ��ء�*/
            if(Math.abs(checked[i] - ref[i]) > precision) {
                result = false;
                break;
            }
        }
        return result;
    }
    
    /**
    *��������Ǽ���page rank��ֵ�ģ�����links����Ϊn x n�ķ�����Ҫ�г�ʼֵ����init�ĳ���ҲΪn.
    *@param init ����ĳ�ʼֵ������һ�μ��㲻����Ҫ��Ľ����
    *@param linkOut ҳ����������顣
    *@param links ҳ�����ӵ�Graph
    *@param right �����õ�Ȩֵ��
    *@param pr ��������ֵ���顣
    */
    public static double[] doPageRank(double[] init, int[] linkOut, int[][] links, double right) {
        /*���init�ĳ��Ȳ���links�ĳ�����ȣ��򷵻ؿ�ֵ��
        */
        if(init.length != linkOut.length) {
            return null;
        }
        
        int len = init.length;
        
        //Ҫ���صĽ������
        double[] pr = new double[len];
        
        //��ʱ����
        double temp = 0;
        
        //ѭ������
        int i = 0, j = 0;
        for(i = 0; i<len; i++) {
            //pr[i] = right;
            j = 0;
            temp = 0;
            for(j = 0; j < len; j++) {
                //ֻ�ܼ���Ǳ�ҳ�棬����������Ϊ0��ҳ��,�����Ǹ�ҳ����������ҳ���ֵ������links[j][i] !=0��
                if((i != j) && (linkOut[j] != 0) && (links[j][i] != 0)) {
                    temp += init[j]/linkOut[j];
                }            
            }
            pr[i] = right + (1 - right)* temp;
        }
        return pr;
    }
}