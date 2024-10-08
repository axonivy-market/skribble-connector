package com.axonivy.connector.skribble.bean;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivyteam.ivy.environment.Ivy;

@ViewScoped
@ManagedBean
public class DateTimeHelperBean {
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
