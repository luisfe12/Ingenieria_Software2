<?php
error_reporting(E_ALL);
ini_set('display_errors', 1);

$user = 'postgres';
$password = 'unsa';
$db = 'Farmacia';
$port = 5432;
$host = 'localhost';
$strCnx = "host = $host port = $port dbname = $db user = $user password = $password";

try
{
    $cnx = pg_connect($strCnx) or die ("Error conexion. ". pg_last_error());
}
catch(Exception $e)
{
    echo 'Exception', $e->getMessage();
}

try
{
    $codigo_historia = $_POST['codigo_historia'];
    $codigo_usuario = $_POST['codigo_usuario'];
    $sql = "INSERT INTO usuario VALUES ('$codigo_historia', '$codigo_usuario');" ;
    $query = pg_query($cnx, $sql);
    $result = (int) pg_affected_rows($query);
    if($result > 0)
        echo "insertado";
    else
        echo "no insertado";
    pg_close($cnx);
}
catch(Exception $e)
{
    echo 'Exception: ' , $e->getMessage(); 
}
?>