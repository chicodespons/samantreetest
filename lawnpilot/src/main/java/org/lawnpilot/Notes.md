1) No Tests => before refactor make tests
2) Intellij added gradle/wrapper/gradle-wrapper.jar and gradle-wrapper.properties => why, check this out
3) Project has to run with Java 17 or higher => add check for this requirement + tests


4) Input is is hardcoded in Main but spec say an input file is provided. Include file reading.
5) Main does everything. Refactor to separate classes.

6) InputParser works with indexes => caller has to now convention, parselawn reeds element 0, parseMowers reeds sublisht(1,size)
