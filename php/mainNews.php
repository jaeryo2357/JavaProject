<?php

	require_once('dbConnect.php');


     $sql="SELECT * from News";
 

	
 	$res = mysqli_query($con,$sql);
 
 	$D_row=array();
 	while($row = mysqli_fetch_array($res)){
 
        $row_array['image']=$row['Main_Image'];
       
      array_push($D_row,$row_array);

 }
 
   echo json_encode($D_row,JSON_UNESCAPED_UNICODE);
   
 
 mysqli_close($con);
 

