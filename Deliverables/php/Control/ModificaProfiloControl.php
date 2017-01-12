<?php
	//************************************************************************************************
    // Control che crea la Manager --> profilo (Invia informazioni dell' utente) 
    // Viene creato Utente con le informazioni
    // Viene convertito l' Utente in json
    // Viene inviato json
    //************************************************************************************************
    
	include '../Manager/Manager.php';
    include '../Entity/Profilo.php';
    include '../Entity/Statistiche.php';

	if(isset($_GET['profilo']))
		$obj = json_decode($_GET['profilo']);
       
    $profilo = new Profilo(null, null, null, null, new Statistiche(null));
    $profilo->castObjectToProfilo($obj);
	$manager = new Manager();
    $result = $manager->modificaProfilo($profilo);
    echo json_encode($result);
    //$nickname = $profilo->getNickname();
    
?>