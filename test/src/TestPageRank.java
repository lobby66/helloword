
public class TestPageRank {
  public static void main(String[] args) {
	float PRA=0f;
	float PRB=0f;
	float PRC=0f;
	float PRD=0f;
	float PRE=0f;
	float a=0.85f;
	PRA=a+1/2*PRB*a+1/2*PRC*a + 1/2*PRE*a;
	PRB=a+1/3*PRA*a;
	PRC=a+1/3*PRA*a+PRD*a+1/2*PRE*a;
	PRD=a+1/3*PRA*a;
	PRE=a+1/2*PRB*a + 1/2*PRC*a;
	
}
}
 