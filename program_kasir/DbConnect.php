<?php
class DbConnect{
	private $con;

	function __construct(){
	}

	function connect(){
	require_once dirname(__FILE__).'/DbConfig.php';
		$this->con = new mysqli(db_host,db_user,db_password,db_name);

		if (mysqli_connect_errno()) {
			echo "Database tidak ada".mysqli_connect_err();
		}
		return $this->con;
	}
}