/*
 * Copyright (C) 2011 University of Washington.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.opendatakit.briefcase.util;

import java.util.List;

import org.opendatakit.briefcase.model.FormStatus;
import org.opendatakit.briefcase.model.ServerConnectionInfo;
import org.opendatakit.briefcase.model.TerminationFuture;

public class TransferFromServer implements ITransferFromSourceAction {

  final ServerConnectionInfo originServerInfo;
  final TerminationFuture terminationFuture;
  final List<FormStatus> formsToTransfer;

  public TransferFromServer(ServerConnectionInfo originServerInfo, 
      TerminationFuture terminationFuture, List<FormStatus> formsToTransfer) {
    this.originServerInfo = originServerInfo;
    this.terminationFuture = terminationFuture;
    this.formsToTransfer = formsToTransfer;
  }

  @Override
  public boolean doAction() {
    
    ServerFetcher fetcher = new ServerFetcher(originServerInfo, terminationFuture);
    
    return fetcher.downloadFormAndSubmissionFiles(formsToTransfer);
  }

  @Override
  public boolean isSourceDeletable() {
    return false;
  }

}