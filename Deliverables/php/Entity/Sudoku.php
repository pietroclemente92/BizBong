<?php
	//**********************************************************************************************
    // Creazione dell' oggetto Sudoku che implementa JsonSerializable per essere inviato tramite json
    // Variabili: modalita, tempo, mosse, dimensione, traccia
    // jsonSerialize() --> utilizzato per la conversione a json
    //**********************************************************************************************
	
    //include ".../Libreria/ArrayList.php";
    
    class Sudoku implements JsonSerializable {
    	private $modalita;
        private $tempo;
        private $mosse;
        private $dimensione;
        private $traccia;
        
        public function __construct($modalita, $tempo, $mosse, $dimensione, $traccia){
        	$this->modalita = $modalita;
            $this->tempo = $tempo;
            $this->mosse = $mosse;
            $this->dimensione = $dimensione;
            $this->traccia = $traccia;
        }
        
        public function getModalita(){
        	return $this->modalita;
        }
        
        public function getTempo(){
        	return $this->tempo;
        }
        
        public function getMosse(){
        	return $this->mosse;
        }
        
        public function getDimensione(){
        	return $this->dimensione;
        }
        
        public function getTraccia(){
        	return $this->traccia;
        }
        
        public function setModalita($modalita){
        	$this->modalita = $modalita;
        }
        
        public function setTempo($tempo){
        	$this->tempo = $tempo;
        }
        
        public function setMosse($mosse){
        	$this->mosse = $mosse;
        }
        
        public function setDimensione($dimensione){
        	$this->dimensione = $dimensione;
        }
        
        public function setTraccia($traccia){
        	$this->traccia = $traccia;
        }
              
        public function jsonSerialize() {
        	return get_object_vars($this);
    	}
    }
?>