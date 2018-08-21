package com.wesley.bean;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

public class ProxyBuilder {
	private Class<?>[] interfaces;
    private InvocationHandler handler;
    private ClassLoader classLoader = ProxyBuilder.class.getClassLoader();
    public static ProxyBuilder newProxyBuilder() {
        return new ProxyBuilder();
    }

    public ProxyBuilder setInterFaces(Class<?> ... interFaces) {
        this.interfaces = interFaces;
        return this;
    }

    public ProxyBuilder setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
        return this;
    }

    public ProxyBuilder setInvocationHandler(InvocationHandler handler) {
        this.handler = handler;
        return this;
    }

    public Object build() {
        return Proxy.newProxyInstance(classLoader, interfaces, handler);
    }

    /*public void buildClassFile(String className, String dir) {
        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(className, interfaces);

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(dir).append("/").append(className).append(".class");
        String classFileName = strBuilder.toString();

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(classFileName);
            out.write(proxyClassFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/
}
