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
    echo json_encode(array('RESULT_CODE' => 1, 'PROPS' => 'What are u trying to do??? don\'t joke with my validation'));
    die();
}


$response = json_encode(
    array('RESULT_CODE' => 0,
          'PROPS' =>
              array('x' => $x,
                  'y' => $y,
                  'r' => $r,
                  'result' => true,
                  'currentTime' => date("Y-m-d H:i:s"),
                  'computedTime' => (microtime() - $start)
              )
    )
);

echo $response;