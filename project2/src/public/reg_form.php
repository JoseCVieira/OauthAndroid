<!DOCTYPE html>
<html>
<?php
    include "../include/db_access.php";
    //include "../include/common.php";
?>
  <head>
    <meta charset="utf-8">
    <title>title</title>
  </head>
  <body>
    <h1> Registration </h1>
    <form method="post" action="register.php">
       <fieldset>
         <legend>Register</legend>
         <div>
          <label for="requester_name">Username</label>
          <input type="text" id="requester_name" name="requester_name">
         </div>
         <div>
          <label for="requester_pass">Password</label>
          <input type="text" id="requester_pass" name="requester_pass">
         </div>
         <div>
          <label for="application_uri">URI</label>
          <input type="text" id="application_uri" name="application_uri">
         </div>
         <div>
          <label for="callback_uri">Callback URI</label>
          <input type="text" id="callback_uri" name="callback_uri">
         </div>
       </fieldset>
       <input type="submit" value="Register">
    </form>
    <?php
    $pass = 'abcxyz123'; // password 
    $salt = '}#f4ga~g%7hjg4&j(7mk?/!bj30ab-wi=6^7-$^R9F|GK5J#E6WT;IO[JN'; // random string 

    $hash = md5($pass); 
    $hash_md5 = md5($salt . $pass); 

    // echo now 
    echo 'Original Password: ' . $pass . '<br><br>';
    echo 'Original Salt: ' . $salt . '<br><br>';
    echo 'MD5: ' . $hash . '<br><br>';
    echo 'MD5 with Salt: ' . $hash_md5 . '<br><br>';
    ?>
  </body>
</html>
