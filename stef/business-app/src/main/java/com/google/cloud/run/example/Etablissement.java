package com.google.cloud.run.example;

public class Etablissement {
    String libelle;
    String code;
    String codeSociete;
    String siret;
    String telephone;

    public Etablissement() {
    }
    public Etablissement(String libelle, String code, String codeSociete, String siret, String telephone) {
        this.libelle = libelle;
        this.code = code;
        this.codeSociete = codeSociete;
        this.siret = siret;
        this.telephone = telephone;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getCodeSociete() {
        return codeSociete;
    }
    public void setCodeSociete(String codeSociete) {
        this.codeSociete = codeSociete;
    }
    public String getSiret() {
        return siret;
    }
    public void setSiret(String siret) {
        this.siret = siret;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    protected Stub getStub() {
        return new Stub(
            this.code,
            this.libelle
        );
    }

    /**
     * Lighweight info about an Etablissement: just code and libelle.
     */
    public static final class Stub {
        String code;
        String libelle;
        public Stub() {
        }
        public Stub(String code, String libelle) {
            this.code = code;
            this.libelle = libelle;
        }
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getLibelle() {
            return libelle;
        }
        public void setLibelle(String libelle) {
            this.libelle = libelle;
        }
    }
}
