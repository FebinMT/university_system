<?php

require_once('init.php');

$user_type = $_POST["user_type"];
$user_id = $_POST["user_id"];
$password = $_POST["password"];


if($user_type === "admin"){

$sql1 = "SELECT * FROM admin WHERE id = '$user_id' AND password = '$password';";

$result1 = mysqli_query($con, $sql1);

if(mysqli_num_rows($result1)>0)
echo "Login Successful !";

else
echo "Id and Password does not match !";

}


else if($user_type === "college"){

$sql1 = "SELECT * FROM college WHERE id = '$user_id' AND password = '$password';";

$result1 = mysqli_query($con, $sql1);

if(mysqli_num_rows($result1)>0)
echo "Login Successful !";

else
echo "Id and Password does not match !";

}


else if($user_type === "student"){

$sql1 = "SELECT * FROM student WHERE id = '$user_id' AND password = '$password';";

$result1 = mysqli_query($con, $sql1);

if(mysqli_num_rows($result1)>0)
echo "Login Successful !";

else
echo "Id and Password does not match !";

}


?>