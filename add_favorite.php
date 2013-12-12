<?php

    $con = mysql_connect("localhost", "root", "22784");
    if (!$con)
    {
        die('Could not connect: ' . mysql_error());
    }

    mysql_select_db("smoothies", $con) 
        or die("Unable to select database:" . mysql_error());

$id = $_POST['userID'];
$recipeID = $POST['recipeID'];

$query = "Insert into favorites values (NULL,$id,$recipeID)";

$result = mysql_query($query);

?>