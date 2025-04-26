This project shows a setup of a Java project for Cucumber and Mockito. It works together with both JUnit 4 and JUnit 5 and JDK >= 21. 

All the tests should pass in your favourite IDE
and when running `mvn test`. If not, please contact me (huba@dtu.dk). 

You can use the existing tests as a starting point
for your own tests.

Remember not to do too much in a single step. Aim to progress from one functioning system to the next with small, incremental changes. Avoid leaving the system in a broken state for an extended period, as this makes identifying errors much harder. It’s crucial to know exactly what change caused a working project to fail. When in doubt, revert to the last working version and proceed again in smaller, manageable steps.

For using Cucumber in Eclipse, you want to install the Cucumber plugin. In Eclipse, installing the Cucumber plugin is offered to you once you try to open a file with extension .feature. After the plugin is installled and Eclipse is restarted, you can select the project and then choose in the right button menu "Configure::Convert to Cucumber Project ...". This allows you later in a feature file to jump directly to the corresponding step definitions by using Crtl when selecting a step definition and then clicking.

When you use this project as a starting point, please remember to rename the project in the pom.xml file (e.g. artefact-id) and remember to remove those feature files and classes that were just included for demonstration purposes. That is, make sure your code is cleaned up and all unnecessary code is removed.

**Important for IntelliJ**, rebuild the project from the build menu after loading the example to ensure that IntelliJ finds the step definition classes. You can also edit the run configuration to make sure that the correct build function is called for the Cucumber Java configuration. That build function needs to be "build project" instead of just "build". One can also update the Cucumber Java build configuration template to replace "build" by "build project".

![screenshot of the configuration editor](img/build-configuration.jpg)