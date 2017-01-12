<?php
	// definisco mittente e destinatario della mail
	$nome_mittente = "BizBong";
	$mail_mittente = "bizbong.unisa@gmail.com";
	$mail_destinatario = $email;

	// definisco il subject ed il body della mail
	$mail_oggetto = "Iscrizione a BizBong";
	$mail_corpo = "Grazie di aver effettuato l' iscrizione al nostro gioco. Conferma la tua iscriozione cliccando sul link: http://bizbong.altervista.org/php/Connessione/AccountConvalidate.php?nickname=$nickname ";

	// aggiusto un po' le intestazioni della mail
	// E' in questa sezione che deve essere definito il mittente (From)
	// ed altri eventuali valori come Cc, Bcc, ReplyTo e X-Mailer
	$mail_headers = "From: " .  $nome_mittente . " <" .  $mail_mittente . ">\r\n";
	$mail_headers .= "Reply-To: " .  $mail_mittente . "\r\n";
	$mail_headers .= "X-Mailer: PHP/" . phpversion();
	mail($mail_destinatario, $mail_oggetto, $mail_corpo, $mail_headers);
?>