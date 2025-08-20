package com.axonivy.connector.skribble.test;

import static com.axonivy.utils.e2etest.enums.E2EEnvironment.REAL_SERVER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.axonivy.connector.skribble.demo.SampleHelper;
import com.axonivy.connector.skribble.documents.DocumentsData;
import com.axonivy.connector.skribble.mocks.SkribbleServiceMock;
import com.axonivy.connector.skribble.signaturerequest.SignatureRequestData;
import com.axonivy.utils.e2etest.context.MultiEnvironmentContextProvider;
import com.axonivy.utils.e2etest.utils.E2ETestUtils;
import com.skribble.api.v2.client.CreateSignature;
import com.skribble.api.v2.client.CreateSignatureRequest;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.AppFixture;
import ch.ivyteam.ivy.rest.client.mapper.JsonFeature;
import ch.ivyteam.ivy.rest.client.security.CsrfHeaderFeature;
import constants.SkribbleCommonConstants;

@IvyProcessTest(enableWebServer = true)
@ExtendWith(MultiEnvironmentContextProvider.class)
class TestApiSubProcess {

  private static final BpmProcess SIGNATURE_REQUEST = BpmProcess.name("SignatureRequest");
  private static final BpmProcess DOCUMENTS = BpmProcess.name("Documents");
  private boolean isRealTest;

  private interface Start {
    BpmElement GET_ALL_SIGNATURE_REQUEST = SIGNATURE_REQUEST.elementName("getAll(SignatureRequestSearchParameters)");
    BpmElement CREATE_SIGNATURE_REQUEST = SIGNATURE_REQUEST.elementName("create(CreateSignatureRequest)");
    BpmElement GET_DOCUMENT_CONTENT = DOCUMENTS.elementName("getContentData(String)");
    BpmElement GET_DOCUMENT_META = DOCUMENTS.elementName("getMetaData(String)");
    BpmElement DELETE_DOCUMENT = SIGNATURE_REQUEST.elementName("delete(SignatureRequest)");
  }

  private Runnable runRealEnv(AppFixture fixture) {
    return () -> {
      String username = System.getProperty(SkribbleCommonConstants.USERNAME);
      String authKey = System.getProperty(SkribbleCommonConstants.AUTH_KEY);
      fixture.var("skribbleConnector.username", username);
      fixture.var("skribbleConnector.authKey", authKey);
    };
  }

  private Runnable runMockEnv(AppFixture fixture) {
    return () -> {
      fixture.config("RestClients.Skribble.Url", SkribbleServiceMock.URI);
      fixture.config("RestClients.Skribble.Features",
          List.of(JsonFeature.class.getName(), CsrfHeaderFeature.class.getName()));
    };
  }

  @BeforeEach
  void setup(ExtensionContext context, AppFixture fixture) {
    isRealTest = context.getDisplayName().equals(REAL_SERVER.getDisplayName());
    E2ETestUtils.determineConfigForContext(context.getDisplayName(), runRealEnv(fixture), runMockEnv(fixture));
  }

  @TestTemplate
  void callSubProcess_getAllSignatureRequest(ExtensionContext context, BpmClient bpmClient) {
    var result = bpmClient.start().subProcess(Start.GET_ALL_SIGNATURE_REQUEST).execute();
    SignatureRequestData data = result.data().last();
    if (isRealTest) {
      assertThat(data.getSignatureRequests()).hasSizeGreaterThanOrEqualTo(1);
    } else {
      assertThat(data.getSignatureRequests()).hasSize(1);
    }

    assertThat(data.getSignatureRequests().get(0).getSignatures()).hasSize(2);
    assertThat(data.getSignatureRequests().get(0).getSignatures().get(0).getSignerIdentityData().getFirstName())
        .contains("Max");
  }

  @TestTemplate
  void callSubProcess_getDocumentContent(ExtensionContext context, BpmClient bpmClient) {
    String documentId = isRealTest ? "d76812ab-e3cc-a709-a928-5bf5899a93a2" : "20c535e0-4260-f52a-b2ba-a45eb280d9a3";
    var result = bpmClient.start().subProcess(Start.GET_DOCUMENT_CONTENT).withParam("documentId", documentId).execute();
    DocumentsData data = result.data().last();
    assertThat(data.getContent()).startsWith("JVBER");
  }

  @TestTemplate
  void callSubProcess_getDocumentMeta(ExtensionContext context, BpmClient bpmClient) {
    String documentId = isRealTest ? "d76812ab-e3cc-a709-a928-5bf5899a93a2" : "20c535e0-4260-f52a-b2ba-a45eb280d9a3";
    var result = bpmClient.start().subProcess(Start.GET_DOCUMENT_META).withParam("documentId", documentId).execute();
    DocumentsData data = result.data().last();
    if (isRealTest) {
      assertThat(data.getDocument().getTitle()).startsWith("Test-Document");
    } else {
      assertThat(data.getDocument().getTitle()).startsWith("Test-Title");
    }
  }

  @TestTemplate
  void callSubProcess_createSignatureRequest(ExtensionContext context, BpmClient bpmClient)
      throws NoSuchFieldException {
    CreateSignatureRequest sample = SampleHelper.createSignatureRequestDocSample("Test-Title", "Test-message");
    CreateSignature cs = SampleHelper.createSignature("max.muster@yxz.com", false);
    cs.setSignerIdentityData(SampleHelper.createSignerIdentityData("max.muster@yxz.com", "Max", "Muster"));
    cs.setVisualSignature(SampleHelper.createVisualSignature(100, 100));
    sample.addSignaturesItem(cs);

    var result = bpmClient.start().subProcess(Start.CREATE_SIGNATURE_REQUEST)
        .withParam("createSignatureRequest", sample).execute();

    SignatureRequestData data = result.data().last();
    if (isRealTest) {
      assertNotNull(data.getSignatureRequest().getDocumentId());

      var deleteResult = bpmClient.start().subProcess(Start.DELETE_DOCUMENT)
          .withParam("signatureRequest", data.getSignatureRequest()).execute();

      assertThat(deleteResult.data().last().get("statusResponse").toString().contains("status=200"));
    } else {
      assertThat(data.getSignatureRequest().getDocumentId().startsWith("6a41f"));
    }
  }
}
