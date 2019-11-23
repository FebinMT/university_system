<?php

require_once('init.php');

$enter_type = $_POST["enter_type"];
$student_id = $_POST["student_id"];
$s1 = $_POST["s1"];
$s2 = $_POST["s2"];
$s3 = $_POST["s3"];
$s4 = $_POST["s4"];
$s5 = $_POST["s5"];
$s6 = $_POST["s6"];
$s7 = $_POST["s7"];
$s8 = $_POST["s8"];
$s9 = $_POST["s9"];

$sql = "SELECT * FROM student WHERE id = '$student_id'";

$result = mysqli_query($con, $sql);


if($enter_type === "0"){

if(mysqli_num_rows($result) > 0) {

$sql1 = "INSERT INTO external(student_id, s1, s2, s3, s4, s5, s6, s7, s8, s9) VALUES ('$student_id', '$s1', '$s2', '$s3', '$s4', '$s5', '$s6', '$s7', '$s8', '$s9')";

if(mysqli_query($con, $sql1))
echo "Entering Marks Successful !";

else
echo "Entering Marks Failed !";

}

else
echo "Student doesn't exist !";

}


else if($enter_type === "1"){

if(mysqli_num_rows($result) > 0) {

$sql1 = "INSERT INTO internal(id, student_id, s1, s2, s3, s4, s5, s6, s7, s8, s9) VALUES ('1', '$student_id', '$s1', '$s2', '$s3', '$s4', '$s5', '$s6', '$s7', '$s8', '$s9')";

if(mysqli_query($con, $sql1))
echo "Entering Marks Successful !";

else
echo "Entering Marks Failed !";

}

else
echo "Student doesn't exist !";

}


else if($enter_type === "2"){

if(mysqli_num_rows($result) > 0) {

$sql1 = "INSERT INTO internal(id, student_id, s1, s2, s3, s4, s5, s6, s7, s8, s9) VALUES ('2', '$student_id', '$s1', '$s2', '$s3', '$s4', '$s5', '$s6', '$s7', '$s8', '$s9')";

if(mysqli_query($con, $sql1))
echo "Entering Marks Successful !";

else
echo "Entering Marks Failed !";

}

else
echo "Student doesn't exist !";

}

else if($enter_type === "3"){

if(mysqli_num_rows($result) > 0) {

$sql1 = "INSERT INTO internal(id, student_id, s1, s2, s3, s4, s5, s6, s7, s8, s9) VALUES ('3', '$student_id', '$s1', '$s2', '$s3', '$s4', '$s5', '$s6', '$s7', '$s8', '$s9')";

if(mysqli_query($con, $sql1))
echo "Entering Marks Successful !";

else
echo "Entering Marks Failed !";

}

else
echo "Student doesn't exist !";

}


?>