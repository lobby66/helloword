
public class IDCard {
   // wi =2(n-1)(mod 11)
    final int[] wi = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2}; //

   // verify digit
    final int[] vi = {1,0,'X',9,8,7,6,5,4,3,2}; //

    private int[] ai = new int[18];

     public IDCard() {
      }
   //verify


   //get verify
    public  String getVerify(String eightcardid) { //eightcardid
    int remaining = 0;

    if (eightcardid.length() == 17) {
     int sum = 0;
     for (int i = 0; i < 17; i++) {
     String k = eightcardid.substring(i, i + 1);
     ai[i] = Integer.parseInt(k);
        }
 
     for (int i = 0; i < 17; i++) {
      sum = sum + wi[i]*ai[i];
        }
      remaining = sum % 11;
      return remaining == 2 ? "X" : String.valueOf(vi[remaining]);
 
  }
    else{
     return "出错了!";
    }
   
    }

    

   //用随机数  写出前17位

    public String getSheng(){
     // 第一二位 前两位 01- 64
        // int number = (int)(Math.random()*(tonum-fromnum))+fromnum;
       int number ;
    String str=null;
       number = (int)(Math.random()*(64-1)+1);
    if(number<10)
    {
     //转换成字符串
     
     str = Integer.toString(number);
     str = "0"+number;
     //System.out.println(str);
     return str;
    }
    else{
     
     //System.out.println(Integer.toString(number));
     return Integer.toString(number);
    }
    }
   
   
    public String getShi(){ 
     //第三四位 两位 01-02
       int number ;
       number = (int)(Math.random()*1+1);
    String str=null;
    if(number<10)
    {
     //转换成字符串
     
     str = Integer.toString(number);
     str = "0"+number;
     //System.out.println(str);
     return str;
    }
    else{
     
     //System.out.println(Integer.toString(number));
     return Integer.toString(number);
    }
          }  
  
   
     public String getXian(){
     //第五六位  两位  01-05
    int number ;
       number = (int)(Math.random()*4+1);
    String str=null;
    if(number<10)
    {
     //转换成字符串
     
     str = Integer.toString(number);
     str = "0"+number;
     //System.out.println(str);
     return str;
    }
    else{
     
     //System.out.println(Integer.toString(number));
     return Integer.toString(number);
    }    
        }
   
    public String getYear(){
     //第 7--10位   年份：1970-1990
  int number;
     number = (int)(Math.random()*(1990-1970)+1970);
  String str=null;
  if(number<10)
  {
   //转换成字符串
   
   str = Integer.toString(number);
   str = "0"+number;
   //System.out.println(str);
   return str;
  }
  else{
   
   //System.out.println(Integer.toString(number));
   return Integer.toString(number);
  }
    } 
  
    public String getMonth(){
     //第11 12 位      月份：01-12
  
  int number ;
     number = (int)(Math.random()*(12-1)+1);
  String str=null;
  if(number<10)
  {
   //转换成字符串
   
   str = Integer.toString(number);
   str = "0"+number;
   //System.out.println(str);
   return str;
  }
  else{
   
   //System.out.println(Integer.toString(number));
   return Integer.toString(number);
  }
    } 
  
 public String getDay(){
     //第13   14 位    日期：01-28
  
  int number ;
     number = (int)(Math.random()*(28-1)+1);
  String str=null;
  if(number<10)
  {
   //转换成字符串
   
   str = Integer.toString(number);
   str = "0"+number;
   //System.out.println(str);
   return str;
  }
  else{
   
   //System.out.println(Integer.toString(number));
   return Integer.toString(number);
  }
  
  
    }
   
    public String getSequence(){
     //第15  16 位        两位：01-99
  int number ;
     number = (int)(Math.random()*(99-1)+1);
  String str=null;
  if(number<10)
  {
   //转换成字符串
   
   str = Integer.toString(number);
   str = "0"+number;
   //System.out.println(str);
   return str;
  }
  else{
   
   //System.out.println(Integer.toString(number));
   return Integer.toString(number);
  }
    }
   
    public String getSex(){
         // 第17 位  一位性别：0-9
     int number ;
     number = (int)(Math.random()*(9-0)+0);
  return Integer.toString(number);  
    }

    public  String get17ID(){
     String str=null;
     str = this.getSheng()+this.getShi()+this.getXian()+ this.getYear()+ this.getMonth()+ this.getDay() + this.getSequence() + this.getSex()  ;
     return str;
    }
   
    public String getIDCard(){
     String str=null;
       str = this.get17ID() ;
     str = str + this.getVerify(str); 
     //String test = this.get17ID();
     //System.out.println("身份证号前17位："+test);
     //System.out.println("身份证号："+test+this.getVerify(test));
     return str;
    }

 

public static void main(String[] args){
 
 IDCard card=new IDCard();
  
 //String idcard="41011119820316258";//  41112119821028551  41102219841112602  410111452105122564
//String idcard = card.get17ID();
 //System.out.println("41124919810612243"+card.getVerify(idcard));
//System.out.println("身份证的最后一位为："+card.getVerify(idcard));
//System.out.println("身份证的最后一位为："+card.getVerify("你的身份证号前17位"));
 //System.out.println("身份证号前17位为："+card.get17ID());
 System.out.println("身份证号为："+card.getIDCard());

}

}

 

/*
 *下面是 身份证号码的规则:

身份证号码共18位.

前6位是地址码. 省

7--14位是出生年月日

后面4位是生成的代码,

第17位用来做性别判定:偶数 -- 女 ; 奇数--- 男 .

前17位的构成： 2(省)+2(市)+2(县)+4(年)+2(月)+2(日)+2(序号)+1(性别)

第十八位数字的计算方法为：
1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
2.将这17位数字和系数相乘的结果相加。
3.用加出来和除以11，看余数是多少？
4余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。
5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。


*/