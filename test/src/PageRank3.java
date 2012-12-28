import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PageRank3 {
	private static final double ALPHA = 0.85; // ϵ��ֵ
	private static final double DISTANCE = 0.0000001; // ����ֵ
	static List<List<Double>> sMartix = new ArrayList<List<Double>>();
	static int pages = 0;

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		System.out.println("������page��������");
		String pageString = s.nextLine();
		pages = Integer.valueOf(pageString);

		System.out.println("һ����" + pages
				+ "����ҳ��������google����ÿ��ֵ֮���ÿո�ָ����س�������һ�У�����OK��������");
		System.out.println("���磺");
		System.out.println("0 1/2 1/2 0 1/2");
		System.out.println("1/3 0 0 0 0");
		System.out.println("1/3 0 0 1 1/2");
		System.out.println("1/3 0 0 0 0");
		System.out.println("0 1/2 1/2 0 0");
		System.out.println("ok");
		System.out.println("������:");
		while (true) {
			String line = s.nextLine();
			if (line.equals("ok"))
				break;
			String[] sourceLine = line.split(" ");
			List<Double> sourceList = new ArrayList<Double>();
			for (int i = 0; i < sourceLine.length; i++) {
				String tmp = sourceLine[i];
				if (tmp.indexOf("/") > 0) {// ���г���
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
		System.out.println("alpha��ֵΪ: " + ALPHA);
		List<Double> q1 = getInitQ(pages);
		System.out.println("��ʼ������qΪ:");
		printVec(q1);
		System.out.println("��ʼ�ľ���GΪ:");
		printMatrix(getG(ALPHA));
		List<Double> pageRank = calPageRank(q1, ALPHA);
		System.out.println("PageRankΪ:");
		printVec(pageRank);
		System.out.println();
	}

	/**
	 * ��ӡ���һ������
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
	 * ��ӡ���һ������
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
	 * ���һ����ʼ���������q
	 * 
	 * @param n
	 *            ����q��ά��
	 * @return һ�����������q��ÿһά��0-5֮��������
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
	 * �������������ľ��� ,���2�������ľ���С������ֵ�����˳�����
	 * 
	 * @param q1
	 *            ��һ������
	 * @param q2
	 *            �ڶ�������
	 * @return ���ǵľ���
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
	 * ����pagerank ����������pageRank
	 * 
	 * @param q1
	 *            ��ʼ����
	 * @param a
	 *            alpha��ֵ
	 * @return pagerank�Ľ��
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
		System.out.println(flag + "����");
		return q;
	}

	/**
	 * �����ó�ʼ��G���� G=aS+(1-a)U/n
	 * 
	 * @param a
	 *            Ϊalpha��ֵ��0.85
	 * @return ��ʼ����G
	 */
	public static List<List<Double>> getG(double a) {

		int n = getS().size();
		List<List<Double>> aS = numberMulMatrix(getS(), a);
		List<List<Double>> nU = numberMulMatrix(getU(pages), (1 - a) / n);
		List<List<Double>> g = addMatrix(aS, nU);
		return g;
	}

	/**
	 * ����һ���������һ������
	 * 
	 * @param m
	 *            һ������
	 * @param v
	 *            һ������
	 * @return ����һ���µ�����
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
	 * ������������ĺ�
	 * 
	 * @param list1
	 *            ��һ������
	 * @param list2
	 *            �ڶ�������
	 * @return ��������ĺ�
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
	 * ����һ�������Ծ���
	 * 
	 * @param s
	 *            ����s
	 * @param a
	 *            double���͵���
	 * @return һ���µľ���
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
	 * ��ʼ��S����
	 * 
	 * @return S
	 */
	public static List<List<Double>> getS() {
		return sMartix;
	}

	/**
	 * ��ʼ��U����ȫ1
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
