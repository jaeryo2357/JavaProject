<?php

	require_once('dbConnect.php');

    $M_Id=$_GET['M_ID'];
    $A_Id=$_GET['A_ID'];
    

     $sql="DELETE FROM Follow WHERE Follower='$M_Id' AND Following='$A_Id'";
   


	
   if(mysqli_query($con,$sql))
      $result= array("result" => "true");//100이면 로그인 성공
    else
      $result= array("result" => "false"); // 50이면 db 삽입 실패
 
echo json_encode($result,JSON_UNESCAPED_UNICODE);
   
 
 mysqli_close($con);
 

