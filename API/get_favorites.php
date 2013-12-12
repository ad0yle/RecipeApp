<?php

    $con = mysql_connect("localhost", "root", "22784");
    if (!$con)
    {
        die('Could not connect: ' . mysql_error());
    }

    mysql_select_db("smoothies", $con) 
        or die("Unable to select database:" . mysql_error());

$id = $_GET['userID'];
$int_id = (int) $id;

$query = "Select * from favorites where userID = $int_id";
$result = mysql_query($query);
$print = "";
while ($row = mysql_fetch_assoc($result)){
        $recipeID = $row['recipeID'];
        $print = $print . $recipeID . '#';
    }
    echo $print;
?>