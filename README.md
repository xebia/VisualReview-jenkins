# VisualReview-jenkins
Jenkins plugin for easy access to VisualReview analysis results created by [VisualReview-protractor](https://github.com/xebia/visualreview-protractor/).

## How to run
Download the [latest release](https://github.com/xebia/VisualReview-jenkins/releases) and install it using Jenkins' manual plugin upload.
It's located in the 'Manage plugins' section of Jenkins' configuration.

When a Jenkins build has run a VisualReview analysis using [VisualReview-protractor](https://github.com/xebia/visualreview-protractor/),
icons appear in the build results linking straight to the analysis report.

## How to build
_Note:_ Requires Maven 3.x and Java 7 or higher.

- Run `mvn compile` in order to generate the plugin's i18n Messages class
- Run `mvn hpi:run` to start a Jenkins instance using this jenkins-plugin
- Run `mvn hpi:hpi` to create a `.hpi` package that can be installed using Jenkins' manual plugin uploader.

