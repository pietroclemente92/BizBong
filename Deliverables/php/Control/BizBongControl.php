<?php
	//************************************************************************************************
    // Control che inizializza l'oggetto Manager --> getDomandeBizBong --> ritorna listaDomande; 
	// Istanziato bizbong con listaDomande;
	// Serializatto bizbong attavero json
    //************************************************************************************************

	include '../Manager/Manager.php';
    include '../Entity/BizBong.php';

	if(isset($_GET['modalita']))
		$modalita = $_GET['modalita'];
        
    $manager = new Manager();
    $listaDomande = $manager->getDomandeBizBong($modalita);
    $bizbong = new BizBong($modalita, $listaDomande);
    echo json_encode($bizbong); //Decodifica l'oggetto utente in json
?>