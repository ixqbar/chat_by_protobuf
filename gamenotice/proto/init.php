<?php
/**
 * 解析协议 生成PHP类文件
 */
require_once('../lib/googlebuf/pb_parser.php');
$parser_handle = new PBParser();
$parser_handle->parse('./message_php.proto');
?>
