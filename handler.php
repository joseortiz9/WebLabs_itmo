<?php
$start = microtime();
$x = $_POST['x'];
$y = $_POST['y'];
$r = $_POST['r'];
header('Content-type: application/json');

$argsXandY = array('options' => array('min_range' => -5, 'max_range' => 3));
$argsR = array('options' => array('min_range' => 1, 'max_range' => 3));

$validatedX = true;
foreach ($x as $val) {
    if (filter_var($val, FILTER_VALIDATE_FLOAT, $argsXandY) === FALSE) {
        $validatedX = false;
        break;
    }
}

if (!$validatedX or filter_var($y, FILTER_VALIDATE_FLOAT, $argsXandY) === FALSE or filter_var($r, FILTER_VALIDATE_FLOAT, $argsR) === FALSE) {
    echo "{\"RESULT_CODE\": \"". 1 ."\", \"RESULTS\": \"What are u trying to do? something is wrong with input\"}";
    die();
}

$response = "{\"RESULT_CODE\":\"". 0 ."\", \"RESULTS\": [";

foreach ($x as $valX) {
    $data = "{ \"x\":" . "\"" . $valX . "\""
        . ", \"y\":" . "\"" . $y . "\""
        . ", \"r\":" . "\"" . $r . "\""
        . ", \"result\":" . "\"" . checkInsideFunc($valX, $y, $r) . "\""
        . ", \"currentTime\":" . "\"" . date("Y-m-d H:i:s") . "\""
        . ", \"computedTime\":" . "\"". (microtime() - $start) . "\""
        . "}";
    $data .= ($valX === end($x)) ? "" : ",";
    $response .= $data;
}
$response .= "]}";

echo $response;


function checkInsideFunc($x, $y, $r) {
    if (($x<=0 && $y>=0 && $y <= ($x + $r/2)) //linear function
        or ($y>=0 && $x>=0 && $y <= sqrt($r*$r - $x*$x)) //circular function
        or ($y<=0 && $x>=0 &&  $y>=-$r && $x<=$r)) //lines on r
        return 'true';

    return 'false';
}