/*
 * Copyright (c) 2015 AsyncHttpClient Project. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at
 *     http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package org.asynchttpclient.providers.netty.request.body;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;

public class NettyByteBufferBody extends NettyDirectBody {

    private final ByteBuffer bb;
    private final String contentType;
    private final long length;

    public NettyByteBufferBody(ByteBuffer bb) {
        this(bb, null);
    }

    public NettyByteBufferBody(ByteBuffer bb, String contentType) {
        this.bb = bb;
        length = bb.remaining();
        bb.mark();
        this.contentType = contentType;
    }

    @Override
    public long getContentLength() {
        return length;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public ByteBuf byteBuf() {
        // for retry
        bb.reset();
        return Unpooled.wrappedBuffer(bb);
    }
}
