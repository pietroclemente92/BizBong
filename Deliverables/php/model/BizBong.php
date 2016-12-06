<?php
	//**********************************************************************************************
    // Creazione dell' oggetto BizBong che implementa JsonSerializable per essere inviato tramite json
    // Variabili: tempo, punteggio, listaDomande
    // jsonSerialize() --> utilizzato per la conversione a json
    //**********************************************************************************************
	
    //include ".../Libreria/ArrayList.php";
    
    class BizBong implements JsonSerializable {
    	private $tempo;
        private $punteggio;
        private $listaDomande;
        
        public function __construct($domande){
        	$this->tempo = 160;
            $this->punteggio = 0;
            $this->domande = $domande;
        }
        
        public function getTempo(){
        	return $this->tempo;
        }
        
        public function getPunteggio(){
        	return $this->punteggio;
        }
        
        public function getDomande(){
        	return $this->domande;
        }
        
        public function setTempo($tempo){
        	$this->tempo = $tempo;
        }
        
        public function setPunteggio($punteggio){
        	$this->punteggio = $punteggio;
        }
        
        public function setDomande($domande){
        	$this->domande = $domande;
        }
              
        public function jsonSerialize() {
        	return get_object_vars($this);
    	}
    }
?>