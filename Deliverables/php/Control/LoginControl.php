<?php
	//************************************************************************************************
    // Control che inizializza l'oggetto Manager --> login --> ritorna true o false
    //************************************************************************************************

	include '../Manager/Manager.php';

	if(isset($_GET['nickname']))
		$nickname = $_GET['nickname'];
	if(isset($_GET['password']))
		$password = $_GET['password'];
        
    $manager = new Manager();
    $result = $manager->login($nickname, $password);
    echo json_encode($result); //Decodifica l'oggetto utente in json
?>