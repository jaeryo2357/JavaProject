<?php

	require_once('dbConnect.php');

    $M_Id=$_GET['M_ID'];
    $A_Id=$_GET['A_ID'];
    

    $sql="SELECT * from User where ID='$M_Id'";


    $res=mysqli_query($con,$sql);


    $row = mysqli_fetch_array($res);
      
    $M_ID=$row['ID_Key'];
        
    $sql="INSERT INTO Follow(Follower,Following) Values($M_Id,$A_Id)";
   


	
   if(mysqli_query($con,$sql))
      $result= array("result" => "true");//100이면 로그인 성공
    else
      $result= array("result" => "false"); // 50이면 db 삽입 실패
 
echo json_encode($result,JSON_UNESCAPED_UNICODE);
   
 
 mysqli_close($con);
 

