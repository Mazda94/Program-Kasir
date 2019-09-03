package com.hirano_ali.programkasir;

class Barang {
    String kodeBarang;
    String namaBarang;
    String hargaBarang;
    String stokBarang;

    public Barang(String kodeBarang, String namaBarang, String hargaBarang, String stokBarang) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.stokBarang = stokBarang;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public String getHargaBarang() {
        return hargaBarang;
    }

    public String getStokBarang() {
        return stokBarang;
    }
}
