<?php
	//**********************************************************************************************
    // Creazione dell' oggetto Statistiche che implementa JsonSerializable per essere inviato tramite json
    // Variabili: modalitaList, punteggiList
    // jsonSerialize() --> utilizzato per la conversione a json
    //**********************************************************************************************
	
    //include ".../Libreria/ArrayList.php";
    
    class Statistiche implements JsonSerializable {
    	private $modalitaList;
    	private $punteggiList;
        
        public function Statistiche($punteggiList) {
        	$this->modalitaList = array("storia", "geografia", "sport", "scienza", "cinema e spettacolo",
            		"letteratura", "videogames", "cartoni e serie tv", "arte", "cucina", 
                    "classica", "bizbong");
            $this->punteggiList = $punteggiList;
        }
        
        public function getModalitaList() {
        	return $this->modalitaList;
    	}

        public function getPunteggiList() {
            return $this->punteggiList;
        }

        public function setPunteggiList($punteggiList) {
            $this->punteggiList = $punteggiList;
        }
              
        public function castObjectToStatistiche($obj){
            foreach($obj as $property => &$value){
            	$this->$property = &$value;
            }
        }
              
        public function jsonSerialize() {
        	return get_object_vars($this);
    	}
    }
?>