<?php

    $Name=$_GET['NAME'];


   require_once('dbConnect.php');
 

$sql="SELECT * FROM User WHERE NAME='$Name'";
$res=mysqli_query($con,$sql);

if($res->num_rows===0)
{
  $result =array("result"=>"true");
}else
 $result =array("result"=>"false");

   $output=json_encode($result);
   echo urldecode($output);
  $mysqli->close();
 ?>

