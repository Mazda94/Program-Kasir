<?php

class DbACtion{
	private $con;

	function __construct()
	{
		require_once dirname(__FILE__).'/DbConnect.php';
		$db = new DbConnect();
		$this->con = $db->connect();
	}
    
	public function userLogin($idNumber, $password){
		$pass = md5($password);
		$query = $this->con->prepare("SELECT * FROM t_master_user WHERE nomor_id = ? AND password = ?");
		$query->bind_param("ss", $idNumber, $pass);
		$query->execute();
		$query->store_result();
		return $query->num_rows > 0;
	}

	public function getUserData($idNumber){
		$query = $this->con->prepare("SELECT nomor_id, nama_user, role_name, t_master_user.role_id FROM t_master_user LEFT JOIN t_master_role 
										ON t_master_user.user_id=t_master_role.role_id WHERE nomor_id = ?"  );
		$query->bind_param("s", $idNumber);
		$query->execute();
		return $query->get_result()->fetch_assoc();
	}

	public function tambahUser($name, $nip, $password, $role){
		if ($this->userIsExist($nip)) {
			return 0;
		}else{
			if($role == "kasir"){
				$role_id = 2;
			} elseif ($role == "user") {
				$role_id = 3;
			}
			$pass = md5($password);
			$statement = $this->con->prepare("INSERT INTO t_master_user (nama_user, nomor_id, password, role_id) VALUES (?, ?, ?, ?)");
			$statement->bind_param("sssi",$name,$nip,$pass,$role_id);
			if ($statement->execute()) {
				return 1;
			}else{
				return 2;
			}
		}
	}

	private function userIsExist($nip){
		$query = $this->con->prepare("SELECT * FROM t_master_user WHERE nomor_id = ?");
		$query->bind_param("s", $nip);
		$query->execute();
		$query->store_result();
		return $query->num_rows > 0;
	}

	public function getBarang($kode_barang){
		$query = $this->con->prepare("SELECT * FROM t_master_barang WHERE kode_barang = ?");
		$query->bind_param("s", $kode_barang);
		$query->execute();
		$query->store_result();
		return $query->num_rows > 0;
	}

	public function getDataBarang($kode_barang){
		$query = $this->con->prepare("SELECT * FROM t_master_barang WHERE kode_barang = ?"  );
		$query->bind_param("s", $kode_barang);
		$query->execute();
		return $query->get_result()->fetch_assoc();
	}

	public function getListBarang(){
		$query = $this->con->prepare("SELECT * FROM t_master_barang"  );
		$query->execute();
		return $query->get_result()->fetch_all(MYSQLI_ASSOC);
	}

	public function getListCashier($role_id){
		$query = $this->con->prepare("SELECT nomor_id, nama_user, role_name FROM t_master_user LEFT JOIN t_master_role ON t_master_user.role_id = t_master_role.role_id WHERE t_master_user.role_id = ?"  );
		$query->bind_param("s", $role_id);
		$query->execute();
		return $query->get_result()->fetch_all(MYSQLI_ASSOC);
	}

	public function masukkanBarang($kode_barang, $nama_barang, $harga_barang, $stok_barang){
		if ($this->barangSudahAda($kode_barang)) {
			$stok_barang = $stok_barang + 1;
			$statement = $this->con->prepare("UPDATE t_master_barang SET stok_barang = ? WHERE kode_barang = ?");
			$statement->bind_param("is", $stok_barang,$kode_barang);
			$statement->execute();
		}else{
			$statement = $this->con->prepare("INSERT INTO t_master_barang (kode_barang, nama_barang, harga_Barang, stok_barang) VALUES (?, ?, ?, ?)");
			$statement->bind_param("sssi",$kode_barang ,$nama_barang ,$harga_barang, $stok_barang);
			$statement->execute();
		}
	}


	private function barangSudahAda($kode_barang){
		$query = $this->con->prepare("SELECT * FROM t_master_barang WHERE kode_barang = ?");
		$query->bind_param("s", $kode_barang);
		$query->execute();
		$query->store_result();
		return $query->num_rows > 0;
	}
}