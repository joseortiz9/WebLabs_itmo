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
    echo json_encode(array('RESULT_CODE' => 1, 'RESULTS' => 'What are u trying to do? something is wrong with input'));
    die();
}

$response = array('RESULT_CODE' => 0, 'RESULTS' => array());

foreach ($x as $valX) {
    $data = array('x' => $valX,
        'y' => $y,
        'r' => $r,
        'result' => checkInsideFunc($valX, $y, $r),
        'currentTime' => date("Y-m-d H:i:s"),
        'computedTime' => (microtime() - $start)
    );
    array_push($response['RESULTS'], $data);
}

echo json_encode($response);


function checkInsideFunc($x, $y, $r) {

    return false;
}