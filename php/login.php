<?php

    $Id=$_GET['ID'];
    $Pw=$_GET['PWD'];


   require_once('dbConnect.php');
 

$sql="SELECT * FROM User WHERE ID='$Id' AND PW='$Pw'";
$res=mysqli_query($con,$sql);

if($res->num_rows===0)
{
  $result =array("result"=>"false");
}else
 $result =array("result"=>"true");

   $output=json_encode($result);
   echo urldecode($output);
  $mysqli->close();
 ?>

