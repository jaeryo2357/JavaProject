<?php

	require_once('dbConnect.php');

    $Id=$_GET['ID'];
    $php=$_GET['php'];
  
    if(!strcmp($php,'Follower'))
        $php2='Following';
    else
        $php2='Follower';
    $sql="SELECT User.ID_Key,User.NAME,User.ID FROM Follow,User Where Follow.$php2=User.ID_Key AND Follow.$php='$Id'";


    $res=mysqli_query($con,$sql);

    $D_row=array();
     while($row=mysqli_fetch_array($res))
          {
            $row_array['U_ID_Key']=$row['ID_Key'];
             $row_array['U_name']=$row['NAME'];
             $row_array['U_ID']=$row['ID'];
            array_push($D_row,$row_array);
          }
    
 
     echo json_encode($D_row,JSON_UNESCAPED_UNICODE);
  
    mysqli_close($con);
   
 
 mysqli_close($con);
 

