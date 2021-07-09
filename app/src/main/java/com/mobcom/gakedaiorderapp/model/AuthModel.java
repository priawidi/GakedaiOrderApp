package com.mobcom.gakedaiorderapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthModel {
    @SerializedName("iss")
    @Expose
    private String iss;
    @SerializedName("azp")
    @Expose
    private String azp;
    @SerializedName("aud")
    @Expose
    private String aud;
    @SerializedName("sub")
    @Expose
    private String sub;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("email_verified")
    @Expose
    private String emailVerified;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("given_name")
    @Expose
    private String givenName;
    @SerializedName("family_name")
    @Expose
    private String familyName;
    @SerializedName("locale")
    @Expose
    private String locale;
    @SerializedName("iat")
    @Expose
    private String iat;
    @SerializedName("exp")
    @Expose
    private String exp;
    @SerializedName("alg")
    @Expose
    private String alg;
    @SerializedName("kid")
    @Expose
    private String kid;
    @SerializedName("typ")
    @Expose
    private String typ;

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getAzp() {
        return azp;
    }

    public void setAzp(String azp) {
        this.azp = azp;
    }

    public String getAud() {
        return aud;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(String emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getIat() {
        return iat;
    }

    public void setIat(String iat) {
        this.iat = iat;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

}

