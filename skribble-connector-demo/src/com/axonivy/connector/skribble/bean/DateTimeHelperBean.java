package com.axonivy.connector.skribble.bean;

import java.io.Serializable;
import java.util.Date;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;

import ch.ivyteam.ivy.environment.Ivy;

@ViewScoped
@Named
public class DateTimeHelperBean implements Serializable {
  public static Date getJavaDate(String instantStr) {
    Date date = null;
    try {
      date = Date.from(java.time.Instant.parse(instantStr));
    } catch (Exception e) {
      Ivy.log().info(e);
    }
    return date;
  }
}
