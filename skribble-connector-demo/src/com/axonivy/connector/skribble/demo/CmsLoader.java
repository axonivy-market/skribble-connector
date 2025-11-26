package com.axonivy.connector.skribble.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.File;

public class CmsLoader {

  public static File loadSampleFromCms() throws IOException {
    var ivyFile = new File("invoice.pdf", true);
    Path path = ivyFile.getJavaFile().toPath();
    Files.copy(Ivy.cms().get("/sample/invoice").get().value().get().read().inputStream(), path, StandardCopyOption.REPLACE_EXISTING);
    return ivyFile;
  }
}
