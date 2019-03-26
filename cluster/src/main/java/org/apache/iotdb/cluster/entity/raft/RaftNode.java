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
package org.apache.iotdb.cluster.entity.raft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RaftNode {

  // TODO replace this by PeerId

  private static final Logger LOGGER = LoggerFactory.getLogger(RaftNode.class);

  private String ip;
  private int port;
  private int groupId;

  public RaftNode() {
  }

  public RaftNode(String ip, int port, int groupId) {
    this.ip = ip;
    this.port = port;
    this.groupId = groupId;
  }

  public String getIp() {
    return ip;
  }

  public int getPort() {
    return port;
  }

  public int getGroupId() {
    return groupId;
  }

  /**
   * Parse a raft node from string in the format of "ip:port:groupId",
   * returns null if fail to parse.
   *
   * @param s input string with the format of "ip:port:groupId"
   * @return parsed peer
   */
  public static RaftNode parseRaftNode(String s) {
    final RaftNode node = new RaftNode();
    if (node.parse(s)) {
      return node;
    }
    return null;
  }

  /**
   * Parse raftNode from string that generated by {@link #toString()}
   */
  public boolean parse(String s) {
    final String[] tmps = s.split(":");
    if (tmps.length != 3 && tmps.length != 2) {
      return false;
    }
    try {
      this.ip = tmps[0];
      this.port = Integer.valueOf(tmps[1]);
      if (tmps.length == 3) {
        this.groupId = Integer.valueOf(tmps[2]);
      } else {
        this.groupId = 0;
      }
      return true;
    } catch (final Exception e) {
      LOGGER.error("Parse raft node from string failed: {}", s, e);
      return false;
    }
  }

  @Override
  public String toString() {
    return this.ip + ":" + this.port + ":" + this.groupId;
  }
}
