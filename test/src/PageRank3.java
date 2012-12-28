import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PageRank3 {
	private static final double ALPHA = 0.85; // 系数值
	private static final double DISTANCE = 0.0000001; // 收敛值
	static List<List<Double>> sMartix = new ArrayList<List<Double>>();
	static int pages = 0;

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		System.out.println("请输入page的数量：");
		String pageString = s.nextLine();
		pages = Integer.valueOf(pageString);

		System.out.println("一共有" + pages
				+ "个网页，请输入google矩阵，每个值之间用空格分隔，回车输入另一行，输入OK结束输入");
		System.out.println("例如：");
		System.out.println("0 1/2 1/2 0 1/2");
		System.out.println("1/3 0 0 0 0");
		System.out.println("1/3 0 0 1 1/2");
		System.out.println("1/3 0 0 0 0");
		System.out.println("0 1/2 1/2 0 0");
		System.out.println("ok");
		System.out.println("请输入:");
		while (true) {
			String line = s.nextLine();
			if (line.equals("ok"))
				break;
			String[] sourceLine = line.split(" ");
			List<Double> sourceList = new ArrayList<Double>();
			for (int i = 0; i < sourceLine.length; i++) {
				String tmp = sourceLine[i];
				if (tmp.indexOf("/") > 0) {// 含有除号
					String[] tmpArr = tmp.split("/");
					double a = new Double(tmpArr[0]);
					double b = new Double(tmpArr[1]);
					sourceList.add(a / b);
				} else {
					sourceList.add(new Double(tmp));

				}
			}
			sMartix.add(sourceList);

		}
		System.out.println("alpha的值为: " + ALPHA);
		List<Double> q1 = getInitQ(pages);
		System.out.println("初始的向量q为:");
		printVec(q1);
		System.out.println("初始的矩阵G为:");
		printMatrix(getG(ALPHA));
		List<Double> pageRank = calPageRank(q1, ALPHA);
		System.out.println("PageRank为:");
		printVec(pageRank);
		System.out.println();
	}

	/**
	 * 打印输出一个矩阵
	 * 
	 * @param m
	 */
	public static void printMatrix(List<List<Double>> m) {
		for (int i = 0; i < m.size(); i++) {
			for (int j = 0; j < m.get(i).size(); j++) {
				System.out.print(m.get(i).get(j) + ", ");
			}
			System.out.println();
		}
	}

	/**
	 * 打印输出一个向量
	 * 
	 * @param v
	 */
	public static void printVec(List<Double> v) {
		for (int i = 0; i < v.size(); i++) {
			System.out.print(v.get(i) + ", ");
		}
		System.out.println();
	}

	/**
	 * 获得一个初始的随机向量q
	 * 
	 * @param n
	 *            向量q的维数
	 * @return 一个随机的向量q，每一维是0-5之间的随机数
	 */
	public static List<Double> getInitQ(int n) {
		Random random = new Random();
		List<Double> q = new ArrayList<Double>();
		for (int i = 0; i < n; i++) {
			q.add(new Double(5 * random.nextDouble()));
		}
		return q;
	}

	/**
	 * 计算两个向量的距离 ,如果2个向量的距离小于收敛值，则退出迭代
	 * 
	 * @param q1
	 *            第一个向量
	 * @param q2
	 *            第二个向量
	 * @return 它们的距离
	 */
	public static double calDistance(List<Double> q1, List<Double> q2) {
		double sum = 0;

		if (q1.size() != q2.size()) {
			return -1;
		}

		for (int i = 0; i < q1.size(); i++) {
			sum += Math.pow(q1.get(i).doubleValue() - q2.get(i).doubleValue(),
					2);
		}
		return Math.sqrt(sum);
	}

	/**
	 * 计算pagerank ，迭代计算pageRank
	 * 
	 * @param q1
	 *            初始向量
	 * @param a
	 *            alpha的值
	 * @return pagerank的结果
	 */
	public static List<Double> calPageRank(List<Double> q1, double a) {

		List<List<Double>> g = getG(a);
		List<Double> q = null;
		int flag=0;
		while (true) {
			q = vectorMulMatrix(g, q1);
			double dis = calDistance(q, q1);
			System.out.println(dis);
			if (dis <= DISTANCE) {
				System.out.println("q1:");
				printVec(q1);
				System.out.println("q:");
				printVec(q);
				break;
			}
			q1 = q;
			flag++;
		}
		System.out.println(flag + "次数");
		return q;
	}

	/**
	 * 计算获得初始的G矩阵 G=aS+(1-a)U/n
	 * 
	 * @param a
	 *            为alpha的值，0.85
	 * @return 初始矩阵G
	 */
	public static List<List<Double>> getG(double a) {

		int n = getS().size();
		List<List<Double>> aS = numberMulMatrix(getS(), a);
		List<List<Double>> nU = numberMulMatrix(getU(pages), (1 - a) / n);
		List<List<Double>> g = addMatrix(aS, nU);
		return g;
	}

	/**
	 * 计算一个矩阵乘以一个向量
	 * 
	 * @param m
	 *            一个矩阵
	 * @param v
	 *            一个向量
	 * @return 返回一个新的向量
	 */
	public static List<Double> vectorMulMatrix(List<List<Double>> m,
			List<Double> v) {
		if (m == null || v == null || m.size() <= 0
				|| m.get(0).size() != v.size()) {
			return null;
		}

		List<Double> list = new ArrayList<Double>();
		for (int i = 0; i < m.size(); i++) {
			double sum = 0;
			for (int j = 0; j < m.get(i).size(); j++) {
				double temp = m.get(i).get(j).doubleValue()
						* v.get(j).doubleValue();
				sum += temp;
			}
			list.add(sum);
		}

		return list;
	}

	/**
	 * 计算两个矩阵的和
	 * 
	 * @param list1
	 *            第一个矩阵
	 * @param list2
	 *            第二个矩阵
	 * @return 两个矩阵的和
	 */
	public static List<List<Double>> addMatrix(List<List<Double>> list1,
			List<List<Double>> list2) {
		List<List<Double>> list = new ArrayList<List<Double>>();
		if (list1.size() != list2.size() || list1.size() <= 0
				|| list2.size() <= 0) {
			return null;
		}
		for (int i = 0; i < list1.size(); i++) {
			list.add(new ArrayList<Double>());
			for (int j = 0; j < list1.get(i).size(); j++) {
				double temp = list1.get(i).get(j).doubleValue()
						+ list2.get(i).get(j).doubleValue();
				list.get(i).add(new Double(temp));
			}
		}
		return list;
	}

	/**
	 * 计算一个数乘以矩阵
	 * 
	 * @param s
	 *            矩阵s
	 * @param a
	 *            double类型的数
	 * @return 一个新的矩阵
	 */
	public static List<List<Double>> numberMulMatrix(List<List<Double>> s,
			double a) {
		List<List<Double>> list = new ArrayList<List<Double>>();

		for (int i = 0; i < s.size(); i++) {
			list.add(new ArrayList<Double>());
			for (int j = 0; j < s.get(i).size(); j++) {
				double temp = a * s.get(i).get(j).doubleValue();
				list.get(i).add(new Double(temp));
			}
		}
		return list;
	}

	/**
	 * 初始化S矩阵
	 * 
	 * @return S
	 */
	public static List<List<Double>> getS() {
		return sMartix;
	}

	/**
	 * 初始化U矩阵，全1
	 * 
	 * @return U
	 */
	public static List<List<Double>> getU(int pages) {
		List<List<Double>> s = new ArrayList<List<Double>>();
		for (int i = 0; i < pages; i++) {
			List<Double> row = new ArrayList<Double>();
			for (int j = 0; j < pages; j++) {
				row.add(new Double(1));
			}
			s.add(row);
		}

		return s;
	}
}
