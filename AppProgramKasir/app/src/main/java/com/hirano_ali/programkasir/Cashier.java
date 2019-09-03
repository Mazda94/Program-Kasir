package com.hirano_ali.programkasir;

class Cashier {
    String nip;
    String namaPetugas;
    String status;

    public Cashier(String nip, String namaPetugas, String status) {
        this.nip = nip;
        this.namaPetugas = namaPetugas;
        this.status = status;
    }

    public String getNip() {
        return nip;
    }

    public String getNamaPetugas() {
        return namaPetugas;
    }

    public String getStatus() {
        return status;
    }
}
