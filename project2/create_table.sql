DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS clients;


CREATE TABLE users (
    id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL DEFAULT '',
    password VARCHAR(255) NOT NULL DEFAULT '',
    salt VARCHAR(255) NOT NULL DEFAULT '', 
    created DATE NOT NULL DEFAULT '1111-11-11',

    PRIMARY KEY (id)
);

CREATE TABLE clients (
    client_id VARCHAR(255) NOT NULL DEFAULT '',
    client_secret VARCHAR(255) NOT NULL DEFAULT '',
    name	VARCHAR(255) NOT NULL DEFAULT '',
    redirect_uri VARCHAR(255) NOT NULL DEFAULT '', 
    is_confidential VARCHAR(255) NOT NULL DEFAULT '',

    PRIMARY KEY (client_id)
);


insert into clients values("projetocsc", "$2y$10$BiXbl/B3IzpRroX3f07R1uoa7Wk649IjBbnyTgIHgtpBHsI2CJWa.", "Projecto CSC", "http://foo//bar", "true");
