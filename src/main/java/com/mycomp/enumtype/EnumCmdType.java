package com.mycomp.enumtype;

/**
 * Type of command line arguments indicate the various way accepting the command line arguments
 *  VALID : -f filename -d YYYY-HH-DD
 *  USAGE: no of command like -f filename.
 *  INVALID_IND: if args -f filename -v or -f filename -cfg
 *  LOGICAL_DATE_EXCEPTION: if -f filename -d DD-MM-YYYY as the date format not correct.
 *                          correct for is YYYY-MM-DD
 *  LOGICAL_FILE_EXCEPTION: in case the file is not valid.
 *  KNOWN_ERROR: Any other error.
 *
 */
public enum EnumCmdType {
    VALID, USAGE,INVALID_IND, LOGICAL_DATE_EXCEPTION, LOGICAL_FILE_EXCEPTION,KNOWN_ERROR
}
