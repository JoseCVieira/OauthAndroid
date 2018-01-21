<?php
    $host = "localhost";
    $user = "user";
    $pass = "inseguro2";
    $dsn = "mysql:host=$host; dbname=project";
    try {
            $connection = new PDO($dsn, $user, $pass);
    } 
    catch (PDOException $exception) {
            echo("<p>Error: ");
            echo($exception->getMessage());
            echo("</p>");
            exit();
    }
    $_SESSION['conn'] = $connection;
?>
