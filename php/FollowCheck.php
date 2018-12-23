<?php

	require_once('dbConnect.php');

    $M_Id=$_GET['M_ID'];
    $A_Id=$_GET['A_ID'];
    
    $sql="SELECT * from User where ID='$M_Id'";


    $res=mysqli_query($con,$sql);


    $row = mysqli_fetch_array($res);
      
    $M_Id=$row['ID_Key'];


    $sql="SELECT * from User where ID='$A_Id'";


    $res=mysqli_query($con,$sql);


    $row = mysqli_fetch_array($res);
      
    $A_Id=$row['ID_Key'];

     $sql="SELECT * FROM Follow WHERE Follower='$M_Id' AND Following='$A_Id'";
   

     $res=mysqli_query($con,$sql);
	
   if($res->num_rows===0)
      $result= array("result" => "false");//100이면 로그인 성공
    else
      $result= array("result" => "true"); // 50이면 db 삽입 실패
 
echo json_encode($result,JSON_UNESCAPED_UNICODE);
   
 
 mysqli_close($con);
 

