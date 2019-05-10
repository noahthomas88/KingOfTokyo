package main;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	private String BUNDLE_NAME = "main.messages"; //$NON-NLS-1$

	private ResourceBundle RESOURCE_BUNDLE;

	public Messages(String code) {
		BUNDLE_NAME = "main.messages" + code;
		RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	}
	
	public String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
