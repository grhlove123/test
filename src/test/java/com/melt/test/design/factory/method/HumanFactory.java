package com.melt.test.design.factory.method;

import com.melt.test.design.factory.simple.Human;

/**
 * 一个实现对应一个工厂，如果增加了人类种类，只需要增加一个类实现和工厂实现
 *
 * @author rhguo
 *
 * 2018-01-12 上午10:40:01
 */

public interface HumanFactory {

	Human newInstance() ;
}
