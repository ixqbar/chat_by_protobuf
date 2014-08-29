<?php
class ChatMessage extends PBMessage
{
  var $wired_type = PBMessage::WIRED_LENGTH_DELIMITED;
  public function __construct($reader=null)
  {
    parent::__construct($reader);
    $this->fields["1"] = "PBInt";
    $this->values["1"] = "";
    $this->fields["2"] = "PBString";
    $this->values["2"] = "";
    $this->values["2"] = new PBString();
    $this->values["2"]->value = "";
    $this->fields["3"] = "PBString";
    $this->values["3"] = "";
    $this->values["3"] = new PBString();
    $this->values["3"]->value = "";
    $this->fields["4"] = "PBString";
    $this->values["4"] = "";
    $this->values["4"] = new PBString();
    $this->values["4"]->value = "";
    $this->fields["5"] = "PBString";
    $this->values["5"] = "";
    $this->values["5"] = new PBString();
    $this->values["5"]->value = "0";
    $this->fields["6"] = "PBString";
    $this->values["6"] = "";
    $this->values["6"] = new PBString();
    $this->values["6"]->value = "";
  }
  function chatChannel()
  {
    return $this->_get_value("1");
  }
  function set_chatChannel($value)
  {
    return $this->_set_value("1", $value);
  }
  function fromId()
  {
    return $this->_get_value("2");
  }
  function set_fromId($value)
  {
    return $this->_set_value("2", $value);
  }
  function toId()
  {
    return $this->_get_value("3");
  }
  function set_toId($value)
  {
    return $this->_set_value("3", $value);
  }
  function name()
  {
    return $this->_get_value("4");
  }
  function set_name($value)
  {
    return $this->_set_value("4", $value);
  }
  function groupId()
  {
    return $this->_get_value("5");
  }
  function set_groupId($value)
  {
    return $this->_set_value("5", $value);
  }
  function message()
  {
    return $this->_get_value("6");
  }
  function set_message($value)
  {
    return $this->_set_value("6", $value);
  }
}
?>