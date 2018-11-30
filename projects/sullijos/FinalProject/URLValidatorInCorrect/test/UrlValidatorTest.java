

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
//You can use this function to implement your manual testing	
	   	UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   
	   //assertTrue(!urlVal.isValid(""));
	   //assertTrue(urlVal.isValid("http://www.google.com"));
	   //assertTrue(urlVal.isValid("https://www.google.com"));
	   //assertTrue(!urlVal.isValid("htt//wwogle.com/"));
	   //assertTrue(urlVal.isValid("www.google.com")); //Bug number 1
	   //assertTrue(!urlVal.isValid("http://www.google")); // Bug number 2
	   //assertTrue(!urlVal.isValid("http://www.google:")); 

   }
   
   
   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing	   
	   
	    String[] validSchemes = {"http", "https", "ftp"};
	    String[] otherSchemes = {"abcd", "12d", "pph"};
	    
	    String punct = "://www."; 
	    String[] validDomain = {"google.com", "oregonstate.edu", "en.wikipedia.org"};
	    String[] validPaths = {"", "wiki/Oregon", "cs362-004/CS362-004-F2018"};
	    String[] otherDomain = {"", "..", ":"};
	    String[] otherPaths = {"<", "\"", "\\"};
	    
	    String test = "";
	    
	    UrlValidator uv = new UrlValidator( validSchemes);
	    //test the valid versions
	    //may be interesting to do this randomly?
	    //random picking adapted from 
	    //https://stackoverflow.com/questions/25150199/pick-a-random-element-from-a-string-array
	/*    for(int i = 0; i < 50; i++)
	    {
	    	//build the string
	    	test = test + validSchemes[(int)(Math.random() * validSchemes.length)];
	    	test = test + punct + validDomain[(int)(Math.random() * validDomain.length)];
	    	test = test + "/" +  validPaths[(int)(Math.random() * validPaths.length)];
	    	assertTrue("String failed: " + test, uv.isValid(test));
	    	test = "";
	    	
	   }
	   
	  */ 
	    for(int i =0; i < validDomain.length; i++)
	    {
	    	
	    	//isolated domain
	    	assertEquals(validDomain[i], false, uv.isValid(validDomain[i]));
	    	
	    	//isolated bad domain
	    	assertEquals(otherDomain[i], false, uv.isValid(otherDomain[i]));
	    	
	    	//add valid scheme
	    	String with_valid_scheme = validSchemes[0] + punct + validDomain[i];
	    	assertEquals(with_valid_scheme, true, uv.isValid(with_valid_scheme));
	    	
	    	//try invalid scheme
	    	String with_invalid_scheme = otherSchemes[0] + punct + validDomain[i];
	    	assertEquals(with_invalid_scheme, false, uv.isValid(with_invalid_scheme));
	    	
	    	//valid scheme, domain, and path
	    	//Something about the paths i've chosen are considered invalid?
	    	//
	    	with_valid_scheme = with_valid_scheme + "/" + validPaths[i];
	    //	assertEquals(with_valid_scheme, true, uv.isValid(with_valid_scheme));
	    	
	    	//valid scheme, domain, bad path
	    	with_valid_scheme = validSchemes[0] + punct + validDomain[i] + "/" + otherPaths[i];
	    	assertEquals(with_valid_scheme, false, uv.isValid(with_valid_scheme));
	    	
	    	//valid scheme, bad domain, valid path
	    	//this case is screwy, I expected an empty authority to be invalid?
	    	String bad_domain = validSchemes[i] + punct + otherDomain[i] + "/" +validPaths[i];
	 //   	assertEquals(bad_domain, false, uv.isValid(bad_domain));
	    	
	    	//bad scheme, valid domain + path
	    	String bad_scheme = otherSchemes[i] + punct + validDomain[i] + "/" + validPaths[i];
	    	assertEquals(bad_scheme, false, uv.isValid(bad_scheme));
	    	
		}
   }
   
   public void testYourSecondPartition(){
		//You can use this function to implement your Second Partition testing	   
		UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	    String[] validIpAdds = {"0.0.0.0", "255.255.255.255"};
	    String[] invalidIpAdds = {"0.0.0.-1", "255.255.255.256"};
	    for (int i = 0; i < validIpAdds.length; i++) {
			// test alone 
			assertEquals(validIpAdds[i], false, urlVal.isValid(validIpAdds[i]));
	    	  
	    	// test with valid scheme
	    	String validScheme = "https://" + validIpAdds[i];
	    	assertEquals(validScheme, true, urlVal.isValid(validScheme));
	    	  
	    	// test with invalid scheme
	    	String invalidScheme = "1ttp://" + validIpAdds[i];
	    	assertEquals(invalidScheme, false, urlVal.isValid(invalidScheme));
	    	  
	    	// test with valid port
	    	String validPort = validScheme + ":80";
	    	assertEquals(validPort, true, urlVal.isValid(validPort));
	    	  
	    	// test with invalid port
	    	String invalidPort = validScheme + ":-1";
	    	assertEquals(invalidPort, false, urlVal.isValid(invalidPort));
	    	  
	    	// test with valid path
	    	String validPath = validPort + "/path1";
	       	assertEquals(validPath, true, urlVal.isValid(validPath));
	       	  
	       	// test with invalid path
	       	String invalidPath = validPort + "/..";
	       	assertEquals(invalidPath, false, urlVal.isValid(invalidPath));
	       	  
	       	// test valid query
	    	String validQuery = validPath + "?action=view";
	       	assertEquals(validQuery, true, urlVal.isValid(validQuery));    	  
	    }
	      
	      for (int i = 0; i < invalidIpAdds.length; i++) {
	    	// test alone 
	    	assertEquals(invalidIpAdds[i], false, urlVal.isValid(invalidIpAdds[i]));
	    	  
	    	// test with valid scheme
	    	String validScheme = "https://" + invalidIpAdds[i];
	    	assertEquals(validScheme, false, urlVal.isValid(validScheme));
	    	  
	    	// test with valid port
	    	String validPort = validScheme + ":80";
	    	assertEquals(validPort, false, urlVal.isValid(validPort));
	    	  
	    	// test with valid path
	    	String validPath = validPort + "/path1";
	       	assertEquals(validPath, false, urlVal.isValid(validPath));
	       	  
	       	// test valid query
	    	String validQuery = validPath + "?action=view";
	       	assertEquals(validQuery, false, urlVal.isValid(validQuery)); 
	    }

   }
   
   public void testIsValid()
   {
	   //First part of web address
	   ResultPair[] testPart1 = {
			   new ResultPair("http://www.google",true),
			   new ResultPair("www.google",true),
			   new ResultPair("http://www.bbc",true),
			   new ResultPair("https://www.bbc",true),
			   new ResultPair("httpwwwgoogle",false),
			   new ResultPair("http://www.google.com.com",false),
			   new ResultPair("http://www..",false),
	   };
	   
	   //Second part of web address
	   ResultPair[] testPart2 = {
			   new ResultPair(".com",true),
			   new ResultPair(".co.uk",true),
			   new ResultPair(".ca.com",false),
			   new ResultPair(".com.com",false),
	   };
	   
	   //Length of lists (This is a bad solution. 
	   //Better to call a length method from ResultPair, but that doesn't exist.
	   int testListLength1 = 7;
	   int testListLength2 = 4;
	   
	   //Testing loop
	   for(int i = 0; i < testListLength1; i++) {
		   for(int j = 0; j < testListLength2; j++) {
			   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
			   
			   String myURL = testPart1[i].item + testPart2[j].item;
			   boolean ismyUrlValid = testPart1[i].valid && testPart2[j].valid;
			   assertEquals(urlVal.isValid(myURL), ismyUrlValid);
		    }
	   }   
   }
}
