
public class TestZH {
   public static void main(String[] args) {
	   String zm[]={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	   int sz[]={0,1,2,3,4,5,6,7,8,9};
	   String tel[] ={"150","151","130","131","186","188","138","137","136"};
	   String xing[] ={"��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","֣","л","��","��","��","��","��","��","��","��","Ԭ","��","��","��","��","��","��","��","��","¬","��","��","��","��","κ","Ѧ","Ҷ","��","��","��","��","��","��","��","��","��","��","��","��","��","ʯ","Ҧ","̷","ʢ","��","��","��","½","��","��","��","��","��","ë","��","��","��","ʷ","��","��","��","��","��","��","��","��","Ǯ","��","��","��","��","��","��","��","��","��","��","��"};
	  
	   StringBuffer sb= null;
	   IDCard idcard =new IDCard();
	for(int j=0;j<600;j++)  {
		sb= new StringBuffer();
	   for(int i=0;i<5;i++){
		   double d=Math.random()*25;
		   int index=(int) d;
		   sb.append(zm[index]);
	   }
	   for(int i=0;i<4;i++){
		   double d=Math.random()*9;
		   int index=(int) d;
		   sb.append(sz[index]);
	   }
	   
	   sb.append(" ���֤:" ).append(idcard.getIDCard());
	   String telNum="";
	   double e=Math.random()*9;
	   int index1=(int) e;
	   telNum+=tel[index1];
	   for(int i=0;i<8;i++){
		   double d=Math.random()*9;
		   int index=(int) d;
		   telNum+=sz[index];
	   }
	   sb.append(" �绰").append(telNum);
	   sb.append(" ������");
	   for(int i=0;i<3;i++){
		   double d=Math.random()*99;
		   int index=(int) d;
		   sb.append(xing[index]);
	   }
	   
	System.out.println(sb.toString());
	}
}
}
