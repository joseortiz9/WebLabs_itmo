<?php
$start = microtime();
$x = $_POST['x'];
$y = $_POST['y'];
$r = $_POST['r'];
header('Content-type: application/json');

$argsX = array('-5', '-4', '-3', '-2', '-1', '0', '1', '2', '3');
$argsR = array('1', '1.5', '2', '2.5', '3');

$validatedX = true;
foreach ($x as $val) {
    if (filter_var($val, FILTER_VALIDATE_FLOAT) === FALSE or !in_array($val, $argsX)) {
        $validatedX = false;
        break;
    }
}

if (!$validatedX or !in_array($r, $argsR) or ($y < -5 or $y > 3)
    or filter_var($y, FILTER_VALIDATE_FLOAT) === FALSE or filter_var($r, FILTER_VALIDATE_FLOAT) === FALSE) {
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