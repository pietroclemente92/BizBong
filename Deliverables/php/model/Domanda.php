<?php
	//**********************************************************************************************
    // Creazione dell' oggetto Domanda che implementa JsonSerializable per essere inviato tramite json
    // Variabili: punteggio, tema, domanda, listaDomande, rispostaVera
    // jsonSerialize() --> utilizzato per la conversione a json
    //**********************************************************************************************
	
    //include ".../Libreria/ArrayList.php";
    
    class Domanda implements JsonSerializable {
    	private $punteggio;
        private $tema;
        private $domanda;
        private $listaRisposte;
        private $rispostaVera;
        
        public function __construct($punteggio, $tema, $domanda, $listaRisposte, $rispostaVera){
            $this->punteggio = $punteggio;
            $this->tema = $tema;
            $this->domanda = $domanda;
            $this->listaRisposte = $listaRisposte;
            $this->rispostaVera = $rispostaVera;
        }
        
        public function getPunteggio(){
        	return $this->punteggio;
        }
        
        public function getTema(){
        	return $this->tema;
        }
        
        public function getDomanda(){
        	return $this->domanda;
        }
        
        public function getListaRisposte(){
        	return $this->listaRisposte;
        }
        
        public function getRispostaVera(){
        	return $this->rispostaVera;
        }
        
        public function setPunteggio($punteggio){
        	$this->punteggio = $punteggio;
        }
        
        public function setTema($tema){
        	$this->tema = $tema;
        }
        
        public function setDomanda($domanda){
        	$this->domanda = $domanda;
        }
        
        public function setListaRisposte($listaRisposte){
        	$this->listaRisposte = $listaRisposte;
        }
        
        public function setRispostaVera($rispostaVera){
        	$this->rispostaVera = $rispostaVera;
        }
              
        public function jsonSerialize() {
        	return get_object_vars($this);
    	}
    }
?>