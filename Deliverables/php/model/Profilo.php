<?php
	//**********************************************************************************************
    // Creazione dell' oggetto Utente che implementa JsonSerializable per essere inviato tramite json
    // Variabili: nickname, password, email, img_profilo, statistica
    // jsonSerialize() --> utilizzato per la conversione a json
    //**********************************************************************************************
	
    class Profilo implements JsonSerializable {
    	private $nickname;
        private $password;
        private $email;
        private $img_profilo;
        //private $statistica;
        
        public function __construct($nickname, $password, $email, $img_profilo){
        	$this->nickname = $nickname;
            $this->password = $password;
            $this->email = $email;
            $this->img_profilo = $profilo;
            //$this->statistica = $statistica;
        }
        
        public function getNickname(){
        	return $this->nickname;
        }
        
        public function getPassword(){
        	return $this->password;
        }
        
        public function getEmail(){
        	return $this->email;
        }
        
        public function getImgProfilo(){
        	return $this->img_profilo;
        }
        
        public function getStatistica(){
        	return $this->statistica;
        }
        
        public function setNickname($nickname){
        	$this->nickname = $nickname;
        }
        
        public function setPassword($password){
        	$this->password = $password;
        }
        
        public function setEmail($email){
        	$this->email = $email;
        }
        
        public function setImgProfilo($img_profilo){
        	$this->img_profilo = $img_profilo;
        }
        
        public function setStatistica($statistica){
        	$this->statistica = $statistica;
        }
        
        public function jsonSerialize() {
        	return get_object_vars($this);
    	}
    }
?>