pipeline {
    agent any

    stages {
        stage('First') {
            steps {
                git 'https://github.com/ertugrulsmz/IPL-Dashboard'
                bat 'mvn clean compile'

            }
        }
    }
}
