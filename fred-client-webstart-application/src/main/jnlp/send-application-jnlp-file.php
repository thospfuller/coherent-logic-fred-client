<?php
ini_set('display_errors', 1);
error_reporting(E_ALL|E_STRICT);

header('Content-type: application/x-java-jnlp-file');
header('Content-Disposition: attachment; filename="application.jnlp"');
readfile('application.jnlp');
?>