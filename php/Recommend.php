<?php

	 require_once('dbConnect.php');

    $Id=$_GET['ID'];
  
    $sql="SELECT ID_Key from User where ID='$Id'";


    $res=mysqli_query($con,$sql);

    $row = mysqli_fetch_array($res);

    $Id=$row['ID_Key'];
    
    $sql="SELECT Webtoon.GENRE,COUNT(Webtoon.GENRE) FROM Wish,Webtoon Where Wish.Webtoon_ID=Webtoon.ID and User_ID='$Id' GROUP BY Webtoon.GENRE ORDER BY 2 DESC";

    $res=mysqli_query($con,$sql);

    $D_row=array();
   
   for($i=0;$i<2&&$row=mysqli_fetch_array($res);$i++){
      
      $genre=$row['GENRE'];
      $sql2="SELECT * from Webtoon where GENRE='$genre' order by rand() limit 3";
      $res2=mysqli_query($con,$sql2);      
        while($row = mysqli_fetch_array($res2)){
 
        $row_array['ID']=$row['ID'];
        $row_array['Title']=$row['TITLE'];
        $row_array['ByName']=$row['BY_NAME'];
        $row_array['big_image']=$row['BIG_IMAGE'];
        $row_array['small_image']=$row['SMALL_IMAGE'];
        $row_array['Explan']=$row['W_EXPLAIN'];
        $row_array['Genre']=$row['GENRE'];
          array_push($D_row,$row_array);
    }
  }
      

     echo json_encode($D_row,JSON_UNESCAPED_UNICODE);
  
    mysqli_close($con);
   
  

