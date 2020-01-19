package com.xtel;

import org.apache.log4j.Logger;
public class Demo {
  private static final Logger logger = Logger.getLogger(Demo.class);
  public static void main(String[] args) {
    logger.debug("debug log");
    logger.error("error log");
    logger.info("info log");
  }
}
