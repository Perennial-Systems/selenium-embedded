# Selenium Embedded

A sample Selenium project that embeds Chrome and Firefox drivers for better portability.

## Highlights

This projects builds on the sample Selenium reference project and adds support for reusability.

1. `SampleClass` - The base class which handles parsing the browser name passed and extracting the drivers from the JAR to the filesystem. It also has reusable methods that allow assertion of things. There are only 4 methods defined now but this is meant to be updated with more methods. The class also has a run method which can be overridden by the user to write tests as per requirement.
2. `EntryPoint` - The execution class which will override the `run` method and add the logic needed for the current test scenario.

Mainintaining this sort of a structure allows us to ensure code-reusability and also allows the user to concentrate on writing business logic instead of wasting time on boilerplate.

