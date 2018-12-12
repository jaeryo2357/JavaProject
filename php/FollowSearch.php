<?php

	require_once('dbConnect.php');

    $Id=$_GET['ID'];
    $php=$_GET['php'];
    
    $sql="SELECT * FROM Follow,User Where Follow.$php=User.ID_Key AND Follow.$php=$Id";


    $res=mysqli_query($con,$sql);

    $D_row=array();
     while($row=mysqli_fetch_array($res))
          {
            $row_array['U_ID']=$row['ID_Key'];
             $row_array['U_name']=$row['NAME'];
            array_push($D_row,$row_array);
          }
    
 
     echo json_encode($D_row,JSON_UNESCAPED_UNICODE);
  
    mysqli_close($con);
   
 
 mysqli_close($con);
 

