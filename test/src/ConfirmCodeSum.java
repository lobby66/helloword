public class ConfirmCodeSum
{
   public static String gen(String conf)
   {
     int tmp = Integer.parseInt(conf);
     int i1 = (tmp & 0xff) << 8;
     i1 |= (tmp & 0xff00) >> 8;
     i1 ^= 0x30cd;
     String ret = Integer.toString(i1);
     return ret;
   }
  
   public static void main(String args[])
   {
     String conf = "99334827";   // 此处可以随意修改，但必须为十进制数！
System.out.println("Confirm Code:"+conf+"    Gen Serial Code:"+gen(conf));
   }
}

