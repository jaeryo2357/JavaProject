<?php

	require_once('dbConnect.php');

    $Id=$_GET['ID'];
    $W_Id=$_GET['W_ID'];
    
    $sql="SELECT ID_Key from User where ID='$Id'";


    $res=mysqli_query($con,$sql);

    $row = mysqli_fetch_array($res);

    $Id=$row['ID_Key'];


     $sql="DELETE FROM Wish where User_ID='$Id' and Webtoon_ID='$W_Id'";
   


	
    if(mysqli_query($con,$sql))
      $result= array("result" => "true");//100이면 로그인 성공
    else
      $result= array("result" => "false"); // 50이면 db 삽입 실패
 
     echo json_encode($result,JSON_UNESCAPED_UNICODE);
  
    mysqli_close($con);
   
 
 mysqli_close($con);
 

