<?php
	//*************************************************************************************************
    // Vengono definite le impostazioni del database: hostName, userName, password, nomeDatabase
    // $connect --> stabilisce la connessione al database
    //*************************************************************************************************
    
	define('HOST','localhost');
	define('USER',' bizbong');
	define('PASS','ht9G33uH91TKM0');
	define('DB','my_bizbong');
 
	$connect = mysql_connect(HOST,USER,PASS,DB);
?>