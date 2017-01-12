<?php
	//************************************************************************************************
    // Control che crea la Manager --> getStatistiche(nickname)
    // Control che crea la Manager --> getProfilo (Invia informazioni dell' utente) 
    // Viene creato Profilo con le informazioni con statistiche
    // Viene convertito il Profilo in json
    // Viene inviato json
    //************************************************************************************************
    
	include '../Manager/Manager.php';
    include '../Entity/Profilo.php';
    include '../Entity/Statistiche.php';

	if(isset($_GET['nickname']))
		$nickname = $_GET['nickname'];
        
    $manager = new Manager();
    $array = $manager->getStatistiche($nickname);
    $statistiche = new Statistiche($array);
    $array = $manager->getProfilo($nickname);
    $profilo = new Profilo($array[0], $array[1], $array[2], $array[3], $statistiche);
    echo json_encode($profilo); //Decodifica l'oggetto utente in json
?>