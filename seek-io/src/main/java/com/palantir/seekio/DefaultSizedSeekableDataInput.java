/*
 * Copyright 2017 Palantir Technologies, Inc. All rights reserved.
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

public final class DefaultSizedSeekableDataInput implements SizedSeekableDataInput {
    private final SeekableDataInput delegate;
    private final long streamLength;

    public DefaultSizedSeekableDataInput(SeekableDataInput delegate, long streamLength) {
        this.delegate = delegate;
        this.streamLength = streamLength;
    }

    @Override
    public void seek(long offset) throws IOException {
        delegate.seek(offset);
    }

    @Override
    public long getPos() throws IOException {
        return delegate.getPos();
    }

    @Override
    public int read(byte[] bytes, int offset, int length) throws IOException {
        return delegate.read(bytes, offset, length);
    }

    @Override
    public long length() {
        return streamLength;
    }

    @Override
    public void close() throws IOException {
        delegate.close();
    }

    @Override
    public byte readByte() throws IOException {
        return delegate.readByte();
    }

    @Override
    public short readShort() throws IOException {
        return delegate.readShort();
    }

    @Override
    public int readInt() throws IOException {
        return delegate.readInt();
    }

    @Override
    public long readLong() throws IOException {
        return delegate.readLong();
    }

    @Override
    public int skipBytes(int numBytes) throws IOException {
        return delegate.skipBytes(numBytes);
    }

    @Override
    public void readFully(byte[] buf) throws IOException {
        delegate.readFully(buf);
    }

    @Override
    public void readFully(byte[] buf, int offset, int length) throws IOException {
        delegate.readFully(buf, offset, length);
    }

    @Override
    public boolean readBoolean() throws IOException {
        return delegate.readBoolean();
    }

    @Override
    public int readUnsignedByte() throws IOException {
        return delegate.readUnsignedByte();
    }

    @Override
    public int readUnsignedShort() throws IOException {
        return delegate.readUnsignedShort();
    }

    @Override
    public char readChar() throws IOException {
        return delegate.readChar();
    }

    @Override
    public float readFloat() throws IOException {
        return delegate.readFloat();
    }

    @Override
    public double readDouble() throws IOException {
        return delegate.readDouble();
    }

    @Override
    public String readLine() throws IOException {
        return delegate.readLine();
    }

    @Override
    public String readUTF() throws IOException {
        return delegate.readUTF();
    }
}
