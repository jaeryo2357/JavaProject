<?php

	require_once('dbConnect.php');

    $Id=$_GET['ID'];
    $W_Id=$_GET['W_ID'];
    
    $sql="SELECT ID_Key from User where ID='$Id'";


    $res=mysqli_query($con,$sql);

    $row = mysqli_fetch_array($res);

    $Id=$row['ID_Key'];


     $sql="SELECT * FROM Wish where User_ID='$Id' and Webtoon_ID='$W_Id'";
   
      $res=mysqli_query($con,$sql);

      if($res->num_rows===0)
      {
        $result=array("result"=>"false");

      }else
      $result=array("result"=>"true");

	   
 
     echo json_encode($result,JSON_UNESCAPED_UNICODE);
  
    mysqli_close($con);
   
 
 mysqli_close($con);
 

