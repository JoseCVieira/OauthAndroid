
<!DOCTYPE html>
<html>
<?php

include "../include/db_access.php";

$allow_registration = true;
$result = $connection->query('select * from users;');
foreach($result as $row){
	$name = $row[name];
	if($name === $_POST['requester_name']){
		$allow_registration = false;
	}
}
if($allow_registration === true){
	$stmt = $connection->prepare('INSERT INTO users (name,password,salt, created) ' . 'VALUES (:name, :password, :salt, NOW());');

	$pass = $_POST['requester_pass']; // password 
	$salt = uniqid(mt_rand(), true);
	$hash_md5 = md5($salt . $pass); 

	$stmt->execute(array( 'name' => $_POST['requester_name'], 'password' => $hash_md5, 'salt' => $salt));
	echo("1");
}else{
echo("0");
}
?>

<body>
</html>
