<?php

$response = array();
require_once dirname(__FILE__).'/DbAction.php';

if ($_SERVER['REQUEST_METHOD']=='POST') {
	if (isset($_POST['kode_barang']) and
		isset($_POST['nama_barang']) and
        isset($_POST['harga_barang']) and
        isset($_POST['stok_barang']) ) {
            if ($_POST['kode_barang'] !== null &&
                $_POST['nama_barang'] !== null &&
                $_POST['harga_barang'] !== null &&
                $_POST['stok_barang'] !== null ){
                $db = new DbAction();
		        $hasil = $db->masukkanBarang($_POST['kode_barang'],
									$_POST['nama_barang'],
                                    $_POST['harga_barang'],
                                    $_POST['stok_barang']); 
		        $response['error'] = false;
                $response['message'] = "Insert barang berhasil";
            } else {
                $response['error'] = true;
                $response['message'] = 'Pastikan data terisi dengan benar';
            }
	}else{
	$response['error'] = true;
	$response['message'] = "field ada yang kosong";
	}
}else{
	$response['error'] = true;
	$response['message'] = "Invalid request";
	
}

echo json_encode($response);