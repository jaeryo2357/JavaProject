<?php

	require_once('dbConnect.php');

    $Id=$_GET['ID'];

    
      $sql="SELECT * FROM Wish,Webtoon Where Wish.Webtoon_ID=Webtoon.ID AND USER_ID='$Id'";

    $res=mysqli_query($con,$sql);

    $D_row=array();
     while($row=mysqli_fetch_array($res))
          {
             $row_array['ID']=$row['ID'];
        $row_array['Title']=$row['TITLE'];
        $row_array['ByName']=$row['BY_NAME'];
        $row_array['big_image']=$row['BIG_IMAGE'];
        $row_array['small_image']=$row['SMALL_IMAGE'];
        $row_array['Explan']=$row['W_EXPLAIN'];
        $row_array['Genre']=$row['GENRE'];
            array_push($D_row,$row_array);
          }
    
 
     echo json_encode($D_row,JSON_UNESCAPED_UNICODE);
  
    mysqli_close($con);
   
 
 mysqli_close($con);
 

