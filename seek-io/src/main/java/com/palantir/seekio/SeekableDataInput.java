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

import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;

/** A convenience interface for seekable {@link DataInput}s. */
public interface SeekableDataInput extends SeekableInput, DataInput {

    /** A default, fast implementation that takes advantage of {@link #seek(long)}. */
    @Override
    default int skipBytes(int numBytes) throws IOException {
        seek(getPos() + numBytes);
        return numBytes;
    }

    @Override
    default void readFully(byte[] buf) throws IOException {
        readFully(buf, 0, buf.length);
    }

    @Override
    default void readFully(byte[] buf, int offset, int length) throws IOException {
        int numRead = 0;
        while (numRead < length) {
            int count = read(buf, offset + numRead, length - numRead);
            if (count < 0) {
                throw new EOFException();
            }
            numRead += count;
        }
    }

    @Override
    default boolean readBoolean() throws IOException {
        return readByte() != 0;
    }

    @Override
    default int readUnsignedByte() throws IOException {
        return Byte.toUnsignedInt(readByte());
    }

    @Override
    default int readUnsignedShort() throws IOException {
        return Short.toUnsignedInt(readShort());
    }

    @Override
    default char readChar() throws IOException {
        return (char) readShort();
    }

    @Override
    default float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    @Override
    default double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    @Override
    default String readLine() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    default String readUTF() throws IOException {
        throw new UnsupportedOperationException();
    }

}

