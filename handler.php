<?php
$start = microtime();
$x = $_POST['x'];
$y = $_POST['y'];
$r = $_POST['r'];
header('Content-type: application/json');

$response = json_encode(array('RESULT_CODE' => 1, 'RESPONSE' => 'X= '.$x.', y='.$y.', r='.$r));

echo $response;