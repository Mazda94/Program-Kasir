<?php

$response = array();
require_once dirname(__FILE__).'/DbAction.php';


if ($_SERVER['REQUEST_METHOD']=='POST') {
	if (isset($_POST['fullname']) and
		isset($_POST['nip']) and
        isset($_POST['password']) and
        isset($_POST['role']) ) {

		$db = new DbAction();
		$hasil = $db->tambahUser(	$_POST['fullname'],
									$_POST['nip'],
                                    $_POST['password'],
                                    $_POST['role']); 
		
		if ($hasil == 1) {
			$response['error'] = false;
            $response['message'] = "User berhasil ditambah";
		}elseif($hasil == 2){
			$response['error'] = true;
			$response['message'] = "Registrasi gagal";
		}elseif ($hasil == 0) {
			$response['error'] = true;
			$response['message'] = "User sudah terdaftar";
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