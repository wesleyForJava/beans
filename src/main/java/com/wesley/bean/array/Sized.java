package com.wesley.bean.array;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

public interface Sized {
	int size();

	default boolean isEmpty() {
		return size() == 0;
	}
	
//	default void setRelativeSize(int wFactor, int hFactor) {
//		setAbsoluteSize(getWidth() / wFactor, getHeight() / hFactor);
//	}
	
	public class ArrayList<E> extends AbstractList<E>
	implements List<E>, RandomAccess, Cloneable,
	Serializable, Iterable<E>, Collection<E> {
		private static final long serialVersionUID = 1L;

		@Override
		public E get(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return 0;
		}
	}
	
//	public interface Rotatable {
//		void setRotationAngle(int angleInDegrees);
//
//		int getRotationAngle();
//
//		default void rotateBy(int angleInDegrees) {
//			setRotationAngle((getRotationAngle() + angle) % 360);
//		}
//	}
	
	
	public interface Moveable {
		int getX();

		int getY();

		void setX(int x);

		void setY(int y);

		default void moveHorizontally(int distance) {
			setX(getX() + distance);
		}

		default void moveVertically(int distance) {
			setY(getY() + distance);
		}
	}
	
	
	public interface Resizable {
		int getWidth();

		int getHeight();

		void setWidth(int width);

		void setHeight(int height);

		void setAbsoluteSize(int width, int height);

		default void setRelativeSize(int wFactor, int hFactor) {
			setAbsoluteSize(getWidth() / wFactor, getHeight() / hFactor);
		}
	}
	
	public class Monster implements Moveable, Resizable {

		@Override
		public int getWidth() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getHeight() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setWidth(int width) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setHeight(int height) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setAbsoluteSize(int width, int height) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getX() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getY() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setX(int x) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setY(int y) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		}
//	(1) 类中的方法优先级最高。类或父类中声明的方法的优先级高于任何声明为默认方法的优
//	先级。
//	(2) 如果无法依据第一条进行判断，那么子接口的优先级更高：函数签名相同时，优先选择
//	拥有最具体实现的默认方法的接口，即如果 B 继承了 A ，那么 B 就比 A 更加具体。
//	(3) 最后，如果还是无法判断，继承了多个接口的类必须通过显式覆盖和调用期望的方法，
//	猜猜打印输出
//	的是什么？
//	显式地选择使用哪一个默认方法的实现。
//	我们保证，这些就是你需要知道的全部！让我们一起看几个例子。
	
	public interface A {
		default void hello() {
			System.out.println("Hello from A");
		}
	}

	public interface B extends A {
		default void hello() {
			System.out.println("Hello from B");
		}
	}

	public class C implements B, A {
		public static void main(String... args) {
			new C().hello();
		}
	}
	
	public class D implements A{ }
	public class C1 extends D implements B, A {
	public static void main(String... args) {
	new C().hello();
	}
	}
}
