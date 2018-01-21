# proj-csc

Requerimentos:
    Apache2 - configurar apache2.conf com:
                         <      exemplo        >
              <Directory </home/user/Documents/>project2/src/public>
                    Options Indexes FollowSymLinks
                    AllowOverride All
                    Require all granted
              </Directory>
              
           - configurar sites-available/000-default.conf com:
                        <  igual ao anterior  >
           DocumentRoot </home/user/Documents/>project2/src/public


    php
    mysql - correr script para inserir tabelas
            user da DB e respetiva pass em include/db_access.php
    
    
