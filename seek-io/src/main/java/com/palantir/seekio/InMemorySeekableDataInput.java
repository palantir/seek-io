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

import java.io.EOFException;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

public final class InMemorySeekableDataInput implements SeekableDataInput {

    private final ByteBuffer bytes;

    public InMemorySeekableDataInput(byte[] bytes) {
        this.bytes = ByteBuffer.wrap(bytes);
    }

    @Override
    public int read(byte[] buf, int offset, int length) throws IOException {
        int remaining = bytes.remaining();
        if (remaining == 0) {
            return -1;
        } else if (remaining < length) {
            bytes.get(buf, offset, remaining);
            return remaining;
        } else {
            bytes.get(buf, offset, length);
            return length;
        }
    }

    @Override
    public void readFully(byte[] buf) throws IOException {
        try {
            bytes.get(buf);
        } catch (BufferUnderflowException e) {
            throw new EOFException();
        }
    }

    @Override
    public void readFully(byte[] buf, int off, int len) throws IOException {
        try {
            bytes.get(buf, off, len);
        } catch (BufferUnderflowException e) {
            throw new EOFException();
        }
    }

    @Override
    public byte readByte() throws IOException {
        try {
            return bytes.get();
        } catch (BufferUnderflowException e) {
            throw new EOFException();
        }
    }

    @Override
    public short readShort() throws IOException {
        try {
            return bytes.getShort();
        } catch (BufferUnderflowException e) {
            throw new EOFException();
        }
    }

    @Override
    public char readChar() throws IOException {
        try {
            return bytes.getChar();
        } catch (BufferUnderflowException e) {
            throw new EOFException();
        }
    }

    @Override
    public int readInt() throws IOException {
        try {
            return bytes.getInt();
        } catch (BufferUnderflowException e) {
            throw new EOFException();
        }
    }

    @Override
    public long readLong() throws IOException {
        try {
            return bytes.getLong();
        } catch (BufferUnderflowException e) {
            throw new EOFException();
        }
    }

    @Override
    public float readFloat() throws IOException {
        try {
            return bytes.getFloat();
        } catch (BufferUnderflowException e) {
            throw new EOFException();
        }
    }

    @Override
    public double readDouble() throws IOException {
        try {
            return bytes.getDouble();
        } catch (BufferUnderflowException e) {
            throw new EOFException();
        }
    }

    @Override
    public void seek(long position) throws IOException {
        try {
            bytes.position(toInt(position));
        } catch (BufferUnderflowException e) {
            throw new EOFException();
        }
    }

    /** Conversion function to assist with getting {@link #seek} to inline. */
    private static int toInt(long position) {
        if (position > Integer.MAX_VALUE) {
            throw illegalPosition(position);
        }
        return (int) position;
    }

    /** String and exception construction function to assist with getting {@link #toInt} to inline. */
    private static IllegalArgumentException illegalPosition(long position) {
        return new IllegalArgumentException(
                "position for InMemorySeekableDataInput may not exceed Integer.MAX_VALUE: " + position);
    }

    @Override
    public long getPos() throws IOException {
        return bytes.position();
    }

    @Override
    public void close() {}

}
