<?php

    $con = mysql_connect("localhost", "root", "22784");
    if (!$con)
    {
        die('Could not connect: ' . mysql_error());
    }

    mysql_select_db("smoothies", $con) 
        or die("Unable to select database:" . mysql_error());

$query = "Select * from ingredients";

$result = mysql_query($query);

$ingredients = array();

while ($row = mysql_fetch_assoc($result)){
		$name = $row['name'];
		$recipeID = $row['recipeID'];
		$ingredient = array("name"=>$name,"recipeID"=>$recipeID);
		array_push($ingredients,$ingredient);
    }

    $all_ingredients = array("ingredients")=>$ingredients);

$json = json_encode($all_ingredients);
echo $json;
?>