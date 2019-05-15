package com.wesley.bean.test;

import java.util.Arrays;

public class SelectionSort {
/**
 * <p>选择排序是一种简单直观的排序算法，无论什么数据进去都是 O(n²) 的时间复杂度。
 *  <p>所以用到它的时候，数据规模越小越好。唯一的好处可能就是不占用额外的内存空间了吧。
 *	1. 算法步骤
 *	首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。	
 *	再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 *	重复第二步，直到所有元素均排序完毕。
 * @param sourceArray
 * @return
 * @throws Exception
 */
    public static int[] sort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // 总共要经过 N-1 轮比较
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;

            // 每轮需要比较的次数 N-i
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    // 记录目前能找到的最小值元素的下标
                    min = j;
                }
            }

            // 将找到的最小值和i位置所在的值进行交换
            if (i != min) {
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }

        }
        return arr;
    }
    public static void main(String[] args) throws Exception {
		int a []= {1,2,8,34,31,43,41,23,46,34,13,14,83,43};
		System.out.println("1"+System.currentTimeMillis());
		int[] sort = sort(a);
		System.out.println("2"+System.currentTimeMillis());
		System.out.println(Arrays.toString(sort));
		String aString="11111111111111111111111111111";
		int hashCode = aString.hashCode();
		int spread = spread(hashCode);
		System.out.println(spread);
	}
    static final int spread(int h) {
    	System.out.println(h);
    	System.out.println(h >>> 16);
    	System.out.println(h ^ (h >>> 16));
    	System.out.println(0x7fffffff);
    	return (h ^ (h >>> 16)) & 0x7fffffff;
    }
    
    

    static String toBinary(int num) {
        String str = "";
        while (num != 0) {
            str = num % 2 + str;
            num = num / 2;
        }
        return str;
    }
    
}