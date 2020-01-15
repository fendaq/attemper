CREATE TABLE log_op (
  ID varchar(64) COLLATE utf8_bin NOT NULL,
  TENANT_ID varchar(64) COLLATE utf8_bin DEFAULT NULL,
  CLASS_NAME varchar(255) COLLATE utf8_bin DEFAULT NULL,
  METHOD varchar(255) COLLATE utf8_bin DEFAULT NULL,
  TAG varchar(255) COLLATE utf8_bin DEFAULT NULL,
  OPERATION varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PATH varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PARAM text COLLATE utf8_bin,
  RESULT text COLLATE utf8_bin,
  CODE int(11) DEFAULT NULL,
  MSG text COLLATE utf8_bin,
  HANDLE_TIME bigint(13) DEFAULT NULL,
  DURATION varchar(30) COLLATE utf8_bin DEFAULT NULL,
  IP varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;