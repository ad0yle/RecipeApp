<?php

    $con = mysql_connect("localhost", "root", "22784");
    if (!$con)
    {
        die('Could not connect: ' . mysql_error());
    }

    mysql_select_db("smoothies", $con) 
        or die("Unable to select database:" . mysql_error());

$id = $_POST['userID'];
$int_id = (int) $id;
$recipeID = $_POST['recipeID'];
$int_recipe = (int) $recipeID;
$query = "Insert into favorites values (NULL,$int_id,$int_recipe)";

$result = mysql_query($query);
?>