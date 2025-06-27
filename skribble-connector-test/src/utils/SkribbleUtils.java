package utils;

import java.util.List;

import com.axonivy.connector.skribble.mocks.SkribbleServiceMock;

import ch.ivyteam.ivy.environment.AppFixture;
import ch.ivyteam.ivy.rest.client.mapper.JsonFeature;
import ch.ivyteam.ivy.rest.client.security.CsrfHeaderFeature;
import constants.SkribbleCommonConstants;

public class SkribbleUtils {
  public static void setUpConfigForContext(String contextName, AppFixture fixture) {
    switch (contextName) {
      case SkribbleCommonConstants.REAL_CALL_CONTEXT_DISPLAY_NAME:
        setUpConfigForApiTest(fixture);
        break;
      case SkribbleCommonConstants.MOCK_SERVER_CONTEXT_DISPLAY_NAME:
        setUpConfigForMockServer(fixture);
        break;
      default:
        break;
    }
  }

  public static void setUpConfigForApiTest(AppFixture fixture) {
    String username = System.getProperty(SkribbleCommonConstants.USERNAME);
    String authKey = System.getProperty(SkribbleCommonConstants.AUTH_KEY);
    fixture.var("skribbleConnector.username", username);
    fixture.var("skribbleConnector.authKey", authKey);
  }

  public static void setUpConfigForMockServer(AppFixture fixture) {
    fixture.config("RestClients.Skribble.Url", SkribbleServiceMock.URI);
    fixture.config("RestClients.Skribble.Features",
        List.of(JsonFeature.class.getName(), CsrfHeaderFeature.class.getName()));
  }
}
