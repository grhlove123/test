package com.melt.test.design.factory.simple;

public class HumanFactory {

	public static Human newInstance(int type){
		Human human = null ;
		switch (type) {
			case 1:
				human = new YellowHuman() ;
				break ;
			case 2:
				human = new WhiteHuman() ;
		}
		return human ;
		
	}
}
