
<!DOCTYPE html>
<html>
<?php

include "../include/db_access.php";


$stmt = $connection->prepare('INSERT INTO users (name,password,salt, created) ' . 'VALUES (:name, :password, :salt, NOW());');

$pass = $_POST['requester_pass']; // password 
$salt = uniqid(mt_rand(), true);
$hash_md5 = md5($salt . $pass); 


$stmt->execute(array( 'name' => $_POST['requester_name'], 'password' => $hash_md5, 'salt' => $salt));

//$id = $db->lastInsertId();
$result = $connection->query('select salt from users where users.id = 1');

 foreach($result as $row) {
        $r_number = $row['salt'];
        echo("<p>".$r_number . "</p>");
    }

//$key = $store->updateConsumer($_POST, $id, true);

//$c = $store->getConsumer($key, $id);
?>

<body>
<p><strong>Save these values!</strong></p>
<?php echo('Retrieved Password:'. md5($salt . $pass));?>
</body>
</html>
