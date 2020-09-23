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
    $dni = $_POST['dni'];
    $nombre = $_POST['nombre'];
    $apellido_paterno = $_POST['apellido_paterno'];
    $apellido_materno = $_POST['apellido_materno'];
    $celular = $_POST['celular'];
    $sql = "INSERT INTO persona VALUES ('$dni', '$nombre', '$apellido_paterno', '$apellido_materno', '$celular');" ;
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