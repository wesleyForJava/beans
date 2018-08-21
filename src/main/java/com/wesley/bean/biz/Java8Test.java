package com.wesley.bean.biz;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;  
  
/** 
 * Created on 18/7/31 by bingyin.gby. 
 */  
public class Java8Test {  
  
    public static void main(String[] args) {  
  
        //将1个判断输入值字符串否为空，非空则输出，否则赋予默认值  
      String s="1";  
        String brandId= Optional.ofNullable(s).orElse("123456");  
         System.out.println(brandId);
         
         
          @SuppressWarnings("unused")
		List<String> names= Arrays.asList("bingyingao","tieyi","aaa");   
          
          //遍历int数组  
          int[] luckNums={0,8,5,6,7};  
        Arrays.stream(luckNums).forEach(System.out::println);
          
          /*    //遍历字符串数组  
          String[] charsArrays="a,c,d,b".split(",");  
          Arrays.sort(charsArrays);  
          Arrays.asList(charsArrays).stream().forEach(a->System.out.println(a));  */
          /*   names.forEach(System.out::println);*/
  
        //遍历集合得到一个新的集合  
/*       List<String> collect = names.stream().filter(b->StringUtils.contains(b, "a")).map(a->"ok"+a).collect(Collectors.toList());
        collect.forEach(System.out::println);  */
  
       //遍历原集合并且对原集合中对象进行操作???  
/*        names.stream().map(a->a+"sufix");  
        names.forEach(System.out::println);*/
      /*  names.forEach(System.out::println);  
        new Java8Test().resetUserName();  */
  
        /*  
        //定义function函数并且调用:list转set  
        Function<List<String>,Set<String>> listToMap= list->list.stream().collect(Collectors.toSet());  
        Set<String> names1=listToMap.apply(Arrays.asList("tieyi","tieyi","bingyingao"));  
        System.out.println("listToSet:");  
        names1.forEach(System.out::println);  
  
        //所有用户的年龄进行求和,reduce  
        new Java8Test().sumUserAge();  
        //所有用户的年龄最大、最小、平均，求sum  
        new Java8Test().intSummaryStatistics();  
        //根据年龄排序  
        new Java8Test().sortAgeAsc();  
  
     
        //flatmap的用法  
*/    }  
  
  
    /** 
     * 给用户名重设值 
     */  
    @SuppressWarnings("unused")
	private void resetUserName(){  
        List<Java8Test.UserInfo> userInfoList= Arrays.asList(new Java8Test.UserInfo("tieyi"),new Java8Test.UserInfo("bingyingao"));  
        userInfoList.forEach(u->u.setName(u.getName()+"suFix"));  
        userInfoList.forEach(a->System.out.println(a.getName()));  
    }  
  
    /** 
     * 累加用户年龄 
     */  
    @SuppressWarnings("unused")
	private void sumUserAge(){  
        List<Java8Test.UserInfo> userInfoList= Arrays.asList(new Java8Test.UserInfo("tieyi",30),new Java8Test.UserInfo("bingyingao",31));  
        Integer sumAge= userInfoList.stream().map(a->a.getAge()).reduce((a,b)->a=a+b).get();  
        System.out.println(sumAge);  
  
  
    }  
  
  
    /** 
     * 最佳统计方法 
     */  
    @SuppressWarnings("unused")
	private void intSummaryStatistics(){  
        List<Java8Test.UserInfo> userInfoList= Arrays.asList(new Java8Test.UserInfo("tieyi",30),new Java8Test.UserInfo("bingyingao",31));  
        IntSummaryStatistics intSummaryStatistics= userInfoList.stream().mapToInt(a->a.getAge()).summaryStatistics();  
        System.out.println(intSummaryStatistics.getAverage());  
        System.out.println(intSummaryStatistics.getCount());  
        System.out.println(intSummaryStatistics.getMax());  
        System.out.println(intSummaryStatistics.getMin());  
        System.out.println(intSummaryStatistics.getSum());  
  
  
  
  
    }  
  
  
    /** 
     * 根据用户年龄由低到高排序 
     */  
    @SuppressWarnings("unused")
	private void sortAgeAsc(){  
        List<Java8Test.UserInfo> userInfoList= Arrays.asList(new Java8Test.UserInfo("tieyi",30),new Java8Test.UserInfo("bingyingao",31),new Java8Test.UserInfo("abing",29));  
        //由低到高正序  
        userInfoList.sort(Comparator.comparingInt(Java8Test.UserInfo::getAge));  
        //由高到低正序  
        userInfoList.sort(Comparator.comparingInt(Java8Test.UserInfo::getAge).reversed());  
        userInfoList.forEach(a->System.out.println(a.getAge()));  
  
    }  
  
  
  
  
    public   class UserInfo{  
  
  
        String name ="";  
        int age;  
        UserInfo(String name ){  
            this.name=name;  
        }  
        UserInfo(int age){  
            this.age=age;  
        }  
  
        UserInfo(String name,int age){  
            this.name=name;  
            this.age=age;  
        }  
        public String getName() {  
            return name;  
        }  
  
        public void setName(String name) {  
            this.name = name;  
        }  
  
        public int getAge() {  
            return age;  
        }  
  
        public void setAge(int age) {  
            this.age = age;  
        }  
    }  
}  