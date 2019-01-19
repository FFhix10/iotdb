/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.iotdb.tsfile.file.metadata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.iotdb.tsfile.file.metadata.utils.TestHelper;
import org.apache.iotdb.tsfile.file.metadata.utils.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TsDeviceMetadataTest {

  public static final long START_TIME = 523372036854775806L;
  public static final long END_TIME = 523372036854775806L;
  final String PATH = "target/outputDevice.tsfile";

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
    File file = new File(PATH);
    if (file.exists()) {
      file.delete();
    }
  }

  @Test
  public void testWriteIntoFile() throws IOException {
    TsDeviceMetadata metaData = TestHelper.createSimpleDeviceMetaData();
    File file = new File(PATH);
    if (file.exists()) {
      file.delete();
    }
    FileOutputStream fos = new FileOutputStream(file);
    metaData.serializeTo(fos);
    fos.close();

    FileInputStream fis = new FileInputStream(new File(PATH));
    Utils.isTsDeviceMetadataEqual(metaData, TsDeviceMetadata.deserializeFrom(fis));
    fis.close();
  }
}
