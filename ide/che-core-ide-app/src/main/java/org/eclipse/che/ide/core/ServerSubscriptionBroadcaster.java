/*
 * Copyright (c) 2012-2017 Red Hat, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package org.eclipse.che.ide.core;

import static org.eclipse.che.api.core.jsonrpc.commons.ClientSubscriptionHandler.CLIENT_SUBSCRIBE_METHOD_NAME;
import static org.eclipse.che.ide.api.jsonrpc.Constants.WS_AGENT_JSON_RPC_ENDPOINT_ID;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.che.api.core.jsonrpc.commons.RequestTransmitter;

/** Broadcaster sends subscription event to the server when client has already started. */
@Singleton
public class ServerSubscriptionBroadcaster {
  private boolean isSubscribed = false;

  @Inject
  private void subscribe(RequestTransmitter requestTransmitter) {
    if (isSubscribed) {
      return;
    }

    requestTransmitter
        .newRequest()
        .endpointId(WS_AGENT_JSON_RPC_ENDPOINT_ID)
        .methodName(CLIENT_SUBSCRIBE_METHOD_NAME)
        .noParams()
        .sendAndSkipResult();

    isSubscribed = true;
  }
}
