pipeline {
    agent any

    //stages
    stages {
        stage('Build') {
            steps {
                git 'https://github.com/ertugrulsmz/IPL-Dashboard'
                bat 'mvn clean compile'

            }
        }

        stage('Test'){

            steps{
                bat 'mvn test'
            }

            post{

                always{
                    junit '**/target/surefire-reports/TEST-*.xml'

                }
            }
        }

        stage('Publish'){

            steps{
                bat 'mvn package'
            }

            post{
                success{
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
