package com.melt.test.design.factory.abstr;
/**
 * 创建一组产品
 * 同一种风格类的产品
 * 与工厂方法最大的不同是：
 * 工厂方法：工厂类只能创建一种产品
 * 抽象工厂：工厂类可以创建一族产品(具有同一种风格)
 *
 * @author rhguo
 *
 * 2018-01-12 上午11:27:52
 */
public interface SkinFactory {

	Button createButton() ;
	
	TextField createTextField() ;
}
