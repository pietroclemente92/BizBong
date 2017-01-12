<?php
	//*******************************************************************************************************
    // Manager che gestisce le query (write/read) nel database
    // login(nickname, password) --> controlla se le credenziali passate sono esatte e l'utente è activate
    // registrazione(nickname, password, email) --> registra un nuovo utente con le credenziali passate.
    // 					   							Riceverà un' email di convalida. 
    // profilo(nickname) --> ritorna le informazioni di utente avente il nickname passante.
   	// getStatistiche(nickname) --> cerca le statistiche di un determinato profilo avente il nickname passato
    // getClassificato(modalia) --> cerca i classificati della modalita proposta
    //*******************************************************************************************************
	
	require_once '../Connessione/DB.php';
    include '../Entity/Domanda.php';
    
	class Manager {
        
    	public function Manager(){
        
        }
  
    	public function login($nickname, $password) {
        	$hashedPassword = md5($password);
          	$query = "SELECT * FROM my_bizbong.users WHERE nickname like '$nickname' and password like '$hashedPassword' ";
    		if($row = mysql_query($query)){
            	if(mysql_num_rows($row) == 1){
            		$data= mysql_fetch_row($row);
        			if($data[4] == 1){
                    	return true;
                    } else {
                    	return false;
                    }
                } else {
                	return false;
                }
            }
      	}
        
        public function registrazione($nickname, $password, $email){
        	$image = "ic_profile";
            $hashedPassword = md5($password);
    		$query = "INSERT INTO my_bizbong.users (nickname, password, email, immagine_profilo, active) VALUES ('$nickname','$hashedPassword','$email','$image', '0');";
            if(mysql_query($query)) {
                require_once('../Connessione/SendMessage.php');
                return true;
            } else {
                return false;
            }
        }
        
        public function getProfilo($nickname){
        	$query = "SELECT * FROM my_bizbong.users WHERE nickname like '$nickname'";
    		if($row = mysql_query($query)){
            	$data= mysql_fetch_row($row);
                return $data;
            }
            
            return null;
        }
        
        public function modificaProfilo($profilo){
        	$nickname = $profilo->getNickname();
            $password = md5($profilo->getPassword());
            $email = $profilo->getEmail();
        	$img_profilo = $profilo->getImgProfilo();
            
        	$query = "UPDATE my_bizbong.users SET immagine_profilo = '$img_profilo', password = '$password',
            			email = '$email' WHERE users.nickname = '$nickname';";
            if(mysql_query($query))
            	return true;
                
            return false;
        }
        
        /************************************************************************************/
        /* Viene creato un array avente tutte le modalita e il range dei questiti           */
        /* Viene scelto il quesito                                                          */
        /* Vengono create le domande                                                        */
        /* Viene fatta la fetch sulla singola riga                                          */
        /************************************************************************************/
        public function getDomandeBizBong($modalita){
        	//bizbong contiene i temi sulla prima riga, la posizione della prima domanda alla seconda riga
        	$bizbong = array(array("videogames", "logica", "informatica"),
                              array("1", "21", "41"));
           
            if($modalita == 'classica'){
            	//Viene generato un numero random x scegliere il tema
                $indiceTema = $this->UniqueRandomNumbersWithinRange(0, 2, 1)[0];
                $minRange = $bizbong[1][$indiceTema];
                $maxRange = $minRange + 19;
                $rangeDomande = $this->UniqueRandomNumbersWithinRange($minRange, $maxRange, 10);
                for($i = 0; $i < count($rangeDomande); $i++){
                	$query = "SELECT * FROM my_bizbong.bizbong WHERE id = '$rangeDomande[$i]'";
    				if($result = mysql_query($query)){
                    	$row = mysql_fetch_row($result);
                        $domanda = new Domanda($row[1], $row[2], $row[3], array($row[4], $row[5], $row[6], $row[7]), $row[8]);
                        $listaDomande[$i] = $domanda;
                    }
                }
            }
            return $listaDomande;
        }
        
        /************************************************************************************/
        /* Viene creato un array avente tutte le modalita e il range dei questiti           */
        /* Viene scelto il quesito                                                          */
        /* Viene fatta la fetch sulla singola riga                                          */
        /************************************************************************************/
        public function getSudoku($modalita){
        	//bizbong contiene i temi sulla prima riga, la posizione della prima domanda alla seconda riga
        	$sceltaSudoku = array(array("3x3m", "3x3d", "2x2m", "2x2d"),
                              array("1", "3", "5", "7"));
           
            //Viene generato un numero random x scegliere la traccia sudoku
            for($i = 0; $i < count($sceltaSudoku[0]);$i++){
            	if($modalita == $sceltaSudoku[0][$i]){
                	$minRange = $sceltaSudoku[1][$i];	// Genera in minRange dalla lista di quelli possibili nella riga 1 di sudoku
                }
            }
            $rangeSudoku = $this->UniqueRandomNumbersWithinRange($minRange, $minRange + 1, 1);
            $query = "SELECT * FROM my_bizbong.sudoku WHERE id = '$rangeSudoku[0]'";
    		if($result = mysql_query($query)){
            	$row = mysql_fetch_row($result);
                return $row;
            }
        }      
        
        /************************************************************************************/
        /* Viene eseguita query su statistiche passando tutto tranne il nickname            */
        /* Viene fatta la fetch sulla singola riga                                          */
        /************************************************************************************/
        public function getStatistiche($nickname){
        	$query = "SELECT `storia`, `geografia`, `sport`, `scienze`, `cinema e spettacolo`, 
            	`letteratura`, `logica`, `videogames`, `informatica`, `cartoni e serie tv`, `arte`, 
                `cucina e galateo`, `classica`, `bizbong`, `2x2f`, `2x2m`, `2x2d`,
                `3x3f`, `3x3m`, `3x3d` FROM my_bizbong.statistiche WHERE nickname like '$nickname'";
    		if($row = mysql_query($query)){
            	$data= mysql_fetch_row($row);
                return $data;
            }
            
            return null;
        }
        
        /************************************************************************************/
        /* Viene eseguita una join tra users e statistiche attraverso il nickname avente    */
        /* la modalita proposta da parametro.                                               */
        /* Viene fatta la fetch attraverso il while che scorre tutte le righe               */
        /************************************************************************************/
        public function getClassifica($modalita){
        	$query = "SELECT Users.`nickname`, Users.`immagine_profilo`, Statistiche.`$modalita` FROM my_bizbong.users as Users 
                INNER JOIN my_bizbong.statistiche as Statistiche ON Users.`nickname` = Statistiche.`nickname` 
                ORDER BY Statistiche.`$modalita` DESC;";
    		if($result = mysql_query($query)){
            	$i = 0;
            	while($row = mysql_fetch_row($result)){
                	$data[$i] = $row;
                    $i++;
                }
                return $data;
            }
            
            return null;
        }
        
        public function modificaStatistiche($profilo){
        	$nickname = $profilo->getNickname();
            $statistiche = $profilo->getStatistiche();
            $punteggiList = $statistiche->getPunteggiList();
            
        	$query = "UPDATE my_bizbong.statistiche SET `storia`='$punteggiList[0]',
            	`geografia`='$punteggiList[1]',`sport`='$punteggiList[2]',
                `scienze`='$punteggiList[3]',`cinema e spettacolo`='$punteggiList[4]',
                `letteratura`='$punteggiList[5]',`logica`='$punteggiList[6]',
                `videogames`='$punteggiList[7]',`informatica`='$punteggiList[8]',
                `cartoni e serie tv`='$punteggiList[9]',`arte`='$punteggiList[10]',
                `cucina e galateo`='$punteggiList[11]',
               	`classica`='$punteggiList[12]',`bizbong`='$punteggiList[13]',
                `2x2f`='$punteggiList[14]', `2x2m`='$punteggiList[15]',
                `2x2d`='$punteggiList[16]', `3x3f`='$punteggiList[17]',
                `3x3m`='$punteggiList[18]', `3x3d`='$punteggiList[19]'
                WHERE `nickname` = '$nickname';";
            if(mysql_query($query))
            	return true;
                
            return false;
        }
        
        public function closeDB(){
        	mysql_close($connect);
        }
        
        private function UniqueRandomNumbersWithinRange($min, $max, $quantity) {
          $numbers = range($min, $max);
          shuffle($numbers);
          return array_slice($numbers, 0, $quantity);
		}
	}
?>