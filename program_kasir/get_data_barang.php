<?php

$response = array();
$data = array();
	
require_once dirname(__FILE__).'/DbAction.php';
if ($_SERVER['REQUEST_METHOD']=='POST') {
	if (isset($_POST['kode_barang'])) {
		$db = new DbAction();
		$barang = $db->getBarang($_POST['kode_barang']);
		if($barang){
			$barang = $db->getDataBarang($_POST["kode_barang"]);
			$data['kode_barang'] = $barang['kode_barang'];
			$data['nama_barang'] = $barang['nama_barang'];
			$data['harga_barang'] = $barang['harga_barang'];
			$data['stok_barang'] = $barang['stok_barang'];
			$response['error'] = false;
			$response['message'] = '1 Data ditemukan';
			$response['data'] = $data;
		} else {
			$response['error'] = true;
			$response['message'] = 'Barang tidak ditemukan';
		}
	} else {
		$response['error'] = true;
		$response['message'] = 'Silahkan masukkan kode barang';
	}
}else{
	$response['error'] = true;
	$response['message'] = "Invalid request";
}

echo json_encode($response);