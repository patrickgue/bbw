<?php
include 'php/utils/sql_util.php';
$resultset = $connection->query("SHOW TABLES");
echo "Test";
while($obj = $resultset->fetch_object()){
    var_dump($obj);
    echo "<br/>";
}

 ?>
