<?php

require __DIR__."/../include/db_access.php";
include __DIR__ . '/../../vendor/autoload.php';

use League\OAuth2\Server\ResourceServer;

use OAuth2ServerExamples\Repositories\AccessTokenRepository;

use Psr\Http\Message\ResponseInterface;

use Psr\Http\Message\ServerRequestInterface;

use Slim\App;







$app = new App([

    // Add the resource server to the DI container

    ResourceServer::class => function () {

        $server = new ResourceServer(

            new AccessTokenRepository(),            // instance of AccessTokenRepositoryInterface

            'file://' . __DIR__ . '/../public.key'  // the authorization server's public key

        );



        return $server;

    },

]);



// Add the resource server middleware which will intercept and validate requests

$app->add(

    new \League\OAuth2\Server\Middleware\ResourceServerMiddleware(

        $app->getContainer()->get(ResourceServer::class)

    )

);



// An example endpoint secured with OAuth 2.0

$app->get(

    '/users',

    function (ServerRequestInterface $request, ResponseInterface $response) use ($app) {

		$user_id = $request->getAttribute('oauth_user_id');
        $connection = $_SESSION['conn'];
		$result = $connection->query("Select name from users where id = $user_id");
		foreach($result as $row){
			$user_name = $row['name'];
		}



        $response->getBody()->write(json_encode($user_name));



        return $response->withStatus(200);

    }

);



$app->run();
