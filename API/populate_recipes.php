<?php

    $con = mysql_connect("localhost", "root", "22784");
    if (!$con)
    {
        die('Could not connect: ' . mysql_error());
    }

    mysql_select_db("smoothies", $con) 
        or die("Unable to select database:" . mysql_error());


$query = "Select * from recipes";

$result = mysql_query($query);
$all_info = array();

    while ($row = mysql_fetch_assoc($result)){
        $recipe_id = $row['recipeID'];
        $recipe_name = $row['name'];
        $category = $row['category'];
        $prep_time = $row['prep_time'];
        
        $info = array("recipeID"=>$recipe_id, "recipeName"=>$recipe_name, "category"=>$category, "prepTime"=>$prep_time);
        array_push($all_info,$info);
    }

    $all_recipes = array("recipes"=>$all_info);

    $json = json_encode($all_recipes);
    echo $json;
?>