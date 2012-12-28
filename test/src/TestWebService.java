import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.ytec.bi.ods.etl.webservice.FileMonitorResultRequestDocument;
import com.ytec.bi.ods.etl.webservice.FileMonitorResultRequestDocument.FileMonitorResultRequest;
import com.ytec.bi.ods.etl.webservice.FileMonitorResultResponseDocument;
public class TestWebService {

	

	private String feedbackUri;


	public String getFeedbackUri() {
		return feedbackUri;
	}

	public void setFeedbackUri(String feedbackUri) {
		this.feedbackUri = feedbackUri;
	}
	
	
	
	public static void main(String[] args) {
		TestWebService tt=new TestWebService();
	String	uri="http://localhost:8080/lbpm-agent/services";
		try {
			FileMonitorResultRequest request = FileMonitorResultRequest.Factory.newInstance();
			 ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml", TestWebService.class);
			  WebServiceTemplate webServiceTemplate = (WebServiceTemplate) applicationContext
			    .getBean("webServiceTemplate");
			FileMonitorResultRequestDocument requestDocument = FileMonitorResultRequestDocument.Factory
					.newInstance();
			requestDocument.setFileMonitorResultRequest(request);
			FileMonitorResultResponseDocument responseDocument;
			responseDocument = (FileMonitorResultResponseDocument)webServiceTemplate.marshalSendAndReceive(uri,
							requestDocument);
			boolean flag= responseDocument.getFileMonitorResultResponse();
			System.out.println("调用成功!" + flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
	
	
	
	
	
	
	
	
	
}
