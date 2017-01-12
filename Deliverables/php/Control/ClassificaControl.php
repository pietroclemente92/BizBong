<?php
	//****************************************************************************************************************
    // Control che crea la Manager --> getClassifica (fetch dei nickname, punteggi, img_profilo della modalita cercata) 
    // Vengono istanziati i Classificati
    // Vengono inseriti in Classifica
    // Viene convertito l' Classifica in json
    // Viene inviato json
    //****************************************************************************************************************
    
	include '../Manager/Manager.php';
     include '../Entity/Profilo.php';
    include '../Entity/Classificato.php';
    include '../Entity/Classifica.php';

	if(isset($_GET['modalita']))
		$modalita = $_GET['modalita'];
        
    $manager = new Manager();
    $result = $manager->getClassifica($modalita);
    for($i = 0; $i < count($result); $i++){
    	$classificati[$i] = new Classificato($result[$i][0], $result[$i][1], $result[$i][2]);
    }
    $classifica = new Classifica($classificati);
    echo json_encode($classifica); //Decodifica l'oggetto utente in json
?>