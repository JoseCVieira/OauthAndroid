<?php
/**
 * @author      Alex Bilbie <hello@alexbilbie.com>
 * @copyright   Copyright (c) Alex Bilbie
 * @license     http://mit-license.org/
 *
 * @link        https://github.com/thephpleague/oauth2-server
 */

namespace OAuth2ServerExamples\Repositories;
include __DIR__."/../../../vendor/autoload.php";

use League\OAuth2\Server\Entities\ClientEntityInterface;
use League\OAuth2\Server\Repositories\UserRepositoryInterface;
use OAuth2ServerExamples\Entities\UserEntity;

class UserRepository implements UserRepositoryInterface
{
    /**
     * {@inheritdoc}
     */
    public function getUserEntityByUserCredentials(
        $username,
        $password,
        $grantType,
        ClientEntityInterface $clientEntity
    ) {
		$connection = $_SESSION['conn'];
		//queries users from database resource
		$result = $connection->query('select name, salt, password from users;');
		//verifies username and password
		foreach($result as $row) {
		    $salt = $row['salt'];
		    $user = $row['name'];
		    $pass = $row['password'];
	            if ($username === $user && $pass === md5($salt . $password)) {
            		return new UserEntity();
        	    }
    	        }
		
        return;
    }
}
