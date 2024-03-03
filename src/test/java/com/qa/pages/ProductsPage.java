package com.qa.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductsPage extends BasePage {
	@AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")
	private MobileElement titleTxt;

	public ProductsPage() {
	}

	public String getTitle() {

		return getText(titleTxt, "product page title is - ");
	}
}
