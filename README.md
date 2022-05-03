# code_assignment
## Most Active Cookie
Given a cookie log file in the following format:
cookie,timestamp 
AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00
SAZuXPGUrfbcn5UA,2018-12-09T10:13:00+00:00
5UAVanZf6UtGyKVS,2018-12-09T07:25:00+00:00
AtY0laUfhglK3lC7,2018-12-09T06:19:00+00:00
SAZuXPGUrfbcn5UA,2018-12-08T22:03:00+00:00
4sMM2LxV07bPJzwf,2018-12-08T21:30:00+00:00
fbcn5UAVanZf6UtG,2018-12-08T09:30:00+00:00
4sMM2LxV07bPJzwf,2018-12-07T23:30:00+00:00
Write a command line program in your preferred language to process the log file and return the most active
cookie for a specific day. Please include a -f parameter for the filename to process and a -d parameter to
specify the date.
e.g. we’d execute your program like this to obtain the most active cookie for 9th Dec 2018.
$ ./[command] -f cookie_log.csv -d 2018-12-09
And it would write to stdout:
AtY0laUfhglK3lC7
We define the most active cookie as one seen in the log the most times during a given day.

## Assumptions:
* If multiple cookies meet that criteria, please return all of them on separate lines.
* Please only use additional libraries for testing, logging and cli-parsing. There are libraries for most
  languages which make this too easy (e.g pandas) and we’d like you to show off your coding skills.
* You can assume -d parameter takes date in UTC time zone.
* You have enough memory to store the contents of the whole file.
* Cookies in the log file are sorted by timestamp (most recent occurrence is the first line of the file).
* We're looking for a concise, maintainable, extendable and correct solution. 

We're hoping you'll deliver your solution as production grade code and demonstrate:
* good testing practices
* knowledge of build systems, testing frameworks, etc.
* clean coding practices (meaningful names, clean abstractions, etc.)

Please use a programming language you’re very comfortable with. The next stage of the interview
will involve extending your code.
