<?php
$start = microtime();
$x = $_POST['x'];
$y = $_POST['y'];
$r = $_POST['r'];
header('Content-type: application/json');



$response = json_encode(
    array('RESULT_CODE' => 1,
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