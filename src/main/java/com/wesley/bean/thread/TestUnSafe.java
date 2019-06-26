package com.wesley.bean.thread;
import sun.misc.Unsafe;
public class TestUnSafe {

    //获取 Unsafe 的实例 1
    static final Unsafe unsafe= Unsafe.getUnsafe(); 
    
    //记录变量state在类TestUnsafe中偏移值2
    static final long stateOffset;

   //变量3
    private volatile long state;
    
    static {
    	 //获取 state 变量在类 TestUnSafe 中的偏移值4
    	try {
    		stateOffset = unsafe.objectFieldOffset(TestUnSafe.class.getDeclaredField("state"));
		} catch (NoSuchFieldException | SecurityException e) {
			 System.out.println(e.getLocalizedMessage());
	            throw new Error(e);
		}
    	
    }
    public static void main(String[] args) {
    	//创建实例5
        TestUnSafe test = new TestUnSafe();
        Boolean success = unsafe.compareAndSwapInt(test,stateOffset,0,1);
        System.out.println(success);
	}
    
    
}
