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
include __DIR__."/../include/db_access.php";
use League\OAuth2\Server\Repositories\ClientRepositoryInterface;
use OAuth2ServerExamples\Entities\ClientEntity;

class ClientRepository implements ClientRepositoryInterface
{
    /**
     * {@inheritdoc}
     */
    public function getClientEntity($clientIdentifier, $grantType, $clientSecret = null, $mustValidateSecret = true)
    {

		$found_client = false;
		$connection = $_SESSION['conn'];
		$result = $connection->query("SELECT * from clients;");
		foreach($result as $row){

			$client_id = $row['client_id'];
			$client_secret = $row['client_secret'];
			$client_confidential = $row['is_confidential'];
			$client_name = $row['name'];
			$client_uri = $row['redirect_uri'];
			if ($client_id === $clientIdentifier){
				$found_client = true;
				if (
            		$mustValidateSecret === true
            		&& $client_confidential === 'true'
            		&& password_verify($clientSecret, $client_secret) === false
        			) {
            			return;
        			}
			}
		}
		if($found_client === false){
			return;		
		}

        $client = new ClientEntity();
        $client->setIdentifier($client_id);
        $client->setName($client_name);
        $client->setRedirectUri($client_uri);

        return $client;
    }
}
