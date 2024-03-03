package com.qa.pages;

import com.qa.utils.GlobalParams;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/***
 * Ths class is containing all teh functions of login page of mobile application
 * with the xpath of android application.
 */
public class LoginPage extends BasePage {
	GlobalParams utils = new GlobalParams();

	@AndroidFindBy (accessibility = "test-Username") 
	private MobileElement usernameTxtFld;

	@AndroidFindBy (accessibility = "test-Password") 
	private MobileElement passwordTxtFld;
	
	@AndroidFindBy (accessibility = "test-LOGIN") 
	private MobileElement loginBtn;
	
	@AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView") 
	private MobileElement errTxt;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView")
	private MobileElement settingbutton;

	@AndroidFindBy (accessibility="test-LOGOUT")
	private MobileElement logoutBtn;

	@AndroidFindBy (xpath="(//android.view.ViewGroup[@content-desc=\"test-Item\"])[3]/android.view.ViewGroup/android.widget.ImageView")
	private MobileElement swipe_startElement;

	@AndroidFindBy (xpath="(//android.view.ViewGroup[@content-desc=\"test-Item\"])[1]/android.view.ViewGroup/android.widget.ImageView")
	private MobileElement swipe_EndElement;

	@AndroidFindBy (xpath="//android.widget.TextView[@text=\"BACK TO PRODUCTS\"]")
	private MobileElement resultvalidate;
//	@AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")
//	private MobileElement titleTxt;

	public LoginPage(){
	}

public LoginPage enterUserName(String username) throws InterruptedException {
	clear(usernameTxtFld);	
	sendKeys(usernameTxtFld, username, "login with " + username);
	return this;
}

public LoginPage enterPassword(String password) {
	clear(passwordTxtFld);
	sendKeys(passwordTxtFld, password, "password is " + password);
	return this;
}

public ProductsPage pressLoginBtn() {
	click(loginBtn, "press login button");
	return new ProductsPage();
}
public ProductsPage login(String username, String password) throws InterruptedException {
	enterUserName(username);
	enterPassword(password);
	return pressLoginBtn();
}

public String getErrTxt() {
	String err = getText(errTxt, "error text is - ");
	return err;
}

public LoginPage presssettingbutton(){
		click(settingbutton,"press setting button");
		return new LoginPage();
}

	public LoginPage pressLogoutBtn() {
		click(logoutBtn, "press Logout button");
		return new LoginPage();
	}

	public LoginPage swipeBystartAndEndElement(){
		swipeByElements(swipe_startElement,swipe_EndElement);
		tapByElement(swipe_startElement);
		return new LoginPage();
	}

	public String getresultValidate() {
		return getText(resultvalidate, "page test is - ");
	}
}
