<?php

	require_once('dbConnect.php');

    $Id=$_GET['ID'];
    

     $sql="SELECT * from Webtoon0 where ID='$Id'";
   


	
 	$res = mysqli_query($con,$sql);
  
 	$D_row=array();
 	while($row = mysqli_fetch_array($res)){
      
     
          $row_array['W_ID']=$row['ID'];
          $row_array['ID']=$row['Content_Num'];
          $row_array['title']=$row['Title'];
          $row_array['Release']=$row['Release_W'];
          $row_array['last']=$row['LastIndex'];
       
          array_push($D_row,$row_array);
       
 }
 
echo json_encode($D_row,JSON_UNESCAPED_UNICODE);
   
 
 mysqli_close($con);
 

