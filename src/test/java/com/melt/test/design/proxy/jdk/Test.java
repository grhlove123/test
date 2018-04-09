package com.melt.test.design.proxy.jdk;

public class Test {

	public static void main(String[] args) {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		UserService userservice = new UserServiceImpl() ;
		ProxyFactory factory = new ProxyFactory(userservice) ;
		UserService proxy = (UserService)factory.newInstanceProxy() ;
		
		proxy.save("melt") ;
	}

}
