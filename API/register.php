<?php
$con = mysql_connect("localhost", "root", "22784");
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	mysql_select_db("smoothies", $con)
		or die("Unable to select database:" . mysql_error());

	$email = $_POST['email'];
	$query = "SELECT email FROM users WHERE email='$email'";

	$name = $_POST['name'];
	$password = $_POST['password'];

	
	$result = mysql_query($query);
	if (mysql_num_rows($result) > 0){
		echo "error";
	}
	else {
	$query = "INSERT INTO users VALUES(NULL,'$name','$email','$password')";
	mysql_query($query);
		echo $query;
	}
?>