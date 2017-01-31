/*
 * Copyright 2016 Palantir Technologies, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.palantir.seekio;

import java.io.IOException;
import java.nio.ByteBuffer;

public final class InMemorySeekableDataInput implements SeekableDataInput {

    private final ByteBuffer bytes;

    public InMemorySeekableDataInput(byte[] bytes) {
        this.bytes = ByteBuffer.wrap(bytes);
    }

    @Override
    public int read(byte[] buf, int offset, int length) throws IOException {
        bytes.get(buf, offset, length);
        return length;
    }

    @Override
    public void readFully(byte[] buf) throws IOException {
        bytes.get(buf);
    }

    @Override
    public void readFully(byte[] buf, int off, int len) throws IOException {
        bytes.get(buf, off, len);
    }

    @Override
    public byte readByte() throws IOException {
        return bytes.get();
    }

    @Override
    public short readShort() throws IOException {
        return bytes.getShort();
    }

    @Override
    public char readChar() throws IOException {
        return bytes.getChar();
    }

    @Override
    public int readInt() throws IOException {
        return bytes.getInt();
    }

    @Override
    public long readLong() throws IOException {
        return bytes.getLong();
    }

    @Override
    public float readFloat() throws IOException {
        return bytes.getFloat();
    }

    @Override
    public double readDouble() throws IOException {
        return bytes.getDouble();
    }

    @Override
    public void seek(long position) throws IOException {
        if (position > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(
                    "position for InMemorySeekableInput may not exceed Integer.MAX_VALUE: " + position);
        }
        bytes.position((int) position);
    }

    @Override
    public long getPos() throws IOException {
        return bytes.position();
    }

    @Override
    public void close() {}

}
