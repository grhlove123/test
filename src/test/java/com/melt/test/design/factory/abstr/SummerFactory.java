package com.melt.test.design.factory.abstr;

public class SummerFactory implements SkinFactory {

	@Override
	public Button createButton() {
		return new SummerButton();
	}

	@Override
	public TextField createTextField() {
		return new SummerTextField();
	}

}
