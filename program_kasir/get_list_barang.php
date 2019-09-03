<?php

$response = array();

require_once dirname(__FILE__).'/DbAction.php';

$db = new DbAction();
$listBarang = $db->getListbarang();
if($listBarang){
    $response['error'] = false;
    $response['message'] = count($listBarang)." data barang ditemukan";
    $response['data'] = $listBarang;
} else {
    $response['error'] = true;
    $response['message'] = 'Daftar barang kosong';
}

echo json_encode($response);
