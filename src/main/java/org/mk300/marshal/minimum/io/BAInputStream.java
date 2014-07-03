/*
 * Copyright 2014 Masazumi Kobayashi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mk300.marshal.minimum.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
public final class BAInputStream extends InputStream {

    protected final byte buf[];
    protected int pos;
    protected int mark = 0;
    protected int count;
    
    public BAInputStream(byte buf[]) {
        this.buf = buf;
        this.pos = 0;
        this.count = buf.length;
    }

    public BAInputStream(byte buf[], int offset, int length) {
        this.buf = buf;
        this.pos = offset;
        this.count = Math.min(offset + length, buf.length);
        this.mark = offset;
    }
    
    public final int read() {
        return (pos < count) ? (buf[pos++] & 0xff) : -1;
    }

    public final int read(byte b[], int off, int len) {
        if (pos >= count) {
            return -1;
        }

        int avail = count - pos;
        if (len > avail) {
            len = avail;
        }
        if (len <= 0) {
            return 0;
        }
        System.arraycopy(buf, pos, b, off, len);
        pos += len;
        return len;
    }

    public final long skip(long n) {
        long k = count - pos;
        if (n < k) {
            k = n < 0 ? 0 : n;
        }

        pos += k;
        return k;
    }

    public final int available() {
        return count - pos;
    }

    public final boolean markSupported() {
        return true;
    }

    public final void mark(int readAheadLimit) {
        mark = pos;
    }

    public final void reset() {
        pos = mark;
    }

    public final void close() throws IOException {
    }

    public final int getPos() {
    	return pos;
    }
    public final byte[] getBuf() {
    	return buf;
    }
}