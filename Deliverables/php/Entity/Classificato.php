<?php
	//**********************************************************************************************
    // Creazione dell' oggetto Classificato eredità Profilo che implementa JsonSerializable per essere inviato tramite json
    // Variabili: nickname, email, img_profilo, statistica, punteggio
    // jsonSerialize() --> utilizzato per la conversione a json
    //**********************************************************************************************
	
    include ".../Entity/Profilo.php";
    
    class Classificato extends Profilo implements JsonSerializable {
    	private $punteggio;
        
        public function Classificato($nickname, $img_profilo, $punteggio) {
        	parent::setNickname($nickname);
            parent::setImgProfilo($img_profilo);
            $this->punteggio = $punteggio;
        }
        
        public function getPunteggio(){
        	return $this->punteggio;
        }
        
        public function setPunteggio($punteggio){
        	$this->punteggio = $punteggio;
        }
        
        public function jsonSerialize() {
        	$obj = parent::jsonSerialize();
        	$obj["punteggio"] = $this->punteggio;
        	return $obj;
    	}
    }
?>