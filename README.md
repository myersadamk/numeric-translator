# numeric-translator
Exercise problem for translating Roman numerals.

When I'm asked to do a programming exercise (and time permits), I like to use it as an opportunity 
to learn and expand on my skills. For this project, I decided to use the latest [Spring Shell](https://docs.spring.io/spring-shell/docs/3.1.4/docs/index.html#what-is-spring-shell)
and learn my way around it. I could have done this in either Java or Kotlin, but I chose Kotlin
to continue building my knowledge around it.

At the risk of being too generic, I chose to name the project `numeric-translator` because it 
doesn't confine the project to one numeric system. I mostly want to demonstrate thoughtfulness.

## Building
This project uses Gradle as its build system. To build the project, run the following command:
```
./gradlew build
```

## Running
To begin the interactive shell, run the following command:
```
java -jar build/libs/numeric-translator-0.0.1-SNAPSHOT.jar
```

## Usage
The shell supports the following commands:
```
Built-In Commands
       help: Display help about available commands
       stacktrace: Display the full stacktrace of the last error.
       clear: Clear the shell screen.
       quit, exit: Exit the shell.
       history: Display or save the history of previously run commands
       version: Show version info
       script: Read and execute commands from a file.

Default
       to-roman-numeral: Converts a number to a Roman numeral
       from-roman-numeral: Converts a Roman numeral to a number
```

You can receive additional information about a command by typing `help <command>`:

```
shell:>help to-roman-numeral
NAME
       to-roman-numeral - Converts a number to a Roman numeral

SYNOPSIS
       to-roman-numeral [--value int]

OPTIONS
       --value or -v int
       The numeric value to convert
       [Mandatory]
```
For instance, here is a log of some sample commands:
```
shell:>to-roman-numeral 123
CXXIII
shell:>to-roman-numeral --value 13534
MMMMMMMMMMMMMDXXXIV
shell:>from-roman-numeral XIVV
19
shell:>from-roman-numeral invalid
Text must only contain valid roman numeral symbols
shell:>to-roman-numeral 123
CXXIII
shell:>to-roman-numeral --value 13534
MMMMMMMMMMMMMDXXXIV
shell:>from-roman-numeral XIVV
19
shell:>from-roman-numeral invalid
Text must only contain valid roman numeral symbols
```

## Things I didn't get around to
* I did not do a thorough QA of the shell experience - I'm sure there are rough edges.
* I wrote about 90% of the tests I would write for production code, so my coverage is not complete.
* I did not go back and take a thoughtful look over the work to see if it could use further
    refactoring. I'm sure there are things that could be improved, possibly big things, and I like 
    to review my own code before creating a pull request. I definitely welcome feedback.
* I couldn't get the non-interactive shell to work.
