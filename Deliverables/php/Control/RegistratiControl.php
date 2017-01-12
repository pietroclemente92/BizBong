<?php
	//************************************************************************************************
    // Control che inizializza l'oggetto Manager -> registrazione --> sendMessage --> Autenticazione
    //************************************************************************************************
    
	include '../Manager/Manager.php';

	if(isset($_GET['nickname']))
		$nickname = $_GET['nickname'];
	if(isset($_GET['password']))
		$password = $_GET['password'];
    if(isset($_GET['email']))
    	$email = $_GET['email'];
        
    $manager = new Manager();
    $result = $manager->registrazione($nickname, $password, $email);
    echo json_encode($result); //Decodifica l'oggetto utente in json
?>