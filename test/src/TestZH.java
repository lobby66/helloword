
public class TestZH {
   public static void main(String[] args) {
	   String zm[]={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	   int sz[]={0,1,2,3,4,5,6,7,8,9};
	   String tel[] ={"150","151","130","131","186","188","138","137","136"};
	   String xing[] ={"李","王","张","刘","陈","杨","赵","黄","周","吴","徐","孙","胡","朱","高","林","何","郭","马","罗","梁","宋","郑","谢","韩","唐","冯","于","董","萧","程","曹","袁","邓","许","傅","沈","曾","彭","吕","苏","卢","蒋","蔡","贾","丁","魏","薛","叶","阎","余","潘","杜","戴","夏","钟","汪","田","任","姜","范","方","石","姚","谭","盛","邹","熊","金","陆","郝","孔","白","崔","康","毛","邱","秦","江","史","顾","侯","邵","孟","龙","万","段","章","钱","汤","尹","黎","易","常","武","乔","贺","赖","龚","文"};
	  
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
	   
	   sb.append(" 身份证:" ).append(idcard.getIDCard());
	   String telNum="";
	   double e=Math.random()*9;
	   int index1=(int) e;
	   telNum+=tel[index1];
	   for(int i=0;i<8;i++){
		   double d=Math.random()*9;
		   int index=(int) d;
		   telNum+=sz[index];
	   }
	   sb.append(" 电话").append(telNum);
	   sb.append(" 姓名：");
	   for(int i=0;i<3;i++){
		   double d=Math.random()*99;
		   int index=(int) d;
		   sb.append(xing[index]);
	   }
	   
	System.out.println(sb.toString());
	}
}
}
