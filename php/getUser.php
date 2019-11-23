<?php

if($_SERVER['REQUEST_METHOD']=='GET'){

$user_type = $_GET['user_type'];
$user_id = $_GET['user_id'];

require_once('init.php');


if($user_type === "admin") {

$sql = "SELECT name FROM admin WHERE id = '".$user_id."'";
 
$r = mysqli_query($con,$sql);
 
$res = mysqli_fetch_array($r);

$result = array();
 
array_push($result,array(

	 "name"=>$res['name']

)
);
 
echo json_encode(array("result"=>$result));

}
 

else if($user_type === "college") {

$sql = "SELECT name FROM college WHERE id = '".$user_id."'";
 
$r = mysqli_query($con,$sql);
 
$res = mysqli_fetch_array($r);

$result = array();
 
array_push($result,array(

	 "name"=>$res['name']

)
);
 
echo json_encode(array("result"=>$result));

}


else if($user_type === "student") {

$sql = "SELECT s.name, s.dob, c.name as college, s.department, s.year, s.semester FROM student s, college c WHERE s.id = '".$user_id."' AND s.college_id = c.id";
 
$r = mysqli_query($con,$sql);
 
$res = mysqli_fetch_array($r);

$result = array();
 
array_push($result,array(

	 "name"=>$res['name'],
	 "dob"=>$res['dob'],
	 "college"=>$res['college'],
	 "department"=>$res['department'],
	 "year"=>$res['year'],
	 "semester"=>$res['semester'],


)
);
 
echo json_encode(array("result"=>$result));

}

mysqli_close($con);
 
}

?>
