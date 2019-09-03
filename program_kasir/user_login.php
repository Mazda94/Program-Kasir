<?php

$response = array();
$data = array();
	
require_once dirname(__FILE__).'/DbAction.php';
if ($_SERVER['REQUEST_METHOD']=='POST') {
	if (isset($_POST['nomor_id']) and isset($_POST['password'])) {
		$db = new DbAction();
		$userData = $db->userLogin($_POST['nomor_id'], $_POST['password']);
		if ($userData) {
			$userData = $db->getUserData($_POST['nomor_id']);
			$data['nomor_id'] = $userData['nomor_id'];
			$data['nama'] = $userData['nama_user'];
			$data['role_id'] = $userData['role_id'];
			$data['role_name'] = $userData['role_name'];
			$response['error'] = false;
			$response['message'] = "Login berhasil";
			$response['data'] = $data;
		}else{
			$response['error'] = true;
			$response['message'] = "Username atau password yang anda masukkan salah";
		}
	}else{
		$response['error'] = true;
		$response['message'] = "Invalid request";
	}
}

echo json_encode($response);