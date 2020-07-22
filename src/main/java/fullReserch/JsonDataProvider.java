package fullReserch;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;




public class JsonDataProvider {
     
	
	@BeforeMethod
	public void setUp() {
		System.out.println("Test start");
	}
	
	@DataProvider(name="dp")
	public String [] readJson() throws Exception{
		JSONParser jsonParse = new JSONParser();
		FileReader file= new FileReader(".//JSON_Data//PaymentData.json");
		Object obj = jsonParse.parse(file);
		JSONObject userLoginJsonObject=(JSONObject)obj;
		JSONArray userJsonArray=(JSONArray)userLoginJsonObject.get("OneTimePayment");
	
	String arr [] = new String[userJsonArray.size()];
	
	     for(int i =0 ;i<=userJsonArray.size()-1;i++) {
	    	 JSONObject users=(JSONObject)userJsonArray.get(i);
	    	String accountNumber= (String) users.get("AccountNumber");
	    	String amount= (String) users.get("Amount");
	    	String type= (String) users.get("Type");
	    	String dateOfPayment= (String) users.get("DateOfPayment");
	    	 
	    	arr[i]=accountNumber+","+amount+","+type+","+dateOfPayment;
	     }
	     
	     return arr;
	}
	
	@Test(dataProvider="dp")
	public void dataTest(String data) {
		
		String[] users = data.split(",");
		System.out.println(users[0]+","+users[1]+","+users[2]+","+users[3]);
		System.out.println("testing code");
	}
	
	
	
	
	@AfterMethod
	public void endTest() {
		System.out.println("End the test");
	}
}
