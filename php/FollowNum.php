<?php

	require_once('dbConnect.php');

  $M_Id=$_GET['M_ID'];
   
    

  $sql="SELECT * FROM Follow WHERE Follower='$M_ID'";
   


	$D_row=array();
  $res=mysqli_query($con,$sql);
  $result= array("Follow" => $res->num_rows);
    array_push($D_row,$result);

  $sql="SELECT * FROM Follow WHERE Following='$M_ID'";
  $res=mysqli_query($con,$sql);
   $result= array("Follower" => $res->num_rows);
   array_push($D_row,$result);
 
echo json_encode($D_row,JSON_UNESCAPED_UNICODE);
   
 
 mysqli_close($con);
 

