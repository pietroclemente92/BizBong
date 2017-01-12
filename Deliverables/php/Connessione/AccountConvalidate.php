<?php
	//**********************************************************************************************
    // La convalida all'utente setta 1 ad active 
    //**********************************************************************************************

    if(isset($_GET['nickname']))
    	$nickname = $_GET['nickname'];
        
    require_once('../Connessione/DB.php');
	$query = "UPDATE my_bizbong.users SET active = '1' WHERE users.nickname = '$nickname';";
	if(mysql_query($query)){
    	echo '<script language="javascript">';
        echo 'alert("Account attivato senza problemi!")';
        echo '</script>';
        echo "<script>window.close();</script>";
    } else{
    	echo '<script language="javascript">';
        echo 'alert("Problemi con il database! Riprovare più tardi grazie.")';
        echo '</script>';           
    }
    
	mysql_close($connect);
?>