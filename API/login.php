<?php

    $con = mysql_connect("localhost", "root", "22784");
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}

    mysql_select_db("smoothies", $con) 
        or die("Unable to select database:" . mysql_error());

    $password = $_GET['password'];
    $email = $_GET['email'];

    $query = "select * from users where email = '$email'";
	$result = mysql_query($query);

	while ($row = mysql_fetch_assoc($result)){
        $id = $row['userID'];
    }
    
	$query = "select password from users where userID ='$id'";
    $result2 = mysql_query($query);
    while ($row = mysql_fetch_assoc($result2)){
    	$database_password = $row ['password'];
    }
    if($password != $database_password){
   		echo 'error';
    }
    else{
   		echo $id;
        }
    ?>