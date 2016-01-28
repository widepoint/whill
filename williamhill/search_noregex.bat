
REM Simple batch file to run the file searcher utility

java -Dregex.supported=false -cp "target/williamshill-0.0.1-SNAPSHOT.jar" co.uk.widepoint.filesearch.FileSearcher %1 %2 %3 %4 %5