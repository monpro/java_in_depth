package com.monpro.file;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

class FileSharingTest {

  @Test
  void FileSharingBehaviorTest() {
    FileSharing fileSharing = new FileSharing();
    assertEquals(fileSharing.join(new ArrayList<>(Arrays.asList(101, 102, 103))), 1);
    assertEquals(fileSharing.join(new ArrayList<>(Arrays.asList(101, 102, 103))), 2);
    assertEquals(fileSharing.join(new ArrayList<>(Arrays.asList(101, 102, 103))), 3);

    fileSharing.leave(2);
    assertEquals(fileSharing.join(new ArrayList<>(Arrays.asList(103, 104, 105))), 2);

    assertEquals(fileSharing.request(2, 103), new ArrayList<>(Arrays.asList(1, 2, 3)));
    assertEquals(fileSharing.request(2, 102), new ArrayList<>(Arrays.asList(1, 3)));
  }
}
