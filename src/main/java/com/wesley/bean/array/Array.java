package com.wesley.bean.array;

public class Array<E> {

	private E [] data;
	private int size;
	@SuppressWarnings("unchecked")
	public Array(int capacity) {
		data=(E [])new Object[capacity];
		size=0;
	}
	
	public Array(){
		this(10);
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
     	
    public int getCapacity() {
    	return data.length;
    }	
  
    
    // 在index索引的位置插入一个新元素e
    public void add(int index,E e) {
    if(size == data.length) //如果数组容量满了就无法插入
        throw new IllegalArgumentException("Add failed. Array is full.");

    if(index < 0 || index > size)//如果插入元素的索引小于0 或者大于数组的长度也不可取
        throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
	
    for (int i = size-1; i >=index; i--) {
		data[i+1]=data[i];
	}
	    data[index]=e; 
	    size++;
    }

    public void addFirst(E e) {
    	add(0, e);
    }
    
    
    // 获取index索引位置的元素
    public E get(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    // 修改index索引位置的元素为e
    public void set(int index, E e){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        data[index] = e;
    }
    
    public void addLast(E e) {
    	add(size, e);
    }
    @Override
	public String toString() {
	       StringBuilder res=new StringBuilder();
	       res.append(String.format("Arrays: size=%d ,capacity=%d\n", size,data.length));
	       res.append("[");
	       for (int i = 0; i < size; i++) {
	    	   res.append(data[i]+" ");
	    	   if(i!=size-1)
	    		   res.append(", ");
		}
	       res.append("]");
		return res.toString();
	}
    
    // 查找数组中是否有元素e
    public boolean contains(E e) {
    	for (int i = 0; i < data.length; i++) {
			if(data[i]==e)
				return true;
		}
    	 return false;
    }
 // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E e) {
    	for (int i = 0; i < data.length; i++) {
    		if(data[i]==e)
    			return i;
    	}
    	return -1;
    }
  // 从数组中删除index位置的元素, 返回删除的元素
    public int remove(int index) {
    	 if(index < 0 || index >= size)
             throw new IllegalArgumentException("Remove failed. Index is illegal.");
    	int ret = Integer.parseInt(data[index].toString());
    	for (int i = index; i <size-1; i++) {
			data[i]=data[i+1];
		}
    	/*or
    	for (int i = index+1; i < size; i++) {
    		data[i-1]=data[i];
		}*/
    	size--;
		return ret;
    }
    // 从数组中删除最后一个元素, 返回删除的元素
    public int removeLast() {
		return remove(size-1);
    }
    
    // 从数组中删除元素e
    public void removeElement(E e) {
    	int index = find(e);
    	if(index!=-1)
    		remove(index);
    }
    
   // 从数组中删除第一个元素, 返回删除的元素
    public int removeFirst() {
		return remove(0);
    }
    
}
