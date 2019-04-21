package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;

abstract class Token {
    static final Token EMPTY = new SimpleToken(null, 0, 0);
    private final Token previous;

    public abstract void appendTo(BitArray bitArray, byte[] bArr);

    Token(Token previous) {
        this.previous = previous;
    }

    /* Access modifiers changed, original: final */
    public final Token getPrevious() {
        return this.previous;
    }

    /* Access modifiers changed, original: final */
    public final Token add(int value, int bitCount) {
        return new SimpleToken(this, value, bitCount);
    }

    /* Access modifiers changed, original: final */
    public final Token addBinaryShift(int start, int byteCount) {
        return new BinaryShiftToken(this, start, byteCount);
    }
}
