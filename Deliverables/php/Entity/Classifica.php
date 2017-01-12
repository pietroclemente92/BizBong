<?php
	//**********************************************************************************************
    // Creazione dell' oggetto Classifica eredità Profilo che implementa JsonSerializable per essere inviato tramite json
    // Variabili: arrayClassificati
    // jsonSerialize() --> utilizzato per la conversione a json
    //**********************************************************************************************
	
    include ".../Entity/Profilo.php";
    include ".../Entity/Classificato.php";
    
    class Classifica implements JsonSerializable {
    	private $classificatiList;
        
        public function Classifica($classificatiList) {
        	$this->classificatiList = $classificatiList;
        }
        
        public function getClassificatiList(){
        	return $this->classificatiList;
        }
        
        public function setClassificatiList($classificatiList){
        	$this->classificatiList = $classificatiList;
        }
        
        public function jsonSerialize() {
        	return get_object_vars($this);
    	}
    }
?>