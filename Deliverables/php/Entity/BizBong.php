<?php
	//**********************************************************************************************
    // Creazione dell' oggetto BizBong che implementa JsonSerializable per essere inviato tramite json
    // Variabili: modalita, listaDomande
    // jsonSerialize() --> utilizzato per la conversione a json
    //**********************************************************************************************
	
    //include ".../Libreria/ArrayList.php";
    
    class BizBong implements JsonSerializable{
    	private $modalita;
        private $listaDomande;
        
        public function __construct($modalita, $domande){
        	$this->modalita = $modalita;
            $this->listaDomande = $domande;
        }
        
        public function getModalita(){
        	return $this->modalita;
        }
        
        public function getDomande(){
        	return $this->listaDomande;
        }
        
        public function setModalita($modalita){
        	$this->modalita = $modalita;
        }
        
        public function setDomande($domande){
        	$this->listaDomande = $domande;
        }
              
        public function jsonSerialize() {
        	return get_object_vars($this);
    	}
    }
?>