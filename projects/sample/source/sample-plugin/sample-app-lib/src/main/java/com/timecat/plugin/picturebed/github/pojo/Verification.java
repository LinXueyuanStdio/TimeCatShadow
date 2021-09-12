package com.timecat.plugin.picturebed.github.pojo;

public final class Verification {
    private final boolean verified;

    private final String reason;

    private final String signature;

    private final String payload;

    public final boolean getVerified() {
        return this.verified;
    }


    public final String getReason() {
        return this.reason;
    }


    public final String getSignature() {
        return this.signature;
    }


    public final String getPayload() {
        return this.payload;
    }

    public Verification(boolean verified,  String reason,  String signature,  String payload) {
        super();
        this.verified = verified;
        this.reason = reason;
        this.signature = signature;
        this.payload = payload;
    }


    public final Verification copy(boolean verified,  String reason,  String signature,  String payload) {
        return new Verification(verified, reason, signature, payload);
    }


    public String toString() {
        return "Verification(verified=" + this.verified + ", reason=" + this.reason + ", signature=" + this.signature + ", payload=" + this.payload + ")";
    }
}
