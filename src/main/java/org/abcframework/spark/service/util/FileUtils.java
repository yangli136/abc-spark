package org.abcframework.spark.service.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

public class FileUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

  private FileUtils() {}

  public static String getFullFilePath(String fileName) {
    File file = null;
    String fullPath = "";
    try {
      file = ResourceUtils.getFile("classpath:" + fileName);

      try {
        fullPath = file.getCanonicalPath();
      } catch (IOException e) {
        LOGGER.error("failed to retrieve full path of file:" + fileName, e);
      }
    } catch (FileNotFoundException e) {
      LOGGER.error("failed to find file:" + fileName, e);
    }

    LOGGER.info("fullpath:[{}]", fullPath);
    return fullPath;
  }
}
