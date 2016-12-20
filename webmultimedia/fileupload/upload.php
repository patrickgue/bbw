<?php



   
   echo "start script<br/>";

   if(isset($_POST["submit"])) {


   $target_dir = "uploads/";
   $target_file = $target_dir . basename($_FILES["file"]["name"]);

   echo $target_file . "<br/>" . $_FILES["file"]["tmp_name"] . "<br/>";
   
   if(move_uploaded_file($_FILES["file"]["tmp_name"], $target_file)) {
     echo "successful";
   }
   else {
     echo "failure";
   }

   }

   

?>
