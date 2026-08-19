1) No Tests => before refactor make tests
2) Intellij added gradle/wrapper/gradle-wrapper.jar and gradle-wrapper.properties => why, check this out
3) Project has to run with Java 17 or higher => add check for this requirement + tests


4) Input is is hardcoded in Main but spec say an input file is provided. Include file reading.
5) Main does everything. Refactor to separate classes.

6) InputParser works with indexes => caller has to now convention, parselawn reeds element 0, parseMowers reeds sublisht(1,size)

7) mowers move sequentially => stated in specification, but in current implementation this has no observable effect => each mower's result is independent of the others, two mowers can end up on the same square.
Is a mower supposed to treat an occupied square the way it treats a lewn edge. What if the mower is spawn on the other mower?
=> for now i chose to not care about this.

8) Direction is an empty enum , better to add the movement knowledge to the enum

