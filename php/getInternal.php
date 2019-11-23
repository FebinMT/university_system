<?php

if($_SERVER['REQUEST_METHOD']=='GET'){

$student_id = $_GET['student_id'];

require_once('init.php');

$sql = "SELECT i1.s1 as i1s1, i1.s2 as i1s2, i1.s3 as i1s3, i1.s4 as i1s4, i1.s5 as i1s5, i1.s6 as i1s6, i1.s7 as i1s7, i1.s8 as i1s8, i1.s9 as i1s9, i2.s1 as i2s1, i2.s2 as i2s2, i2.s3 as i2s3, i2.s4 as i2s4, i2.s5 as i2s5, i2.s6 as i2s6, i2.s7 as i2s7, i2.s8 as i2s8, i2.s9 as i2s9, i3.s1 as i3s1, i3.s2 as i3s2, i3.s3 as i3s3, i3.s4 as i3s4, i3.s5 as i3s5, i3.s6 as i3s6, i3.s7 as i3s7, i3.s8 as i3s8, i3.s9 as i3s9 FROM internal i1, internal i2, internal i3 WHERE (i1.id = 1 AND i1.student_id = '$student_id') AND (i2.id = 2 AND i2.student_id = '$student_id') AND (i3.id = 3 AND i3.student_id = '$student_id')";
 
$r = mysqli_query($con,$sql);
 
$res = mysqli_fetch_array($r);


$s1 = ($res['i1s1']*0.075) + ($res['i2s1']*0.05) + ($res['i3s1']*0.075);

$s2 = ($res['i1s2']*0.075) + ($res['i2s2']*0.05) + ($res['i3s2']*0.075);

$s3 = ($res['i1s3']*0.075) + ($res['i2s3']*0.05) + ($res['i3s3']*0.075);

$s4 = ($res['i1s4']*0.075) + ($res['i2s4']*0.05) + ($res['i3s4']*0.075);

$s5 = ($res['i1s5']*0.075) + ($res['i2s5']*0.05) + ($res['i3s5']*0.075);

$s6 = ($res['i1s6']*0.075) + ($res['i2s6']*0.05) + ($res['i3s6']*0.075);

$s7 = ($res['i1s7']*0.075) + ($res['i2s7']*0.05) + ($res['i3s7']*0.075);

$s8 = ($res['i1s8']*0.075) + ($res['i2s8']*0.05) + ($res['i3s8']*0.075);

$s9 = ($res['i1s9']*0.075) + ($res['i2s9']*0.05) + ($res['i3s9']*0.075);



$result = array();
 
array_push($result,array(

	 "s1"=>((int)$s1),
	 "s2"=>((int)$s2),
	 "s3"=>((int)$s3),
	 "s4"=>((int)$s4),
	 "s5"=>((int)$s5),
	 "s6"=>((int)$s6),
	 "s7"=>((int)$s7),
	 "s8"=>((int)$s8),
	 "s9"=>((int)$s9),

)
);
 
echo json_encode(array("result"=>$result));

mysqli_close($con);
 
}

?>
