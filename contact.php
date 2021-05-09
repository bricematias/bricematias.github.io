<?php
if (isset($_Post)['mailform'])
{
	if(!empty($_Post['name'])AND !empty($_Post['email']) AND !empty($_Post['message']))
	{

	}
	else
	{
		$msg="Tout les champs doivent être complété"
	}
}


?>
<!DOCTYPE html>
<html>
<head>
	<title>Contact</title>
	<link rel="stylesheet" type="text/css" href="contact.css">

</head>
<body>
	<header>
		<nav>
			<ul class="navigation">
				<li><a href="index.html" id="menu">Accueil</li></a>
				<li><a href="presentation.html" id="menu">Présentation</li></a>
				<li><a href="stages.html" id="menu">Stages</li></a>
				<li><a href="contact.html" id="menu">Contact</li></a>
			</ul>	
		</nav></header>
			<a href="" class="btn-area">Login</a><div>




	<div class="Contact-title">
		<H1>Bonjour à tous</H1>
		<h2>Je suis disponible pour répondre à vos questions</h2>
	</div>

	<div class="contact-form">
		<form id="contact-form" method="post" action="contact-form-handler.php">
			<input type="text" name="name" class="form-control" placeholder="Ton nom" ><br>
			<input type="email" name="email" class="form-control" placeholder="Ton adresse" required><br>
				<textarea name="message" class="form-control" placeholder="Message" rows="4" required=""></textarea><br>

				<input type="submit" class="form-control submit" value="Envoyer le message" name="mailform">
		</form>
		<?php
			if (isset($msg))
			{
				echo $msg;
			}
		?>

	</div>


</body>
</html>