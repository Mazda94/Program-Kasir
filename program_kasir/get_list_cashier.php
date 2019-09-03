<?php

$response = array();

require_once dirname(__FILE__).'/DbAction.php';

if ($_SERVER['REQUEST_METHOD']=='POST') {
	if (isset($_POST['role_id']) ) {
            if ($_POST['role_id'] !== null ){
                $db = new DbAction();
                $listCashier = $db->getListCashier($_POST['role_id']);
                if($listCashier){
                    $response['error'] = false;
                    $response['message'] = count($listCashier)." data cashier ditemukan";
                    $response['data'] = $listCashier;
                } else {
                    $response['error'] = true;
                    $response['message'] = 'Daftar cashier kosong';
                }                
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
