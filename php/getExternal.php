<?php

if($_SERVER['REQUEST_METHOD']=='GET'){

$student_id = $_GET['student_id'];

require_once('init.php');

$sql = "SELECT e.s1 as es1, e.s2 as es2, e.s3 as es3, e.s4 as es4, e.s5 as es5, e.s6 as es6, e.s7 as es7, e.s8 as es8, e.s9 as es9, i1.s1 as i1s1, i1.s2 as i1s2, i1.s3 as i1s3, i1.s4 as i1s4, i1.s5 as i1s5, i1.s6 as i1s6, i1.s7 as i1s7, i1.s8 as i1s8, i1.s9 as i1s9, i2.s1 as i2s1, i2.s2 as i2s2, i2.s3 as i2s3, i2.s4 as i2s4, i2.s5 as i2s5, i2.s6 as i2s6, i2.s7 as i2s7, i2.s8 as i2s8, i2.s9 as i2s9, i3.s1 as i3s1, i3.s2 as i3s2, i3.s3 as i3s3, i3.s4 as i3s4, i3.s5 as i3s5, i3.s6 as i3s6, i3.s7 as i3s7, i3.s8 as i3s8, i3.s9 as i3s9 FROM external e, internal i1, internal i2, internal i3 WHERE e.student_id = '$student_id' AND (i1.id = 1 AND i1.student_id = '$student_id') AND (i2.id = 2 AND i2.student_id = '$student_id') AND (i3.id = 3 AND i3.student_id = '$student_id')";
 
$r = mysqli_query($con,$sql);
 
$res = mysqli_fetch_array($r);


$s1 = ($res['es1']*0.8) + ($res['i1s1']*0.075) + ($res['i2s1']*0.05) + ($res['i3s1']*0.075);

$s2 = ($res['es2']*0.8) + ($res['i1s2']*0.075) + ($res['i2s2']*0.05) + ($res['i3s2']*0.075);

$s3 = ($res['es3']*0.8) + ($res['i1s3']*0.075) + ($res['i2s3']*0.05) + ($res['i3s3']*0.075);

$s4 = ($res['es4']*0.8) + ($res['i1s4']*0.075) + ($res['i2s4']*0.05) + ($res['i3s4']*0.075);

$s5 = ($res['es5']*0.8) + ($res['i1s5']*0.075) + ($res['i2s5']*0.05) + ($res['i3s5']*0.075);

$s6 = ($res['es6']*0.8) + ($res['i1s6']*0.075) + ($res['i2s6']*0.05) + ($res['i3s6']*0.075);

$s7 = ($res['es7']*0.8) + ($res['i1s7']*0.075) + ($res['i2s7']*0.05) + ($res['i3s7']*0.075);

$s8 = ($res['es8']*0.8) + ($res['i1s8']*0.075) + ($res['i2s8']*0.05) + ($res['i3s8']*0.075);

$s9 = ($res['es9']*0.8) + ($res['i1s9']*0.075) + ($res['i2s9']*0.05) + ($res['i3s9']*0.075);


$g1 = calculateGrade($s1);

$g2 = calculateGrade($s2);

$g3 = calculateGrade($s3);

$g4 = calculateGrade($s4);

$g5 = calculateGrade($s5);

$g6 = calculateGrade($s6);

$g7 = calculateGrade($s7);

$g8 = calculateGrade($s8);

$g9 = calculateGrade($s9);


$gp1 = calculateGradePoint($g1);

$gp2 = calculateGradePoint($g2);

$gp3 = calculateGradePoint($g3);

$gp4 = calculateGradePoint($g4);

$gp5 = calculateGradePoint($g5);

$gp6 = calculateGradePoint($g6);

$gp7 = calculateGradePoint($g7);

$gp8 = calculateGradePoint($g8);

$gp9 = calculateGradePoint($g9);

$gpa = ($gp1 + $gp2 + $gp3 + $gp4 + $gp5 + $gp6 + $gp7 + $gp8 + $gp9)/10;


$result = array();
 
array_push($result,array(

	 "s1"=>$g1,
	 "s2"=>$g2,
	 "s3"=>$g3,
	 "s4"=>$g4,
	 "s5"=>$g5,
	 "s6"=>$g6,
	 "s7"=>$g7,
	 "s8"=>$g8,
	 "s9"=>$g9,
	 "gpa"=>$gpa,	

)
);
 
echo json_encode(array("result"=>$result));

mysqli_close($con);
 
}

function calculateGrade($value) {

$grade = "";

if($value >= 90)
$grade = "S";

else if($value >= 80 && $value < 90)
$grade = "A";

else if($value >= 70 && $value < 80)
$grade = "B";

else if($value >= 60 && $value < 70)
$grade = "C";

else if($value >= 55 && $value < 60)
$grade = "D";

else if($value >= 50 && $value < 55)
$grade = "E";

else
$grade = "U";

return $grade;

}

function calculateGradePoint($value) {

$gradepoint = "";

if($value === "S")
$gradepoint = "10";

else if($value === "A")
$gradepoint = "9";

else if($value === "B")
$gradepoint = "8";

else if($value === "C")
$gradepoint = "7";

else if($value === "D")
$gradepoint = "6";

else if($value === "E")
$gradepoint = "5";

else
$gradepoint = "0";

return $gradepoint ;

}



?>
