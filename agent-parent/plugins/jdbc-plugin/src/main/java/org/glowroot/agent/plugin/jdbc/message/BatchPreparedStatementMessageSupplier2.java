/*
 * Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.glowroot.agent.plugin.jdbc.message;

import org.glowroot.agent.plugin.api.Message;
import org.glowroot.agent.plugin.api.MessageSupplier;

public class BatchPreparedStatementMessageSupplier2 extends MessageSupplier {

    private final String sql;

    private final int batchSize;

    public BatchPreparedStatementMessageSupplier2(String sql, int batchSize) {
        this.sql = sql;
        this.batchSize = batchSize;
    }

    @Override
    public Message get() {
        StringBuilder sb = new StringBuilder();
        sb.append("jdbc execution: ");
        if (batchSize > 1) {
            // print out number of batches to make it easy to identify
            sb.append(batchSize);
            sb.append(" x ");
        }
        sb.append(sql);
        return Message.from(sb.toString());
    }
}
