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
    $codigo_doctor = $_POST['codigo_doctor'];
    $dni_doctor = $_POST['dni_doctor'];
    $especialidad = $_POST['especialidad'];
    $sueldo = $_POST['sueldo'];
    $horario_entrada = $_POST['horario_entrada'];
    $horario_salida = $_POST['horario_salida'];
    $sql = "INSERT INTO doctor VALUES ('$codigo_doctor', '$dni_doctor', '$especialidad', $sueldo, '$horario_entrada', '$horario_salida');" ;
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