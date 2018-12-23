<?php

	require_once('dbConnect.php');

    $Se=$_GET['Search'];
   
    $result=array();
 	$sql="SELECT * FROM User Where Name LIKE '%$Se%'";
 	$res=mysqli_query($con,$sql);
 	$D_row=array();
          while($row=mysqli_fetch_array($res))
          {
            $row_array['ID_Key']=$row['ID_Key'];
            $row_array['ID']=$row['ID'];
            $row_array['Name']=$row['NAME'];
            array_push($D_row,$row_array);
          }

          $result['UserArray']=$D_row;

     	$sql="SELECT * FROM Webtoon where TITLE LIKE '%$Se%'";
 	$res=mysqli_query($con,$sql);
 	$D_row2=array();
          while($row2=mysqli_fetch_array($res))
          {
            $row_array2['W_ID']=$row2['ID'];
            $row_array2['W_Title']=$row2['TITLE'];
            $row_array2['W_ByName']=$row2['BY_NAME'];
            $row_array2['W_Image']=$row2['SMALL_IMAGE'];
            $row_array2['W_Explan']=$row2['W_EXPLAIN'];
            $row_array2['W_Genre']=$row2['GENRE'];
            array_push($D_row2,$row_array2);
          }

          $result['WebtoonArray']=$D_row2;






echo json_encode($result,JSON_UNESCAPED_UNICODE);
   
 
 mysqli_close($con);
 

