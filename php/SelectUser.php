<?php

	require_once('dbConnect.php');

    $Id=$_GET['ID'];
    
    
    $sql="SELECT * from User where ID='$Id'";


    $res=mysqli_query($con,$sql);


        $row = mysqli_fetch_array($res);
      
          $M_ID=$row['ID_Key'];
          $row_array['ID_Key']=$row['ID_Key'];
          $row_array['NAME']=$row['NAME'];
         
          $sql="SELECT Webtoon.GENRE FROM Wish,Webtoon Where Wish.Webtoon_ID=Webtoon.ID AND USER_ID='$M_ID'";


          $res=mysqli_query($con,$sql);

          $row_array['Wish_Num']=$res->num_rows;

          $D_row=array();
          while($row2=mysqli_fetch_array($res))
          {
            $row_wish['GENRE']=$row2['GENRE'];
            array_push($D_row,$row_wish);
          }

          

          $row_array['WishArray']=$D_row;

      
     echo json_encode($row_array,JSON_UNESCAPED_UNICODE);
  
    mysqli_close($con);
   
 
 mysqli_close($con);
 

