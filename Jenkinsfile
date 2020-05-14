pipeline{
    agent any

    stages {
        stage('Compile Stage'){
            withMaven(maven: 'MAVEN_HOME'){
                sh 'mvn clean compile'
            }
        }

        stage('Testing Stage'){
                withMaven(maven: 'MAVEN_HOME'){
                    sh 'mvn test -P Regression'
                }
        }

        stage('Email report'){
                emailext body: '''<html>
                <head>
                	<title>Smoke Test report report</title>
                </head>
                <body>
                <h1>Build status: $BUILD_STATUS</h1>
                <br/>
                <br/>
                <h3>Click to view cucumber reports for each browser:</h3>
                <br/>
                <h4>Chrome test results</4h>
                <br/>
                http://ec2-34-228-187-40.compute-1.amazonaws.com/reports/pipeline-reports/chrome/${BUILD_NUMBER}/overview-features.html
                <br/>
                <h4>Firefox test results</4h>
                <br/>
                http://ec2-34-228-187-40.compute-1.amazonaws.com/reports/pipeline-reports/firefox/${BUILD_NUMBER}/overview-features.html
                <br/>
                </body>
                </html>''', subject: '$BUILD_STATUS', to: 'akyyev222@gmail.com'
            }
    }
}