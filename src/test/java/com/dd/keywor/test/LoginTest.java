package com.dd.keywor.test;

import org.testng.annotations.Test;

import com.dd.keyword.engine.KeywordEngine;

public class LoginTest {
	KeywordEngine keywordEngine;
	@Test
	public void loginTest () {
	keywordEngine = new KeywordEngine ();
	keywordEngine.startExecution("FbSheet");
	}
}
