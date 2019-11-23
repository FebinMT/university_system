<?php

require_once('init.php');

$student_id = $_POST["student_id"];
$message = $_POST["message"];


$sql1 = "INSERT INTO report(student_id, message) VALUES ('$student_id', '$message')";

if(mysqli_query($con, $sql1))
echo "Reporting Successful !";

else
echo "Reporting Failed !";


?>