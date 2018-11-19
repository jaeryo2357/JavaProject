<?php

    $Id = $_GET['ID']; 
     $Pw = $_GET['PWD']; 
      $Name = $_GET['NAME']; 
  
    
    require_once('dbConnect.php');

  $sql = "SELECT * FROM User WHERE ID = '$Id'";
 $res =mysqli_query($con,$sql);

if($res->num_rows ===0)
 { 
    $sql="INSERT INTO User (ID,PW,NAME) Values ('$Id','$Pw','$Name')";
    if(mysqli_query($con,$sql))
      $result= array("result" => "100");//100이면 로그인 성공
    else
      $result= array("result" => "50"); // 50이면 db 삽입 실패
}
else{
  $result= array("result" => "10");//10이면 ID 중복
}
  $output=json_encode($result);
  echo urldecode($output);
  mysqli_close($con);
 ?>
