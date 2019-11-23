<?php

require_once('init.php');

$user_type = $_POST["user_type"];

if($user_type === "college") {

$name = $_POST["name"];
$password = $_POST["password"];

$sql1 = "INSERT INTO college(name, password) VALUES('$name', '$password')";

if(mysqli_query($con, $sql1))
echo "Adding User Successful !";

else
echo "Adding User Failed !";

}

else if($user_type === "student") {

$name = $_POST["name"];
$password = $_POST["password"];
$dob = $_POST["dob"];
$college_id = $_POST["college_id"];
$dept = $_POST["dept"];
$year = $_POST["year"];
$sem = $_POST["sem"];

$sql1 = "INSERT INTO student(name, password, dob, college_id, department, year, semester) VALUES('$name', '$password', '$dob', '$college_id', '$dept', '$year', '$sem');";

if(mysqli_query($con, $sql1))
echo "Adding User Successful !";

else
echo "Adding User Failed !";

}

?>