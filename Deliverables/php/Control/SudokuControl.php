<?php
	//************************************************************************************************
    // Control che inizializza l'oggetto Manager --> getDomandeBizBong --> ritorna listaDomande; 
	// Istanziato bizbong con listaDomande;
	// Serializatto bizbong attavero json
    //************************************************************************************************

	include '../Manager/Manager.php';
    include '../Entity/Sudoku.php';

	if(isset($_GET['modalita']))
		$modalita = $_GET['modalita'];
        
    $manager = new Manager();
    $resultSudoku = $manager->getSudoku($modalita);
    $sudoku = new Sudoku($resultSudoku[1], $resultSudoku[2],$resultSudoku[3], $resultSudoku[4], $resultSudoku[5]);
    echo json_encode($sudoku); //Decodifica l'oggetto utente in json
?>