package com.monpro.effectivejava.objects;

import java.io.*;

public class TryResource {

  /**
   * Try finally have many issues bufferedReader.readLine() could thrown an IOException
   * bufferReader.close() could also thrown an IOException the latter one will override the former
   * one Make it hard to debug
   */
  static String getFirstLineContent(String src) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new FileReader(src));
    try {
      return bufferedReader.readLine();
    } finally {
      bufferedReader.close();
    }
  }

  /**
   * it's getting worse when there are multiple resources so hard to track exception and code style
   * is ugly so we might use try-with-resources
   */
  static void copyFileFromSrcToDst(String src, String dst) throws IOException {
    InputStream inputStream = new FileInputStream(src);
    try {
      OutputStream outputStream = new FileOutputStream(dst);
      try {
        int size;
        byte[] bytes = new byte[1024];
        while ((size = inputStream.read(bytes)) >= 0) {
          outputStream.write(bytes, 0, size);
        }
      } finally {
        outputStream.close();
      }
    } finally {
      inputStream.close();
    }
  }

  /**
   * try with resource is a better way to do this It will suppress multi resources and make it easy
   * to track Also the code style is better
   */
  static String getFirstLineContentTryResource(String src) throws IOException {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(src))) {
      return bufferedReader.readLine();
    }
  }

  static void copyFileFromSrcToDstTryResource(String src, String dst) throws IOException {
    try (InputStream inputStream = new FileInputStream(src);
        OutputStream outputStream = new FileOutputStream(dst)) {
      byte[] bytes = new byte[1024];
      int size;
      while ((size = inputStream.read(bytes)) >= 0) {
        outputStream.write(bytes, 0, size);
      }
    }
  }
}
